package com.appatstudio.epicdungeontactics2.screens.menu.perkScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;

final class PerkRandomizer {

    private static final int PERK_AT_ONCE_COUNT = 4;

    static PerkEnum[] getRandomPerks() {
        PerkEnum[] allPerks = PerkEnum.values();
        PerkEnum[] readyPerks = new PerkEnum[PERK_AT_ONCE_COUNT];

        int random;
        for (int i = 0; i < PERK_AT_ONCE_COUNT; i++) {
            do {
                random = EpicDungeonTactics.random.nextInt(allPerks.length);
            }
            while (isInArray(allPerks[random], readyPerks));
            readyPerks[i] = allPerks[random];
        }

        return readyPerks;
    }

    static PerkEnum[] getRandomPerks(PerkEnum[] oldPerks) {
        PerkEnum[] allPerks = PerkEnum.values();
        PerkEnum[] readyPerks = new PerkEnum[PERK_AT_ONCE_COUNT];

        int random;
        for (int i = 0; i < PERK_AT_ONCE_COUNT; i++) {
            do {
                random = EpicDungeonTactics.random.nextInt(allPerks.length);
            }
            while (isInArray(allPerks[random], readyPerks) || isInArray(allPerks[random], oldPerks));
            readyPerks[i] = allPerks[random];
        }

        return readyPerks;
    }

    private static boolean isInArray(PerkEnum perk, PerkEnum[] array) {
        for (PerkEnum p : array) {
            if (p == perk) return true;
        }
        return false;
    }

}
