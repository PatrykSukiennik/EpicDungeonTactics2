package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RelativePosText {

    BitmapFont font;
    String text;
    float modifX, modifY;

    public RelativePosText(BitmapFont font, String text) {
        this.font = font;
        this.text = text;
        modifY = FontsManager.getTextHeight(font, text)/2f;
        modifX = -FontsManager.getTextWidth(font, text)/2f;
    }

    public void setText(String text) {
        this.text = text;
        modifX = -FontsManager.getTextWidth(font, text)/2f;
    }

    public void draw(Batch batch, float centerX, float centerY) {
        font.draw(batch, text, centerX + modifX, centerY + modifY);
    }

    public String getText() {
        return text;
    }

}
