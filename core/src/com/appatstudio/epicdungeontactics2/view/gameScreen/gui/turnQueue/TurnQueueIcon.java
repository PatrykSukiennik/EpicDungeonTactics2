package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.ChangeAlphaAction;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import static com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue.TurnQueue.COORDS;
import static com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue.TurnQueue.ICON_ALPHA;
import static com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue.TurnQueue.QUEUE_ICON_SIZE;
import static com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue.TurnQueue.QUEUE_MOVE_DURATION;
import static com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue.TurnQueue.QUEUE_SIZE;

public class TurnQueueIcon extends Image {

    private CharacterDrawable representedCharacter;
    private SpriteDrawable topSprite;
    private int extraMoves;
    private boolean isDestroyed;
    private int index;

    private float topX = Gdx.graphics.getWidth() - QUEUE_ICON_SIZE + QUEUE_ICON_SIZE * 0.15f;
    private float topSize = QUEUE_ICON_SIZE * 0.7f;

    public TurnQueueIcon(int index, CoordsFloat coords) {
        super(GraphicsManager.getGuiElement(GuiElementEnum.QUEUE_BG_HERO));

        this.isDestroyed = false;
        this.setPosition(coords.x, coords.y);
        this.setSize(QUEUE_ICON_SIZE, QUEUE_ICON_SIZE);
        this.getColor().a = TurnQueue.ICON_ALPHA[index];
        this.index = index;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        topSprite.draw(batch, topX, this.getY() + this.getHeight()/3f, topSize, topSize);
        topSprite.draw(batch, topX, this.getY() + this.getHeight()/3f, topSize, topSize);
        topSprite.draw(batch, topX, this.getY() + this.getHeight()/3f, topSize, topSize);
    }

    public CharacterDrawable getRepresentedCharacter() {
        return representedCharacter;
    }

    public int getExtraMoves() {
        return extraMoves;
    }

    public void setExtraMoves(int extraMoves) {
        this.extraMoves = extraMoves;
    }

    public void destroyed() {
        isDestroyed = true;

    }

    public void tick() {
        index--;
        if (index < 0) index = QUEUE_SIZE - 1;

        if (index == 0) {
            this.addAction(Actions.parallel(
                    Actions.moveTo(COORDS[index].x, COORDS[index].y, QUEUE_MOVE_DURATION),
                    Actions.fadeOut(QUEUE_MOVE_DURATION)
            ));
        } else if (index == QUEUE_SIZE - 1) {
            this.addAction(Actions.parallel(
                    Actions.alpha(ICON_ALPHA[index], QUEUE_MOVE_DURATION),
                    Actions.moveTo(COORDS[index].x, COORDS[index].y)
            ));
        } else {
            this.addAction(Actions.parallel(
                    Actions.moveTo(COORDS[index].x, COORDS[index].y, QUEUE_MOVE_DURATION),
                    Actions.alpha(ICON_ALPHA[index], QUEUE_MOVE_DURATION)

            ));
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRepresentedCharacter(CharacterDrawable character) {
        this.representedCharacter = character;

        if (representedCharacter.getCharacterEnum().toString().startsWith("BOSS")) {
            this.setDrawable(GraphicsManager.getGuiElement(GuiElementEnum.QUEUE_BG_BOSS));
        }
        else if (representedCharacter.getCharacterEnum().toString().startsWith("HERO")) {
            this.setDrawable(GraphicsManager.getGuiElement(GuiElementEnum.QUEUE_BG_HERO));
        }
        else if (representedCharacter.getCharacterEnum().toString().startsWith("PET")) {
            this.setDrawable(GraphicsManager.getGuiElement(GuiElementEnum.QUEUE_BG_PET));
        }
        else this.setDrawable(GraphicsManager.getGuiElement(GuiElementEnum.NONE));

        topSprite = GraphicsManager.getCharactersAnimation(
                character.getCharacterEnum(), CharacterStateEnum.IDLE).getKeyFrame(0);
    }
}
