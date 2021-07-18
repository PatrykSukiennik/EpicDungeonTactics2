package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.badlogic.gdx.scenes.scene2d.Action;

public class SetRotationX extends Action {

    CharacterDrawable character;
    boolean targetRotation;

    public SetRotationX(CharacterDrawable character, boolean b) {
          this.character = character;
          this.targetRotation = b;
    }

    @Override
    public boolean act(float delta) {
        character.setRotationX(targetRotation);
        return true;
    }
}
