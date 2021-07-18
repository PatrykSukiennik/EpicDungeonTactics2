package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
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
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapGenerator;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoMaster;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGenerator;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.CameraHandler;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.SoundPlayAction;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.Attack;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.ChangeState;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.DamageGiving;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.RefreshShotableTiles;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.Shot;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.SwitchMapTile;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.TurnAction;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.TurnFinished;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.TurnStarted;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions.Wait;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.AutonomousCharacter;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.turnQueue.TurnQueue;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.weaponSelector.WeaponSelector;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.screens.viewElements.game.BossHpBar;
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

import java.util.Comparator;
import java.util.HashMap;

import box2dLight.RayHandler;

import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_BABY;
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

    private Array<MapTile> nodesInRoom;
    private Array<MapTile> goingDownTiles;

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

    private static WeaponSelector weaponSelector;
    private CharacterDrawable meleTarget = null;


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
        weaponSelector = new WeaponSelector();

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

        MapPathFindingFlags[][] walkableArray = MapInfoMaster.getWalkableArray(roomEnum);
        goingDownTiles = new Array<>();
        for (int x = 0; x < WorldConfig.ROOM_WIDTH; x++) {
            for (int y = 0; y < WorldConfig.ROOM_HEIGHT; y++) {
                mapTiles[x][y] = new MapTile(x, y, walkableArray[WorldConfig.ROOM_HEIGHT - y - 1][x] != MapPathFindingFlags.NONE);
                if (walkableArray[WorldConfig.ROOM_HEIGHT - y - 1][x] == MapPathFindingFlags.NEW_STAGE) {
                    goingDownTiles.add(mapTiles[x][y]);
                    mapTiles[x][y].setIsGoingDown(true);
                }
            }
        }

        MapElementAnimationEnum[][] animatedElements = MapInfoMaster.getAnimatedElements(roomEnum);
        CharacterEnum[][] characters = MapInfoMaster.getCharactersInfo(roomEnum);

        System.out.println(roomEnum.toString());
        CoordsInt heroCoords = null;

        if (type == RoomTypeEnum.FIRST_ROOM) {
            boolean flag = false;
            for (int x = 0; x < WorldConfig.ROOM_WIDTH; x++) {
                for (int y = 0; y < WorldConfig.ROOM_HEIGHT; y++) {
                    System.out.println(x + "  " + y);
                    if (characters[y][x] == HERO_BABY) {
                        flag = true;
                        heroCoords = new CoordsInt(x, WorldConfig.ROOM_HEIGHT - y - 1);

                        charactersInRoom.add(
                                new Hero(
                                        StatTracker.getHero(),
                                        heroCoords,
                                        rayHandler,
                                        world,
                                        this,
                                        mapTiles[heroCoords.x][heroCoords.y]));
                    }
                }
                if (flag) break;
            }
        } else {
            charactersInRoom.add(
                    new Hero(StatTracker.getHero(),
                            new CoordsInt(WorldConfig.ROOM_WIDTH / 2, WorldConfig.ROOM_HEIGHT / 2),
                            rayHandler,
                            world,
                            this,
                            mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][(int) (WorldConfig.ROOM_HEIGHT / 2f)]));
        }
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

                else if (type == RoomTypeEnum.FIRST_ROOM && characters[y][x] != null && characters[y][x] != HERO_BABY) {
                    boolean doCreate = false;
                    switch (characters[y][x]) {
                        case NPC_BUTCHER: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.BUTCHER) > 0) {
                                doCreate = true;
                                guiContainer.createOrRefreshShop(characters[y][x]);
                            }
                            break;
                        }
                        case NPC_ALCHEMIST: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.ALCHEMIST) > 0) {
                                doCreate = true;
                                guiContainer.createOrRefreshShop(characters[y][x]);
                            }
                            break;
                        }
                        case NPC_BLACKSMITH: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.BLACKSMITH) > 0) {
                                doCreate = true;
                                guiContainer.createOrRefreshShop(characters[y][x]);
                            }
                            break;
                        }
                        case NPC_MAGIC_SHOP: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.MAGIC_SHOP) > 0) {
                                doCreate = true;
                                guiContainer.createOrRefreshShop(characters[y][x]);
                            }
                            break;
                        }
                        case NPC_CITIZEN_MALE: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.LUGGAGE_CARRIAGE) > 0) {
                                doCreate = true;
                            }
                            break;
                        }
                        case NPC_MOUNTAIN_KING: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.MOUNTAIN_KING) > 0) {
                                doCreate = true;
                            }
                            break;
                        }
                        case ELVEN_PRINCESS: {
                            if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.PRINCESS) > 0) {
                                doCreate = true;
                            }
                            break;
                        }
                    }
                    if (doCreate) {
                        CharacterDrawable newCharacter = new AutonomousCharacter(
                                characters[y][x],
                                new CoordsInt(x, WorldConfig.ROOM_HEIGHT - y - 1),
                                rayHandler, world, this, mapTiles[x][y], x > heroInRoom.getPosition().x);


                        mapTiles[x][WorldConfig.ROOM_HEIGHT - y - 1].setCharacter(newCharacter, true);
                        charactersInRoom.add(newCharacter);
                    }
                }

            }

        }

        if (type != RoomTypeEnum.FIRST_ROOM) createEnemies(characters, stage);

        createNodes();

        this.stageObject = stageObject;

        if (type == RoomTypeEnum.FIRST_ROOM) {
            mapTiles[heroCoords.x][heroCoords.y]
                    .setCharacter(heroInRoom, true);

            currentCharacterMoving = heroInRoom;
            getMoveInfo(heroInRoom);
        } else {
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

    private void createEnemies(CharacterEnum[][] characters, int stage) {
        HashMap<CoordsInt, CharacterEnum> enemies = new HashMap<>();

        for (int x=0; x<WorldConfig.ROOM_WIDTH; x++) {
            for (int y=0; y<WorldConfig.ROOM_HEIGHT; y++) {
                if (characters[y][x] != null && !characters[y][x].toString().startsWith("NPC")) {
                    enemies.put(new CoordsInt(x, WorldConfig.ROOM_HEIGHT - y - 1), characters[y][x]);
                }
            }
        }

        int count = (int)(MapGenerator.getEnemiesFactor(stage) * (float)enemies.size());

        System.out.println("size: " + count);
        System.out.println("enemies: " + enemies.size());

        System.out.println(enemies.toString());

        if (count == enemies.size()) {
            for (CoordsInt coords : enemies.keySet()) {

                System.out.println("coords: " + coords.x + " " + coords.y);
                System.out.println("entry: " + enemies.get(coords));

                CharacterEnum randomEnemy = MapGenerator.getRandomEnemy(stage);

                CharacterDrawable newCharacter = new AutonomousCharacter(
                        randomEnemy,
                        new CoordsInt(coords.x, WorldConfig.ROOM_HEIGHT - coords.y - 1),
                        rayHandler, world, this, mapTiles[coords.x][coords.y], coords.x > heroInRoom.getPosition().x);


                mapTiles[coords.x][WorldConfig.ROOM_HEIGHT - coords.y - 1].setCharacter(newCharacter, true);
                charactersInRoom.add(newCharacter);
            }
        } else while(count > 0) {
            CoordsInt newEnemyCoords = (CoordsInt) enemies.keySet().toArray()[EpicDungeonTactics.random.nextInt() % enemies.keySet().size()];

            CharacterEnum randomEnemy = MapGenerator.getRandomEnemy(stage);

            CharacterDrawable newCharacter = new AutonomousCharacter(
                    randomEnemy,
                    new CoordsInt(newEnemyCoords.x, WorldConfig.ROOM_HEIGHT - newEnemyCoords.y - 1),
                    rayHandler, world, this, mapTiles[newEnemyCoords.x][newEnemyCoords.y], newEnemyCoords.x > heroInRoom.getPosition().x);


            mapTiles[newEnemyCoords.x][WorldConfig.ROOM_HEIGHT - newEnemyCoords.y - 1].setCharacter(newCharacter, true);
            charactersInRoom.add(newCharacter);

            enemies.remove(newEnemyCoords);
            count--;
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
        System.out.println(StatTracker.getCurrentStat(CompleteHeroStatsEnum.RANGE));
        if (currentCharacterMoving == heroInRoom || currentCharacterMoving.isPet()) {
            MapTile tapped = getTouchTile(x, y);
            if (tapped == null) return true;

            System.out.println("pyk");
            if (!freezeTime) {
                System.out.println("no freeztime");
                if (roomState == RoomStateEnum.CLEAN) {
                    System.out.println("clean");
                    if (currentCharacterMoving.isReady()) {
                        System.out.println("ready");
                        if (currentCharacterMoving.canMoveTo(tapped)) {
                            System.out.println("can move to");
                            TurnAction action = new TurnAction();
                            action.addAction(new TurnStarted(this));
                            action.addAction(getWay(currentCharacterMoving.getPossibleMovements(), tapped, currentCharacterMoving));
                            action.addAction(new TurnFinished(this));
                            currentCharacterMoving.addAction(action.getSequence());
                            currentCharacterMoving.setTargetTile(tapped);
                        } else if (tapped == currentCharacterMoving.getTileStandingOn()) {
                            moveStarted();
                            moveFinished();
                        }
                    }
                } else if (roomState == RoomStateEnum.FIGHT) {

                    if (currentCharacterMoving.isReady()) {
                        if (meleTarget != null) {
                            if (currentCharacterMoving.getPossibleMoveToTiles().contains(tapped, false)
                                    && Vector2.dst(
                                    tapped.getPositionInt().x,
                                    tapped.getPositionInt().y,
                                    meleTarget.getPosition().x,
                                    meleTarget.getPosition().y) == 1) {
                                moveAndAttack(currentCharacterMoving, tapped, meleTarget);
                                meleTarget = null;
                            } else if (tapped.getCharacterStandingOn() == currentCharacterMoving
                                    && Vector2.dst(
                                    tapped.getPositionInt().x,
                                    tapped.getPositionInt().y,
                                    meleTarget.getPosition().x,
                                    meleTarget.getPosition().y) == 1) {
                                justAttack(currentCharacterMoving, meleTarget.getTileStandingOn());
                                meleTarget = null;
                            } else {
                                meleTarget = null;
                                guiContainer.hideWeaponSelector();
                            }
                        } else {
                            if (currentCharacterMoving.canMoveTo(tapped)) {
                                TurnAction action = new TurnAction();
                                action.addAction(new TurnStarted(this));
                                action.addAction(getWay(currentCharacterMoving.getPossibleMovements(), tapped, currentCharacterMoving));
                                action.addAction(new TurnFinished(this));
                                currentCharacterMoving.addAction(action.getSequence());
                                currentCharacterMoving.setTargetTile(tapped);
                            } else if (tapped == currentCharacterMoving.getTileStandingOn()) {
                                moveStarted();
                                moveFinished();
                            } else if (tapped.getCharacterStandingOn() != null) {
                                if (tapped.getCharacterStandingOn().isEnemy()
                                        && (
                                        (heroInRoom.getAttackableTiles().contains(tapped, false)
                                                || Vector2.dst(
                                                heroInRoom.getPosition().x,
                                                heroInRoom.getPosition().y,
                                                tapped.getPositionInt().x,
                                                tapped.getPositionInt().y)
                                                < StatTracker.getCurrentStat(CompleteHeroStatsEnum.RANGE)))) {
                                    guiContainer.showWeaponSelector(heroInRoom, tapped.getCharacterStandingOn());
                                }
                            }
                        }

                    }
                }
            }
        } else {

        }

        return true;
    }

    public void moveStarted() {
        this.freezeTime = true;
        this.npcMode = null;
    }

    public void moveFinished() {

        if (!GameScreen.isStop()) {
            this.freezeTime = false;
            if (roomState == RoomStateEnum.CLEAN) {

                getMoveInfo(heroInRoom);

                getNpcInfo(heroInRoom); //npcMode set if possible

            } else if (roomState == RoomStateEnum.FIGHT) {

                queue.tick();
                currentCharacterMoving = queue.getCurrentCharacter();
                while (currentCharacterMoving == null) {
                    queue.tick();
                    currentCharacterMoving = queue.getCurrentCharacter();
                }

                getMoveInfo(currentCharacterMoving);
                if (currentCharacterMoving.isHero() || currentCharacterMoving.isPet()) {
                } else {
                    makeMove(currentCharacterMoving);
                }

            }
            System.out.println("MF no stop");
        }

        System.out.println("MF stop");
    }

    private void getNpcInfo(Hero heroInRoom) {
        CoordsInt coords = heroInRoom.getPosition();

        Array<MapTile> tilesAround = new Array<>();

        if (coords.x > 0) {
            tilesAround.add(mapTiles[coords.x - 1][coords.y]);
            if (coords.y > 0) {
                tilesAround.add(mapTiles[coords.x - 1][coords.y - 1]);
            }
            if (coords.y < WorldConfig.ROOM_HEIGHT - 1) {
                tilesAround.add(mapTiles[coords.x - 1][coords.y + 1]);
            }
        }
        if (coords.x < WorldConfig.ROOM_WIDTH - 1) {
            tilesAround.add(mapTiles[coords.x + 1][coords.y]);
            if (coords.y > 0) {
                tilesAround.add(mapTiles[coords.x + 1][coords.y - 1]);
            }
            if (coords.y < WorldConfig.ROOM_HEIGHT - 1) {
                tilesAround.add(mapTiles[coords.x + 1][coords.y + 1]);
            }
        }

        if (coords.y > 0) {
            tilesAround.add(mapTiles[coords.x][coords.y - 1]);
            if (coords.x > 0) {
                tilesAround.add(mapTiles[coords.x - 1][coords.y - 1]);
            }
            if (coords.x < WorldConfig.ROOM_WIDTH - 1) {
                tilesAround.add(mapTiles[coords.x + 1][coords.y - 1]);
            }
        }

        if (coords.y < WorldConfig.ROOM_HEIGHT - 1) {
            tilesAround.add(mapTiles[coords.x][coords.y + 1]);
            if (coords.x > 0) {
                tilesAround.add(mapTiles[coords.x - 1][coords.y + 1]);
            }
            if (coords.x < WorldConfig.ROOM_WIDTH - 1) {
                tilesAround.add(mapTiles[coords.x + 1][coords.y + 1]);
            }
        }

        for (MapTile tile : tilesAround) {
            if (tile.getCharacterStandingOn() != null) {
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
        if (meleTarget == null) {
            drawEnemyShotTiles(mapBatch);
        } else {
            drawTilesForAtttack(mapBatch, meleTarget);
        }

        if (roomState != RoomStateEnum.FIGHT) drawNodes(mapBatch);

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

    private void drawTilesForAtttack(Batch mapBatch, CharacterDrawable meleTarget) {
        for (MapTile tile : heroInRoom.getPossibleMoveToTiles()) {
            if (Vector2.dst(
                    tile.getPositionInt().x,
                    tile.getPositionInt().y,
                    meleTarget.getPosition().x,
                    meleTarget.getPosition().y) == 1) {
                tile.drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
                tile.drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
                tile.drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
                tile.drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
            }
        }
        if (Vector2.dst(
                heroInRoom.getTileStandingOn().getPositionInt().x,
                heroInRoom.getTileStandingOn().getPositionInt().y,
                meleTarget.getPosition().x,
                meleTarget.getPosition().y) == 1) {
            heroInRoom.getTileStandingOn().drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
            heroInRoom.getTileStandingOn().drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
            heroInRoom.getTileStandingOn().drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
            heroInRoom.getTileStandingOn().drawFlag(MapPathFindingFlags.ATTACKABLE, mapBatch);
        }
    }

    private void drawEnemyShotTiles(Batch batch) {
        for (CharacterDrawable character : charactersInRoom) {
            if (character.isEnemy()) {
                if (character.getRangeTiles() != null) {
                    for (MapTile tile : character.getRangeTiles()) {
                        tile.drawFlag(MapPathFindingFlags.ATTACKABLE, batch);
                    }
                }
            }
        }
    }

    private void drawNodes(Batch mapBatch) {
        for (MapTile node : nodesInRoom) {
            node.drawFlag(MapPathFindingFlags.MOVABLE, mapBatch);
            node.drawFlag(MapPathFindingFlags.MOVABLE, mapBatch);
            node.drawFlag(MapPathFindingFlags.MOVABLE, mapBatch);
        }
        if (type == RoomTypeEnum.GOING_DOWN_ROOM) {
            for (MapTile goingDown : goingDownTiles) {
                goingDown.drawFlag(MapPathFindingFlags.MOVABLE, mapBatch);
                goingDown.drawFlag(MapPathFindingFlags.MOVABLE, mapBatch);
                goingDown.drawFlag(MapPathFindingFlags.MOVABLE, mapBatch);
            }
        }
    }

    public MapTile getTouchTile(float x, float y) {
        CoordsInt coords = WorldConfig.getIntCoordsFromFloatPoint(x, y);
        if (coords.x == -1 || coords.y == -1) return null;
        return mapTiles[coords.x][coords.y];
    }

    public void createNodes() {
        nodesInRoom = new Array<>();

        if (roomNodes.get(DirectionEnum.TOP) != null) {
            if (mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][WorldConfig.ROOM_HEIGHT - 1].isWalkable()) {
                mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][WorldConfig.ROOM_HEIGHT - 1].setIsNode(true);
                nodesInRoom.add(mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][WorldConfig.ROOM_HEIGHT - 1]);
            }
            if (mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][WorldConfig.ROOM_HEIGHT - 1].isWalkable()) {
                mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][WorldConfig.ROOM_HEIGHT - 1].setIsNode(true);
                nodesInRoom.add(mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][WorldConfig.ROOM_HEIGHT - 1]);
            }
            if (mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f + 1)][WorldConfig.ROOM_HEIGHT - 1].isWalkable()) {
                mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][WorldConfig.ROOM_HEIGHT - 1].setIsNode(true);
                nodesInRoom.add(mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][WorldConfig.ROOM_HEIGHT - 1]);
            }
        }
        if (roomNodes.get(DirectionEnum.BOTTOM) != null) {
            if (mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][0].isWalkable()) {
                mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][0].setIsNode(true);
                nodesInRoom.add(mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f)][0]);
            }
            if (mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][0].isWalkable()) {
                mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][0].setIsNode(true);
                nodesInRoom.add(mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) - 1][0]);
            }
            if (mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][0].isWalkable()) {
                mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][0].setIsNode(true);
                nodesInRoom.add(mapTiles[(int) (WorldConfig.ROOM_WIDTH / 2f) + 1][0]);
            }
        }
        if (roomNodes.get(DirectionEnum.LEFT) != null) {
            if (mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f)].isWalkable()) {
                mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f)].setIsNode(true);
                nodesInRoom.add(mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f)]);
            }
            if (mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].isWalkable()) {
                mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].setIsNode(true);
                nodesInRoom.add(mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1]);
            }
            if (mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1].isWalkable()) {
                mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1].setIsNode(true);
                nodesInRoom.add(mapTiles[0][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1]);
            }
        }
        if (roomNodes.get(DirectionEnum.RIGHT) != null) {
            if (mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f)].isWalkable()) {
                mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f)].setIsNode(true);
                nodesInRoom.add(mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f)]);
            }
            if (mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].isWalkable()) {
                mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1].setIsNode(true);
                nodesInRoom.add(mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) - 1]);
            }
            if (mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1].isWalkable()) {
                mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1].setIsNode(true);
                nodesInRoom.add(mapTiles[WorldConfig.ROOM_WIDTH - 1][(int) (WorldConfig.ROOM_HEIGHT / 2f) + 1]);
            }
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
        int range = (roomState == RoomStateEnum.CLEAN) ? -1 : character.getStats().getSpeed();
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
                                        if (characterStandingOnTile.isEnemy()) {
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
                                            if (characterStandingOnTile.isEnemy()) {
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
                //loop += nowAdded;
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
        allCharacters.addAll(charactersInRoom);

        allCharacters.sort(new Comparator<CharacterDrawable>() {
            @Override
            public int compare(CharacterDrawable t0, CharacterDrawable t1) {
                if (t0.getStats().getSpeed() < t1.getStats().getSpeed()) {
                    return -1;
                } else return 0;
            }
        });

        return allCharacters;
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
        CoordsInt targetCoords = new CoordsInt(
                WorldConfig.ROOM_WIDTH - 1 - coords.x,
                WorldConfig.ROOM_HEIGHT - 1 - coords.y);

        if (!mapTiles[targetCoords.x][targetCoords.y].isNode()) {
            if (dir == DirectionEnum.TOP || dir == DirectionEnum.BOTTOM) {
                if (mapTiles[targetCoords.x + 1][targetCoords.y].isNode()) {
                    targetCoords.x = targetCoords.x + 1;
                } else if (mapTiles[targetCoords.x - 1][targetCoords.y].isNode()) {
                    targetCoords.x = targetCoords.x - 1;
                } else if (mapTiles[targetCoords.x - 2][targetCoords.y].isNode()) {
                    targetCoords.x = targetCoords.x - 2;
                } else if (mapTiles[targetCoords.x + 2][targetCoords.y].isNode()) {
                    targetCoords.x = targetCoords.x + 2;
                }
            } else {
                if (mapTiles[targetCoords.x][targetCoords.y + 1].isNode()) {
                    targetCoords.y = targetCoords.y + 1;
                } else if (mapTiles[targetCoords.x][targetCoords.y - 1].isNode()) {
                    targetCoords.y = targetCoords.y - 1;
                } else if (mapTiles[targetCoords.x][targetCoords.y - 2].isNode()) {
                    targetCoords.y = targetCoords.y - 2;
                } else if (mapTiles[targetCoords.x][targetCoords.y + 2].isNode()) {
                    targetCoords.y = targetCoords.y + 2;
                }
            }
        }

        if (mapTiles[targetCoords.x][targetCoords.y].getCharacterStandingOn() != null) {
            if (!mapTiles[targetCoords.x][targetCoords.y].getCharacterStandingOn().isHero()) {
                //stampkill
                mapTiles[targetCoords.x][targetCoords.y].getCharacterStandingOn().dead();
            }
        }

        mapTiles[targetCoords.x][targetCoords.y].setCharacter(heroInRoom, true);
        heroInRoom.setTileStandingOn(mapTiles[targetCoords.x][targetCoords.y], true);
        heroInRoom.setPositionForce(targetCoords);

        //queue.jumpToHero();
        moveStarted();
        moveFinished();

        //currentCharacterMoving = queue.getCurrentCharacter();
        //if (currentCharacterMoving != heroInRoom) makeMove(currentCharacterMoving);

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

    public void itemDropped(AbstractItem item, CharacterDrawable character) {
        CoordsInt pos = character.getPosition();
        mapTiles[pos.x][pos.y].dropItem(item);
    }

    public void itemPickedUp(AbstractItem selectedItem) {
        MapTile tile = heroInRoom.getTileStandingOn();
        tile.getItemsToPick().removeValue(selectedItem, true);
        GuiContainer.setItemsToPick(tile.getItemsToPick());
    }

    private void makeMove(CharacterDrawable character) {

        getMoveInfo(character);
        System.out.println("Character making move now: " + character.getCharacterEnum().toString());
        if (character.getStats().getRange() > 1) { //ranged
            if (character.isEnemy()) {//enemy
                if (character.getRangeTiles() != null) {
                    for (MapTile tile : character.getRangeTiles()) {
                        if (tile.getCharacterStandingOn() != null &&
                                (tile.getCharacterStandingOn().isHero() || tile.getCharacterStandingOn().isPet())) {
                            shot(character, tile.getCharacterStandingOn());
                            return;
                        }
                    }
                }
                //if no shot - make move

                //move
                MapTile moveTile = findBestTileForRangedCharacter(character);
                if (moveTile == null) {
                    moveStarted();
                    moveFinished();
                    return;
                }

                moveToMapTileAndRefreshShot(character, moveTile);

            }
        } else { //mele
            if (character.isEnemy()) {
                //is attacktile next to current standing tile

                for (MapTile tile : character.getAttackableTiles()) {
                    if (tile.getCharacterStandingOn() != null && tile.getCharacterStandingOn().isPet()
                            && (
                            (Math.abs(tile.getPositionInt().x - character.getTileStandingOn().getPositionInt().x) == 1
                                    && Math.abs(tile.getPositionInt().y - character.getTileStandingOn().getPositionInt().y) == 0)

                                    ||

                                    (Math.abs(tile.getPositionInt().y - character.getTileStandingOn().getPositionInt().y) == 1
                                            && Math.abs(tile.getPositionInt().x - character.getTileStandingOn().getPositionInt().x) == 0))

                    ) {

                        justAttack(character, tile);
                        return;
                    } else if (tile.getCharacterStandingOn() != null && tile.getCharacterStandingOn().isHero()
                            && (
                            (Math.abs(tile.getPositionInt().x - character.getTileStandingOn().getPositionInt().x) == 1
                                    && Math.abs(tile.getPositionInt().y - character.getTileStandingOn().getPositionInt().y) == 0)

                                    ||

                                    (Math.abs(tile.getPositionInt().y - character.getTileStandingOn().getPositionInt().y) == 1
                                            && Math.abs(tile.getPositionInt().x - character.getTileStandingOn().getPositionInt().x) == 0))

                    ) {
                        justAttack(character, tile);
                        return;
                    }
                }


                //move and attack if no enemy standing next to
                for (MapTile tile : character.getAttackableTiles()) {
                    if (tile.getCharacterStandingOn() != null && tile.getCharacterStandingOn().isPet()) {
                        moveAndAttack(character, tile);
                        return;
                    } else if (tile.getCharacterStandingOn() != null && tile.getCharacterStandingOn().isHero()) {
                        moveAndAttack(character, tile);
                        return;
                    }
                }
                //if no attack - just move
                if (findBestTileForMeleCharacter(character) != null) {
                    moveToMapTile(character, findBestTileForMeleCharacter(character));
                } else {
                    moveStarted();
                    moveFinished();
                }
            }
            //System.out.println("SOMETHING NOT OK - " + character.getCharacterEnum().toString());
        }
    }

    private void shot(CharacterDrawable shooter, CharacterDrawable target) {
        TurnAction action = new TurnAction();
        action.addAction(new TurnStarted(this));
        action.addAction(new Wait(WorldConfig.SHOT_DURATION));
        shooter.getProjectile().addAction(new Shot(shooter, target, shooter.getProjectile(), null, shooter.getShotDamage()));
        action.addAction(new DamageGiving(target, shooter.getShotDamage()));
        action.addAction(new TurnFinished(this));
        shooter.addAction(action.getSequence());
    }

    private void moveToMapTileAndRefreshShot(CharacterDrawable character, MapTile bestTileForDistanceCharacter) {
        TurnAction action = new TurnAction();
        action.addAction(new TurnStarted(this));
        action.addAction(getWay(character.getPossibleMovements(), bestTileForDistanceCharacter, character));
        action.addAction(new RefreshShotableTiles(character, this));
        action.addAction(new TurnFinished(this));
        character.addAction(action.getSequence());
    }

    private void justAttack(CharacterDrawable character, MapTile tile) {
        TurnAction action = new TurnAction();
        action.addAction(new TurnStarted(this));
        action.addSequenceAction(new Attack(character, tile.getCharacterStandingOn(), character.getTileStandingOn()).getSequence());
        action.addAction(new TurnFinished(this));
        character.addAction(action.getSequence());
    }

    private void moveToMapTile(CharacterDrawable character, MapTile tile) {
        TurnAction action = new TurnAction();
        action.addAction(new TurnStarted(this));
        action.addAction(getWay(character.getPossibleMovements(), tile, character));
        action.addAction(new TurnFinished(this));
        character.addAction(action.getSequence());
    }

    private void moveAndAttack(CharacterDrawable character, MapTile attackTile) {
        for (MapTile moveTile : character.getPossibleMoveToTiles()) {
            if ((Math.abs(moveTile.getPositionInt().x - attackTile.getPositionInt().x) == 0
                    && Math.abs(moveTile.getPositionInt().y - attackTile.getPositionInt().y) == 1)
                    ||

                    (Math.abs(moveTile.getPositionInt().x - attackTile.getPositionInt().x) == 1
                            && Math.abs(moveTile.getPositionInt().y - attackTile.getPositionInt().y) == 0)) {

                //good tile found


                TurnAction action = new TurnAction();
                action.addAction(new TurnStarted(this));
                action.addAction(getWay(character.getPossibleMovements(), moveTile, character));

                //action.addAction(new Attack(character, attackTile.getCharacterStandingOn()));
                action.addSequenceAction(new Attack(character, attackTile.getCharacterStandingOn(), moveTile).getSequence());

                action.addAction(new TurnFinished(this));
                character.addAction(action.getSequence());

                break;
            }
        }
    }

    private void moveAndAttack(CharacterDrawable character, MapTile attackTile, CharacterDrawable target) {
        TurnAction action = new TurnAction();
        action.addAction(new TurnStarted(this));
        action.addAction(getWay(character.getPossibleMovements(), attackTile, character));
        //action.addAction(new Attack(character, attackTile.getCharacterStandingOn()));
        action.addSequenceAction(new Attack(character, target, attackTile).getSequence());
        action.addAction(new TurnFinished(this));
        character.addAction(action.getSequence());

    }


    private MapTile findBestTileForMeleCharacter(CharacterDrawable character) {
        MapTile result = null;
        float closestToTargetTile = 999f;

        CharacterDrawable targetToAttack = null;
        float closestToTarget = 999f;

        System.out.println("CHUUUJ:  " + character.getPossibleMoveToTiles().size);

        if (character.isPet()) {
            for (CharacterDrawable maybeTarget : charactersInRoom) {
                if (maybeTarget.isEnemy() || maybeTarget.getCharacterEnum().toString().startsWith("BOSS")) {
                    float distance = Vector2.dst(
                            character.getPosition().x,
                            character.getPosition().y,
                            maybeTarget.getPosition().x,
                            maybeTarget.getPosition().y);

                    if (distance < closestToTarget) {
                        targetToAttack = maybeTarget;
                        closestToTarget = distance;
                    }
                }
            }
        } else if (character.isEnemy()) {
            for (CharacterDrawable maybeTarget : charactersInRoom) {
                if (maybeTarget.isHero() || maybeTarget.isPet()) {
                    float distance = Vector2.dst(
                            character.getPosition().x,
                            character.getPosition().y,
                            maybeTarget.getPosition().x,
                            maybeTarget.getPosition().y);
                    if (distance < closestToTarget) {
                        targetToAttack = maybeTarget;
                        closestToTarget = distance;
                    }
                }
            }
        }


        for (MapTile maybeTile : character.getPossibleMoveToTiles()) {
            float distance = Vector2.dst(
                    maybeTile.getPositionInt().x,
                    maybeTile.getPositionInt().y,
                    targetToAttack.getTileStandingOn().getPositionInt().x,
                    targetToAttack.getTileStandingOn().getPositionInt().y);

            if (distance < closestToTargetTile) {
                result = maybeTile;
                closestToTargetTile = distance;
            }
        }

        return result;
    }

    private Array<MapTile> getTilesBetweenCharacters(CoordsInt start, CoordsInt end, int shotRange) {
        int minX, maxX, minY, maxY, width, height;
        int minCharX, maxCharX, minCharY, maxCharY;

        if (start.x < end.x) { //shot to the right
            minX = start.x;
            maxX = WorldConfig.ROOM_WIDTH - 1;
            minCharX = start.x;
            maxCharX = end.x;
        } else if (start.x > end.x) { //shot to the left
            minX = 0;
            maxX = start.x;
            minCharX = end.x;
            maxCharX = start.x;
        } else { //shot vertically
            minX = start.x;
            maxX = end.x;
            minCharX = start.x;
            maxCharX = start.x;
        }

        if (start.y < end.y) { //shot up
            minY = start.y;
            maxY = WorldConfig.ROOM_HEIGHT - 1;
            minCharY = start.y;
            maxCharY = end.y;
        } else if (start.y > end.y) { //shot down
            minY = 0;
            maxY = start.y;
            maxCharY = start.y;
            minCharY = end.y;
        } else { //shot horizontally
            minY = start.y;
            maxY = end.y;
            minCharY = start.y;
            maxCharY = end.y;
        }

        Array<MapTile> result = new Array<>();

        if (minCharX == maxCharX) { //vertical shot
            for (int y = minY; y <= maxY; y++) {
                float rangeDistance = Vector2.dst(start.x, start.y, minCharX, y);
                if (rangeDistance <= shotRange)
                    result.add(mapTiles[minCharX][y]);
            }
            return result;
        } else {
            float a = (float) ((minCharY - maxCharY)) / (float) (minCharX - maxCharX);

            if (start.y > end.y && start.x < end.x) a *= -1;
            else if (end.y > start.y && end.x < start.x) a *= -1;
            //f(minCharX) = minCharY
            //a * minCharX + b = minCharY
            //b = minCharY - a*minCharX
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA: " + a);

            float b = 0;
            if (start.x < end.x) b = start.y - a * start.x;
            else b = end.y - a * end.x;

            System.out.println("BBBBBBBBBBBBBB: " + b);
            //f(x) = ax + b

            float distanceFromLine;
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    distanceFromLine =
                            Math.abs(
                                    (a * x + b) //f(x)
                                            - y
                            );
                    float rangeDistance = Vector2.dst(start.x, start.y, x, y);
                    if (rangeDistance <= shotRange && distanceFromLine < 1.9f)
                        result.add(mapTiles[x][y]);
                }
            }
            return result;
        }
    }

    private MapTile findBestTileForRangedCharacter(CharacterDrawable character) {
        //character.getPossibleWays(character.getSpeed());
        getMoveInfo(character);
        Array<Array<MapTile>> allArrays = character.getPossibleMovements();
        CoordsInt coords = character.getPosition();
        MapTile result = null;
        float bestResult = 0;

        for (MapTile mapTile : character.getPossibleMoveToTiles()) {

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

                    if (temp <= distanceToClosestEnemy) distanceToClosestEnemy = temp;
                }
            }

            if (distanceToClosestEnemy <= character.getStats().getRange()) currResult += 1000;

            if (currResult > 1000) { //enough range for shot
                currResult += distanceToClosestEnemy;
            } else { //too far away for shot
                currResult -= distanceToClosestEnemy;
            }

            if (currResult > bestResult) {
                result = mapTile;
                bestResult = currResult;
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

    public void getShotInfo(CharacterDrawable characterDrawable) {

        //check range targets from new tile
        CharacterDrawable target = null;
        float currentTargetDistance = -1;
        for (CharacterDrawable maybeTarget : charactersInRoom) {
            if (maybeTarget != characterDrawable
                    && (maybeTarget.isPet() || maybeTarget.isHero())) {

                float distance = Vector2.dst(
                        characterDrawable.getTileStandingOn().getPositionInt().x,
                        characterDrawable.getTileStandingOn().getPositionInt().y,
                        maybeTarget.getPosition().x,
                        maybeTarget.getPosition().y);


                if (distance <= characterDrawable.getStats().getRange()) {
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
            characterDrawable.setRangeTiles(
                    getTilesBetweenCharacters(characterDrawable.getTileStandingOn().getPositionInt(),
                            target.getPosition(), characterDrawable.getStats().getRange()));
        } else characterDrawable.setRangeTiles(null);

    }

    public void setMeleTarget(CharacterDrawable target) {
        this.meleTarget = target;
    }

    public void shotSelected(CharacterDrawable target) {
        shot(this.currentCharacterMoving, target);
    }

    public void newHero(CharacterEnum newHero) {
        heroInRoom.newCharacter(newHero);
        queue = new TurnQueue(this);
    }

    public void enemyDied(CharacterDrawable characterDrawable) {
        if (characterDrawable.isBoss()) {
            itemDropped(ItemGenerator.getItemUnique(), characterDrawable);
        } else if (EpicDungeonTactics.random.nextFloat() <= StatTracker.getCurrentStat(CompleteHeroStatsEnum.EXP_MULTIPLIER)) {
            itemDropped(ItemGenerator.getItem(), characterDrawable);
        }

        StatTracker.expEffect(
                (int) ((CharacterStats.getCharacterBasicExpReward(characterDrawable.getCharacterEnum())
                        + StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * CharacterStats.getCharacterLvlExpReward(characterDrawable.getCharacterEnum()))
                        * StatTracker.getCurrentStat(CompleteHeroStatsEnum.EXP_MULTIPLIER)));

        int goldCollected =
                (int) ((CharacterStats.getCharacterBasicGoldReward(characterDrawable.getCharacterEnum())
                        + StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * CharacterStats.getCharacterLvlGoldReward(characterDrawable.getCharacterEnum()))
                        * StatTracker.getCurrentStat(CompleteHeroStatsEnum.GOLD_MULTIPLIER));

        GameScreen.goldCollected(goldCollected);
        GlobalValues.addGold(goldCollected);


        for (MapTile[] m1 : mapTiles) {
            for (MapTile m : m1) {
                if (m.getCharacterStandingOn() == characterDrawable) {
                    m.setCharacter(null, false);
                    m.setCharacterStandingOn(null);
                    //m.getCharacterStandingOn().getColor().a = 0f;
                }
            }
        }
        characterDrawable.dead();
        charactersInRoom.removeIndex(charactersInRoom.indexOf(characterDrawable, false));

        CommunicatePrinter.killedEnemy(StringsManager.getCharacterName(characterDrawable.getCharacterEnum()), characterDrawable.isBoss());
        queue.removeCharacter(characterDrawable);

        if (charactersInRoom.size == 1) {
            roomState = RoomStateEnum.CLEAN;
            GameScreen.roomCleared();
            if (type == RoomTypeEnum.BOSS_ROOM) {
                type = RoomTypeEnum.GOING_DOWN_ROOM;
                SoundsManager.playSound(SoundEnum.BOSS_DEFEATED);
            }
        }


    }

    public void removeBody(Body body) {
        this.world.destroyBody(body);
    }
}
