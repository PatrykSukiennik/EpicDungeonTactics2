package com.appatstudio.epicdungeontactics2.global.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

final class GraphicsLoader {

    public static void init(AssetManager assetManager) {
        assetManager.load("gui-atlas.txt", TextureAtlas.class);
        assetManager.load("game-world-graphics-atlas.txt", TextureAtlas.class);
    }

}
