package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ItemBlock extends Image {

    AbstractItem item;

    public ItemBlock(Vector2 pos, float size) {

    }

    public AbstractItem getItem() {
        return item;
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }

    public void draw(Batch batch, boolean isSelected) {
        if (item != null) super.draw(batch, 1f);
    }

    public boolean isTap(float x, float y) {
        return  (x > this.getX() && x < this.getX() + this.getWidth()
                && y > this.getY() && y < this.getY() + this.getHeight());
    }
}
