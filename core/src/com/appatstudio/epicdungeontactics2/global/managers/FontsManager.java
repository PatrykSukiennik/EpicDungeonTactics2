package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.util.HashMap;
import java.util.Map;

public final class FontsManager {

    private static Map<FontEnum, BitmapFont> fontMap;
    private static GlyphLayout glyphLayout;

    static {
        glyphLayout = new GlyphLayout();
    }

    public static void load(AssetManager assetManager) {
        fontMap = new HashMap<>();

        FontEnum[] allFonts = FontEnum.values();
        for (FontEnum f : allFonts) {
            fontMap.put(f, assetManager.get(f.toString() + ".ttf", BitmapFont.class));
        }
    }

    public static BitmapFont getFont(FontEnum fontEnum) {
        return fontMap.get(fontEnum);
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
