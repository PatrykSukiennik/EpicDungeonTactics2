package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class MoveToMapTile {
    private float duration;
    private SequenceAction sequenceAction;

    public MoveToMapTile(SequenceAction action, float duration) {
        this.sequenceAction = action;
        this.duration = duration;
    }

    public float getDuration() {
        return duration;
    }

    public SequenceAction getSequenceAction() {
        return sequenceAction;
    }

}
