package com.appatstudio.epicdungeontactics2.screens.menu.financesScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.FinancesStats;
import com.appatstudio.epicdungeontactics2.screens.menu.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import java.util.HashMap;

public final class FinancesUpgradeScreen {

    private static SpriteBatch batch;
    private static TextWithIcon backButton, goldStatus;
    private static ButtonWithText investButton;
    private static MultiLineText title;

    private static FinanceUpgradeEnum selectedEnum = null;

    private final Image campUpgradeButton, financesUpgradeButton;
    private final static HashMap<FinanceUpgradeEnum, FinanceUpgradeIcon> financeUpgradeIcons = new HashMap<>();

    public FinancesUpgradeScreen() {

        batch = new SpriteBatch();
        batch.enableBlending();


        title = new MultiLineText(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.YOUR_INVESTMENTS),
                Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight() * 0.92f,
                Align.center);
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
        investButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth() / 2f - MenuScreen.BOTTOM_BUTTON_WIDTH / 2f,
                CharacterSelector.bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.INVEST));

        float campUpgradeButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth() * 0.15f;

        campUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.CAMP_UPGRADE_BUTTON));
        campUpgradeButton.setSize(campUpgradeButtonSize, campUpgradeButtonSize);
        campUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f);
        campUpgradeButton.getColor().a = 0.8f;

        financesUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.FINANCES_UPGRADE_BUTTON));
        financesUpgradeButton.setSize(campUpgradeButtonSize, campUpgradeButtonSize);
        financesUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f + campUpgradeButtonSize);
        financesUpgradeButton.getColor().a = 0.4f;

        float iconsYModif = Gdx.graphics.getHeight() * 0.92f - (title.getHeight() * 2) - FinanceUpgradeEnum.values().length * FinanceUpgradeIcon.getIconHeight() * 1.2f;

        FinanceUpgradeEnum[] allFinances = FinanceUpgradeEnum.values();
        for (int i = 0; i < allFinances.length; i++) {
            financeUpgradeIcons.put(allFinances[i],
                    new FinanceUpgradeIcon(FinanceUpgradeEnum.values()[i],
                            iconsYModif + (FinanceUpgradeIcon.getIconHeight() * i * 1.2f)));
        }

    }

    public void draw() {
        MenuBgContainer.drawOnlyBg(batch);
        MenuBgContainer.drawAlpha50(batch);

        batch.begin();
        batch.getColor().a = 1f;

        goldStatus.draw(batch);
        backButton.draw(batch);

        campUpgradeButton.draw(batch, 1f);
        financesUpgradeButton.draw(batch, 1f);

        if (selectedEnum != null && GlobalValues.getGold() >= FinancesStats.getCost(selectedEnum))
            investButton.draw(batch, 1f);

        for (FinanceUpgradeIcon f : financeUpgradeIcons.values())
            f.draw(batch, 1f, selectedEnum == f.getFinanceEnum());

        title.draw(batch);
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
        } else if (x < financesUpgradeButton.getWidth() &&
                y > financesUpgradeButton.getY() &&
                y < financesUpgradeButton.getY() + financesUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        } else if (selectedEnum != null && GlobalValues.getGold() >= financeUpgradeIcons.get(selectedEnum).getUpgradeCost() && investButton.tap(x, y)) {
            SavedInfoManager.saveFinancesLvl(
                    selectedEnum,
                    SavedInfoManager.getFinancesLvl(selectedEnum) + 1);

            GlobalValues.minusGold(financeUpgradeIcons.get(selectedEnum).getUpgradeCost());
            updateFinances();
            updateGold();

            if (GlobalValues.getGold() < financeUpgradeIcons.get(selectedEnum).getUpgradeCost()) selectedEnum = null;

        } else {
            for (FinanceUpgradeIcon f : financeUpgradeIcons.values()) {
                if (f.tap(x, y)) {
                    if (selectedEnum == f.getFinanceEnum()) {
                        selectedEnum = null;
                        break;
                    }
                    else if (GlobalValues.getGold() >= f.getUpgradeCost()) {
                        selectedEnum = f.getFinanceEnum();
                        break;
                    }
                }
            }
        }


        return false;
    }

    static void updateFinances() {
        float iconsYModif = Gdx.graphics.getHeight() * 0.92f - (title.getHeight() * 2) - FinanceUpgradeEnum.values().length * FinanceUpgradeIcon.getIconHeight() * 1.2f;

        FinanceUpgradeEnum[] allFinances = FinanceUpgradeEnum.values();
        for (int i = 0; i < allFinances.length; i++) {
            financeUpgradeIcons.put(allFinances[i],
                    new FinanceUpgradeIcon(FinanceUpgradeEnum.values()[i],
                            iconsYModif + (FinanceUpgradeIcon.getIconHeight() * i * 1.2f)));
        }

    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

}
