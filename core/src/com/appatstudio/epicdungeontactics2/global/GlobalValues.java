package com.appatstudio.epicdungeontactics2.global;

import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;

public final class GlobalValues {

    private static int gold;

    static {
        gold = SavedInfoManager.getIntFromFlag(SavedInfoFlagsEnum.GOLD);
        if (gold == -1) {
            gold = 500;
            SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, gold);
        }
    }

    public static int getGold() {
        return gold;
    }


    public static void setGold(int v) {
        SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, v);
        gold = v;
    }

    public static void addGold(int v) {
        gold += v;
        SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, gold);
    }

    public static void minusGold(int v) {
        gold -= v;
        SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, gold);
    }
}
