package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;

import java.util.HashMap;

public class CharacterStatsObject {
    private CharacterPrototype baseStats;
    private CharacterPrototype lvlStats;
    private CharacterPrototype bonusStats;

    private int maxHp;
    private int currHp;

    private int range;

    public CharacterStatsObject(CharacterEnum characterEnum) {
        baseStats = CharacterStats.getPrototype(characterEnum);
        lvlStats = CharacterStats.getEnemeyLvlUpStats(characterEnum);

        maxHp = 20;
        currHp = 14;

        range = 3;
    }


    public int getSpeed() {
        //return baseStats.getDEX() + lvlStats.getDEX() + bonusStats.getDEX();
        return 5;
    }

    public int getMeleDmg() {
        return 2;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrHp() {
        return currHp;
    }

    public int getRange() {
        return range;
    }
}
