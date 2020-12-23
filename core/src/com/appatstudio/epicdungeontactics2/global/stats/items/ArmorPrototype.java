package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class ArmorPrototype extends ItemPrototype {

    @Getter private final int ARMOR;
    @Getter private final int MOVE_SPEED_COST;

    public ArmorPrototype(ItemEnum id, ItemTypeEnum type, int value, int armor, int moveSpeedCost, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);
        this.ARMOR = armor;
        this.MOVE_SPEED_COST = moveSpeedCost;
    }


}
