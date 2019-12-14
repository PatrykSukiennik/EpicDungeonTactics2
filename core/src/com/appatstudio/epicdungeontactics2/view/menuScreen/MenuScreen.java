package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public final class MenuScreen {

    private Stage stage;
    private CharacterSelector characterSelector;

    public MenuScreen() {
        stage = new Stage();
        characterSelector = new CharacterSelector(stage);
    }

    public void draw(SpriteBatch batch) {
        stage.draw();
    }

}
