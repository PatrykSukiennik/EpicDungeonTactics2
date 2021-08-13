package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import static com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum.BLUE;
import static com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum.GREEN;
import static com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum.ORANGE;
import static com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum.RED;
import static com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum.VIOLET;
import static com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum.WHITE;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
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
import com.appatstudio.epicdungeontactics2.global.stats.items.ItemPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.MelePrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.NecklacePrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.RingPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.ShieldPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.items.StaffPrototype;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Armor;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Arrow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Book;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Bow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Food;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Helmet;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.MeleWeapon;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Necklace;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Ring;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Shield;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Staff;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class ItemGenerator {

    private static int itemTypeSum;
    private static int itemRaritySum;
    private static int itemEffectSum;
    private static ItemEffectEnum[] max100PercentPowerEffects;
    private static HashMap<ItemTypeEnum, Integer> itemSums = new HashMap<>();
    private static HashMap<ItemRarityEnum, Float> basicItemEffectsChanceMap = new HashMap<>();
    private static HashMap<ItemRarityEnum, Float> lvlItemEffectsChanceMap = new HashMap<>();

    private static int lvl;

    static {
        max100PercentPowerEffects = new ItemEffectEnum[]{
                ItemEffectEnum.PERCENT_HP_REGEN_CLEAN_ROOM,
                ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK,
                ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_MOVE,
                ItemEffectEnum.PERCENT_ANY_DROP_CHANCE,
                ItemEffectEnum.PERCENT_SPELL_CHANCE,
                ItemEffectEnum.PERCENT_CRIT_CHANCE,
                ItemEffectEnum.PERCENT_CHANCE_FOR_STUN
        };

        basicItemEffectsChanceMap.put(WHITE, 0.1f);
        basicItemEffectsChanceMap.put(GREEN, 0.2f);
        basicItemEffectsChanceMap.put(BLUE, 0.3f);
        basicItemEffectsChanceMap.put(VIOLET, 0.4f);
        basicItemEffectsChanceMap.put(ORANGE, 0.5f);
        basicItemEffectsChanceMap.put(RED, 0.6f);

        lvlItemEffectsChanceMap.put(WHITE, 0.02f);
        lvlItemEffectsChanceMap.put(GREEN, 0.03f);
        lvlItemEffectsChanceMap.put(BLUE, 0.04f);
        lvlItemEffectsChanceMap.put(VIOLET, 0.05f);
        lvlItemEffectsChanceMap.put(ORANGE, 0.06f);
        lvlItemEffectsChanceMap.put(RED, 0.07f);
    }

    public static AbstractItem getItem() {
        ItemTypeEnum type = getType();
        ItemRarityEnum rarityEnum = getRarity(type);
        return createItem(getItemEnum(type, rarityEnum, false), type, true, false);
    }

    public static AbstractItem getItemUnique() {
        ItemTypeEnum type = getType();
        ItemRarityEnum rarityEnum = getRarity(type);
        return createItem(getItemEnum(type, rarityEnum, true), type, true, true);
    }

    public static AbstractItem getItem(ItemEnum itemEnum) {
        return createItem(itemEnum, AbstractItem.getItemTypeEnum(itemEnum), true, false);
    }

    public static AbstractItem getItem(ItemTypeEnum typeEnum) {
        ItemEnum item = getItemEnum(typeEnum, getRarity(typeEnum), false);
        return createItem(item, typeEnum, true, false);
    }

    public static AbstractItem getItemNoPerks(ItemEnum item, ItemTypeEnum type) {
        return createItem(getItemEnum(type, getRarity(type), false), type, false, false);
    }

    public static void refresh() {

        lvl = SavedInfoManager.getMaxCharacterLvl();
        System.out.println("MAXLVL: " + SavedInfoManager.getMaxCharacterLvl());

        itemTypeSum = 0;
        for (ItemTypeEnum t : ItemTypeEnum.values()) {
            itemTypeSum += ItemGeneratorConfig.basicTypeChance.get(StatTracker.getHero()).get(t)
                    + ItemGeneratorConfig.lvlTypeChance.get(StatTracker.getHero()).get(t) * lvl;
        }

        itemRaritySum = 0;
        for (ItemRarityEnum r : ItemRarityEnum.values()) {
            itemRaritySum += ItemGeneratorConfig.basicItemRarity.get(r)
                    + ItemGeneratorConfig.lvlItemRarity.get(r) * lvl;
        }

    }

    public static ItemTypeEnum getType() {
        if (itemTypeSum == 0) refresh();
        int temp = 0;
        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % itemTypeSum;

        for (ItemTypeEnum type : ItemTypeEnum.values()) {
            temp += ItemGeneratorConfig.basicTypeChance.get(StatTracker.getHero()).get(type) +
                    lvl * ItemGeneratorConfig.lvlTypeChance.get(StatTracker.getHero()).get(type);

            if (result <= temp) return type;
        }
        return ItemTypeEnum.values()[0];
    }

    public static ItemRarityEnum getRarity(ItemTypeEnum type) {
        if (itemRaritySum == 0) refresh();
        int temp = 0;
        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % itemRaritySum;

        for (ItemRarityEnum rarity : ItemRarityEnum.values()) {
            temp += ItemGeneratorConfig.basicItemRarity.get(rarity) +
                    lvl * ItemGeneratorConfig.lvlItemRarity.get(rarity);

            if (result <= temp) {
                return rarity;
            }
        }
        return ItemRarityEnum.values()[0];
    }

    private static ItemEnum getItemEnum(ItemTypeEnum type, ItemRarityEnum rarity, boolean isUnique) {
        int temp = 0;
        int lvlNow = isUnique ? lvl + 5 : lvl;
        int sum = 0;

        System.out.println(type.toString());
        System.out.println(rarity.toString());
        ItemEnum[] items = ItemGeneratorConfig.itemRarities.get(type).get(rarity);

        if (items.length == 0) {
            return getItemEnum(type, WHITE, isUnique);
        }

        for (ItemEnum item : items) {
            sum += getItemChance(type, item, lvlNow);
        }

        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % sum;

        for (ItemEnum item : items) {
            temp += getItemChance(type, item, lvlNow);
            if (result <= temp) return item;
        }

        return (ItemEnum) items[0];
    }

    private static AbstractItem createItem(ItemEnum itemEnum, ItemTypeEnum typeEnum, boolean withPerks, boolean isUnique) {
        int lvlNow = isUnique ? lvl + 5 : lvl;

        switch (typeEnum) {
            case ARMOR:
                ArmorPrototype prototypeArmor = ItemStatsConfig.basicArmorStats.get(itemEnum);
                ArmorPrototype lvlPrototypeArmor = ItemStatsConfig.lvlArmorStats.get(itemEnum);
                return new Armor(
                        itemEnum,
                        typeEnum,
                        prototypeArmor.getVALUE() + lvlNow * lvlPrototypeArmor.getVALUE(),
                        withPerks ? generateEffects(prototypeArmor) : new Array<ItemEffect>(),
                        prototypeArmor.getARMOR() + lvlNow * lvlPrototypeArmor.getARMOR(),
                        prototypeArmor.getMOVE_SPEED_COST() + lvlNow * lvlPrototypeArmor.getMOVE_SPEED_COST(),
                        prototypeArmor.getRARITY()
                );

            case ARROW:
                ArrowPrototype prototypeArrow = ItemStatsConfig.basicArrowStats.get(itemEnum);
                ArrowPrototype lvlPrototypeArrow = ItemStatsConfig.lvlArrowStats.get(itemEnum);
                return new Arrow(
                        itemEnum,
                        typeEnum,
                        prototypeArrow.getVALUE() + lvlNow * lvlPrototypeArrow.getVALUE(),
                        withPerks ? generateEffects(prototypeArrow) : new Array<ItemEffect>(),
                        prototypeArrow.getRANGE_EFFECT() + lvlNow * lvlPrototypeArrow.getRANGE_EFFECT(),
                        prototypeArrow.getDMG_EFFECT() + lvlNow * lvlPrototypeArrow.getDMG_EFFECT(),
                        prototypeArrow.getRARITY()
                );

            case BOW:
                BowPrototype prototypeBow = ItemStatsConfig.basicBowStats.get(itemEnum);
                BowPrototype lvlPrototypeBow = ItemStatsConfig.lvlBowStats.get(itemEnum);
                return new Bow(
                        itemEnum,
                        typeEnum,
                        prototypeBow.getVALUE() + lvlNow * lvlPrototypeBow.getVALUE(),
                        withPerks ? generateEffects(prototypeBow) : new Array<ItemEffect>(),
                        prototypeBow.getRANGE() + lvlNow * lvlPrototypeBow.getRANGE(),
                        prototypeBow.getDMG() + lvlNow * lvlPrototypeBow.getDMG(),
                        prototypeBow.getRARITY()
                );

            case BOOK:
                BookPrototype prototypeBook = ItemStatsConfig.basicBookStats.get(itemEnum);
                BookPrototype lvlPrototypeBook = ItemStatsConfig.lvlBookStats.get(itemEnum);
                return new Book(
                        itemEnum,
                        typeEnum,
                        prototypeBook.getVALUE() + lvlNow * lvlPrototypeBook.getVALUE(),
                        withPerks ? generateEffects(prototypeBook) : new Array<ItemEffect>(),
                        prototypeBook.getSpell(),
                        (int) (prototypeBook.getMP_COST() + lvlNow * 0.1f * lvlPrototypeBook.getMP_COST()),
                        (int) (prototypeBook.getDURATION() + lvlNow * 0.1f * lvlPrototypeBook.getDURATION()),
                        (int) (prototypeBook.getDMG() + lvlNow * 0.1f * lvlPrototypeBook.getDMG()),
                        prototypeBook.getRARITY()
                );

            case FOOD:
                FoodPrototype prototypeFood = ItemStatsConfig.basicFoodStats.get(itemEnum);
                FoodPrototype lvlPrototypeFood = ItemStatsConfig.lvlFoodStats.get(itemEnum);
                return new Food(
                        itemEnum,
                        typeEnum,
                        prototypeFood.getVALUE() + lvlNow * lvlPrototypeFood.getVALUE(),
                        withPerks ? generateEffects(prototypeFood) : new Array<ItemEffect>(),
                        prototypeFood.getHP_EFFECT() + lvlNow * lvlPrototypeFood.getHP_EFFECT(),
                        prototypeFood.getMP_EFFECT() + lvlNow * lvlPrototypeFood.getMP_EFFECT(),
                        prototypeFood.getRARITY()
                );


            case HELMET:
                HelmetPrototype prototypeHelmet = ItemStatsConfig.basicHelmetStats.get(itemEnum);
                HelmetPrototype lvlPrototypeHelmet = ItemStatsConfig.lvlHelmetStats.get(itemEnum);
                return new Helmet(
                        itemEnum,
                        typeEnum,
                        prototypeHelmet.getVALUE() + lvlNow * lvlPrototypeHelmet.getVALUE(),
                        withPerks ? generateEffects(prototypeHelmet) : new Array<ItemEffect>(),
                        prototypeHelmet.getARMOR() + lvlNow * lvlPrototypeHelmet.getARMOR(),
                        prototypeHelmet.getRARITY()
                );


            case MELE:
                MelePrototype prototypeMele = ItemStatsConfig.basicMeleStats.get(itemEnum);
                MelePrototype lvlPrototypeMele = ItemStatsConfig.lvlMeleStats.get(itemEnum);
                return new MeleWeapon(
                        itemEnum,
                        typeEnum,
                        prototypeMele.getVALUE() + lvlNow * lvlPrototypeMele.getVALUE(),
                        withPerks ? generateEffects(prototypeMele) : new Array<ItemEffect>(),
                        prototypeMele.getSPEED_EFFECT() + lvlNow * lvlPrototypeMele.getSPEED_EFFECT(),
                        prototypeMele.getDMG() + lvlNow * lvlPrototypeMele.getDMG(),
                        prototypeMele.getRARITY()
                );


            case NECKLACE:
                NecklacePrototype prototypeNecklace = ItemStatsConfig.basicNecklaceStats.get(itemEnum);
                NecklacePrototype lvlPrototypeNecklace = ItemStatsConfig.lvlNecklaceStats.get(itemEnum);
                return new Necklace(
                        itemEnum,
                        typeEnum,
                        prototypeNecklace.getVALUE() + lvlNow * lvlPrototypeNecklace.getVALUE(),
                        withPerks ? generateEffects(prototypeNecklace) : new Array<ItemEffect>(),
                        prototypeNecklace.getRARITY()
                );


            case RING:
                RingPrototype prototypeRing = ItemStatsConfig.basicRingStats.get(itemEnum);
                RingPrototype lvlPrototypeRing = ItemStatsConfig.lvlRingStats.get(itemEnum);
                return new Ring(
                        itemEnum,
                        typeEnum,
                        prototypeRing.getVALUE() + lvlNow * lvlPrototypeRing.getVALUE(),
                        withPerks ? generateEffects(prototypeRing) : new Array<ItemEffect>(),
                        prototypeRing.getRARITY()
                );


            case SHIELD:
                ShieldPrototype prototypeShield = ItemStatsConfig.basicShieldStats.get(itemEnum);
                ShieldPrototype lvlPrototypeShield = ItemStatsConfig.lvlShieldStats.get(itemEnum);
                return new Shield(
                        itemEnum,
                        typeEnum,
                        prototypeShield.getVALUE() + lvlNow * lvlPrototypeShield.getVALUE(),
                        withPerks ? generateEffects(prototypeShield) : new Array<ItemEffect>(),
                        prototypeShield.getARMOR() + lvlNow * lvlPrototypeShield.getARMOR(),
                        prototypeShield.getSPEED_EFFECT() + lvlNow * lvlPrototypeShield.getSPEED_EFFECT(),
                        prototypeShield.getRARITY()
                );


            case STAFF:
                StaffPrototype prototypeStaff = ItemStatsConfig.basicStaffStats.get(itemEnum);
                StaffPrototype lvlPrototypeStaff = ItemStatsConfig.lvlStaffStats.get(itemEnum);
                return new Staff(
                        itemEnum,
                        typeEnum,
                        prototypeStaff.getVALUE() + lvlNow * lvlPrototypeStaff.getVALUE(),
                        withPerks ? generateEffects(prototypeStaff) : new Array<ItemEffect>(),
                        prototypeStaff.getSPELL(),
                        prototypeStaff.getDMG() + lvlNow * lvlPrototypeStaff.getDMG(),
                        prototypeStaff.getSPELL_CHANCE() + lvlNow * lvlPrototypeStaff.getSPELL_CHANCE(),
                        prototypeStaff.getSPEED_EFFECT() + lvlNow * lvlPrototypeStaff.getSPEED_EFFECT(),
                        prototypeStaff.getRARITY(),
                        prototypeStaff.getRANGE()
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

    private static Array<ItemEffect> generateEffects(ItemPrototype item) {
        ItemTypeEnum type = item.getTYPE();
        ItemRarityEnum rarity = item.getRARITY();

        int startIndex = 0;


        for (Integer i : ItemEffectsConfig.effectChance.get(type).values()) {
            itemEffectSum += i;
        }

        Array<ItemEffect> effects = new Array<>();

        if (item.getItemEnum() == ItemEnum.SWORDv38) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv39) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv40) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv67) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_STUN));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv72) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_STUN));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv66) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_STUN));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv65) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_STUN));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.SWORDv52) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_STUN));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv3) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv4) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv9) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv10) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv15) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv16) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv21) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv22) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv27) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.BOWv28) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.ARROWv2) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.ARROWv3) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.ARROWv4) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.ARROWv5) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_FREEZE));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.ARROWv8) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING));
            startIndex++;
        } else if (item.getItemEnum() == ItemEnum.ARROWv9) {
            effects.add(getEffect(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING));
            startIndex++;
        }
        if (effects.size > 0) {
            System.out.println("LOLOLO " + effects.get(0).getEffectEnum().toString() + "  " + effects.get(0).getPower());
        }

        switch (type) {

            case RING:
                float powersRing = (((float) (ItemStatsConfig.basicRingStats.get(item.getItemEnum()).getEFFECTS() + ItemStatsConfig.lvlRingStats.get(item.getItemEnum()).getEFFECTS() * SavedInfoManager.getMaxCharacterLvl())) / 100f);
                for (int i = startIndex; i < 4; i++) {
                    ItemEffect effect = getEffect(effects, item);
                    if (
                            (effects.size == 0 && effect != null)
                            ||
                            (EpicDungeonTactics.random.nextFloat() < (powersRing + (basicItemEffectsChanceMap.get(rarity)/5f)) * (float) SavedInfoManager.getMaxCharacterLvl() && effect != null)) {
                        effects.add(effect);
                    }
                }
                break;
            case NECKLACE:
                float powersNecklace = (((float) (ItemStatsConfig.basicNecklaceStats.get(item.getItemEnum()).getEFFECTS() + ItemStatsConfig.lvlNecklaceStats.get(item.getItemEnum()).getEFFECTS() * SavedInfoManager.getMaxCharacterLvl())) / 100f);
                for (int i = startIndex; i < 4; i++) {
                    ItemEffect effect = getEffect(effects, item);
                    if (
                            (effects.size == 0 && getEffect(effects, item) != null)
                            ||
                            (EpicDungeonTactics.random.nextFloat() < (powersNecklace + (basicItemEffectsChanceMap.get(rarity)/5f)) * SavedInfoManager.getMaxCharacterLvl() && effect != null)) {
                        effects.add(effect);
                    }
                }
                break;
            case ARMOR:
            case STAFF:
            case SHIELD:
            case MELE:
            case HELMET:
            case BOW:
            case ARROW:
                for (int i = startIndex; i < 4; i++) {
                    ItemEffect effect = getEffect(effects, item);
                    if (EpicDungeonTactics.random.nextFloat() < basicItemEffectsChanceMap.get(rarity) + (lvlItemEffectsChanceMap.get(rarity) * (float)SavedInfoManager.getMaxCharacterLvl()) && effect != null) {
                        effects.add(effect);
                    }
                }
            case BOOK:
            case FOOD:
                break;
        }
        return effects;
    }

    private static ItemEffect getEffect(ItemEffectEnum effectEnum) {
        float power =
                EpicDungeonTactics.random.nextFloat()
                        * (ItemEffectsConfig.basicEffectPower.get(effectEnum)
                        + SavedInfoManager.getMaxCharacterLvl() * ItemEffectsConfig.lvlEffectPower.get(effectEnum));

        if (isEffectInArray(max100PercentPowerEffects, effectEnum) && power > 0.9f) power = 0.9f;
        if (power < 0.1f) power = 0.1f;

        System.out.println("TOTOTOTOTOTO " + power + "  " + effectEnum.toString());
        return new ItemEffect(effectEnum, power);
    }

    private static ItemEffect getEffect(Array<ItemEffect> effects, ItemPrototype itemPrototype) {

        ItemRarityEnum rarity = itemPrototype.getRARITY();
        ItemTypeEnum typeEnum = itemPrototype.getTYPE();
        ItemEffectEnum effectEnum = ItemEffectEnum.DEX_BONUS;
        ItemEffectEnum[] allEffects = ItemEffectEnum.values();

        itemEffectSum = 0;
        for (ItemEffectEnum effect : allEffects) {
            itemEffectSum += ItemEffectsConfig.effectChance.get(typeEnum).get(effect);
        }

        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % itemEffectSum;

        int temp = 0;
        for (ItemEffectEnum effect : allEffects) {
            temp += ItemEffectsConfig.effectChance.get(typeEnum).get(effect);

            if (result <= temp && !isEffectInArray(effects, effect)) {
                effectEnum = effect;
                break;
            }
        }
        //if (effectEnum == null) effectEnum = ItemEffectEnum.PERCENT_HP_REGEN_KILL;

        System.out.println("EFF: " + effectEnum.toString());

        float power =
                EpicDungeonTactics.random.nextFloat()
                        * (ItemEffectsConfig.basicEffectPower.get(effectEnum)
                        + (SavedInfoManager.getMaxCharacterLvl() + ItemEffectsConfig.rarityEffectPowerBonusLvl.get(rarity)) * ItemEffectsConfig.lvlEffectPower.get(effectEnum));

        if (isEffectInArray(max100PercentPowerEffects, effectEnum) && power > 0.9f) power = 0.9f;

        boolean isInt = (Math.floor(ItemEffectsConfig.basicEffectPower.get(effectEnum)) == ItemEffectsConfig.basicEffectPower.get(effectEnum));

        return isInt && power < 1 ? null : new ItemEffect(effectEnum, power);

    }

    private static boolean isEffectInArray(Array<ItemEffect> effects, ItemEffectEnum effect) {
        for (ItemEffect effectObject : effects) {
            if (effectObject != null && effectObject.getEffectEnum() == effect) return true;
        }
        return false;
    }

    private static boolean isEffectInArray(ItemEffectEnum[] effects, ItemEffectEnum effect) {
        for (ItemEffectEnum effectEnum : effects) {
            if (effectEnum == effect) return true;
        }
        return false;
    }

    private static int getItemChance(ItemTypeEnum type, ItemEnum item, int lvl) {
        ItemRarityEnum rarityEnum;
        switch (type) {
            case ARMOR: {
                rarityEnum = ItemStatsConfig.basicArmorStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case ARROW: {
                rarityEnum = ItemStatsConfig.basicArrowStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case BOOK: {
                rarityEnum = ItemStatsConfig.basicBookStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case BOW: {
                rarityEnum = ItemStatsConfig.basicBowStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case HELMET: {
                rarityEnum = ItemStatsConfig.basicHelmetStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case SHIELD: {
                rarityEnum = ItemStatsConfig.basicShieldStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case STAFF: {
                rarityEnum = ItemStatsConfig.basicStaffStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case MELE: {
                rarityEnum = ItemStatsConfig.basicMeleStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case NECKLACE: {
                rarityEnum = ItemStatsConfig.basicNecklaceStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case RING: {
                rarityEnum = ItemStatsConfig.basicRingStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
            case FOOD: {
                rarityEnum = ItemStatsConfig.basicFoodStats.get(item).getRARITY();
                return ItemGeneratorConfig.basicItemRarity.get(rarityEnum) + (lvl * ItemGeneratorConfig.lvlItemRarity.get(rarityEnum));
            }
        }
        return 0;
    }

}
