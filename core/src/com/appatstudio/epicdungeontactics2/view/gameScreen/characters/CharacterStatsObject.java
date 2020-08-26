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

    public CharacterStatsObject(CharacterEnum characterEnum) {
        baseStats = CharacterStats.getPrototype(characterEnum);
        lvlStats = CharacterStats.getEnemeyLvlUpStats(characterEnum);
    }


    public int getSpeed() {
        return baseStats.getDEX() + lvlStats.getDEX() + bonusStats.getDEX();
    }


}
