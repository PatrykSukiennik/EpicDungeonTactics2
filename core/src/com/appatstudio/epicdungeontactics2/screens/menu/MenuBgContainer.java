package com.appatstudio.epicdungeontactics2.screens.menu;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public final class MenuBgContainer {

    private static SpriteDrawable bg1, bg2, alpha50;
    private static float bg1x, bg2x, bgW;

    private static SpriteDrawable lightTexture;

    static {
        alpha50 = GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent);

        bg1 = GraphicsManager.getGuiElement(GuiElementEnum.MENU_BG1);
        bg2 = GraphicsManager.getGuiElement(GuiElementEnum.MENU_BG2);

        bgW = Gdx.graphics.getHeight();

        bg1x = 0;
        bg2x = bgW;

        if (EpicDungeonTactics.isDay()) {
            lightTexture = GraphicsManager.getGuiElement(GuiElementEnum.DAY_LIGHT_TEXTURE);
        }
        else {
            lightTexture = GraphicsManager.getGuiElement(GuiElementEnum.NIGHT_LIGHT_TEXTURE);
        }
    }

    public static void draw(Batch batch) {
        batch.begin();
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

        lightTexture.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    public static void drawOnlyBg(Batch batch) {
        batch.begin();
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
        batch.end();
    }

    public static void drawOnlyLights(Batch batch) {
        lightTexture.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public static void drawAlpha50(Batch batch) {
        batch.begin();
        alpha50.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

}
