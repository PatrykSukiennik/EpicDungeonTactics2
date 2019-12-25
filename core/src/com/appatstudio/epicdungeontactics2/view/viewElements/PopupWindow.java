package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class PopupWindow extends Image {

    private SpriteDrawable bg;
    private boolean isUp;

    public PopupWindow(SpriteDrawable bg, float x, float y, float width, float height) {
        super(bg);
        setWidth(width);
        setHeight(height);
        setPosition(x, y);
        isUp = false;
    }

    public void hide() {
        isUp = false;
    }

    public void show() {
        isUp = true;
    }

    public void draw(Batch batch) {
        super.act(Gdx.graphics.getDeltaTime());
        super.draw(batch, 1f);
    }

    public boolean tap(float x, float y) {
        return x > getX() && x < getX() + getWidth() &&
                y > getY() && y < getY() + getHeight();
    }

    public boolean isUp() {
        return isUp;
    }
}
