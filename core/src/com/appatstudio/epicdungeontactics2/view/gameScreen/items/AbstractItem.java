package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractItem {

    private ItemEnum itemEnum;
    private ItemTypeEnum itemTypeEnum;
    private int value;
    private Array<ItemEffect> effects;
    private ItemRarityEnum rarity;

    public AbstractItem(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, ItemRarityEnum rarity) {
        this.itemEnum = itemEnum;
        this.itemTypeEnum = typeEnum;
        this.value = value;
        this.effects = effects;
        this.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public ItemEnum getItemEnum() {
        return itemEnum;
    }

    public ItemTypeEnum getItemTypeEnum() {
        return itemTypeEnum;
    }

    public Array<ItemEffect> getEffects() {
        return effects;
    }

    public ItemRarityEnum getRarity() {
        return rarity;
    }

    public static ItemTypeEnum getItemTypeEnum(ItemEnum itemEnum) {
        if (itemEnum.toString().startsWith("MELE")) {
            return ItemTypeEnum.MELE;
        }
        else if (itemEnum.toString().startsWith("ARROW")) {
            return ItemTypeEnum.ARROW;
        }
        else if (itemEnum.toString().startsWith("BOW")) {
            return ItemTypeEnum.BOW;
        }
        else if (itemEnum.toString().startsWith("ARMOR")) {
            return ItemTypeEnum.ARMOR;
        }
        else if (itemEnum.toString().startsWith("STAFF")) {
            return ItemTypeEnum.STAFF;
        }
        else if (itemEnum.toString().startsWith("HELMET")) {
            return ItemTypeEnum.HELMET;
        }
        else if (itemEnum.toString().startsWith("FOOD")) {
            return ItemTypeEnum.FOOD;
        }
        else if (itemEnum.toString().startsWith("BOOK")) {
            return ItemTypeEnum.BOOK;
        }
        else if (itemEnum.toString().startsWith("OTHER")) {
            return ItemTypeEnum.OTHER;
        }
        else if (itemEnum.toString().startsWith("SHIELD")) {
            return ItemTypeEnum.SHIELD;
        }
        else if (itemEnum.toString().startsWith("NECKLACE")) {
            return ItemTypeEnum.NECKLACE;
        }
        else if (itemEnum.toString().startsWith("RING")) {
            return ItemTypeEnum.RING;
        }
        return ItemTypeEnum.MELE;
    }
}
