package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

public final class MenuScreen {

    private SpriteBatch batch;
    private CharacterSelector characterSelector;

    private TextWithIcon settingsButton;
    private SettingsWindow settingsWindow;

    private static PerkWindow perkWindow;

    public MenuScreen() {
        batch = new SpriteBatch();

        characterSelector = new CharacterSelector();
        settingsButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.SETTINGS_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                "Settings",
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.1f,
                Align.left);

        settingsWindow = new SettingsWindow(
                GraphicsManager.getGuiElement(GuiElementEnum.STONE_COMMUNICATE_WINDOW),
                Gdx.graphics.getWidth()/2f - (Gdx.graphics.getWidth() * 0.75f/2f),
                Gdx.graphics.getHeight()/2f - (Gdx.graphics.getWidth() * 0.75f),
                Gdx.graphics.getWidth() * 0.75f,
                (Gdx.graphics.getWidth() * 0.75f) * 2
        );
    }

    public static void showPerkSelector() {
        perkWindow.show();
    }

    public void draw() {
        batch.begin();
        characterSelector.draw(batch);

        settingsButton.draw(batch);

        if (settingsWindow.isUp()) settingsWindow.draw(batch);

        batch.end();
    }

    public boolean tap(float x, float y) {
        if (settingsWindow.isUp()) {
            if (settingsWindow.tap(x, y)) {}
            else {
                settingsWindow.hide();
            }
        }
        else if (settingsButton.tap(x, y)) {
            settingsWindow.show();
        }
        else if (characterSelector.tap(x, y)) {}

        return true;
    }

}
