package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.badlogic.gdx.scenes.scene2d.Action;

public class SoundPlayAction extends Action {

    SoundEnum sound;

    public SoundPlayAction(SoundEnum sound) {
        this.sound = sound;
    }

    @Override
    public boolean act(float delta) {
        SoundsManager.playSound(this.sound);
        return true;
    }
}
