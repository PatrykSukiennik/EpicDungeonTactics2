package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class ItemPrototype {

    private final ItemEnum ID;
    private final ItemTypeEnum TYPE;
    private final int VALUE;
    private final ItemRarityEnum RARITY;

    public ItemPrototype(ItemEnum id, ItemTypeEnum type, int value, ItemRarityEnum rarity) {
        ID = id;
        TYPE = type;
        VALUE = value;
        RARITY = rarity;
    }

    public int getVALUE() {
        return VALUE;
    }

    public ItemEnum getID() {
        return ID;
    }

    public ItemTypeEnum getTYPE() {
        return TYPE;
    }

    public ItemRarityEnum getRARITY() {
        return RARITY;
    }
}
