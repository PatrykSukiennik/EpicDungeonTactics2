package com.appatstudio.epicdungeontactics2.view;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import box2dLight.RayHandler;

public final class MenuBgContainer {

    private static SpriteDrawable bg1, bg2;
    private static float bg1x, bg2x, bgW;

    private static SpriteDrawable lightTexture;

    static {
        bg1 = GraphicsManager.getGuiElement(GuiElementEnum.MENU_BG1);
        bg2 = GraphicsManager.getGuiElement(GuiElementEnum.MENU_BG2);

        bgW = Gdx.graphics.getHeight();

        bg1x = 0;
        bg2x = bgW;

        Date date = new Date(TimeUtils.millis());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour < 6) {
            lightTexture = GraphicsManager.getGuiElement(GuiElementEnum.NIGHT_LIGHT_TEXTURE);
        }
        else if (hour < 21) {
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

}
