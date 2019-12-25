package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public final class GraphicsManager {

    private static final float CHARACTER_IDLE_FRAMETIME = 0.2f;
    private static final float CHARACTER_RUN_FRAMETIME = 0.1f;

    private static Map<ItemEnum, SpriteDrawable> itemImages;
    private static Map<GuiElementEnum, SpriteDrawable> guiElements;
    private static Map<StatisticEnum, SpriteDrawable> statIconsMap;
    private static Map<CharacterEnum, Map<CharacterStateEnum, Animation<SpriteDrawable>>> charactersAnimations;

    public static void load(AssetManager assetManager) {
        TextureAtlas guiAtlas = assetManager.get("gui-atlas.txt", TextureAtlas.class);
        TextureAtlas worldAtlas = assetManager.get("game-world-graphics-atlas.txt", TextureAtlas.class);

        loadItems(guiAtlas);
        loadGuiElements(guiAtlas);

        loadCharacters(worldAtlas);

    }

    private static void loadItems(TextureAtlas atlas) {
        itemImages = new HashMap<>();
        ItemEnum[] allItems = ItemEnum.values();
        for (ItemEnum e : allItems) {
            String s = e.toString();
            if (s.contains("ARMOR")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("armors/" + s))));
            }
            else if (s.contains("ARROW")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("arrows/" + s))));

            }
            else if (s.contains("BOOK")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("books/" + s))));

            }
            else if (s.contains("BOW")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("bows/" + s))));

            }
            else if (s.contains("FOOD")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("food/" + s))));

            }
            else if (s.contains("HELMET")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("helmets/" + s))));

            }
            else if (s.contains("NECKLACE")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("necklaces/" + s))));

            }
            else if (s.contains("RING")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("rings/" + s))));

            }
            else if (s.contains("SHIELD")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("shields/" + s))));

            }
            else if (s.contains("STAFF")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("staffs/" + s))));

            }
            else if (s.contains("SWORD")){
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("swords/" + s))));

            }
        }

        itemImages.put(
                ItemEnum.KEY,
                new SpriteDrawable(
                        new Sprite(
                                atlas.findRegion("KEY"))));

    }
    private static void loadGuiElements(TextureAtlas atlas) {
        guiElements = new HashMap<>();
        GuiElementEnum[] allElements = GuiElementEnum.values();
        for (GuiElementEnum g : allElements) {
            guiElements.put(g, new SpriteDrawable(new Sprite(atlas.findRegion(g.toString()))));
        }

        statIconsMap = new HashMap<>();
        StatisticEnum[] allStats = StatisticEnum.values();
        for (StatisticEnum s : allStats) {
            statIconsMap.put(s, new SpriteDrawable(new Sprite(atlas.findRegion("stat-icons/" + s.toString()))));
        }
    }

    private static void loadCharacters(TextureAtlas atlas) {
        charactersAnimations = new HashMap<>();
        CharacterEnum[] allCharacters = CharacterEnum.values();

        for (CharacterEnum c : allCharacters) {

            charactersAnimations.put(c,
                    new HashMap<CharacterStateEnum, Animation<SpriteDrawable>>());

            charactersAnimations.get(c).put(
                    CharacterStateEnum.IDLE,
                    createAnimation(
                            atlas,
                            "characters/" + c.toString() + "/idle",
                            CHARACTER_IDLE_FRAMETIME,
                            Animation.PlayMode.LOOP));

            charactersAnimations.get(c).put(
                    CharacterStateEnum.RUN,
                    createAnimation(
                            atlas,
                            "characters/" + c.toString() + "/run",
                            CHARACTER_RUN_FRAMETIME,
                            Animation.PlayMode.LOOP));
        }
    }

    private static Animation<SpriteDrawable> createAnimation(TextureAtlas atlas, String path, float frameTime, Animation.PlayMode playMode) {
        TextureRegion[] regions = atlas.findRegions(path).toArray();
        Array<SpriteDrawable> frames = new Array<>();
        for (TextureRegion r : regions) {
            frames.add(new SpriteDrawable(new Sprite(r)));
        }
        return new Animation<>(frameTime, frames, playMode);
    }

    public static Animation<SpriteDrawable> getCharactersAnimation(CharacterEnum c, CharacterStateEnum s) {
        return charactersAnimations.get(c).get(s);
    }

    public static SpriteDrawable getGuiElement(GuiElementEnum g) {
        return guiElements.get(g);
    }

    public static SpriteDrawable getItemImage(ItemEnum itemEnum) {
        return itemImages.get(itemEnum);
    }

    public static SpriteDrawable getStatIcon(StatisticEnum s) {
        return statIconsMap.get(s);
    }
}
