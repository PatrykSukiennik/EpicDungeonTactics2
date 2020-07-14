package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class HeroSegment extends AbstractSegment {

    private AbstractItem selectedItem;
    private static float posY;
    private float stateTime = 0;

    private static float heroSize = fullHeight * 0.8f;
    private static float heroX = getPosX() + heroSize * 0.1f;
    private static float heroY = posY + heroSize * 0.1f;

    private Animation<SpriteDrawable> heroAnimation;

    HeroSegment(CharacterEnum hero) {
        posY = Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight()/2f + AbstractSegment.fullHeight * 1.1f;
        bg = GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_HERO);
    }

    void draw(Batch batch) {
        bg.draw(batch, posX, posY, fullWidth, fullHeight);

        stateTime += Gdx.graphics.getDeltaTime();
        heroAnimation.getKeyFrame(stateTime).draw(batch, heroX, heroY, heroSize, heroSize);

    }

    AbstractItem tap(float x, float y) {
        return null;
//        return x > posX && x < posX + fullWidth
//                && y > posY && posY < posY + fullHeight;
    }

    void selectItem(AbstractItem item) {
        this.selectedItem = item;
    }

    AbstractItem getTapItem(float x, float y) {
        return null;
    }

    boolean tapWithItem(float x, float y, AbstractItem item) {
        return false;
//        return x > posX && x < posX + fullWidth
//                && y > posY && posY < posY + fullHeight;
    }

    public boolean isTap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && posY < posY + fullHeight;
    }


}
