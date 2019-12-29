package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public final class ItemPrototype {

    private final ItemEnum ID;
    private final ItemTypeEnum TYPE;
    private final int VALUE, WEIGHT;

    public ItemPrototype(ItemEnum id, ItemTypeEnum type, int value, int weight) {
        ID = id;
        TYPE = type;
        VALUE = value;
        WEIGHT = weight;
    }

    public int getVALUE() {
        return VALUE;
    }

    public int getWEIGHT() {
        return WEIGHT;
    }

    public ItemEnum getID() {
        return ID;
    }

    public ItemTypeEnum getTYPE() {
        return TYPE;
    }
}
