package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class ItemPrototype {

    @Getter private final ItemEnum ID;
    @Getter private final ItemTypeEnum TYPE;
    @Getter private final int VALUE;
    @Getter private final ItemRarityEnum RARITY;

    public ItemPrototype(ItemEnum id, ItemTypeEnum type, int value, ItemRarityEnum rarity) {
        ID = id;
        TYPE = type;
        VALUE = value;
        RARITY = rarity;
    }

}
