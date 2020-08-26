package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.scenes.scene2d.Action;

public class ChangeState extends Action {

    private CharacterDrawable characterDrawable;
    private CharacterStateEnum targetState;

    public ChangeState(CharacterDrawable characterDrawable, CharacterStateEnum stateEnum) {
        this.characterDrawable = characterDrawable;
        this.targetState = stateEnum;
    }

    @Override
    public boolean act(float delta) {
        characterDrawable.setState(targetState);
        return true;
    }

}
