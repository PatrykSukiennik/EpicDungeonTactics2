package com.appatstudio.epicdungeontactics2.global.managers.savedInfo;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.campUpgradeScreen.CampUpgradeScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import sun.util.calendar.LocalGregorianCalendar;

public class SavedInfoManager {

    private static Preferences preferences;

    private static Map<CharacterEnum, Boolean> isCharacterUnlocked;
    private static Map<SavedInfoFlagsEnum, Integer> integerMap;
    private static Map<PlayerStatsTrackerFlagsEnum, Integer> playerStatsMap;
    private static Map<PlayerStatsTrackerFlagsEnum, Integer> playerStatsWaitingRewards;

    private static Map<PerkEnum, Integer> perkLvls;
    private static Map<CharacterEnum, Integer> characterLvls;
    private static Map<FinanceUpgradeEnum, Integer> financesLvls;
    private static Map<CharacterEnum, Integer> characterExps;
    private static Map<CharacterEnum, Map<StatisticEnum, Integer>> characterStats;
    private static Map<CampUpgradeEnum, Integer> campUpgradeLvls;

    private static Long saved_date;

    static {
        preferences = Gdx.app.getPreferences("epicdungeontactics2");

        integerMap = new HashMap<>();
        SavedInfoFlagsEnum[] allFlags = SavedInfoFlagsEnum.values();
        for (SavedInfoFlagsEnum f : allFlags) {
            integerMap.put(f, preferences.getInteger(f.toString(), -1));
        }

        playerStatsMap = new HashMap<>();
        playerStatsWaitingRewards = new HashMap<>();
        PlayerStatsTrackerFlagsEnum[] playerFlags = PlayerStatsTrackerFlagsEnum.values();
        for (PlayerStatsTrackerFlagsEnum f : playerFlags) {
            playerStatsMap.put(f, preferences.getInteger(f.toString(), 0));
            playerStatsWaitingRewards.put(f, preferences.getInteger("waitingRewards" + f.toString(), 0));
        }

        StatisticEnum[] allStats = StatisticEnum.values();
        CharacterEnum[] heroes = new CharacterEnum[]{
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
                characterStats.get(c).put(s, preferences.getInteger("charStat" + c.toString() + s.toString(), CharacterStats.getDefHeroStat(c, s)));
            }
        }

        perkLvls = new HashMap<>();
        PerkEnum[] allPerks = PerkEnum.values();
        for (PerkEnum p : allPerks) {
            perkLvls.put(p, preferences.getInteger("perkLvl" + p.toString(), 1));
        }

        campUpgradeLvls = new HashMap<>();
        CampUpgradeEnum[] allUpgrades = CampUpgradeEnum.values();
        for (CampUpgradeEnum c : allUpgrades) {
            campUpgradeLvls.put(c, preferences.getInteger("campUpgradeLvl" + c.toString(), 0));
        }

        financesLvls = new HashMap<>();
        FinanceUpgradeEnum[] allFinances = FinanceUpgradeEnum.values();
        for (FinanceUpgradeEnum f : allFinances) {
            financesLvls.put(f, preferences.getInteger("financesUpgradeLvl" + f.toString(), 0));
        }

