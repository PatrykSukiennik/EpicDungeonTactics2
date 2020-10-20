package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public class TextObject {

    protected BitmapFont font;
    protected String text;
    protected float objectHeight, textX, textY, wholeWidth;
    protected int alignment;
    protected float posX, posY;

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

    public void setPos(float x, float y) {
        this.posX = x;
        this.posY = y;

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
