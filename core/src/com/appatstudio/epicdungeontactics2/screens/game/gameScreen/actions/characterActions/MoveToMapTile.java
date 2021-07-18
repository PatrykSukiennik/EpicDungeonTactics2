 package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

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
