package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class HelmetPrototype extends ItemPrototype {

    @Getter private final int ARMOR;

    public HelmetPrototype(ItemEnum id, ItemTypeEnum type, int value, int armor, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.ARMOR = armor;
    }

}
