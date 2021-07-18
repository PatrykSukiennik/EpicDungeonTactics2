package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.SoundPlayAction;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class TurnAction extends SequenceAction {
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

    public void addSequenceAction(MoveToMapTile moveToMapTile) {
        for (Action a : moveToMapTile.getSequence().getActions()) {
            turn.addAction(a);
        }
    }

    public void addSequenceAction(Array<Action> actions) {
        for (Action a : actions) {
            turn.addAction(a);
//            turn.addAction(new SoundPlayAction(GameScreen.stepSound));
        }
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        for (Action a : turn.getActions()) {
            a.setActor(actor);
        }
    }

    public SequenceAction getSequence() {
        return turn;
    }
}
