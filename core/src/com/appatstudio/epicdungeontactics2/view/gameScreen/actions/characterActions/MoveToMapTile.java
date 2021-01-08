 package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class MoveToMapTile extends AbstractCharacterAction {
    private SequenceAction sequenceAction;

    public MoveToMapTile(SequenceAction action) {
        this.sequenceAction = action;
    }

    @Override
    public boolean act(float delta) {
        return sequenceAction.act(delta);
    }

    public SequenceAction getSequence() {
        return sequenceAction;
    }
}
