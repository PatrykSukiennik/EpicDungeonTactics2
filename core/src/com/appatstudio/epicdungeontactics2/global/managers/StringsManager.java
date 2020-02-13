package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CommunicatesEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoFlagsEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.HashMap;
import java.util.Map;

public final class StringsManager {

    private static Map<ItemEnum, String> itemNamesMap;
    private static Map<PerkEnum, String> perkNamesMap;
    private static Map<PerkEnum, String> perkDescriptionsMap;
    private static Map<CampUpgradeEnum, String> campUpgradeNamesMap;
    private static Map<CampUpgradeEnum, String> campUpgradeDescriptionsMap;
    private static Map<CampUpgradeEnum, String> campUpgradeMainDescriptionsMap;
    private static Map<CharacterEnum, String> characterNamesMap;
    private static Map<CharacterEnum, String> characterDescriptionsMap;
    private static Map<CharacterEnum, String> characterBonusesMap;
    private static Map<StatisticEnum, String> statsNameMap;
    private static Map<GuiStringEnum, String> guiStringMap;
    private static Map<CommunicatesEnum, String> communicatesStringMap;
    private static Map<PlayerStatsTrackerFlagsEnum, String> playerStatStringMap;

    public static void load() {
        I18NBundle itemNames = I18NBundle.createBundle(Gdx.files.internal("strings/item_names"), java.util.Locale.getDefault());
        I18NBundle perkNames = I18NBundle.createBundle(Gdx.files.internal("strings/perk_names"), java.util.Locale.getDefault());
        I18NBundle perkDescriptions = I18NBundle.createBundle(Gdx.files.internal("strings/perk_descriptions"), java.util.Locale.getDefault());
        I18NBundle campUpgradeNames = I18NBundle.createBundle(Gdx.files.internal("strings/camp_upgrade_names"), java.util.Locale.getDefault());
        I18NBundle campUpgradeDescriptions = I18NBundle.createBundle(Gdx.files.internal("strings/camp_upgrade_descriptions"), java.util.Locale.getDefault());
        I18NBundle campUpgradeMainDescriptions = I18NBundle.createBundle(Gdx.files.internal("strings/camp_upgrade_main_descriptions"), java.util.Locale.getDefault());
        I18NBundle characterNames = I18NBundle.createBundle(Gdx.files.internal("strings/character_names"), java.util.Locale.getDefault());
        I18NBundle characterBonuses = I18NBundle.createBundle(Gdx.files.internal("strings/character_bonus"), java.util.Locale.getDefault());
        I18NBundle characterDescriptions = I18NBundle.createBundle(Gdx.files.internal("strings/character_descriptions"), java.util.Locale.getDefault());
        I18NBundle statsNames = I18NBundle.createBundle(Gdx.files.internal("strings/stats_names"), java.util.Locale.getDefault());
        I18NBundle guiStrings = I18NBundle.createBundle(Gdx.files.internal("strings/gui_strings"), java.util.Locale.getDefault());
        I18NBundle playerStatStrings = I18NBundle.createBundle(Gdx.files.internal("strings/player_statistic_descriptions"), java.util.Locale.getDefault());
        I18NBundle communicatesStrings = I18NBundle.createBundle(Gdx.files.internal("strings/communicates"), java.util.Locale.getDefault());

        itemNamesMap = new HashMap<>();
        ItemEnum[] itemEnums = ItemEnum.values();
        for (ItemEnum currEnum : itemEnums) {
            itemNamesMap.put(currEnum, itemNames.get(currEnum.toString()));
        }

        perkNamesMap = new HashMap<>();
        perkDescriptionsMap = new HashMap<>();
        PerkEnum[] perkEnums = PerkEnum.values();
        for (PerkEnum currEnum : perkEnums) {
            perkNamesMap.put(currEnum, perkNames.get(currEnum.toString()));
            perkDescriptionsMap.put(currEnum, perkDescriptions.get(currEnum.toString()));
        }

        campUpgradeNamesMap = new HashMap<>();
        campUpgradeDescriptionsMap = new HashMap<>();
        campUpgradeMainDescriptionsMap = new HashMap<>();
        CampUpgradeEnum[] upgradeEnums = CampUpgradeEnum.values();
        for (CampUpgradeEnum currEnum : upgradeEnums) {
            campUpgradeNamesMap.put(currEnum, campUpgradeNames.get(currEnum.toString()));
            campUpgradeDescriptionsMap.put(currEnum, campUpgradeDescriptions.get(currEnum.toString()));
            campUpgradeMainDescriptionsMap.put(currEnum, campUpgradeMainDescriptions.get(currEnum.toString()));
        }

        characterNamesMap = new HashMap<>();
        characterDescriptionsMap = new HashMap<>();
        characterBonusesMap = new HashMap<>();
        CharacterEnum[] characterEnums = CharacterEnum.values();
        for (CharacterEnum currEnum : characterEnums) {
            characterNamesMap.put(currEnum, characterNames.get(currEnum.toString()));
            characterDescriptionsMap.put(currEnum, characterDescriptions.get(currEnum.toString()));
            characterBonusesMap.put(currEnum, characterBonuses.get(currEnum.toString()));
        }

        statsNameMap = new HashMap<>();
        StatisticEnum[] statisticEnums = StatisticEnum.values();
        for (StatisticEnum currEnum : statisticEnums) {
            statsNameMap.put(currEnum, statsNames.get(currEnum.toString()));
        }

        guiStringMap = new HashMap<>();
        GuiStringEnum[] guiStringEnums = GuiStringEnum.values();
        for (GuiStringEnum currEnum : guiStringEnums) {
            guiStringMap.put(currEnum, guiStrings.get(currEnum.toString()));
        }

        playerStatStringMap = new HashMap<>();
        PlayerStatsTrackerFlagsEnum[] allFlags = PlayerStatsTrackerFlagsEnum.values();
        for (PlayerStatsTrackerFlagsEnum f : allFlags) {
            playerStatStringMap.put(f, playerStatStrings.get(f.toString()));
        }

        communicatesStringMap = new HashMap<>();
        CommunicatesEnum[] allCommunicates = CommunicatesEnum.values();
        for (CommunicatesEnum c : allCommunicates) {
            communicatesStringMap.put(c, communicatesStrings.get(c.toString()));
        }

    }

