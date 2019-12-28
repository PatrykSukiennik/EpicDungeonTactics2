package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public final class TextObject {

    private BitmapFont font;
    private String text;
    private float objectHeight, textX, textY, wholeWidth;
    private int alignment;
    private float posX, posY;

    public TextObject(BitmapFont font, String text, float posX, float posY, int alignment) {
        this.font = font;
        this.text = text;
        this.alignment = alignment;
        this.posX = posX;
        this.posY = posY;

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

    public void draw(Batch batch) {
        font.draw(batch, text, textX, textY);
    }

    public void setText(String text) {
        wholeWidth = objectHeight * 1.2f + FontsManager.getTextWidth(font, text);

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
