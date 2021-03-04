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
        this.posY = posY;

        iconSize = FontsManager.getTextHeight(font, "0") * 1.2f;
        wholeWidth = iconSize * 1.2f + FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                iconX = posX - wholeWidth / 2f;
                iconY = posY - iconSize / 2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + iconSize / 2f;
                break;

            }
            case Align.left: {
                iconX = posX;
                iconY = posY - iconSize / 2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + iconSize / 2f;
                break;
            }
            case Align.right: {
                iconX = posX - iconSize;
                iconY = posY - iconSize / 2f;
                textX = posX - wholeWidth;
                textY = posY + iconSize / 2f;
                break;
            }
            case Align.topRight: {
                iconX = posX + wholeWidth / 2f - iconSize;
                iconY = posY - iconSize / 2f;
                textX = posX - wholeWidth / 2f;
                textY = posY + iconSize / 2f;
            }
        }
    }

    public void draw(Batch batch) {
        if (icon != null) icon.draw(batch, iconX, iconY, iconSize, iconSize);
        font.draw(batch, text, textX, textY);
    }

    public boolean tap(float x, float y) {
        return alignment == Align.right ?
                x > iconX + iconSize - wholeWidth - iconSize * 0.4f &&
                        x < iconX + iconSize * 1.4f &&
                        y > iconY - iconSize / 2f - iconSize * 0.4f &&
                        y < iconY + iconSize / 2f + iconSize * 0.4f
                :
                x > iconX - iconSize * 0.4f &&
                        x < iconX + wholeWidth + iconSize * 0.4f &&
                        y > iconY - iconSize / 2f - iconSize * 0.4f &&
                        y < iconY + iconSize / 2f + iconSize * 0.4f;
    }

    public void setText(String text) {
        this.text = text;

        wholeWidth = iconSize * 1.2f + FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                iconX = posX - wholeWidth / 2f;
                iconY = posY - iconSize / 2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + iconSize / 2f;
                break;

            }
            case Align.left: {
                iconX = posX;
                iconY = posY - iconSize / 2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + iconSize / 2f;
                break;
            }
            case Align.right: {
                iconX = posX - iconSize;
                iconY = posY - iconSize / 2f;
                textX = posX - wholeWidth;
                textY = posY + iconSize / 2f;
                break;
            }
            case Align.topRight: {
                iconX = posX + wholeWidth / 2f - iconSize;
                iconY = posY - iconSize / 2f;
                textX = posX - wholeWidth / 2f;
                textY = posY + iconSize / 2f;
            }
        }

    }

    public float getIconY() {
        return iconY;
    }

    public float getIconHeight() {
        return iconSize;
    }

    public void setFont(BitmapFont newFont) {
        this.font = newFont;
    }

    public void setIconAndFont(SpriteDrawable icon, String text) {
        this.icon = icon;
        setText(text);
        wholeWidth = iconSize * 1.2f + FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                iconX = posX - wholeWidth / 2f;
                iconY = posY - iconSize / 2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + iconSize / 2f;
                break;

            }
            case Align.left: {
                iconX = posX;
                iconY = posY - iconSize / 2f;
                textX = iconX + iconSize * 1.2f;
                textY = posY + iconSize / 2f;
                break;
            }
            case Align.right: {
                iconX = posX - iconSize;
                iconY = posY - iconSize / 2f;
                textX = posX - wholeWidth;
                textY = posY + iconSize / 2f;
                break;
            }
            case Align.topRight: {
                iconX = posX + wholeWidth / 2f - iconSize;
                iconY = posY - iconSize / 2f;
                textX = posX - wholeWidth / 2f;
                textY = posY + iconSize / 2f;
            }
        }
    }
}
