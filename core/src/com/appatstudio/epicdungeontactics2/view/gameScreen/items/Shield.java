package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class Shield extends AbstractItem {

    private int armor;
    private int speedEffect;

    public Shield(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int armor, int speedEffect, ItemRarityEnum rarityEnum) {
        super(itemEnum, typeEnum, value, effects, rarityEnum);
        this.armor = armor;
        this.speedEffect = speedEffect;
    }
}
