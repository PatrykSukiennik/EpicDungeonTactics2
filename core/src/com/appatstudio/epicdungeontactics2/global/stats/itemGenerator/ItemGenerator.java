package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;

import java.util.HashMap;
import java.util.Set;

public class ItemGenerator {

    private static int itemTypeSum;
    private static HashMap<ItemTypeEnum, Integer> itemSums = new HashMap<>();

    private static int lvl;

    public static AbstractItem getItem() {
        return createItem(getItemEnum(getType()));
    }

    public static void refresh() {
        itemTypeSum = 0;
        lvl = StatTracker.getLvl();

        for (Integer i : ItemGeneratorConfig.basicTypeChance.get(StatTracker.getHero()).values()) {
            itemTypeSum += i;
        }
        for (Integer i : ItemGeneratorConfig.lvlTypeChance.get(StatTracker.getHero()).values()) {
            itemTypeSum += i * lvl;
        }

        for (ItemTypeEnum t : ItemTypeEnum.values()) {
            itemSums.put(t, 0);
            for (ItemEnum i : ItemGeneratorConfig.basicItemChance.get(t).keySet()) {
                itemSums.put(t, itemSums.get(t) + ItemGeneratorConfig.basicItemChance.get(t).get(i));
            }
        }

        for (ItemTypeEnum t : ItemTypeEnum.values()) {
            for (ItemEnum i : ItemGeneratorConfig.basicItemChance.get(t).keySet()) {
                itemSums.put(t, itemSums.get(t) + (lvl * ItemGeneratorConfig.lvlItemChance.get(t).get(i)));
            }
        }

    }

    private static ItemTypeEnum getType() {
        int temp = 0;
        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % itemTypeSum;

        ItemTypeEnum[] types = ItemTypeEnum.values();

        for (ItemTypeEnum type : types) {
            temp += ItemGeneratorConfig.basicTypeChance.get(StatTracker.getHero()).get(type) +
                    lvl * ItemGeneratorConfig.lvlTypeChance.get(StatTracker.getHero()).get(type);

            if (result <= temp) return type;
        }
        return types[0];
    }

    private static ItemEnum getItemEnum(ItemTypeEnum type) {
        int temp = 0;
        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % itemTypeSum;

        Set<ItemEnum> items = ItemGeneratorConfig.basicItemChance.get(type).keySet();

        for (ItemEnum item : items) {
            temp += ItemGeneratorConfig.basicItemChance.get(type).get(item) +
                    lvl * ItemGeneratorConfig.lvlItemChance.get(type).get(item);

            if (result <= temp) return item;
        }
        return (ItemEnum) items.toArray()[0];
    }

    private static AbstractItem createItem(ItemEnum itemEnum) {
        return null;
    }

}
