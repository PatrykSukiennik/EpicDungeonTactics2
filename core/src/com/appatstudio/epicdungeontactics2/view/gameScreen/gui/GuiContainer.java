package com.appatstudio.epicdungeontactics2.view.gameScreen.gui;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.questWindow.QuestWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars.StatusBarContainer;
import com.appatstudio.epicdungeontactics2.view.viewElements.GuiButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class GuiContainer {

    private Batch batch;

    private CommunicatePrinter communicatePrinter;
    private StatusBarContainer statusBarContainer;

    private EquipmentWindow equipmentWindow;
    private QuestWindow questWindow;

    private GuiButton eqButton, questButton;

    public static final float guiButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.07f : Gdx.graphics.getWidth() * 0.1f;
    public static final float guiMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.05f;

    public GuiContainer(GameScreen gameScreen) {
        batch = new SpriteBatch();

        communicatePrinter = new CommunicatePrinter();
        statusBarContainer = new StatusBarContainer(gameScreen.getHero());

        equipmentWindow = new EquipmentWindow();
        questWindow = new QuestWindow();

        statusBarContainer.addEffect(EffectEnum.POISON, 4);
        statusBarContainer.addEffect(EffectEnum.POISON, 3);
        statusBarContainer.addEffect(EffectEnum.POISON, 2);
        statusBarContainer.addEffect(EffectEnum.POISON, 11);

        eqButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.EQUIPMENT_ICON), guiButtonSize, guiMargin, statusBarContainer.getBottomY() - guiMargin - guiButtonSize);
        questButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.QUEST_ICON), guiButtonSize, guiMargin, eqButton.getY() -guiMargin - guiButtonSize);
    }

    public void draw() {
        batch.begin();
        communicatePrinter.draw(batch);
        statusBarContainer.draw(batch);

        eqButton.draw(batch);
        questButton.draw(batch);

        batch.end();
    }

}
