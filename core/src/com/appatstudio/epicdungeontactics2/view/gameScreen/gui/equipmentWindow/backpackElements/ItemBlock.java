package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ItemBlock {

    AbstractItem item;


    public AbstractItem getItem() {
        return item;
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }

    public void draw(Batch batch, Vector2 pos, boolean isSelected, float size) {

    }
}
