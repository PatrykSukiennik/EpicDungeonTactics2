package com.appatstudio.epicdungeontactics2.global.input;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.CameraHandler;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public final class MobileInputManager implements GestureDetector.GestureListener {

    private final float SWIPE_TOLERANCE = Gdx.graphics.getWidth() * 0.1f;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        EpicDungeonTactics.tap(x, Gdx.graphics.getHeight() - y);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        if (EpicDungeonTactics.isGame()) {
            GameScreen.mapLongPress(x, y);
        }
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > SWIPE_TOLERANCE) {
                EpicDungeonTactics.swiped(DirectionEnum.RIGHT);
            } else if (velocityX < -SWIPE_TOLERANCE) {
                EpicDungeonTactics.swiped(DirectionEnum.LEFT);
            }
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (EpicDungeonTactics.isGame() && EpicDungeonTactics.canCameraMove()) {
            CameraHandler.moveCamera(x, y, deltaX, deltaY);
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (EpicDungeonTactics.isGame()) {
            CameraHandler.panStop();
        }
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (EpicDungeonTactics.isGame()) {
            CameraHandler.zoomCamera(initialDistance, distance);
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }


}
