package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.communicatePrinter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class CommunicateObject {

    private static final float TEXT_X = Gdx.graphics.getWidth() * 0.05f;
    private BitmapFont font;
    private String text;

    CommunicateObject(BitmapFont font, String text) {
        this.font = font;
        this.text = text;
    }

    void draw(Batch batch, float y) {
        font.draw(batch, text, TEXT_X, y);
    }

}
