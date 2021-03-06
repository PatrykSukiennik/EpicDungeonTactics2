package com.appatstudio.epicdungeontactics2.global.stats.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;

import java.util.HashMap;
import java.util.Map;

public final class CharacterStats {

    private static final Map<CharacterEnum, CharacterPrototype> characterPrototypes;
    private static final Map<CharacterEnum, CharacterPrototype> enemyLvlUpStats;
    private static final Map<CharacterEnum, Integer> characterSizes;
    private static final Map<CharacterEnum, Integer> characterSpeeds;
    private static final Map<CharacterEnum, Integer> characterRanges;
    private static final Map<CharacterEnum, Integer> characterBasicGoldRewards;
    private static final Map<CharacterEnum, Integer> characterLvlGoldRewards;
    private static final Map<CharacterEnum, Integer> characterBasicExpRewards;
    private static final Map<CharacterEnum, Integer> characterLvlExpRewards;

    static {
        characterPrototypes = new HashMap<>();
        characterPrototypes.put(CharacterEnum.HERO_ELF, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.HERO_KNIGHT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.HERO_WIZZARD, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.HERO_LIZARD, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.HERO_NINJA, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.HERO_PIRATE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.HERO_BABY, new CharacterPrototype(3, 3, 3, 3, 3));

        //forest
        characterPrototypes.put(CharacterEnum.CENTAUR_MALE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.CENTAUR_FEMALE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ENT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.TROLL, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.WOLF, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.MUSHROOM_SMALL, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.MUSHROOM_NORMAL, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.MUSHROOM_LARGE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.BEAR, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.BOSS_FOREST_GUARDIAN, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ELVEN_PRINCESS, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ELVEN_KING, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ELVEN_KNIGHT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.GNOLL_BRUTE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.GNOLL_OVERSEER, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.GNOLL_SCOUT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.GNOLL_SHAMAN, new CharacterPrototype(3, 3, 3, 3, 3));

        //npcs
        characterPrototypes.put(CharacterEnum.NPC_ALCHEMIST, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_CITIZEN_FEMALE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_CITIZEN_MALE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_PRINCESS, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_THIEF, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_BISHOP, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_NUN_FAT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_NUN_NORMAL, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_MAGIC_SHOP, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_BLACKSMITH, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_KING, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NPC_KNIGHT_ELITE, new CharacterPrototype(3, 3, 3, 3, 3));

        //pets
        characterPrototypes.put(CharacterEnum.PET_KNIGHT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.PET_HUNTER, new CharacterPrototype(3, 3, 3, 3, 3));

        //tier1
        characterPrototypes.put(CharacterEnum.TINY_ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.MUDDY, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ICE_ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.GOBLIN, new CharacterPrototype(3, 3, 3, 3, 3));

        characterPrototypes.put(CharacterEnum.BIG_ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));


        //tier2
        characterPrototypes.put(CharacterEnum.ORC_SHAMAN, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.MASKED_ORC, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.ORC_WARRIOR, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.SWAMPY, new CharacterPrototype(3, 3, 3, 3, 3));

        characterPrototypes.put(CharacterEnum.BOSS_OGRE, new CharacterPrototype(3, 3, 3, 3, 3));


        //tier3
        characterPrototypes.put(CharacterEnum.CHORT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.IMP, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NECROMANCER, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.SKELET, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.WOGOL, new CharacterPrototype(3, 3, 3, 3, 3));

        characterPrototypes.put(CharacterEnum.BOSS_BIG_DEMON, new CharacterPrototype(3, 3, 3, 3, 3));


        enemyLvlUpStats = new HashMap<>();
        //forest
        enemyLvlUpStats.put(CharacterEnum.CENTAUR_MALE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.CENTAUR_FEMALE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ENT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.TROLL, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.WOLF, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.MUSHROOM_SMALL, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.MUSHROOM_NORMAL, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.MUSHROOM_LARGE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.BEAR, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.BOSS_FOREST_GUARDIAN, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ELVEN_PRINCESS, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ELVEN_KING, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ELVEN_KNIGHT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.GNOLL_BRUTE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.GNOLL_OVERSEER, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.GNOLL_SCOUT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.GNOLL_SHAMAN, new CharacterPrototype(3, 3, 3, 3, 3));

        //npcs
        enemyLvlUpStats.put(CharacterEnum.NPC_ALCHEMIST, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_CITIZEN_FEMALE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_CITIZEN_MALE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_PRINCESS, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_THIEF, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_BISHOP, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_NUN_FAT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_NUN_NORMAL, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_MAGIC_SHOP, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_BLACKSMITH, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_KING, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NPC_KNIGHT_ELITE, new CharacterPrototype(3, 3, 3, 3, 3));

