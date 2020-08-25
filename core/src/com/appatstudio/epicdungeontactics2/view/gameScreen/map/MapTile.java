package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
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
    private boolean isWalkable;
    private int pathFindingFlag = -1; //index of correct path

    private CoordsFloat positionFloat;
    private CoordsInt positionInt;

    public MapTile(int x, int y, boolean isWalkable) {
        this.positionFloat = WorldConfig.getTileCoord(x, y);
        this.positionInt = new CoordsInt(x, y);
        this.isWalkable = isWalkable;
    }

    public void draw(Batch batch) {
        if (animatedElement != null) animatedElement.draw(batch);
        if (spriteElement != null) spriteElement.draw(batch);
        if (character != null) {
            System.out.println("drawing: " + positionInt.x + " " + positionInt.y);
            character.draw(batch);
        }
    }

    public void setAnimatedElement(AnimatedElement animatedElement) {
        this.animatedElement = animatedElement;
    }

    public void setSpriteElement(SpriteElement spriteElement) {
        this.spriteElement = spriteElement;
    }

    public void setCharacter(CharacterDrawable character) {
        this.character = character;
    }

    public void destroyElements() {
        if (animatedElement != null) animatedElement.destroy();
        if (spriteElement != null) spriteElement.destroy();
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setPathFindingFlag(int pathFindingFlag) {
        this.pathFindingFlag = pathFindingFlag;
    }

    public int getPathFindingFlag() {
        return pathFindingFlag;
    }

    public CoordsFloat getPositionFloat() {
        return positionFloat;
    }

    public CoordsInt getPositionInt() {
        return positionInt;
    }
}
