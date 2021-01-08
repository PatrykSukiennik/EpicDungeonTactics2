package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.AbstractSegment;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class CategoryButton {

    private ItemBackpackShelfEnum category;
    private SpriteDrawable bg;

    CategoryButton(ItemBackpackShelfEnum category) {
        this.category = category;

        switch (category) {
            case FOOD: bg = GraphicsManager.getGuiElement(GuiElementEnum.CATEGORY_BUTTON_FOOD); break;
            case ARMOR: bg = GraphicsManager.getGuiElement(GuiElementEnum.CATEGORY_BUTTON_ARMOR); break;
            case MELE: bg = GraphicsManager.getGuiElement(GuiElementEnum.CATEGORY_BUTTON_MELE); break;
            case DISTANCE: bg = GraphicsManager.getGuiElement(GuiElementEnum.CATEGORY_BUTTON_DISTANCE); break;
        }
    }

    void draw(Batch batch, boolean isSelected, float y) {
        if (!isSelected) {
            batch.getColor().a = 0.5f;
            bg.draw(batch, AbstractSegment.getPosX() + AbstractSegment.getFullWidth()*0.06f, y, CategoryColumn.getW(), CategoryColumn.getSegmentH());
            batch.getColor().a = 1f;
            batch.end();
            batch.begin();
        }
        else bg.draw(batch, AbstractSegment.getPosX() + AbstractSegment.getFullWidth()*0.06f, y, CategoryColumn.getW(), CategoryColumn.getSegmentH());
    }

}
