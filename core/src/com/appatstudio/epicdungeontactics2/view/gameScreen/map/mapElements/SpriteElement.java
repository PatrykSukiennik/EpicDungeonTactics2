package com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.MapInfoElementsLocations;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class SpriteElement {
    private SpriteDrawable sprite;
    private float spriteHeight;

    public SpriteElement(MapElementSpriteEnum spriteEnum) {
        this.sprite = GraphicsManager.getMapElementSprite(spriteEnum);
        this.spriteHeight = 16;
    }

    public void draw(Batch batch, float x, float y) {
        sprite.draw(batch, x, y, WorldConfig.TILE_SIZE, spriteHeight);
    }
}
