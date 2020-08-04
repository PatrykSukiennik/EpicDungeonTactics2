package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class Armor extends AbstractItem {

    private int armor;
    private int moveSpeedCost;


    public Armor(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, int armor, int moveSpeedCost) {
        super(itemEnum, typeEnum, value, effects);
        this.armor = armor;
        this.moveSpeedCost = moveSpeedCost;
    }

    public int getArmor() {
        return armor;
    }

    public int getMoveSpeedCost() {
        return moveSpeedCost;
    }
}
