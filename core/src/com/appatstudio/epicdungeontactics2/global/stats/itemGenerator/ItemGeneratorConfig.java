package com.appatstudio.epicdungeontactics2.global.stats.itemGenerator;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.items.ItemPrototype;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemGeneratorConfig {

    public static final HashMap<CharacterEnum, HashMap<ItemTypeEnum, Integer>> basicTypeChance;
    public static final HashMap<CharacterEnum, HashMap<ItemTypeEnum, Integer>> lvlTypeChance;

    public static final HashMap<ItemRarityEnum, Integer> basicItemRarity;
    public static final HashMap<ItemRarityEnum, Integer> lvlItemRarity;

    public static final HashMap<ItemTypeEnum, HashMap<ItemRarityEnum, ItemEnum[]>> itemRarities;

    static {
        basicTypeChance = new HashMap<>();
        basicTypeChance.put(CharacterEnum.HERO_ELF, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_KNIGHT, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_WIZZARD, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_LIZARD, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_NINJA, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_PIRATE, new HashMap<ItemTypeEnum, Integer>());
        basicTypeChance.put(CharacterEnum.HERO_BABY, new HashMap<ItemTypeEnum, Integer>());

        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.HELMET, 400);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.FOOD, 1000);
        basicTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOOK, 1000);

        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.FOOD, 100);
        basicTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOOK, 100);

        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.MELE, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.STAFF, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOW, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARROW, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.HELMET, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARMOR, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.SHIELD, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.NECKLACE, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.RING, 100);
        basicTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.FOOD, 100);
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

        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_ELF).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_KNIGHT).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_WIZZARD).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_LIZARD).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_NINJA).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_PIRATE).put(ItemTypeEnum.BOOK, 100);

        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.MELE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.STAFF, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARROW, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.HELMET, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.ARMOR, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.SHIELD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.NECKLACE, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.RING, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.FOOD, 100);
        lvlTypeChance.get(CharacterEnum.HERO_BABY).put(ItemTypeEnum.BOOK, 100);



        //____________________________________________________________________________________________________


        basicItemRarity = new HashMap<>();
        basicItemRarity.put(ItemRarityEnum.WHITE, 120);
        basicItemRarity.put(ItemRarityEnum.GREEN, 90);
        basicItemRarity.put(ItemRarityEnum.BLUE, 40);
        basicItemRarity.put(ItemRarityEnum.VIOLET, 4);
        basicItemRarity.put(ItemRarityEnum.ORANGE, 2);
        basicItemRarity.put(ItemRarityEnum.RED, 1);

        lvlItemRarity = new HashMap<>();
        lvlItemRarity.put(ItemRarityEnum.WHITE, 2);
        lvlItemRarity.put(ItemRarityEnum.GREEN, 5);
        lvlItemRarity.put(ItemRarityEnum.BLUE, 7);
        lvlItemRarity.put(ItemRarityEnum.VIOLET, 8);
        lvlItemRarity.put(ItemRarityEnum.ORANGE, 10);
        lvlItemRarity.put(ItemRarityEnum.RED, 11);



        //____________________________________________________________________________________________________

        itemRarities = new HashMap<>();

        //armors

        HashMap<ItemRarityEnum, ItemEnum[]> armorRarities = new HashMap<>();
        List<ItemEnum> whiteArmors = new ArrayList<>();
        List<ItemEnum> greenArmors = new ArrayList<>();
        List<ItemEnum> blueArmors = new ArrayList<>();
        List<ItemEnum> violetArmors = new ArrayList<>();
        List<ItemEnum> orangeArmors = new ArrayList<>();
        List<ItemEnum> redArmors = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicArmorStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteArmors.add(i.getItemEnum()); break;
                case GREEN: greenArmors.add(i.getItemEnum()); break;
                case BLUE: blueArmors.add(i.getItemEnum()); break;
                case VIOLET: violetArmors.add(i.getItemEnum()); break;
                case ORANGE: orangeArmors.add(i.getItemEnum()); break;
                case RED: redArmors.add(i.getItemEnum()); break;
            }
        }
        armorRarities.put(ItemRarityEnum.WHITE, whiteArmors.toArray(new ItemEnum[0]));
        armorRarities.put(ItemRarityEnum.GREEN, greenArmors.toArray(new ItemEnum[0]));
        armorRarities.put(ItemRarityEnum.BLUE, blueArmors.toArray(new ItemEnum[0]));
        armorRarities.put(ItemRarityEnum.VIOLET, violetArmors.toArray(new ItemEnum[0]));
        armorRarities.put(ItemRarityEnum.ORANGE, orangeArmors.toArray(new ItemEnum[0]));
        armorRarities.put(ItemRarityEnum.RED, redArmors.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.ARMOR, armorRarities);

        //arrows

        HashMap<ItemRarityEnum, ItemEnum[]> arrowRarities = new HashMap<>();
        List<ItemEnum> whiteArrows = new ArrayList<>();
        List<ItemEnum> greenArrows = new ArrayList<>();
        List<ItemEnum> blueArrows = new ArrayList<>();
        List<ItemEnum> violetArrows = new ArrayList<>();
        List<ItemEnum> orangeArrows = new ArrayList<>();
        List<ItemEnum> redArrows = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicArrowStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteArrows.add(i.getItemEnum()); break;
                case GREEN: greenArrows.add(i.getItemEnum()); break;
                case BLUE: blueArrows.add(i.getItemEnum()); break;
                case VIOLET: violetArrows.add(i.getItemEnum()); break;
                case ORANGE: orangeArrows.add(i.getItemEnum()); break;
                case RED: redArrows.add(i.getItemEnum()); break;
            }
        }
        arrowRarities.put(ItemRarityEnum.WHITE, whiteArrows.toArray(new ItemEnum[0]));
        arrowRarities.put(ItemRarityEnum.GREEN, greenArrows.toArray(new ItemEnum[0]));
        arrowRarities.put(ItemRarityEnum.BLUE, blueArrows.toArray(new ItemEnum[0]));
        arrowRarities.put(ItemRarityEnum.VIOLET, violetArrows.toArray(new ItemEnum[0]));
        arrowRarities.put(ItemRarityEnum.ORANGE, orangeArrows.toArray(new ItemEnum[0]));
        arrowRarities.put(ItemRarityEnum.RED, redArrows.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.ARROW, arrowRarities);

        //books

        HashMap<ItemRarityEnum, ItemEnum[]> bookRarities = new HashMap<>();
        List<ItemEnum> whiteBooks = new ArrayList<>();
        List<ItemEnum> greenBooks = new ArrayList<>();
        List<ItemEnum> blueBooks = new ArrayList<>();
        List<ItemEnum> violetBooks = new ArrayList<>();
        List<ItemEnum> orangeBooks = new ArrayList<>();
        List<ItemEnum> redBooks = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicBookStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteBooks.add(i.getItemEnum()); break;
                case GREEN: greenBooks.add(i.getItemEnum()); break;
                case BLUE: blueBooks.add(i.getItemEnum()); break;
                case VIOLET: violetBooks.add(i.getItemEnum()); break;
                case ORANGE: orangeBooks.add(i.getItemEnum()); break;
                case RED: redBooks.add(i.getItemEnum()); break;
            }
        }
        bookRarities.put(ItemRarityEnum.WHITE, whiteBooks.toArray(new ItemEnum[0]));
        bookRarities.put(ItemRarityEnum.GREEN, greenBooks.toArray(new ItemEnum[0]));
        bookRarities.put(ItemRarityEnum.BLUE, blueBooks.toArray(new ItemEnum[0]));
        bookRarities.put(ItemRarityEnum.VIOLET, violetBooks.toArray(new ItemEnum[0]));
        bookRarities.put(ItemRarityEnum.ORANGE, orangeBooks.toArray(new ItemEnum[0]));
        bookRarities.put(ItemRarityEnum.RED, redBooks.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.BOOK, bookRarities);

        //bows

        HashMap<ItemRarityEnum, ItemEnum[]> bowRarities = new HashMap<>();
        List<ItemEnum> whiteBows = new ArrayList<>();
        List<ItemEnum> greenBows = new ArrayList<>();
        List<ItemEnum> blueBows = new ArrayList<>();
        List<ItemEnum> violetBows = new ArrayList<>();
        List<ItemEnum> orangeBows = new ArrayList<>();
        List<ItemEnum> redBows = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicBowStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteBows.add(i.getItemEnum()); break;
                case GREEN: greenBows.add(i.getItemEnum()); break;
                case BLUE: blueBows.add(i.getItemEnum()); break;
                case VIOLET: violetBows.add(i.getItemEnum()); break;
                case ORANGE: orangeBows.add(i.getItemEnum()); break;
                case RED: redBows.add(i.getItemEnum()); break;
            }
        }
        bowRarities.put(ItemRarityEnum.WHITE, whiteBows.toArray(new ItemEnum[0]));
        bowRarities.put(ItemRarityEnum.GREEN, greenBows.toArray(new ItemEnum[0]));
        bowRarities.put(ItemRarityEnum.BLUE, blueBows.toArray(new ItemEnum[0]));
        bowRarities.put(ItemRarityEnum.VIOLET, violetBows.toArray(new ItemEnum[0]));
        bowRarities.put(ItemRarityEnum.ORANGE, orangeBows.toArray(new ItemEnum[0]));
        bowRarities.put(ItemRarityEnum.RED, redBows.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.BOW, bowRarities);

        //shields

        HashMap<ItemRarityEnum, ItemEnum[]> shieldRarities = new HashMap<>();
        List<ItemEnum> whiteShields = new ArrayList<>();
        List<ItemEnum> greenShields = new ArrayList<>();
        List<ItemEnum> blueShields = new ArrayList<>();
        List<ItemEnum> violetShields = new ArrayList<>();
        List<ItemEnum> orangeShields = new ArrayList<>();
        List<ItemEnum> redShields = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicShieldStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteShields.add(i.getItemEnum()); break;
                case GREEN: greenShields.add(i.getItemEnum()); break;
                case BLUE: blueShields.add(i.getItemEnum()); break;
                case VIOLET: violetShields.add(i.getItemEnum()); break;
                case ORANGE: orangeShields.add(i.getItemEnum()); break;
                case RED: redShields.add(i.getItemEnum()); break;
            }
        }
        shieldRarities.put(ItemRarityEnum.WHITE, whiteShields.toArray(new ItemEnum[0]));
        shieldRarities.put(ItemRarityEnum.GREEN, greenShields.toArray(new ItemEnum[0]));
        shieldRarities.put(ItemRarityEnum.BLUE, blueShields.toArray(new ItemEnum[0]));
        shieldRarities.put(ItemRarityEnum.VIOLET, violetShields.toArray(new ItemEnum[0]));
        shieldRarities.put(ItemRarityEnum.ORANGE, orangeShields.toArray(new ItemEnum[0]));
        shieldRarities.put(ItemRarityEnum.RED, redShields.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.SHIELD, shieldRarities);

        //necklace

        HashMap<ItemRarityEnum, ItemEnum[]> necklaceRarities = new HashMap<>();
        List<ItemEnum> whiteNecklaces = new ArrayList<>();
        List<ItemEnum> greenNecklaces = new ArrayList<>();
        List<ItemEnum> blueNecklaces = new ArrayList<>();
        List<ItemEnum> violetNecklaces = new ArrayList<>();
        List<ItemEnum> orangeNecklaces = new ArrayList<>();
        List<ItemEnum> redNecklaces = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicNecklaceStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteNecklaces.add(i.getItemEnum()); break;
                case GREEN: greenNecklaces.add(i.getItemEnum()); break;
                case BLUE: blueNecklaces.add(i.getItemEnum()); break;
                case VIOLET: violetNecklaces.add(i.getItemEnum()); break;
                case ORANGE: orangeNecklaces.add(i.getItemEnum()); break;
                case RED: redNecklaces.add(i.getItemEnum()); break;
            }
        }
        necklaceRarities.put(ItemRarityEnum.WHITE, whiteNecklaces.toArray(new ItemEnum[0]));
        necklaceRarities.put(ItemRarityEnum.GREEN, greenNecklaces.toArray(new ItemEnum[0]));
        necklaceRarities.put(ItemRarityEnum.BLUE, blueNecklaces.toArray(new ItemEnum[0]));
        necklaceRarities.put(ItemRarityEnum.VIOLET, violetNecklaces.toArray(new ItemEnum[0]));
        necklaceRarities.put(ItemRarityEnum.ORANGE, orangeNecklaces.toArray(new ItemEnum[0]));
        necklaceRarities.put(ItemRarityEnum.RED, redNecklaces.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.NECKLACE, necklaceRarities);

        //rings

        HashMap<ItemRarityEnum, ItemEnum[]> ringRarities = new HashMap<>();
        List<ItemEnum> whiteRings = new ArrayList<>();
        List<ItemEnum> greenRings = new ArrayList<>();
        List<ItemEnum> blueRings = new ArrayList<>();
        List<ItemEnum> violetRings = new ArrayList<>();
        List<ItemEnum> orangeRings = new ArrayList<>();
        List<ItemEnum> redRings = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicRingStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteRings.add(i.getItemEnum()); break;
                case GREEN: greenRings.add(i.getItemEnum()); break;
                case BLUE: blueRings.add(i.getItemEnum()); break;
                case VIOLET: violetRings.add(i.getItemEnum()); break;
                case ORANGE: orangeRings.add(i.getItemEnum()); break;
                case RED: redRings.add(i.getItemEnum()); break;
            }
        }
        ringRarities.put(ItemRarityEnum.WHITE, whiteRings.toArray(new ItemEnum[0]));
        ringRarities.put(ItemRarityEnum.GREEN, greenRings.toArray(new ItemEnum[0]));
        ringRarities.put(ItemRarityEnum.BLUE, blueRings.toArray(new ItemEnum[0]));
        ringRarities.put(ItemRarityEnum.VIOLET, violetRings.toArray(new ItemEnum[0]));
        ringRarities.put(ItemRarityEnum.ORANGE, orangeRings.toArray(new ItemEnum[0]));
        ringRarities.put(ItemRarityEnum.RED, redRings.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.RING, ringRarities);

        //mele

        HashMap<ItemRarityEnum, ItemEnum[]> meleRarities = new HashMap<>();
        List<ItemEnum> whiteMeles = new ArrayList<>();
        List<ItemEnum> greenMeles = new ArrayList<>();
        List<ItemEnum> blueMeles = new ArrayList<>();
        List<ItemEnum> violetMeles = new ArrayList<>();
        List<ItemEnum> orangeMeles = new ArrayList<>();
        List<ItemEnum> redMeles = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicMeleStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteMeles.add(i.getItemEnum()); break;
                case GREEN: greenMeles.add(i.getItemEnum()); break;
                case BLUE: blueMeles.add(i.getItemEnum()); break;
                case VIOLET: violetMeles.add(i.getItemEnum()); break;
                case ORANGE: orangeMeles.add(i.getItemEnum()); break;
                case RED: redMeles.add(i.getItemEnum()); break;
            }
        }
        meleRarities.put(ItemRarityEnum.WHITE, whiteMeles.toArray(new ItemEnum[0]));
        meleRarities.put(ItemRarityEnum.GREEN, greenMeles.toArray(new ItemEnum[0]));
        meleRarities.put(ItemRarityEnum.BLUE, blueMeles.toArray(new ItemEnum[0]));
        meleRarities.put(ItemRarityEnum.VIOLET, violetMeles.toArray(new ItemEnum[0]));
        meleRarities.put(ItemRarityEnum.ORANGE, orangeMeles.toArray(new ItemEnum[0]));
        meleRarities.put(ItemRarityEnum.RED, redMeles.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.MELE, meleRarities);

        //staffs

        HashMap<ItemRarityEnum, ItemEnum[]> staffRarities = new HashMap<>();
        List<ItemEnum> whiteStaffs = new ArrayList<>();
        List<ItemEnum> greenStaffs = new ArrayList<>();
        List<ItemEnum> blueStaffs = new ArrayList<>();
        List<ItemEnum> violetStaffs = new ArrayList<>();
        List<ItemEnum> orangeStaffs = new ArrayList<>();
        List<ItemEnum> redStaffs = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicStaffStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteStaffs.add(i.getItemEnum()); break;
                case GREEN: greenStaffs.add(i.getItemEnum()); break;
                case BLUE: blueStaffs.add(i.getItemEnum()); break;
                case VIOLET: violetStaffs.add(i.getItemEnum()); break;
                case ORANGE: orangeStaffs.add(i.getItemEnum()); break;
                case RED: redStaffs.add(i.getItemEnum()); break;
            }
        }
        staffRarities.put(ItemRarityEnum.WHITE, whiteStaffs.toArray(new ItemEnum[0]));
        staffRarities.put(ItemRarityEnum.GREEN, greenStaffs.toArray(new ItemEnum[0]));
        staffRarities.put(ItemRarityEnum.BLUE, blueStaffs.toArray(new ItemEnum[0]));
        staffRarities.put(ItemRarityEnum.VIOLET, violetStaffs.toArray(new ItemEnum[0]));
        staffRarities.put(ItemRarityEnum.ORANGE, orangeStaffs.toArray(new ItemEnum[0]));
        staffRarities.put(ItemRarityEnum.RED, redStaffs.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.STAFF, staffRarities);

        //helmets

        HashMap<ItemRarityEnum, ItemEnum[]> helmetRarities = new HashMap<>();
        List<ItemEnum> whiteHelmets = new ArrayList<>();
        List<ItemEnum> greenHelmets = new ArrayList<>();
        List<ItemEnum> blueHelmets = new ArrayList<>();
        List<ItemEnum> violetHelmets = new ArrayList<>();
        List<ItemEnum> orangeHelmets = new ArrayList<>();
        List<ItemEnum> redHelmets = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicHelmetStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteHelmets.add(i.getItemEnum()); break;
                case GREEN: greenHelmets.add(i.getItemEnum()); break;
                case BLUE: blueHelmets.add(i.getItemEnum()); break;
                case VIOLET: violetHelmets.add(i.getItemEnum()); break;
                case ORANGE: orangeHelmets.add(i.getItemEnum()); break;
                case RED: redHelmets.add(i.getItemEnum()); break;
            }
        }
        helmetRarities.put(ItemRarityEnum.WHITE, whiteHelmets.toArray(new ItemEnum[0]));
        helmetRarities.put(ItemRarityEnum.GREEN, greenHelmets.toArray(new ItemEnum[0]));
        helmetRarities.put(ItemRarityEnum.BLUE, blueHelmets.toArray(new ItemEnum[0]));
        helmetRarities.put(ItemRarityEnum.VIOLET, violetHelmets.toArray(new ItemEnum[0]));
        helmetRarities.put(ItemRarityEnum.ORANGE, orangeHelmets.toArray(new ItemEnum[0]));
        helmetRarities.put(ItemRarityEnum.RED, redHelmets.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.HELMET, helmetRarities);

        //Foods

        HashMap<ItemRarityEnum, ItemEnum[]> foodRarities = new HashMap<>();
        List<ItemEnum> whiteFoods = new ArrayList<>();
        List<ItemEnum> greenFoods = new ArrayList<>();
        List<ItemEnum> blueFoods = new ArrayList<>();
        List<ItemEnum> violetFoods = new ArrayList<>();
        List<ItemEnum> orangeFoods = new ArrayList<>();
        List<ItemEnum> redFoods = new ArrayList<>();

        for (ItemPrototype i : ItemStatsConfig.basicFoodStats.values()) {
            switch (i.getRARITY()) {
                case WHITE: whiteFoods.add(i.getItemEnum()); break;
                case GREEN: greenFoods.add(i.getItemEnum()); break;
                case BLUE: blueFoods.add(i.getItemEnum()); break;
                case VIOLET: violetFoods.add(i.getItemEnum()); break;
                case ORANGE: orangeFoods.add(i.getItemEnum()); break;
                case RED: redFoods.add(i.getItemEnum()); break;
            }
        }
        foodRarities.put(ItemRarityEnum.WHITE, whiteFoods.toArray(new ItemEnum[0]));
        foodRarities.put(ItemRarityEnum.GREEN, greenFoods.toArray(new ItemEnum[0]));
        foodRarities.put(ItemRarityEnum.BLUE, blueFoods.toArray(new ItemEnum[0]));
        foodRarities.put(ItemRarityEnum.VIOLET, violetFoods.toArray(new ItemEnum[0]));
        foodRarities.put(ItemRarityEnum.ORANGE, orangeFoods.toArray(new ItemEnum[0]));
        foodRarities.put(ItemRarityEnum.RED, redFoods.toArray(new ItemEnum[0]));

        itemRarities.put(ItemTypeEnum.FOOD, foodRarities);



    }

}
