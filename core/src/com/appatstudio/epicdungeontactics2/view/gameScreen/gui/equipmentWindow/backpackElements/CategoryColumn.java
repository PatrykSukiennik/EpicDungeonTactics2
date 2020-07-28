package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.BackpackSegment;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class CategoryColumns {

    private static final float W = AbstractSegment.getFullWidth() * 0.2f;
    private static final ItemBackpackShelfEnum[] categories = ItemBackpackShelfEnum.values();
    private static final float segmentH = AbstractSegment.getFullHeight() / categories.length;
    private static final HashMap<ItemBackpackShelfEnum, CategoryButton> categoryButtons = new HashMap<>();

    static {
        for (ItemBackpackShelfEnum category : categories) {
            categoryButtons.put(category, new CategoryButton(category));
        }

    }

    public CategoryColumns() {




    }

    public void draw(Batch batch, ItemBackpackShelfEnum selectedCategory) {
        categoryButtons.get(ItemBackpackShelfEnum.MELE).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.MELE,
                BackpackSegment.getPosY() + segmentH * 3);

        categoryButtons.get(ItemBackpackShelfEnum.DISTANCE).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.DISTANCE,
                BackpackSegment.getPosY() + segmentH * 2);

        categoryButtons.get(ItemBackpackShelfEnum.ARMOR).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.ARMOR,
                BackpackSegment.getPosY() + segmentH);

        categoryButtons.get(ItemBackpackShelfEnum.FOOD).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.FOOD,
                BackpackSegment.getPosY());
    }

    public static float getW() {
        return W;
    }

    public static float getSegmentH() {
        return segmentH;
    }

    public ItemBackpackShelfEnum getCategory(float y) {
        return null;
    }
}
