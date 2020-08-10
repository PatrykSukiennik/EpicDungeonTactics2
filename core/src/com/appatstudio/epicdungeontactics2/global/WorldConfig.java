package com.appatstudio.epicdungeontactics2.global;

import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class WorldConfig {

    public static final int ROOM_WIDTH = 11;
    public static final int ROOM_HEIGHT = 21;
    public static final float TILE_SIZE = 32;
    public static final float ROOM_WIDTH_RES = ROOM_WIDTH * TILE_SIZE;
    public static final float ROOM_HEIGHT_RES = ROOM_HEIGHT * TILE_SIZE;
    public static final float INIT_CAMERA_ZOOM = ROOM_WIDTH_RES / Gdx.graphics.getWidth();

    public static final float ROOM_POS_X = ROOM_WIDTH_RES / 2f - (ROOM_WIDTH_RES / 2f);
    public static final float ROOM_POS_Y = ROOM_HEIGHT_RES / 2f - (ROOM_HEIGHT_RES / 2f);

    public static final float CAMERA_POSITION_X_MAX = ROOM_POS_X + ROOM_WIDTH_RES - TILE_SIZE * 4f;
    public static final float CAMERA_POSITION_X_MIN = ROOM_POS_X + TILE_SIZE * 4f;
    public static final float CAMERA_POSITION_Y_MAX = ROOM_POS_Y + ROOM_HEIGHT_RES - TILE_SIZE * 4f;
    public static final float CAMERA_POSITION_Y_MIN = ROOM_POS_Y + TILE_SIZE * 4f;

    public static final float CAMERA_ZOOM_LIMIT_MIN = INIT_CAMERA_ZOOM * 0.4f;
    public static final float CAMERA_ZOOM_LIMIT_MAX = INIT_CAMERA_ZOOM * 1.2f;

    public static final int STAGE_MIN_ROOMS = 15;
    public static final int STAGE_MAX_ROOMS = 25;

    public static final CoordsFloat[][] tileCoords;

    static {
        tileCoords = new CoordsFloat[ROOM_WIDTH][ROOM_HEIGHT];
        for (int x = 0; x < ROOM_WIDTH; x++) {
            for (int y = 0; y < ROOM_HEIGHT; y++) {
                tileCoords[x][y] = new CoordsFloat(x * TILE_SIZE, y * TILE_SIZE);
            }
        }

    }

    public static CoordsFloat getTileCoord(int x, int y) {
        return tileCoords[x][y];
    }

    public static CoordsInt getTouchCoords(float x, float y) {
        CoordsInt result = new CoordsInt(-1, -1);

        for (int i = 0; i < ROOM_WIDTH; i++) {
            if (x < tileCoords[i][0].x) {
                result.x = i-1;
                break;
            }
        }
        for (int i = 0; i < ROOM_HEIGHT; i++) {
            if (y < tileCoords[0][i].y) {
                result.y = i-1;
                break;
            }
        }

        if (result.x == -1) result.x = ROOM_WIDTH - 1;
        if (result.y == -1) result.y = ROOM_HEIGHT - 1;

        return result;
    }


}
