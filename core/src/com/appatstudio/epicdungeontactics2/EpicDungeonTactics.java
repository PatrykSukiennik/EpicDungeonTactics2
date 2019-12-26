package com.appatstudio.epicdungeontactics2;

import com.appatstudio.epicdungeontactics2.global.assets.AssetsMaster;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.view.LoadingScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.menuScreen.MenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.Random;

import static com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum.MENU_SCREEN;

public class EpicDungeonTactics extends ApplicationAdapter {

	public static Random random;

	private static LoadingScreen loadingScreen;
	private static MenuScreen menuScreen;
	private static GameScreen gameScreen;

	private static CurrentScreenEnum currentScreen;

	private static CharacterEnum selectedHero;
	private static PerkEnum selectedPerk;

	static {
		random = new Random();
	}

	public static void startGame() {

	}

	@Override
	public void create () {
		currentScreen = CurrentScreenEnum.LOADING_SCREEN;
		loadingScreen = new LoadingScreen();

		AssetsMaster.init();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,  0, 0, 1);
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

			case GAME_SCREEN:
				gameScreen.draw();
				break;
		}
	}

	@Override
	public void dispose () {
		AssetsMaster.dispose();
	}

	public static void setCurrentScreen(CurrentScreenEnum newCurrentScreen) {
		switch (newCurrentScreen) {
			case MENU_SCREEN:
				if (menuScreen == null) menuScreen = new MenuScreen();
				menuScreen.draw();
				break;
			case GAME_SCREEN:
				gameScreen = new GameScreen();
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
			case GAME_SCREEN:

		}
	}

	public static void setSelectedHero(CharacterEnum selectedHero) {
		EpicDungeonTactics.selectedHero = selectedHero;
	}

	public static void setSelectedPerk(PerkEnum selectedPerk) {
		EpicDungeonTactics.selectedPerk = selectedPerk;
	}

	public static CurrentScreenEnum getCurrentScreen() {
		return currentScreen;
	}

	public static void swiped(DirectionEnum directionEnum) {
		switch (currentScreen) {
			case MENU_SCREEN: menuScreen.swiped(directionEnum); break;
		}
	}
}
