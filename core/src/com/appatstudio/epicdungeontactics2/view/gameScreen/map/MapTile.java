package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.ItemOnMapDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.SpriteElement;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MapTile {

    private AnimatedElement animatedElement = null;
    private SpriteElement spriteElement = null;
    private CharacterDrawable character = null;
    private ItemOnMapDrawable item = null;
    private boolean isWalkable;

    private CoordsFloat positionFloat;
    private CoordsInt positionInt;

    private MapPathFindingFlags flag = MapPathFindingFlags.NONE;
    private int pathFindingIndex = 0;

    public MapTile(int x, int y, boolean isWalkable) {
        this.positionFloat = WorldConfig.getTileCoord(x, y);
        this.positionInt = new CoordsInt(x, y);
        this.isWalkable = isWalkable;
    }

    public void draw(Batch mapBatch) {
        if (animatedElement != null) animatedElement.draw(mapBatch);
        if (spriteElement != null) spriteElement.draw(mapBatch);
        if (character != null) character.draw(mapBatch);
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
        if (isWalkable) {
            if (animatedElement != null) {
                return animatedElement.isWalkable();
            }
            if (spriteElement != null) {
                return spriteElement.isWalkable();
            }
            return character == null;
        }

        return false;
    }

    public MapPathFindingFlags getFlag() {
        return flag;
    }

    public int getPathFindingIndex() {
        return pathFindingIndex;
    }

    public void setFlag(MapPathFindingFlags flag, int pathFindingIndex) {
        if (flag == MapPathFindingFlags.MOVABLE) {
            if (item != null) this.flag = MapPathFindingFlags.ITEM_MOVABLE;
            else this.flag = MapPathFindingFlags.MOVABLE;

            this.pathFindingIndex = pathFindingIndex;
        }
        else {
            this.flag = flag;
            this.pathFindingIndex = pathFindingIndex;
        }
    }

    public CoordsFloat getPositionFloat() {
        return positionFloat;
    }

    public CoordsInt getPositionInt() {
        return positionInt;
    }

    public void drawTop(Batch guiBatch) {
        if (character != null) {
            character.drawTop(guiBatch);
        }
    }
}
