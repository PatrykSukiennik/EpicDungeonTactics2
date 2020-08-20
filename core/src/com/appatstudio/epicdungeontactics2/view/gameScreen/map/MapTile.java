package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.SpriteElement;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MapTile {

    private AnimatedElement animatedElement = null;
    private SpriteElement spriteElement = null;
    private CharacterDrawable character = null;
    private CoordsFloat position;

    public MapTile(int x, int y) {
        position = WorldConfig.getTileCoord(x, y);

    }

    public void draw(Batch batch) {
        if (animatedElement != null) animatedElement.draw(batch);
        if (spriteElement != null) spriteElement.draw(batch);
        if (character != null) character.draw(batch);
    }

    public void setAnimatedElement(AnimatedElement animatedElement) {
        this.animatedElement = animatedElement;
    }

    public void setSpriteElement(SpriteElement spriteElement) {
        this.spriteElement = spriteElement;
    }

    public void setCharacter(Hero heroInRoom) {
        character = heroInRoom;
    }

    public void destroyElements() {
        if (animatedElement != null) animatedElement.destroy();
        if (spriteElement != null) spriteElement.destroy();
    }

}
