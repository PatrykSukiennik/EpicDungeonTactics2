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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class Shot extends AbstractCharacterAction {

    Image projectile;
    float projectileStateTime = 0;
    float durationShot = 1f;
    Image animation = null;
    float animationStateTime = 0;
    float durationEffectAnimation = 0f;
    DamageGiving dmgAction;

    public Shot(CharacterDrawable attacker, CharacterDrawable target, SpriteDrawable projectile, SpellEnum effect, int dmg) {
        CoordsFloat start = new CoordsFloat(
                attacker.getX() + attacker.getWidth()/2f,
                attacker.getY() + attacker.getHeight()/2f
        );
        CoordsFloat end = new CoordsFloat(
                target.getX() + target.getWidth()/2f,
                target.getY() + target.getHeight()/2f
        );

        this.projectile = new Image(projectile);
        this.projectile.setPosition(
                start.x - WorldConfig.TILE_SIZE/2f,
                start.y - WorldConfig.TILE_SIZE/2f);
        this.projectile.setSize(
                WorldConfig.TILE_SIZE,
                WorldConfig.TILE_SIZE);
        this.projectile.addAction(
                Actions.moveTo(
                        end.x - WorldConfig.TILE_SIZE/2f,
                        end.y - WorldConfig.TILE_SIZE/2f,
                        durationShot
                ));

        if (effect != null) {
            this.animation = new Image();
            this.animation.setPosition(end.x - WorldConfig.TILE_SIZE,
                    end.y - WorldConfig.TILE_SIZE);
            this.animation.setSize(
                    WorldConfig.TILE_SIZE * 2,
                    WorldConfig.TILE_SIZE * 2);
            durationEffectAnimation = 2f;
        }

        dmgAction = new DamageGiving(target, attacker.getShotDamage());
    }

    @Override
    public boolean act(float delta) {
        if (durationShot > 0) {
            durationShot -= Gdx.graphics.getDeltaTime();
            projectileStateTime += Gdx.graphics.getDeltaTime();
            projectile.act(Gdx.graphics.getDeltaTime());
            return false;
        }
        else if (durationEffectAnimation > 0) {
            durationEffectAnimation -= Gdx.graphics.getDeltaTime();
            animationStateTime += Gdx.graphics.getDeltaTime();
            animation.act(Gdx.graphics.getDeltaTime());
            return false;
        }

        else {
            return dmgAction.act(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    void draw(Batch batch) {
        if (durationShot > 0) {
            projectile.draw(batch, 1f);
        }
        else if (durationEffectAnimation > 0) {
            animation.draw(batch, 1f);
        }
    }
}
