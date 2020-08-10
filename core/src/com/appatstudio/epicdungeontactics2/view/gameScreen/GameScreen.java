package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapGenerator;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runQuitWindow.RunQuitWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public final class GameScreen {

    private CharacterEnum hero;
    private PerkEnum perk;

    private Room currRoom;
    private Stage currStage;
    private static OrthographicCamera camera;
    private static int stage = 1;
    private static float currentZoom;

    private static GuiContainer guiContainer;

    private static SpriteBatch mapGuiBatch;
    private static SpriteBatch gameBatch;

    private static EquipmentWindow equipmentWindow;

    private static final Color DAY_COLOR = new Color(1f, 1f, 1f, 0f);
    private static final Color NIGHT_COLOR = new Color(0f, 0f, 0.3f, 0.45f);

    public GameScreen(CharacterEnum hero, PerkEnum perk) {
        this.hero = hero;
        this.perk = perk;
        stage = 1;

        currStage = MapGenerator.createStage(stage);
        currRoom = currStage.getFirstRoom();

        camera = new OrthographicCamera();
        camera.zoom = WorldConfig.INIT_CAMERA_ZOOM;
        currentZoom = WorldConfig.INIT_CAMERA_ZOOM;

        gameBatch = new SpriteBatch();
        mapGuiBatch = new SpriteBatch();

        guiContainer = new GuiContainer(this);

    }

    public static void mapLongPress(float x, float y) {
    }

    public static void panStop() {
        currentZoom = camera.zoom;
    }

    public static void zoomCamera(float initialDistance, float distance) {
        camera.zoom = (initialDistance / distance) * currentZoom;

        if (camera.zoom < WorldConfig.CAMERA_ZOOM_LIMIT_MIN) {
            camera.zoom = WorldConfig.CAMERA_ZOOM_LIMIT_MIN;

        } else if (camera.zoom > WorldConfig.CAMERA_ZOOM_LIMIT_MAX) {
            camera.zoom = WorldConfig.CAMERA_ZOOM_LIMIT_MAX;

        }
    }

    public void backPressed() {
        guiContainer.backPressed();
    }

    public void tap(float x, float y) {
        guiContainer.tap(x, y);
    }

    public void draw() {
        camera.update();
        gameBatch.setProjectionMatrix(camera.combined);

        currRoom.draw(gameBatch, mapGuiBatch);

        guiContainer.draw();

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

    public static int getStage() {
        return stage;
    }

    public static void moveCamera(float x, float y, float deltaX, float deltaY) {

        camera.position.set(camera.position.x - deltaX * camera.zoom, camera.position.y + deltaY * camera.zoom, 1f);

        if (camera.position.x > WorldConfig.CAMERA_POSITION_X_MAX) {
            camera.position.x = WorldConfig.CAMERA_POSITION_X_MAX;
        } else if (camera.position.x < WorldConfig.CAMERA_POSITION_X_MIN) {
            camera.position.x = WorldConfig.CAMERA_POSITION_X_MIN;
        }
        if (camera.position.y > WorldConfig.CAMERA_POSITION_Y_MAX) {
            camera.position.y = WorldConfig.CAMERA_POSITION_Y_MAX;
        } else if (camera.position.y < WorldConfig.CAMERA_POSITION_Y_MIN) {
            camera.position.y = WorldConfig.CAMERA_POSITION_Y_MIN;
        }

    }




}
