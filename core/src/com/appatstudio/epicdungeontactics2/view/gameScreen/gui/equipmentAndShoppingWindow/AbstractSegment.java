package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class AbstractSegment {

    protected static final float fullWidth = Gdx.graphics.getWidth() * 0.7f;
    protected static final float fullHeight = fullWidth * 0.4f;

    protected static final float posX = Gdx.graphics.getWidth()/2f - fullWidth/2f;

    protected SpriteDrawable bg;

    void draw(Batch batch) {

    }

    public static float getFullHeight() {
        return fullHeight;
    }

    public static float getFullWidth() {
        return fullWidth;
    }

    public static float getPosX() {
        return posX;
    }

}
