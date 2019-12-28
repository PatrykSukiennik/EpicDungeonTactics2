package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public final class ButtonWithText extends Image {

    private BitmapFont font;
    private String text;
    private float textX, textY;

    public ButtonWithText(SpriteDrawable bg, float x, float y, float width, float height, BitmapFont font, String text) {
        super(bg);
        setSize(width, height);
        setPosition(x, y);

        this.font = font;
        this.text = text;
        textX = x + width / 2f - FontsManager.getTextWidth(font, text) / 2f;
        textY = y + height / 2f + FontsManager.getTextHeight(font, text) * 0.7f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (getDrawable() != null) super.draw(batch, parentAlpha);
        font.draw(batch, text, textX, textY);
    }

    public boolean tap(float x, float y) {
        return x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight();
    }
}
