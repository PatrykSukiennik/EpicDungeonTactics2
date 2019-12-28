package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;

import java.util.HashMap;
import java.util.Map;

public final class CharacterStats {

    private static final Map<CharacterEnum, Integer> strengthMap;
    private static final Map<CharacterEnum, Integer> dexterityMap;
    private static final Map<CharacterEnum, Integer> intelligenceMap;
    private static final Map<CharacterEnum, Integer> vitalityMap;
    private static final Map<CharacterEnum, Integer> luckMap;

    static {
        strengthMap = new HashMap<>();
        strengthMap.put(CharacterEnum.HERO_ELF, 3);
        strengthMap.put(CharacterEnum.HERO_KNIGHT, 3);
        strengthMap.put(CharacterEnum.HERO_WIZZARD, 3);
        strengthMap.put(CharacterEnum.HERO_LIZARD, 3);
        strengthMap.put(CharacterEnum.HERO_NINJA, 3);
        strengthMap.put(CharacterEnum.HERO_PIRATE, 3);
        strengthMap.put(CharacterEnum.HERO_BABY, 3);

        dexterityMap = new HashMap<>();
        dexterityMap.put(CharacterEnum.HERO_ELF, 3);
        dexterityMap.put(CharacterEnum.HERO_KNIGHT, 3);
        dexterityMap.put(CharacterEnum.HERO_WIZZARD, 3);
        dexterityMap.put(CharacterEnum.HERO_LIZARD, 3);
        dexterityMap.put(CharacterEnum.HERO_NINJA, 3);
        dexterityMap.put(CharacterEnum.HERO_PIRATE, 3);
        dexterityMap.put(CharacterEnum.HERO_BABY, 3);

        intelligenceMap = new HashMap<>();
        intelligenceMap.put(CharacterEnum.HERO_ELF, 3);
        intelligenceMap.put(CharacterEnum.HERO_KNIGHT, 3);
        intelligenceMap.put(CharacterEnum.HERO_WIZZARD, 3);
        intelligenceMap.put(CharacterEnum.HERO_LIZARD, 3);
        intelligenceMap.put(CharacterEnum.HERO_NINJA, 3);
        intelligenceMap.put(CharacterEnum.HERO_PIRATE, 3);
        intelligenceMap.put(CharacterEnum.HERO_BABY, 3);

        vitalityMap = new HashMap<>();
        vitalityMap.put(CharacterEnum.HERO_ELF, 3);
        vitalityMap.put(CharacterEnum.HERO_KNIGHT, 3);
        vitalityMap.put(CharacterEnum.HERO_WIZZARD, 3);
        vitalityMap.put(CharacterEnum.HERO_LIZARD, 3);
        vitalityMap.put(CharacterEnum.HERO_NINJA, 3);
        vitalityMap.put(CharacterEnum.HERO_PIRATE, 3);
        vitalityMap.put(CharacterEnum.HERO_BABY, 3);

        luckMap = new HashMap<>();
        luckMap.put(CharacterEnum.HERO_ELF, 3);
        luckMap.put(CharacterEnum.HERO_KNIGHT, 3);
        luckMap.put(CharacterEnum.HERO_WIZZARD, 3);
        luckMap.put(CharacterEnum.HERO_LIZARD, 3);
        luckMap.put(CharacterEnum.HERO_NINJA, 3);
        luckMap.put(CharacterEnum.HERO_PIRATE, 3);
        luckMap.put(CharacterEnum.HERO_BABY, 3);

    }

    public static int getStat(CharacterEnum c, StatisticEnum s) {
        switch (s) {
            case DEX:
                return dexterityMap.get(c);
            case INT:
                return intelligenceMap.get(c);
            case STR:
                return strengthMap.get(c);
            case LCK:
                return luckMap.get(c);
            case VIT:
                return vitalityMap.get(c);
            default:
                return 1;
        }
    }
}
