package com.appatstudio.epicdungeontactics2.screens.game.statsScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.screens.menu.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class StatsScreen {

    private static SpriteBatch batch;
    private static TextWithIcon backButton, goldStatus;
    private static StatPosition[] stats;
    private static MultiLineText title;

    public StatsScreen() {
        batch = new SpriteBatch();

        backButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.BACK_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.BACK),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0"),
                Align.right
        );
        title = new MultiLineText(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.PLAYER_STATS),
                Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight() * 0.92f,
                Align.center);
        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.left
        );


    }

    static {
        PlayerStatsTrackerFlagsEnum[] allStats = PlayerStatsTrackerFlagsEnum.values();

        stats = new StatPosition[allStats.length];
        for (int i = 0; i < stats.length; i++) {
            stats[i] = new StatPosition(allStats[i], i);
        }
    }

    public static void refreshStat(PlayerStatsTrackerFlagsEnum flag) {
        for (int i = 0; i < stats.length; i++) {
            if (stats[i].getFlag() == flag) stats[i] = new StatPosition(flag, i);
        }
    }

    public void draw() {
        MenuBgContainer.draw(batch);
        MenuBgContainer.drawAlpha50(batch);

        batch.begin();
        title.draw(batch);
        backButton.draw(batch);
        goldStatus.draw(batch);
        for (StatPosition s : stats) {
            s.draw(batch);
        }
        batch.end();
    }

    public void tap(float x, float y) {
        for (StatPosition sp : stats) {
            if (sp.tap(x, y)) {
                return;
            }
        }
        if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

}
