package com.appatstudio.epicdungeontactics2.view.menuScreen;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class CharacterIcon extends Image {

    private final CharacterEnum characterEnum;
    private final Animation<SpriteDrawable> animation;
    private float stateTime = 0f;

    private RelativePosText title, description, unlockStage;
    private RelativePosTextWithIcon cost;

    private LvlExpBar lvlExpBar;

    boolean isUnlocked;

    private float itemsY;
    private float itemSize;
    private float[] itemsX;
    private SpriteDrawable[] startingItems;
    private RelativePosTextWithIcon[] stats;

    CharacterIcon(CharacterEnum character) {
        this.animation = GraphicsManager.getCharactersAnimation(character, CharacterStateEnum.IDLE);

        this.characterEnum = character;
        this.isUnlocked = SavedInfoManager.isUnlocked(characterEnum);

        this.setSize(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getWidth() / 2f);
        this.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.85f - this.getHeight());

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

        if (!isUnlocked) this.getColor().a = 0.3f;

        ItemEnum[] items = HeroStats.getStartingItems(characterEnum);
        startingItems = new SpriteDrawable[items.length];
        itemsX = new float[items.length];
        for (int i = 0; i < items.length; i++) {
            startingItems[i] = GraphicsManager.getItemImage(items[i]);
            itemSize = this.getWidth() / 5f;
            itemsX[i] = Gdx.graphics.getWidth() / 2f - (itemSize / 2f * startingItems.length * 1.2f) + i * itemSize * 1.2f;
        }
        itemsY = this.getY() - itemSize * 1.8f;


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
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                    StringsManager.getGuiString(GuiStringEnum.AVAILABLE_AT_STAGE) + ": " + HeroStats.getRequiredStageToUnlock(characterEnum),
                    Align.center);

            cost = new RelativePosTextWithIcon(
                    GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isUnlocked) stateTime += Gdx.graphics.getDeltaTime();

        if (isUnlocked)
            lvlExpBar.draw(batch, this.getX() + this.getWidth() * 1.1f, this.getY() + this.getHeight() * 0.2f);

        this.setDrawable(animation.getKeyFrame(stateTime));
        super.draw(batch, parentAlpha);

        title.draw(batch,
                this.getX() + this.getWidth() / 2f,
                this.getY() + this.getHeight() * 1.2f);
        description.draw(batch,
                this.getX() + this.getWidth() / 2f,
                this.getY() + this.getHeight() * 1.07f);

        for (int i = 0; i < startingItems.length; i++) {
            startingItems[i].draw(batch, this.getX() - Gdx.graphics.getWidth() / 2f + this.getWidth() / 2f + itemsX[i], itemsY, itemSize, itemSize);
        }

        for (int i = 0; i < stats.length; i++) {
            stats[i].draw(batch, this.getX() + this.getWidth() * 0.35f, this.getY() - this.getWidth() * 0.6f - (i * this.getWidth() * 0.16f));
        }

        if (!isUnlocked) {
            cost.draw(batch, this.getX() + this.getWidth() / 2f, this.getY() + this.getHeight() * 0.65f);
            if (SavedInfoManager.getPlayerStat(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE) < HeroStats.getRequiredStageToUnlock(characterEnum))
                unlockStage.draw(batch, this.getX() + this.getWidth() / 2f, this.getY() + this.getHeight() * 0.45f);
        }

    }

    boolean canBeUnlocked() {
        return GlobalValues.getGold() >= HeroStats.getBuyCost(characterEnum) && SavedInfoManager.getPlayerStat(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE) >= HeroStats.getRequiredStageToUnlock(characterEnum);
    }

    void moveToCenterNow() {
        this.setPosition(Gdx.graphics.getWidth() / 2f - this.getWidth() / 2f, this.getY());
    }


}
