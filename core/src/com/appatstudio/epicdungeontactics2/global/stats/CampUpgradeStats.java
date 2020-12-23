package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;

import java.util.HashMap;
import java.util.Map;

public final class CampUpgradeStats {

    private final static Map<CampUpgradeEnum, int[]> campUpgradeStats;
    private final static Map<CampUpgradeEnum, int[]> campUpgradeCost;
    private final static Map<CampUpgradeEnum, Integer> campUnlockStage;

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

        campUnlockStage = new HashMap<>();
        campUnlockStage.put(CampUpgradeEnum.ALCHEMIST, 0);
        campUnlockStage.put(CampUpgradeEnum.BLACKSMITH, 0);
        campUnlockStage.put(CampUpgradeEnum.MAGIC_SHOP, 6);
        campUnlockStage.put(CampUpgradeEnum.BUTCHER, 6);
        campUnlockStage.put(CampUpgradeEnum.LUGGAGE_CARRIAGE, 6);
        campUnlockStage.put(CampUpgradeEnum.MOUNTAIN_KING, 6);
        campUnlockStage.put(CampUpgradeEnum.PRINCESS, 8);
    }

    public static int getCampUpgradeCost(CampUpgradeEnum upgrade, int lvl) {
        return campUpgradeCost.get(upgrade)[lvl];
    }
    public static int getCampUpgradeStat(CampUpgradeEnum upgrade, int lvl) {
        return campUpgradeStats.get(upgrade)[lvl];
    }
    public static int getCampUnlockStage(CampUpgradeEnum upgradeEnum) {
        return campUnlockStage.get(upgradeEnum);
    }
}
