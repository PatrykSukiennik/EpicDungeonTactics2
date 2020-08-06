package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class Move {

    private float duration;
    private Array<MapTile> path;
    private SequenceAction moveAction;

    public Array<MapTile> getPath() {
        return path;
    }

    public float getDuration() {
        return duration;
    }

    public SequenceAction getMoveAction() {
        return moveAction;
    }
}
