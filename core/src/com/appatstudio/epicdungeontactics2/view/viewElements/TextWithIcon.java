package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class TextWithIcon {

    private SpriteDrawable icon;
    private BitmapFont font;
    private String text;
    private float iconSize, iconX, iconY, textX, textY, wholeWidth;
    private int alignment;
    private float posX, posY;

    public TextWithIcon(SpriteDrawable icon, BitmapFont font, String text, float posX, float posY, int alignment) {
        this.icon = icon;
        this.font = font;
        this.text = text;
        this.alignment = alignment;
        this.posX = posX;
        this.posX = posY;

        iconSize = FontsManager.getTextHeight(font, "0") * 1.2f;
        wholeWidth = iconSize * 1.2f + FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                iconX = posX - wholeWidth/2f;
                iconY = posY - FontsManager.getTextHeight(font, "0")/2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + FontsManager.getTextHeight(font, "0")/2f;

            }
            case Align.left: {
                iconX = posX;
                iconY = posY - FontsManager.getTextHeight(font, "0")/2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + FontsManager.getTextHeight(font, "0")/2f;
            }
        }
    }

    public void draw(Batch batch) {
        icon.draw(batch, iconX, iconY, iconSize, iconSize);
        font.draw(batch, text, textX, textY);
    }

    public boolean tap(float x, float y) {
        return x > iconX - iconSize * 0.4f &&
                x < iconX + wholeWidth + iconSize * 0.4f &&
                y > iconY - iconSize/2f - iconSize * 0.4f &&
                y < iconY + iconSize/2f + iconSize * 0.4f;
    }

    public void setText(String text) {
        wholeWidth = iconSize * 1.2f + FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                iconX = posX - wholeWidth/2f;
                iconY = posY - FontsManager.getTextHeight(font, "0")/2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + FontsManager.getTextHeight(font, "0")/2f;

            }
            case Align.left: {
                iconX = posX;
                iconY = posY - FontsManager.getTextHeight(font, "0")/2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + FontsManager.getTextHeight(font, "0")/2f;
            }
        }
    }

}
