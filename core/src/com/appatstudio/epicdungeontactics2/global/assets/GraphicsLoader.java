package com.appatstudio.epicdungeontactics2.global.assets;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Map;

final class GraphicsLoader {

    public static void init(AssetManager assetManager) {
        assetManager.load("gui-atlas.txt", TextureAtlas.class);
        assetManager.load("game-world-graphics-atlas.txt", TextureAtlas.class);
    }

}
