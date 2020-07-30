package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGenerator;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class StatTracker {

    private int goldCollected;
    private int roomsCleared;
    private HashMap<CharacterEnum, Integer> expCollected;
    private HashMap<CharacterEnum, Boolean> lvlUps;
    private Array<CharacterEnum> usedCharacters;
    public Array<AbstractItem> eqItems;

    private static CharacterEnum currHero;
    private static int lvl;

    public StatTracker() {
        usedCharacters = new Array<>();

        eqItems = new Array<>();
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
}
