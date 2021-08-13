package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import static com.appatstudio.epicdungeontactics2.global.WorldConfig.TILE_SIZE;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Shot extends SequenceAction {


    public Shot(CharacterDrawable attacker, CharacterDrawable target, Image projectile, SpellEnum effect, int dmg) {

        projectile.setSize(TILE_SIZE, TILE_SIZE);
        projectile.setOrigin(TILE_SIZE/2f, TILE_SIZE/2f);

        CoordsFloat start = new CoordsFloat(
                attacker.getTileStandingOn().getPositionFloat().x,
                attacker.getTileStandingOn().getPositionFloat().y
        );
        CoordsFloat end = new CoordsFloat(
                target.getTileStandingOn().getPositionFloat().x,
                target.getTileStandingOn().getPositionFloat().y
        );

        float projectileRotation = (float) Math.toDegrees(Math.atan2(
                end.y - start.y,
                end.x - start.x));
        projectileRotation += 220; //correction

        if (projectileRotation < 0) projectileRotation += 360;

        System.out.println("ROTATION: " + projectileRotation);
        System.out.println("SIZE: " + projectile.getWidth());


        this.addAction(Actions.moveTo(
                start.x,
                start.y));

        this.addAction(Actions.rotateTo(projectileRotation));

        this.addAction(
                Actions.moveTo(
                        end.x,
                        end.y,
                        WorldConfig.SHOT_DURATION
                ));

    }

}
