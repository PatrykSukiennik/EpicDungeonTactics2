package com.appatstudio.epicdungeontactics2.global;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.GameModeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;

import lombok.Getter;

public final class GlobalValues {

    @Getter private static int gold;

    static {
        gold = SavedInfoManager.getIntFromFlag(SavedInfoFlagsEnum.GOLD);
        if (gold == -1) {
            gold = 500;
            SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, gold);
        }
        if (EpicDungeonTactics.GAMEMODE == GameModeEnum.DEBUG) gold = 500000;
    }

    public static void setGold(int v) {
        SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, v);
        gold = v;
    }

    public static void addGold(int v) {
        gold += v;
        SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, gold);

        SoundsManager.playSound(SoundEnum.COINS_PICKUP);
        EpicDungeonTactics.updateGold();
    }

    public static void minusGold(int v) {
        gold -= v;
        SavedInfoManager.saveInt(SavedInfoFlagsEnum.GOLD, gold);

        SoundsManager.playSound(SoundEnum.COINS_SPENT);
        EpicDungeonTactics.updateGold();
    }
}
