package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.communicatePrinter;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.GuiContainer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class CommunicateObject {

    private BitmapFont font;
    private String text;

    CommunicateObject(BitmapFont font, String text) {
        this.font = font;
        this.text = text;
    }

    void draw(Batch batch, float y) {
        font.draw(batch, text, GuiContainer.guiMargin, y);
    }

}