        //pets
        enemyLvlUpStats.put(CharacterEnum.PET_KNIGHT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.PET_HUNTER, new CharacterPrototype(3, 3, 3, 3, 3));

        //tier1
        enemyLvlUpStats.put(CharacterEnum.TINY_ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.MUDDY, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ICE_ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.GOBLIN, new CharacterPrototype(3, 3, 3, 3, 3));

        enemyLvlUpStats.put(CharacterEnum.BIG_ZOMBIE, new CharacterPrototype(3, 3, 3, 3, 3));


        //tier2
        enemyLvlUpStats.put(CharacterEnum.ORC_SHAMAN, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.MASKED_ORC, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.ORC_WARRIOR, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.SWAMPY, new CharacterPrototype(3, 3, 3, 3, 3));

        enemyLvlUpStats.put(CharacterEnum.BOSS_OGRE, new CharacterPrototype(3, 3, 3, 3, 3));


        //tier3
        enemyLvlUpStats.put(CharacterEnum.CHORT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.IMP, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NECROMANCER, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.SKELET, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.WOGOL, new CharacterPrototype(3, 3, 3, 3, 3));

        enemyLvlUpStats.put(CharacterEnum.BOSS_BIG_DEMON, new CharacterPrototype(3, 3, 3, 3, 3));


        characterSizes = new HashMap<>();
        characterSizes.put(CharacterEnum.HERO_ELF, 1);
        characterSizes.put(CharacterEnum.HERO_KNIGHT, 1);
        characterSizes.put(CharacterEnum.HERO_WIZZARD, 1);
        characterSizes.put(CharacterEnum.HERO_LIZARD, 1);
        characterSizes.put(CharacterEnum.HERO_NINJA, 1);
        characterSizes.put(CharacterEnum.HERO_PIRATE, 1);
        characterSizes.put(CharacterEnum.HERO_BABY, 1);

