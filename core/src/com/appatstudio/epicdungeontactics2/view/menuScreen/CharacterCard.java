package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosText;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosTextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class CharacterCard {

    private final CharacterEnum characterEnum;

    private CharacterIcon icon;

    private RelativePosText title, description, bonus, unlockStage;
    private RelativePosTextWithIcon cost;

    private LvlExpBar lvlExpBar;

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

        itemSize = CharacterSelector.iconSize / 5f;
        bonusY = CharacterSelector.bottomY + MenuScreen.BOTTOM_BUTTON_HEIGHT + CharacterSelector.bigMargin + smallFontSize / 2f;
        statsY = new float[StatisticEnum.values().length + 1];
        for (int i = 0; i < statsY.length; i++) {
            statsY[i] = bonusY + CharacterSelector.bigMargin + (smallFontSize * 1.2f + CharacterSelector.smallMargin) * i;
        }
        itemsY = statsY[statsY.length - 1] + smallFontSize / 2f + CharacterSelector.bigMargin;
        iconY = itemsY + itemSize + CharacterSelector.bigMargin;
        descY = iconY + CharacterSelector.iconSize + CharacterSelector.bigMargin + smallFontSize / 2f;
        titleY = descY + smallFontSize * 2;


    }

    CharacterCard(CharacterEnum character) {

        this.icon = new CharacterIcon(character);

        this.characterEnum = character;
        this.isUnlocked = SavedInfoManager.isUnlocked(characterEnum);

        icon.setSize(CharacterSelector.iconSize, CharacterSelector.iconSize);
        icon.setPosition(
                Gdx.graphics.getWidth(),
                iconY);

        title = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_TITLE_UNLOCKED : FontEnum.MENU_HERO_TITLE_LOCKED),
                StringsManager.getCharacterName(characterEnum),
                Align.center
        );

        description = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED : FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getCharacterDescription(characterEnum),
                Align.center
        );

        bonus = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED : FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getCharacterBonus(characterEnum),
                Align.center
        );

        if (!isUnlocked) icon.getColor().a = 0.3f;

        ItemEnum[] items = HeroStats.getStartingItems(characterEnum);
        startingItems = new SpriteDrawable[items.length];
        itemsX = new float[items.length];
        for (int i = 0; i < items.length; i++) {
            startingItems[i] = GraphicsManager.getItemImage(items[i]);
            itemsX[i] = Gdx.graphics.getWidth() / 2f - (itemSize / 2f * startingItems.length * 1.2f) + i * itemSize * 1.2f;
        }


        StatisticEnum[] allStats = StatisticEnum.values();
        stats = new RelativePosTextWithIcon[allStats.length + 1];
        for (int i = 0; i < allStats.length; i++) {
            stats[i] = new RelativePosTextWithIcon(
                    GraphicsManager.getStatIcon(allStats[i]),
                    isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                    allStats[i].toString() + ": " + SavedInfoManager.getCharacterStat(characterEnum, allStats[i]),
                    Align.left);
        }
        stats[stats.length - 1] = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.POINTS_PER_LVL_ICON),
                isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                "PTS: " + HeroStats.getPointsPerLvl(characterEnum),
                Align.left);


        if (!isUnlocked) {
            unlockStage = new RelativePosText(
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    StringsManager.getGuiString(GuiStringEnum.AVAILABLE_AT_STAGE) + ": " + HeroStats.getRequiredStageToUnlock(characterEnum),
                    Align.center);

            cost = new RelativePosTextWithIcon(
                    GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    StringsManager.getGuiString(GuiStringEnum.COST) + ": " + HeroStats.getBuyCost(characterEnum),
                    Align.center
            );
        }

        lvlExpBar = new LvlExpBar(
                SavedInfoManager.getCharacterExp(character),
                HeroStats.getExpCap(SavedInfoManager.getCharacterLvl(character)),
                SavedInfoManager.getCharacterLvl(character)
        );
    }

    CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    public void draw(Batch batch, float parentAlpha) {

        icon.act(Gdx.graphics.getDeltaTime());
        icon.draw(batch, parentAlpha, isUnlocked);

        if (isUnlocked)
            lvlExpBar.draw(batch, icon.getX() + icon.getWidth() * 1.1f, icon.getY() + icon.getHeight() * 0.2f);

        icon.draw(batch, parentAlpha);

        title.draw(batch,
                icon.getX() + icon.getWidth() / 2f,
                titleY);
        description.draw(batch,
                icon.getX() + icon.getWidth() / 2f,
                descY);
        bonus.draw(batch,
                icon.getX() + icon.getWidth() / 2f,
                bonusY);

        for (int i = 0; i < startingItems.length; i++) {
            startingItems[i].draw(batch, icon.getX() - Gdx.graphics.getWidth() / 2f + icon.getWidth() / 2f + itemsX[i], itemsY, itemSize, itemSize);
        }

        for (int i = 0; i < stats.length; i++) {
            stats[i].draw(batch, icon.getX() + icon.getWidth() * 0.35f, statsY[i]);
        }

        if (!isUnlocked) {
            batch.getColor().a = 0.8f;
            cost.draw(batch, icon.getX() + icon.getWidth() / 2f, icon.getY() + icon.getHeight() * 0.65f);
            if (SavedInfoManager.getPlayerStat(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE) < HeroStats.getRequiredStageToUnlock(characterEnum))
                unlockStage.draw(batch, icon.getX() + icon.getWidth() / 2f, icon.getY() + icon.getHeight() * 0.45f);
        }

    }

    boolean canBeUnlocked() {
        return GlobalValues.getGold() >= HeroStats.getBuyCost(characterEnum) && SavedInfoManager.getPlayerStat(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE) >= HeroStats.getRequiredStageToUnlock(characterEnum);
    }

    void moveToCenterNow() {
        icon.setPosition(Gdx.graphics.getWidth() / 2f - icon.getWidth() / 2f, icon.getY());
    }

    void moveToRightBorder() {
        icon.addAction(
                Actions.moveTo(Gdx.graphics.getWidth() + icon.getWidth(),
                        icon.getY(),
                        MOVE_TIME));
    }

    void moveToLeftBorder() {
        icon.addAction(
                Actions.moveTo(-icon.getWidth() - icon.getWidth(),
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
}
