package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.communicatePrinter.CommunicatePrinter;

public class DamageGiving extends AbstractCharacterAction{

    private CharacterDrawable target;
    private int dmg;
    private boolean isCrit;

    public DamageGiving(CharacterDrawable target, int dmg) {
        this.target = target;
        this.dmg = dmg;
        this.isCrit = false;
    }

    @Override
    public boolean act(float delta) {
        if (target.isHero()) {
            CommunicatePrinter.dmgGot(dmg, StringsManager.getCharacterName(target.getCharacterEnum()), isCrit);
        }
        else CommunicatePrinter.dmgDealt(dmg, StringsManager.getCharacterName(target.getCharacterEnum()), isCrit);

        target.dmgGet(dmg);
        return true;
    }
}
