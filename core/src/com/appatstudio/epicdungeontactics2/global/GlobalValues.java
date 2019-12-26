package com.appatstudio.epicdungeontactics2.global;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;

import java.util.Map;

public final class GlobalValues {

    private static int gold;

    static {
        gold = SavedInfoManager.getIntFromFlag(SavedInfoFlagsEnum.GOLD);
        if (gold == -1) {
            gold = 3000;
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
}
