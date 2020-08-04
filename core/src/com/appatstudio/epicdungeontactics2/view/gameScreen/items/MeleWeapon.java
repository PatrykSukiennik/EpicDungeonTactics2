package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class MeleWeapon extends AbstractItem {

    private int speedEffect;
    private int dmg;

    public MeleWeapon(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int speedEffect, int dmg) {
        super(itemEnum, typeEnum, value, effects);
        this.speedEffect = speedEffect;
        this.dmg = dmg;
    }
}
