package com.appatstudio.epicdungeontactics2.screens.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public class RelativePosMultilineText {

    private BitmapFont font;
    private String text;
    private float modifX, modifY;
    private float width;
    private int alignment;

    public RelativePosMultilineText(BitmapFont font, String text, int alignment, float width) {
        this.font = font;
        this.text = text;
        this.alignment = alignment;
        this.width = width;

        switch (alignment) {
            case Align.center: {
                modifY = -FontsManager.getTextHeightMultiline(font, text, width, alignment);
                modifX = -width / 2f;
                break;
            }
            case Align.left: {
                modifX = 0;
                modifY = FontsManager.getTextHeight(font, "0");
                break;
            }
            case Align.right: {
                modifX = -FontsManager.getTextWidth(font, text);
                modifY = FontsManager.getTextHeight(font, "0");
                break;
            }
        }
    }

    public void setText(String text) {
        this.text = text;

        switch (alignment) {
            case Align.center: {
                modifY = FontsManager.getTextHeight(font, text) / 2f;
                modifX = -width/2f;
                break;
            }
            case Align.left: {
                modifX = 0;
                modifY = FontsManager.getTextHeight(font, "0");
                break;
            }
            case Align.right: {
                modifX = -FontsManager.getTextWidth(font, text);
                modifY = FontsManager.getTextHeight(font, "0");
                break;
            }
        }
    }

    public void draw(Batch batch, float centerX, float topY) {
        font.draw(batch, text, centerX + modifX, topY + modifY, width, alignment, true);
    }
}
