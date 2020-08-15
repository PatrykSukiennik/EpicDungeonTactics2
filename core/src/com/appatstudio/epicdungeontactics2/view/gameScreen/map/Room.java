package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapGenerator;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoElementsLocations;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.SpriteElement;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class Room {

    private SpriteDrawable mapDrawable;
    private MapTile[][] mapTiles;
    private CoordsInt position;
    private RoomTypeEnum type;
    private RoomEnum roomEnum;
    private HashMap<DirectionEnum, Room> roomNodes;

    private Hero heroInRoom;
    private Array<CharacterDrawable> charactersInRoom;

    public Room(RoomTypeEnum type, int stage, CoordsInt position) {
        roomNodes = new HashMap<>();
        roomNodes.put(DirectionEnum.TOP, null);
        roomNodes.put(DirectionEnum.RIGHT, null);
        roomNodes.put(DirectionEnum.BOTTOM, null);
        roomNodes.put(DirectionEnum.LEFT, null);

        this.roomEnum = MapGenerator.getRandomRoom(type, stage);

        this.position = position;
        this.type = type;

        charactersInRoom = new Array<>();

        mapDrawable = new SpriteDrawable(new Sprite(new Texture("maps/1_FOREST_1.jpg")));

        mapTiles = new MapTile[WorldConfig.ROOM_WIDTH][WorldConfig.ROOM_HEIGHT];

        for (int x=0; x<WorldConfig.ROOM_WIDTH; x++) {
            for (int y=0; y<WorldConfig.ROOM_HEIGHT; y++) {
                mapTiles[x][y] = new MapTile(x, y);
            }
        }

        MapElementAnimationEnum[][] animatedElements = MapInfoElementsLocations.getAnimatedElements(roomEnum);
        MapElementSpriteEnum[][] spriteElements = MapInfoElementsLocations.getSpriteElements(roomEnum);

        for (int x=0; x<WorldConfig.ROOM_WIDTH; x++) {
            for (int y=0; y<WorldConfig.ROOM_HEIGHT; y++) {
                if (animatedElements[x][y] != null) mapTiles[x][y].setAnimatedElement(
                        new AnimatedElement(animatedElements[x][y]));
                else if (spriteElements[x][y] != null) mapTiles[x][y].setSpriteElement(
                        new SpriteElement(spriteElements[x][y]));
            }
        }


        charactersInRoom.add(new Hero(StatTracker.getHero()));
        heroInRoom = (Hero)charactersInRoom.get(0);

    }

    public void draw(Batch mapBatch, Batch guiBatch) {
        mapBatch.begin();
        mapDrawable.draw(mapBatch, WorldConfig.ROOM_POS_X, WorldConfig.ROOM_POS_Y, WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES);
        for (CharacterDrawable c : charactersInRoom) c.draw(mapBatch, 1f);
        for (int x=WorldConfig.ROOM_WIDTH-1; x>=0; x--) {
            for (int y=WorldConfig.ROOM_HEIGHT-1; y>=0; y--) {
                mapTiles[x][y].draw(mapBatch);
            }
        }
        mapBatch.end();
    }

    public MapTile getTouchTile(float x, float y) {
        CoordsInt coords = WorldConfig.getIntCoordsFromFloatPoint(x, y);
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


}
