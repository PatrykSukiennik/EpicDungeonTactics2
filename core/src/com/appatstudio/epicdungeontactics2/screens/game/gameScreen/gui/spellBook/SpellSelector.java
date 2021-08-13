package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.spellBook;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

public class SpellSelector {

    Array<KnownSpell> spells;
    KnownSpell selectedSpell;
    float topY;
    float iconSize;

    public SpellSelector(float topY, float size) {
        spells = new Array<>();
        this.topY = topY;
        this.iconSize = size;

        selectedSpell = null;
        System.out.println("DZIALAJ");
    }

    public void draw(Batch guiBatch) {
        //refreshSpells();
        for (KnownSpell spell : spells) {
            spell.draw(guiBatch, selectedSpell == spell);
            //System.out.println("DRAW CALLED INNER");
        }
        //System.out.println(spells.size);
    }

    public void refreshSpells() {
        selectedSpell = null;
        spells.clear();

        Array<KnownSpell> statTrackerSpells = StatTracker.getKnownSpells();
        for (int i=0; i<statTrackerSpells.size; i++) {
            spells.add(statTrackerSpells.get(i));
            spells.get(i).setPosition(iconSize * 0.15f, topY - i * iconSize);
            spells.get(i).setSize(iconSize * 0.7f, iconSize * 0.7f);
            spells.get(i).setY(topY - i * iconSize);
        }
        System.out.println("REFRESHED SPELLS: " + spells.size);
    }

    public KnownSpell getSelectedSpell() {
        return selectedSpell;
    }

    public void refreshMp() {
        for (KnownSpell spell : spells) spell.refreshIsEnoughMP();
    }

    public void setSelectedSpell(KnownSpell selectedSpell) {
        this.selectedSpell = selectedSpell;
    }

    public boolean tap(float x, float y) {
        for (KnownSpell spell : spells) {
            if (spell.isTap(x, y)) {
                if (spell == selectedSpell) {
                    selectedSpell = null;
                } else {
                    selectedSpell = spell;
                }
                return true;
            }
        }
        return false;
    }

}
