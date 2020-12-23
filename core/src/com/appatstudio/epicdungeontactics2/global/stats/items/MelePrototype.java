package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class MelePrototype extends ItemPrototype {

    @Getter private final int SPEED_EFFECT;
    @Getter private final int DMG;

    public MelePrototype(ItemEnum id, ItemTypeEnum type, int value, int speed, int dmg, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.SPEED_EFFECT = speed;
        this.DMG = dmg;
    }

}
