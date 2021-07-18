package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.badlogic.gdx.Gdx;

public class Wait extends AbstractCharacterAction{

    float duration;

    public Wait(float duration) {
        this.duration = duration;
    }

    @Override
    public boolean act(float delta) {
        duration -= Gdx.graphics.getDeltaTime();
        return duration <= 0;
    }
}
