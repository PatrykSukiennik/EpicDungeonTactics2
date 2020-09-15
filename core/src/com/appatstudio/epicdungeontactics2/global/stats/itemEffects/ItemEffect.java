package com.appatstudio.epicdungeontactics2.global.stats.itemEffects;

import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEffectEnum;

public class ItemEffect {

    private ItemEffectEnum effectEnum;
    private float power;

    public ItemEffect(ItemEffectEnum effectEnum, float power) {
        this.effectEnum = effectEnum;
        this.power = power;
    }

    public ItemEffectEnum getEffectEnum() {
        return effectEnum;
    }

    public float getPower() {
        return power;
    }
}
