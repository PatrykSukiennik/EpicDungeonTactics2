package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.BackpackSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class BackpackPage {

    private final int COLS;
    private final int ROWS;

    private static final float WIDTH = AbstractSegment.getFullWidth();
    private static final float HEIGHT = AbstractSegment.getFullHeight();
    private static final float X = AbstractSegment.getPosX();

    private final float itemSize;
    private CoordsFloat[][] grid;
    private ItemBlock[] items;
    private static SpriteDrawable borders;

    private CategoryColumn categoryColumn;

    public BackpackPage(ItemBackpackShelfEnum shelf, CharacterEnum hero) {
        COLS = HeroStats.getEquipmentSize(hero)[0];
        ROWS = HeroStats.getEquipmentSize(hero)[1];

        grid = new CoordsFloat[COLS][ROWS];

        if (COLS == 2) {
            itemSize = (41f/50f) * HEIGHT;
            grid[0][0] = new CoordsFloat(X + (35f/124f) * WIDTH, BackpackSegment.getPosY() + (6f/50f) * HEIGHT);
            grid[1][0]  = new CoordsFloat(X + (77f/124f) * WIDTH, BackpackSegment.getPosY() + (6f/50f) * HEIGHT);
        }
        else if (COLS == 4) {
            itemSize = (20f/50f) * HEIGHT;
            grid[0][0] = new CoordsFloat(X + (35f/124f) * WIDTH, BackpackSegment.getPosY() + (27f/50f) * HEIGHT);
            grid[1][0] = new CoordsFloat(X + (56f/124f) * WIDTH, BackpackSegment.getPosY() + (27f/50f) * HEIGHT);
            grid[2][0] = new CoordsFloat(X + (77f/124f) * WIDTH, BackpackSegment.getPosY() + (27f/50f) * HEIGHT);
            grid[3][0] = new CoordsFloat(X + (98f/124f) * WIDTH, BackpackSegment.getPosY() + (27f/50f) * HEIGHT);
            grid[0][1] = new CoordsFloat(X + (35f/124f) * WIDTH, BackpackSegment.getPosY() + (6f/50f) * HEIGHT);
            grid[1][1] = new CoordsFloat(X + (56f/124f) * WIDTH, BackpackSegment.getPosY() + (6f/50f) * HEIGHT);
            grid[2][1] = new CoordsFloat(X + (77f/124f) * WIDTH, BackpackSegment.getPosY() + (6f/50f) * HEIGHT);
            grid[3][1] = new CoordsFloat(X + (98f/124f) * WIDTH, BackpackSegment.getPosY() + (6f/50f) * HEIGHT);
        }
        else { //todo maybe
            itemSize = 0;
        }

        items = new ItemBlock[COLS * ROWS];

        for (int i=0; i<COLS * ROWS; i++) {
            items[i] = new ItemBlock(grid[i % COLS][i / COLS], itemSize);
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
        for (int i=0; i < ROWS * COLS - 1; i++) {
            if (items[i].getItem() == null && items[i + 1].getItem() != null) {
                items[i].setItem(items[i + 1].getItem());
                items[i + 1].setItem(null);
            }
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
        borders.draw(batch, X, BackpackSegment.getPosY(), WIDTH, HEIGHT);
        for (int i = 0; i < COLS * ROWS; i++) {
            items[i].draw(batch, selectedItem, 1f);
        }
    }

    public void replace(AbstractItem toReplace, AbstractItem newItem) {
        for (ItemBlock i : items) {
            if (i != null && i.getItem() == toReplace) {
                i.setItem(toReplace);
                return;
            }
        }
    }

    public void itemUsed(AbstractItem item) {
        for (ItemBlock i : items) {
            if (i != null && i.getItem() == item) {
                i.setItem(null);
                return;
            }
        }
    }

    public void dropItem(AbstractItem currItem) {
        for (ItemBlock i : items) {
            if (i != null && i.getItem() == currItem) {
                i.setItem(null);
                cleanUp();
                return;
            }
        }
    }
}
