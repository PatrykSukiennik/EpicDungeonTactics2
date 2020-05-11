package com.appatstudio.epicdungeontactics2;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.assets.AssetsMaster;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.FinancesStats;
import com.appatstudio.epicdungeontactics2.view.LoadingScreen;
import com.appatstudio.epicdungeontactics2.view.campUpgradeScreen.CampUpgradeScreen;
import com.appatstudio.epicdungeontactics2.view.financesScreen.FinancesUpgradeScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.view.perkScreen.PerkScreen;
import com.appatstudio.epicdungeontactics2.view.statsScreen.StatsScreen;
import com.appatstudio.epicdungeontactics2.view.viewElements.IdleGoldCollectedBanner;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import java.util.Random;

import static com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum.GAME_SCREEN;
import static com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum.MENU_SCREEN;

public class EpicDungeonTactics extends ApplicationAdapter {

    private static AndroidCommunication androidCommunication;

    public static Random random;

    private static LoadingScreen loadingScreen;
    private static MenuScreen menuScreen;
    private static StatsScreen statsScreen;
    private static PerkScreen perkScreen;
    private static CampUpgradeScreen campUpgradeScreen;
    private static FinancesUpgradeScreen financesUpgradeScreen;
    private static GameScreen gameScreen;

    private static CurrentScreenEnum currentScreen;

    private static CharacterEnum selectedHero;
    private static PerkEnum selectedPerk;

    private static IdleGoldCollectedBanner idleGoldCollectedBanner;

    private static boolean isTablet = false;

    static {
        random = new Random();
    }

    public static void startGame() {
        gameScreen = new GameScreen(selectedHero, selectedPerk);
        setCurrentScreen(GAME_SCREEN);
    }

    public EpicDungeonTactics(AndroidCommunication ac) {
        androidCommunication = ac;
    }

    public static void characterUnlocked() {
        if (campUpgradeScreen != null) CampUpgradeScreen.updateMainCharacters();
    }

    @Override
    public void create() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        isTablet = (float)Gdx.graphics.getHeight()/Gdx.graphics.getWidth() < 16f/10;

        currentScreen = CurrentScreenEnum.LOADING_SCREEN;
        loadingScreen = new LoadingScreen();

        AssetsMaster.init();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (currentScreen) {
            case LOADING_SCREEN:
                loadingScreen.draw(AssetsMaster.getProgress());

                if (AssetsMaster.isFinished()) {
                    AssetsMaster.loaded();
                    loadingScreen.dispose();
                    setCurrentScreen(MENU_SCREEN);
                }
                break;

            case MENU_SCREEN:
                menuScreen.draw();
                break;

            case STATS_SCREEN:
                if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                    setCurrentScreen(MENU_SCREEN);
                }
                statsScreen.draw();
                break;

            case PERK_SCREEN:
                if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                    setCurrentScreen(MENU_SCREEN);
                }
                perkScreen.draw();
                break;

            case CAMP_UPGRADE_SCREEN:
                if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                    setCurrentScreen(MENU_SCREEN);
                }
                campUpgradeScreen.draw();
                break;

            case FINANCES_UPGRADE_SCREEN:
                if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                    setCurrentScreen(MENU_SCREEN);
                }
                financesUpgradeScreen.draw();
                break;

            case GAME_SCREEN:
                if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                    gameScreen.backPressed();
                }
                gameScreen.draw();
                break;
        }

        if (idleGoldCollectedBanner != null) idleGoldCollectedBanner.draw();
    }

    @Override
    public void dispose() {
        AssetsMaster.dispose();
    }

    public static void setCurrentScreen(CurrentScreenEnum newCurrentScreen) {
        switch (newCurrentScreen) {
            case MENU_SCREEN:
                if (menuScreen == null) menuScreen = new MenuScreen();
                if (idleGoldCollectedBanner == null) idleGoldCollectedBanner = new IdleGoldCollectedBanner();
                menuScreen.draw();
                SavedInfoManager.checkChangeDay();
                break;
            case STATS_SCREEN:
                if (statsScreen == null) statsScreen = new StatsScreen();
                statsScreen.draw();
                SavedInfoManager.checkChangeDay();
                break;
            case PERK_SCREEN:
                if (perkScreen == null) perkScreen = new PerkScreen();
                perkScreen.draw();
                break;
            case CAMP_UPGRADE_SCREEN:
                if (campUpgradeScreen == null) campUpgradeScreen = new CampUpgradeScreen();
                campUpgradeScreen.show();
                SavedInfoManager.checkChangeDay();
                campUpgradeScreen.draw();
                break;
            case FINANCES_UPGRADE_SCREEN:
                if (financesUpgradeScreen == null) financesUpgradeScreen = new FinancesUpgradeScreen();
                SavedInfoManager.checkChangeDay();
                financesUpgradeScreen.draw();
                break;
            case GAME_SCREEN:
                //startGame() should be done yet
                gameScreen.draw();
                break;
        }

        currentScreen = newCurrentScreen;
    }

    public static void tap(float x, float y) {
        switch (currentScreen) {
            case MENU_SCREEN:
                menuScreen.tap(x, y);
                break;
            case STATS_SCREEN:
                statsScreen.tap(x, y);
                break;
            case PERK_SCREEN:
                perkScreen.tap(x, y);
                break;
            case CAMP_UPGRADE_SCREEN:
                campUpgradeScreen.tap(x, y);
                break;
            case FINANCES_UPGRADE_SCREEN:
                financesUpgradeScreen.tap(x, y);
                break;
            case GAME_SCREEN:
                gameScreen.tap(x, y);
        }
    }

    public static void setSelectedHero(CharacterEnum selectedHero) {
        EpicDungeonTactics.selectedHero = selectedHero;
    }

    public static void setSelectedPerk(PerkEnum selectedPerk) {
        EpicDungeonTactics.selectedPerk = selectedPerk;
    }

    public static void swiped(DirectionEnum directionEnum) {
        if (currentScreen == MENU_SCREEN) {
            menuScreen.swiped(directionEnum);
        }
    }

    public static void runEnded() {
        menuScreen = new MenuScreen();
        perkScreen = new PerkScreen();
    }

    public static void updateGold() {
        if (menuScreen != null) menuScreen.updateGold();
        if (perkScreen != null) perkScreen.updateGold();
        if (statsScreen != null) statsScreen.updateGold();
        if (campUpgradeScreen != null) campUpgradeScreen.updateGold();
        if (gameScreen != null) gameScreen.updateGold();
    }

    public static boolean isInternetOn() {
        return androidCommunication.checkInternetConnection();
    }

    public static void generateInterstitialAd() {
        androidCommunication.generateInterstitialAd();
    }

    public static boolean isTablet() {
        return isTablet;
    }

    public static void reportDayChanged(int days) {
        int income = 0;
        FinanceUpgradeEnum allFinances[] = FinanceUpgradeEnum.values();
        for (FinanceUpgradeEnum f : allFinances) {
            income += SavedInfoManager.getFinancesLvl(f) * FinancesStats.getIncome(f) * days;
        }
        if (income > 0) {
            GlobalValues.addGold(income);
            if (idleGoldCollectedBanner == null) idleGoldCollectedBanner = new IdleGoldCollectedBanner();
            idleGoldCollectedBanner.show(income);
        }
    }
}
