package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public abstract class AbstractItem {

    private ItemEnum itemEnum;
    private ItemTypeEnum itemTypeEnum;
    private int weight;


    public int getWeight() {
        return weight;
    }

    public ItemEnum getItemEnum() {
        return itemEnum;
    }

    public ItemTypeEnum getItemTypeEnum() {
        return itemTypeEnum;
    }
}
