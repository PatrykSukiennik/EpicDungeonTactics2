package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class StatTracker {

    private int goldCollected;
    private int roomsCleared;
    private HashMap<CharacterEnum, Integer> expCollected;
    private HashMap<CharacterEnum, Boolean> lvlUps;
    private Array<CharacterEnum> usedCharacters;

    public StatTracker() {
        usedCharacters = new Array<>();
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
