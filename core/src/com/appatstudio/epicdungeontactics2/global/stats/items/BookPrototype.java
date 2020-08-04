package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class BookPrototype extends ItemPrototype {

    private final int EFFECT_SLOTS;

    public BookPrototype(ItemEnum id, ItemTypeEnum type, int value, int effects) {
        super(id, type, value);

        this.EFFECT_SLOTS = effects;
    }

    public int getEFFECT_SLOTS() {
        return EFFECT_SLOTS;
    }
}
