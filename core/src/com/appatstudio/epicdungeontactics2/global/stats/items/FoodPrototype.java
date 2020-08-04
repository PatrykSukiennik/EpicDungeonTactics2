package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class FoodPrototype extends ItemPrototype {

    private final int HP_EFFECT;

    public FoodPrototype(ItemEnum id, ItemTypeEnum type, int value, int hp) {
        super(id, type, value);

        this.HP_EFFECT = hp;
    }

    public int getHP_EFFECT() {
        return HP_EFFECT;
    }
}
