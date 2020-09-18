package com.appatstudio.epicdungeontactics2.global.stats;

import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;

import java.util.HashMap;
import java.util.Map;

public final class PerkStats {

    private static final Map<PerkEnum, float[]> perkStats;
    private static final Map<PerkEnum, int[]> perkUpgradeCosts;

    static {
        perkStats = new HashMap<>();
        perkStats.put(PerkEnum.GOLD_BONUS, new float[]{0.3f, 0.6f, 1f});
        perkStats.put(PerkEnum.EXP_BONUS, new float[]{0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.DOUBLE_MOVE, new float[]{0.3f, 0.6f, 0.9f});
        perkStats.put(PerkEnum.DOUBLE_ATTACK, new float[]{0.15f, 0.3f, 0.5f});
        //perkStats.put(PerkEnum.UNIQUE_LOOT, new float[]{0.1f, 0.2f, 0.3f});
        perkStats.put(PerkEnum.MELE_DMG, new float[]{0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.BOW_DMG, new float[]{0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.MAGICAL_DMG_BONUS, new float[]{0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.HP_RESTORATION_ROOM, new float[]{0.1f, 0.2f, 0.3f});
        perkStats.put(PerkEnum.HP_RESTORATION_KILL, new float[]{0.02f, 0.04f, 0.06f});
        //perkStats.put(PerkEnum.MANA_RESTORATION_ROOM, new float[]{0.15f, 0.3f, 0.5f});
        //perkStats.put(PerkEnum.MANA_RESTORATION_KILL, new float[]{0.05f, 0.1f, 0.15f});
        perkStats.put(PerkEnum.POISON_DURATION_DECREASE, new float[]{2f, 4f, 6f});
        perkStats.put(PerkEnum.MAGICAL_RESIST, new float[]{0.15f, 0.3f, 0.45f});
        perkStats.put(PerkEnum.INSTAKILL_ENEMY, new float[]{0.01f, 0.02f, 0.03f});
        perkStats.put(PerkEnum.CHANCE_FOR_ANY_DROP, new float[]{0.2f, 0.4f, 0.6f});
        perkStats.put(PerkEnum.MISS_STAGE, new float[]{0.05f, 0.1f, 0.15f});
        perkStats.put(PerkEnum.APPLE_FINDER, new float[]{0.15f, 0.3f, 0.5f});
        perkStats.put(PerkEnum.GLASS_CANNON, new float[]{0.5f, 0.8f, 1f});
        perkStats.put(PerkEnum.TANK, new float[]{0.5f, 0.35f, 0.2f});
        perkStats.put(PerkEnum.THIRD_CHANCE, new float[]{0.25f, 0.5f, 0.75f});
        perkStats.put(PerkEnum.MAGIC_MIRROR, new float[]{0.1f, 0.2f, 0.3f});
        perkStats.put(PerkEnum.STR_BONUS, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.INT_BONUS, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.LCK_BONUS, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.VIT_BONUS, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.DEX_BONUS, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.STRONGER_ENEMIES, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.KEY_FINDER, new float[]{2f, 6f, 10f});
        //perkStats.put(PerkEnum.ELEMENTALIST, new float[]{0.2f, 0.6f, 0.8f});
        perkStats.put(PerkEnum.EXPERIENCED_ADVENTURER, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.SUGAR_LOVER, new float[]{2f, 6f, 10f});
        perkStats.put(PerkEnum.BERSERKER, new float[]{2f, 6f, 10f});

        perkUpgradeCosts = new HashMap<>();
        perkUpgradeCosts.put(PerkEnum.GOLD_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.EXP_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.DOUBLE_MOVE, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.DOUBLE_ATTACK, new int[]{500, 2500, 10000});
        //perkUpgradeCosts.put(PerkEnum.UNIQUE_LOOT, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.MELE_DMG, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.BOW_DMG, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.MAGICAL_DMG_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.HP_RESTORATION_ROOM, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.HP_RESTORATION_KILL, new int[]{500, 2500, 10000});
        //perkUpgradeCosts.put(PerkEnum.MANA_RESTORATION_ROOM, new int[]{500, 2500, 10000});
        //perkUpgradeCosts.put(PerkEnum.MANA_RESTORATION_KILL, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.POISON_DURATION_DECREASE, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.MAGICAL_RESIST, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.INSTAKILL_ENEMY, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.CHANCE_FOR_ANY_DROP, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.MISS_STAGE, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.APPLE_FINDER, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.GLASS_CANNON, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.TANK, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.THIRD_CHANCE, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.MAGIC_MIRROR, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.STR_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.INT_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.LCK_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.VIT_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.DEX_BONUS, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.STRONGER_ENEMIES, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.KEY_FINDER, new int[]{500, 2500, 10000});
        //perkUpgradeCosts.put(PerkEnum.ELEMENTALIST, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.EXPERIENCED_ADVENTURER, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.SUGAR_LOVER, new int[]{500, 2500, 10000});
        perkUpgradeCosts.put(PerkEnum.BERSERKER, new int[]{500, 2500, 10000});

    }

    public static float getPerkStat(PerkEnum perkEnum, int lvl) {
        return perkStats.get(perkEnum)[lvl-1];
    }

    public static int getPerkUpgradeCost(PerkEnum perkEnum, int lvl) {
        return perkUpgradeCosts.get(perkEnum)[lvl-1];
    }


}
