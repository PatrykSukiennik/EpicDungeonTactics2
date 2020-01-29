package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

public final class CharacterSelector {

    private CharacterCard[] characterCards;
    private SelectorArrow leftArrow, rightArrow;
    private int currectIndex = 0;
    private TextWithIcon goldStatus, statsButton;
    private ButtonWithText startButton, unlockButton;

    static final float iconSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.35f : Gdx.graphics.getWidth()/2f;
    static final float bigMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.05f : Gdx.graphics.getWidth() * 0.08f;
    static final float smallMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.03f;
    static final float wholeHeight =
                    iconSize
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_LOCKED), "0")
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0")
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0") * StatisticEnum.values().length * 1.2f
                    + MenuScreen.BOTTOM_BUTTON_HEIGHT
                    + bigMargin * 5
                    + smallMargin * (StatisticEnum.values().length + 1)
                    + iconSize/5f;
    public static final float bottomY = Gdx.graphics.getHeight()/2f - wholeHeight/2f;

    CharacterSelector() {

        characterCards = new CharacterCard[]{
                new CharacterCard(CharacterEnum.HERO_ELF),
                new CharacterCard(CharacterEnum.HERO_KNIGHT),
                new CharacterCard(CharacterEnum.HERO_WIZZARD),
                new CharacterCard(CharacterEnum.HERO_LIZARD),
                new CharacterCard(CharacterEnum.HERO_NINJA),
                new CharacterCard(CharacterEnum.HERO_PIRATE),
                new CharacterCard(CharacterEnum.HERO_BABY)
        };

        characterCards[currectIndex].justCenter();

        leftArrow = new SelectorArrow(DirectionEnum.LEFT);
        rightArrow = new SelectorArrow(DirectionEnum.RIGHT);

        startButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.SELECT));

        unlockButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.UNLOCK));

        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.left
        );

        statsButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.STATS_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STATS),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.right
        );
    }

    private void moveRight() {

        characterCards[currectIndex].moveToLeftBorder();
        currectIndex++;

        if (currectIndex >= characterCards.length) {
            currectIndex = 0;
        }

        characterCards[currectIndex].centerFromRight();
    }

    private void moveLeft() {

        characterCards[currectIndex].moveToRightBorder();
        currectIndex--;

        if (currectIndex < 0) {
            currectIndex = characterCards.length - 1;
        }

        characterCards[currectIndex].centerFromLeft();
    }

    void swiped(DirectionEnum directionEnum) {
        switch (directionEnum) {
            case RIGHT:
                moveLeft();
                break;
            case LEFT:
                moveRight();
                break;
        }
    }

    public void draw(SpriteBatch batch) {
        for (CharacterCard icon : characterCards) {
            icon.draw(batch, 1f);
        }

        leftArrow.act(Gdx.graphics.getDeltaTime());
        leftArrow.draw(batch, 1f);

        rightArrow.act(Gdx.graphics.getDeltaTime());
        rightArrow.draw(batch, 1f);

        goldStatus.draw(batch);
        statsButton.draw(batch);

        if (characterCards[currectIndex].isUnlocked) startButton.draw(batch, 1f);
        else if (characterCards[currectIndex].canBeUnlocked()) unlockButton.draw(batch, 1f);
    }

    public boolean tap(float x, float y) {
        if (leftArrow.tap(x, y)) {
            moveLeft();
            return true;
        } else if (rightArrow.tap(x, y)) {
            moveRight();
            return true;
        } else if (statsButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.STATS_SCREEN);
            return true;
        } else {
            if (characterCards[currectIndex].isUnlocked) {
                if (startButton.tap(x, y)) {
                    EpicDungeonTactics.setSelectedHero(characterCards[currectIndex].getCharacterEnum());
                    EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.PERK_SCREEN);
                    return true;
                }
            } else if (characterCards[currectIndex].canBeUnlocked()) {
                if (unlockButton.tap(x, y)) {
                    SavedInfoManager.unlock(characterCards[currectIndex].getCharacterEnum());
                    GlobalValues.minusGold(HeroStats.getBuyCost(characterCards[currectIndex].getCharacterEnum()));
                    CharacterEnum unlockedEnum = characterCards[currectIndex].getCharacterEnum();
                    characterCards[currectIndex] = new CharacterCard(unlockedEnum);
                    characterCards[currectIndex].moveToCenterNow();
                    return true;
                }

            }
            return false;
        }
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

}