        //forest
        characterSizes.put(CharacterEnum.CENTAUR_MALE, 1);
        characterSizes.put(CharacterEnum.CENTAUR_FEMALE, 1);
        characterSizes.put(CharacterEnum.ENT, 1);
        characterSizes.put(CharacterEnum.TROLL, 1);
        characterSizes.put(CharacterEnum.WOLF, 1);
        characterSizes.put(CharacterEnum.MUSHROOM_SMALL, 1);
        characterSizes.put(CharacterEnum.MUSHROOM_NORMAL,1);
        characterSizes.put(CharacterEnum.MUSHROOM_LARGE, 1);
        characterSizes.put(CharacterEnum.BEAR, 2);
        characterSizes.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 1);
        characterSizes.put(CharacterEnum.ELVEN_PRINCESS, 1);
        characterSizes.put(CharacterEnum.ELVEN_KING, 1);
        characterSizes.put(CharacterEnum.ELVEN_KNIGHT, 1);
        characterSizes.put(CharacterEnum.GNOLL_BRUTE, 1);
        characterSizes.put(CharacterEnum.GNOLL_OVERSEER, 1);
        characterSizes.put(CharacterEnum.GNOLL_SCOUT, 1);
        characterSizes.put(CharacterEnum.GNOLL_SHAMAN, 1);

        //npcs
        characterSizes.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterSizes.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterSizes.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterSizes.put(CharacterEnum.NPC_PRINCESS, 1);
        characterSizes.put(CharacterEnum.NPC_THIEF, 1);
        characterSizes.put(CharacterEnum.NPC_BISHOP, 1);
        characterSizes.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterSizes.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterSizes.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterSizes.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterSizes.put(CharacterEnum.NPC_KING, 1);
        characterSizes.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterSizes.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterSizes.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterSizes.put(CharacterEnum.PET_KNIGHT, 1);
        characterSizes.put(CharacterEnum.PET_HUNTER, 1);

        //tier1
        characterSizes.put(CharacterEnum.TINY_ZOMBIE, 1);
        characterSizes.put(CharacterEnum.ZOMBIE, 1);
        characterSizes.put(CharacterEnum.MUDDY, 1);
        characterSizes.put(CharacterEnum.ICE_ZOMBIE, 1);
        characterSizes.put(CharacterEnum.GOBLIN, 1);

        characterSizes.put(CharacterEnum.BIG_ZOMBIE, 1);


        //tier2
        characterSizes.put(CharacterEnum.ORC_SHAMAN, 1);
        characterSizes.put(CharacterEnum.MASKED_ORC, 1);
        characterSizes.put(CharacterEnum.ORC_WARRIOR,1);
        characterSizes.put(CharacterEnum.SWAMPY, 1);

        characterSizes.put(CharacterEnum.BOSS_OGRE, 3);


        //tier3
        characterSizes.put(CharacterEnum.CHORT, 1);
        characterSizes.put(CharacterEnum.IMP, 1);
        characterSizes.put(CharacterEnum.NECROMANCER, 1);
        characterSizes.put(CharacterEnum.SKELET, 1);
        characterSizes.put(CharacterEnum.WOGOL, 1);

        characterSizes.put(CharacterEnum.BOSS_BIG_DEMON, 3);


        characterSpeeds = new HashMap<>();
        characterSpeeds.put(CharacterEnum.HERO_ELF, 5);
        characterSpeeds.put(CharacterEnum.HERO_KNIGHT, 5);
        characterSpeeds.put(CharacterEnum.HERO_WIZZARD, 5);
        characterSpeeds.put(CharacterEnum.HERO_LIZARD, 5);
        characterSpeeds.put(CharacterEnum.HERO_NINJA, 5);
        characterSpeeds.put(CharacterEnum.HERO_PIRATE, 5);
        characterSpeeds.put(CharacterEnum.HERO_BABY, 5);

        //forest
        characterSpeeds.put(CharacterEnum.CENTAUR_MALE, 8);
        characterSpeeds.put(CharacterEnum.CENTAUR_FEMALE, 8);
        characterSpeeds.put(CharacterEnum.ENT, 5);
        characterSpeeds.put(CharacterEnum.TROLL, 5);
        characterSpeeds.put(CharacterEnum.WOLF, 5);
        characterSpeeds.put(CharacterEnum.MUSHROOM_SMALL, 5);
        characterSpeeds.put(CharacterEnum.MUSHROOM_NORMAL,5);
        characterSpeeds.put(CharacterEnum.MUSHROOM_LARGE, 5);
        characterSpeeds.put(CharacterEnum.BEAR, 5);
        characterSpeeds.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 5);
        characterSpeeds.put(CharacterEnum.ELVEN_PRINCESS, 5);
        characterSpeeds.put(CharacterEnum.ELVEN_KING, 5);
        characterSpeeds.put(CharacterEnum.ELVEN_KNIGHT, 5);
        characterSpeeds.put(CharacterEnum.GNOLL_BRUTE, 5);
        characterSpeeds.put(CharacterEnum.GNOLL_OVERSEER, 5);
        characterSpeeds.put(CharacterEnum.GNOLL_SCOUT, 5);
        characterSpeeds.put(CharacterEnum.GNOLL_SHAMAN, 5);

        //npcs
        characterSpeeds.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterSpeeds.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterSpeeds.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterSpeeds.put(CharacterEnum.NPC_PRINCESS, 1);
        characterSpeeds.put(CharacterEnum.NPC_THIEF, 1);
        characterSpeeds.put(CharacterEnum.NPC_BISHOP, 1);
        characterSpeeds.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterSpeeds.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterSpeeds.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterSpeeds.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterSpeeds.put(CharacterEnum.NPC_KING, 1);
        characterSpeeds.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterSpeeds.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterSpeeds.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterSpeeds.put(CharacterEnum.PET_KNIGHT, 5);
        characterSpeeds.put(CharacterEnum.PET_HUNTER, 8);

        //tier1
        characterSpeeds.put(CharacterEnum.TINY_ZOMBIE, 7);
        characterSpeeds.put(CharacterEnum.ZOMBIE, 7);
        characterSpeeds.put(CharacterEnum.MUDDY, 4);
        characterSpeeds.put(CharacterEnum.ICE_ZOMBIE, 7);
        characterSpeeds.put(CharacterEnum.GOBLIN, 6);

        characterSpeeds.put(CharacterEnum.BIG_ZOMBIE, 9);


        //tier2
        characterSpeeds.put(CharacterEnum.ORC_SHAMAN, 5);
        characterSpeeds.put(CharacterEnum.MASKED_ORC, 8);
        characterSpeeds.put(CharacterEnum.ORC_WARRIOR,7);
        characterSpeeds.put(CharacterEnum.SWAMPY, 5);

        characterSpeeds.put(CharacterEnum.BOSS_OGRE, 9);


        //tier3
        characterSpeeds.put(CharacterEnum.CHORT, 9);
        characterSpeeds.put(CharacterEnum.IMP, 8);
        characterSpeeds.put(CharacterEnum.NECROMANCER, 4);
        characterSpeeds.put(CharacterEnum.SKELET, 6);
        characterSpeeds.put(CharacterEnum.WOGOL, 9);

        characterSpeeds.put(CharacterEnum.BOSS_BIG_DEMON, 5);


        characterRanges = new HashMap<>();
        characterRanges.put(CharacterEnum.HERO_ELF, 1);
        characterRanges.put(CharacterEnum.HERO_KNIGHT, 1);
        characterRanges.put(CharacterEnum.HERO_WIZZARD, 1);
        characterRanges.put(CharacterEnum.HERO_LIZARD, 1);
        characterRanges.put(CharacterEnum.HERO_NINJA, 1);
        characterRanges.put(CharacterEnum.HERO_PIRATE, 1);
        characterRanges.put(CharacterEnum.HERO_BABY, 1);

        //forest
        characterRanges.put(CharacterEnum.CENTAUR_MALE, 1);
        characterRanges.put(CharacterEnum.CENTAUR_FEMALE, 7);
        characterRanges.put(CharacterEnum.ENT, 1);
        characterRanges.put(CharacterEnum.TROLL, 1);
        characterRanges.put(CharacterEnum.WOLF, 1);
        characterRanges.put(CharacterEnum.MUSHROOM_SMALL, 1);
        characterRanges.put(CharacterEnum.MUSHROOM_NORMAL,1);
        characterRanges.put(CharacterEnum.MUSHROOM_LARGE, 1);
        characterRanges.put(CharacterEnum.BEAR, 1);
        characterRanges.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 1);
        characterRanges.put(CharacterEnum.ELVEN_PRINCESS, 8);
        characterRanges.put(CharacterEnum.ELVEN_KING, 1);
        characterRanges.put(CharacterEnum.ELVEN_KNIGHT, 1);
        characterRanges.put(CharacterEnum.GNOLL_BRUTE, 1);
        characterRanges.put(CharacterEnum.GNOLL_OVERSEER, 1);
        characterRanges.put(CharacterEnum.GNOLL_SCOUT, 1);
        characterRanges.put(CharacterEnum.GNOLL_SHAMAN, 9);

        //npcs
        characterRanges.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterRanges.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterRanges.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterRanges.put(CharacterEnum.NPC_PRINCESS, 1);
        characterRanges.put(CharacterEnum.NPC_THIEF, 1);
        characterRanges.put(CharacterEnum.NPC_BISHOP, 1);
        characterRanges.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterRanges.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterRanges.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterRanges.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterRanges.put(CharacterEnum.NPC_KING, 1);
        characterRanges.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterRanges.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterRanges.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterRanges.put(CharacterEnum.PET_KNIGHT, 1);
        characterRanges.put(CharacterEnum.PET_HUNTER, 9);

        //tier1
        characterRanges.put(CharacterEnum.TINY_ZOMBIE, 1);
        characterRanges.put(CharacterEnum.ZOMBIE, 7);
        characterRanges.put(CharacterEnum.MUDDY, 1);
        characterRanges.put(CharacterEnum.ICE_ZOMBIE, 7);
        characterRanges.put(CharacterEnum.GOBLIN, 1);

        characterRanges.put(CharacterEnum.BIG_ZOMBIE, 1);


        //tier2
        characterRanges.put(CharacterEnum.ORC_SHAMAN, 7);
        characterRanges.put(CharacterEnum.MASKED_ORC, 1);
        characterRanges.put(CharacterEnum.ORC_WARRIOR,1);
        characterRanges.put(CharacterEnum.SWAMPY, 1);

        characterRanges.put(CharacterEnum.BOSS_OGRE, 3);


        //tier3
        characterRanges.put(CharacterEnum.CHORT, 1);
        characterRanges.put(CharacterEnum.IMP, 1);
        characterRanges.put(CharacterEnum.NECROMANCER, 1);
        characterRanges.put(CharacterEnum.SKELET, 8);
        characterRanges.put(CharacterEnum.WOGOL, 1);

        characterRanges.put(CharacterEnum.BOSS_BIG_DEMON, 9);




        characterBasicGoldRewards = new HashMap<>();
        characterBasicGoldRewards.put(CharacterEnum.HERO_ELF, 5);
        characterBasicGoldRewards.put(CharacterEnum.HERO_KNIGHT, 5);
        characterBasicGoldRewards.put(CharacterEnum.HERO_WIZZARD, 5);
        characterBasicGoldRewards.put(CharacterEnum.HERO_LIZARD, 5);
        characterBasicGoldRewards.put(CharacterEnum.HERO_NINJA, 5);
        characterBasicGoldRewards.put(CharacterEnum.HERO_PIRATE, 5);
        characterBasicGoldRewards.put(CharacterEnum.HERO_BABY, 5);

        //forest
        characterBasicGoldRewards.put(CharacterEnum.CENTAUR_MALE, 8);
        characterBasicGoldRewards.put(CharacterEnum.CENTAUR_FEMALE, 8);
        characterBasicGoldRewards.put(CharacterEnum.ENT, 5);
        characterBasicGoldRewards.put(CharacterEnum.TROLL, 5);
        characterBasicGoldRewards.put(CharacterEnum.WOLF, 5);
        characterBasicGoldRewards.put(CharacterEnum.MUSHROOM_SMALL, 5);
        characterBasicGoldRewards.put(CharacterEnum.MUSHROOM_NORMAL,5);
        characterBasicGoldRewards.put(CharacterEnum.MUSHROOM_LARGE, 5);
        characterBasicGoldRewards.put(CharacterEnum.BEAR, 5);
        characterBasicGoldRewards.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 5);
        characterBasicGoldRewards.put(CharacterEnum.ELVEN_PRINCESS, 5);
        characterBasicGoldRewards.put(CharacterEnum.ELVEN_KING, 5);
        characterBasicGoldRewards.put(CharacterEnum.ELVEN_KNIGHT, 5);
        characterBasicGoldRewards.put(CharacterEnum.GNOLL_BRUTE, 5);
        characterBasicGoldRewards.put(CharacterEnum.GNOLL_OVERSEER, 5);
        characterBasicGoldRewards.put(CharacterEnum.GNOLL_SCOUT, 5);
        characterBasicGoldRewards.put(CharacterEnum.GNOLL_SHAMAN, 5);

        //npcs
        characterBasicGoldRewards.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_PRINCESS, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_THIEF, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_BISHOP, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_KING, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterBasicGoldRewards.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterBasicGoldRewards.put(CharacterEnum.PET_KNIGHT, 5);
        characterBasicGoldRewards.put(CharacterEnum.PET_HUNTER, 8);

        //tier1
        characterBasicGoldRewards.put(CharacterEnum.TINY_ZOMBIE, 7);
        characterBasicGoldRewards.put(CharacterEnum.ZOMBIE, 7);
        characterBasicGoldRewards.put(CharacterEnum.MUDDY, 4);
        characterBasicGoldRewards.put(CharacterEnum.ICE_ZOMBIE, 7);
        characterBasicGoldRewards.put(CharacterEnum.GOBLIN, 6);

        characterBasicGoldRewards.put(CharacterEnum.BIG_ZOMBIE, 9);


        //tier2
        characterBasicGoldRewards.put(CharacterEnum.ORC_SHAMAN, 5);
        characterBasicGoldRewards.put(CharacterEnum.MASKED_ORC, 8);
        characterBasicGoldRewards.put(CharacterEnum.ORC_WARRIOR,7);
        characterBasicGoldRewards.put(CharacterEnum.SWAMPY, 5);

        characterBasicGoldRewards.put(CharacterEnum.BOSS_OGRE, 9);


        //tier3
        characterBasicGoldRewards.put(CharacterEnum.CHORT, 9);
        characterBasicGoldRewards.put(CharacterEnum.IMP, 8);
        characterBasicGoldRewards.put(CharacterEnum.NECROMANCER, 4);
        characterBasicGoldRewards.put(CharacterEnum.SKELET, 6);
        characterBasicGoldRewards.put(CharacterEnum.WOGOL, 9);

        characterBasicGoldRewards.put(CharacterEnum.BOSS_BIG_DEMON, 5);







        characterLvlGoldRewards = new HashMap<>();
        characterLvlGoldRewards.put(CharacterEnum.HERO_ELF, 5);
        characterLvlGoldRewards.put(CharacterEnum.HERO_KNIGHT, 5);
        characterLvlGoldRewards.put(CharacterEnum.HERO_WIZZARD, 5);
        characterLvlGoldRewards.put(CharacterEnum.HERO_LIZARD, 5);
        characterLvlGoldRewards.put(CharacterEnum.HERO_NINJA, 5);
        characterLvlGoldRewards.put(CharacterEnum.HERO_PIRATE, 5);
        characterLvlGoldRewards.put(CharacterEnum.HERO_BABY, 5);

        //forest
        characterLvlGoldRewards.put(CharacterEnum.CENTAUR_MALE, 8);
        characterLvlGoldRewards.put(CharacterEnum.CENTAUR_FEMALE, 8);
        characterLvlGoldRewards.put(CharacterEnum.ENT, 5);
        characterLvlGoldRewards.put(CharacterEnum.TROLL, 5);
        characterLvlGoldRewards.put(CharacterEnum.WOLF, 5);
        characterLvlGoldRewards.put(CharacterEnum.MUSHROOM_SMALL, 5);
        characterLvlGoldRewards.put(CharacterEnum.MUSHROOM_NORMAL,5);
        characterLvlGoldRewards.put(CharacterEnum.MUSHROOM_LARGE, 5);
        characterLvlGoldRewards.put(CharacterEnum.BEAR, 5);
        characterLvlGoldRewards.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 5);
        characterLvlGoldRewards.put(CharacterEnum.ELVEN_PRINCESS, 5);
        characterLvlGoldRewards.put(CharacterEnum.ELVEN_KING, 5);
        characterLvlGoldRewards.put(CharacterEnum.ELVEN_KNIGHT, 5);
        characterLvlGoldRewards.put(CharacterEnum.GNOLL_BRUTE, 5);
        characterLvlGoldRewards.put(CharacterEnum.GNOLL_OVERSEER, 5);
        characterLvlGoldRewards.put(CharacterEnum.GNOLL_SCOUT, 5);
        characterLvlGoldRewards.put(CharacterEnum.GNOLL_SHAMAN, 5);

        //npcs
        characterLvlGoldRewards.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_PRINCESS, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_THIEF, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_BISHOP, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_KING, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterLvlGoldRewards.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterLvlGoldRewards.put(CharacterEnum.PET_KNIGHT, 5);
        characterLvlGoldRewards.put(CharacterEnum.PET_HUNTER, 8);

        //tier1
        characterLvlGoldRewards.put(CharacterEnum.TINY_ZOMBIE, 7);
        characterLvlGoldRewards.put(CharacterEnum.ZOMBIE, 7);
        characterLvlGoldRewards.put(CharacterEnum.MUDDY, 4);
        characterLvlGoldRewards.put(CharacterEnum.ICE_ZOMBIE, 7);
        characterLvlGoldRewards.put(CharacterEnum.GOBLIN, 6);

        characterLvlGoldRewards.put(CharacterEnum.BIG_ZOMBIE, 9);


        //tier2
        characterLvlGoldRewards.put(CharacterEnum.ORC_SHAMAN, 5);
        characterLvlGoldRewards.put(CharacterEnum.MASKED_ORC, 8);
        characterLvlGoldRewards.put(CharacterEnum.ORC_WARRIOR,7);
        characterLvlGoldRewards.put(CharacterEnum.SWAMPY, 5);

        characterLvlGoldRewards.put(CharacterEnum.BOSS_OGRE, 9);


        //tier3
        characterLvlGoldRewards.put(CharacterEnum.CHORT, 9);
        characterLvlGoldRewards.put(CharacterEnum.IMP, 8);
        characterLvlGoldRewards.put(CharacterEnum.NECROMANCER, 4);
        characterLvlGoldRewards.put(CharacterEnum.SKELET, 6);
        characterLvlGoldRewards.put(CharacterEnum.WOGOL, 9);

        characterLvlGoldRewards.put(CharacterEnum.BOSS_BIG_DEMON, 5);







        characterBasicExpRewards = new HashMap<>();
        characterBasicExpRewards.put(CharacterEnum.HERO_ELF, 5);
        characterBasicExpRewards.put(CharacterEnum.HERO_KNIGHT, 5);
        characterBasicExpRewards.put(CharacterEnum.HERO_WIZZARD, 5);
        characterBasicExpRewards.put(CharacterEnum.HERO_LIZARD, 5);
        characterBasicExpRewards.put(CharacterEnum.HERO_NINJA, 5);
        characterBasicExpRewards.put(CharacterEnum.HERO_PIRATE, 5);
        characterBasicExpRewards.put(CharacterEnum.HERO_BABY, 5);

        //forest
        characterBasicExpRewards.put(CharacterEnum.CENTAUR_MALE, 8);
        characterBasicExpRewards.put(CharacterEnum.CENTAUR_FEMALE, 8);
        characterBasicExpRewards.put(CharacterEnum.ENT, 5);
        characterBasicExpRewards.put(CharacterEnum.TROLL, 5);
        characterBasicExpRewards.put(CharacterEnum.WOLF, 5);
        characterBasicExpRewards.put(CharacterEnum.MUSHROOM_SMALL, 5);
        characterBasicExpRewards.put(CharacterEnum.MUSHROOM_NORMAL,5);
        characterBasicExpRewards.put(CharacterEnum.MUSHROOM_LARGE, 5);
        characterBasicExpRewards.put(CharacterEnum.BEAR, 5);
        characterBasicExpRewards.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 5);
        characterBasicExpRewards.put(CharacterEnum.ELVEN_PRINCESS, 5);
        characterBasicExpRewards.put(CharacterEnum.ELVEN_KING, 5);
        characterBasicExpRewards.put(CharacterEnum.ELVEN_KNIGHT, 5);
        characterBasicExpRewards.put(CharacterEnum.GNOLL_BRUTE, 5);
        characterBasicExpRewards.put(CharacterEnum.GNOLL_OVERSEER, 5);
        characterBasicExpRewards.put(CharacterEnum.GNOLL_SCOUT, 5);
        characterBasicExpRewards.put(CharacterEnum.GNOLL_SHAMAN, 5);

        //npcs
        characterBasicExpRewards.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_PRINCESS, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_THIEF, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_BISHOP, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_KING, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterBasicExpRewards.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterBasicExpRewards.put(CharacterEnum.PET_KNIGHT, 5);
        characterBasicExpRewards.put(CharacterEnum.PET_HUNTER, 8);

        //tier1
        characterBasicExpRewards.put(CharacterEnum.TINY_ZOMBIE, 7);
        characterBasicExpRewards.put(CharacterEnum.ZOMBIE, 7);
        characterBasicExpRewards.put(CharacterEnum.MUDDY, 4);
        characterBasicExpRewards.put(CharacterEnum.ICE_ZOMBIE, 7);
        characterBasicExpRewards.put(CharacterEnum.GOBLIN, 6);

        characterBasicExpRewards.put(CharacterEnum.BIG_ZOMBIE, 9);


        //tier2
        characterBasicExpRewards.put(CharacterEnum.ORC_SHAMAN, 5);
        characterBasicExpRewards.put(CharacterEnum.MASKED_ORC, 8);
        characterBasicExpRewards.put(CharacterEnum.ORC_WARRIOR,7);
        characterBasicExpRewards.put(CharacterEnum.SWAMPY, 5);

        characterBasicExpRewards.put(CharacterEnum.BOSS_OGRE, 9);


        //tier3
        characterBasicExpRewards.put(CharacterEnum.CHORT, 9);
        characterBasicExpRewards.put(CharacterEnum.IMP, 8);
        characterBasicExpRewards.put(CharacterEnum.NECROMANCER, 4);
        characterBasicExpRewards.put(CharacterEnum.SKELET, 6);
        characterBasicExpRewards.put(CharacterEnum.WOGOL, 9);

        characterBasicExpRewards.put(CharacterEnum.BOSS_BIG_DEMON, 5);







        characterLvlExpRewards = new HashMap<>();
        characterLvlExpRewards.put(CharacterEnum.HERO_ELF, 5);
        characterLvlExpRewards.put(CharacterEnum.HERO_KNIGHT, 5);
        characterLvlExpRewards.put(CharacterEnum.HERO_WIZZARD, 5);
        characterLvlExpRewards.put(CharacterEnum.HERO_LIZARD, 5);
        characterLvlExpRewards.put(CharacterEnum.HERO_NINJA, 5);
        characterLvlExpRewards.put(CharacterEnum.HERO_PIRATE, 5);
        characterLvlExpRewards.put(CharacterEnum.HERO_BABY, 5);

        //forest
        characterLvlExpRewards.put(CharacterEnum.CENTAUR_MALE, 8);
        characterLvlExpRewards.put(CharacterEnum.CENTAUR_FEMALE, 8);
        characterLvlExpRewards.put(CharacterEnum.ENT, 5);
        characterLvlExpRewards.put(CharacterEnum.TROLL, 5);
        characterLvlExpRewards.put(CharacterEnum.WOLF, 5);
        characterLvlExpRewards.put(CharacterEnum.MUSHROOM_SMALL, 5);
        characterLvlExpRewards.put(CharacterEnum.MUSHROOM_NORMAL,5);
        characterLvlExpRewards.put(CharacterEnum.MUSHROOM_LARGE, 5);
        characterLvlExpRewards.put(CharacterEnum.BEAR, 5);
        characterLvlExpRewards.put(CharacterEnum.BOSS_FOREST_GUARDIAN, 5);
        characterLvlExpRewards.put(CharacterEnum.ELVEN_PRINCESS, 5);
        characterLvlExpRewards.put(CharacterEnum.ELVEN_KING, 5);
        characterLvlExpRewards.put(CharacterEnum.ELVEN_KNIGHT, 5);
        characterLvlExpRewards.put(CharacterEnum.GNOLL_BRUTE, 5);
        characterLvlExpRewards.put(CharacterEnum.GNOLL_OVERSEER, 5);
        characterLvlExpRewards.put(CharacterEnum.GNOLL_SCOUT, 5);
        characterLvlExpRewards.put(CharacterEnum.GNOLL_SHAMAN, 5);

        //npcs
        characterLvlExpRewards.put(CharacterEnum.NPC_ALCHEMIST, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_CITIZEN_FEMALE, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_CITIZEN_MALE, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_PRINCESS, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_THIEF, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_BISHOP, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_NUN_FAT, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_NUN_NORMAL, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_MAGIC_SHOP, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_BLACKSMITH, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_KING, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_KNIGHT_ELITE, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_MOUNTAIN_KING, 1);
        characterLvlExpRewards.put(CharacterEnum.NPC_BUTCHER, 1);

        //pets
        characterLvlExpRewards.put(CharacterEnum.PET_KNIGHT, 5);
        characterLvlExpRewards.put(CharacterEnum.PET_HUNTER, 8);

        //tier1
        characterLvlExpRewards.put(CharacterEnum.TINY_ZOMBIE, 7);
        characterLvlExpRewards.put(CharacterEnum.ZOMBIE, 7);
        characterLvlExpRewards.put(CharacterEnum.MUDDY, 4);
        characterLvlExpRewards.put(CharacterEnum.ICE_ZOMBIE, 7);
        characterLvlExpRewards.put(CharacterEnum.GOBLIN, 6);

        characterLvlExpRewards.put(CharacterEnum.BIG_ZOMBIE, 9);


        //tier2
        characterLvlExpRewards.put(CharacterEnum.ORC_SHAMAN, 5);
        characterLvlExpRewards.put(CharacterEnum.MASKED_ORC, 8);
        characterLvlExpRewards.put(CharacterEnum.ORC_WARRIOR,7);
        characterLvlExpRewards.put(CharacterEnum.SWAMPY, 5);

        characterLvlExpRewards.put(CharacterEnum.BOSS_OGRE, 9);


        //tier3
        characterLvlExpRewards.put(CharacterEnum.CHORT, 9);
        characterLvlExpRewards.put(CharacterEnum.IMP, 8);
        characterLvlExpRewards.put(CharacterEnum.NECROMANCER, 4);
        characterLvlExpRewards.put(CharacterEnum.SKELET, 6);
        characterLvlExpRewards.put(CharacterEnum.WOGOL, 9);

        characterLvlExpRewards.put(CharacterEnum.BOSS_BIG_DEMON, 5);





    }

    public static CharacterPrototype getPrototype(CharacterEnum characterEnum) {
        return characterPrototypes.get(characterEnum);
    }
    public static CharacterPrototype getEnemyLvlUpStats(CharacterEnum characterEnum) {
        return enemyLvlUpStats.get(characterEnum);
    }

    public static int getCharacterSize(CharacterEnum characterEnum) {
        return characterSizes.get(characterEnum);
    }

    public static int getBasicHeroStat(CharacterEnum c, StatisticEnum s) {
        switch (s) {
            case STR: return characterPrototypes.get(c).getSTR();
            case DEX: return characterPrototypes.get(c).getDEX();
            case INT: return characterPrototypes.get(c).getINT();
            case VIT: return characterPrototypes.get(c).getVIT();
            case LCK: return characterPrototypes.get(c).getLCK();
            default: return 0;
        }
    }

    public static int getRange(CharacterEnum characterEnum) {
        return characterRanges.get(characterEnum);
    }

    public static int getSpeed(CharacterEnum characterEnum) {
        return characterSpeeds.get(characterEnum);
    }

    public static int getCharacterBasicExpReward(CharacterEnum characterEnum) {
        return characterBasicExpRewards.get(characterEnum);
    }

    public static int getCharacterBasicGoldReward(CharacterEnum characterEnum) {
        return characterBasicGoldRewards.get(characterEnum);
    }

    public static int getCharacterLvlExpReward(CharacterEnum characterEnum) {
        return characterLvlExpRewards.get(characterEnum);
    }

    public static int getCharacterLvlGoldReward(CharacterEnum characterEnum) {
        return characterLvlGoldRewards.get(characterEnum);
    }
}
