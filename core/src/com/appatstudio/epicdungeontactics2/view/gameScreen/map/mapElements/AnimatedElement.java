package com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.BodyConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightConfigObject;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class AnimatedElement {

    private MapElementAnimationEnum animationEnum;
    private Animation<SpriteDrawable> animation;
    private float stateTime = EpicDungeonTactics.random.nextFloat();
    private CoordsInt size;
    private boolean isActive = true;
    private SpriteDrawable destroyedSprite;

    private PointLight pointLight;
    private CoordsFloat coords;
    private Body body;

    private boolean isWalkable = false;

    public AnimatedElement(MapElementAnimationEnum animationEnum, CoordsFloat coords, RayHandler rayHandler, World world) {
        animation = GraphicsManager.getMapElementAnimation(animationEnum);
        this.size = WorldConfig.getMapElementAnimationSize(animationEnum);
        //this.destroyedSprite = GraphicsManager.getMapElementInactiveSprite(animationEnum);

        this.animationEnum = animationEnum;
        this.coords = coords;

        LightConfigObject lightConfigObject = LightsConfig.getMapElementAnimationLights(animationEnum);
        if (lightConfigObject != null) {
            this.pointLight = new PointLight(
                    rayHandler,
                    LightsConfig.ELEMENT_RAYS,
                    lightConfigObject.getColor(),
                    lightConfigObject.getRadius(),
                    coords.x + lightConfigObject.getOffset().x,
                    coords.y + lightConfigObject.getOffset().y
            );
        }

        if (BodyConfig.getMapElementsAnimationBodyDef(animationEnum) != null) {
            this.body = world.createBody(BodyConfig.getMapElementsAnimationBodyDef(animationEnum));
            this.body.createFixture(BodyConfig.getMapElementsAnimationFixtureDef(animationEnum));
            this.body.setTransform(coords.x + size.x / 2f, coords.y + size.y / 2f, 0);
            if (pointLight != null) pointLight.attachToBody(this.body);
        }

    }

    public void draw(Batch batch) {
        if (isActive) {
            stateTime += Gdx.graphics.getDeltaTime();
            animation.getKeyFrame(stateTime).draw(batch, this.coords.x - this.size.x/2f, this.coords.y, this.size.x, this.size.y);
        }
        else destroyedSprite.draw(batch, this.coords.x - this.size.x/2f, this.coords.y, this.size.x, this.size.y);
    }

    public void destroy() {
        isActive = false;
        pointLight.remove(true);
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
