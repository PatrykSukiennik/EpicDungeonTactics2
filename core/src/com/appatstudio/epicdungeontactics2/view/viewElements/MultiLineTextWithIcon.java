package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class MultiLineTextWithIcon {

    private SpriteDrawable icon;
    private BitmapFont font;
    private String text;
    private float iconSize, iconX, iconY, textX, topY, maxWidth;

    public MultiLineTextWithIcon(SpriteDrawable icon, BitmapFont font, String text, float posX, float maxWidth, float topY) {
        this.icon = icon;
        this.font = font;
        this.text = text;
        this.topY = topY;
        this.maxWidth = maxWidth;

        iconSize = FontsManager.getTextHeight(font, "0") * 1.2f;

        textX = posX + iconSize * 1.2f;

        iconY = topY - iconSize;
        iconX = posX;
    }

    public void draw(Batch batch) {
        if (icon != null) icon.draw(batch, iconX, iconY, iconSize, iconSize);
        font.draw(batch, text, textX, topY, maxWidth, Align.left, true);
    }

    public void setIconAndText(SpriteDrawable icon, String text) {
        this.icon = icon;
        this.text = text;
    }
}
