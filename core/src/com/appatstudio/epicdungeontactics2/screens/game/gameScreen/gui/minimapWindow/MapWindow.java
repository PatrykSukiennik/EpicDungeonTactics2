package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.minimapWindow;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Stage;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class MapWindow {

    private static boolean isUp = false;

    private TextObject title;
    private SpriteDrawable blackBg;
    private Array<MapRoomIcon> roomIcons = new Array<>();

    public MapWindow() {
        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getCurrentStage(),
                Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getHeight() * 0.8f,
                Align.center);

        blackBg = GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent);

        int arraySize = WorldConfig.STAGE_MAX_ROOMS + 1;

    }

    public void draw(Batch batch) {
        blackBg.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (MapRoomIcon i : roomIcons) i.draw(batch);
        title.draw(batch);
    }

    public void tap(float x, float y) {
        if (!(x > Gdx.graphics.getWidth() * 0.2f && x < Gdx.graphics.getWidth() * 0.8f &&
                y > Gdx.graphics.getHeight() * 0.2f && y < Gdx.graphics.getHeight() * 0.8f)) this.hide();
    }

    public void hide() {
        isUp = false;
    }

    public void show() {
        isUp = true;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setStage(Stage stage) {
        roomIcons.clear();
        int count = stage.getRooms().size;

        calculateSize(stage.getRooms());
    }

    private void calculateSize(Array<Room> rooms) {
        int minX = rooms.get(0).getPosition().x;
        int maxX = rooms.get(0).getPosition().x;

        int minY = rooms.get(0).getPosition().y;
        int maxY = rooms.get(0).getPosition().y;

        for (Room r : rooms) {
            if (r.getPosition().x > maxX) maxX = r.getPosition().x;
            else if (r.getPosition().x < minX) minX = r.getPosition().x;

            if (r.getPosition().y > maxY) maxY = r.getPosition().y;
            else if (r.getPosition().y < minY) minY = r.getPosition().y;
        }

        int w = maxX - minX + 1;
        int h = maxY - minY + 1;

        float size = h >= w * 2f ?
                (Gdx.graphics.getHeight() * 0.7f) / h :
                (Gdx.graphics.getWidth() * 0.8f) / w;

        MapRoomIcon.setSize(size);
        CoordsFloat[][] coords = new CoordsFloat[w][h];

        for (int x=0; x<w; x++) {
            for (int y=0; y<h; y++) {
                coords[x][y] = new CoordsFloat(Gdx.graphics.getWidth() * 0.10f + size * x, Gdx.graphics.getHeight() * 0.45f - h/2f * size + size * y);
            }
        }

        roomIcons.clear();

        for (Room r : rooms) {
            roomIcons.add(
                    new MapRoomIcon(
                            r,
                            r.getType(),
                            coords[Math.abs(minX - r.getPosition().x)][Math.abs(minY - r.getPosition().y)]));
            roomIcons.get(roomIcons.size-1).getColor().a = 0.25f;

        }
    }

    public void setCurrRoom(Room oldRoom, Room newRoom) {
        for (MapRoomIcon i : roomIcons) {
            if (i.getRoom() == oldRoom) i.getColor().a = 0.6f;
            else if (i.getRoom() == newRoom) i.getColor().a = 1f;
        }
    }

}
