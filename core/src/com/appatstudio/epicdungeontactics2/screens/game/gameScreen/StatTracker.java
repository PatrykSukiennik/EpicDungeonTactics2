package com.appatstudio.epicdungeontactics2.screens.game.gameScreen;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.CampUpgradeStats;
import com.appatstudio.epicdungeontactics2.global.stats.PerkStats;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGenerator;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterStatsObject;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.HeroSegment;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.heroStatWindow.HeroStatWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.statusBars.StatusBarContainer;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Armor;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Arrow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Book;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Bow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Food;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Helmet;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.MeleWeapon;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Necklace;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Ring;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Shield;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.Staff;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class StatTracker {

    private static HashMap<CharacterEnum, Integer> expCollected;
    private static HashMap<CharacterEnum, Boolean> lvlUps;
    private static Array<CharacterEnum> usedCharacters;

    public static Array<AbstractItem> backpackItems;
    public static Array<AbstractItem> equippedItems;

    public static CharacterStatsObject stats;

    private static HashMap<CompleteHeroStatsEnum, Float> currStats;

    private static CharacterEnum currHero;

    private static Image projectile;

    private static PerkEnum selectedPerk;

    private static final int HP_PER_VIT_POINT = 10;
    private static final int MP_PER_INT_POINT = 10;

    public StatTracker() {
        backpackItems = new Array<>();
    }

    static {
        usedCharacters = new Array<>();
        expCollected = new HashMap<>();
        lvlUps = new HashMap<>();

        projectile = new Image();
        projectile.setSize(WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);
    }

    public static void newRun(CharacterEnum runHero) {
        System.out.println("newRun");
        backpackItems = new Array<>();
        equippedItems = new Array<>();
        currHero = runHero;
        
        EquipmentWindow.newRun(runHero);

        usedCharacters.clear();
        for (CharacterEnum hero : SavedInfoManager.getAllUnlockedCharacters()) {
            expCollected.put(hero, 0);
            lvlUps.put(hero, false);
        }

        getStartItems(runHero);
    }

    public static void clearEq() {
        if (backpackItems == null) backpackItems = new Array<>();
        else backpackItems.clear();
    }

    public static void init(CharacterEnum hero, PerkEnum perk) {
        currHero = hero;
        selectedPerk = perk;

        stats = new CharacterStatsObject(GameScreen.getInstance().getHero());
        if (currStats == null) currStats = new HashMap<>();
        else currStats.clear();

        if (!usedCharacters.contains(hero, false)) {
            usedCharacters.add(hero);
            expCollected.put(hero, 0);
            lvlUps.put(hero, false);
        }

//        for (CharacterEnum h : SavedInfoManager.getAllUnlockedCharacters()) {
//            expCollected.put(h, 0);
//            lvlUps.put(h, false);
//        }

//        usedCharacters = new Array<>();
//        usedCharacters.add(hero);

        //newRun(hero);
        //getStartItems(hero);

        refreshWholeCharacter();
    }

    private static void getStartItems(CharacterEnum hero) {

        equippedItems = new Array<>();
        backpackItems = new Array<>();

        ItemEnum[] items = HeroStats.getStartingItems(hero);

        for (ItemEnum i : items) {
            System.out.println("DUPA");
            backpackItems.add(ItemGenerator.getItem(i));

            HeroSegment.equipIfIsSpace(backpackItems.get(backpackItems.size-1));

            switch (backpackItems.get(backpackItems.size - 1).getItemTypeEnum()) {
                case ARMOR:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                    refreshProjectile();
                case ARROW:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                    refreshProjectile();
                case NECKLACE:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                case SHIELD:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                case STAFF:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                    refreshProjectile();
                case MELE:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                case RING:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                case HELMET:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                case BOW:
                    equippedItems.add(backpackItems.get(backpackItems.size-1));
                    refreshProjectile();
            }
        }
    }

    public static CharacterEnum getHero() {
        return currHero;
    }

    public static void lvlUp() {
        ItemGenerator.refresh();
        CommunicatePrinter.lvlUp();
    }

    public static void refreshWholeCharacter() {
        if (equippedItems == null) equippedItems = new Array<>();
        else equippedItems.clear();
        
        System.out.println("DUPA4: " + equippedItems.size);
        Array<AbstractItem> itemsObjects = HeroSegment.getItems();

        System.out.println("DUPA5: " + itemsObjects.size);

        for (AbstractItem i : itemsObjects) {
            System.out.println("DUPA3");

            switch (i.getItemTypeEnum()) {
                case ARMOR:
                case HELMET:
                case RING:
                case MELE:
                case SHIELD:
                case NECKLACE:
                    equippedItems.add(i);
                    break;
                case ARROW:
                case STAFF:
                case BOW:
                    equippedItems.add(i);
                    refreshProjectile();
                    break;
            }
        }

        System.out.println("DUPA6: " + equippedItems.size);

        getBasicStats();

        refreshBonuses();
    }

    public static void refreshBonuses() {
        currStats.put(CompleteHeroStatsEnum.LVL, (float) SavedInfoManager.getCharacterLvl(currHero));
        currStats.put(CompleteHeroStatsEnum.MAX_EXP, (float) HeroStats.getExpCap(SavedInfoManager.getCharacterLvl(currHero)));

        HashMap<CompleteHeroStatsEnum, Float> itemBonuses = getItemEffects();

        currStats.put(
                CompleteHeroStatsEnum.STR,
                (SavedInfoManager.getCharacterStat(currHero, StatisticEnum.STR)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.STR, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.VIT,
                (SavedInfoManager.getCharacterStat(currHero, StatisticEnum.VIT)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.VIT, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.DEX,
                (SavedInfoManager.getCharacterStat(currHero, StatisticEnum.DEX)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.DEX, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.LCK,
                (SavedInfoManager.getCharacterStat(currHero, StatisticEnum.LCK)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.LCK, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.INT,
                (SavedInfoManager.getCharacterStat(currHero, StatisticEnum.INT)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.INT, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MAX_HP,
                (currStats.get(CompleteHeroStatsEnum.VIT) * HP_PER_VIT_POINT)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MAX_HP, itemBonuses));

        currStats.put(CompleteHeroStatsEnum.MAX_MP,
                (currStats.get(CompleteHeroStatsEnum.INT)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MAX_MP, itemBonuses)) * MP_PER_INT_POINT);

        currStats.put(CompleteHeroStatsEnum.CRIT_CHANCE,
                (currStats.get(CompleteHeroStatsEnum.LCK) * 0.02f / currStats.get(CompleteHeroStatsEnum.LVL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CRIT_CHANCE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MISS_CHANCE,
                (currStats.get(CompleteHeroStatsEnum.DEX) * 0.02f / currStats.get(CompleteHeroStatsEnum.LVL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MISS_CHANCE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.DOUBLE_ATTACK_CHANCE,
                (currStats.get(CompleteHeroStatsEnum.LCK) * 0.01f / currStats.get(CompleteHeroStatsEnum.LVL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.DOUBLE_ATTACK_CHANCE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.DOUBLE_MOVE_CHANCE,
                (currStats.get(CompleteHeroStatsEnum.LCK) * 0.01f / currStats.get(CompleteHeroStatsEnum.LVL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.DOUBLE_MOVE_CHANCE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.GOLD_MULTIPLIER,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.GOLD_MULTIPLIER, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.EXP_MULTIPLIER,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.EXP_MULTIPLIER, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MELE_DMG_MULTIPLIER,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MELE_DMG_MULTIPLIER, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MAGICAL_DMG_MULTIPLIER,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MAGICAL_DMG_MULTIPLIER, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.BOW_DMG_MULTIPLIER,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.BOW_DMG_MULTIPLIER, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.HP_RESTORE_ROOM,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.HP_RESTORE_ROOM, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.HP_RESTORE_KILL,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.HP_RESTORE_KILL, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.POISON_DURATION_EFFECT,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.POISON_DURATION_EFFECT, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MAGICAL_RESIST,
                (currStats.get(CompleteHeroStatsEnum.INT) * 0.02f / currStats.get(CompleteHeroStatsEnum.LVL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MAGICAL_RESIST, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.INSTAKILL_ENEMY_CHANCE,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.INSTAKILL_ENEMY_CHANCE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_ANY_DROP,
                (0.1f
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CHANCE_FOR_ANY_DROP, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_MISS_STAGE,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CHANCE_FOR_MISS_STAGE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_RANDOM_APPLE,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CHANCE_FOR_RANDOM_APPLE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_SECOND_REVIVE,
                (getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CHANCE_FOR_SECOND_REVIVE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_MAGIC_MIRROR,
                (currStats.get(CompleteHeroStatsEnum.INT) * 0.01f / currStats.get(CompleteHeroStatsEnum.LVL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CHANCE_FOR_MAGIC_MIRROR, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.ENEMY_POWER_MULTIPLIER,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.ENEMY_POWER_MULTIPLIER, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.DMG_MULTIPLIER_LESS_THAN_30_PERCENT_HP,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.DMG_MULTIPLIER_LESS_THAN_30_PERCENT_HP, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_RANDOM_KEY,
                (0.02f
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.CHANCE_FOR_RANDOM_KEY, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.RANGE,
                (1
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.RANGE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.ARMOR,
                (getItemStats(CompleteHeroStatsEnum.ARMOR)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.ARMOR, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MELE_DMG,
                (currStats.get(CompleteHeroStatsEnum.STR)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MELE_DMG, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.BOW_DMG,
                (getItemStats(CompleteHeroStatsEnum.BOW_DMG)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.BOW_DMG, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.MAGICAL_DMG,
                (getItemStats(CompleteHeroStatsEnum.MAGICAL_DMG)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.MAGICAL_DMG, itemBonuses)));;

        currStats.put(CompleteHeroStatsEnum.SPEED,
                (currStats.get(CompleteHeroStatsEnum.DEX)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.SPEED, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_DOUBLE_MOVE,
                (currStats.get(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_DOUBLE_MOVE)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_DOUBLE_MOVE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.PERCENT_HP_REGEN_KILL,
                (currStats.get(CompleteHeroStatsEnum.PERCENT_HP_REGEN_KILL)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.PERCENT_HP_REGEN_KILL, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.PERCENT_ANY_DROP_CHANCE,
                (currStats.get(CompleteHeroStatsEnum.PERCENT_ANY_DROP_CHANCE)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.PERCENT_ANY_DROP_CHANCE, itemBonuses)));

        currStats.put(CompleteHeroStatsEnum.PERCENT_SPELL_CHANCE,
                (currStats.get(CompleteHeroStatsEnum.PERCENT_SPELL_CHANCE)
                        + getItemAndPerkEffectsValue(CompleteHeroStatsEnum.PERCENT_SPELL_CHANCE, itemBonuses)));

        //_______________
        currStats.put(CompleteHeroStatsEnum.HP, currStats.get(CompleteHeroStatsEnum.MAX_HP));
        currStats.put(CompleteHeroStatsEnum.MP, currStats.get(CompleteHeroStatsEnum.MAX_MP));

    }

    private static float getItemAndPerkEffectsValue(CompleteHeroStatsEnum statsEnum, HashMap<CompleteHeroStatsEnum, Float> itemBonusesValue) {
        float result = 0;
        result += itemBonusesValue.get(statsEnum);

        switch (statsEnum) {
            default:
                return result;

            case RANGE:
                result += 1 + getItemStats(CompleteHeroStatsEnum.RANGE);
                break;
            case MAGICAL_DMG_MULTIPLIER:
                if (currHero == CharacterEnum.HERO_WIZZARD) result += 0.25f;
                break;
            case MELE_DMG_MULTIPLIER:
                if (currHero == CharacterEnum.HERO_KNIGHT) result += 0.25f;
                break;
            case BOW_DMG_MULTIPLIER:
                if (currHero == CharacterEnum.HERO_ELF) result += 0.25f;
                break;
            case STR:
                if (selectedPerk == PerkEnum.STR_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case VIT:
                if (selectedPerk == PerkEnum.VIT_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case DEX:
                if (selectedPerk == PerkEnum.DEX_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case LCK:
                result = 0;
                if (selectedPerk == PerkEnum.LCK_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case INT:
                result = 0;
                if (selectedPerk == PerkEnum.INT_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case CHANCE_FOR_RANDOM_KEY:
                result = 0;
                if (selectedPerk == PerkEnum.KEY_FINDER)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case DOUBLE_ATTACK_CHANCE:
                result = 0;
                if (selectedPerk == PerkEnum.DOUBLE_ATTACK)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case DOUBLE_MOVE_CHANCE:
                result = 0;
                if (selectedPerk == PerkEnum.DOUBLE_MOVE)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case GOLD_MULTIPLIER:
                result = 0;
                if (selectedPerk == PerkEnum.GOLD_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.MOUNTAIN_KING) > 0)
                    result += CampUpgradeStats.getCampUpgradeStat(CampUpgradeEnum.MOUNTAIN_KING, SavedInfoManager.getNpcLvl(CampUpgradeEnum.MOUNTAIN_KING) - 1);
                if (currHero == CharacterEnum.HERO_PIRATE) result += 0.25f;
                return result;
            case EXP_MULTIPLIER:
                result = 0;
                if (selectedPerk == PerkEnum.EXP_BONUS)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                if (SavedInfoManager.getNpcLvl(CampUpgradeEnum.PRINCESS) > 0)
                    result += CampUpgradeStats.getCampUpgradeStat(CampUpgradeEnum.PRINCESS, SavedInfoManager.getNpcLvl(CampUpgradeEnum.PRINCESS) - 1);
                if (currHero == CharacterEnum.HERO_BABY) result += 0.5f;
                return result;
            case POISON_DURATION_EFFECT:
                result = 0;
                if (selectedPerk == PerkEnum.POISON_DURATION_DECREASE)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case MAGICAL_RESIST:
                result = 0;
                if (selectedPerk == PerkEnum.MAGICAL_RESIST)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case INSTAKILL_ENEMY_CHANCE:
                result = 0;
                if (selectedPerk == PerkEnum.INSTAKILL_ENEMY)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case CHANCE_FOR_ANY_DROP:
                result = 0;
                if (selectedPerk == PerkEnum.CHANCE_FOR_ANY_DROP)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case CHANCE_FOR_MISS_STAGE:
                result = 0;
                if (selectedPerk == PerkEnum.MISS_STAGE)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case CHANCE_FOR_RANDOM_APPLE:
                result = 0;
                if (selectedPerk == PerkEnum.APPLE_FINDER)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case CHANCE_FOR_SECOND_REVIVE:
                result = 0;
                if (selectedPerk == PerkEnum.THIRD_CHANCE)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case CHANCE_FOR_MAGIC_MIRROR:
                result = 0;
                if (selectedPerk == PerkEnum.MAGIC_MIRROR)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case ENEMY_POWER_MULTIPLIER:
                result = 0;
                if (selectedPerk == PerkEnum.STRONGER_ENEMIES)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;
            case DMG_MULTIPLIER_LESS_THAN_30_PERCENT_HP:
                result = 0;
                if (selectedPerk == PerkEnum.BERSERKER)
                    result += PerkStats.getPerkStat(selectedPerk, SavedInfoManager.getPerkLvl(selectedPerk));
                return result;

        }

        return result;

    }

    private static float getItemStats(CompleteHeroStatsEnum statsEnum) {
        float result = 0;

        switch (statsEnum) {
            case ARMOR:
                for (AbstractItem item : equippedItems) {
                    if (item.getItemTypeEnum() == ItemTypeEnum.ARMOR)
                        result += ((Armor) item).getArmor();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.HELMET)
                        result += ((Helmet) item).getArmor();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.SHIELD)
                        result += ((Shield) item).getArmor();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.RING)
                        result += ((Ring) item).getArmor();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.NECKLACE)
                        result += ((Necklace) item).getArmor();
                }
                break;

            case RANGE:
                for (AbstractItem item : equippedItems) {
                    if (item.getItemTypeEnum() == ItemTypeEnum.BOW)
                        result += ((Bow) item).getRange();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.ARROW)
                        result += ((Arrow) item).getRangeEffect();
                }
                break;

            case MELE_DMG:
                for (AbstractItem item : equippedItems) {
                    if (item.getItemTypeEnum() == ItemTypeEnum.MELE)
                        result += ((MeleWeapon) item).getDmg();
                }
                break;

            case BOW_DMG:
                for (AbstractItem item : equippedItems) {
                    if (item.getItemTypeEnum() == ItemTypeEnum.BOW)
                        result += ((Bow) item).getDmg();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.ARROW)
                        result += ((Arrow) item).getDmgEffect();
                }
                break;

            case MAGICAL_DMG:
                for (AbstractItem item : equippedItems) {
                    if (item.getItemTypeEnum() == ItemTypeEnum.STAFF)
                        result += ((Staff) item).getDmg();
                }
                break;

            case SPEED:
                for (AbstractItem item : equippedItems) {
                    if (item.getItemTypeEnum() == ItemTypeEnum.ARMOR)
                        result += ((Armor) item).getMoveSpeedCost();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.MELE)
                        result += ((MeleWeapon) item).getSpeedEffect();
                    else if (item.getItemTypeEnum() == ItemTypeEnum.SHIELD)
                        result += ((Shield) item).getSpeedEffect();
                }
                break;

        }

        return result;
    }

    public static PerkEnum getPerk() {
        return selectedPerk;
    }

    public static void itemUsed(AbstractItem item) {
        if (item.getItemTypeEnum() == ItemTypeEnum.BOOK) {
            expEffect(((Book) item).getExpEffect());
        } else if (item.getItemTypeEnum() == ItemTypeEnum.FOOD) {
            hpEffect(((Food) item).getHpEffect());
            hpEffect(((Food) item).getMpEffect());
        }
        CommunicatePrinter.usedItem(
                StringsManager.getItemName(item.getItemEnum()));
    }

    public static void hpEffect(int hpEffect) {
        Float curr = currStats.get(CompleteHeroStatsEnum.HP);
        Float max = currStats.get(CompleteHeroStatsEnum.MAX_HP);
        curr += hpEffect;
        if (curr > max) curr = max;
        if (curr <= 0) {
            curr = 0f;
            GameScreen.heroDied();
        }
        currStats.put(CompleteHeroStatsEnum.HP, curr);
        StatusBarContainer.setHp(currStats.get(CompleteHeroStatsEnum.HP) / currStats.get(CompleteHeroStatsEnum.MAX_HP));
        HeroStatWindow.refreshStats();
        HeroStatWindow.refreshBars();
    }

    public static void mpEffect(int mpEffect) {
        Float curr = currStats.get(CompleteHeroStatsEnum.MP);
        Float max = currStats.get(CompleteHeroStatsEnum.MAX_MP);
        curr += mpEffect;
        if (curr > max) curr = max;
        currStats.put(CompleteHeroStatsEnum.MP, curr);
        HeroStatWindow.refreshStats();
        HeroStatWindow.refreshBars();
    }

    public static void expEffect(int expEffect) {
        Float curr = currStats.get(CompleteHeroStatsEnum.EXP);
        Float max = currStats.get(CompleteHeroStatsEnum.MAX_EXP);
        curr += expEffect;
        if (curr >= max) {
            curr -= max;
            SavedInfoManager.saveCharacterLvl(currHero, SavedInfoManager.getCharacterLvl(currHero));
            lvlUp();
            lvlUps.put(currHero, true);
        }
        currStats.put(CompleteHeroStatsEnum.EXP, curr);
        SavedInfoManager.saveCharacterExp(currHero, curr.intValue());
        StatusBarContainer.setExp(currStats.get(CompleteHeroStatsEnum.EXP) / currStats.get(CompleteHeroStatsEnum.MAX_EXP));
        HeroStatWindow.refreshStats();
        HeroStatWindow.refreshBars();

        expCollected.put(currHero, expCollected.get(currHero) + expEffect);
    }

    private static HashMap<CompleteHeroStatsEnum, Float> getItemEffects() {
        HashMap<CompleteHeroStatsEnum, Float> result = new HashMap<>();

        CompleteHeroStatsEnum[] allStats = CompleteHeroStatsEnum.values();
        for (CompleteHeroStatsEnum c : allStats) result.put(c, 0f);

        for (AbstractItem item : equippedItems) {
            for (ItemEffect effect : item.getEffects()) {
                switch (effect.getEffectEnum()) {
                    case DMG_BONUS:
                        result.put(CompleteHeroStatsEnum.MELE_DMG, result.get(CompleteHeroStatsEnum.MELE_DMG) + effect.getPower());
                        break;
                    case STR_BONUS:
                        result.put(CompleteHeroStatsEnum.STR, result.get(CompleteHeroStatsEnum.STR) + effect.getPower());
                        break;
                    case DEX_BONUS:
                        result.put(CompleteHeroStatsEnum.DEX, result.get(CompleteHeroStatsEnum.DEX) + effect.getPower());
                        break;
                    case LCK_BONUS:
                        result.put(CompleteHeroStatsEnum.LCK, result.get(CompleteHeroStatsEnum.LCK) + effect.getPower());
                        break;
                    case INT_BONUS:
                        result.put(CompleteHeroStatsEnum.INT, result.get(CompleteHeroStatsEnum.INT) + effect.getPower());
                        break;
                    case VIT_BONUS:
                        result.put(CompleteHeroStatsEnum.VIT, result.get(CompleteHeroStatsEnum.VIT) + effect.getPower());
                        break;
                    case PERCENT_HP_REGEN_CLEAN_ROOM:
                        result.put(CompleteHeroStatsEnum.HP_RESTORE_ROOM, result.get(CompleteHeroStatsEnum.HP_RESTORE_ROOM) + effect.getPower());
                        break;
                    case PERCENT_GOLD_COLLECTED_BONUS:
                        result.put(CompleteHeroStatsEnum.GOLD_MULTIPLIER, result.get(CompleteHeroStatsEnum.GOLD_MULTIPLIER) + effect.getPower());
                        break;
                    case PERCENT_EXP_COLLECTED_BONUS:
                        result.put(CompleteHeroStatsEnum.EXP_MULTIPLIER, result.get(CompleteHeroStatsEnum.EXP_MULTIPLIER) + effect.getPower());
                        break;
                    case PERCENT_CHANCE_FOR_DOUBLE_ATTACK:
                        result.put(CompleteHeroStatsEnum.DOUBLE_ATTACK_CHANCE, result.get(CompleteHeroStatsEnum.DOUBLE_ATTACK_CHANCE) + effect.getPower());
                        break;
                    case PERCENT_CHANCE_FOR_POISONING:
                        result.put(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_POISONING, result.get(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_POISONING) + effect.getPower());
                        break;
                    case PERCENT_CHANCE_FOR_BURNING:
                        result.put(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_BURNING, result.get(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_BURNING) + effect.getPower());
                        break;
                    case PERCENT_CHANCE_FOR_DOUBLE_MOVE:
                        result.put(CompleteHeroStatsEnum.DOUBLE_MOVE_CHANCE, result.get(CompleteHeroStatsEnum.DOUBLE_MOVE_CHANCE) + effect.getPower());
                        break;
                    case PERCENT_HP_REGEN_KILL:
                        result.put(CompleteHeroStatsEnum.HP_RESTORE_KILL, result.get(CompleteHeroStatsEnum.HP_RESTORE_KILL) + effect.getPower());
                        break;
                    case PERCENT_ANY_DROP_CHANCE:
                        result.put(CompleteHeroStatsEnum.PERCENT_ANY_DROP_CHANCE, result.get(CompleteHeroStatsEnum.PERCENT_ANY_DROP_CHANCE) + effect.getPower());
                        break;
                    case PERCENT_SPELL_CHANCE:
                        result.put(CompleteHeroStatsEnum.PERCENT_SPELL_CHANCE, result.get(CompleteHeroStatsEnum.PERCENT_SPELL_CHANCE) + effect.getPower());
                        break;
                }
            }
        }
        return result;
    }

    public static float getCurrentStat(CompleteHeroStatsEnum stat) {
        return currStats.get(stat);
    }

    public static AbstractItem getRangedWeapon() {
        for (AbstractItem item : equippedItems) {
            if (
                    item.getItemTypeEnum() == ItemTypeEnum.BOW
                            || item.getItemTypeEnum() == ItemTypeEnum.STAFF) return item;
        }
        return null;
    }

    public static AbstractItem getMeleWeapon() {
        for (AbstractItem item : equippedItems)
            if (item.getItemTypeEnum() == ItemTypeEnum.MELE) return item;
        return null;
    }

    public static AbstractItem getArrow() {
        for (AbstractItem item : equippedItems)
            if (item.getItemTypeEnum() == ItemTypeEnum.ARROW) return item;
        return null;
    }


    public static HashMap<CharacterEnum, Boolean> getLvlUps() {
        return lvlUps;
    }

    public static HashMap<CharacterEnum, Integer> getExpCollected() {
        return expCollected;
    }

    public static Image getProjectile() {
        return projectile;
    }

    public static void statAdd(StatisticEnum statisticEnum) {
        if (statisticEnum == StatisticEnum.STR) currStats.put(CompleteHeroStatsEnum.STR, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.STR) + 1);
        else if (statisticEnum == StatisticEnum.DEX) currStats.put(CompleteHeroStatsEnum.DEX, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.DEX) + 1);
        else if (statisticEnum == StatisticEnum.INT) currStats.put(CompleteHeroStatsEnum.INT, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.INT) + 1);
        else if (statisticEnum == StatisticEnum.LCK) currStats.put(CompleteHeroStatsEnum.LCK, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.LCK) + 1);
        else if (statisticEnum == StatisticEnum.VIT) currStats.put(CompleteHeroStatsEnum.VIT, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.VIT) + 1);

        SavedInfoManager.saveCharacterStat(
                currHero,
                statisticEnum,
                SavedInfoManager.getCharacterStat(currHero, statisticEnum) + 1);

        HeroStatWindow.refreshStats();
    }

    public static Array<CharacterEnum> getUsedCharacters() {
        return usedCharacters;
    }

    public static CharacterStatsObject getStats() {
        return stats;
    }

    public static void setSelectedPerk(PerkEnum perk) {
        selectedPerk = perk;
    }

    private static void getBasicStats() {
        if (currStats == null) currStats = new HashMap<>();
        else currStats.clear();

        currStats.put(CompleteHeroStatsEnum.STR, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.STR));
        currStats.put(CompleteHeroStatsEnum.DEX, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.DEX));
        currStats.put(CompleteHeroStatsEnum.INT, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.INT));
        currStats.put(CompleteHeroStatsEnum.LCK, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.LCK));
        currStats.put(CompleteHeroStatsEnum.VIT, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.VIT));
        currStats.put(CompleteHeroStatsEnum.LVL, (float)SavedInfoManager.getCharacterLvl(currHero));
        currStats.put(CompleteHeroStatsEnum.HP, 1f);
        currStats.put(CompleteHeroStatsEnum.MP, 1f);
        currStats.put(CompleteHeroStatsEnum.MAX_MP, 1f);
        currStats.put(CompleteHeroStatsEnum.MAX_HP, 1f);
        currStats.put(CompleteHeroStatsEnum.EXP, (float)SavedInfoManager.getCharacterExp(currHero));
        currStats.put(CompleteHeroStatsEnum.MAX_EXP, (float)HeroStats.getExpCap(SavedInfoManager.getCharacterLvl(currHero)));

        currStats.put(CompleteHeroStatsEnum.CRIT_CHANCE, (currStats.get(CompleteHeroStatsEnum.LCK) * 10f) / (currStats.get(CompleteHeroStatsEnum.LVL) * 3f));
        currStats.put(CompleteHeroStatsEnum.MISS_CHANCE, 0.05f);

        currStats.put(CompleteHeroStatsEnum.RANGE, 0f);
        currStats.put(CompleteHeroStatsEnum.ARMOR,0f);
        currStats.put(CompleteHeroStatsEnum.DOUBLE_ATTACK_CHANCE,0f);
        currStats.put(CompleteHeroStatsEnum.DOUBLE_MOVE_CHANCE,0f);
        currStats.put(CompleteHeroStatsEnum.GOLD_MULTIPLIER,1f);
        currStats.put(CompleteHeroStatsEnum.EXP_MULTIPLIER,1f);
        currStats.put(CompleteHeroStatsEnum.MELE_DMG_MULTIPLIER,1f);
        currStats.put(CompleteHeroStatsEnum.MAGICAL_DMG_MULTIPLIER,1f);
        currStats.put(CompleteHeroStatsEnum.BOW_DMG_MULTIPLIER,1f);
        currStats.put(CompleteHeroStatsEnum.HP_RESTORE_ROOM,0.05f);
        currStats.put(CompleteHeroStatsEnum.HP_RESTORE_KILL,0f);
        currStats.put(CompleteHeroStatsEnum.POISON_DURATION_EFFECT,0f);
        currStats.put(CompleteHeroStatsEnum.MAGICAL_RESIST,0f);
        currStats.put(CompleteHeroStatsEnum.INSTAKILL_ENEMY_CHANCE,0f);
        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_ANY_DROP,0.1f);
        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_MISS_STAGE,0f);
        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_RANDOM_APPLE,0f);
        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_RANDOM_KEY,0f);
        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_SECOND_REVIVE,0f);
        currStats.put(CompleteHeroStatsEnum.CHANCE_FOR_MAGIC_MIRROR,0f);
        currStats.put(CompleteHeroStatsEnum.ENEMY_POWER_MULTIPLIER,1f);
        currStats.put(CompleteHeroStatsEnum.DMG_MULTIPLIER_LESS_THAN_30_PERCENT_HP,1f);
        currStats.put(CompleteHeroStatsEnum.SPEED, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.DEX) - SavedInfoManager.getCharacterLvl(currHero));
        currStats.put(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_DOUBLE_MOVE,0.05f);
        currStats.put(CompleteHeroStatsEnum.PERCENT_HP_REGEN_KILL,0.05f);
        currStats.put(CompleteHeroStatsEnum.PERCENT_ANY_DROP_CHANCE,0.05f);
        currStats.put(CompleteHeroStatsEnum.PERCENT_SPELL_CHANCE,0.05f);
        currStats.put(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_POISONING,0f);
        currStats.put(CompleteHeroStatsEnum.PERCENT_CHANCE_FOR_BURNING,0f); 
        currStats.put(CompleteHeroStatsEnum.MELE_DMG, (float)SavedInfoManager.getCharacterStat(currHero, StatisticEnum.STR));
        currStats.put(CompleteHeroStatsEnum.BOW_DMG, 0f);
        currStats.put(CompleteHeroStatsEnum.MAGICAL_DMG, 0f);
    }

    public static Array<AbstractItem> getBackpackItems() {
        return backpackItems;
    }

    public static Array<AbstractItem> getEquippedItems() {
        return equippedItems;
    }

    private static void refreshProjectile() {
        if (getArrow() != null) projectile.setDrawable(GraphicsManager.getItemImage(getArrow().getItemEnum()));
        else if (getRangedWeapon() != null) projectile.setDrawable(GraphicsManager.getProjectile(getRangedWeapon().getItemEnum()));
    }

}
