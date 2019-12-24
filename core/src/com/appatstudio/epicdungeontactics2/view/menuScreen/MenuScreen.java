package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public final class MenuScreen {

    private SpriteBatch batch;
    private CharacterSelector characterSelector;
    private static PerkWindow perkWindow;

    public MenuScreen() {
        batch = new SpriteBatch();

        characterSelector = new CharacterSelector();
    }

    public static void showPerkSelector() {
        perkWindow.show();
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

}
