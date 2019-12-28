package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class MenuScreen {

    private SpriteBatch batch;
    private CharacterSelector characterSelector;

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

}
