package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class BowPrototype extends ItemPrototype {

    @Getter private final int RANGE;
    @Getter private final int DMG;

    public BowPrototype(ItemEnum id, ItemTypeEnum type, int value, int range, int dmg, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.RANGE = range;
        this.DMG = dmg;
    }

}
