package com.appatstudio.epicdungeontactics2.view.gameScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;

public final class GameScreen {

    private CharacterEnum hero;
    private PerkEnum perk;

    private static GuiContainer guiContainer;

    public GameScreen(CharacterEnum hero, PerkEnum perk) {

        this.hero = hero;
        this.perk = perk;


        guiContainer = new GuiContainer(this);

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
}
