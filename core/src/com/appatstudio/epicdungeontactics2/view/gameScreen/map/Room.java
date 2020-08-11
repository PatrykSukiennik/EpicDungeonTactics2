package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.HashMap;

public class Room {

    private SpriteDrawable mapDrawable;
    private MapTile[][] mapTiles;
    private CoordsInt position;
    private RoomTypeEnum type;
    private HashMap<DirectionEnum, Room> roomNodes;

    public Room(RoomTypeEnum type, int stage, CoordsInt position) {
        roomNodes = new HashMap<>();
        roomNodes.put(DirectionEnum.TOP, null);
        roomNodes.put(DirectionEnum.RIGHT, null);
        roomNodes.put(DirectionEnum.BOTTOM, null);
        roomNodes.put(DirectionEnum.LEFT, null);

        this.position = position;
        this.type = type;

        mapDrawable = new SpriteDrawable(new Sprite(new Texture("maps/1_FOREST_1.jpg")));
    }

    public void draw(Batch mapBatch, Batch guiBatch) {
        mapBatch.begin();
        mapDrawable.draw(mapBatch, WorldConfig.ROOM_POS_X, WorldConfig.ROOM_POS_Y, WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES);
        mapBatch.end();
    }

    public MapTile getTouchTile(float x, float y) {
        CoordsInt coords = WorldConfig.getTouchCoords(x, y);
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
