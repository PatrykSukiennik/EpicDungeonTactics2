package com.appatstudio.epicdungeontactics2.global;

import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.CameraHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

public class WorldConfig {

    public static final int ROOM_WIDTH = 11;
    public static final int ROOM_HEIGHT = 21;
    public static final float TILE_SIZE = 16;
    public static final float ROOM_WIDTH_RES = ROOM_WIDTH * TILE_SIZE;
    public static final float ROOM_HEIGHT_RES = ROOM_HEIGHT * TILE_SIZE;

    public static final float ROOM_POS_X = ROOM_WIDTH_RES / 2f - (ROOM_WIDTH_RES / 2f);
    public static final float ROOM_POS_Y = ROOM_HEIGHT_RES / 2f - (ROOM_HEIGHT_RES / 2f);

    public static final float CAMERA_POSITION_X_MAX = ROOM_POS_X + ROOM_WIDTH_RES - TILE_SIZE * 2f;
    public static final float CAMERA_POSITION_X_MIN = ROOM_POS_X + TILE_SIZE * 2f;
    public static final float CAMERA_POSITION_Y_MAX = ROOM_POS_Y + ROOM_HEIGHT_RES - TILE_SIZE * 4f;
    public static final float CAMERA_POSITION_Y_MIN = ROOM_POS_Y + TILE_SIZE * 4f;

    public static final float INIT_CAMERA_ZOOM = ROOM_WIDTH_RES / Gdx.graphics.getWidth() * 8f;
    public static final float CAMERA_ZOOM_LIMIT_MIN = INIT_CAMERA_ZOOM * 0.4f;
    public static final float CAMERA_ZOOM_LIMIT_MAX = INIT_CAMERA_ZOOM * 1.2f;

    public static final int STAGE_MIN_ROOMS = 15;
    public static final int STAGE_MAX_ROOMS = 25;

    public static final float MOVE_SPEED_CLEAN = 0.08f;
    public static final float MOVE_SPEED_FIGHT = 0.15f;

    public static final CoordsFloat[][] tileCoords;

    public static final HashMap<MapElementAnimationEnum, CoordsInt> mapElementAnimationsSize;
    public static final HashMap<MapElementSpriteEnum, CoordsInt> mapElementSpritesSize;

    static {
        tileCoords = new CoordsFloat[ROOM_WIDTH][ROOM_HEIGHT];
        for (int x = 0; x < ROOM_WIDTH; x++) {
            for (int y = 0; y < ROOM_HEIGHT; y++) {
                tileCoords[x][y] = new CoordsFloat(-TILE_SIZE/2f + x * TILE_SIZE, TILE_SIZE/4f + y * TILE_SIZE);
            }
        }

        mapElementAnimationsSize = new HashMap<>();
        mapElementAnimationsSize.put(MapElementAnimationEnum.CHEST, new CoordsInt(16, 16));
        mapElementAnimationsSize.put(MapElementAnimationEnum.LAVA, new CoordsInt(16, 48));
        mapElementAnimationsSize.put(MapElementAnimationEnum.WATER, new CoordsInt(16, 48));
        mapElementAnimationsSize.put(MapElementAnimationEnum.CANDLE, new CoordsInt(16, 16));
        mapElementAnimationsSize.put(MapElementAnimationEnum.CANDLE_BIG, new CoordsInt(16, 48));
        mapElementAnimationsSize.put(MapElementAnimationEnum.TORCH, new CoordsInt(16, 16));

        mapElementSpritesSize = new HashMap<>();
        mapElementSpritesSize.put(MapElementSpriteEnum.CHEST_EMPTY, new CoordsInt(16, 16));
        mapElementSpritesSize.put(MapElementSpriteEnum.CRATE, new CoordsInt(16, 16));
    }

    public static CoordsFloat getTileCoord(int x, int y) {
        return tileCoords[x][y];
    }

    public static CoordsInt getIntCoordsFromFloatPoint(float x, float y) {
        Vector3 touch = new Vector3(x, Gdx.graphics.getHeight()-y, 0);
        CameraHandler.getCamera().unproject(touch);

        CoordsInt result = new CoordsInt(-1, -1);

        for (int i = 0; i < ROOM_WIDTH; i++) {
            if (touch.x < tileCoords[i][0].x) {
                result.x = i-1;
                break;
            }
        }
        for (int i = 0; i < ROOM_HEIGHT; i++) {
            if (touch.y < tileCoords[0][i].y) {
                result.y = i-1;
                break;
            }
        }

        if (result.x == -1) result.x = ROOM_WIDTH - 1;
        if (result.y == -1) result.y = ROOM_HEIGHT - 1;

        return result;
    }

    public static CoordsInt getIntCoordsFromTouch(OrthographicCamera camera, float x, float y) {
        Vector3 coords = new Vector3(x, y, 0);
        camera.unproject(coords);
        return getIntCoordsFromFloatPoint(coords.x, coords.y);
    }

    public static CoordsInt getMapElementSpriteSize(MapElementSpriteEnum spriteEnum) {
        return mapElementSpritesSize.get(spriteEnum);
    }

    public static CoordsInt getMapElementAnimationSize(MapElementAnimationEnum animationEnum) {
        return mapElementAnimationsSize.get(animationEnum);
    }




}
