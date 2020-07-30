package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import java.util.HashMap;

public class ItemGeneratorConfig {

    public static final HashMap<CharacterEnum, HashMap<ItemTypeEnum, Integer>> basicTypeChance;
    public static final HashMap<CharacterEnum, HashMap<ItemTypeEnum, Integer>> lvlTypeChance;

    public static final HashMap<ItemTypeEnum, HashMap<ItemEnum, Integer>> basicItemChance;
    public static final HashMap<ItemTypeEnum, HashMap<ItemEnum, Integer>> lvlItemChance;


    static {
        basicTypeChance = new HashMap<>();
        basicTypeChance.put(CharacterEnum.HERO_ELF, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_KNIGHT, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_WIZZARD, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_LIZARD, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_NINJA, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_PIRATE, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_BABY, new HashMap<ItemTypeEnum, Integer>());

        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.SWORD, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.OTHER, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOOK, 100);



        //____________________________________________________________________________________________________



        lvlTypeChance = new HashMap<>();
        lvlTypeChance.put(CharacterEnum.HERO_ELF, new HashMap<ItemTypeEnum, Integer>());
        lvlTypeChance.put(CharacterEnum.HERO_KNIGHT, new HashMap<ItemTypeEnum, Integer>());
        lvlTypeChance.put(CharacterEnum.HERO_WIZZARD, new HashMap<ItemTypeEnum, Integer>());
        lvlTypeChance.put(CharacterEnum.HERO_LIZARD, new HashMap<ItemTypeEnum, Integer>());
        lvlTypeChance.put(CharacterEnum.HERO_NINJA, new HashMap<ItemTypeEnum, Integer>());
        lvlTypeChance.put(CharacterEnum.HERO_PIRATE, new HashMap<ItemTypeEnum, Integer>());
        lvlTypeChance.put(CharacterEnum.HERO_BABY, new HashMap<ItemTypeEnum, Integer>());

        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.SWORD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.OTHER, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOOK, 100);



        //____________________________________________________________________________________________________


        basicItemChance = new HashMap<>();

        HashMap<ItemEnum, Integer> basicItemArmorChance = new HashMap<>();
        basicItemArmorChance.put(ItemEnum.ARMORv0, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv1, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv2, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv3, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv4, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv5, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv6, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv7, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv8, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv9, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv10, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv11, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv12, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv13, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv14, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv15, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv16, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv17, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv18, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv19, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv20, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv21, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv22, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv23, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv24, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv25, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv26, 100);
        basicItemArmorChance.put(ItemEnum.ARMORv27, 100);
        basicItemChance.put(ItemTypeEnum.ARMOR, basicItemArmorChance);

        HashMap<ItemEnum, Integer> basicItemArrowChance = new HashMap<>();
        basicItemArrowChance.put(ItemEnum.ARROWv0, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv1, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv2, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv3, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv4, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv5, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv6, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv7, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv8, 100);
        basicItemArrowChance.put(ItemEnum.ARROWv9, 100);
        basicItemChance.put(ItemTypeEnum.ARROW, basicItemArrowChance);

        HashMap<ItemEnum, Integer> basicItemOtherChance = new HashMap<>();
        basicItemOtherChance.put(ItemEnum.BOOKv0, 100);
        basicItemOtherChance.put(ItemEnum.BOOKv1, 100);
        basicItemOtherChance.put(ItemEnum.BOOKv2, 100);
        basicItemOtherChance.put(ItemEnum.BOOKv3, 100);
        basicItemOtherChance.put(ItemEnum.BOOKv4, 100);
        basicItemOtherChance.put(ItemEnum.BOOKv5, 100);
        basicItemChance.put(ItemTypeEnum.BOOK, basicItemOtherChance);

        HashMap<ItemEnum, Integer> basicItemBowChance = new HashMap<>();
        basicItemBowChance.put(ItemEnum.BOWv0, 100);
        basicItemBowChance.put(ItemEnum.BOWv1, 100);
        basicItemBowChance.put(ItemEnum.BOWv2, 100);
        basicItemBowChance.put(ItemEnum.BOWv3, 100);
        basicItemBowChance.put(ItemEnum.BOWv4, 100);
        basicItemBowChance.put(ItemEnum.BOWv5, 100);
        basicItemBowChance.put(ItemEnum.BOWv6, 100);
        basicItemBowChance.put(ItemEnum.BOWv7, 100);
        basicItemBowChance.put(ItemEnum.BOWv8, 100);
        basicItemBowChance.put(ItemEnum.BOWv9, 100);
        basicItemBowChance.put(ItemEnum.BOWv10, 100);
        basicItemBowChance.put(ItemEnum.BOWv11, 100);
        basicItemBowChance.put(ItemEnum.BOWv12, 100);
        basicItemBowChance.put(ItemEnum.BOWv13, 100);
        basicItemBowChance.put(ItemEnum.BOWv14, 100);
        basicItemBowChance.put(ItemEnum.BOWv15, 100);
        basicItemBowChance.put(ItemEnum.BOWv16, 100);
        basicItemBowChance.put(ItemEnum.BOWv17, 100);
        basicItemBowChance.put(ItemEnum.BOWv18, 100);
        basicItemBowChance.put(ItemEnum.BOWv19, 100);
        basicItemBowChance.put(ItemEnum.BOWv20, 100);
        basicItemBowChance.put(ItemEnum.BOWv21, 100);
        basicItemBowChance.put(ItemEnum.BOWv22, 100);
        basicItemBowChance.put(ItemEnum.BOWv23, 100);
        basicItemBowChance.put(ItemEnum.BOWv24, 100);
        basicItemBowChance.put(ItemEnum.BOWv25, 100);
        basicItemBowChance.put(ItemEnum.BOWv26, 100);
        basicItemBowChance.put(ItemEnum.BOWv27, 100);
        basicItemBowChance.put(ItemEnum.BOWv28, 100);
        basicItemBowChance.put(ItemEnum.BOWv29, 100);
        basicItemChance.put(ItemTypeEnum.BOW, basicItemBowChance);


        HashMap<ItemEnum, Integer> basicItemFoodChance = new HashMap<>();
        basicItemFoodChance.put(ItemEnum.FOODv0, 100);
        basicItemFoodChance.put(ItemEnum.FOODv1, 100);
        basicItemFoodChance.put(ItemEnum.FOODv2, 100);
        basicItemFoodChance.put(ItemEnum.FOODv3, 100);
        basicItemFoodChance.put(ItemEnum.FOODv4, 100);
        basicItemFoodChance.put(ItemEnum.FOODv5, 100);
        basicItemFoodChance.put(ItemEnum.FOODv6, 100);
        basicItemFoodChance.put(ItemEnum.FOODv7, 100);
        basicItemFoodChance.put(ItemEnum.FOODv8, 100);
        basicItemFoodChance.put(ItemEnum.FOODv9, 100);
        basicItemFoodChance.put(ItemEnum.FOODv10, 100);
        basicItemChance.put(ItemTypeEnum.FOOD, basicItemFoodChance);


        HashMap<ItemEnum, Integer> basicItemHelmetChance = new HashMap<>();
        basicItemHelmetChance.put(ItemEnum.HELMETv0, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv1, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv2, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv3, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv4, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv5, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv6, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv7, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv8, 100);
        basicItemHelmetChance.put(ItemEnum.HELMETv9, 100);
        basicItemChance.put(ItemTypeEnum.HELMET, basicItemHelmetChance);


        HashMap<ItemEnum, Integer> basicItemNecklaceChance = new HashMap<>();
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv0, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv1, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv2, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv3, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv4, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv5, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv6, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv7, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv8, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv9, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv10, 100);
        basicItemNecklaceChance.put(ItemEnum.NECKLACEv11, 100);
        basicItemChance.put(ItemTypeEnum.NECKLACE, basicItemNecklaceChance);


        HashMap<ItemEnum, Integer> basicItemRingChance = new HashMap<>();
        basicItemRingChance.put(ItemEnum.RINGv0, 100);
        basicItemRingChance.put(ItemEnum.RINGv1, 100);
        basicItemRingChance.put(ItemEnum.RINGv2, 100);
        basicItemRingChance.put(ItemEnum.RINGv3, 100);
        basicItemRingChance.put(ItemEnum.RINGv4, 100);
        basicItemRingChance.put(ItemEnum.RINGv5, 100);
        basicItemRingChance.put(ItemEnum.RINGv6, 100);
        basicItemRingChance.put(ItemEnum.RINGv7, 100);
        basicItemRingChance.put(ItemEnum.RINGv8, 100);
        basicItemRingChance.put(ItemEnum.RINGv9, 100);
        basicItemRingChance.put(ItemEnum.RINGv10, 100);
        basicItemRingChance.put(ItemEnum.RINGv11, 100);
        basicItemRingChance.put(ItemEnum.RINGv12, 100);
        basicItemRingChance.put(ItemEnum.RINGv13, 100);
        basicItemRingChance.put(ItemEnum.RINGv14, 100);
        basicItemRingChance.put(ItemEnum.RINGv15, 100);
        basicItemRingChance.put(ItemEnum.RINGv16, 100);
        basicItemRingChance.put(ItemEnum.RINGv17, 100);
        basicItemRingChance.put(ItemEnum.RINGv18, 100);
        basicItemRingChance.put(ItemEnum.RINGv19, 100);
        basicItemRingChance.put(ItemEnum.RINGv20, 100);
        basicItemRingChance.put(ItemEnum.RINGv21, 100);
        basicItemChance.put(ItemTypeEnum.RING, basicItemRingChance);


        HashMap<ItemEnum, Integer> basicItemStaffChance = new HashMap<>();
        basicItemStaffChance.put(ItemEnum.STAFFv0, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv1, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv2, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv3, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv4, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv5, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv6, 100);
        basicItemStaffChance.put(ItemEnum.STAFFv7, 100);
        basicItemChance.put(ItemTypeEnum.STAFF, basicItemStaffChance);


        HashMap<ItemEnum, Integer> basicItemShieldChance = new HashMap<>();
        basicItemShieldChance.put(ItemEnum.SHIELDv0, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv1, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv2, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv3, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv4, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv5, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv6, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv7, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv8, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv9, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv10, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv11, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv12, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv13, 100);
        basicItemShieldChance.put(ItemEnum.SHIELDv14, 100);
        basicItemChance.put(ItemTypeEnum.SHIELD, basicItemShieldChance);


        HashMap<ItemEnum, Integer> basicItemSwordChance = new HashMap<>();
        basicItemSwordChance.put(ItemEnum.SWORDv0, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv1, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv2, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv3, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv4, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv5, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv6, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv7, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv8, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv9, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv10, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv11, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv12, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv13, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv14, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv15, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv16, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv17, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv18, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv19, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv20, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv21, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv22, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv23, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv24, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv25, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv26, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv27, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv28, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv29, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv30, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv31, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv32, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv33, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv34, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv35, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv36, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv37, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv38, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv39, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv40, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv41, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv42, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv43, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv44, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv45, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv46, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv47, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv48, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv49, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv50, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv51, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv52, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv53, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv54, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv55, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv56, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv57, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv58, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv59, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv60, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv61, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv62, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv63, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv64, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv65, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv66, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv67, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv68, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv69, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv70, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv71, 100);
        basicItemSwordChance.put(ItemEnum.SWORDv72, 100);
        basicItemChance.put(ItemTypeEnum.SWORD, basicItemSwordChance);



        //-------------------------------------------------------------------------------------------

        lvlItemChance = new HashMap<>();


        HashMap<ItemEnum, Integer> lvlItemArmorChance = new HashMap<>();
        lvlItemArmorChance.put(ItemEnum.ARMORv0, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv1, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv2, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv3, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv4, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv5, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv6, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv7, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv8, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv9, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv10, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv11, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv12, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv13, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv14, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv15, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv16, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv17, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv18, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv19, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv20, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv21, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv22, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv23, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv24, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv25, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv26, 100);
        lvlItemArmorChance.put(ItemEnum.ARMORv27, 100);
        lvlItemChance.put(ItemTypeEnum.ARMOR, lvlItemArmorChance);

        HashMap<ItemEnum, Integer> lvlItemArrowChance = new HashMap<>();
        lvlItemArrowChance.put(ItemEnum.ARROWv0, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv1, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv2, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv3, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv4, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv5, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv6, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv7, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv8, 100);
        lvlItemArrowChance.put(ItemEnum.ARROWv9, 100);
        lvlItemChance.put(ItemTypeEnum.ARROW, lvlItemArrowChance);

        HashMap<ItemEnum, Integer> lvlItemOtherChance = new HashMap<>();
        lvlItemOtherChance.put(ItemEnum.BOOKv0, 100);
        lvlItemOtherChance.put(ItemEnum.BOOKv1, 100);
        lvlItemOtherChance.put(ItemEnum.BOOKv2, 100);
        lvlItemOtherChance.put(ItemEnum.BOOKv3, 100);
        lvlItemOtherChance.put(ItemEnum.BOOKv4, 100);
        lvlItemOtherChance.put(ItemEnum.BOOKv5, 100);
        lvlItemChance.put(ItemTypeEnum.BOOK, lvlItemOtherChance);

        HashMap<ItemEnum, Integer> lvlItemBowChance = new HashMap<>();
        lvlItemBowChance.put(ItemEnum.BOWv0, 100);
        lvlItemBowChance.put(ItemEnum.BOWv1, 100);
        lvlItemBowChance.put(ItemEnum.BOWv2, 100);
        lvlItemBowChance.put(ItemEnum.BOWv3, 100);
        lvlItemBowChance.put(ItemEnum.BOWv4, 100);
        lvlItemBowChance.put(ItemEnum.BOWv5, 100);
        lvlItemBowChance.put(ItemEnum.BOWv6, 100);
        lvlItemBowChance.put(ItemEnum.BOWv7, 100);
        lvlItemBowChance.put(ItemEnum.BOWv8, 100);
        lvlItemBowChance.put(ItemEnum.BOWv9, 100);
        lvlItemBowChance.put(ItemEnum.BOWv10, 100);
        lvlItemBowChance.put(ItemEnum.BOWv11, 100);
        lvlItemBowChance.put(ItemEnum.BOWv12, 100);
        lvlItemBowChance.put(ItemEnum.BOWv13, 100);
        lvlItemBowChance.put(ItemEnum.BOWv14, 100);
        lvlItemBowChance.put(ItemEnum.BOWv15, 100);
        lvlItemBowChance.put(ItemEnum.BOWv16, 100);
        lvlItemBowChance.put(ItemEnum.BOWv17, 100);
        lvlItemBowChance.put(ItemEnum.BOWv18, 100);
        lvlItemBowChance.put(ItemEnum.BOWv19, 100);
        lvlItemBowChance.put(ItemEnum.BOWv20, 100);
        lvlItemBowChance.put(ItemEnum.BOWv21, 100);
        lvlItemBowChance.put(ItemEnum.BOWv22, 100);
        lvlItemBowChance.put(ItemEnum.BOWv23, 100);
        lvlItemBowChance.put(ItemEnum.BOWv24, 100);
        lvlItemBowChance.put(ItemEnum.BOWv25, 100);
        lvlItemBowChance.put(ItemEnum.BOWv26, 100);
        lvlItemBowChance.put(ItemEnum.BOWv27, 100);
        lvlItemBowChance.put(ItemEnum.BOWv28, 100);
        lvlItemBowChance.put(ItemEnum.BOWv29, 100);
        lvlItemChance.put(ItemTypeEnum.BOW, lvlItemBowChance);


        HashMap<ItemEnum, Integer> lvlItemFoodChance = new HashMap<>();
        lvlItemFoodChance.put(ItemEnum.FOODv0, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv1, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv2, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv3, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv4, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv5, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv6, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv7, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv8, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv9, 100);
        lvlItemFoodChance.put(ItemEnum.FOODv10, 100);
        lvlItemChance.put(ItemTypeEnum.FOOD, lvlItemFoodChance);


        HashMap<ItemEnum, Integer> lvlItemHelmetChance = new HashMap<>();
        lvlItemHelmetChance.put(ItemEnum.HELMETv0, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv1, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv2, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv3, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv4, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv5, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv6, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv7, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv8, 100);
        lvlItemHelmetChance.put(ItemEnum.HELMETv9, 100);
        lvlItemChance.put(ItemTypeEnum.HELMET, lvlItemHelmetChance);


        HashMap<ItemEnum, Integer> lvlItemNecklaceChance = new HashMap<>();
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv0, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv1, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv2, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv3, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv4, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv5, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv6, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv7, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv8, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv9, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv10, 100);
        lvlItemNecklaceChance.put(ItemEnum.NECKLACEv11, 100);
        lvlItemChance.put(ItemTypeEnum.NECKLACE, lvlItemNecklaceChance);


        HashMap<ItemEnum, Integer> lvlItemRingChance = new HashMap<>();
        lvlItemRingChance.put(ItemEnum.RINGv0, 100);
        lvlItemRingChance.put(ItemEnum.RINGv1, 100);
        lvlItemRingChance.put(ItemEnum.RINGv2, 100);
        lvlItemRingChance.put(ItemEnum.RINGv3, 100);
        lvlItemRingChance.put(ItemEnum.RINGv4, 100);
        lvlItemRingChance.put(ItemEnum.RINGv5, 100);
        lvlItemRingChance.put(ItemEnum.RINGv6, 100);
        lvlItemRingChance.put(ItemEnum.RINGv7, 100);
        lvlItemRingChance.put(ItemEnum.RINGv8, 100);
        lvlItemRingChance.put(ItemEnum.RINGv9, 100);
        lvlItemRingChance.put(ItemEnum.RINGv10, 100);
        lvlItemRingChance.put(ItemEnum.RINGv11, 100);
        lvlItemRingChance.put(ItemEnum.RINGv12, 100);
        lvlItemRingChance.put(ItemEnum.RINGv13, 100);
        lvlItemRingChance.put(ItemEnum.RINGv14, 100);
        lvlItemRingChance.put(ItemEnum.RINGv15, 100);
        lvlItemRingChance.put(ItemEnum.RINGv16, 100);
        lvlItemRingChance.put(ItemEnum.RINGv17, 100);
        lvlItemRingChance.put(ItemEnum.RINGv18, 100);
        lvlItemRingChance.put(ItemEnum.RINGv19, 100);
        lvlItemRingChance.put(ItemEnum.RINGv20, 100);
        lvlItemRingChance.put(ItemEnum.RINGv21, 100);
        lvlItemChance.put(ItemTypeEnum.RING, lvlItemRingChance);


        HashMap<ItemEnum, Integer> lvlItemStaffChance = new HashMap<>();
        lvlItemStaffChance.put(ItemEnum.STAFFv0, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv1, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv2, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv3, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv4, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv5, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv6, 100);
        lvlItemStaffChance.put(ItemEnum.STAFFv7, 100);
        lvlItemChance.put(ItemTypeEnum.STAFF, lvlItemStaffChance);


        HashMap<ItemEnum, Integer> lvlItemShieldChance = new HashMap<>();
        lvlItemShieldChance.put(ItemEnum.SHIELDv0, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv1, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv2, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv3, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv4, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv5, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv6, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv7, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv8, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv9, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv10, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv11, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv12, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv13, 100);
        lvlItemShieldChance.put(ItemEnum.SHIELDv14, 100);
        lvlItemChance.put(ItemTypeEnum.SHIELD, lvlItemShieldChance);


        HashMap<ItemEnum, Integer> lvlItemSwordChance = new HashMap<>();
        lvlItemSwordChance.put(ItemEnum.SWORDv0, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv1, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv2, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv3, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv4, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv5, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv6, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv7, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv8, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv9, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv10, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv11, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv12, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv13, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv14, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv15, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv16, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv17, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv18, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv19, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv20, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv21, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv22, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv23, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv24, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv25, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv26, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv27, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv28, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv29, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv30, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv31, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv32, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv33, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv34, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv35, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv36, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv37, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv38, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv39, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv40, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv41, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv42, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv43, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv44, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv45, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv46, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv47, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv48, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv49, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv50, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv51, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv52, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv53, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv54, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv55, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv56, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv57, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv58, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv59, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv60, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv61, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv62, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv63, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv64, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv65, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv66, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv67, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv68, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv69, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv70, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv71, 100);
        lvlItemSwordChance.put(ItemEnum.SWORDv72, 100);
        lvlItemChance.put(ItemTypeEnum.SWORD, lvlItemSwordChance);

    }

}
