package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.MagicalEffectAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightConfigObject;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.AutonomousCharacter;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import box2dLight.PointLight;

public class SpellExplosionAction extends Action {

    private static final float SPELL_SIZE = WorldConfig.TILE_SIZE * 3f;
    private Animation<SpriteDrawable> animation;
    private float duration = 0;
    private int effectDuration = 0;
    private float dmg = 0;
    private boolean wasEffectDone = false;
    private AutonomousCharacter target;
    private PointLight pointLight;
    private SpellEnum spell;

    public SpellExplosionAction(SpellEnum spell, AutonomousCharacter target, Room room, int dmg, int effectDuration) {

        MagicalEffectAnimationEnum effect = null;
        switch (spell) {
            case FIRE_EXPLOSION: effect = MagicalEffectAnimationEnum.FIRE_EXPLOSION; break;
            case ICE_EXPLOSION: effect = MagicalEffectAnimationEnum.ICE_EXPLOSION; break;
            case POISON_EXPLOSION: effect = MagicalEffectAnimationEnum.POISON_EXPLOSION; break;
            case STUN_EXPLOSION: effect = MagicalEffectAnimationEnum.MAGIC_EXPLOSION; break;
        }

        this.animation = GraphicsManager.getMagicalEffect(effect);
        this.duration = animation.getAnimationDuration();
        this.target = target;
        this.spell = spell;
        this.dmg = dmg;
        this.effectDuration = effectDuration;

        LightConfigObject lightConfigObject = LightsConfig.getMagicEffectLight(effect);
        this.pointLight = new PointLight(
                room.getRayHandler(),
                LightsConfig.CHARACTER_RAYS,
                lightConfigObject.getColor(),
                lightConfigObject.getRadius(),
                this.target.getTileStandingOn().getPositionFloat().x + WorldConfig.TILE_SIZE/2f,
                this.target.getTileStandingOn().getPositionFloat().y + WorldConfig.TILE_SIZE/2f
        );

        room.startExplosion(effect, target);
    }

    @Override
    public boolean act(float delta) {
        //System.out.println("EXPLOSION_DRAWING");
        this.duration -= Gdx.graphics.getDeltaTime();
        if (!wasEffectDone) {
            wasEffectDone = true;
            int dmg = 0;

            switch (spell) {
                case FIRE_EXPLOSION:
                    target.setBurning(effectDuration);
                    dmg = (int)(dmg * WorldConfig.BURN_DMG_FACTOR);
                    target.setBurningDmg(dmg > 0 ? dmg : 1);
                    if (effectDuration > 0) CommunicatePrinter.burning(effectDuration);
                    break;
                case ICE_EXPLOSION:
                    target.setFreeze(effectDuration);
                    dmg = (int)(dmg * WorldConfig.FREEZE_DMG_FACTOR);
                    target.setFreezeDmg(dmg > 0 ? dmg : 1);
                    if (effectDuration > 0) CommunicatePrinter.frozen(effectDuration);
                    break;
                case POISON_EXPLOSION:
                    target.setPosion(effectDuration);
                    dmg = ((int)(dmg * WorldConfig.POISON_DMG_FACTOR));
                    target.setPosionDmg(dmg > 0 ? dmg : 1);
                    if (effectDuration > 0) CommunicatePrinter.poison(effectDuration);
                    break;
                case STUN_EXPLOSION:
                    target.setStun(effectDuration);
                    if (effectDuration > 0) CommunicatePrinter.stun(effectDuration);
                    break;
            }

            return false;
        } else if (duration < 0) {
            this.pointLight.remove();
            return true;
        } else return false;
    }

}