        isCharacterUnlocked = new HashMap<>();
        isCharacterUnlocked.put(CharacterEnum.HERO_ELF, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_ELF.toString(), true));
        isCharacterUnlocked.put(CharacterEnum.HERO_KNIGHT, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_KNIGHT.toString(), true));
        isCharacterUnlocked.put(CharacterEnum.HERO_WIZZARD, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_WIZZARD.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_LIZARD, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_LIZARD.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_NINJA, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_NINJA.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_PIRATE, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_PIRATE.toString(), false));
        isCharacterUnlocked.put(CharacterEnum.HERO_BABY, preferences.getBoolean("isUnlocked" + CharacterEnum.HERO_BABY.toString(), false));

        checkChangeDay();
    }

    public static boolean isUnlocked(CharacterEnum characterEnum) {
        return isCharacterUnlocked.get(characterEnum);
    }

    public static boolean isUnlockedCamp(CampUpgradeEnum campUpgradeEnum) {
        return campUpgradeLvls.get(campUpgradeEnum) != 0;
    }

    public static int getIntFromFlag(SavedInfoFlagsEnum flag) {
        return integerMap.get(flag);
    }

    public static void saveInt(SavedInfoFlagsEnum flag, int value) {
        preferences.putInteger(flag.toString(), value);
        preferences.flush();
    }

    public static void addValueToFlag(SavedInfoFlagsEnum flag, int value) {
        int value2 = getIntFromFlag(flag) + value;
        preferences.putInteger(flag.toString(), value2);
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

    public static int getPlayerStat(PlayerStatsTrackerFlagsEnum flag) {return playerStatsMap.get(flag);}

    public static int getPlayerStatRewardWaiting(PlayerStatsTrackerFlagsEnum flag) {
        return playerStatsWaitingRewards.get(flag);
    }

    public static void savePerkLvl(PerkEnum perkEnum, int lvl) {
        perkLvls.put(perkEnum, lvl);
        preferences.putInteger("perkLvl" + perkEnum.toString(), lvl);
        preferences.flush();
    }

    public static void saveCampUpgradeLvl(CampUpgradeEnum campUpgradeEnum, int lvl) {
        campUpgradeLvls.put(campUpgradeEnum, lvl);
        preferences.putInteger("campUpgradeLvl" + campUpgradeEnum.toString(), lvl);
        preferences.flush();
    }

    public static void saveFinancesLvl(FinanceUpgradeEnum financeUpgradeEnum, int lvl) {
        financesLvls.put(financeUpgradeEnum, lvl);
        preferences.putInteger("financesUpgradeLvl" + financeUpgradeEnum.toString(), lvl);
        preferences.flush();
    }

    public static CharacterEnum[] getAllUnlockedCharacters() {
        Array<CharacterEnum> characters = new Array<>();

        if (isUnlocked(CharacterEnum.HERO_ELF)) characters.add(CharacterEnum.HERO_ELF);
        if (isUnlocked(CharacterEnum.HERO_KNIGHT)) characters.add(CharacterEnum.HERO_KNIGHT);
        if (isUnlocked(CharacterEnum.HERO_WIZZARD)) characters.add(CharacterEnum.HERO_WIZZARD);
        if (isUnlocked(CharacterEnum.HERO_LIZARD)) characters.add(CharacterEnum.HERO_LIZARD);
        if (isUnlocked(CharacterEnum.HERO_NINJA)) characters.add(CharacterEnum.HERO_NINJA);
        if (isUnlocked(CharacterEnum.HERO_PIRATE)) characters.add(CharacterEnum.HERO_PIRATE);
        if (isUnlocked(CharacterEnum.HERO_BABY)) characters.add(CharacterEnum.HERO_BABY);

        CharacterEnum[] returnArray = new CharacterEnum[characters.size];
        for (int i=0; i<characters.size; i++) {
            returnArray[i] = characters.get(i);
        }
        return returnArray;
    }

    public static CharacterEnum[] getAllUnlockedNpcs() {
        Array<CharacterEnum> characters = new Array<>();

        if (isUnlockedCamp(CampUpgradeEnum.ALCHEMIST)) characters.add(CharacterEnum.NPC_ALCHEMIST);
        if (isUnlockedCamp(CampUpgradeEnum.BLACKSMITH)) characters.add(CharacterEnum.NPC_BLACKSMITH);
        if (isUnlockedCamp(CampUpgradeEnum.BUTCHER)) characters.add(CharacterEnum.NPC_BUTCHER);
        if (isUnlockedCamp(CampUpgradeEnum.LUGGAGE_CARRIAGE)) characters.add(CharacterEnum.NPC_CITIZEN_MALE);
        if (isUnlockedCamp(CampUpgradeEnum.MAGIC_SHOP)) characters.add(CharacterEnum.NPC_MAGIC_SHOP);
        if (isUnlockedCamp(CampUpgradeEnum.PRINCESS)) characters.add(CharacterEnum.NPC_PRINCESS);
        if (isUnlockedCamp(CampUpgradeEnum.MOUNTAIN_KING)) characters.add(CharacterEnum.NPC_MOUNTAIN_KING);

        CharacterEnum[] returnArray = new CharacterEnum[characters.size];
        for (int i=0; i<characters.size; i++) {
            returnArray[i] = characters.get(i);
        }
        return returnArray;
    }

    public static int getNpcLvl(CampUpgradeEnum campUpgradeEnum) {
        return campUpgradeLvls.get(campUpgradeEnum);
    }

    public static int getFinancesLvl(FinanceUpgradeEnum financeUpgradeEnum) {
        return financesLvls.get(financeUpgradeEnum);
    }

    public static void checkChangeDay() {
        saved_date = preferences.getLong("date", -1);
        if (saved_date == -1) {
            preferences.putLong("date", TimeUnit.MICROSECONDS.toDays(System.currentTimeMillis()));
        }
        else {
            long days = TimeUnit.MICROSECONDS.toDays(System.currentTimeMillis()) - saved_date;
            System.out.println(days);
            if (days > 0) {
                EpicDungeonTactics.reportDayChanged((int)days);
                preferences.putLong("date", TimeUnit.MICROSECONDS.toDays(System.currentTimeMillis()));
                preferences.flush();
            }
        }
    }
}
