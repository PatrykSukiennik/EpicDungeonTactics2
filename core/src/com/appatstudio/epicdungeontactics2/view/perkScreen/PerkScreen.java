package com.appatstudio.epicdungeontactics2.view.perkScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public final class PerkScreen {

    private SpriteBatch batch;
    private PerkIcon[] perks;
    private PerkIcon selectedPerk;
    private ButtonWithText startButton, rerollButton;
    private MultiLineText title;
    private TextWithIcon backButton;

    private boolean wasRerollUsed;

    public PerkScreen() {
        batch = new SpriteBatch();

        wasRerollUsed = false;

        startButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.25f,
                Gdx.graphics.getWidth() * 0.34f,
                Gdx.graphics.getWidth() * 0.5f,
                Gdx.graphics.getWidth() * 0.15f,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.FIGHT));
        rerollButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.25f,
                Gdx.graphics.getWidth() * 0.34f + Gdx.graphics.getWidth() * 0.15f,
                Gdx.graphics.getWidth() * 0.5f,
                Gdx.graphics.getWidth() * 0.15f,
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
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f - FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0"),
                Align.left
        );

        PerkEnum[] perkEnums = PerkRandomizer.getRandomPerks();
        perks = new PerkIcon[perkEnums.length];
        for (int i = 0; i < perkEnums.length; i++) {
            perks[i] = new PerkIcon(perkEnums[i], Gdx.graphics.getWidth() * 0.32f + Gdx.graphics.getWidth() * 0.39f + PerkIcon.getIconHeight() * 1.4f * i);
            perks[i].getColor().a = 0.3f;
        }
    }

    public void draw() {
        batch.begin();
        title.draw(batch);
        backButton.draw(batch);

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
            perks[i] = new PerkIcon(perkEnums[i], Gdx.graphics.getWidth() * 0.32f + Gdx.graphics.getWidth() * 0.39f + PerkIcon.getIconHeight() * 1.4f * i);
            perks[i].getColor().a = 0.3f;
        }
    }
}
