package com.appatstudio.epicdungeontactics2.global.managers.savedInfo;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.stats.CharacterStats;
import com.appatstudio.epicdungeontactics2.global.stats.HeroStats;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.HashMap;
import java.util.Map;

public class SavedInfoManager {

    private static Preferences preferences;

    private static Map<CharacterEnum, Boolean> isCharacterUnlocked;
    private static Map<SavedInfoFlagsEnum, Integer> integerMap;

    private static Map<PerkEnum, Integer> perkLvls;
    private static Map<CharacterEnum, Integer> characterLvls;
    private static Map<CharacterEnum, Integer> characterExps;
    private static Map<CharacterEnum, Map<StatisticEnum, Integer>> characterStats;

    static {
        preferences = Gdx.app.getPreferences("epicdungeontactics2");

        integerMap = new HashMap<>();
        SavedInfoFlagsEnum[] allFlags = SavedInfoFlagsEnum.values();
        for (SavedInfoFlagsEnum f : allFlags) {
            integerMap.put(f, preferences.getInteger(f.toString(), -1));
        }

        StatisticEnum[] allStats = StatisticEnum.values();
        CharacterEnum[] heroes = new CharacterEnum[] {
                CharacterEnum.HERO_ELF, CharacterEnum.HERO_KNIGHT, CharacterEnum.HERO_WIZZARD, CharacterEnum.HERO_LIZARD,
                CharacterEnum.HERO_NINJA, CharacterEnum.HERO_PIRATE, CharacterEnum.HERO_BABY
        };

        characterLvls = new HashMap<>();
        characterExps = new HashMap<>();
        characterStats = new HashMap<>();
        for (CharacterEnum c : heroes) {
            characterLvls.put(c, preferences.getInteger("characterLvl" + c.toString(), 1));
            characterExps.put(c, preferences.getInteger("characterExp" + c.toString(), 0));

            characterStats.put(c, new HashMap<StatisticEnum, Integer>());
            for (StatisticEnum s : allStats) {
                characterStats.get(c).put(s, preferences.getInteger("charStat"+c.toString()+s.toString(), CharacterStats.getStat(c, s)));
            }
        }

        perkLvls = new HashMap<>();
        PerkEnum[] allPerks = PerkEnum.values();
        for (PerkEnum p : allPerks) {
            perkLvls.put(p, preferences.getInteger("perkLvl" + p.toString(), 1));
        }


        isCharacterUnlocked = new HashMap<>();
        isCharacterUnlocked.put(CharacterEnum.HERO_ELF, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_ELF.toString(), true));
        isCharacterUnlocked.put(CharacterEnum.HERO_KNIGHT, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_KNIGHT.toString(), true));
        isCharacterUnlocked.put(CharacterEnum.HERO_WIZZARD, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_WIZZARD.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_LIZARD, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_LIZARD.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_NINJA, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_NINJA.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_PIRATE, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_PIRATE.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_BABY, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_BABY.toString(), false));

    }

    public static boolean isUnlocked(CharacterEnum characterEnum) {
        return isCharacterUnlocked.get(characterEnum);
    }

    public static int getIntFromFlag(SavedInfoFlagsEnum flag) {
        return integerMap.get(flag);
    }

    public static void saveInt(SavedInfoFlagsEnum flag, int value) {
        preferences.putInteger(flag.toString(), value);
        preferences.flush();
    }

    public static void unlock(CharacterEnum characterEnum) {
        isCharacterUnlocked.put(characterEnum, true);
        preferences.putBoolean("isUnlocked" + characterEnum.toString(), true);
        preferences.flush();
    }

    public static int getCharacterStat(CharacterEnum c, StatisticEnum s) {
        return characterStats.get(c).get(s);
    }

    public static int getCharacterLvl(CharacterEnum c) {
        return characterLvls.get(c);
    }

    public static int getCharacterExp(CharacterEnum c) {
        return characterExps.get(c);
    }

    public static int getPerkLvl(PerkEnum perkEnum) {
        return perkLvls.get(perkEnum);
    }
}
