package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.BackpackSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class BackpackPage {

    private final int COLS;
    private final int ROWS;

    private static final float WIDTH = AbstractSegment.getFullWidth() * 0.8f;
    private static final float HEIGHT = AbstractSegment.getFullHeight();
    private static final float X = AbstractSegment.getPosX() + CategoryColumns.getW();

    private final float itemSize;
    private Vector2[][] grid;
    private ItemBlock[] items;
    private static SpriteDrawable borders;

    private CategoryColumns categoryColumns;

    public BackpackPage(ItemBackpackShelfEnum shelf, CharacterEnum hero) {
        COLS = HeroStats.getEquipmentSize(hero)[0];
        ROWS = HeroStats.getEquipmentSize(hero)[1];

        itemSize = WIDTH / COLS;
        grid = new Vector2[COLS][ROWS];
        items = new ItemBlock[COLS * ROWS];

        for (int i=0; i<COLS * ROWS; i++) {
            items[i] = new ItemBlock();
        }

        if (COLS == 2) {
            borders = GraphicsManager.getGuiElement(GuiElementEnum.BACKPACK_GRID_1x2);
        }
        else if (COLS == 4) {
            borders = GraphicsManager.getGuiElement(GuiElementEnum.BACKPACK_GRID_2x4);
        }
        else if (COLS == 6) {
            borders = GraphicsManager.getGuiElement(GuiElementEnum.BACKPACK_GRID_3x6);
        }
    }

    public void cleanUp() {
        for (int i = ROWS * COLS - 2; i >= 0; i--) {
            if (items[i].getItem() == null && items[i + 1].getItem() != null)
                items[i] = items[i + 1];
        }
    }

    public boolean isSpace() {
        for (ItemBlock ib : items) if (ib.getItem() == null) return true;
        return false;
    }

    public void put(AbstractItem item) {
        for (int i=0; i<ROWS * COLS; i++) if (items[i].getItem() == null) {
            items[i].setItem(item);
            return;
        }
    }

    public void drop(AbstractItem item) {
        for (ItemBlock ib : items) {
            if (ib.getItem() == item) ib.setItem(null);
        }
        cleanUp();
    }

    public AbstractItem tap(float x, float y) {
        if (y > grid[0][0].y) {
            for (int i = 0; i < COLS; i++) {
                if (x >= grid[i][0].x && x <= grid[i][0].x + itemSize)
                    return items[i] == null ? null : items[i].getItem();
            }
        } else {
            for (int i = 0; i < COLS; i++) {
                if (x >= grid[i][1].x && x <= grid[i][1].x + itemSize)
                    return items[COLS + i] == null ? null : items[COLS + i].getItem();
            }
        }
        return null;
    }

    public void draw(Batch batch, AbstractItem selectedItem) {
        for (int i = 0; i < COLS * ROWS; i++) {
            items[i].draw(batch, grid[i % COLS][i / COLS], items[i].getItem() == selectedItem, itemSize);
        }
        borders.draw(batch, X, BackpackSegment.getPosY(), WIDTH, HEIGHT);
    }

}
