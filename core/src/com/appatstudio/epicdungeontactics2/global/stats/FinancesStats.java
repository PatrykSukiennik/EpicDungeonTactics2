package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;

import java.util.HashMap;

public final class FinancesStats {

    private static final HashMap<FinanceUpgradeEnum, Integer> costMap;
    private static final HashMap<FinanceUpgradeEnum, Integer> incomeMap;

    static {
        costMap = new HashMap<>();
        costMap.put(FinanceUpgradeEnum.HIRED_MERCHANT, 200);
        costMap.put(FinanceUpgradeEnum.CARAVAN, 200);
        costMap.put(FinanceUpgradeEnum.TAVERN, 200);
        costMap.put(FinanceUpgradeEnum.MUSEUM, 200);
        costMap.put(FinanceUpgradeEnum.FARM, 200);
        costMap.put(FinanceUpgradeEnum.VILLAGE, 200);
        costMap.put(FinanceUpgradeEnum.CASTLE, 200);

        incomeMap = new HashMap<>();
        incomeMap.put(FinanceUpgradeEnum.HIRED_MERCHANT, 200);
        incomeMap.put(FinanceUpgradeEnum.CARAVAN, 200);
        incomeMap.put(FinanceUpgradeEnum.TAVERN, 200);
        incomeMap.put(FinanceUpgradeEnum.MUSEUM, 200);
        incomeMap.put(FinanceUpgradeEnum.FARM, 200);
        incomeMap.put(FinanceUpgradeEnum.VILLAGE, 200);
        incomeMap.put(FinanceUpgradeEnum.CASTLE, 200);
    }

    public static int getCost(FinanceUpgradeEnum upgradeEnum) {
        return costMap.get(upgradeEnum);
    }

    public static int getIncome(FinanceUpgradeEnum upgradeEnum) {
        return incomeMap.get(upgradeEnum);
    }
}
