package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public class Food extends AbstractItem {

    private int hpEffect;
    private int mpEffect;

    public Food(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int hpEffect, int mpEffect, ItemRarityEnum rarityEnum) {
        super(itemEnum, typeEnum, value, effects, rarityEnum);
        this.hpEffect = hpEffect;
        this.mpEffect = mpEffect;
    }

    public int getHpEffect() {
        return hpEffect;
    }

    public int getMpEffect() {
        return mpEffect;
    }
}
