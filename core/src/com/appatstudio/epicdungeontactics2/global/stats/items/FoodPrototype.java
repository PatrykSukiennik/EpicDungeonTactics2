package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class FoodPrototype extends ItemPrototype {

    private final int HP_EFFECT;
    private final int MP_EFFECT;

    public FoodPrototype(ItemEnum id, ItemTypeEnum type, int value, int hp, int mp, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.HP_EFFECT = hp;
        this.MP_EFFECT = mp;
    }

    public int getHP_EFFECT() {
        return HP_EFFECT;
    }

    public int getMP_EFFECT() {
        return MP_EFFECT;
    }
}
