package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class ShieldPrototype extends ItemPrototype {

    @Getter private final int ARMOR;
    @Getter private final int SPEED_EFFECT;

    public ShieldPrototype(ItemEnum id, ItemTypeEnum type, int value, int armor, int speed, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.ARMOR = armor;
        this.SPEED_EFFECT = speed;
    }

}
