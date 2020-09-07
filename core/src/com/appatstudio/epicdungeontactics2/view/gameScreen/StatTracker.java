package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGenerator;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterStatsObject;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class StatTracker {

    private static int goldCollected;
    private static int roomsCleared;
    private static HashMap<CharacterEnum, Integer> expCollected;
    private static HashMap<CharacterEnum, Boolean> lvlUps;
    private static Array<CharacterEnum> usedCharacters;
    public static Array<AbstractItem> eqItems;
    public static CharacterStatsObject stats;

    private static HashMap<StatisticEnum, Integer> basicStats;
    private static HashMap<StatisticEnum, Integer> currStats;

    private static CharacterEnum currHero;
    private static int lvl;

    static {
        currHero = GameScreen.getHero();
        usedCharacters = new Array<>();

        eqItems = new Array<>();

        stats = new CharacterStatsObject(GameScreen.getHero());

        basicStats = new HashMap<>();
        currStats = new HashMap<>();
        StatisticEnum[] allEnums = StatisticEnum.values();
        for (StatisticEnum s : allEnums) basicStats.put(s, SavedInfoManager.getCharacterStat(currHero, s));
        for (StatisticEnum s : allEnums) currStats.put(s, SavedInfoManager.getCharacterStat(currHero, s));
    }

    public static int getLvl() {
        return lvl;
    }

    public static CharacterEnum getHero() {
        return currHero;
    }

    public static void refreshEq() {

    }

    public static void lvlUp() {
        ItemGenerator.refresh();
    }

    public static int getBasicStat(StatisticEnum statisticEnum) {
        return basicStats.get(statisticEnum);
    }

    public static int getCurrentStat(StatisticEnum statisticEnum) {
        return currStats.get(statisticEnum);
    }

    public HashMap<CharacterEnum, Boolean> getLvlUps() {
        return lvlUps;
    }

    public HashMap<CharacterEnum, Integer> getExpCollected() {
        return expCollected;
    }

    public int getGoldCollected() {
        return goldCollected;
    }

    public int getRoomsCleared() {
        return roomsCleared;
    }

    public Array<CharacterEnum> getUsedCharacters() {
        return usedCharacters;
    }

    public static CharacterStatsObject getStats() {
        return stats;
    }

}
