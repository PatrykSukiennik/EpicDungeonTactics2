package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class Bow extends AbstractItem {

    private int range;
    private int dmg;

    public Bow(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int range, int dmg, ItemRarityEnum rarityEnum) {
        super(itemEnum, typeEnum, value, effects, rarityEnum);
        this.range = range;
        this.dmg = dmg;
    }
}
