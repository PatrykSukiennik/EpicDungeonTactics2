package com.appatstudio.epicdungeontactics2.global.assets;

import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.I18NBundle;

public final class AssetsMaster {

    private static AssetManager assetManager;

    static {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);

        assetManager = new AssetManager();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(resolver));
        assetManager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
        assetManager.setLoader(I18NBundle.class, new I18NBundleLoader(resolver));
    }

    public static void loaded() {
        GraphicsManager.load(assetManager);
        StringsManager.load();
        FontsManager.load(assetManager);
        SoundsManager.load(assetManager);
    }

    public static void init() {
        FontsLoader.init(assetManager);
        GraphicsLoader.init(assetManager);
    }

    public static float getProgress() {
        assetManager.update();
        return assetManager.getProgress();
    }

    public static boolean isFinished() {
        assetManager.update();
        return assetManager.isFinished() && assetManager.isLoaded("game-world-graphics-atlas.txt");
    }

    public static void dispose() {
        assetManager.dispose();
    }

}
