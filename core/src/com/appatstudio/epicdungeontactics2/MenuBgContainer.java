package com.appatstudio.epicdungeontactics2;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public final class MenuBgContainer {

    private static SpriteDrawable bg1, bg2;
    private static float bg1x, bg2x, bgW;

    static {
        bg1 = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_PIRATE);
        bg2 = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_NINJA);

        bgW = Gdx.graphics.getHeight() * 2;

        bg1x = 0;
        bg2x = bgW;

    }

    public static void draw(Batch batch) {
        float step = Gdx.graphics.getDeltaTime() * Gdx.graphics.getWidth() * 0.4f;
        bg1x -= step;
        bg2x -= step;

        if (bg1x < -bgW) {
            bg1x = bg2x + bgW;
        }
        else if (bg2x < -bgW) {
            bg2x = bg1x + bgW;
        }

        bg1.draw(batch, bg1x, 0, bgW, Gdx.graphics.getHeight());
        bg2.draw(batch, bg2x, 0, bgW, Gdx.graphics.getHeight());
    }

}
