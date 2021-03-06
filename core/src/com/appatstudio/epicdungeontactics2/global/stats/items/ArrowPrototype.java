package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public final class ArrowPrototype extends ItemPrototype {

    @Getter private final int RANGE_EFFECT;
    @Getter private final int DMG_EFFECT;

    public ArrowPrototype(ItemEnum id, ItemTypeEnum type, int value, int range, int dmg, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.RANGE_EFFECT = range;
        this.DMG_EFFECT = dmg;
    }
    
}
