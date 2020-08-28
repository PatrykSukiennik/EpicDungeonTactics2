package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
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
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.CameraHandler;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.ChangeState;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.MoveToMapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.RemoveFromTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.SwitchMapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.TurnFinished;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.SpriteElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import box2dLight.RayHandler;

public class Room {

    private SpriteDrawable mapDrawable;
    private MapTile[][] mapTiles;
    private CoordsInt position;
    private RoomTypeEnum type;
    private RoomEnum roomEnum;
    private HashMap<DirectionEnum, Room> roomNodes;

    private CharacterDrawable currentCharacterMoving;
    private Hero heroInRoom;
    private Array<CharacterDrawable> charactersInRoom;

    private RoomStateEnum roomState;
    private Array<Array<MapTile>> paths;

    private World world;
    private RayHandler rayHandler;
    private static Box2DDebugRenderer b2dr;

    private boolean freezeTime = false;

    public Room(RoomTypeEnum type, int stage, CoordsInt position) {
        roomNodes = new HashMap<>();
        roomNodes.put(DirectionEnum.TOP, null);
        roomNodes.put(DirectionEnum.RIGHT, null);
        roomNodes.put(DirectionEnum.BOTTOM, null);
        roomNodes.put(DirectionEnum.LEFT, null);

        this.roomEnum = MapGenerator.getRandomRoom(type, stage);

        this.position = position;
        this.type = type;

        this.roomState = RoomStateEnum.CLEAN; //todo

        charactersInRoom = new Array<>();

        world = new World(new Vector2(0, 0), true);
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(LightsConfig.getAmbientColor(stage));
        b2dr = new Box2DDebugRenderer(); //todo

        mapDrawable = new SpriteDrawable(new Sprite(new Texture("maps/1_FOREST_1.jpg")));

        mapTiles = new MapTile[WorldConfig.ROOM_WIDTH][WorldConfig.ROOM_HEIGHT];

        for (int x = 0; x < WorldConfig.ROOM_WIDTH; x++) {
            for (int y = 0; y < WorldConfig.ROOM_HEIGHT; y++) {
                mapTiles[x][y] = new MapTile(x, y, true); //todo walkable array
            }
        }

        MapElementAnimationEnum[][] animatedElements = MapInfoElementsLocations.getAnimatedElements(roomEnum);
        MapElementSpriteEnum[][] spriteElements = MapInfoElementsLocations.getSpriteElements(roomEnum);

        for (int x = 0; x < WorldConfig.ROOM_WIDTH; x++) {
            for (int y = 0; y < WorldConfig.ROOM_HEIGHT; y++) {
                if (animatedElements[x][y] != null) mapTiles[x][y].setAnimatedElement(
                        new AnimatedElement(animatedElements[x][y],
                                new CoordsFloat(
                                        WorldConfig.getTileCoord(x, y).x,
                                        WorldConfig.getTileCoord(x, y).y),
                                rayHandler, world));

                else if (spriteElements[x][y] != null) mapTiles[x][y].setSpriteElement(
                        new SpriteElement(spriteElements[x][y],
                                new CoordsFloat(
                                        WorldConfig.getTileCoord(x, y).x,
                                        WorldConfig.getTileCoord(x, y).y),
                                rayHandler, world));
            }
        }


        charactersInRoom.add(new Hero(StatTracker.getHero(), new CoordsInt(WorldConfig.ROOM_WIDTH / 2, WorldConfig.ROOM_HEIGHT / 2), rayHandler, world, this));
        heroInRoom = (Hero) charactersInRoom.get(0);

        if (type == RoomTypeEnum.FIRST_ROOM) {
            mapTiles[WorldConfig.ROOM_WIDTH / 2][WorldConfig.ROOM_HEIGHT / 2]
                    .setCharacter(heroInRoom);
        }

        if (type == RoomTypeEnum.FIRST_ROOM) {
            System.out.println("dec");
            heroInRoom.getPossibleWays(); //todo first move
        }

    }

    public boolean tap(float x, float y) { //todo
        MapTile tapped = getTouchTile(x, y);
        if (tapped == null) return true;

        if (!freezeTime) {
            if (roomState == RoomStateEnum.CLEAN) {
                if (heroInRoom.isReady()) {
                    if (tapped.getFlag() == MapPathFindingFlags.MOVABLE ||
                            tapped.getFlag() == MapPathFindingFlags.ITEM_MOVABLE) {
                        heroInRoom.moveToMapTile(tapped);
                        moveStarted();
                    }
                }
            } else {

            }
        }

        return true;
    }

