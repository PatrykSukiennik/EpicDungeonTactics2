package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class FoodPrototype extends ItemPrototype {

    @Getter private final int HP_EFFECT;
    @Getter
    private final int MP_EFFECT;

    public FoodPrototype(ItemEnum id, ItemTypeEnum type, int value, int hp, int mp, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.HP_EFFECT = hp;
        this.MP_EFFECT = mp;
    }
}
