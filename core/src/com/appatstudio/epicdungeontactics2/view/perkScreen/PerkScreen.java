package com.appatstudio.epicdungeontactics2.view.perkScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.view.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.view.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public final class PerkScreen {

    private SpriteBatch batch;
    private static PerkIcon[] perks;
    private PerkIcon selectedPerk;
    private ButtonWithText startButton, rerollButton;
    private MultiLineText title;
    private TextWithIcon backButton, goldStatus;

    private static float perkStartY;

    private boolean wasRerollUsed;
    private static float perkHeightModif = EpicDungeonTactics.isTablet() ? PerkIcon.getIconHeight() * 1.3f : PerkIcon.getIconHeight() * 1.5f;

    public PerkScreen() {
        batch = new SpriteBatch();
        batch.enableBlending();

        wasRerollUsed = false;

        startButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                CharacterSelector.bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.FIGHT));
        rerollButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                startButton.getY() + startButton.getHeight(),
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.REROLL));
        title = new MultiLineText(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.SELECT_PERK),
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

        perkStartY = EpicDungeonTactics.isTablet() ? rerollButton.getY() + rerollButton.getHeight() * 1.5f : rerollButton.getY() + rerollButton.getHeight() * 2.2f;

        PerkEnum[] perkEnums = PerkRandomizer.getRandomPerks();
        perks = new PerkIcon[perkEnums.length];
        for (int i = 0; i < perkEnums.length; i++) {
            perks[i] = new PerkIcon(perkEnums[i],  perkStartY + perkHeightModif * i);
            perks[i].getColor().a = 0.3f;
        }
    }

    static void updatePerks() {
        for (int i=0; i<perks.length; i++) {
            perks[i] = new PerkIcon(perks[i].getPerkEnum(), perkStartY + perkHeightModif * i);
            perks[i].getColor().a = 0.3f;
        }



    }

    public void draw() {
        MenuBgContainer.draw(batch);

        batch.begin();
        title.draw(batch);
        backButton.draw(batch);
        goldStatus.draw(batch);

        if (selectedPerk != null) startButton.draw(batch, 1f);
        if (!wasRerollUsed && EpicDungeonTactics.isInternetOn()) rerollButton.draw(batch, 1f);

        for (PerkIcon p : perks) {
            p.draw(batch, 1f);
        }

        batch.getColor().a = 1f;
        batch.end();
    }

    public void tap(float x, float y) {
        if (EpicDungeonTactics.isInternetOn() && !wasRerollUsed && rerollButton.tap(x, y)) {
            EpicDungeonTactics.generateInterstitialAd();
            wasRerollUsed = true;
            selectedPerk = null;
            reroll();
        } else if (selectedPerk != null && startButton.tap(x, y)) {
            EpicDungeonTactics.setSelectedPerk(selectedPerk.getPerkEnum());
            EpicDungeonTactics.startGame();
        } else if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        } else {
            for (PerkIcon perkIcon : perks) {
                if (perkIcon.tap(x, y)) {
                    if (selectedPerk == perkIcon) {
                        perkIcon.getColor().a = 0.3f;
                        selectedPerk = null;
                    } else {
                        if (selectedPerk != null) {
                            selectedPerk.getColor().a = 0.3f;
                        }
                        selectedPerk = perkIcon;
                        perkIcon.getColor().a = 1f;
                    }
                }
            }
        }
    }

    private void reroll() {
        PerkEnum[] oldPerks = new PerkEnum[perks.length];
        for (int i = 0; i < perks.length; i++) {
            oldPerks[i] = perks[i].getPerkEnum();
        }
        PerkEnum[] perkEnums = PerkRandomizer.getRandomPerks(oldPerks);
        perks = new PerkIcon[perkEnums.length];
        for (int i = 0; i < perkEnums.length; i++) {
            perks[i] = new PerkIcon(perkEnums[i], perkStartY + perkHeightModif * i);
            perks[i].getColor().a = 0.3f;
        }
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }
}
