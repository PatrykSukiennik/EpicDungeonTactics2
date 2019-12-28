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

    private static final int[] EXP_CAPS = new int[]{
            100, 1000, 10000, 100000, 10000000, 1000000000
    };

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
        buyCostMap.put(CharacterEnum.HERO_ELF, 0);
        buyCostMap.put(CharacterEnum.HERO_KNIGHT, 0);
        buyCostMap.put(CharacterEnum.HERO_WIZZARD, 2000);
        buyCostMap.put(CharacterEnum.HERO_LIZARD, 5000);
        buyCostMap.put(CharacterEnum.HERO_NINJA, 10000);
        buyCostMap.put(CharacterEnum.HERO_PIRATE, 15000);
        buyCostMap.put(CharacterEnum.HERO_BABY, 25000);

        requiredStageToUnlockMap = new HashMap<>();
        requiredStageToUnlockMap.put(CharacterEnum.HERO_ELF, 0);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_KNIGHT, 0);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_WIZZARD, -3);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_LIZARD, 5);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_NINJA, 7);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_PIRATE, 9);
        requiredStageToUnlockMap.put(CharacterEnum.HERO_BABY, 12);

        startingItemMap = new HashMap<>();
        startingItemMap.put(CharacterEnum.HERO_ELF, new ItemEnum[]{ItemEnum.BOWv1, ItemEnum.FOODv3, ItemEnum.FOODv3});
        startingItemMap.put(CharacterEnum.HERO_KNIGHT, new ItemEnum[]{ItemEnum.SWORDv2, ItemEnum.ARMORv7, ItemEnum.SHIELDv0});
        startingItemMap.put(CharacterEnum.HERO_WIZZARD, new ItemEnum[]{ItemEnum.STAFFv1, ItemEnum.ARMORv23, ItemEnum.FOODv8});
        startingItemMap.put(CharacterEnum.HERO_LIZARD, new ItemEnum[]{ItemEnum.BOWv4, ItemEnum.FOODv6, ItemEnum.FOODv6});
        startingItemMap.put(CharacterEnum.HERO_NINJA, new ItemEnum[]{ItemEnum.SWORDv3, ItemEnum.BOWv5, ItemEnum.FOODv4});
        startingItemMap.put(CharacterEnum.HERO_PIRATE, new ItemEnum[]{ItemEnum.SWORDv4, ItemEnum.ARMORv2, ItemEnum.FOODv10});
        startingItemMap.put(CharacterEnum.HERO_BABY, new ItemEnum[]{ItemEnum.STAFFv3, ItemEnum.KEY, ItemEnum.KEY});

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

    public static int getExpCap(int lvl) {
        return EXP_CAPS[lvl - 1];
    }

}
