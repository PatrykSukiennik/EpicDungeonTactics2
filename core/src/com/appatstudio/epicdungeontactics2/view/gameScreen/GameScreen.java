package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runQuitWindow.RunQuitWindow;
import com.badlogic.gdx.graphics.Color;

public final class GameScreen {

    private CharacterEnum hero;
    private PerkEnum perk;

    private static int stage = 1;

    private static GuiContainer guiContainer;
    
    private static final Color DAY_COLOR = new Color(1f, 1f, 1f, 0f);
    private static final Color NIGHT_COLOR = new Color(0f, 0f, 0.3f, 0.45f);

    public GameScreen(CharacterEnum hero, PerkEnum perk) {
        this.hero = hero;
        this.perk = perk;
        stage = 1;

        guiContainer = new GuiContainer(this);

    }

    public void backPressed() {
        guiContainer.backPressed();
    }

    public void tap(float x, float y) {
        guiContainer.tap(x, y);
    }

    public void draw() {
        guiContainer.draw();

    }

    public CharacterEnum getHero() {
        return hero;
    }

    public PerkEnum getPerk() {
        return perk;
    }

    public void updateGold() {
        guiContainer.updateGold();
    }

    public static int getStage() {
        return stage;
    }
}
