package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import lombok.Getter;

public class TurnAction extends AbstractCharacterAction {
    private final SequenceAction turn;

    public TurnAction() {
        turn = new SequenceAction();
    }

    public void addAction(AbstractCharacterAction action) {
        turn.addAction(action);
    }

    public void addAction(SequenceAction action) {
        turn.addAction(action);
    }

    public void addAction(Action action) {
        turn.addAction(action);
    }

    @Override
    public boolean act(float delta) {
        return turn.act(delta);
    }
}
