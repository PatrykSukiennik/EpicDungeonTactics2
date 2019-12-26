package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;

import java.util.HashMap;
import java.util.Map;

public final class PerkStats {

    private static final Map<PerkEnum, float[]> perkStats;

    static {
        perkStats = new HashMap<>();
        perkStats.put(PerkEnum.GOLD_BONUS, new float[] {0.3f, 0.6f, 1f});
        perkStats.put(PerkEnum.EXP_BONUS, new float[] {0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.DOUBLE_MOVE, new float[] {0.3f, 0.6f, 0.9f});
        perkStats.put(PerkEnum.DOUBLE_ATTACK, new float[] {0.15f, 0.3f, 0.5f});
        perkStats.put(PerkEnum.UNIQUE_LOOT, new float[] {0.1f, 0.2f, 0.3f});
        perkStats.put(PerkEnum.MELE_DMG, new float[] {0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.BOW_DMG, new float[] {0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.MAGICAL_DMG_BONUS, new float[] {0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.HP_RESTORATION_ROOM, new float[] {0.1f, 0.2f, 0.3f});
        perkStats.put(PerkEnum.HP_RESTORATION_KILL, new float[] {0.02f, 0.04f, 0.06f});
        perkStats.put(PerkEnum.MANA_RESTORATION_ROOM, new float[] {0.15f, 0.3f, 0.5f});
        perkStats.put(PerkEnum.MANA_RESTORATION_KILL, new float[] {0.05f, 0.1f, 0.15f});
        perkStats.put(PerkEnum.POISON_DURATION_DECREASE, new float[] {2f, 4f, 6f});
        perkStats.put(PerkEnum.MAGICAL_RESIST, new float[] {0.15f, 0.3f, 0.45f});
        perkStats.put(PerkEnum.INSTAKILL_ENEMY, new float[] {0.01f, 0.02f, 0.03f});
        perkStats.put(PerkEnum.CHANCE_FOR_ANY_DROP, new float[] {0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.MISS_STAGE, new float[] {0.05f, 0.1f, 0.15f});
        perkStats.put(PerkEnum.APPLE_FINDER, new float[] {0.15f, 0.3f, 0.5f});
        perkStats.put(PerkEnum.GLASS_CANNON, new float[] {0.5f, 0.8f, 1f});
        perkStats.put(PerkEnum.TANK, new float[] {0.5f, 0.35f, 0.2f});
        perkStats.put(PerkEnum.THIRD_CHANCE, new float[] {0.25f, 0.5f, 0.75f});
        perkStats.put(PerkEnum.MAGIC_MIRROR, new float[] {0.1f, 0.2f, 0.3f});
        perkStats.put(PerkEnum.STR_BONUS, new float[] {2f, 6f, 10f});
        perkStats.put(PerkEnum.INT_BONUS, new float[] {2f, 6f, 10f});
        perkStats.put(PerkEnum.LCK_BONUS, new float[] {2f, 6f, 10f});
        perkStats.put(PerkEnum.VIT_BONUS, new float[] {2f, 6f, 10f});
        perkStats.put(PerkEnum.DEX_BONUS, new float[] {2f, 6f, 10f});
    }

    public static float getPerkStat(PerkEnum perkEnum, int lvl) {
        return perkStats.get(perkEnum)[lvl];
    }


}
