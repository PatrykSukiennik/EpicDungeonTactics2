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

    private CharacterEnum hero;
    private PerkEnum perk;

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

    public GameScreen(CharacterEnum hero, PerkEnum perk) {
        this.hero = hero;
        this.perk = perk;
        stage = 1;

        currStage = MapGenerator.createStage(stage);
        currRoom = currStage.getFirstRoom();

        gameBatch = new SpriteBatch();
        mapGuiBatch = new SpriteBatch();

        guiContainer = new GuiContainer(this);

        guiContainer.setMapStage(currStage);

    }

    public void switchRoomInit(DirectionEnum direction) {
        CameraHandler.changeRoom(direction);
        switchRoomDelay = 0.5f;
        switchRoomDirection = direction;
    }

    public void nextStageInit() {
        newStageText.setText(StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + (stage + 1));
        CameraHandler.nextStage();
        switchStageDelay = 0.5f;
    }

    public static void mapLongPress(float x, float y) {
    }

    public void backPressed() {
        guiContainer.backPressed();
    }

    public void tap(float x, float y) {
        guiContainer.tap(x, y);

//        if (currRoom.getRoomNodes().get(DirectionEnum.RIGHT) != null) switchRoomInit(DirectionEnum.RIGHT);
//        else if (currRoom.getRoomNodes().get(DirectionEnum.BOTTOM) != null) switchRoomInit(DirectionEnum.BOTTOM);
//        else if (currRoom.getRoomNodes().get(DirectionEnum.LEFT) != null) switchRoomInit(DirectionEnum.LEFT);
//        else if (currRoom.getRoomNodes().get(DirectionEnum.TOP) != null) switchRoomInit(DirectionEnum.TOP);
        //if (y < Gdx.graphics.getHeight() * 0.5f) switchRoomInit(DirectionEnum.RIGHT);

        nextStageInit();
    }

    public void draw() {
        this.act(Gdx.graphics.getDeltaTime());

        CameraHandler.update(gameBatch);

        currRoom.draw(gameBatch, mapGuiBatch);

        CameraHandler.drawEffects(mapGuiBatch);

        guiContainer.draw();

        if (switchRoomDelay > 0) {
            CameraHandler.updateCamera();
            switchRoomDelay -= Gdx.graphics.getDeltaTime();
            if (switchRoomDelay <= 0) currRoom = currRoom.getRoomNodes().get(switchRoomDirection);
        }
        if (switchStageDelay > 0) {
            CameraHandler.updateCamera();
            switchStageDelay -= Gdx.graphics.getDeltaTime();
            CameraHandler.drawEffects(mapGuiBatch);
            newStageText.draw(mapGuiBatch, CameraHandler.getBlackOutAlpha());
            if (switchStageDelay <= 0) {
                stage++;
                newStageText.draw(mapGuiBatch, CameraHandler.getBlackOutAlpha());
                CameraHandler.resetCameraPos();
                currStage = MapGenerator.createStage(stage);
                currRoom = currStage.getFirstRoom();
                guiContainer.updateStage();
            }
        }
    }

    public CharacterEnum getHero() {
        return hero;
    }

    public PerkEnum getPerk() {
        return perk;
    }

    public void updateGold() {
        guiContainer.updateGold();
    }

    public static int getCurrentStage() {
        return stage;
    }


}
