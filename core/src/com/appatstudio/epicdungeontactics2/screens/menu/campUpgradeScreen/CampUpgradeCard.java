package com.appatstudio.epicdungeontactics2.screens.menu.campUpgradeScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.CampUpgradeStats;
import com.appatstudio.epicdungeontactics2.global.stats.PlayerStatisticsStats;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.CharacterCard;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import lombok.Getter;

public final class CampUpgradeCard {

    private static SpriteDrawable blackAlpha = GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent);

    private CampUpgradeEnum upgradeEnum;

    private static float stateTime = 0;
    private Animation<SpriteDrawable> animation;

    @Getter private int lvl;
    private int upgradeCost;
    private int unlockCap;

    private static float iconX = Gdx.graphics.getWidth()/2f - (CharacterSelector.iconSize * 1.6f)/2f;
    private static float iconY = CharacterCard.getIconY();
    private static float iconSize = CharacterSelector.iconSize * 1.6f;

    private TextObject title, unlockStage;
    private MultiLineText unlockedDesc, mainDesc;
    private TextWithIcon cost;

    private final static float descY = iconY + CharacterSelector.iconSize + CharacterSelector.bigMargin + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") / 2f;
    private static float titleY = descY + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 2;

    CampUpgradeCard(CampUpgradeEnum upgradeEnum) {
        this.upgradeEnum = upgradeEnum;

        lvl = SavedInfoManager.getNpcLvl(upgradeEnum);

        if (lvl <= 2) upgradeCost = CampUpgradeStats.getCampUpgradeCost(upgradeEnum, lvl);

        animation = GraphicsManager.getCampUpgradeFellow(upgradeEnum, CharacterStateEnum.IDLE);

        if (lvl == 0) title = new TextObject(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_LOCKED),
                StringsManager.getCampUpgradeName(upgradeEnum),
                Gdx.graphics.getWidth()/2f,
                titleY,
                Align.center);

        else title = new TextObject(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
               StringsManager.getCampUpgradeName(upgradeEnum) + " lvl." + lvl,
                Gdx.graphics.getWidth()/2f,
                titleY,
                Align.center);

        if (lvl == 0) {
            unlockCap = CampUpgradeStats.getCampUnlockStage(upgradeEnum);
            unlockStage = new TextObject(
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    StringsManager.getGuiString(GuiStringEnum.AVAILABLE_AT_STAGE) + ": " + CampUpgradeStats.getCampUnlockStage(upgradeEnum),
                    iconX + iconSize / 2f,
                    iconY + (iconSize/2f) * 0.45f,
                    Align.center);

            cost = new TextWithIcon(
                    GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    StringsManager.getGuiString(GuiStringEnum.COST) + ": " + CampUpgradeStats.getCampUpgradeCost(upgradeEnum, lvl),
                    iconX + iconSize / 2f,
                    iconY + (iconSize/2f) * 0.65f,
                    Align.center
            );
        }
        else {
            float descW = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.35f : Gdx.graphics.getWidth() * 0.5f;

            unlockedDesc = new MultiLineText(
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    StringsManager.getCampUpgradeDescription(upgradeEnum) + " " + CampUpgradeStats.getCampUpgradeStat(upgradeEnum, lvl-1),
                    Gdx.graphics.getWidth()/2f,
                    descW,
                    (Gdx.graphics.getWidth()/(float)CampUpgradeEnum.values().length) * 3.5f,
                    Align.center
            );
            if (lvl < 3) {
                cost = new TextWithIcon(
                        GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                        FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                        StringsManager.getGuiString(GuiStringEnum.UPGRADE) + " " + CampUpgradeStats.getCampUpgradeCost(upgradeEnum, lvl),
                        iconX + iconSize / 2f,
                        descY,
                        Align.topRight
                );
            }
        }

        mainDesc = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCampUpgradeMainDescription(upgradeEnum),
                Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getWidth()/2f,
                iconY - iconSize * 0.15f,
                Align.center
        );

    }

    void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (lvl > 0) {
            animation.getKeyFrame(stateTime).draw(batch, iconX, iconY, iconSize, iconSize);
            blackAlpha.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            unlockedDesc.draw(batch);
            if (lvl < 3) cost.draw(batch);

        }
        else {
            batch.getColor().a = 0.8f;
            animation.getKeyFrame(0).draw(batch, iconX, iconY, iconSize, iconSize);

            batch.getColor().a = 1f;
            blackAlpha.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            cost.draw(batch);
            if (SavedInfoManager.getPlayerStat(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE) < CampUpgradeStats.getCampUnlockStage(upgradeEnum)) {
                unlockStage.draw(batch);
            }
        }

        title.draw(batch);
        mainDesc.draw(batch);
    }

    boolean isUpgradePossible() {
        return lvl > 0 && lvl <= 2 && GlobalValues.getGold() >= upgradeCost && upgradeCost != -1;
    }

    boolean isUnlockPossible() {
        return lvl == 0 && PlayerStatisticsStats.getStatLvl(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE) >= unlockCap &&
                GlobalValues.getGold() >= upgradeCost;
    }

    CampUpgradeEnum getUpgradeEnum() {
        return upgradeEnum;
    }

    boolean isMaxedOut() {
        return lvl == 3;
    }
}
