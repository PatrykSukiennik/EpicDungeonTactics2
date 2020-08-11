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
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.minimapWindow.MapWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runQuitWindow.RunQuitWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars.StatusBarContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Stage;
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
    private MapWindow mapWindow;

    private GuiButton eqButton, mapButton;

    public static final float guiButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;
    public static final float guiMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.05f;

    public GuiContainer(GameScreen gameScreen) {
        batch = new SpriteBatch();
        batch.enableBlending();

        runQuitWindow = new RunQuitWindow();

        communicatePrinter = new CommunicatePrinter();
        statusBarContainer = new StatusBarContainer(gameScreen.getHero());

        equipmentWindow = new EquipmentWindow(gameScreen.getHero());
        mapWindow = new MapWindow();

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
                StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getCurrentStage(),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f
                        - Gdx.graphics.getWidth() * 0.05f,
                Align.right
        );

        eqButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.EQUIPMENT_ICON), guiButtonSize, 0, Gdx.graphics.getHeight() * 0.7f);
        mapButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.MAP_ICON), guiButtonSize, 0, Gdx.graphics.getHeight() * 0.7f + guiButtonSize);

    }

    public void draw() {
        batch.begin();
        communicatePrinter.draw(batch);
        statusBarContainer.draw(batch);

        goldStatus.draw(batch);
        stageStatus.draw(batch);

        if (EquipmentWindow.isUp()) {
            eqButton.draw(batch, 0.5f);
        }
        else eqButton.draw(batch);


        mapButton.draw(batch);

        if (runQuitWindow.isUp()) {
            runQuitWindow.act(Gdx.graphics.getDeltaTime());
            runQuitWindow.draw(batch, 1f);
        }

        else if (EquipmentWindow.isUp()) {
            equipmentWindow.draw(batch);
        }

        else if (mapWindow.isUp()) {
            mapWindow.draw(batch);
        }


        batch.end();
    }

    public boolean tap(float x, float y) {
        if (runQuitWindow.isUp()) {
            runQuitWindow.tap(x, y);
            return true;
        }
        else if (mapWindow.isUp()) {
            mapWindow.tap(x, y);
            return true;
        }
        else if (EquipmentWindow.isUp()) {
            equipmentWindow.tap(x, y);
            return true;
        }
        else if (eqButton.isTap(x, y)) {
            EquipmentWindow.show();
        }
        else if (mapButton.isTap(x, y)) {
            mapWindow.show();
        }
        return false;
    }

    public void backPressed() {
        if (EquipmentWindow.isUp()) {
            EquipmentWindow.hide();
        }
        else if (mapWindow.isUp()) {
            mapWindow.hide();
        }
        else runQuitWindow.show();
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

    public void updateStage() {
        stageStatus.setText(StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getCurrentStage());
    }

    public void setMapStage(Stage stage) {
        mapWindow.setStage(stage);
    }

}
