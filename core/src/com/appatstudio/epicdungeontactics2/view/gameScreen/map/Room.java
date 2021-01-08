package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapGenerator;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoElementsLocations;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoEnemy;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoWalkableArray;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.CameraHandler;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions.ChangeState;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions.MoveToMapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions.SwitchMapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions.TurnAction;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions.TurnFinished;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions.TurnStarted;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.AutonomousCharacter;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue.TurnQueue;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.view.viewElements.game.BossHpBar;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import box2dLight.RayHandler;

import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_ALCHEMIST;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_BLACKSMITH;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_BUTCHER;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_MAGIC_SHOP;

public class Room {

    private SpriteDrawable mapDrawable;

    private static SpriteDrawable mapBorder;
    private static CoordsFloat borderCoords, borderSize;
    private static SpriteDrawable grid;

    private MapTile[][] mapTiles;
    private CoordsInt position;
    private RoomTypeEnum type;
    private RoomEnum roomEnum;
    private HashMap<DirectionEnum, Room> roomNodes;
    private Stage stageObject;

    private static GuiContainer guiContainer;

    private CharacterDrawable currentCharacterMoving;
    private Hero heroInRoom;
    private Array<CharacterDrawable> charactersInRoom;

    private RoomStateEnum roomState;
    private Array<Array<MapTile>> paths;

    private World world;
    private RayHandler rayHandler;
    private static Box2DDebugRenderer b2dr;

    private TurnQueue queue;

    private static BossHpBar bossHpBar;

    private boolean freezeTime = false;

    private Array<MapTile> cachedMovableTiles;
    private Array<MapTile> cachedAttackableTiles;

    private CharacterEnum npcMode = null;


    static {
        guiContainer = GuiContainer.getInstance();
        mapBorder = new SpriteDrawable(new Sprite(new Texture("maps/BORDER.png")));
        grid = new SpriteDrawable(new Sprite(new Texture("maps/GRID.png")));

        borderCoords = new CoordsFloat(
                WorldConfig.ROOM_POS_X - WorldConfig.ROOM_WIDTH_RES / 2f,
                WorldConfig.ROOM_POS_Y - WorldConfig.ROOM_HEIGHT_RES / 2f
        );
        borderSize = new CoordsFloat(
                WorldConfig.ROOM_WIDTH_RES * 2f,
                WorldConfig.ROOM_HEIGHT_RES * 2f
        );


    }

    public Room(RoomTypeEnum type, int stage, CoordsInt position, Stage stageObject) {
        roomNodes = new HashMap<>();
        roomNodes.put(DirectionEnum.TOP, null);
        roomNodes.put(DirectionEnum.RIGHT, null);
        roomNodes.put(DirectionEnum.BOTTOM, null);
        roomNodes.put(DirectionEnum.LEFT, null);

        this.roomEnum = MapGenerator.getRandomRoom(type, stage);

        this.position = position;
        this.type = type;

        this.roomState = type == RoomTypeEnum.FIRST_ROOM ? RoomStateEnum.CLEAN : RoomStateEnum.FIGHT;

        charactersInRoom = new Array<>();

        world = new World(new Vector2(0, 0), true);
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(LightsConfig.getAmbientColor(stage));
        b2dr = new Box2DDebugRenderer(); //todo

        mapDrawable = new SpriteDrawable(new Sprite(new Texture("maps/" + roomEnum.toString() + ".png")));

        mapTiles = new MapTile[WorldConfig.ROOM_WIDTH][WorldConfig.ROOM_HEIGHT];

        MapPathFindingFlags[][] walkableArray = MapInfoWalkableArray.getWalkableArray(roomEnum);

        for (int x = 0; x < WorldConfig.ROOM_WIDTH; x++) {
            for (int y = 0; y < WorldConfig.ROOM_HEIGHT; y++) {
                mapTiles[x][y] = new MapTile(x, y, walkableArray[WorldConfig.ROOM_HEIGHT - y - 1][x] != MapPathFindingFlags.NONE);
            }
        }

        MapElementAnimationEnum[][] animatedElements = MapInfoElementsLocations.getAnimatedElements(roomEnum);
        MapElementSpriteEnum[][] spriteElements = MapInfoElementsLocations.getSpriteElements(roomEnum);
        CharacterEnum[][] characters = MapInfoEnemy.getCharactersInfo(roomEnum);

        charactersInRoom.add(
                new Hero(StatTracker.getHero(),
                        new CoordsInt(WorldConfig.ROOM_WIDTH / 2, WorldConfig.ROOM_HEIGHT / 2),
                        rayHandler,
                        world,
                        this,
                        mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][(int) (WorldConfig.ROOM_HEIGHT / 2f)]));

