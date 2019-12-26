package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.HashMap;
import java.util.Map;

public final class StringsManager {

    private static I18NBundle itemNames;
    private static I18NBundle perkNames;
    private static I18NBundle perkDescriptions;
    private static I18NBundle characterNames;
    private static I18NBundle characterDescriptions;
    private static I18NBundle statsNames;
    private static I18NBundle guiStrings;

    private static Map<ItemEnum, String> itemNamesMap;
    private static Map<PerkEnum, String> perkNamesMap;
    private static Map<PerkEnum, String> perkDescriptionsMap;
    private static Map<CharacterEnum, String> characterNamesMap;
    private static Map<CharacterEnum, String> characterDescriptionsMap;
    private static Map<StatisticEnum, String> statsNameMap;
    private static Map<GuiStringEnum, String> guiStringMap;



    public static void load() {
        itemNames = I18NBundle.createBundle(Gdx.files.internal("strings/item_names"), java.util.Locale.getDefault());
        perkNames = I18NBundle.createBundle(Gdx.files.internal("strings/perk_names"), java.util.Locale.getDefault());
        perkDescriptions = I18NBundle.createBundle(Gdx.files.internal("strings/perk_descriptions"), java.util.Locale.getDefault());
        characterNames = I18NBundle.createBundle(Gdx.files.internal("strings/character_names"), java.util.Locale.getDefault());
        characterDescriptions = I18NBundle.createBundle(Gdx.files.internal("strings/character_descriptions"), java.util.Locale.getDefault());
        statsNames = I18NBundle.createBundle(Gdx.files.internal("strings/stats_names"), java.util.Locale.getDefault());
        guiStrings = I18NBundle.createBundle(Gdx.files.internal("strings/gui_strings"), java.util.Locale.getDefault());

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

        characterNamesMap = new HashMap<>();
        characterDescriptionsMap = new HashMap<>();
        CharacterEnum[] characterEnums = CharacterEnum.values();
        for (CharacterEnum currEnum : characterEnums) {
            characterNamesMap.put(currEnum, characterNames.get(currEnum.toString()));
            characterDescriptionsMap.put(currEnum, characterDescriptions.get(currEnum.toString()));
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

    public static String getCharacterName(CharacterEnum characterEnum) {
        return characterNamesMap.get(characterEnum);
    }

    public static String getCharacterDescription(CharacterEnum characterEnum) {
        return characterDescriptionsMap.get(characterEnum);
    }

    public static  String getStatName(StatisticEnum statisticEnum) {
        return statsNameMap.get(statisticEnum);
    }

    public static String getGuiString(GuiStringEnum stringEnum) {
        return guiStringMap.get(stringEnum);
    }
}
