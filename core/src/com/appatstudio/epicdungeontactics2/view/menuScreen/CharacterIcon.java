package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosText;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosTextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class CharacterIcon extends Image {

    CharacterEnum characterEnum;

    RelativePosText title;
    RelativePosTextWithIcon cost;

    boolean isUnlocked = false;

    CharacterIcon(CharacterEnum character) {
        this.characterEnum = character;

        this.setSize(Gdx.graphics.getWidth()/2f, Gdx.graphics.getWidth()/2f);
        this.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2f - this.getHeight());

        title = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_TITLE_UNLOCKED : FontEnum.MENU_HERO_TITLE_LOCKED),
                StringsManager.getCharacterName(characterEnum)
                );

        if (!isUnlocked) this.getColor().a = 0.3f;
    }

    boolean tap(float x, float y) {
        return x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight();
    }

    public CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        title.draw(batch,
                this.getX() + this.getWidth()/2f,
                this.getY() + this.getHeight() * 1.2f);
    }
}
