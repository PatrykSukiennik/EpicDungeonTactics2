package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class Arrow extends AbstractItem {

    private int rangeEffect;
    private int dmgEffect;

    public Arrow(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int rangeEffect, int dmgEffect) {
        super(itemEnum, typeEnum, value, effects);
        this.rangeEffect = rangeEffect;
        this.dmgEffect = dmgEffect;
    }
}
