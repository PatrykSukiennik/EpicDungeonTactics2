package com.appatstudio.epicdungeontactics2.global.stats.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;

import java.util.HashMap;
import java.util.Map;

public final class CharacterStats {

    private static final Map<CharacterEnum, CharacterPrototype> characterPrototypes;
    private static final Map<CharacterEnum, CharacterPrototype> enemyLvlUpStats;

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
        characterPrototypes.put(CharacterEnum.FOREST_GUARDIAN, new CharacterPrototype(3, 3, 3, 3, 3));
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
        characterPrototypes.put(CharacterEnum.PET_DOG, new CharacterPrototype(3, 3, 3, 3, 3));
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

        characterPrototypes.put(CharacterEnum.OGRE, new CharacterPrototype(3, 3, 3, 3, 3));


        //tier3
        characterPrototypes.put(CharacterEnum.CHORT, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.IMP, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.NECROMANCER, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.SKELET, new CharacterPrototype(3, 3, 3, 3, 3));
        characterPrototypes.put(CharacterEnum.WOGOL, new CharacterPrototype(3, 3, 3, 3, 3));

        characterPrototypes.put(CharacterEnum.BIG_DEMON, new CharacterPrototype(3, 3, 3, 3, 3));


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
        enemyLvlUpStats.put(CharacterEnum.FOREST_GUARDIAN, new CharacterPrototype(3, 3, 3, 3, 3));
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
        enemyLvlUpStats.put(CharacterEnum.PET_DOG, new CharacterPrototype(3, 3, 3, 3, 3));
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

        enemyLvlUpStats.put(CharacterEnum.OGRE, new CharacterPrototype(3, 3, 3, 3, 3));


        //tier3
        enemyLvlUpStats.put(CharacterEnum.CHORT, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.IMP, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.NECROMANCER, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.SKELET, new CharacterPrototype(3, 3, 3, 3, 3));
        enemyLvlUpStats.put(CharacterEnum.WOGOL, new CharacterPrototype(3, 3, 3, 3, 3));

        enemyLvlUpStats.put(CharacterEnum.BIG_DEMON, new CharacterPrototype(3, 3, 3, 3, 3));

    }

    public static CharacterPrototype getPrototype(CharacterEnum characterEnum) {
        return characterPrototypes.get(characterEnum);
    }
    public static CharacterPrototype getEnemeyLvlUpStats(CharacterEnum characterEnum) {
        return enemyLvlUpStats.get(characterEnum);
    }
    public static int getDefHeroStat(CharacterEnum c, StatisticEnum s) {
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
