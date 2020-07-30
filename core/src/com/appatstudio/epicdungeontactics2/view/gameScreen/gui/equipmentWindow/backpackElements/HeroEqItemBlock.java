package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HeroEqItemBlock extends ItemBlock {

    private Image bg;
    private ItemTypeEnum requiredCategory;

    public HeroEqItemBlock(Vector2 pos, float size, ItemTypeEnum requiredCategory) {
        super(pos, size);
        bg = new Image(GraphicsManager.getItemCategoryIcon(requiredCategory));
        bg.setSize(size, size);
        bg.setPosition(pos.x, pos.y);

        this.requiredCategory = requiredCategory;
    }

    @Override
    public void draw(Batch batch, boolean isSelected) {
        bg.draw(batch, 1f);
        super.draw(batch, isSelected);
    }

    @Override
    public boolean isTap(float x, float y) {
        return super.isTap(x, y);
    }

    public boolean isTapWithItem(float x, float y, AbstractItem item) {
        if (item.getItemTypeEnum() == requiredCategory) {
            return isTap(x, y);
        }
        return false;
    }

    public ItemTypeEnum getRequiredItem() {
        return requiredCategory;
    }
}
