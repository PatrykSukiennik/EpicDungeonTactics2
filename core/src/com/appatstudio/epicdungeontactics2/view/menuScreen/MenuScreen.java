package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
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
        if (characterSelector.tap(x, y)) {}

        return true;
    }

    public void swiped(DirectionEnum directionEnum) {
        characterSelector.swiped(directionEnum);
    }

}
