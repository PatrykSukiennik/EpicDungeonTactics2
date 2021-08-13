package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.screens.viewElements.game.StatBar;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import box2dLight.RayHandler;

public class AutonomousCharacter extends CharacterDrawable {

    private StatBar statBar;
    private int posionDuration = 0;
    private int burningDuration = 0;
    private int freezeDuration = 0;
    private int stunDuration = 0;
    private int posionDmg = 0;
    private int freezeDmg = 0;
    private int burningDmg = 0;
    private CoordsFloat spellEffectAnimationPosition;

    private static Animation<SpriteDrawable> burningAnimation;
    private static Animation<SpriteDrawable> freezeAnimation;
    private static Animation<SpriteDrawable> stunAnimation;
    private static Animation<SpriteDrawable> poisonAnimation;
    private static final float SPELL_EFFECT_ANIMATION_SIZE = WorldConfig.TILE_SIZE * 2f;

    static {
        burningAnimation = GraphicsManager.getSpellEffectAnimation(SpellEnum.FIRE_EXPLOSION);
        freezeAnimation = GraphicsManager.getSpellEffectAnimation(SpellEnum.ICE_EXPLOSION);
        poisonAnimation = GraphicsManager.getSpellEffectAnimation(SpellEnum.POISON_EXPLOSION);
        stunAnimation = GraphicsManager.getSpellEffectAnimation(SpellEnum.STUN_EXPLOSION);
    }

    public AutonomousCharacter(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world, Room room, MapTile tile, boolean isRotated, boolean isUnique) {
        super(characterEnum, position, rayHandler, world, room, tile, isRotated, isUnique);
        spellEffectAnimationPosition = new CoordsFloat(0, 0);

        if (!characterEnum.toString().startsWith("NPC")) statBar = new StatBar(this, isUnique);
    }

    @Override
    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(getCharacterEnum());
    }

    @Override
    public void draw(Batch mapBatch) {
        super.draw(mapBatch);

        if (burningDuration > 0 || freezeDuration > 0 || posionDuration > 0 || stunDuration > 0) {
            spellEffectAnimationPosition.x = (this.getX() + this.getWidth() / 2f) - SPELL_EFFECT_ANIMATION_SIZE / 2f;
            spellEffectAnimationPosition.y = (this.getY() + this.getHeight() / 4f) - SPELL_EFFECT_ANIMATION_SIZE / 2f;
        }

        if (burningDuration > 0) {
            burningAnimation.getKeyFrame(this.stateTime, true).draw(
                    mapBatch, spellEffectAnimationPosition.x, spellEffectAnimationPosition.y,
                    SPELL_EFFECT_ANIMATION_SIZE, SPELL_EFFECT_ANIMATION_SIZE
            );
        }
        if (freezeDuration > 0) {
            freezeAnimation.getKeyFrame(this.stateTime, true).draw(
                    mapBatch, spellEffectAnimationPosition.x, spellEffectAnimationPosition.y,
                    SPELL_EFFECT_ANIMATION_SIZE, SPELL_EFFECT_ANIMATION_SIZE
            );
        }
        if (posionDuration > 0) {
            poisonAnimation.getKeyFrame(this.stateTime, true).draw(
                    mapBatch, spellEffectAnimationPosition.x, spellEffectAnimationPosition.y,
                    SPELL_EFFECT_ANIMATION_SIZE, SPELL_EFFECT_ANIMATION_SIZE
            );
        }
        if (stunDuration > 0) {
            //System.out.println("STUUUUUUN");
            stunAnimation.getKeyFrame(this.stateTime, true).draw(
                    mapBatch, spellEffectAnimationPosition.x, spellEffectAnimationPosition.y,
                    SPELL_EFFECT_ANIMATION_SIZE, SPELL_EFFECT_ANIMATION_SIZE
            );
        }
    }

    @Override
    public void drawTop(Batch guiBatch) {
        super.drawTop(guiBatch);
        if (statBar != null) statBar.draw(guiBatch);
    }

    @Override
    public void dmgGet(int dmg) {
        stats.dmgGot(dmg);
        if (stats.getCurrHp() <= 0) {
            GameScreen.getInstance().enemyDied(this);
        }
    }

    public int getFreezeDuration() {
        return freezeDuration;
    }

    public int getBurningDuration() {
        return burningDuration;
    }

    public int getStunDuration() {
        return stunDuration;
    }

    public int getPosionDuration() {
        return posionDuration;
    }

    public void setPosion(int posionDuration) {
        this.posionDuration = posionDuration;
    }

    public void setFreeze(int freezeDuration) {
        this.freezeDuration = freezeDuration;
    }

    public void setBurning(int burningDuration) {
        this.burningDuration = burningDuration;
    }

    public void setStun(int stunDuration) {
        this.stunDuration = stunDuration;
    }

    public void setFreezeDmg(int freezeDmg) {
        this.freezeDmg = freezeDmg;
    }

    public void setBurningDmg(int burningDmg) {
        this.burningDmg = burningDmg;
    }

    public void setPosionDmg(int posionDmg) {
        this.posionDmg = posionDmg;
    }

    public int getFreezeDmg() {
        return freezeDmg;
    }

    public int getPoisonDmg() {
        return posionDmg;
    }

    public int getBurningDmg() {
        return burningDmg;
    }
}