    public static String getItemName(ItemEnum itemEnum) {
        return itemNamesMap.get(itemEnum);
    }

    public static String getPerkName(PerkEnum perkEnum) {
        return perkNamesMap.get(perkEnum);
    }

    public static String getPerkDescription(PerkEnum perkEnum) {
        return perkDescriptionsMap.get(perkEnum);
    }

    public static String getCampUpgradeName(CampUpgradeEnum campUpgradeEnum) {
        return campUpgradeNamesMap.get(campUpgradeEnum);
    }

    public static String getCampUpgradeDescription(CampUpgradeEnum campUpgradeEnum) {
        return campUpgradeDescriptionsMap.get(campUpgradeEnum);
    }

    public static String getCampUpgradeMainDescription(CampUpgradeEnum upgradeEnum) {
        return campUpgradeMainDescriptionsMap.get(upgradeEnum);
    }

    public static String getCharacterName(CharacterEnum characterEnum) {
        return characterNamesMap.get(characterEnum);
    }

    public static String getCharacterDescription(CharacterEnum characterEnum) {
        return characterDescriptionsMap.get(characterEnum);
    }

    public static String getCharacterBonus(CharacterEnum characterEnum) {
        return characterBonusesMap.get(characterEnum);
    }

    public static String getStatName(StatisticEnum statisticEnum) {
        return statsNameMap.get(statisticEnum);
    }

    public static String getGuiString(GuiStringEnum stringEnum) {
        return guiStringMap.get(stringEnum);
    }

    public static String getPlayerStatDescription(PlayerStatsTrackerFlagsEnum statEnum) {
        return playerStatStringMap.get(statEnum);
    }

    public static String getCommunicate(CommunicatesEnum commEnum) {
        return communicatesStringMap.get(commEnum);
    }

}
