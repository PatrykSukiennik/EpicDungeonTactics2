package com.appatstudio.epicdungeontactics2.view.statsScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class StatsScreen {

    private SpriteBatch batch;
    private TextWithIcon backButton;
    private MultiLineText[] stats;

    public StatsScreen() {
        batch = new SpriteBatch();

        backButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.BACK_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.BACK),
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f - FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0"),
                Align.left
        );

        SavedInfoFlagsEnum[] statEnums = {
            SavedInfoFlagsEnum.DEEPEST_STAGE,
                SavedInfoFlagsEnum.GOLD_COLLECTED,
                SavedInfoFlagsEnum.SKILL_POINTS_USED,
                SavedInfoFlagsEnum.BOSSES_KILLED,
                SavedInfoFlagsEnum.KILLED_ENEMIES,
                SavedInfoFlagsEnum.DMG_DEALT,
                SavedInfoFlagsEnum.HIGHEST_DMG_DEALT
        };

        stats = new MultiLineText[statEnums.length];

        for (int i=0; i<stats.length; i++) {
            int value = SavedInfoManager.getIntFromFlag(statEnums[i]) == -1 ? 0 : SavedInfoManager.getIntFromFlag(statEnums[i]);
            stats[i] = new MultiLineText(
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    StringsManager.getPlayerStatDescription(statEnums[i]) + " " + Integer.toString(value),
                    Gdx.graphics.getWidth() * 0.5f,
                    Gdx.graphics.getWidth() * 0.8f,
                    Gdx.graphics.getHeight() * 0.8f - i * Gdx.graphics.getWidth() * 0.1f,
                    Align.left
            );
        }
    }

    public void draw() {
        batch.begin();
        backButton.draw(batch);

        for (MultiLineText t : stats) {
            t.draw(batch);
        }
        batch.end();
    }

    public void tap(float x, float y) {
        if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }
    }

}
