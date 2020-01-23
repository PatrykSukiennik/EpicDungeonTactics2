package com.appatstudio.epicdungeontactics2.global.assets;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

final class FontsLoader {

    private static final Color COLOR_WHITE_TEXT = new Color(0.95f, 0.95f, 0.95f, 1f);
    private static final Color COLOR_GRAY_TEXT = new Color(0.45f, 0.45f, 0.45f, 1f);
    private static final Color COLOR_BLUE_TEXT = new Color(0.1f, 0.1f, 0.95f, 1f);
    private static final Color COLOR_YELLOW_TEXT = new Color(0.1f, 0.75f, 0.75f, 1f);
    private static final Color COLOR_GREEN_TEXT = new Color(0.1f, 0.95f, 0.1f, 1f);

    private static final String MAP_FONT_PATH = "fonts/map_font.ttf";
    private static final String DECORATED_FONT_PATH = "fonts/decorated_font.ttf";

    private static final String CHARACTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,.<>?/_!#%&8()1234567890-:=+~[]";

    static void init(AssetManager assetManager) {

        //MENU HERO TITLE
        FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.1f);
        parms.fontParameters.color = COLOR_WHITE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_TITLE_UNLOCKED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.1f);
        parms.fontParameters.color = COLOR_GRAY_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_TITLE_LOCKED.toString() + ".ttf", BitmapFont.class, parms);


        //MENU HERO DESCRIPTION
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.05f);
        parms.fontParameters.color = COLOR_WHITE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.05f);
        parms.fontParameters.color = COLOR_GRAY_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_DESCRIPTION_LOCKED.toString() + ".ttf", BitmapFont.class, parms);



        //COMMUNICATE PRINTER
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.04f);
        parms.fontParameters.color = Color.WHITE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_WHITE.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.04f);
        parms.fontParameters.color = Color.GRAY;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_GRAY.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.04f);
        parms.fontParameters.color = Color.GOLD;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_GOLD.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.04f);
        parms.fontParameters.color = Color.RED;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_RED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.04f);
        parms.fontParameters.color = Color.FIREBRICK;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_DARK_RED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.03f);
        parms.fontParameters.color = Color.WHITE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.EFFECT_DURATION_FONT.toString() + ".ttf", BitmapFont.class, parms);

    }

}
