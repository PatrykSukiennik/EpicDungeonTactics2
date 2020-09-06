package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
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

    public static BitmapFont getFont(ItemRarityEnum rarityEnum) {
        switch (rarityEnum) {
            case WHITE: return fontMap.get(FontEnum.ITEM_TITLE_WHITE);
            case GREEN: return fontMap.get(FontEnum.ITEM_TITLE_GREEN);
            case BLUE: return fontMap.get(FontEnum.ITEM_TITLE_BLUE);
            case VIOLET: return fontMap.get(FontEnum.ITEM_TITLE_VIOLET);
            case ORANGE: return fontMap.get(FontEnum.ITEM_TITLE_ORANGE);
            case RED: return fontMap.get(FontEnum.ITEM_TITLE_RED);
            default: return null;
        }
    }

    public static float getTextWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public static float getTextHeight(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.height;
    }

    public static float getTextHeightMultiline(BitmapFont font, String text, float width, int alignment) {
        glyphLayout.setText(font, text, Color.WHITE, width, alignment, true);
        return glyphLayout.height;
    }
}
