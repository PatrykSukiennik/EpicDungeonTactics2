package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapGenerator;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGenerator;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runQuitWindow.RunQuitWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Stage;
import com.appatstudio.epicdungeontactics2.view.viewElements.AlphaTextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import static java.lang.Thread.sleep;

public final class GameScreen extends Actor {

    private static CharacterEnum hero;
    private static PerkEnum perk;

    private Room currRoom;
    private Stage currStage;
    private static int stage = 1;

    private static GuiContainer guiContainer;

    private static SpriteBatch mapGuiBatch;
    private static SpriteBatch gameBatch;

    private static EquipmentWindow equipmentWindow;

    private static final Color DAY_COLOR = new Color(1f, 1f, 1f, 0f);
    private static final Color NIGHT_COLOR = new Color(0f, 0f, 0.3f, 0.45f);

    private static float switchRoomDelay = 0f;
    private static DirectionEnum switchRoomDirection;

    private static float switchStageDelay = 0f;
    private static AlphaTextObject newStageText = new AlphaTextObject(
            FontsManager.getFont(
                    FontEnum.MENU_HERO_TITLE_UNLOCKED),
                    "",
                    Gdx.graphics.getWidth()/2f,
                    Gdx.graphics.getHeight() * 0.55f,
                    Align.center);

    private static float freshRunTextDelay = 0f;

    private static StatTracker statTracker;

    public GameScreen(CharacterEnum newHero, PerkEnum newPerk) {
        hero = newHero;
        perk = newPerk;

        gameBatch = new SpriteBatch();
        mapGuiBatch = new SpriteBatch();
    }

    public void startGame(CharacterEnum hero, PerkEnum perk) {
        StatTracker.init(hero, perk);
        stage = 1;

        freshRunInit();

        guiContainer = new GuiContainer();

        currStage = MapGenerator.createStage(stage);
        currRoom = currStage.getFirstRoom();
        guiContainer.setMapStage(currStage);
        guiContainer.roomChanged(null, currRoom);
    }

    public void freshRunInit() {
        CameraHandler.freshRun();
        newStageText.setText(StringsManager.getGuiString(GuiStringEnum.STAGE) + " 1");
        freshRunTextDelay = 2f;
        //guiContainer.refreshStats();
    }

    public CharacterEnum getCurrHero() {
        return hero;
    }

    public static void switchRoomInit(DirectionEnum direction) {
        CameraHandler.changeRoom(direction);
        switchRoomDelay = 0.8f;
        switchRoomDirection = direction;
    }

    public static void nextStageInit() {
        CameraHandler.nextStage();
        newStageText.setText(StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + (stage + 1));
        switchStageDelay = 1f;
    }

    public static void mapLongPress(float x, float y) {
    }

    public void backPressed() {
        guiContainer.backPressed();
    }

    public void tap(float x, float y) {
        if (CameraHandler.isTapPossible()) {
            if (guiContainer.tap(x, y));
            else if (currRoom.tap(x, y));
        }
    }


    public void draw() {
        this.act(Gdx.graphics.getDeltaTime());

        CameraHandler.update(gameBatch);

        currRoom.draw(gameBatch, mapGuiBatch, CameraHandler.getCamera());

        CameraHandler.drawEffects(mapGuiBatch);

        guiContainer.draw();

        handleRoomChanges();
    }

    public static CharacterEnum getHero() {
        return hero;
    }

    public static PerkEnum getPerk() {
        return perk;
    }

    public void updateGold() {
        guiContainer.updateGold();
    }

    public static int getCurrentStage() {
        return stage;
    }

    private void handleRoomChanges() {
        if (switchRoomDelay > 0) {
            CameraHandler.updateCamera();
            switchRoomDelay -= Gdx.graphics.getDeltaTime();
            if (switchRoomDelay <= 0) {
                Room oldRoom = currRoom;
                currRoom = currRoom.getRoomNodes().get(switchRoomDirection);
                guiContainer.roomChanged(oldRoom, currRoom);
            }
        }

        if (switchStageDelay > 0) {
            CameraHandler.updateCamera();
            switchStageDelay -= Gdx.graphics.getDeltaTime();
            CameraHandler.drawEffects(mapGuiBatch);
            if (switchStageDelay > 0.2f) newStageText.draw(mapGuiBatch, CameraHandler.getBlackOutAlpha());
            if (switchStageDelay <= 0) {
                stage++;
                newStageText.draw(mapGuiBatch, CameraHandler.getBlackOutAlpha());
                CameraHandler.resetCameraPos();
                currStage = MapGenerator.createStage(stage);
                currRoom = currStage.getFirstRoom();
                guiContainer.updateStage(currStage);
            }
        }

        if (freshRunTextDelay > 0) {
            CameraHandler.updateCamera();
            freshRunTextDelay -= Gdx.graphics.getDeltaTime();
            CameraHandler.drawEffects(mapGuiBatch);
            if (freshRunTextDelay > 0.2f) newStageText.draw(mapGuiBatch, CameraHandler.getBlackOutAlpha());
        }
    }

}
