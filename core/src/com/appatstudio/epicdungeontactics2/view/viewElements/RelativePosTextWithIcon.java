package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;


public class RelativePosTextWithIcon {

    private SpriteDrawable icon;
    private BitmapFont font;
    private String text;
    private float iconSize, iconModifX, iconModifY, textModifX, textModifY, wholeWidth;

    public RelativePosTextWithIcon(SpriteDrawable icon, BitmapFont font, String text, int alignment) {
        this.icon = icon;
        this.font = font;
        this.text = text;

        iconSize = FontsManager.getTextHeight(font, "0") * 1.2f;
        wholeWidth = iconSize * 1.2f + FontsManager.getTextWidth(font, text);

        switch (alignment) {
            case Align.center: {
                iconModifX = -wholeWidth/2f;
                iconModifY = -iconSize/2f;
                textModifX = iconModifX + iconSize * 1.2f;
                textModifY = iconSize/2f;
                break;
            }
            case Align.left: {
                iconModifX = 0;
                iconModifY = -iconSize/2f;
                textModifX = iconSize * 1.2f;
                textModifY = iconSize/2f;
                break;
            }
        }
    }

    public void draw(Batch batch, float relX, float relY) {
        icon.draw(batch, relX + iconModifX, relY + iconModifY, iconSize, iconSize);
        font.draw(batch, text, relX + textModifX, relY + textModifY);
    }

    public boolean tap(float posX, float posY, float tapX, float tapY) {
        return tapX > posX + iconModifX &&
                tapX < posX + wholeWidth/2f &&
                tapY > posY - iconSize/2f &&
                tapY < posY + iconSize/2f;
    }

}
