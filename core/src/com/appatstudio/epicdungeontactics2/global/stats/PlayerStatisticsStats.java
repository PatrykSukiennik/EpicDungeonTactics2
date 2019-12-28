package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;

import java.util.HashMap;
import java.util.Map;

public final class PlayerStatisticsStats {

    private static final Map<PlayerStatsTrackerFlagsEnum, int[]> caps;
    private static final Map<PlayerStatsTrackerFlagsEnum, int[]> rewards;

    static {
        caps = new HashMap<>();
        caps.put(PlayerStatsTrackerFlagsEnum.KILLED_ENEMIES, new int[] {50, 500, 4000, 10000});
        caps.put(PlayerStatsTrackerFlagsEnum.BOSSES_KILLED, new int[] {5, 20, 100, 400});
        caps.put(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE, new int[] {3, 6, 9, 12});
        caps.put(PlayerStatsTrackerFlagsEnum.DMG_DEALT, new int[] {100, 1000, 10000, 50000});
        caps.put(PlayerStatsTrackerFlagsEnum.GOLD_COLLECTED, new int[] {1000, 5000, 20000, 50000});
        caps.put(PlayerStatsTrackerFlagsEnum.HIGHEST_DMG_DEALT, new int[] {10, 20, 30, 40});
        caps.put(PlayerStatsTrackerFlagsEnum.SKILL_POINTS_USED, new int[] {15, 50, 100, 250});
        caps.put(PlayerStatsTrackerFlagsEnum.PERKS_UPGRADED, new int[] {3, 10, 20, 30});

        rewards = new HashMap<>();
        rewards.put(PlayerStatsTrackerFlagsEnum.KILLED_ENEMIES, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.BOSSES_KILLED, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.DEEPEST_STAGE, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.DMG_DEALT, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.GOLD_COLLECTED, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.HIGHEST_DMG_DEALT, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.SKILL_POINTS_USED, new int[] {250, 500, 1000, 5000});
        rewards.put(PlayerStatsTrackerFlagsEnum.PERKS_UPGRADED, new int[] {250, 500, 1000, 5000});
    }

    public static int getReward(PlayerStatsTrackerFlagsEnum flag, int lvl) {
        return rewards.get(flag)[lvl];
    }

    public static int getCap(PlayerStatsTrackerFlagsEnum flag, int lvl) {
        return caps.get(flag)[lvl];
    }

    public static int getStatLvl(PlayerStatsTrackerFlagsEnum flag) {
        int[] c = caps.get(flag);
        int value = SavedInfoManager.getPlayerStat(flag);
        if (value > c[3]) return 3;
        else if (value > c[2]) return 2;
        else if (value > c[1]) return 1;
        else if (value > c[0]) return 0;
        else return 0;
    }



}
