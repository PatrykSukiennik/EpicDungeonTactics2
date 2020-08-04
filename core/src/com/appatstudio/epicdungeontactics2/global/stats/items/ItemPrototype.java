package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class ItemPrototype {

    private final ItemEnum ID;
    private final ItemTypeEnum TYPE;
    private final int VALUE;

    public ItemPrototype(ItemEnum id, ItemTypeEnum type, int value) {
        ID = id;
        TYPE = type;
        VALUE = value;
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
}
