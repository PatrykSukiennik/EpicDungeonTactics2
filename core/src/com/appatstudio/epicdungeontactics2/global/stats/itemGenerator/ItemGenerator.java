package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.appatstudio.epicdungeontactics2.global.stats.items.ArmorPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.ArrowPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.BookPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.BowPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.FoodPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.HelmetPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.MelePrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.NecklacePrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.RingPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.ShieldPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.StaffPrototype;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Armor;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Arrow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Book;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Bow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Food;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Helmet;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.MeleWeapon;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Necklace;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Ring;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Shield;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Staff;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Set;

public class ItemGenerator {

    private static int itemTypeSum;
    private static HashMap<ItemTypeEnum, Integer> itemSums = new HashMap<>();

    private static int lvl;

    public static AbstractItem getItem() {
        ItemTypeEnum type = getType();
        return createItem(getItemEnum(type), type, true);
    }

    public static AbstractItem getItemNoPerks(ItemEnum item, ItemTypeEnum type) {
        return createItem(getItemEnum(type), type, false);
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

    private static AbstractItem createItem(ItemEnum itemEnum, ItemTypeEnum typeEnum, boolean withPerks) {
        switch (typeEnum) {
            case ARMOR:
                ArmorPrototype prototypeArmor = ItemStatsConfig.basicArmorStats.get(itemEnum);
                ArmorPrototype lvlPrototypeArmor = ItemStatsConfig.lvlArmorStats.get(itemEnum);
                return new Armor(
                        itemEnum,
                        typeEnum,
                        prototypeArmor.getVALUE() + lvl * lvlPrototypeArmor.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeArmor.getARMOR() + lvl * lvlPrototypeArmor.getARMOR(),
                        prototypeArmor.getMOVE_SPEED_COST() + lvl * lvlPrototypeArmor.getMOVE_SPEED_COST()
                );

            case ARROW:
                ArrowPrototype prototypeArrow = ItemStatsConfig.basicArrowStats.get(itemEnum);
                ArrowPrototype lvlPrototypeArrow = ItemStatsConfig.lvlArrowStats.get(itemEnum);
                return new Arrow(
                        itemEnum,
                        typeEnum,
                        prototypeArrow.getVALUE() + lvl * lvlPrototypeArrow.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeArrow.getRANGE_EFFECT() + lvl * lvlPrototypeArrow.getRANGE_EFFECT(),
                        prototypeArrow.getDMG_EFFECT() + lvl * lvlPrototypeArrow.getDMG_EFFECT()
                );

            case BOW:
                BowPrototype prototypeBow = ItemStatsConfig.basicBowStats.get(itemEnum);
                BowPrototype lvlPrototypeBow = ItemStatsConfig.lvlBowStats.get(itemEnum);
                return new Bow(
                        itemEnum,
                        typeEnum,
                        prototypeBow.getVALUE() + lvl * lvlPrototypeBow.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeBow.getRANGE() + lvl * prototypeBow.getRANGE(),
                        prototypeBow.getDMG() + lvl * prototypeBow.getDMG()
                );

            case BOOK:
                BookPrototype prototypeBook = ItemStatsConfig.basicBookStats.get(itemEnum);
                BookPrototype lvlPrototypeBook = ItemStatsConfig.lvlBookStats.get(itemEnum);
                return new Book(
                        itemEnum,
                        typeEnum,
                        prototypeBook.getVALUE() + lvl * lvlPrototypeBook.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        (int)(prototypeBook.getEFFECT_SLOTS() + lvl * 0.1f * lvlPrototypeBook.getEFFECT_SLOTS())
                );

            case FOOD:
                FoodPrototype prototypeFood = ItemStatsConfig.basicFoodStats.get(itemEnum);
                FoodPrototype lvlPrototypeFood = ItemStatsConfig.lvlFoodStats.get(itemEnum);
                return new Food(
                        itemEnum,
                        typeEnum,
                        prototypeFood.getVALUE() + lvl * lvlPrototypeFood.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeFood.getHP_EFFECT() + lvl * lvlPrototypeFood.getHP_EFFECT()
                );


            case HELMET:
                HelmetPrototype prototypeHelmet = ItemStatsConfig.basicHelmetStats.get(itemEnum);
                HelmetPrototype lvlPrototypeHelmet = ItemStatsConfig.lvlHelmetStats.get(itemEnum);
                return new Helmet(
                        itemEnum,
                        typeEnum,
                        prototypeHelmet.getVALUE() + lvl * lvlPrototypeHelmet.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeHelmet.getARMOR() + lvl * lvlPrototypeHelmet.getARMOR()
                );


            case MELE:
                MelePrototype prototypeMele = ItemStatsConfig.basicMeleStats.get(itemEnum);
                MelePrototype lvlPrototypeMele = ItemStatsConfig.lvlMeleStats.get(itemEnum);
                return new MeleWeapon(
                        itemEnum,
                        typeEnum,
                        prototypeMele.getVALUE() + lvl * lvlPrototypeMele.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeMele.getDMG() + lvl * lvlPrototypeMele.getDMG(),
                        prototypeMele.getSPEED_EFFECT() + lvl * lvlPrototypeMele.getSPEED_EFFECT()
                );


            case NECKLACE:
                NecklacePrototype prototypeNecklace = ItemStatsConfig.basicNecklaceStats.get(itemEnum);
                NecklacePrototype lvlPrototypeNecklace = ItemStatsConfig.lvlNecklaceStats.get(itemEnum);
                return new Necklace(
                        itemEnum,
                        typeEnum,
                        prototypeNecklace.getVALUE() + lvl * lvlPrototypeNecklace.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeNecklace.getEFFECTS() + lvl * lvlPrototypeNecklace.getEFFECTS()
                );


            case RING:
                RingPrototype prototypeRing = ItemStatsConfig.basicRingStats.get(itemEnum);
                RingPrototype lvlPrototypeRing = ItemStatsConfig.lvlRingStats.get(itemEnum);
                return new Ring(
                        itemEnum,
                        typeEnum,
                        prototypeRing.getVALUE() + lvl * lvlPrototypeRing.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeRing.getEFFECTS() + lvl * lvlPrototypeRing.getEFFECTS()
                );


            case SHIELD:
                ShieldPrototype prototypeShield = ItemStatsConfig.basicShieldStats.get(itemEnum);
                ShieldPrototype lvlPrototypeShield = ItemStatsConfig.lvlShieldStats.get(itemEnum);
                return new Shield(
                        itemEnum,
                        typeEnum,
                        prototypeShield.getVALUE() + lvl * lvlPrototypeShield.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeShield.getARMOR() + lvl * lvlPrototypeShield.getARMOR(),
                        prototypeShield.getSPEED_EFFECT() + lvl * lvlPrototypeShield.getSPEED_EFFECT()
                );


            case STAFF:
                StaffPrototype prototypeStaff = ItemStatsConfig.basicStaffStats.get(itemEnum);
                StaffPrototype lvlPrototypeStaff = ItemStatsConfig.lvlStaffStats.get(itemEnum);
                return new Staff(
                        itemEnum,
                        typeEnum,
                        prototypeStaff.getVALUE() + lvl * lvlPrototypeStaff.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        generateSpell(),
                        prototypeStaff.getDMG() + lvl * lvlPrototypeStaff.getDMG(),
                        prototypeStaff.getSPELL_CHANCE() + lvl * lvlPrototypeStaff.getSPELL_CHANCE(),
                        prototypeStaff.getSPEED_EFFECT() + lvl * lvlPrototypeStaff.getSPEED_EFFECT()
                );


            default: return null;
        }

    }

    private static AbstractItem createItemNoPerks(ItemEnum itemEnum, ItemTypeEnum typeEnum) {




        return null;
    }

    private static SpellEnum generateSpell() {
        return null;
    }

    private static Array<ItemEffect> generateEffects(ItemTypeEnum type) {
        return null;
    }



}
