package com.appatstudio.epicdungeontactics2.screens.menu.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.screens.menu.MenuBgContainer;
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
    private Image campUpgradeButton, financesUpgradeButton, bestiaryButton;

    public static final float BOTTOM_BUTTON_HEIGHT, BOTTOM_BUTTON_WIDTH;

    static {
        BOTTOM_BUTTON_WIDTH = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.4f : Gdx.graphics.getWidth() * 0.5f;
        BOTTOM_BUTTON_HEIGHT = BOTTOM_BUTTON_WIDTH/3f;
    }

    public MenuScreen() {
        batch = new SpriteBatch();
        batch.enableBlending();
        float sideButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;

        characterSelector = new CharacterSelector();

        campUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.CAMP_UPGRADE_BUTTON));
        campUpgradeButton.setSize(sideButtonSize, sideButtonSize);
        campUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f);
        campUpgradeButton.getColor().a = 0.8f;

        financesUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.FINANCES_UPGRADE_BUTTON));
        financesUpgradeButton.setSize(sideButtonSize, sideButtonSize);
        financesUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f + sideButtonSize);
        financesUpgradeButton.getColor().a = 0.8f;

        bestiaryButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.BESTIARY_BUTTON));
        bestiaryButton.setSize(sideButtonSize, sideButtonSize);
        bestiaryButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f + sideButtonSize * 2);
        bestiaryButton.getColor().a = 0.8f;
    }

    public void draw() {
        MenuBgContainer.draw(batch);
        MenuBgContainer.drawAlpha50(batch);

        batch.begin();
        characterSelector.draw(batch);
        campUpgradeButton.draw(batch, 1f);
        financesUpgradeButton.draw(batch, 1f);
        bestiaryButton.draw(batch, 1f);
        batch.end();
    }

    public boolean tap(float x, float y) {
        if (x < campUpgradeButton.getWidth() &&
            y > campUpgradeButton.getY() &&
            y < campUpgradeButton.getY() + campUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.CAMP_UPGRADE_SCREEN);
        }
        else if (x < financesUpgradeButton.getWidth() &&
                y > financesUpgradeButton.getY() &&
                y < financesUpgradeButton.getY() + financesUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.FINANCES_UPGRADE_SCREEN);
        }
        else if (x < bestiaryButton.getWidth() &&
                y > bestiaryButton.getY() &&
                y < bestiaryButton.getY() + bestiaryButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.BESTIARY_SCREEN);
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
