package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MapAction {

    private float duration;
    private SequenceAction moveAction;

    public MapAction(CharacterDrawable character, SequenceAction moveAction) {
        this.moveAction = moveAction;
        character.addAction(moveAction);
    }

    public float getDuration() {
        return duration;
    }

    public SequenceAction getMoveAction() {
        return moveAction;
    }
}
