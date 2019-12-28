package com.appatstudio.epicdungeontactics2.view.statsScreen;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.PlayerStatisticsStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

final class StatPosition {

    private static final SpriteDrawable[] medals;
    private static BitmapFont font;

    private PlayerStatsTrackerFlagsEnum flag;
    private TextWithIcon textLeft, collectText;
    private TextObject textRightProgress;
    private float bottomY;
    private int reward;

    static {
        medals = new SpriteDrawable[]{
                GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_MEDAL),
                GraphicsManager.getGuiElement(GuiElementEnum.SILVER_MEDAL),
                GraphicsManager.getGuiElement(GuiElementEnum.GOLD_MEDAL),
                GraphicsManager.getGuiElement(GuiElementEnum.DIAMOND_MEDAL)
        };

        font = FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED);

    }

    StatPosition(PlayerStatsTrackerFlagsEnum flag, int pos) {
        this.flag = flag;
        this.bottomY = Gdx.graphics.getHeight() * 0.8f - pos * Gdx.graphics.getWidth() * 0.1f;

        int lvl = PlayerStatisticsStats.getStatLvl(flag);
        int value = SavedInfoManager.getPlayerStat(flag);
        if (value == -1) value = 0;

        int cap = PlayerStatisticsStats.getCap(flag, lvl);

        reward = SavedInfoManager.getPlayerStatRewardWaiting(flag);

        textLeft = new TextWithIcon(
                medals[lvl],
                font,
                StringsManager.getPlayerStatDescription(flag),
                Gdx.graphics.getWidth() * 0.05f,
                bottomY,
                Align.left
        );

        textRightProgress = new TextObject(
                font,
                value + "/" + cap,
                Gdx.graphics.getWidth() * 0.95f,
                bottomY,
                Align.right
        );

        if (reward > 0) {
            collectText = new TextWithIcon(
                    GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                    font,
                    Integer.toString(PlayerStatisticsStats.getReward(flag, lvl - 1)),
                    Gdx.graphics.getWidth() * 0.95f,
                    bottomY,
                    Align.right
            );
        }

    }

    void tap(float x, float y) {
        if (collectText != null && collectText.tap(x, y)) {
            GlobalValues.addGold(reward);
            collectText = null;
        }
    }

    void draw(Batch batch) {
        textLeft.draw(batch);
        if (collectText != null) collectText.draw(batch);
        else textRightProgress.draw(batch);
    }


}
