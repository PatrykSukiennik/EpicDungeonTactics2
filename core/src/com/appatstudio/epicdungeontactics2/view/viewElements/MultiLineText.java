package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public final class MultiLineText {

    private BitmapFont font;
    private String text;
    private float x, y, width;
    private int alignment;

    public MultiLineText(BitmapFont font, String text, float x, float maxWidth, float topY, int alignment) {
        this.font = font;
        this.text = text;
        this.width = maxWidth;
        this.alignment = alignment;
        this.x = alignment == Align.center ? x - maxWidth/2f : x;
        y = topY;
    }

    public void draw(Batch batch) {
        font.draw(batch, text, x, y, width, alignment, true);
    }

}
