package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class MultiLineText {

    private BitmapFont font;
    private String text;
    private float x, y, width;
    private int alignment;

    public MultiLineText(BitmapFont font, String text, float centerX, float maxWidth, float topY, int alignment) {
        this.font = font;
        this.text = text;
        x = centerX - maxWidth/2f;
        y = topY;
        this.width = maxWidth;
        this.alignment = alignment;
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, text, x, y, width, alignment, true);
    }

}
