package com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class AnimatedElement {

    private Animation<SpriteDrawable> animation;
    private float stateTime = EpicDungeonTactics.random.nextFloat();
    private float animationHeight;

    public AnimatedElement(MapElementAnimationEnum animationEnum) {
        animation = GraphicsManager.getMapElementAnimation(animationEnum);
        animationHeight = 16;
    }

    public void draw(Batch batch, float x, float y) {
        stateTime += Gdx.graphics.getDeltaTime();
        animation.getKeyFrame(stateTime).draw(batch, x, y, WorldConfig.TILE_SIZE, animationHeight);
    }

}
