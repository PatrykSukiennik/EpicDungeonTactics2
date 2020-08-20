package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class Hero extends CharacterDrawable {
    public Hero(CharacterEnum characterEnum, RayHandler handler, World world) {
        super(characterEnum, new CoordsInt(0, 0), handler, world);
    }

    public Hero(CharacterEnum characterEnum, CoordsInt coords, RayHandler handler, World world) {
        super(characterEnum, coords, handler, world);
    }


}
