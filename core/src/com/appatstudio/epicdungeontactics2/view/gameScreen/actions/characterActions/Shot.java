package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class Shot extends SequenceAction {


    public Shot(CharacterDrawable attacker, CharacterDrawable target, Image projectile, SpellEnum effect, int dmg) {

        CoordsFloat start = new CoordsFloat(
                attacker.getX() + attacker.getWidth()/2f,
                attacker.getY() + attacker.getHeight()/2f
        );
        CoordsFloat end = new CoordsFloat(
                target.getX() + target.getWidth()/2f,
                target.getY() + target.getHeight()/2f
        );

        float projectileRotation = (float) Math.toDegrees(Math.atan2(
                end.y - start.y,
                end.x - start.x));
        projectileRotation += 270;

        if (projectileRotation < 0) projectileRotation += 360;

        System.out.println("ROTATION: " + projectileRotation);


        this.addAction(Actions.rotateTo(projectileRotation));

        this.addAction(Actions.moveTo(
                start.x,// - WorldConfig.TILE_SIZE/2f,
                start.y));// - WorldConfig.TILE_SIZE/2f));

        this.addAction(
                Actions.moveTo(
                        end.x,// - WorldConfig.TILE_SIZE/2f,
                        end.y,// - WorldConfig.TILE_SIZE/2f,
                        WorldConfig.SHOT_DURATION
                ));

    }

}
