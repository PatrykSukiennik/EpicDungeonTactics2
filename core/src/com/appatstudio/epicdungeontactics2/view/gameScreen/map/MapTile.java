package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.AnimatedElement;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.ItemOnMapDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.mapElements.SpriteElement;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class MapTile {

    private static final HashMap<MapPathFindingFlags, SpriteDrawable> pathfindingFlagSprites;

    private AnimatedElement animatedElement = null;
    private SpriteElement spriteElement = null;
    private CharacterDrawable character = null;
    private boolean shouldDrawCharacter = false;
    private ItemOnMapDrawable items = null;
    private boolean isWalkable;
    private boolean isNode;
    private boolean isGoingDown;

    private CoordsFloat positionFloat;
    private CoordsInt positionInt;

    private CharacterDrawable characterStandingOn = null; //one character can stand on few tiles,
                                                            // thats place for marking that

    private MapPathFindingFlags flag = MapPathFindingFlags.NONE;
    private int pathFindingIndex = 0;

    static {
        pathfindingFlagSprites = new HashMap<>();
        pathfindingFlagSprites.put(MapPathFindingFlags.ITEM_MOVABLE, GraphicsManager.getMapElementSprite(MapElementSpriteEnum.TILE_YELLOW));
        pathfindingFlagSprites.put(MapPathFindingFlags.ATTACKABLE, GraphicsManager.getMapElementSprite(MapElementSpriteEnum.TILE_RED));
        pathfindingFlagSprites.put(MapPathFindingFlags.MOVABLE, GraphicsManager.getMapElementSprite(MapElementSpriteEnum.TILE_GREEN));
        pathfindingFlagSprites.put(MapPathFindingFlags.NEW_STAGE, GraphicsManager.getMapElementSprite(MapElementSpriteEnum.TILE_GREEN));
        pathfindingFlagSprites.put(MapPathFindingFlags.ROOM_NODE, GraphicsManager.getMapElementSprite(MapElementSpriteEnum.TILE_GREEN));
        pathfindingFlagSprites.put(MapPathFindingFlags.NONE, GraphicsManager.getMapElementSprite(MapElementSpriteEnum.TILE_GREEN));
    }

    public MapTile(int x, int y, boolean isWalkable) {
        this.positionFloat = WorldConfig.getTileCoord(x, y);
        this.positionInt = new CoordsInt(x, y);
        this.isWalkable = isWalkable;
        this.isNode = false;
        this.isGoingDown = false;
    }

    public void draw(Batch mapBatch, boolean isFight) {
        if (animatedElement != null) animatedElement.draw(mapBatch);
        if (spriteElement != null) spriteElement.draw(mapBatch);
        if (character != null && shouldDrawCharacter) character.draw(mapBatch);
        if (items != null) items.draw(mapBatch);
    }

    public void setAnimatedElement(AnimatedElement animatedElement) {
        this.animatedElement = animatedElement;
    }

    public void setSpriteElement(SpriteElement spriteElement) {
        this.spriteElement = spriteElement;
    }

    public void setCharacter(CharacterDrawable character, boolean shouldDrawCharacter) {
        this.character = character;
        this.shouldDrawCharacter = shouldDrawCharacter;
        this.characterStandingOn = character;
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
            if (items != null && items.getItems().size > 0) this.flag = MapPathFindingFlags.ITEM_MOVABLE;
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
        if (items != null) items.drawTop(guiBatch);
    }

    public void dropItem(AbstractItem item) {
        if (items == null) items = new ItemOnMapDrawable(positionInt);
        items.addItem(item);
    }

    public Array<AbstractItem> getItemsToPick() {
        return items == null ? null : items.getItems();
    }

    public void removeItem(AbstractItem item) {
        if (items != null) items.removeItem(item);
    }

    public CoordsFloat getPositionCharacterDrawingFloat() {
        return WorldConfig.getDrawingCoord(positionInt.x, positionInt.y);
    }

    public void setIsNode(boolean node) {
        isNode = node;
    }

    public void setIsGoingDown(boolean goingDown) {
        isGoingDown = goingDown;
    }

    public boolean isNode() {
        return isNode;
    }

    public boolean isGoingDown() {
        return isGoingDown;
    }

    public void setCharacterStandingOn(CharacterDrawable characterDrawable) {
        characterStandingOn = characterDrawable;
    }

    public CharacterDrawable getCharacterStandingOn() {
        return characterStandingOn;
    }

    public void drawWalkable() {

    }

    public void drawAttackable() {

    }


    public void drawFlag(MapPathFindingFlags flag, Batch batch) {
        switch (flag) {
            case MOVABLE: {
                pathfindingFlagSprites.get(MapPathFindingFlags.MOVABLE).draw(
                        batch,
                        this.positionFloat.x, this.positionFloat.y,
                        WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);
                break;
            }
            case ATTACKABLE: {
                pathfindingFlagSprites.get(MapPathFindingFlags.ATTACKABLE).draw(
                        batch,
                        this.positionFloat.x, this.positionFloat.y,
                        WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);
                break;
            }
            case ROOM_NODE:
            case NEW_STAGE: {
                pathfindingFlagSprites.get(MapPathFindingFlags.ROOM_NODE).draw(
                        batch,
                        this.positionFloat.x, this.positionFloat.y,
                        WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);
                break;
            }
        }
    }
}
