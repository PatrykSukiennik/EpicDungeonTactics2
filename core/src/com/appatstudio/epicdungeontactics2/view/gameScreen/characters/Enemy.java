package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class Enemy extends CharacterDrawable {
    private CharacterStatsObject stats;

    public Enemy(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world, Room room) {
        super(characterEnum, position, rayHandler, world, room);
    }

    @Override
    public void getPossibleWays() {
        this.setPossibleMovements(room.findWays(this.getPosition(), stats.getSpeed()));
    }
}
