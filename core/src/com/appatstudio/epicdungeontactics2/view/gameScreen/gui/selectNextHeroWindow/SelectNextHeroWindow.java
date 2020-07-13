package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.selectNextHeroWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.PlayerStatisticsStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class SelectNextHeroWindow {

    HashMap<CharacterEnum, Boolean> characterAvailability;
    NewHeroLine[] newHeroLines;
    private Image bg;

    public SelectNextHeroWindow(StatTracker statTracker) {
        bg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0, 0);

        characterAvailability = new HashMap<>();

        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_ELF)) {
            characterAvailability.put(
                    CharacterEnum.HERO_ELF,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_ELF, false));
        }
        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_KNIGHT)) {
            characterAvailability.put(
                    CharacterEnum.HERO_KNIGHT,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_KNIGHT, false));
        }
        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_WIZZARD)) {
            characterAvailability.put(
                    CharacterEnum.HERO_WIZZARD,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_WIZZARD, false));
        }
        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_LIZARD)) {
            characterAvailability.put(
                    CharacterEnum.HERO_LIZARD,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_LIZARD, false));
        }
        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_PIRATE)) {
            characterAvailability.put(
                    CharacterEnum.HERO_PIRATE,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_PIRATE, false));
        }
        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_NINJA)) {
            characterAvailability.put(
                    CharacterEnum.HERO_NINJA,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_NINJA, false));
        }
        if (SavedInfoManager.isUnlocked(CharacterEnum.HERO_BABY)) {
            characterAvailability.put(
                    CharacterEnum.HERO_BABY,
                    !statTracker.getUsedCharacters().contains(CharacterEnum.HERO_BABY, false));
        }

        newHeroLines = new NewHeroLine[characterAvailability.size()];

        float segmentSize = FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED), "0") * 1.2f;
        float fullHeight = characterAvailability.size() * segmentSize;


        CharacterEnum[] keys = (CharacterEnum[]) characterAvailability.keySet().toArray();
        for (int i=0; i<keys.length; i++) {
            newHeroLines[i] = new NewHeroLine(
                    keys[i],
                    SavedInfoManager.getCharacterLvl(keys[i]),
                    Gdx.graphics.getHeight()/2f - fullHeight/2f + i*segmentSize,
                    characterAvailability.get(keys[i]));
        }

    }

    public void draw(SpriteBatch batch) {
        bg.draw(batch, 1f);

        for (NewHeroLine nhl : newHeroLines) {
            nhl.draw(batch);
        }
    }

    public CharacterEnum tap(float x, float y) {
        for (NewHeroLine nhl : newHeroLines) {
            if (nhl.tap(x, y)) return nhl.getCharacterEnum();
        }
        return null;
    }


}
