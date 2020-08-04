package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class BowPrototype extends ItemPrototype {

    private final int RANGE;
    private final int DMG;

    public BowPrototype(ItemEnum id, ItemTypeEnum type, int value, int range, int dmg) {
        super(id, type, value);

        this.RANGE = range;
        this.DMG = dmg;
    }

    public int getRANGE() {
        return RANGE;
    }

    public int getDMG() {
        return DMG;
    }
}
