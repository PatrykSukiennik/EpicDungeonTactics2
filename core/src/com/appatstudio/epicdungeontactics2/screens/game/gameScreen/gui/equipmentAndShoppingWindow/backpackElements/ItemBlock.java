package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class ItemBlock extends Image {

    private AbstractItem item = null;
    private CoordsFloat pos;
    private float size;
    private SpriteDrawable bg;
    private static SpriteDrawable selectedSprite = GraphicsManager.getGuiElement(GuiElementEnum.SELECTED_ITEM);

    public ItemBlock(CoordsFloat pos, float size) {
        super();
        this.size = size;
        this.pos = pos;
        this.setSize(size * 0.6f, size * 0.6f);
        this.setPosition(pos.x + size * 0.2f, pos.y + size * 0.2f);
    }

    public AbstractItem getItem() {
        return item;
    }

    public void setItem(AbstractItem newItem) {
        this.item = newItem;

        if (newItem == null) {
            this.setDrawable(GraphicsManager.getGuiElement(GuiElementEnum.NONE));
        }

        if (newItem != null) {
            this.setDrawable(GraphicsManager.getItemImage(newItem.getItemEnum()));
            this.bg = GraphicsManager.getItemRaritySprite(item.getRarity());
        }
    }

    public void draw(Batch batch, AbstractItem selectedItem, float alpha) {
        batch.getColor().a = alpha;
        if (item != null) {
            bg.draw(batch, pos.x, pos.y, size, size);
        }

        super.draw(batch, alpha);

        if (item != null && selectedItem == item) selectedSprite.draw(batch, pos.x, pos.y, size, size);
        batch.getColor().a = 1f;
    }

    public boolean isTap(float x, float y) {
        return  (x > this.getX() && x < this.getX() + this.getWidth()
                && y > this.getY() && y < this.getY() + this.getHeight());
    }
}
