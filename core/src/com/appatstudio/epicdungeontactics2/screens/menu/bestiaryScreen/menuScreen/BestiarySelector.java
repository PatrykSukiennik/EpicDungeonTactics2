package com.appatstudio.epicdungeontactics2.screens.menu.bestiaryScreen.menuScreen;

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
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public final class BestiarySelector {

    private BestiaryCard[] bestiaryCards;
    private SelectorArrow leftArrow, rightArrow;
    private int currectIndex = 0;
    private TextWithIcon goldStatus, backButton;
    private ButtonWithText startButton, unlockButton;

    public static final float iconSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.3f : Gdx.graphics.getWidth()/2.5f;
    public static final float bigMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.05f : Gdx.graphics.getWidth() * 0.08f;
    public static final float smallMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.03f;
    static final float wholeHeight =
                    iconSize
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_LOCKED), "0")
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0")
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0") * StatisticEnum.values().length * 1.2f
                    + BestiaryScreen.BOTTOM_BUTTON_HEIGHT
                    + bigMargin * 5
                    + smallMargin * (StatisticEnum.values().length + 1)
                    + iconSize/5f;
    public static final float bottomY = Gdx.graphics.getHeight()/2f - wholeHeight/1.5f;

    BestiarySelector() {

        bestiaryCards = new BestiaryCard[]{
                new BestiaryCard(CharacterEnum.CENTAUR_MALE),
                new BestiaryCard(CharacterEnum.CENTAUR_FEMALE),
                new BestiaryCard(CharacterEnum.ENT),
                new BestiaryCard(CharacterEnum.TROLL),
                new BestiaryCard(CharacterEnum.WOLF),
                new BestiaryCard(CharacterEnum.MUSHROOM_SMALL),
                new BestiaryCard(CharacterEnum.MUSHROOM_NORMAL),
                new BestiaryCard(CharacterEnum.MUSHROOM_LARGE),
                new BestiaryCard(CharacterEnum.BEAR),
                new BestiaryCard(CharacterEnum.BOSS_FOREST_GUARDIAN),
                new BestiaryCard(CharacterEnum.ELVEN_PRINCESS),
                new BestiaryCard(CharacterEnum.ELVEN_KING),
                new BestiaryCard(CharacterEnum.ELVEN_KNIGHT),
                new BestiaryCard(CharacterEnum.GNOLL_BRUTE),
                new BestiaryCard(CharacterEnum.GNOLL_OVERSEER),
                new BestiaryCard(CharacterEnum.GNOLL_SCOUT),
                new BestiaryCard(CharacterEnum.GNOLL_SHAMAN),
                new BestiaryCard(CharacterEnum.TINY_ZOMBIE),
                new BestiaryCard(CharacterEnum.ZOMBIE),
                new BestiaryCard(CharacterEnum.MUDDY),
                new BestiaryCard(CharacterEnum.ICE_ZOMBIE),
                new BestiaryCard(CharacterEnum.GOBLIN),
                new BestiaryCard(CharacterEnum.BIG_ZOMBIE),
                new BestiaryCard(CharacterEnum.ORC_SHAMAN),
                new BestiaryCard(CharacterEnum.MASKED_ORC),
                new BestiaryCard(CharacterEnum.ORC_WARRIOR),
                new BestiaryCard(CharacterEnum.SWAMPY),
                new BestiaryCard(CharacterEnum.BOSS_OGRE),
                new BestiaryCard(CharacterEnum.CHORT),
                new BestiaryCard(CharacterEnum.IMP),
                new BestiaryCard(CharacterEnum.NECROMANCER),
                new BestiaryCard(CharacterEnum.SKELET),
                new BestiaryCard(CharacterEnum.WOGOL),
                new BestiaryCard(CharacterEnum.BOSS_BIG_DEMON)
        };

        bestiaryCards[currectIndex].justCenter();

        leftArrow = new SelectorArrow(DirectionEnum.LEFT);
        rightArrow = new SelectorArrow(DirectionEnum.RIGHT);

        startButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - BestiaryScreen.BOTTOM_BUTTON_WIDTH/2f,
                bottomY,
                BestiaryScreen.BOTTOM_BUTTON_WIDTH,
                BestiaryScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.SELECT));

        unlockButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - BestiaryScreen.BOTTOM_BUTTON_WIDTH/2f,
                bottomY,
                BestiaryScreen.BOTTOM_BUTTON_WIDTH,
                BestiaryScreen.BOTTOM_BUTTON_HEIGHT,
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

        backButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.BACK_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.BACK),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0"),
                Align.right
        );
    }

    private void moveRight() {

        bestiaryCards[currectIndex].moveToLeftBorder();
        currectIndex++;

        if (currectIndex >= bestiaryCards.length) {
            currectIndex = 0;
        }

        bestiaryCards[currectIndex].centerFromRight();
    }

    private void moveLeft() {

        bestiaryCards[currectIndex].moveToRightBorder();
        currectIndex--;

        if (currectIndex < 0) {
            currectIndex = bestiaryCards.length - 1;
        }

        bestiaryCards[currectIndex].centerFromLeft();
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
        for (int i=0; i<bestiaryCards.length; i++) {
            if (Math.abs( currectIndex - i ) < 3 || (currectIndex > bestiaryCards.length - 3 && i < 2) || (currectIndex < 2 && i > bestiaryCards.length - 3)) bestiaryCards[i].draw(batch, 1f);
        }


        leftArrow.act(Gdx.graphics.getDeltaTime());
        leftArrow.draw(batch, 1f);

        rightArrow.act(Gdx.graphics.getDeltaTime());
        rightArrow.draw(batch, 1f);

        goldStatus.draw(batch);
        backButton.draw(batch);

    }

    public boolean tap(float x, float y) {
        if (leftArrow.tap(x, y)) {
            moveLeft();
            return true;
        } else if (rightArrow.tap(x, y)) {
            moveRight();
            return true;
        } else if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
            return true;
        } else {
            return false;
        }
    }

    void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

    void refreshIsUnlocked() {
        for (BestiaryCard b : bestiaryCards) {
            b.refreshIsUnlocked();
        }
    }

}
