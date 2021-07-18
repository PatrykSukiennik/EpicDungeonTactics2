package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.runEndWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class RunEndWindow {
    private Image bg;
    private boolean isUp = false;

    private MultiLineText title;
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

        float segmentHeight = FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 3f;
        float fullHeight = (4 + expLines.length) * segmentHeight;
        float startY = Gdx.graphics.getHeight()/2f - fullHeight/2f;

        for (int i=usedCharacters.size-1; i>=0; i--) {
            expLines[i] = new CharacterExpLine(usedCharacters.get(i),
                    StatTracker.getExpCollected().get(usedCharacters.get(i)),
                    StatTracker.getLvlUps().get(usedCharacters.get(i)),
                    startY + (usedCharacters.size - 1 - i) * segmentHeight);
        }

        killedEnemies = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPlayerStatDescription(PlayerStatsTrackerFlagsEnum.KILLED_ENEMIES) + " " + GameScreen.getInstance().getKilledEnemies(),
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight,
                Align.center
        );

        goldCollected = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPlayerStatDescription(PlayerStatsTrackerFlagsEnum.GOLD_COLLECTED) + " " + GameScreen.getInstance().getGoldCollected(),
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 2,
                Align.center
        );

        roomsCleared = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPlayerStatDescription(PlayerStatsTrackerFlagsEnum.ROOMS_CLEARED) + " " + GameScreen.getInstance().getRoomsCleared(),
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 3,
                Align.center
        );

        stage = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getCurrentStage(),
                Gdx.graphics.getWidth()/2f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 4,
                Align.center
        );

        title = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.NO_HERO_LEFT),
                Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getWidth() * 0.8f,
                startY + (expLines.length) * segmentHeight + segmentHeight * 8f,
                Align.center
        );

        finishButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                startY - MenuScreen.BOTTOM_BUTTON_HEIGHT * 1.8f,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.FINISH)
        );

    }

    public boolean tap(float x, float y) {
        if (finishButton.tap(x, y)) {
            //EpicDungeonTactics.refreshMenu();
            //EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
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


