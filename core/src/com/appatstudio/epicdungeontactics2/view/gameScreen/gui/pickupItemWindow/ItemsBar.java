package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.pickupItemWindow;

import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements.ItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public class ItemsBar {

    private int page;
    private Array<ItemBlock> items;

    private Image leftArrow, rightArrow;

    private final CoordsFloat leftBottom;
    private final float SIZE;

    private ItemBlock blocks;


    public ItemsBar() {
        items = new Array<>();

        int ITEMS_IN_ROW = 5;
        SIZE = AbstractSegment.getFullWidth() / ITEMS_IN_ROW;

        leftBottom = new CoordsFloat(
                    Gdx.graphics.getWidth()/2f,
                    Gdx.graphics.getHeight()/2f - SIZE/2f);
    }

    public void setItems(Array<AbstractItem> itemsArray) {
        items.clear();
        float startX = -1;
        for (int i=0; i<itemsArray.size; i++) {
            if (itemsArray.size - i > 5) {
                items.add(new ItemBlock(
                    new CoordsFloat(
                        leftBottom.x + (i % 5) * SIZE,
                            leftBottom.y + (int)(i / 5) * SIZE
                    ), SIZE
                ));
            }
            else {
                if (startX == -1) {
                    startX = Gdx.graphics.getWidth()/2f - ((itemsArray.size - i) * SIZE) / 2f;
                }
                items.add(new ItemBlock(
                        new CoordsFloat(
                                startX + (i % 5) * SIZE,
                                leftBottom.y + (int)(i / 5) * SIZE
                        ), SIZE
                ));
            }
            items.get(i).setItem(itemsArray.get(i));
        }

    }

    public float getSIZE() {
        return SIZE;
    }

    void draw(Batch batch, AbstractItem selectedItem) {
        for (ItemBlock b : items) {
            b.draw(batch, selectedItem,1f);
        }
    }

    public AbstractItem tap(float x, float y, AbstractItem selectedItem) {
        for (ItemBlock b : items) {
            if (b.isTap(x, y)) {
                return selectedItem == b.getItem() ? null : b.getItem();
            }
        }
        return null;
    }

    public float getTopY() {
        return items.get(items.size-1).getY() + SIZE;
    }
}
