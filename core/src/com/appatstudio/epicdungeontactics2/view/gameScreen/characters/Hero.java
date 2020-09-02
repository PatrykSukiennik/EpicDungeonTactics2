package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.enums.RoomStateEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.BOTTOM;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.LEFT;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.RIGHT;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.TOP;

public class Hero extends CharacterDrawable {
    public Hero(CharacterEnum characterEnum, RayHandler handler, World world, Room room, MapTile tile) {
        super(characterEnum, new CoordsInt(0, 0), handler, world, room, tile);
    }

    public Hero(CharacterEnum characterEnum, CoordsInt coords, RayHandler handler, World world, Room room, MapTile tile) {
        super(characterEnum, coords, handler, world, room, tile);
    }

    @Override
    public void getPossibleWays() {
        this.setPossibleMovements(
                room.findWays(
                        this.getPosition(),
                        room.getRoomState() == RoomStateEnum.CLEAN ?
                                -1 : StatTracker.getStats().getSpeed()
                ));
    }

    @Override
    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(getCharacterEnum());
    }

    @Override
    public void setPosition(CoordsInt coords) {
        super.setPosition(coords);
        setTileStandingOn(room.getTiles()[coords.x][coords.y]);

        if (getTileStandingOn().getFlag() == MapPathFindingFlags.ROOM_NODE) {
            if (coords.x == 0) {
                room.changeRoom(LEFT, coords);
            } else if (coords.x == WorldConfig.ROOM_WIDTH - 1) {
                room.changeRoom(RIGHT, coords);
            } else if (coords.y == WorldConfig.ROOM_HEIGHT - 1) {
                room.changeRoom(TOP, coords);
            } else if (coords.y == 0) {
                room.changeRoom(BOTTOM, coords);
            }
        }
        else if (getTileStandingOn().getFlag() == MapPathFindingFlags.NEW_STAGE) {
            room.newStage();
        }
    }
}
