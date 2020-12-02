package com.appatstudio.epicdungeontactics2.global.stats.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;

import java.util.HashMap;
import java.util.Map;

public final class CharacterStats {

    private static final Map<CharacterEnum, CharacterPrototype> characterPrototypes;
    private static final Map<CharacterEnum, CharacterPrototype> enemyLvlUpStats;
    private static final Map<CharacterEnum, Integer> characterSizes;

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

    }

    public static CharacterPrototype getPrototype(CharacterEnum characterEnum) {
        return characterPrototypes.get(characterEnum);
    }
    public static CharacterPrototype getEnemeyLvlUpStats(CharacterEnum characterEnum) {
        return enemyLvlUpStats.get(characterEnum);
    }

    public static int getCharacterSize(CharacterEnum characterEnum) {
        System.out.println("eve: " + characterEnum.toString());
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
}
