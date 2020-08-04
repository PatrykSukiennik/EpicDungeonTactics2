package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class NecklacePrototype extends ItemPrototype {

    private final int EFFECTS;

    public NecklacePrototype(ItemEnum id, ItemTypeEnum type, int value, int effects) {
        super(id, type, value);

        this.EFFECTS = effects;
    }

    public int getEFFECTS() {
        return EFFECTS;
    }
}
