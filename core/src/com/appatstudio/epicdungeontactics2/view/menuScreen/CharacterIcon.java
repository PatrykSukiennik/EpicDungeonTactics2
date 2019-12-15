package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.HeroEnum;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class CharacterIcon extends Image {

    CharacterEnum characterEnum;
    HeroEnum heroEnum;
    boolean isUnlocked = false;

    CharacterIcon(HeroEnum heroEnum, CharacterEnum character) {
        this.heroEnum = heroEnum;
        this.characterEnum = character;

        if (!isUnlocked) this.getColor().a = 0.3f;
    }

    boolean tap(float x, float y) {
        return x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight();
    }

    public CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    public HeroEnum getHeroEnum() {
        return heroEnum;
    }
}
