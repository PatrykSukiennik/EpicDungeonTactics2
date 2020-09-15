package com.appatstudio.epicdungeontactics2.view.gameScreen.playerStatus;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

public final class Backpack {

    private static Map<ItemTypeEnum, Array<AbstractItem>> items;
    private static int maxCapacity, currentCapacity;

    public static Array<AbstractItem> getItemArray(ItemTypeEnum typeEnum) {
        return items.get(typeEnum);
    }

    public static void addItem(AbstractItem item) {
        items.get(item.getItemTypeEnum()).add(item);
        currentCapacity += item.getValue();
    }

    public static void deleteItem(AbstractItem item) {
        currentCapacity -= item.getValue();
        items.get(item.getItemTypeEnum()).removeValue(item, true);
    }

}
