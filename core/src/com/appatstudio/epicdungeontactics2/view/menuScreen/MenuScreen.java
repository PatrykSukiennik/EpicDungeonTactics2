package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.view.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class MenuScreen {

    private SpriteBatch batch;
    private CharacterSelector characterSelector;
    private Image campUpgradeButton;

    public static final float BOTTOM_BUTTON_HEIGHT, BOTTOM_BUTTON_WIDTH;

    static {
        BOTTOM_BUTTON_WIDTH = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.4f : Gdx.graphics.getWidth() * 0.5f;
        BOTTOM_BUTTON_HEIGHT = BOTTOM_BUTTON_WIDTH/3f;
    }

    public MenuScreen() {
        batch = new SpriteBatch();
        batch.enableBlending();

        characterSelector = new CharacterSelector();

        campUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.CAMP_UPGRADE_BUTTON));
        float campUpgradeButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;
        campUpgradeButton.setSize(campUpgradeButtonSize, campUpgradeButtonSize);
        campUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f);
    }

    public void draw() {
        MenuBgContainer.draw(batch);

        batch.begin();
        characterSelector.draw(batch);
        campUpgradeButton.draw(batch, 1f);
        batch.end();
    }

    public boolean tap(float x, float y) {
        if (x < campUpgradeButton.getWidth() &&
            y > campUpgradeButton.getY() &&
            y < campUpgradeButton.getY() + campUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.CAMP_UPGRADE_SCREEN);
        }

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
