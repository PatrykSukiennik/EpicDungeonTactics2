package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class MenuScreen {

    private SpriteBatch batch;
    private CharacterSelector characterSelector;

    public static final float BOTTOM_BUTTON_HEIGHT, BOTTOM_BUTTON_WIDTH;

    static {
        BOTTOM_BUTTON_WIDTH = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.4f : Gdx.graphics.getWidth() * 0.5f;
        BOTTOM_BUTTON_HEIGHT = BOTTOM_BUTTON_WIDTH/3f;
    }

    public MenuScreen() {
        batch = new SpriteBatch();

        characterSelector = new CharacterSelector();

    }

    public void draw() {
        batch.begin();
        characterSelector.draw(batch);
        batch.end();
    }

    public boolean tap(float x, float y) {
        characterSelector.tap(x, y);

        return true;
    }

    public void swiped(DirectionEnum directionEnum) {
        characterSelector.swiped(directionEnum);
    }

    public void updateGold() {
        characterSelector.updateGold();
    }

}
