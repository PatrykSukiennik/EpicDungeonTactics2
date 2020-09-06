package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class ShieldPrototype extends ItemPrototype {

    private final int ARMOR;
    private final int SPEED_EFFECT;

    public ShieldPrototype(ItemEnum id, ItemTypeEnum type, int value, int armor, int speed, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.ARMOR = armor;
        this.SPEED_EFFECT = speed;
    }

    public int getSPEED_EFFECT() {
        return SPEED_EFFECT;
    }

    public int getARMOR() {
        return ARMOR;
    }
}
