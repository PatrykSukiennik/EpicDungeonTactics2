package com.appatstudio.epicdungeontactics2;

import com.appatstudio.epicdungeontactics2.global.assets.AssetsMaster;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.view.LoadingScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.view.perkScreen.PerkScreen;
import com.appatstudio.epicdungeontactics2.view.statsScreen.StatsScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
    private static GameScreen gameScreen;

    private static CurrentScreenEnum currentScreen;

    private static CharacterEnum selectedHero;
    private static PerkEnum selectedPerk;

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

    @Override
    public void create() {
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
                statsScreen.draw();
                break;

            case PERK_SCREEN:
                perkScreen.draw();
                break;

            case GAME_SCREEN:
                gameScreen.draw();
                break;
        }
    }

    @Override
    public void dispose() {
        AssetsMaster.dispose();
    }

    public static void setCurrentScreen(CurrentScreenEnum newCurrentScreen) {
        switch (newCurrentScreen) {
            case MENU_SCREEN:
                if (menuScreen == null) menuScreen = new MenuScreen();
                menuScreen.draw();
                break;
            case STATS_SCREEN:
                if (statsScreen == null) statsScreen = new StatsScreen();
                statsScreen.draw();
                break;
            case PERK_SCREEN:
                if (perkScreen == null) perkScreen = new PerkScreen();
                perkScreen.draw();
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
            case GAME_SCREEN:

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

    public static void updateGold() {
        if (menuScreen != null) menuScreen.updateGold();
        if (perkScreen != null) perkScreen.updateGold();
        if (statsScreen != null) statsScreen.updateGold();
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
}
