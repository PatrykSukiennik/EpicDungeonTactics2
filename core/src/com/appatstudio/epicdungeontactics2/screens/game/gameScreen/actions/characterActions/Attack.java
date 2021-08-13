package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.SoundPlayAction;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.MapTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class Attack extends AbstractCharacterAction {

    SequenceAction actions;

    public Attack(CharacterDrawable attacker, CharacterDrawable target, MapTile tileAttackIsFrom) {
        CoordsInt attackerCoords = attacker.getPosition();
        CoordsInt targetCoords = target.getPosition();
        actions = new SequenceAction();

        if (tileAttackIsFrom.getPositionInt().x < targetCoords.x) {
            actions.addAction(new SetRotationX(attacker, false));
            actions.addAction(Actions.moveBy(WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new SoundPlayAction(SoundEnum.FIST_PUNCH));
            actions.addAction(Actions.moveBy(-WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
        else if (tileAttackIsFrom.getPositionInt().x > targetCoords.x) {
            actions.addAction(new SetRotationX(attacker, true));
            actions.addAction(Actions.moveBy(-WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new SoundPlayAction(SoundEnum.FIST_PUNCH));
            actions.addAction(Actions.moveBy(WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
        else if (tileAttackIsFrom.getPositionInt().y > targetCoords.y) {
            actions.addAction(Actions.moveBy(0, -WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new SoundPlayAction(SoundEnum.FIST_PUNCH));
            actions.addAction(Actions.moveBy(0, WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
        else if (tileAttackIsFrom.getPositionInt().y < targetCoords.y) {
            actions.addAction(Actions.moveBy(0, WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new SoundPlayAction(SoundEnum.FIST_PUNCH));
            actions.addAction(Actions.moveBy(0, -WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
    }

    public Attack(CharacterDrawable attacker, CharacterDrawable target, MapTile tileAttackIsFrom, SoundEnum attackSound) {
        CoordsInt attackerCoords = attacker.getPosition();
        CoordsInt targetCoords = target.getPosition();
        actions = new SequenceAction();

        if (tileAttackIsFrom.getPositionInt().x < targetCoords.x) {
            actions.addAction(new SetRotationX(attacker, false));
            actions.addAction(Actions.moveBy(WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new SoundPlayAction(attackSound));
            actions.addAction(Actions.moveBy(-WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
        else if (tileAttackIsFrom.getPositionInt().x > targetCoords.x) {
            actions.addAction(new SetRotationX(attacker, true));
            actions.addAction(Actions.moveBy(-WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new SoundPlayAction(attackSound));
            actions.addAction(Actions.moveBy(WorldConfig.TILE_SIZE/3f, 0, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
        else if (tileAttackIsFrom.getPositionInt().y > targetCoords.y) {
            actions.addAction(Actions.moveBy(0, -WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new SoundPlayAction(attackSound));
            actions.addAction(Actions.moveBy(0, WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
        else if (tileAttackIsFrom.getPositionInt().y < targetCoords.y) {
            actions.addAction(Actions.moveBy(0, WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new SoundPlayAction(attackSound));
            actions.addAction(Actions.moveBy(0, -WorldConfig.TILE_SIZE/3f, 0.1f));
            actions.addAction(new DamageGiving(target, attacker.getMeleDamage(), attacker));
        }
    }

    @Override
    public boolean act(float delta) {
        return actions.act(Gdx.graphics.getDeltaTime());
    }

    public Array<Action> getSequence() {
        return actions.getActions();
    }
}