    public void moveStarted() {
        this.freezeTime = true;
    }

    public void moveFinished() {
        this.freezeTime = false;
        if (roomState == RoomStateEnum.CLEAN) {
            heroInRoom.setPossibleMovements(
                    findWays(
                            heroInRoom.getPosition(),
                            -1
                    ));
        }
    }

    public void draw(Batch mapBatch, Batch guiBatch, OrthographicCamera camera) {

        mapBatch.begin();
        mapDrawable.draw(mapBatch, WorldConfig.ROOM_POS_X, WorldConfig.ROOM_POS_Y, WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES);
        for (int x = WorldConfig.ROOM_WIDTH - 1; x >= 0; x--) {
            for (int y = WorldConfig.ROOM_HEIGHT - 1; y >= 0; y--) {
                mapTiles[x][y].draw(mapBatch);
            }
        }
        mapBatch.end();

        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
        b2dr.render(world, camera.combined.scl(1f));
    }

    public MapTile getTouchTile(float x, float y) {
        CoordsInt coords = WorldConfig.getIntCoordsFromFloatPoint(x, y);
        if (coords.x == -1 || coords.y == -1) return null;
        return mapTiles[coords.x][coords.y];
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

    public Array<Array<MapTile>> findWays(CoordsInt start, int range) {
        Array<Array<MapTile>> allPaths = new Array<>();

        int lifeTime = range == -1 ? WorldConfig.ROOM_HEIGHT * WorldConfig.ROOM_WIDTH * 2 : range;

        int nowAdded = 0;
        int lastlyAdded = 0;
        int currLimit = 1;
        int currSize = 0;
        int currPathLen = 0;
        CoordsInt currCoords;

        allPaths.add(new Array<MapTile>());

        for (int loop = 0; loop < lifeTime; loop++) {

            System.out.println("LOOP: " + loop);

            for (int i = currSize; i < currLimit; i++) {
                currPathLen = allPaths.get(i).size;
                System.out.println("i: " + i);
                if (currPathLen == 0) { //first loop
                    currCoords = start;
                    System.out.println("currsize: " + loop);

                    if (currCoords.x > 0 &&
                            mapTiles[currCoords.x - 1][currCoords.y].isWalkable() &&
                            mapTiles[currCoords.x - 1][currCoords.y].getFlag() == MapPathFindingFlags.NONE) {
                        allPaths.add(new Array<MapTile>());
                        allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x - 1][currCoords.y]);
                        mapTiles[currCoords.x - 1][currCoords.y].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                        nowAdded++;
                    }
                    if (currCoords.x < WorldConfig.ROOM_WIDTH - 1 &&
                            mapTiles[currCoords.x + 1][currCoords.y].isWalkable() &&
                            mapTiles[currCoords.x + 1][currCoords.y].getFlag() == MapPathFindingFlags.NONE) {
                        allPaths.add(new Array<MapTile>());
                        allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x + 1][currCoords.y]);
                        mapTiles[currCoords.x + 1][currCoords.y].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                        nowAdded++;
                    }
                    if (currCoords.y > 0 &&
                            mapTiles[currCoords.x][currCoords.y - 1].isWalkable() &&
                            mapTiles[currCoords.x][currCoords.y - 1].getFlag() == MapPathFindingFlags.NONE) {
                        allPaths.add(new Array<MapTile>());
                        allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x][currCoords.y - 1]);
                        mapTiles[currCoords.x][currCoords.y - 1].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                        nowAdded++;
                    }
                    if (currCoords.y < WorldConfig.ROOM_HEIGHT - 1 &&
                            mapTiles[currCoords.x][currCoords.y + 1].isWalkable() &&
                            mapTiles[currCoords.x][currCoords.y + 1].getFlag() == MapPathFindingFlags.NONE) {
                        allPaths.add(new Array<MapTile>());
                        allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x][currCoords.y + 1]);
                        mapTiles[currCoords.x][currCoords.y + 1].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                        nowAdded++;
                    }
                } else {
                    for (int j = 0; j < currPathLen; j++) {
                        currCoords = allPaths.get(i).get(j).getPositionInt();

                        if (currCoords.x > 0 &&
                                mapTiles[currCoords.x - 1][currCoords.y].isWalkable() &&
                                mapTiles[currCoords.x - 1][currCoords.y].getFlag() == MapPathFindingFlags.NONE) {
                            allPaths.add(new Array<MapTile>());
                            allPaths.get(allPaths.size - 1).addAll(allPaths.get(i));
                            allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x - 1][currCoords.y]);
                            mapTiles[currCoords.x - 1][currCoords.y].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                            nowAdded++;
                        }
                        if (currCoords.x < WorldConfig.ROOM_WIDTH - 1 &&
                                mapTiles[currCoords.x + 1][currCoords.y].isWalkable() &&
                                mapTiles[currCoords.x + 1][currCoords.y].getFlag() == MapPathFindingFlags.NONE) {
                            allPaths.add(new Array<MapTile>());
                            allPaths.get(allPaths.size - 1).addAll(allPaths.get(i));
                            allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x + 1][currCoords.y]);
                            mapTiles[currCoords.x + 1][currCoords.y].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                            nowAdded++;
                        }
                        if (currCoords.y > 0 &&
                                mapTiles[currCoords.x][currCoords.y - 1].isWalkable() &&
                                mapTiles[currCoords.x][currCoords.y - 1].getFlag() == MapPathFindingFlags.NONE) {
                            allPaths.add(new Array<MapTile>());
                            allPaths.get(allPaths.size - 1).addAll(allPaths.get(i));
                            allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x][currCoords.y - 1]);
                            mapTiles[currCoords.x][currCoords.y - 1].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                            nowAdded++;
                        }
                        if (currCoords.y < WorldConfig.ROOM_HEIGHT - 1 &&
                                mapTiles[currCoords.x][currCoords.y + 1].isWalkable() &&
                                mapTiles[currCoords.x][currCoords.y + 1].getFlag() == MapPathFindingFlags.NONE) {
                            allPaths.add(new Array<MapTile>());
                            allPaths.get(allPaths.size - 1).addAll(allPaths.get(i));
                            allPaths.get(allPaths.size - 1).add(mapTiles[currCoords.x][currCoords.y + 1]);
                            mapTiles[currCoords.x][currCoords.y + 1].setFlag(MapPathFindingFlags.MOVABLE, allPaths.size - 1);
                            nowAdded++;
                        }
                    }
                }
            }

            System.out.println("NOW ADDED: " + nowAdded);

            if (nowAdded == 0) break;
            else {
                loop += nowAdded;
                currSize += lastlyAdded;
                lastlyAdded = nowAdded;
                currLimit = allPaths.size;
                nowAdded = 0;
            }
        }
        System.out.println("size: " + allPaths.size);
        return allPaths;
    }

    public MoveToMapTile getWay(Array<Array<MapTile>> allPaths, MapTile end, CharacterDrawable characterDrawable) {
        //System.out.println(end.getPathFindingFlag());
        //System.out.println(end.getPositionInt().x + " " + end.getPositionInt().y);
        //System.out.println(allPaths.get(end.getPathFindingFlag()).size);
        Array<MapTile> path = allPaths.get(end.getPathFindingIndex());
        SequenceAction result = new SequenceAction();
        CoordsFloat coords;
        CoordsInt coordsInt = characterDrawable.getPosition();

        MapTile oldTile = mapTiles[coordsInt.x][coordsInt.y];
        MapTile newTile = null;

        result.addAction(new ChangeState(characterDrawable, CharacterStateEnum.RUN));

        for (MapTile mapTile : path) {
            newTile = mapTile;
            coords = mapTile.getPositionFloat();

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
            //System.out.println("old: " + oldTile.getPositionInt().x + " " + oldTile.getPositionInt().y);
            //System.out.println("new: " + newTile.getPositionInt().x + " " + newTile.getPositionInt().y);
        }

        result.addAction(new SwitchMapTile(oldTile, newTile, characterDrawable));
        result.addAction(new ChangeState(characterDrawable, CharacterStateEnum.IDLE));
        result.addAction(new TurnFinished(this));

        resetPathfindingFlags();

        return new MoveToMapTile(
                result,
                path.size *
                        (roomState == RoomStateEnum.CLEAN ?
                                WorldConfig.MOVE_SPEED_CLEAN : WorldConfig.MOVE_SPEED_FIGHT));
    }

    private void resetPathfindingFlags() {
        for (int x = WorldConfig.ROOM_WIDTH - 1; x >= 0; x--) {
            for (int y = WorldConfig.ROOM_HEIGHT - 1; y >= 0; y--) {
                mapTiles[x][y].setFlag(MapPathFindingFlags.NONE, 0);
            }
        }
    }

}
