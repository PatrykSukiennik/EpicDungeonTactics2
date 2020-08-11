package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.minimapWindow;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Map;

public class MapRoomIcon {

    private static float size;
    private static Map<RoomTypeEnum, SpriteDrawable> icons;

    private SpriteDrawable icon;
    private CoordsFloat coords;

    static {
        icons = GraphicsManager.getMapRoomIcons();
    }

    MapRoomIcon(RoomTypeEnum type, CoordsFloat coords) {
        this.icon = icons.get(type);
        this.coords = coords;
    }

    void draw(Batch batch) {
        icon.draw(batch, coords.x, coords.y, size, size);
    }

    static void setSize(float s) {
        size = s;
    }


}
