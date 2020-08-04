package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class MelePrototype extends ItemPrototype {

    private final int SPEED_EFFECT;
    private final int DMG;

    public MelePrototype(ItemEnum id, ItemTypeEnum type, int value, int speed, int dmg) {
        super(id, type, value);

        this.SPEED_EFFECT = speed;
        this.DMG = dmg;
    }

    public int getDMG() {
        return DMG;
    }

    public int getSPEED_EFFECT() {
        return SPEED_EFFECT;
    }
}
