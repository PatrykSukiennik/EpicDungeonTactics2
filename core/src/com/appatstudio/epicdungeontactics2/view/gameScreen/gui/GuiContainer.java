package com.appatstudio.epicdungeontactics2.view.gameScreen.gui;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.questWindow.QuestWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runQuitWindow.RunQuitWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars.StatusBarContainer;
import com.appatstudio.epicdungeontactics2.view.viewElements.GuiButton;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public final class GuiContainer {

    private Batch batch;

    private RunQuitWindow runQuitWindow;

    private TextWithIcon goldStatus;
    private TextObject stageStatus;

    private CommunicatePrinter communicatePrinter;
    private StatusBarContainer statusBarContainer;

    private EquipmentWindow equipmentWindow;
    private QuestWindow questWindow;

    private GuiButton eqButton, questButton;

    public static final float guiButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.07f : Gdx.graphics.getWidth() * 0.1f;
    public static final float guiMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.05f;

    public GuiContainer(GameScreen gameScreen) {
        batch = new SpriteBatch();
        batch.enableBlending();

        runQuitWindow = new RunQuitWindow();

        communicatePrinter = new CommunicatePrinter();
        statusBarContainer = new StatusBarContainer(gameScreen.getHero());

        equipmentWindow = new EquipmentWindow();
        questWindow = new QuestWindow();

        statusBarContainer.addEffect(EffectEnum.POISON, 4);
        statusBarContainer.addEffect(EffectEnum.POISON, 3);
        statusBarContainer.addEffect(EffectEnum.POISON, 2);
        statusBarContainer.addEffect(EffectEnum.POISON, 11);

        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.right
        );
        stageStatus = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getStage(),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f
                        - Gdx.graphics.getWidth() * 0.05f,
                Align.right
        );

        eqButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.EQUIPMENT_ICON), guiButtonSize, guiMargin, statusBarContainer.getBottomY() - guiMargin - guiButtonSize);
        questButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.QUEST_ICON), guiButtonSize, guiMargin, eqButton.getY() -guiMargin - guiButtonSize);

    }

    public void draw() {
        batch.begin();
        communicatePrinter.draw(batch);
        statusBarContainer.draw(batch);

        goldStatus.draw(batch);
        stageStatus.draw(batch);

        eqButton.draw(batch);
        questButton.draw(batch);

        if (runQuitWindow.isUp()) {
            runQuitWindow.act(Gdx.graphics.getDeltaTime());
            runQuitWindow.draw(batch, 1f);
        }

        batch.end();
    }

    public boolean tap(float x, float y) {
        if (runQuitWindow.isUp()) {
            runQuitWindow.tap(x, y);
            return true;
        }
        return false;
    }

    public void backPressed() {
        runQuitWindow.show();
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

    public void updateStage() {
        stageStatus.setText(StringsManager.getGuiString(GuiStringEnum.STAGE) + "  " + GameScreen.getStage());
    }

}
