package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.AbstractCharacter;
import com.badlogic.gdx.scenes.scene2d.Action;

public class SwitchMode extends Action {

    private AbstractCharacter character;
    private CharacterStateEnum state;

    public SwitchMode(AbstractCharacter character, CharacterStateEnum newState) {
        this.character = character;
        this.state = newState;
    }

    @Override
    public boolean act(float delta) {
        character.setState(state);
        return false;
    }
}
