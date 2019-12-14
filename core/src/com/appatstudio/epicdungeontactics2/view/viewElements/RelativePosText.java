package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RelativePosText {

    BitmapFont font;
    String text;
    float boxWidth, boxHeight;
    float modifX, modifY;

    public RelativePosText(BitmapFont font, String text, float width, float height) {
        this.font = font;
        this.text = text;
        this.boxWidth = width;
        this.boxHeight = height * 1.5f;
        modifY = boxHeight/2f + FontsManager.getTextHeight(font, text)/2f;
        modifX = boxWidth/2f - FontsManager.getTextWidth(font, text)/2f;
    }

    public void setText(String text) {
        this.text = text;
        modifX = boxWidth/2f - FontsManager.getTextWidth(font, text)/2f;
    }

    public void draw(SpriteBatch batch, float x, float y) {
        font.draw(batch, text, x + modifX, y + modifY);
    }

    public String getText() {
        return text;
    }

}
