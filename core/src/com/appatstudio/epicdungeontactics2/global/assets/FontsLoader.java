package com.appatstudio.epicdungeontactics2.global.assets;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
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
    private static final Color COLOR_RED_TEXT = new Color(0.95f, 0.1f, 0.1f, 1f);
    private static final Color COLOR_YELLOW_TEXT = new Color(0.1f, 0.75f, 0.75f, 1f);
    private static final Color COLOR_GREEN_TEXT = new Color(0.1f, 0.75f, 0.1f, 1f);

    private static final String MAP_FONT_PATH = "fonts/map_font.ttf";
    private static final String DECORATED_FONT_PATH = "fonts/decorated_font.ttf";
    private static final String DECORATED_FONT_ITALIC_PATH = "fonts/decorated_font_italic.ttf";
    private static final String DECORATED_FONT_BOLD_PATH = "fonts/decorated_font_bold.ttf";

    private static final String CHARACTERS = "'qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,.<>?/_!#%&8()1234567890-:=+~[]ąĄćĆżŻźŹóÓęĘłŁńŃśŚ";
    private static final String NUMBERS = "1234567890/";

    static void init(AssetManager assetManager) {

        boolean isTablet = EpicDungeonTactics.isTablet();
        int menuBigTextSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.07f) : (int) (Gdx.graphics.getWidth() * 0.1f);
        int menuSmallTextSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.035f) : (int) (Gdx.graphics.getWidth() * 0.05f);

        int communicatePrinterTextSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.03f) : (int) (Gdx.graphics.getWidth() * 0.04f);
        int mapTextSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.02f) : (int) (Gdx.graphics.getWidth() * 0.03f);

        int enemyNameTextSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.025f) : (int) (Gdx.graphics.getWidth() * 0.035f);
        int bossNameTextSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.06f) : (int) (Gdx.graphics.getWidth() * 0.065f);

        int itemTitleSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.035f) : (int) (Gdx.graphics.getWidth() * 0.04f);
        int itemDescSize = isTablet ? (int) (Gdx.graphics.getWidth() * 0.03f) : (int) (Gdx.graphics.getWidth() * 0.035f);


        //MENU HERO TITLE
        FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_BOLD_PATH;
        parms.fontParameters.size = menuBigTextSize;
        parms.fontParameters.color = COLOR_WHITE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_TITLE_UNLOCKED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_BOLD_PATH;
        parms.fontParameters.size = menuBigTextSize;
        parms.fontParameters.color = COLOR_GRAY_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_TITLE_LOCKED.toString() + ".ttf", BitmapFont.class, parms);


        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = (int)(menuBigTextSize * 1.5f);
        parms.fontParameters.color = COLOR_WHITE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 3;
        parms.fontParameters.characters = NUMBERS;
        assetManager.load(FontEnum.LVL_POINTS_NUMBER.toString() + ".ttf", BitmapFont.class, parms);

        //MENU HERO DESCRIPTION
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_WHITE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_GREEN_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_GREEN.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_RED_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_RED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_GRAY_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_HERO_DESCRIPTION_LOCKED.toString() + ".ttf", BitmapFont.class, parms);


        //STAT WINDOW
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_BLUE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = NUMBERS;
        assetManager.load(FontEnum.STAT_MP.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_RED_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = NUMBERS;
        assetManager.load(FontEnum.STAT_HP.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_GREEN_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = NUMBERS;
        assetManager.load(FontEnum.STAT_EXP.toString() + ".ttf", BitmapFont.class, parms);



        //PERK SCREEN
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_WHITE_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_PERK_TITLE.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = menuSmallTextSize;
        parms.fontParameters.color = COLOR_GRAY_TEXT;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.MENU_PERK_DESCRIPTION.toString() + ".ttf", BitmapFont.class, parms);



        //COMMUNICATE PRINTER
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = communicatePrinterTextSize;
        parms.fontParameters.color = Color.WHITE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_WHITE.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = communicatePrinterTextSize;
        parms.fontParameters.color = Color.GRAY;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_GRAY.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = communicatePrinterTextSize;
        parms.fontParameters.color = Color.GOLD;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_GOLD.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = communicatePrinterTextSize;
        parms.fontParameters.color = Color.RED;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_RED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = DECORATED_FONT_PATH;
        parms.fontParameters.size = communicatePrinterTextSize;
        parms.fontParameters.color = Color.FIREBRICK;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.COMMUNICATE_DARK_RED.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = mapTextSize;
        parms.fontParameters.color = Color.WHITE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.EFFECT_DURATION_FONT.toString() + ".ttf", BitmapFont.class, parms);

        //OTHER GAMES FONT

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = enemyNameTextSize;
        parms.fontParameters.color = Color.RED;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ENEMY_BAR_NAME.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = bossNameTextSize;
        parms.fontParameters.color = Color.RED;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 5;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.BOSS_BAR_NAME.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = enemyNameTextSize;
        parms.fontParameters.color = Color.CYAN;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.PET_BAR_NAME.toString() + ".ttf", BitmapFont.class, parms);


        //ITEMS
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemTitleSize;
        parms.fontParameters.color = Color.WHITE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_TITLE_WHITE.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemTitleSize;
        parms.fontParameters.color = Color.GREEN;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_TITLE_GREEN.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemTitleSize;
        parms.fontParameters.color = Color.BLUE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_TITLE_BLUE.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemTitleSize;
        parms.fontParameters.color = Color.VIOLET;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_TITLE_VIOLET.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemTitleSize;
        parms.fontParameters.color = Color.ORANGE;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_TITLE_ORANGE.toString() + ".ttf", BitmapFont.class, parms);

        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemTitleSize;
        parms.fontParameters.color = Color.RED;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 2;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_TITLE_RED.toString() + ".ttf", BitmapFont.class, parms);

        //desc
        parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = MAP_FONT_PATH;
        parms.fontParameters.size = itemDescSize;
        parms.fontParameters.color = Color.LIGHT_GRAY;
        parms.fontParameters.borderColor = Color.BLACK;
        parms.fontParameters.borderWidth = 1;
        parms.fontParameters.characters = CHARACTERS;
        assetManager.load(FontEnum.ITEM_DESC.toString() + ".ttf", BitmapFont.class, parms);



    }

}
