package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractItem {

    private ItemEnum itemEnum;
    private ItemTypeEnum itemTypeEnum;
    private int value;
    private Array<ItemEffect> effects;
    private ItemRarityEnum rarity;

    public AbstractItem(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, ItemRarityEnum rarity) {
        this.itemEnum = itemEnum;
        this.itemTypeEnum = typeEnum;
        this.value = value;
        this.effects = effects;
        this.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public ItemEnum getItemEnum() {
        return itemEnum;
    }

    public ItemTypeEnum getItemTypeEnum() {
        return itemTypeEnum;
    }

    public Array<ItemEffect> getEffects() {
        return effects;
    }

    public ItemRarityEnum getRarity() {
        return rarity;
    }
}
