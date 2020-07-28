package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.AbstractSegment;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class CategoryButton {

    private ItemBackpackShelfEnum category;
    private SpriteDrawable bg = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);

    CategoryButton(ItemBackpackShelfEnum category) {
        this.category = category;
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
