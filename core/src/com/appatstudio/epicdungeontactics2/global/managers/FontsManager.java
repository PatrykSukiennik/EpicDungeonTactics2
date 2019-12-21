package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public final class FontsManager {

    private static GlyphLayout glyphLayout;

    static {
        glyphLayout = new GlyphLayout();
    }

    public static void load(AssetManager assetManager) {

    }

    public static BitmapFont getFont(FontEnum fontEnum) {
        return null;
    }

    public static float getTextWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public static float getTextHeight(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.height;
    }

}
