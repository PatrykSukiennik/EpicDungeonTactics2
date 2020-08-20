package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

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
