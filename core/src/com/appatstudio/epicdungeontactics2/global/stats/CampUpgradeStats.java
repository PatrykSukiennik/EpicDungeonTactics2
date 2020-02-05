package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;

import java.util.HashMap;
import java.util.Map;

public final class CampUpgradeStats {

    private static Map<CampUpgradeEnum, int[]> campUpgradeStats;
    private static Map<CampUpgradeEnum, int[]> campUpgradeCost;

    static {
        campUpgradeStats = new HashMap<>();
        campUpgradeStats.put(CampUpgradeEnum.ALCHEMIST, new int[]{3, 6, 9});
        campUpgradeStats.put(CampUpgradeEnum.BLACKSMITH, new int[]{3, 6, 9});
        campUpgradeStats.put(CampUpgradeEnum.MAGIC_SHOP, new int[]{3, 6, 9});
        campUpgradeStats.put(CampUpgradeEnum.BUTCHER, new int[]{3, 6, 9});
        campUpgradeStats.put(CampUpgradeEnum.LUGGAGE_CARRIAGE, new int[]{3, 6, 9});
        campUpgradeStats.put(CampUpgradeEnum.MOUNTAIN_KING, new int[]{3, 6, 9});
        campUpgradeStats.put(CampUpgradeEnum.PRINCESS, new int[]{3, 6, 9});

        campUpgradeCost = new HashMap<>();
        campUpgradeCost.put(CampUpgradeEnum.ALCHEMIST, new int[]{300, 6, 9});
        campUpgradeCost.put(CampUpgradeEnum.BLACKSMITH, new int[]{300, 6, 9});
        campUpgradeCost.put(CampUpgradeEnum.MAGIC_SHOP, new int[]{300, 6, 9});
        campUpgradeCost.put(CampUpgradeEnum.BUTCHER, new int[]{300, 6, 9});
        campUpgradeCost.put(CampUpgradeEnum.LUGGAGE_CARRIAGE, new int[]{300, 6, 9});
        campUpgradeCost.put(CampUpgradeEnum.MOUNTAIN_KING, new int[]{300, 6, 9});
        campUpgradeCost.put(CampUpgradeEnum.PRINCESS, new int[]{300, 6, 9});
    }

    public static int getCampUpgradeCost(CampUpgradeEnum upgrade, int lvl) {
        return campUpgradeCost.get(upgrade)[lvl];
    }
    public static int getCampUpgradeStat(CampUpgradeEnum upgrade, int lvl) {
        return campUpgradeStats.get(upgrade)[lvl];
    }
}
