package com.appatstudio.epicdungeontactics2.global.stats.characters;

import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;

public final class CharacterPrototype {

    private final int STR, DEX, INT, VIT, LCK;

    public CharacterPrototype(int STR, int DEX, int INT, int VIT, int LCK) {
        this.STR = STR;
        this.DEX = DEX;
        this.INT = INT;
        this.VIT = VIT;
        this.LCK = LCK;
    }

    public int getDEX() {
        return DEX;
    }

    public int getINT() {
        return INT;
    }

    public int getLCK() {
        return LCK;
    }

    public int getSTR() {
        return STR;
    }

    public int getVIT() {
        return VIT;
    }

    public int getStat(StatisticEnum allStat) {
        switch (allStat) {
            case DEX:
                return DEX;
            case INT:
                return INT;
            case LCK:
                return LCK;
            case STR:
                return STR;
            case VIT:
                return VIT;
            default:
                return 1;
        }
    }
}
