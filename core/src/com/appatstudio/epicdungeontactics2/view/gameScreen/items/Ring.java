package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class Ring extends AbstractItem {

    private int itemSlots;

    public Ring(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int itemSlots) {
        super(itemEnum, typeEnum, value, effects);
        this.itemSlots = itemSlots;
    }

    public int getItemSlots() {
        return itemSlots;
    }
}
