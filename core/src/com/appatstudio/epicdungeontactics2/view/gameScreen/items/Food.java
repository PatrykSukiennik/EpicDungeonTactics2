package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public class Food extends AbstractItem {

    private int hpEffect;

    public Food(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int hpEffect) {
        super(itemEnum, typeEnum, value, effects);
        this.hpEffect = hpEffect;
    }

    public int getHpEffect() {
        return hpEffect;
    }
}
