package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Hero extends CharacterDrawable {
    public Hero(CharacterEnum characterEnum) {
        super(characterEnum, new CoordsInt(0, 0));
    }


}
