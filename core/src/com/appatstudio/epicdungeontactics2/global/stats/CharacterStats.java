package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;

import java.util.HashMap;
import java.util.Map;

public final class CharacterStats {

    private static final Map<CharacterEnum, Integer> strengthMap;
    private static final Map<CharacterEnum, Integer> dexterityMap;
    private static final Map<CharacterEnum, Integer> intelligenceMap;
    private static final Map<CharacterEnum, Integer> constitutionMap;
    private static final Map<CharacterEnum, Integer> luckMap;

    static {
        strengthMap = new HashMap<>();
        strengthMap.put(CharacterEnum.HERO_ADVENTURER, 3);
        strengthMap.put(CharacterEnum.HERO_KNIGHT, 3);
        strengthMap.put(CharacterEnum.HERO_WIZZARD, 3);
        strengthMap.put(CharacterEnum.HERO_LIZARD, 3);
        strengthMap.put(CharacterEnum.HERO_NINJA, 3);
        strengthMap.put(CharacterEnum.HERO_PIRATE, 3);
        strengthMap.put(CharacterEnum.HERO_BABY, 3);

        dexterityMap = new HashMap<>();
        dexterityMap.put(CharacterEnum.HERO_ADVENTURER, 3);
        dexterityMap.put(CharacterEnum.HERO_KNIGHT, 3);
        dexterityMap.put(CharacterEnum.HERO_WIZZARD, 3);
        dexterityMap.put(CharacterEnum.HERO_LIZARD, 3);
        dexterityMap.put(CharacterEnum.HERO_NINJA, 3);
        dexterityMap.put(CharacterEnum.HERO_PIRATE, 3);
        dexterityMap.put(CharacterEnum.HERO_BABY, 3);

        intelligenceMap = new HashMap<>();
        intelligenceMap.put(CharacterEnum.HERO_ADVENTURER, 3);
        intelligenceMap.put(CharacterEnum.HERO_KNIGHT, 3);
        intelligenceMap.put(CharacterEnum.HERO_WIZZARD, 3);
        intelligenceMap.put(CharacterEnum.HERO_LIZARD, 3);
        intelligenceMap.put(CharacterEnum.HERO_NINJA, 3);
        intelligenceMap.put(CharacterEnum.HERO_PIRATE, 3);
        intelligenceMap.put(CharacterEnum.HERO_BABY, 3);

        constitutionMap = new HashMap<>();
        constitutionMap.put(CharacterEnum.HERO_ADVENTURER, 3);
        constitutionMap.put(CharacterEnum.HERO_KNIGHT, 3);
        constitutionMap.put(CharacterEnum.HERO_WIZZARD, 3);
        constitutionMap.put(CharacterEnum.HERO_LIZARD, 3);
        constitutionMap.put(CharacterEnum.HERO_NINJA, 3);
        constitutionMap.put(CharacterEnum.HERO_PIRATE, 3);
        constitutionMap.put(CharacterEnum.HERO_BABY, 3);

        luckMap = new HashMap<>();
        luckMap.put(CharacterEnum.HERO_ADVENTURER, 3);
        luckMap.put(CharacterEnum.HERO_KNIGHT, 3);
        luckMap.put(CharacterEnum.HERO_WIZZARD, 3);
        luckMap.put(CharacterEnum.HERO_LIZARD, 3);
        luckMap.put(CharacterEnum.HERO_NINJA, 3);
        luckMap.put(CharacterEnum.HERO_PIRATE, 3);
        luckMap.put(CharacterEnum.HERO_BABY, 3);

    }

    public static int getStrength(CharacterEnum characterEnum) {
        return strengthMap.get(characterEnum);
    }

    public static int getDexterity(CharacterEnum characterEnum) {
        return dexterityMap.get(characterEnum);
    }

    public static int getIntelligence(CharacterEnum characterEnum) {
        return intelligenceMap.get(characterEnum);
    }

    public static int getConstitution(CharacterEnum characterEnum) {
        return constitutionMap.get(characterEnum);
    }

    public static int getLuck(CharacterEnum characterEnum) {
        return luckMap.get(characterEnum);
    }

}
