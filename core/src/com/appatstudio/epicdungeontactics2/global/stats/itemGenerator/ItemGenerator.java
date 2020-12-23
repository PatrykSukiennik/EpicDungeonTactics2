package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
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
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Armor;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Arrow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Book;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Bow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Food;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Helmet;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Key;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.MeleWeapon;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Necklace;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Ring;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Shield;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Staff;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Set;

public class ItemGenerator {

    private static int itemTypeSum;
    private static int itemEffectSum;
    private static HashMap<ItemTypeEnum, Integer> itemSums = new HashMap<>();

    private static int lvl;

    public static AbstractItem getItem() {
        ItemTypeEnum type = getType();
        return createItem(getItemEnum(type), type, true);
    }

    public static AbstractItem getItem(ItemEnum itemEnum) {
        return createItem(itemEnum, AbstractItem.getItemTypeEnum(itemEnum), true);
    }

    public static AbstractItem getItemNoPerks(ItemEnum item, ItemTypeEnum type) {
        return createItem(getItemEnum(type), type, false);
    }

    public static void refresh() {
        itemTypeSum = 0;
        lvl = (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL);

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
        if (itemTypeSum == 0) refresh();
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
                        prototypeArmor.getMOVE_SPEED_COST() + lvl * lvlPrototypeArmor.getMOVE_SPEED_COST(),
                        prototypeArmor.getRARITY()
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
                        prototypeArrow.getDMG_EFFECT() + lvl * lvlPrototypeArrow.getDMG_EFFECT(),
                        prototypeArrow.getRARITY()
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
                        prototypeBow.getDMG() + lvl * prototypeBow.getDMG(),
                        prototypeBow.getRARITY()
                );

            case BOOK:
                BookPrototype prototypeBook = ItemStatsConfig.basicBookStats.get(itemEnum);
                BookPrototype lvlPrototypeBook = ItemStatsConfig.lvlBookStats.get(itemEnum);
                return new Book(
                        itemEnum,
                        typeEnum,
                        prototypeBook.getVALUE() + lvl * lvlPrototypeBook.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        (int)(prototypeBook.getEXP() + lvl * 0.1f * lvlPrototypeBook.getEXP()),
                        prototypeBook.getRARITY()
                );

            case FOOD:
                FoodPrototype prototypeFood = ItemStatsConfig.basicFoodStats.get(itemEnum);
                FoodPrototype lvlPrototypeFood = ItemStatsConfig.lvlFoodStats.get(itemEnum);
                return new Food(
                        itemEnum,
                        typeEnum,
                        prototypeFood.getVALUE() + lvl * lvlPrototypeFood.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeFood.getHP_EFFECT() + lvl * lvlPrototypeFood.getHP_EFFECT(),
                        prototypeFood.getMP_EFFECT() + lvl * lvlPrototypeFood.getMP_EFFECT(),
                        prototypeFood.getRARITY()
                );


            case HELMET:
                HelmetPrototype prototypeHelmet = ItemStatsConfig.basicHelmetStats.get(itemEnum);
                HelmetPrototype lvlPrototypeHelmet = ItemStatsConfig.lvlHelmetStats.get(itemEnum);
                return new Helmet(
                        itemEnum,
                        typeEnum,
                        prototypeHelmet.getVALUE() + lvl * lvlPrototypeHelmet.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeHelmet.getARMOR() + lvl * lvlPrototypeHelmet.getARMOR(),
                        prototypeHelmet.getRARITY()
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
                        prototypeMele.getSPEED_EFFECT() + lvl * lvlPrototypeMele.getSPEED_EFFECT(),
                        prototypeMele.getRARITY()
                );


            case NECKLACE:
                NecklacePrototype prototypeNecklace = ItemStatsConfig.basicNecklaceStats.get(itemEnum);
                NecklacePrototype lvlPrototypeNecklace = ItemStatsConfig.lvlNecklaceStats.get(itemEnum);
                return new Necklace(
                        itemEnum,
                        typeEnum,
                        prototypeNecklace.getVALUE() + lvl * lvlPrototypeNecklace.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeNecklace.getEFFECTS() + lvl * lvlPrototypeNecklace.getEFFECTS(),
                        prototypeNecklace.getRARITY()
                );


