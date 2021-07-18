package com.appatstudio.epicdungeontactics2.screens.viewElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GuiButton extends Image {

    private boolean isClicked = false;

    public GuiButton(SpriteDrawable drawable, float size, float posX, float posY) {
        super(drawable);
        this.setSize(size, size);
        this.setPosition(posX, posY);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setState(boolean state) {
        isClicked = state;

        if (isClicked) this.getColor().a = 0.3f;
        else this.getColor().a = 0.8f;

        this.act(Gdx.graphics.getDeltaTime());
    }

    public boolean isTap(float x, float y) {
        return  (x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight());
    }

    public void draw(Batch batch) {
        super.act(Gdx.graphics.getDeltaTime());
        super.draw(batch, 1f);
    }

    public void draw(Batch batch, float alpha) {
        super.getColor().a = alpha;
        super.act(Gdx.graphics.getDeltaTime());
        super.draw(batch, 1f);
        super.getColor().a = 1f;
        batch.end();
        batch.begin();
    }
}
