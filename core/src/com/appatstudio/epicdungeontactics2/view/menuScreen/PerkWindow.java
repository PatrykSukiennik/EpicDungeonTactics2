package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class PerkWindow {

    private Image bg;
    private PerkIcon[] perks;
    private PerkIcon selectedPerk;

    public boolean tap(float x, float y, CharacterEnum selectedHero) {
        for (PerkIcon perkIcon : perks) {
            if (perkIcon.tap(x, y)) {
                if (selectedPerk == perkIcon) {
                    perkIcon.setSelected(false);
                    selectedPerk = null;
                }
                else {
                    selectedPerk = perkIcon;
                    perkIcon.setSelected(true);
                }
                return true;
            }
        }
        return false;
    }

    public static void show() {

    }

    public static void hide() {

    }

}
