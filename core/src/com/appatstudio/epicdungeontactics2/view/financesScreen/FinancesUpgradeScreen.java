package com.appatstudio.epicdungeontactics2.view.financesScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class FinancesUpgradeScreen {

    private SpriteBatch batch;
    private TextWithIcon backButton, goldStatus;

    private Image campUpgradeButton, financesUpgradeButton;

    public FinancesUpgradeScreen() {

        batch = new SpriteBatch();
        batch.enableBlending();

        backButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.BACK_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.BACK),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0"),
                Align.right
        );
        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.left
        );

        float campUpgradeButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;

        campUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.CAMP_UPGRADE_BUTTON));
        campUpgradeButton.setSize(campUpgradeButtonSize, campUpgradeButtonSize);
        campUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f);
        campUpgradeButton.getColor().a = 0.8f;

        financesUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.FINANCES_UPGRADE_BUTTON));
        financesUpgradeButton.setSize(campUpgradeButtonSize, campUpgradeButtonSize);
        financesUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f + campUpgradeButtonSize);
        financesUpgradeButton.getColor().a = 0.4f;

    }

    public void draw() {
        MenuBgContainer.drawOnlyBg(batch);

        batch.begin();
        batch.getColor().a = 1f;

        campUpgradeButton.draw(batch, 1f);
        financesUpgradeButton.draw(batch, 1f);

        backButton.draw(batch);
        goldStatus.draw(batch);

        batch.end();
    }

    public boolean tap(float x, float y) {
        if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }

        if (x < campUpgradeButton.getWidth() &&
                y > campUpgradeButton.getY() &&
                y < campUpgradeButton.getY() + campUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.CAMP_UPGRADE_SCREEN);
        }
        else if (x < financesUpgradeButton.getWidth() &&
                y > financesUpgradeButton.getY() &&
                y < financesUpgradeButton.getY() + financesUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }


        return false;
    }

}
