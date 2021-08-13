package com.appatstudio.epicdungeontactics2.screens.menu.bestiaryScreen.menuScreen;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.screens.viewElements.RelativePosMultilineText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.RelativePosText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.RelativePosTextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class BestiaryCard {

    private final CharacterEnum characterEnum;

    private BestiaryIcon icon;

    private RelativePosText title;



    boolean isUnlocked;


    private SpriteDrawable[] startingItems;
    private RelativePosTextWithIcon[] stats;
    private float[] itemsX;

    private static float itemsY;
    private static float iconY;
    private static float itemSize;
    private static float titleY;
    private static float descY;
    private static float[] statsY;
    private static float bonusY;

    private final float MOVE_TIME = 0.2f;

    static {
        float smallFontSize = FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0");

        itemSize = BestiarySelector.iconSize / 5f;
        bonusY = BestiarySelector.bottomY + BestiaryScreen.BOTTOM_BUTTON_HEIGHT + BestiarySelector.bigMargin + smallFontSize / 2f;
        statsY = new float[StatisticEnum.values().length + 4];
        for (int i = 0; i < statsY.length; i++) {
            statsY[i] = bonusY + BestiarySelector.bigMargin + (smallFontSize * 1.2f + BestiarySelector.smallMargin) * i;
        }
        itemsY = statsY[statsY.length - 1] + smallFontSize / 2f + BestiarySelector.bigMargin;
        iconY = itemsY + BestiarySelector.bigMargin * 3.4f;
        descY = iconY + BestiarySelector.iconSize + BestiarySelector.bigMargin * 0.8f + smallFontSize * 2.5f;
        titleY = itemsY + BestiarySelector.smallMargin * 6.5f;


    }

    BestiaryCard(CharacterEnum character) {

        this.icon = new BestiaryIcon(character);

        this.characterEnum = character;
        this.isUnlocked = SavedInfoManager.isEnemyKilled(characterEnum);

        icon.setSize(BestiarySelector.iconSize * 1.6f, BestiarySelector.iconSize * 1.6f);
        icon.setPosition(
                Gdx.graphics.getWidth() + icon.getWidth() * 1.5f,
                iconY);

        title = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_TITLE_UNLOCKED : FontEnum.MENU_HERO_TITLE_LOCKED),
                StringsManager.getCharacterName(characterEnum),
                Align.center
        );


        if (!isUnlocked) icon.getColor().a = 0.3f;

        StatisticEnum[] allStats = StatisticEnum.values();
        stats = new RelativePosTextWithIcon[allStats.length + 4];
        for (int i = 0; i < allStats.length; i++) {
            stats[i + 4] = new RelativePosTextWithIcon(
                    GraphicsManager.getStatIcon(allStats[i]),
                    isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                    " " + allStats[i].toString() + ": " + CharacterStats.getPrototype(characterEnum).getStat(allStats[i]) + SavedInfoManager.getMaxCharacterLvl() * CharacterStats.getEnemyLvlUpStats(characterEnum).getStat(allStats[i]),
                    Align.left);
        }
        stats[3] = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.POINTS_PER_LVL_ICON),
                isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                " EXP: " + CharacterStats.getCharacterBasicExpReward(characterEnum) + SavedInfoManager.getMaxCharacterLvl() * CharacterStats.getCharacterLvlExpReward(characterEnum),
                Align.left);
        stats[2] = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.POINTS_PER_LVL_ICON),
                isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                " GLD: " + CharacterStats.getCharacterBasicGoldReward(characterEnum) + SavedInfoManager.getMaxCharacterLvl() * CharacterStats.getCharacterLvlGoldReward(characterEnum),
                Align.left);

        stats[1] = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.POINTS_PER_LVL_ICON),
                isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                " RNG: " + CharacterStats.getRange(characterEnum),
                Align.left);
        stats[0] = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.POINTS_PER_LVL_ICON),
                isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                " SPD: " + CharacterStats.getRange(characterEnum),
                Align.left);

    }

    CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    public void draw(Batch batch, float parentAlpha) {
        if (isUnlocked) {
            batch.getColor().a = 1f;
            for (int i = 0; i < stats.length; i++) {
                //System.out.println(i);
                stats[i].draw(batch, icon.getX() + icon.getWidth() * 0.35f, statsY[i]);
            }
        }
        else {
            batch.getColor().a = 0.6f;
        }

        icon.act(Gdx.graphics.getDeltaTime());
        icon.draw(batch, parentAlpha, isUnlocked);

        if (isUnlocked)
            icon.draw(batch, parentAlpha);

        title.draw(batch,
                icon.getX() + icon.getWidth() / 2f,
                titleY);



    }

    void moveToCenterNow() {
        icon.setPosition(Gdx.graphics.getWidth() / 2f - icon.getWidth() / 2f, icon.getY());
    }

    void moveToRightBorder() {
        icon.addAction(
                Actions.moveTo(Gdx.graphics.getWidth() + icon.getWidth() * 1.5f,
                        icon.getY(),
                        MOVE_TIME));
    }

    void moveToLeftBorder() {
        icon.addAction(
                Actions.moveTo(-icon.getWidth() - icon.getWidth() * 1.5f,
                        icon.getY(),
                        MOVE_TIME));
    }


    void centerFromLeft() {
        icon.addAction(
                Actions.sequence(
                        Actions.moveTo(-icon.getWidth() - icon.getWidth(), icon.getY()),
                        Actions.moveTo(Gdx.graphics.getWidth() / 2f - icon.getWidth() / 2f,
                                icon.getY(),
                                MOVE_TIME)));
    }

    void centerFromRight() {
        icon.addAction(
                Actions.sequence(
                        Actions.moveTo(Gdx.graphics.getWidth() + icon.getWidth()
                                , icon.getY()),
                        Actions.moveTo(Gdx.graphics.getWidth() / 2f - icon.getWidth() / 2f,
                                icon.getY(),
                                MOVE_TIME)));
    }

    void justCenter() {
        icon.setPosition(Gdx.graphics.getWidth() / 2f - icon.getWidth() / 2f,
                icon.getY());
    }

    void refreshIsUnlocked() {
        this.isUnlocked = SavedInfoManager.isEnemyKilled(characterEnum);
    }

    public static float getIconY() {
        return iconY;
    }


}
