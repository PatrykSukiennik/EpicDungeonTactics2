package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public class AlphaTextObject extends TextObject {

    public AlphaTextObject(BitmapFont font, String text, float posX, float posY, int alignment) {
        super(font, text, posX, posY, alignment);
    }

    public void draw(Batch batch, float alpha) {
        batch.getColor().a = alpha;
        batch.begin();
        super.draw(batch);
        batch.getColor().a = 1f;
        batch.end();

        System.out.println("ALPHA:  " + alpha + "  " + posX + "  " + posY + "  " + text);
    }

    @Override
    public void setText(String text) {
        this.text = text;

        objectHeight = FontsManager.getTextHeight(font, "0") * 1.2f;
        wholeWidth = FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                textX = posX - wholeWidth / 2f;
                textY = posY + objectHeight / 2f;
                break;

            }
            case Align.left: {
                textX = posX;
                textY = posY + objectHeight / 2f;
                break;
            }
            case Align.right: {
                textX = posX - wholeWidth;
                textY = posY + objectHeight / 2f;
                break;
            }
        }
    }
}
