package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Attack extends AbstractCharacterAction {

    SequenceAction actions;

    public Attack(CharacterDrawable attacker, CharacterDrawable target) {
        CoordsInt attackerCoords = attacker.getPosition();
        CoordsInt targetCoords = target.getPosition();

        if (attackerCoords.x < targetCoords.x) {
            actions.addAction(Actions.moveBy(WorldConfig.ROOM_WIDTH_RES/4f, 0, 0.1f));
            actions.addAction(Actions.moveBy(-WorldConfig.ROOM_WIDTH_RES/4f, 0, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage()));
        }
        else if (attackerCoords.x > targetCoords.x) {
            actions.addAction(Actions.moveBy(-WorldConfig.ROOM_WIDTH_RES/4f, 0, 0.1f));
            actions.addAction(Actions.moveBy(WorldConfig.ROOM_WIDTH_RES/4f, 0, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage()));
        }
        else if (attackerCoords.y > targetCoords.y) {
            actions.addAction(Actions.moveBy(0, -WorldConfig.ROOM_WIDTH_RES/4f, 0.1f));
            actions.addAction(Actions.moveBy(0, WorldConfig.ROOM_WIDTH_RES/4f, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage()));
        }
        else if (attackerCoords.y < targetCoords.y) {
            actions.addAction(Actions.moveBy(0, WorldConfig.ROOM_WIDTH_RES/4f, 0.1f));
            actions.addAction(Actions.moveBy(0, -WorldConfig.ROOM_WIDTH_RES/4f, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage()));
        }
    }

    @Override
    public boolean act(float delta) {
        return actions.act(Gdx.graphics.getDeltaTime());
    }
}
