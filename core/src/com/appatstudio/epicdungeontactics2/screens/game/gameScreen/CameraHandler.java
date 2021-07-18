package com.appatstudio.epicdungeontactics2.screens.game.gameScreen;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CameraHandler extends Actor {

    private static Actor movingPoint;
    private static OrthographicCamera camera;
    private static float currentZoom;
    private static float actingDuration;

    private static Image blackOut;

    static {
        movingPoint = new Actor();

        camera = new OrthographicCamera(WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES);
        camera.zoom = WorldConfig.INIT_CAMERA_ZOOM;
        currentZoom = WorldConfig.INIT_CAMERA_ZOOM;
        camera.position.x = WorldConfig.ROOM_WIDTH_RES/2f;
        camera.position.y = WorldConfig.ROOM_HEIGHT_RES/2f;
        camera.update();

        blackOut = new Image(GraphicsManager.getGuiElement(GuiElementEnum.BLACK));
        blackOut.setPosition(0, 0);
        blackOut.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        blackOut.getColor().a = 0f;
    }

    CameraHandler() {

    }

    public static void update(Batch gameBatch) {
        if (actingDuration > 0) {
            movingPoint.act(Gdx.graphics.getDeltaTime());
            camera.position.x = movingPoint.getX();
            camera.position.y = movingPoint.getY();
            actingDuration -= Gdx.graphics.getDeltaTime();
        }

        camera.update();
        gameBatch.setProjectionMatrix(camera.combined);
    }

    public static void drawEffects(Batch guiBatch) {
        guiBatch.begin();
        blackOut.act(Gdx.graphics.getDeltaTime());
        blackOut.draw(guiBatch, 1f);
        guiBatch.end();
    }

    public static void drawNewStage(Batch guiBatch) {
        guiBatch.begin();
        blackOut.act(Gdx.graphics.getDeltaTime());
        blackOut.draw(guiBatch, 1f);
        guiBatch.end();
    }

    public static void moveCamera(float x, float y, float deltaX, float deltaY) {

        if (actingDuration <= 0) {

            camera.position.set(camera.position.x - deltaX * camera.zoom / 4f, camera.position.y + deltaY * camera.zoom / 4f, 1f);

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

            camera.update();

        }
    }

    public static void zoomCamera(float initialDistance, float distance) {

        if (actingDuration <= 0) {

            camera.zoom = (initialDistance / distance) * currentZoom;

            if (camera.zoom < WorldConfig.CAMERA_ZOOM_LIMIT_MIN) {
                camera.zoom = WorldConfig.CAMERA_ZOOM_LIMIT_MIN;

            } else if (camera.zoom > WorldConfig.CAMERA_ZOOM_LIMIT_MAX) {
                camera.zoom = WorldConfig.CAMERA_ZOOM_LIMIT_MAX;
            }
        }
    }

    public static void panStop() {
        currentZoom = camera.zoom;
    }

    public static void changeRoom(DirectionEnum direction) {
        movingPoint.setPosition(camera.position.x, camera.position.y);

        blackOut.addAction(
                Actions.sequence(
                        Actions.fadeIn(0.8f),
                        Actions.fadeOut(0.8f)
                )
        );

        actingDuration = 1f;
        switch (direction) {
            case TOP: {
                movingPoint.addAction(
                        Actions.sequence(
                        Actions.moveTo(WorldConfig.ROOM_WIDTH_RES/2f, movingPoint.getY() + WorldConfig.ROOM_HEIGHT_RES/2f, 0.8f),
                                Actions.moveTo(WorldConfig.ROOM_WIDTH_RES/2f,  WorldConfig.TILE_SIZE * 2f, 0),
                                Actions.moveBy(0, -WorldConfig.TILE_SIZE, 0.8f))
                );
                break;
            }
            case LEFT: {
                movingPoint.addAction(
                        Actions.sequence(
                                Actions.moveTo(movingPoint.getX() + WorldConfig.ROOM_WIDTH_RES/2f, WorldConfig.ROOM_HEIGHT_RES/2f, 0.8f),
                                Actions.moveTo(WorldConfig.ROOM_WIDTH_RES, WorldConfig.ROOM_HEIGHT_RES/2f, 0),
                                Actions.moveBy(-WorldConfig.TILE_SIZE, 0, 0.8f))
                );
                break;
            }
            case RIGHT: {
                movingPoint.addAction(
                        Actions.sequence(
                                Actions.moveTo(movingPoint.getX() + WorldConfig.ROOM_WIDTH_RES/2f, WorldConfig.ROOM_HEIGHT_RES/2f, 0.8f),
                                Actions.moveTo(0, WorldConfig.ROOM_HEIGHT_RES/2f, 0),
                                Actions.moveBy(WorldConfig.TILE_SIZE, 0, 0.8f))
                );
                break;
            }
            case BOTTOM: {
                movingPoint.addAction(
                        Actions.sequence(
                                Actions.moveTo(WorldConfig.ROOM_WIDTH_RES/2f, movingPoint.getY() - WorldConfig.ROOM_HEIGHT_RES/2f, 0.8f),
                                Actions.moveTo(WorldConfig.ROOM_WIDTH_RES/2f, WorldConfig.ROOM_HEIGHT_RES - WorldConfig.TILE_SIZE * 2f, 0),
                                Actions.moveBy(0, WorldConfig.TILE_SIZE, 0.8f))
                );
                break;
            }
        }
    }

    public static void nextStage() {
        actingDuration = 2.5f;

        blackOut.addAction(
                Actions.sequence(
                        Actions.fadeIn(1f),
                        Actions.fadeOut(1.5f)
                )
        );
    }

    public static float getBlackOutAlpha() {
        return blackOut.getColor().a;
    }

    public static void resetCameraPos() {
        camera.position.x = WorldConfig.ROOM_WIDTH_RES/2f;
        camera.position.y = WorldConfig.ROOM_HEIGHT_RES/2f;
        camera.update();
    }

    public static void updateCamera() {
        camera.update();
    }

    public static boolean isTapPossible() {
        return actingDuration <= 0;
    }

    public static void freshRun() {
        blackOut.getColor().a = 1f;
        blackOut.addAction(
                Actions.fadeOut(4f)
        );

        camera.zoom = WorldConfig.INIT_CAMERA_ZOOM;
        currentZoom = WorldConfig.INIT_CAMERA_ZOOM;
        camera.position.x = WorldConfig.ROOM_WIDTH_RES/2f;
        camera.position.y = WorldConfig.ROOM_HEIGHT_RES/2f;
        camera.update();
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public static Vector3 projectCoords(Vector3 pos) {
        return camera.project(pos);
    }
    public static Vector3 unprojectCoords(Vector3 pos) {
        return camera.unproject(pos);
    }

    public static float getZoom() {
        return camera.zoom;
    }

    public static void centerOnCoords(float x, float y) {
        currentZoom = WorldConfig.CAMERA_ZOOM_LIMIT_MIN;
        camera.zoom = WorldConfig.CAMERA_ZOOM_LIMIT_MIN;
        camera.position.x = x;
        camera.position.y = y;
    }
}
