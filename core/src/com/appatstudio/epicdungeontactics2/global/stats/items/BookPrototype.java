package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class BookPrototype extends ItemPrototype {

    private final int EXP;

    public BookPrototype(ItemEnum id, ItemTypeEnum type, int value, int exp, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.EXP = exp;
    }

    public int getEXP() {
        return EXP;
    }
}
