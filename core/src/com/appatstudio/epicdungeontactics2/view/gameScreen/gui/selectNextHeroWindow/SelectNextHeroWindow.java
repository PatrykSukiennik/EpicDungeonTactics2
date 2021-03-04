package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.selectNextHeroWindow;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.view.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.view.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import java.util.HashMap;

public final class SelectNextHeroWindow {

    private static ButtonWithText justEndButton;
    private static ButtonWithText selectButton;
    private static MultiLineText title;

    private static boolean isUp = false;

    private static CharacterEnum selectedEnum = null;
    private final static HashMap<CharacterEnum, NewHeroLine> newHeroLines = new HashMap<>();

    public SelectNextHeroWindow() {

        title = new MultiLineText(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.CHOOSE_NEXT_HERO),
                Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight() * 0.92f,
                Align.center);

        justEndButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.NONE),
                Gdx.graphics.getWidth() / 2f - MenuScreen.BOTTOM_BUTTON_WIDTH / 2f,
                CharacterSelector.bottomY - MenuScreen.BOTTOM_BUTTON_HEIGHT,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.JUST_FINISH_GAME));

        selectButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth() / 2f - MenuScreen.BOTTOM_BUTTON_WIDTH / 2f,
                CharacterSelector.bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.SELECT));

        float iconsYModif = Gdx.graphics.getHeight() * 0.92f - (title.getHeight() * 2) - newHeroLines.size() * NewHeroLine.getIconHeight();

        CharacterEnum[] allCharacters = SavedInfoManager.getAllUnlockedCharacters();
        for (int i = 0; i < allCharacters.length; i++) {
            newHeroLines.put(allCharacters[i],
                    new NewHeroLine(allCharacters[allCharacters.length - 1 - i],
                            iconsYModif + (NewHeroLine.getIconHeight() * i)));
        }

    }

    public void draw(Batch batch) {
        justEndButton.draw(batch, 1f);

        if (selectedEnum != null)
            selectButton.draw(batch, 1f);

        for (NewHeroLine h : newHeroLines.values())
            h.draw(batch, 1f, selectedEnum == h.getCharacterEnum());

        title.draw(batch);
    }

    public CharacterEnum tap(float x, float y) {
        if (justEndButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
            return null;
        }

        for (NewHeroLine h : newHeroLines.values()) {
            if (h.tap(x, y) && h.isAvailable()) {
                if (selectedEnum == h.getCharacterEnum()) {
                    selectedEnum = null;
                    return null;
                } else {
                    selectedEnum = h.getCharacterEnum();
                    return null;
                }
            }
        }

        if (selectButton.tap(x, y) && selectedEnum != null) {
            return selectedEnum;
        }

        else {
            selectedEnum = null;
            return null;
        }
    }

    public void show() {
        isUp = true;
    }

    public void hide() {
        isUp = false;
        selectedEnum = null;
    }

    public boolean isUp() {
        return isUp;
    }
}