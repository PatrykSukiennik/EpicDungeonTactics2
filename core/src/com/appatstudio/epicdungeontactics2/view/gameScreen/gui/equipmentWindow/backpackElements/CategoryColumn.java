package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.BackpackSegment;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class CategoryColumn {

    private static final float W = AbstractSegment.getFullWidth() * 0.2f;
    private static final ItemBackpackShelfEnum[] categories = ItemBackpackShelfEnum.values();
    private static final float segmentH = AbstractSegment.getFullHeight() * 0.82f / categories.length;
    private static final HashMap<ItemBackpackShelfEnum, CategoryButton> categoryButtons = new HashMap<>();

    static {
        for (ItemBackpackShelfEnum category : categories) {
            categoryButtons.put(category, new CategoryButton(category));
        }

    }

    public CategoryColumn() {




    }

    public void draw(Batch batch, ItemBackpackShelfEnum selectedCategory) {
        categoryButtons.get(ItemBackpackShelfEnum.MELE).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.MELE,
                BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f + segmentH * 3);

        categoryButtons.get(ItemBackpackShelfEnum.DISTANCE).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.DISTANCE,
                BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f + segmentH * 2);

        categoryButtons.get(ItemBackpackShelfEnum.ARMOR).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.ARMOR,
                BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f + segmentH);

        categoryButtons.get(ItemBackpackShelfEnum.FOOD).draw(
                batch, selectedCategory == ItemBackpackShelfEnum.FOOD,
                BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f);
    }

    public static float getW() {
        return W;
    }

    public static float getSegmentH() {
        return segmentH;
    }

    public ItemBackpackShelfEnum getCategory(float y) {
        if (y < BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f + segmentH) return ItemBackpackShelfEnum.FOOD;
        else if (y < BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f + segmentH * 2) return ItemBackpackShelfEnum.ARMOR;
        else if (y < BackpackSegment.getPosY() + AbstractSegment.getFullHeight() * 0.12f + segmentH * 3) return ItemBackpackShelfEnum.DISTANCE;
        else return ItemBackpackShelfEnum.MELE;
    }

}
