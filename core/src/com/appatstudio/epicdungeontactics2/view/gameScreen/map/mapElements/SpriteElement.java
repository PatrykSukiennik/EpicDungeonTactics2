package com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.BodyConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightConfigObject;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoElementsLocations;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class SpriteElement {
    private SpriteDrawable sprite;
    private CoordsInt size;
    private boolean isActive =  true;
    private SpriteDrawable destroyedSprite;

    private PointLight pointLight;
    private CoordsFloat coords;
    private Body body;

    public SpriteElement(MapElementSpriteEnum spriteEnum, CoordsFloat coords, RayHandler rayHandler, World world) {
        this.sprite = GraphicsManager.getMapElementSprite(spriteEnum);
        this.size = WorldConfig.getMapElementSpriteSize(spriteEnum);
        this.destroyedSprite = GraphicsManager.getMapElementInactiveSprite(spriteEnum);

        this.coords = coords;

        LightConfigObject lightConfigObject = LightsConfig.getMapElementSpriteLights(spriteEnum);
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

        if (BodyConfig.getMapElementsSpriteBodyDef(spriteEnum) != null) {
            this.body = world.createBody(BodyConfig.getMapElementsSpriteBodyDef(spriteEnum));
            this.body.createFixture(BodyConfig.getMapElementsSpriteFixtureDef(spriteEnum));
            this.body.setTransform(coords.x + size.x / 2f, coords.y + size.y / 2f, 0);
        }
    }
    
    public void draw(Batch batch) {
        if (isActive) sprite.draw(batch, this.coords.x, this.coords.y, this.size.x, this.size.y);
        else destroyedSprite.draw(batch, this.coords.x, this.coords.y, this.size.x, this.size.y);
    }

    public void destroy() {
        isActive = false;
        pointLight.remove(true);
    }
}
