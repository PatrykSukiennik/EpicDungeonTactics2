package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class RelativePosText {

    private BitmapFont font;
    private String text;
    private float modifX, modifY;
    private int alignment;

    public RelativePosText(BitmapFont font, String text, int alignment) {
        this.font = font;
        this.text = text;
        this.alignment = alignment;

        switch (alignment) {
            case Align.center: {
                modifY = FontsManager.getTextHeight(font, text) / 2f;
                modifX = -FontsManager.getTextWidth(font, text) / 2f;
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
                modifX = -FontsManager.getTextWidth(font, text) / 2f;
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

    public void draw(Batch batch, float relX, float relY) {
        font.draw(batch, text, relX + modifX, relY + modifY);
    }

}
