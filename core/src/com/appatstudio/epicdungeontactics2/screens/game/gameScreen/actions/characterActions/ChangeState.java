package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;

public class ChangeState extends AbstractCharacterAction {

    private CharacterDrawable characterDrawable;
    private CharacterStateEnum targetState;

    public ChangeState(CharacterDrawable characterDrawable, CharacterStateEnum stateEnum) {
        this.characterDrawable = characterDrawable;
        this.targetState = stateEnum;
    }

    @Override
    public boolean act(float delta) {
        characterDrawable.setState(targetState);

        if (targetState == CharacterStateEnum.RUN) SoundsManager.setCharacterMoving(true);
        else SoundsManager.setCharacterMoving(false);

        return true;
    }

}
