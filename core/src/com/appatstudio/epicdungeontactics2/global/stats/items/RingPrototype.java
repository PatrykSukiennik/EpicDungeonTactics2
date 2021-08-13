package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class RingPrototype extends ItemPrototype {

    @Getter private final int EFFECTS;

    public RingPrototype(ItemEnum id, ItemTypeEnum type, int value, int effects, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.EFFECTS = effects;
    }

    public int getEFFECTS() {
        return EFFECTS;
    }
}
