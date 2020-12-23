package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;

import lombok.Getter;

public abstract class AbstractCharacterAction extends Action {
    @Getter protected float duration;

    void draw(Batch batch) {}
}
