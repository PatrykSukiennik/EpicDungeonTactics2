package com.appatstudio.epicdungeontactics2.screens.menu.bestiaryScreen.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.screens.menu.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class BestiaryScreen {

    private SpriteBatch batch;
    private BestiarySelector bestiarySelector;
    private Image campUpgradeButton, financesUpgradeButton, bestiaryButton;
    private MultiLineText title;

    public static final float BOTTOM_BUTTON_HEIGHT, BOTTOM_BUTTON_WIDTH;

    static {
        BOTTOM_BUTTON_WIDTH = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.4f : Gdx.graphics.getWidth() * 0.5f;
        BOTTOM_BUTTON_HEIGHT = BOTTOM_BUTTON_WIDTH/3f;
    }

    public BestiaryScreen() {
        batch = new SpriteBatch();
        batch.enableBlending();
        float sideButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;

        title = new MultiLineText(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.BESTIARY),
                Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight() * 0.92f,
                Align.center);

        bestiarySelector = new BestiarySelector();

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
        bestiaryButton.getColor().a = 0.4f;
    }

    public void draw() {
        MenuBgContainer.draw(batch);
        //MenuBgContainer.drawAlpha50(batch);

        batch.begin();
        title.draw(batch);
        bestiarySelector.draw(batch);
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
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }

        bestiarySelector.tap(x, y);

        return true;
    }

    public void swiped(DirectionEnum directionEnum) {
        bestiarySelector.swiped(directionEnum);
    }

    public void updateGold() {
        bestiarySelector.updateGold();
    }

    public void refreshIsUnlocked() {
        bestiarySelector.refreshIsUnlocked();
    }

}
