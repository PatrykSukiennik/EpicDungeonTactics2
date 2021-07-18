package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;

public class CharacterStatsObject {
    private CharacterPrototype baseStats;
    private CharacterPrototype lvlStats;
    private CharacterPrototype bonusStats;

    private int maxHp;
    private int currHp;

    private int range;
    private int speed;

    public CharacterStatsObject(CharacterEnum characterEnum) {
        baseStats = CharacterStats.getPrototype(characterEnum);
        lvlStats = CharacterStats.getEnemyLvlUpStats(characterEnum);

        range = CharacterStats.getRange(characterEnum);
        speed = CharacterStats.getSpeed(characterEnum);

        if (!characterEnum.toString().startsWith("NPC") && !characterEnum.toString().startsWith("HERO")) {
            maxHp = CharacterStats.getBasicHeroStat(characterEnum, StatisticEnum.VIT)
                    + CharacterStats.getEnemyLvlUpStats(characterEnum).getVIT()
                    * (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL);
            currHp = maxHp;
        }
    }


    public int getMeleDmg(int lvl) {
        return (baseStats.getSTR() + lvlStats.getSTR() * lvl);
    }

    public int getShotDmg(int lvl) {
        return (baseStats.getSTR() + lvlStats.getSTR() * lvl);
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

    public int getSpeed() {
        return speed;
    }

    public void dmgGot(int dmg) {
        currHp -= dmg;
    }
}