            case RING:
                RingPrototype prototypeRing = ItemStatsConfig.basicRingStats.get(itemEnum);
                RingPrototype lvlPrototypeRing = ItemStatsConfig.lvlRingStats.get(itemEnum);
                return new Ring(
                        itemEnum,
                        typeEnum,
                        prototypeRing.getVALUE() + lvl * lvlPrototypeRing.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeRing.getEFFECTS() + lvl * lvlPrototypeRing.getEFFECTS(),
                        prototypeRing.getRARITY()
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
                        prototypeShield.getSPEED_EFFECT() + lvl * lvlPrototypeShield.getSPEED_EFFECT(),
                        prototypeShield.getRARITY()
                );


            case STAFF:
                StaffPrototype prototypeStaff = ItemStatsConfig.basicStaffStats.get(itemEnum);
                StaffPrototype lvlPrototypeStaff = ItemStatsConfig.lvlStaffStats.get(itemEnum);
                return new Staff(
                        itemEnum,
                        typeEnum,
                        prototypeStaff.getVALUE() + lvl * lvlPrototypeStaff.getVALUE(),
                        withPerks ? generateEffects(typeEnum) : new Array<ItemEffect>(),
                        prototypeStaff.getSPELL(),
                        prototypeStaff.getDMG() + lvl * lvlPrototypeStaff.getDMG(),
                        prototypeStaff.getSPELL_CHANCE() + lvl * lvlPrototypeStaff.getSPELL_CHANCE(),
                        prototypeStaff.getSPEED_EFFECT() + lvl * lvlPrototypeStaff.getSPEED_EFFECT(),
                        prototypeStaff.getRARITY()
                );


            case OTHER:
                return new Key(
                        itemEnum,
                        typeEnum,
                        30,
                        null,
                        ItemRarityEnum.WHITE
                );


            default: {
                return null;
            }
        }

    }

    private static AbstractItem createItemNoPerks(ItemEnum itemEnum, ItemTypeEnum typeEnum) {




        return null;
    }

    private static SpellEnum generateSpell() {
        return null;
    }

    private static Array<ItemEffect> generateEffects(ItemTypeEnum type) {
        for (Integer i : ItemEffectsConfig.effectChance.get(type).values()) {
            itemEffectSum += i;
        }

        Array<ItemEffect> effects = new Array<>();
        switch (type) {

            case RING:
            case NECKLACE:
                for (int i=0; i<4; i++) {
                    if (EpicDungeonTactics.random.nextFloat() < 1){//0.08f * StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL)) {
                        effects.add(getEffect(effects, type));
                    }
                }
                break;
            case ARMOR:
            case STAFF:
            case SHIELD:
            case MELE:
            case HELMET:
            case BOW:
                for (int i=0; i<4; i++) {
                    if (EpicDungeonTactics.random.nextFloat() < 1){//0.06f * SavedInfoManager.getPerkLvl(GameScreen.getPerk())) {
                        effects.add(getEffect(effects, type));
                    }
                }
                break;
            case ARROW:
                for (int i=0; i<4; i++) {
                    if (EpicDungeonTactics.random.nextFloat() < 1){//0.03f * SavedInfoManager.getCharacterLvl(GameScreen.getHero())) {
                        effects.add(getEffect(effects, type));
                    }
                }
            case BOOK:
            case FOOD:
            case OTHER:
                break;
        }
        return effects;
    }

    private static ItemEffect getEffect(Array<ItemEffect> effects, ItemTypeEnum typeEnum) {
            if (itemEffectSum == 0) refresh();
            int temp = 0;
            int result = Math.abs(EpicDungeonTactics.random.nextInt()) % itemEffectSum;
            ItemEffectEnum effectEnum = ItemEffectEnum.DEX_BONUS;
            ItemEffectEnum[] allEffects = ItemEffectEnum.values();

            for (ItemEffectEnum effect : allEffects) {
                temp += ItemEffectsConfig.effectChance.get(typeEnum).get(effect);

                if (result <= temp && !isEffectInArray(effects, effect)) {
                    effectEnum = effect;
                    break;
                }
            }
            //if (effectEnum == null) effectEnum = ItemEffectEnum.PERCENT_HP_REGEN_KILL;

            return new ItemEffect(
                    effectEnum,
                    EpicDungeonTactics.random.nextFloat()
                            * (ItemEffectsConfig.basicEffectPower.get(effectEnum)
                            + SavedInfoManager.getCharacterLvl(GameScreen.getInstance().getHero()) * ItemEffectsConfig.lvlEffectPower.get(effectEnum))
            );
    }

    private static boolean isEffectInArray(Array<ItemEffect> effects, ItemEffectEnum effect) {
        for (ItemEffect effectObject : effects) {
            if (effectObject.getEffectEnum() == effect) return true;
        }
        return false;
    }


}
