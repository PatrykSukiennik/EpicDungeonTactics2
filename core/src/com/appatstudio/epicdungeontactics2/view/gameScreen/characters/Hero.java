package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomStateEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

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
}
