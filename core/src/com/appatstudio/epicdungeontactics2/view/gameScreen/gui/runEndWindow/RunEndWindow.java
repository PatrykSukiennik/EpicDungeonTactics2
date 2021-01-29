package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runEndWindow;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class RunEndWindow {
    private Image bg;
    private boolean isUp = false;

    private TextObject title;
    private TextWithIcon stage;
    private TextWithIcon roomsCleared;
    private TextWithIcon goldCollected;
    private TextWithIcon killedEnemies;
    private CharacterExpLine[] expLines;
    private ButtonWithText finishButton;

    public RunEndWindow() {
        bg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0, 0);

        Array<CharacterEnum> usedCharacters = StatTracker.getUsedCharacters();
        expLines = new CharacterExpLine[usedCharacters.size];

        float segmentHeight = FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f;
        float fullHeight = (4 + expLines.length) * segmentHeight;
        float startY = Gdx.graphics.getHeight()/2f - fullHeight/2f;

        for (int i=0; i<usedCharacters.size; i++) {
            expLines[i] = new CharacterExpLine(usedCharacters.get(i),
                    StatTracker.getExpCollected().get(usedCharacters.get(i)),
                    StatTracker.getLvlUps().get(usedCharacters.get(i)),
                    startY + i * segmentHeight);
        }

        killedEnemies = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPlayerStatDescription(PlayerStatsTrackerFlagsEnum.KILLED_ENEMIES) + killedEnemies,
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight,
                Align.center
        );

        goldCollected = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPlayerStatDescription(PlayerStatsTrackerFlagsEnum.GOLD_COLLECTED) + goldCollected,
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 2,
                Align.center
        );

        roomsCleared = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPlayerStatDescription(PlayerStatsTrackerFlagsEnum.ROOMS_CLEARED) + roomsCleared,
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 3,
                Align.center
        );

        stage = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STAGE) + stage,
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 4,
                Align.center
        );

        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.NO_HERO_LEFT),
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 5.5f,
                Align.center
        );

        finishButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                Gdx.graphics.getWidth()/2f - segmentHeight * 1.5f,
                startY - segmentHeight * 1.8f,
                segmentHeight * 3,
                segmentHeight * 1.5f,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.FINISH)
        );

    }

    public boolean tap(float x, float y) {
        if (finishButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
            return true;
        }
        else return false;
    }

    public void draw(Batch batch) {
        bg.draw(batch, 1f);

        title.draw(batch);

        finishButton.draw(batch, 1f);
        stage.draw(batch);
        roomsCleared.draw(batch);
        goldCollected.draw(batch);
        killedEnemies.draw(batch);
        for (CharacterExpLine l : expLines) {
            l.draw(batch);
        }
    }

    public void show() {
        isUp = true;
    }

    public void hide() {
        isUp = false;
    }

    public boolean isUp() {
        return isUp;
    }
}


