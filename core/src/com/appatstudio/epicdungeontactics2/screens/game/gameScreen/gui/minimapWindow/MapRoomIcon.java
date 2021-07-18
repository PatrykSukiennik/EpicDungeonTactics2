package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.minimapWindow;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Map;

public class MapRoomIcon extends Image {

    private static float size;
    private static Map<RoomTypeEnum, SpriteDrawable> icons;
    private Room room;
    private Image statusIcon;

    static {
        icons = GraphicsManager.getMapRoomIcons();
    }

    MapRoomIcon(Room room, RoomTypeEnum type, CoordsFloat coords) {
        super(icons.get(type));
        this.setPosition(coords.x, coords.y);
        this.setSize(size, size);
        this.room = room;

        statusIcon = new Image(GraphicsManager.getGuiElement(GuiElementEnum.BLACK));
        statusIcon.setSize(size/2f, size/2f);
        statusIcon.setPosition(
                getX() + getWidth()/2f - statusIcon.getWidth()/2f,
                getY() + getHeight()/2f - statusIcon.getHeight()/2f);
    }

    void draw(Batch batch) {
        this.draw(batch, 1f);
    }

    public Room getRoom() {
        return room;
    }

    static void setSize(float s) {
        size = s;
    }


}
