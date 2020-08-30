package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ChangeAlphaAction extends Action {

    private float targetAlpha = 1f;
    private Actor actor;

    public ChangeAlphaAction(float target, Actor actor) {
        this.targetAlpha = target;
        this.actor = actor;
    }

    @Override
    public boolean act(float delta) {
        this.actor.getColor().a = targetAlpha;
        return true;
    }
}
