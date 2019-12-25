package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;

import java.util.HashMap;
import java.util.Map;

public final class HeroStats {

    private final static Map<CharacterEnum, Integer> pointsPerLvlMap;
    private final static Map<CharacterEnum, Integer> buyCostMap;
    private final static Map<CharacterEnum, Integer> requiredStageToUnlockMap;
    private final static Map<CharacterEnum, ItemEnum[]> startingItemMap;
    private final static Map<CharacterEnum, Integer> skillCooldownMap;

    static {
        pointsPerLvlMap = new HashMap<>();
        pointsPerLvlMap.put(CharacterEnum.HERO_ELF, 3);
        pointsPerLvlMap.put(CharacterEnum.HERO_KNIGHT, 2);
        pointsPerLvlMap.put(CharacterEnum.HERO_WIZZARD, 5);
        pointsPerLvlMap.put(CharacterEnum.HERO_LIZARD, 4);
        pointsPerLvlMap.put(CharacterEnum.HERO_NINJA, 4);
        pointsPerLvlMap.put(CharacterEnum.HERO_PIRATE, 3);
        pointsPerLvlMap.put(CharacterEnum.HERO_BABY, 7);

        buyCostMap = new HashMap<>();
        buyCostMap.put(CharacterEnum.HERO_ELF, 3);
        buyCostMap.put(CharacterEnum.HERO_KNIGHT, 2);
        buyCostMap.put(CharacterEnum.HERO_WIZZARD, 5);
        buyCostMap.put(CharacterEnum.HERO_LIZARD, 4);
        buyCostMap.put(CharacterEnum.HERO_NINJA, 4);
        buyCostMap.put(CharacterEnum.HERO_PIRATE, 3);
        buyCostMap.put(CharacterEnum.HERO_BABY, 7);

        requiredStageToUnlockMap = new HashMap<>();
        requiredStageToUnlockMap.put(CharacterEnum.HERO_ELF, 3);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_KNIGHT, 2);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_WIZZARD, 5);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_LIZARD, 4);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_NINJA, 4);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_PIRATE, 3);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_BABY, 7);

        skillCooldownMap = new HashMap<>();
        skillCooldownMap.put(CharacterEnum.HERO_ELF, 3);
        skillCooldownMap.put(CharacterEnum.HERO_KNIGHT, 2);
        skillCooldownMap.put(CharacterEnum.HERO_WIZZARD, 5);
        skillCooldownMap.put(CharacterEnum.HERO_LIZARD, 4);
        skillCooldownMap.put(CharacterEnum.HERO_NINJA, 4);
        skillCooldownMap.put(CharacterEnum.HERO_PIRATE, 3);
        skillCooldownMap.put(CharacterEnum.HERO_BABY, 7);

        startingItemMap = new HashMap<>();
        startingItemMap.put(CharacterEnum.HERO_ELF, new ItemEnum[]{ItemEnum.BOWv0, ItemEnum.SWORDv0, ItemEnum.FOODv3});
        startingItemMap.put(CharacterEnum.HERO_KNIGHT, new ItemEnum[]{});
        startingItemMap.put(CharacterEnum.HERO_WIZZARD, new ItemEnum[]{});
        startingItemMap.put(CharacterEnum.HERO_LIZARD, new ItemEnum[]{});
        startingItemMap.put(CharacterEnum.HERO_NINJA, new ItemEnum[]{});
        startingItemMap.put(CharacterEnum.HERO_PIRATE, new ItemEnum[]{});
        startingItemMap.put(CharacterEnum.HERO_BABY, new ItemEnum[]{});

    }

    public static int getPointsPerLvl(CharacterEnum characterEnum) {
        return pointsPerLvlMap.get(characterEnum);
    }

    public static int getBuyCost(CharacterEnum characterEnum) {
        return buyCostMap.get(characterEnum);
    }

    public static int getRequiredStageToUnlock(CharacterEnum characterEnum) {
        return requiredStageToUnlockMap.get(characterEnum);
    }

    public static ItemEnum[] getStartingItems(CharacterEnum characterEnum) {
        return startingItemMap.get(characterEnum);
    }

    public static int getSkillCooldown(CharacterEnum characterEnum) {
        return skillCooldownMap.get(characterEnum);
    }

}