        heroInRoom = (Hero) charactersInRoom.get(0);

        for (int x = 0; x < WorldConfig.ROOM_WIDTH; x++) {
            for (int y = 0; y < WorldConfig.ROOM_HEIGHT; y++) {
                if (animatedElements[y][x] != null)
                    mapTiles[x][WorldConfig.ROOM_HEIGHT - y - 1].setAnimatedElement(
                            new AnimatedElement(animatedElements[y][x],
                                    new CoordsFloat(
                                            WorldConfig.getTileCoord(x, WorldConfig.ROOM_HEIGHT - y - 1).x,
                                            WorldConfig.getTileCoord(x, WorldConfig.ROOM_HEIGHT - y - 1).y),
                                    rayHandler, world));

//                else if (spriteElements[y][x] != null) mapTiles[x][WorldConfig.ROOM_HEIGHT-y-1].setSpriteElement(
//                        new SpriteElement(spriteElements[y][x],
//                                new CoordsFloat(
//                                        WorldConfig.getTileCoord(x, y).x,
//                                        WorldConfig.getTileCoord(x, y).y),
//                                rayHandler, world));

                else if (characters[y][x] != null) {
                    boolean flag = true;
                    if (characters[y][x].toString().startsWith("NPC")) {
                        switch (characters[y][x]) {
                            case NPC_BUTCHER: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.BUTCHER) == 0) {
                                    flag = false;
                                }
                                else {
                                    guiContainer.createOrRefreshShop(characters[y][x]);
                                }
                                break;
                            }
                            case NPC_ALCHEMIST: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.ALCHEMIST) == 0) {
                                    flag = false;
                                }
                                else {
                                    guiContainer.createOrRefreshShop(characters[y][x]);
                                }
                                break;
                            }
                            case NPC_BLACKSMITH: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.BLACKSMITH) == 0) {
                                    flag = false;
                                }
                                else {
                                    guiContainer.createOrRefreshShop(characters[y][x]);
                                }
                                break;
                            }
                            case NPC_MAGIC_SHOP: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.MAGIC_SHOP) == 0) {
                                    flag = false;
                                }
                                else {
                                    guiContainer.createOrRefreshShop(characters[y][x]);
                                }
                                break;
                            }
                            case NPC_CITIZEN_MALE: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.LUGGAGE_CARRIAGE) == 0) {
                                    flag = false;
                                }
                                break;
                            }
                            case NPC_MOUNTAIN_KING: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.MOUNTAIN_KING) == 0) {
                                    flag = false;
                                }
                                break;
                            }
                            case ELVEN_PRINCESS: {
                                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.PRINCESS) == 0) {
                                    flag = false;
                                }
                                break;
                            }
                        }
                    }
                    if (flag) {
                        CharacterDrawable newCharacter = new AutonomousCharacter(
                                characters[y][x],
                                new CoordsInt(x, WorldConfig.ROOM_HEIGHT - y - 1),
                                rayHandler, world, this, mapTiles[x][y], x > heroInRoom.getPosition().x);


                        for (int i = 0; i < CharacterStats.getCharacterSize(newCharacter.getCharacterEnum()); i++) {
                            mapTiles[x + i][WorldConfig.ROOM_HEIGHT - y - 1].setCharacter(newCharacter, i == 0);
                        }
                        charactersInRoom.add(newCharacter);
                    }
                }

            }
        }

        createNodes();

        this.stageObject = stageObject;

        if (type == RoomTypeEnum.FIRST_ROOM) {
            mapTiles[WorldConfig.ROOM_WIDTH / 2][WorldConfig.ROOM_HEIGHT / 2]
                    .setCharacter(heroInRoom, true);

            currentCharacterMoving = heroInRoom;
            getMoveInfo(heroInRoom);
        }
        else {
            this.queue = new TurnQueue(this);
            currentCharacterMoving = queue.getCurrentCharacter();

            if (type == RoomTypeEnum.BOSS_ROOM) {
                CharacterDrawable boss = null;
                for (CharacterDrawable c : charactersInRoom)
                    if (c.getCharacterEnum().toString().startsWith("BOSS")) {
                        boss = c;
                        break;
                    }

                if (boss != null) bossHpBar = new BossHpBar(boss);
            }
        }


    }

    public boolean tap(float x, float y) { //todo
        if (roomState != RoomStateEnum.CLEAN) {
            CharacterDrawable tappedQueue = queue.getTapCharacter(x, y);
            if (tappedQueue != null) {
                CameraHandler.centerOnCoords(
                        tappedQueue.getX() + tappedQueue.getWidth() / 2f,
                        tappedQueue.getY() + tappedQueue.getHeight() / 2f);
                return true;
            }
        }

        if (currentCharacterMoving == heroInRoom) {
            MapTile tapped = getTouchTile(x, y);
            if (tapped == null) return true;

            if (!freezeTime) {
                if (roomState == RoomStateEnum.CLEAN) {
                    if (heroInRoom.isReady()) {
                        if (heroInRoom.canMoveTo(tapped)) {
                            TurnAction action = new TurnAction();
                            action.addAction(new TurnStarted(this));
                            action.addAction(getWay(heroInRoom.getPossibleMovements(), tapped, heroInRoom));
                            action.addAction(new TurnFinished(this));
                            heroInRoom.addAction(action.getSequence());
                        }
                    }
                } else if (roomState == RoomStateEnum.FIGHT) {

                    if (currentCharacterMoving.isHero()) {
                        if (heroInRoom.isReady()) {
                            if (tapped.getFlag() == MapPathFindingFlags.MOVABLE ||
                                    tapped.getFlag() == MapPathFindingFlags.ITEM_MOVABLE) {

                            }
                        }
                    } else {
                        if (currentCharacterMoving.isReady()) {
                            makeMove(currentCharacterMoving);
                        }
                    }
                }
            }
        }

        else {

        }

        return true;
    }

    public void moveStarted() {
        this.freezeTime = true;
        this.npcMode = null;
    }

    public void moveFinished() {
        this.freezeTime = false;
        if (roomState == RoomStateEnum.CLEAN) {

            getMoveInfo(heroInRoom);

            getNpcInfo(heroInRoom); //npcMode set if possible

        } else if (roomState == RoomStateEnum.FIGHT) {
            queue.tick();
            currentCharacterMoving = queue.getCurrentCharacter();

            getMoveInfo(currentCharacterMoving);
            if (currentCharacterMoving.isHero()) {
            }
            else {
                makeMove(currentCharacterMoving);
            }

        }
    }

    private void getNpcInfo(Hero heroInRoom) {
        CoordsInt coords = heroInRoom.getPosition();

        Array<MapTile> tilesAround = new Array<>();

        if (coords.x > 0) {
            tilesAround.add(mapTiles[coords.x-1][coords.y]);
            if (coords.y > 0) {
                tilesAround.add(mapTiles[coords.x-1][coords.y - 1]);
            }
            if (coords.y < WorldConfig.ROOM_HEIGHT - 1) {
                tilesAround.add(mapTiles[coords.x-1][coords.y + 1]);
            }
        }
        if (coords.x < WorldConfig.ROOM_WIDTH - 1) {
            tilesAround.add(mapTiles[coords.x+1][coords.y]);
            if (coords.y > 0) {
                tilesAround.add(mapTiles[coords.x+1][coords.y - 1]);
            }
            if (coords.y < WorldConfig.ROOM_HEIGHT - 1) {
                tilesAround.add(mapTiles[coords.x+1][coords.y + 1]);
            }
        }

        if (coords.y > 0) {
            tilesAround.add(mapTiles[coords.x][coords.y - 1]);
            if (coords.x > 0) {
                tilesAround.add(mapTiles[coords.x+1][coords.y - 1]);
            }
            if (coords.x < WorldConfig.ROOM_WIDTH - 1) {
                tilesAround.add(mapTiles[coords.x-1][coords.y - 1]);
            }
        }

        if (coords.y < WorldConfig.ROOM_HEIGHT - 1) {
            tilesAround.add(mapTiles[coords.x][coords.y + 1]);
            if (coords.x > 0) {
                tilesAround.add(mapTiles[coords.x+1][coords.y + 1]);
            }
            if (coords.x < WorldConfig.ROOM_WIDTH - 1) {
                tilesAround.add(mapTiles[coords.x-1][coords.y + 1]);
            }
        }

        for (MapTile tile : tilesAround) {
            if (tile.getCharacterStandingOn() != null) {
                System.out.println("chuj   " + tile.getPositionInt().x + "  " + tile.getPositionInt().y + "  " + tile.getCharacterStandingOn().getCharacterEnum().toString());
                switch (tile.getCharacterStandingOn().getCharacterEnum()) {
                    case NPC_ALCHEMIST: {
                        npcMode = NPC_ALCHEMIST;
                        break;
                    }
                    case NPC_BLACKSMITH: {
                        npcMode = NPC_BLACKSMITH;
                        break;
                    }
                    case NPC_BUTCHER: {
                        npcMode = NPC_BUTCHER;
                        break;
                    }
                    case NPC_MAGIC_SHOP: {
                        npcMode = NPC_MAGIC_SHOP;
                        break;
                    }
                }
            }
        }

    }

    public void draw(Batch mapBatch, Batch guiBatch, OrthographicCamera camera) {

        mapBatch.begin();
        mapDrawable.draw(mapBatch, WorldConfig.ROOM_POS_X, WorldConfig.ROOM_POS_Y, WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES);

        drawMovableTiles(heroInRoom, mapBatch); //____________________________________________________________??

        grid.draw(mapBatch, WorldConfig.ROOM_POS_X, WorldConfig.ROOM_POS_Y, WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES);

        for (int y = WorldConfig.ROOM_HEIGHT - 1; y >= 0; y--) {
            for (int x = WorldConfig.ROOM_WIDTH - 1; x >= 0; x--) {
                mapTiles[x][y].draw(mapBatch, roomState == RoomStateEnum.FIGHT);
            }
        }

        mapBorder.draw(mapBatch, borderCoords.x, borderCoords.y, borderSize.x, borderSize.y);

        mapBatch.end();

        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
        b2dr.render(world, camera.combined.scl(1f));

        guiBatch.begin();
        for (int y = WorldConfig.ROOM_HEIGHT - 1; y >= 0; y--) {
            for (int x = WorldConfig.ROOM_WIDTH - 1; x >= 0; x--) {
                mapTiles[x][y].drawTop(guiBatch);
            }
        }

        if (type == RoomTypeEnum.BOSS_ROOM) {
            bossHpBar.draw(guiBatch);
        }

        if (roomState != RoomStateEnum.CLEAN) queue.draw(guiBatch);

        guiBatch.end();

    }

    public MapTile getTouchTile(float x, float y) {
        CoordsInt coords = WorldConfig.getIntCoordsFromFloatPoint(x, y);
        if (coords.x == -1 || coords.y == -1) return null;
        return mapTiles[coords.x][coords.y];
    }

    public void createNodes() {
        if (roomNodes.get(DirectionEnum.TOP) != null) {
            //mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][WorldConfig.ROOM_HEIGHT - 1].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            //mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][WorldConfig.ROOM_HEIGHT - 1].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][WorldConfig.ROOM_HEIGHT - 1].setIsNode(true);
            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][WorldConfig.ROOM_HEIGHT - 1].setIsNode(true);
            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][WorldConfig.ROOM_HEIGHT - 1].setIsNode(true);
        }
        if (roomNodes.get(DirectionEnum.BOTTOM) != null) {
            //mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][0].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            //mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][0].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][0].setIsNode(true);
            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][0].setIsNode(true);
            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][0].setIsNode(true);
        }
        if (roomNodes.get(DirectionEnum.LEFT) != null) {
            //mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f)].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            //mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f)].setIsNode(true);
            mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].setIsNode(true);
            mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1].setIsNode(true);
        }
        if (roomNodes.get(DirectionEnum.RIGHT) != null) {
            //mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f)].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            //mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].setFlag(MapPathFindingFlags.ROOM_NODE, -2);
            mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f)].setIsNode(true);
            mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].setIsNode(true);
            mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1].setIsNode(true);
        }
    }

    public HashMap<DirectionEnum, Room> getRoomNodes() {
        return roomNodes;
    }

    public CoordsInt getPosition() {
        return position;
    }

    public RoomTypeEnum getType() {
        return type;
    }

    public RoomStateEnum getRoomState() {
        return roomState;
    }

    public void disposeRoom() {
        rayHandler.removeAll();
        rayHandler.dispose();

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body b : bodies) {
            world.destroyBody(b);
        }
    }

    public void getMoveInfo(CharacterDrawable character) {
        int range = (roomState == RoomStateEnum.CLEAN) ? -1 : character.getStats().getRange();
        CoordsInt start = character.getPosition();

        Array<Array<MapTile>> allPaths = new Array<>();
        Array<MapTile> attackableTiles = new Array<>();
        Array<MapTile> moveToTiles = new Array<>();

        int lifeTime = range == -1 ? WorldConfig.ROOM_HEIGHT * WorldConfig.ROOM_WIDTH * 2 : range;

        int nowAdded = 0;
        int lastlyAdded = 0;
        int currLimit = 1;
        int currSize = 0;
        int currPathLen = 0;
        CoordsInt currCoords;
        MapTile currTile = null;

        DirectionEnum[] directions =
                {DirectionEnum.LEFT, DirectionEnum.TOP, DirectionEnum.RIGHT, DirectionEnum.BOTTOM};

        allPaths.add(new Array<MapTile>());

        for (int loop = 0; loop < lifeTime; loop++) {

            for (int i = currSize; i < currLimit; i++) {
                currPathLen = allPaths.get(i).size;
                if (currPathLen == 0) { //first loop
                    currCoords = start;

                    for (DirectionEnum direction : directions) {
                        currTile = null;

                        switch (direction) {
                            case BOTTOM: {
                                if (currCoords.y > 0) {
                                    currTile = mapTiles[currCoords.x][currCoords.y - 1];
                                }
                                break;
                            }
                            case RIGHT: {
                                if (currCoords.x < WorldConfig.ROOM_WIDTH - 1) {
                                    currTile = mapTiles[currCoords.x + 1][currCoords.y];
                                }
                                break;
                            }
                            case TOP: {
                                if (currCoords.y < WorldConfig.ROOM_HEIGHT - 1) {
                                    currTile = mapTiles[currCoords.x][currCoords.y + 1];
                                }
                                break;
                            }
                            case LEFT: {
                                if (currCoords.x > 0) {
                                    currTile = mapTiles[currCoords.x - 1][currCoords.y];
                                }
                                break;
                            }
                        }
                        if (currTile != null) {

                            if (currTile.getFlag() == MapPathFindingFlags.NONE) {
                                if (currTile.isWalkable()) {
                                    allPaths.add(new Array<MapTile>());
                                    allPaths.get(allPaths.size - 1).addAll(allPaths.get(i));
                                    allPaths.get(allPaths.size - 1).add(currTile);
                                    currTile.setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                                    moveToTiles.add(currTile);
                                    nowAdded++;
                                } else if (currTile.getCharacterStandingOn() != null) {
                                    CharacterDrawable characterStandingOnTile = currTile.getCharacterStandingOn();

                                    if (character.isHero()) {
                                        if (!characterStandingOnTile.isPet() && !character.isHero()) {
                                            attackableTiles.add(currTile);
                                            currTile.setFlag(MapPathFindingFlags.ATTACKABLE, allPaths.size - 1);
                                            nowAdded++;
                                        }
                                    } else if (character.isPet()) {
                                        if (!characterStandingOnTile.isPet() && !character.isHero()) {
                                            attackableTiles.add(currTile);
                                            currTile.setFlag(MapPathFindingFlags.ATTACKABLE, allPaths.size - 1);
                                            nowAdded++;
                                        }
                                    } else {
                                        if (characterStandingOnTile.isPet() || !character.isHero()) {
                                            attackableTiles.add(currTile);
                                            currTile.setFlag(MapPathFindingFlags.ATTACKABLE, allPaths.size - 1);
                                            nowAdded++;
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    for (int j = 0; j < currPathLen; j++) {
                        currCoords = allPaths.get(i).get(j).getPositionInt();

                        for (DirectionEnum direction : directions) {
                            currTile = null;

                            switch (direction) {
                                case BOTTOM: {
                                    if (currCoords.y > 0)
                                        currTile = mapTiles[currCoords.x][currCoords.y - 1];
                                    break;
                                }
                                case RIGHT: {
                                    if (currCoords.x < WorldConfig.ROOM_WIDTH - 1)
                                        currTile = mapTiles[currCoords.x + 1][currCoords.y];
                                    break;
                                }
                                case TOP: {
                                    if (currCoords.y < WorldConfig.ROOM_HEIGHT - 1)
                                        currTile = mapTiles[currCoords.x][currCoords.y + 1];
                                    break;
                                }
                                case LEFT: {
                                    if (currCoords.x > 0)
                                        currTile = mapTiles[currCoords.x - 1][currCoords.y];
                                    break;
                                }
                            }

                            if (currTile != null) {

                                if (currTile.getFlag() == MapPathFindingFlags.NONE) {
                                    if (currTile.isWalkable()) {
                                        allPaths.add(new Array<MapTile>());
                                        allPaths.get(allPaths.size - 1).addAll(allPaths.get(i));
                                        allPaths.get(allPaths.size - 1).add(currTile);
                                        currTile.setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                                        moveToTiles.add(currTile);
                                        nowAdded++;
                                    } else if (currTile.getCharacterStandingOn() != null) {
                                        CharacterDrawable characterStandingOnTile = currTile.getCharacterStandingOn();

                                        if (character.isHero()) {
                                            if (!characterStandingOnTile.isPet() && !character.isHero()) {
                                                attackableTiles.add(currTile);
                                            }
                                        } else if (character.isPet()) {
                                            if (!characterStandingOnTile.isPet() && !character.isHero()) {
                                                attackableTiles.add(currTile);
                                            }
                                        } else {
                                            if (characterStandingOnTile.isPet() || !character.isHero()) {
                                                attackableTiles.add(currTile);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }



            if (nowAdded == 0) break;
            else {
                loop += nowAdded;
                currSize += lastlyAdded;
                lastlyAdded = nowAdded;
                currLimit = allPaths.size;
                nowAdded = 0;
            }
        }

        character.setPossibleMovements(allPaths);
        character.setPossibleMoveToTiles(moveToTiles);
        character.setAttackableTiles(attackableTiles);
        resetPathfindingFlags();
    }

    public Array<CharacterDrawable> getTurnQueue() {
        Array<CharacterDrawable> allCharacters = new Array<>();
        Array<CharacterDrawable> result = new Array<>();

        int maxSpeed = 0;
        for (CharacterDrawable character : charactersInRoom) {
            if (character.getStats().getSpeed() > maxSpeed)
                maxSpeed = character.getStats().getSpeed();
        }

        maxSpeed *= 2;

        for (int i = 1; i < maxSpeed; i++) {
            for (CharacterDrawable character : charactersInRoom) {
                if ((maxSpeed - character.getStats().getSpeed()) % i == 0) result.add(character);
            }
        }
        //System.out.println("edvedvedv " + maxSpeed);
        return result;
    }

    public SequenceAction getWay(Array<Array<MapTile>> allPaths, MapTile end, CharacterDrawable characterDrawable) {

        Array<MapTile> path = null;
        for (Array<MapTile> p : allPaths) {
            if (p.contains(end, false)) {
                path = p;
                break;
            }
        }

        SequenceAction result = new SequenceAction();
        CoordsFloat coords;
        CoordsInt coordsInt = characterDrawable.getPosition();

        MapTile oldTile = mapTiles[coordsInt.x][coordsInt.y];
        MapTile newTile = null;

        result.addAction(new ChangeState(characterDrawable, CharacterStateEnum.RUN));

        for (MapTile mapTile : path) {
            newTile = mapTile;
            coords = mapTile.getPositionCharacterDrawingFloat();

            result.addAction(new SwitchMapTile(
                    oldTile,
                    newTile,
                    characterDrawable));

            result.addAction(Actions.moveTo(
                    coords.x,
                    coords.y,
                    roomState == RoomStateEnum.CLEAN ?
                            WorldConfig.MOVE_SPEED_CLEAN : WorldConfig.MOVE_SPEED_FIGHT
            ));

            oldTile = newTile;
        }

        result.addAction(new ChangeState(characterDrawable, CharacterStateEnum.IDLE));
        return result;
    }

    private void resetPathfindingFlags() {
        for (int x = WorldConfig.ROOM_WIDTH - 1; x >= 0; x--) {
            for (int y = WorldConfig.ROOM_HEIGHT - 1; y >= 0; y--) {
                mapTiles[x][y].setFlag(MapPathFindingFlags.NONE, 0);
            }
        }
    }

    public void heroMovedIntoRoom(DirectionEnum dir, CoordsInt coords) {
        mapTiles[WorldConfig.ROOM_WIDTH - 1 - coords.x]
                [WorldConfig.ROOM_HEIGHT - 1 - coords.y]
                .setCharacter(heroInRoom, true);

        heroInRoom.setTileStandingOn(
                mapTiles[WorldConfig.ROOM_WIDTH - 1 - coords.x]
                        [WorldConfig.ROOM_HEIGHT - 1 - coords.y],
                true);

        heroInRoom.setPositionForce(
                new CoordsInt(WorldConfig.ROOM_WIDTH - 1 - coords.x,
                        WorldConfig.ROOM_HEIGHT - 1 - coords.y)
        );

    }

    public Array<CharacterDrawable> getRoomCharacters() {
        return charactersInRoom;
    }

    public MapTile[][] getTiles() {
        return mapTiles;
    }

    public void newStage() {
        GameScreen.nextStageInit();
    }

    public void itemDropped(AbstractItem item) {
        CoordsInt pos = heroInRoom.getTileStandingOn().getPositionInt();
        mapTiles[pos.x][pos.y].dropItem(item);
        GuiContainer.setItemsToPick(mapTiles[pos.x][pos.y].getItemsToPick());
    }

    public void itemPickedUp(AbstractItem selectedItem) {
        MapTile tile = heroInRoom.getTileStandingOn();
        tile.getItemsToPick().removeValue(selectedItem, true);
        GuiContainer.setItemsToPick(tile.getItemsToPick());
    }

    private void makeMove(CharacterDrawable character) {

        if (character.getStats().getRange() > 1) { //ranged
            if (character.isPet()) {
                for (MapTile tile : character.getRangeTiles()) {
                    if (tile.getCharacterStandingOn() != null && tile.getCharacterStandingOn().isEnemy()) {
                        character.shot(tile);
                        return;
                    }

                }
                //if no shot - make move

                //move
                character.moveToMapTile(findBestTileForRangedCharacter(character));

                //check range targets
                CharacterDrawable target = null;
                float currentTargetDistance = -1;
                for (CharacterDrawable maybeTarget : charactersInRoom) {
                    if (maybeTarget != character
                            && maybeTarget.isEnemy()) {

                        float distance = Vector2.dst(
                                character.getX() + character.getWidth() / 2f,
                                character.getY() + character.getHeight() / 2f,
                                maybeTarget.getX() + maybeTarget.getWidth() / 2f,
                                maybeTarget.getY() + maybeTarget.getHeight() / 2f);


                        if (distance <= character.getStats().getRange()) {
                            if (target == null) {
                                target = maybeTarget;
                                currentTargetDistance = distance;
                            } else if (distance < currentTargetDistance) {
                                target = maybeTarget;
                                currentTargetDistance = distance;
                            }
                        }
                    }

                }
                if (target != null) {  //if anything is in range - add range tiles
                    character.setRangeTiles(
                            getTilesBetweenCharacters(character.getPosition(),
                                    target.getPosition(), character.getStats().getRange()));
                }


            } else {//enemy
                for (MapTile tile : character.getRangeTiles()) {
                    if (tile.getCharacterStandingOn() != null &&
                            (tile.getCharacterStandingOn().isHero() || tile.getCharacterStandingOn().isPet())) {
                        character.shot(tile);
                        return;
                    }

                }
                //if no shot - make move

                //move
                character.moveToMapTile(findBestTileForRangedCharacter(character));

                //check range targets
                CharacterDrawable target = null;
                float currentTargetDistance = -1;
                for (CharacterDrawable maybeTarget : charactersInRoom) {
                    if (maybeTarget != character
                            && (maybeTarget.isPet() || maybeTarget.isHero())) {

                        float distance = Vector2.dst(
                                character.getX() + character.getWidth() / 2f,
                                character.getY() + character.getHeight() / 2f,
                                maybeTarget.getX() + maybeTarget.getWidth() / 2f,
                                maybeTarget.getY() + maybeTarget.getHeight() / 2f);


                        if (distance <= character.getStats().getRange()) {
                            if (target == null) {
                                target = maybeTarget;
                                currentTargetDistance = distance;
                            } else if (distance < currentTargetDistance) {
                                target = maybeTarget;
                                currentTargetDistance = distance;
                            }
                        }
                    }

                }
                if (target != null) {  //if anything is in range - add range tiles
                    character.setRangeTiles(
                            getTilesBetweenCharacters(character.getPosition(),
                                    target.getPosition(), character.getStats().getRange()));
                }

            }
        } else { //mele

        }
    }

    private Array<MapTile> getTilesBetweenCharacters(CoordsInt start, CoordsInt end, int shotRange) {
        int minX, maxX, minY, maxY, width, height;

        if (start.x < end.x) {
            minX = start.x;
            maxX = WorldConfig.ROOM_WIDTH - 1;
        } else if (start.x > end.x) {
            minX = 0;
            maxX = end.x;
        } else {
            minX = start.x;
            maxX = end.x;
        }

        if (start.y < end.y) {
            minY = start.y;
            maxY = WorldConfig.ROOM_HEIGHT - 1;
        } else if (start.y > end.y) {
            minY = 0;
            maxY = end.y;
        } else {
            minY = start.y;
            maxY = end.y;
        }

        width = maxX - minX;
        height = maxY - minY;
        Array<MapTile> result = new Array<>();
        float a = (float) height / (float) width;
        float distance;
        float rangeDistance;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                distance = Math.abs(y - (minY + ((x - minX) * a)));
                rangeDistance = Vector2.dst(start.x, start.y, x, y);
                if (rangeDistance < shotRange && distance < 1.5f) result.add(mapTiles[x][y]);
            }
        }
        return result;
    }

    private MapTile findBestTileForRangedCharacter(CharacterDrawable character) {
        //character.getPossibleWays(character.getSpeed());
        Array<Array<MapTile>> allArrays = character.getPossibleMovements();
        CoordsInt coords = character.getPosition();
        MapTile result = allArrays.get(0).get(0);
        float bestResult = 0;

        for (Array<MapTile> array : allArrays) {
            for (MapTile mapTile : array) {
                float currResult = 1000;
                float distanceToClosestEnemy = 999f;

                for (CharacterDrawable maybeTarget : charactersInRoom) {
                    if ((character.isEnemy() && (maybeTarget.isHero() || maybeTarget.isPet()))
                            || (character.isPet() && maybeTarget.isEnemy())) {

                        CoordsInt targetPosition = maybeTarget.getPosition();

                        float temp = Vector2.dst(
                                mapTile.getPositionInt().x,
                                mapTile.getPositionInt().y,
                                targetPosition.x,
                                targetPosition.y);

                        if (temp < distanceToClosestEnemy) distanceToClosestEnemy = temp;
                    }
                }

                if (distanceToClosestEnemy <= character.getStats().getRange()) currResult += 1000;

                if (currResult == 2000) {
                    currResult -= distanceToClosestEnemy;
                } else {
                    currResult += distanceToClosestEnemy;
                }

                if (currResult > bestResult) {
                    result = mapTile;
                    bestResult = currResult;
                }
            }
        }

        return result;
    }

    public void longPress(float x, float y) {

    }

    private void drawMovableTiles(CharacterDrawable character, Batch batch) {
        if (cachedMovableTiles != character.getPossibleMoveToTiles()) {
            cachedMovableTiles = character.getPossibleMoveToTiles();
        }
        if (cachedMovableTiles != null) {
            for (MapTile tile : cachedMovableTiles) {
                tile.drawFlag(MapPathFindingFlags.MOVABLE, batch);
            }
        }
    }

    public CharacterEnum getNpcMode() {
        return npcMode;
    }
}
