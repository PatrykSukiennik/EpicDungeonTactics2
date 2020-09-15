package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class ItemEffectsConfig {

    public static final Map<ItemTypeEnum, HashMap<ItemEffectEnum, Integer>> effectChance;
    public static final Map<ItemEffectEnum, Float> basicEffectPower;
    public static final Map<ItemEffectEnum, Float> lvlEffectPower;

    static {
        effectChance = new HashMap<>();

        effectChance.put(ItemTypeEnum.ARMOR, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARMOR).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.ARROW, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.ARROW).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.BOOK, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOOK).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.BOW, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.BOW).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.FOOD, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.FOOD).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.HELMET, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.HELMET).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.RING, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.RING).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.NECKLACE, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.NECKLACE).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.SHIELD, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.SHIELD).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.STAFF, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.STAFF).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.OTHER, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.OTHER).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);

        effectChance.put(ItemTypeEnum.MELE, new HashMap<ItemEffectEnum, Integer>());
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.HP_REGEN, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.DMG_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.SPEED_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.RANGE_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.STR_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.INT_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.LCK_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.VIT_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.DEX_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.HP_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2);
        effectChance.get(ItemTypeEnum.MELE).put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2);



        basicEffectPower = new HashMap<>();
        basicEffectPower.put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2f);
        basicEffectPower.put(ItemEffectEnum.HP_REGEN, 2f);
        basicEffectPower.put(ItemEffectEnum.DMG_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2f);
        basicEffectPower.put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2f);
        basicEffectPower.put(ItemEffectEnum.SPEED_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.RANGE_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.STR_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.INT_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.LCK_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.VIT_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.DEX_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.HP_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2f);
        basicEffectPower.put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2f);

        lvlEffectPower = new HashMap<>();
        lvlEffectPower.put(ItemEffectEnum.PERCENT_CHANCE_FOR_DOUBLE_ATTACK, 2f);
        lvlEffectPower.put(ItemEffectEnum.HP_REGEN, 2f);
        lvlEffectPower.put(ItemEffectEnum.DMG_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.PERCENT_CHANCE_FOR_BURNING, 2f);
        lvlEffectPower.put(ItemEffectEnum.PERCENT_CHANCE_FOR_POISONING, 2f);
        lvlEffectPower.put(ItemEffectEnum.SPEED_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.RANGE_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.STR_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.INT_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.LCK_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.VIT_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.DEX_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.HP_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.PERCENT_EXP_COLLECTED_BONUS, 2f);
        lvlEffectPower.put(ItemEffectEnum.PERCENT_GOLD_COLLECTED_BONUS, 2f);

    }

}
