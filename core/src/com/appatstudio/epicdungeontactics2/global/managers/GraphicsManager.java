package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars.EffectIcon;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
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
    private static final Map<MapElementAnimationEnum, Float> MAP_ELEMENT_ANIMATION_FRAMETIME;

    private static Map<ItemEnum, SpriteDrawable> itemImages;
    private static Map<RoomTypeEnum, SpriteDrawable> mapRoomTypeImages;
    private static Map<GuiElementEnum, SpriteDrawable> guiElements;
    private static Map<StatisticEnum, SpriteDrawable> statIconsMap;
    private static Map<FinanceUpgradeEnum, SpriteDrawable> financesIconsMap;
    private static Map<PerkEnum, SpriteDrawable> perkIconsMap;
    private static Map<ItemEffectEnum, SpriteDrawable> itemEffectIconsMap;
    private static Map<EffectEnum, SpriteDrawable> effectsIconMap;
    private static Map<ItemTypeEnum, SpriteDrawable> itemCategoryIconsMap;
    private static Map<ItemRarityEnum, SpriteDrawable> itemRarityIconsMap;
    private static Map<CharacterEnum, Map<CharacterStateEnum, Animation<SpriteDrawable>>> charactersAnimations;

    private static Map<CharacterEnum, SpriteDrawable> characterProjectiles;
    private static Map<CharacterEnum, SpriteDrawable> heroHeads;

    private static Map<MapElementAnimationEnum, Animation<SpriteDrawable>> mapElementAnimations;
    private static Map<MapElementSpriteEnum, SpriteDrawable> mapElementSprites;

    private static Map<MapElementAnimationEnum, SpriteDrawable> mapElementAnimationsBroken;
    private static Map<MapElementSpriteEnum, SpriteDrawable> mapElementSpritesBroken;

    static {
        MAP_ELEMENT_ANIMATION_FRAMETIME = new HashMap<>();
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.CHEST, 0.1f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TORCH, 0.1f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.LAVA, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.WATER, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.CANDLE, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.CANDLE_BIG, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_1, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_2, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_3, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_4, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_5, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_6, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_7, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_8, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_9, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_10, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_11, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.TREE_12, 0.2f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.GLOWING_STONE_1, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.GLOWING_STONE_2, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.GLOWING_STONE_3, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.GLOWING_STONE_4, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.LAVA_ROCK_SMOKE_1, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.LAVA_ROCK_SMOKE_2, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.LAVA_ROCK_SMOKE_3, 0.15f);
        MAP_ELEMENT_ANIMATION_FRAMETIME.put(MapElementAnimationEnum.LAVA_ROCK_SMOKE_4, 0.15f);
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
            } else if (s.contains("ARROW")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("arrows/" + s))));

            } else if (s.contains("BOOK")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("books/" + s))));

            } else if (s.contains("BOW")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("bows/" + s))));

            } else if (s.contains("FOOD")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("food/" + s))));

            } else if (s.contains("HELMET")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("helmets/" + s))));

            } else if (s.contains("NECKLACE")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("necklaces/" + s))));

            } else if (s.contains("RING")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("rings/" + s))));

            } else if (s.contains("SHIELD")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("shields/" + s))));

            } else if (s.contains("STAFF")) {
                itemImages.put(
                        e,
                        new SpriteDrawable(
                                new Sprite(
                                        atlas.findRegion("staffs/" + s))));

            } else if (s.contains("SWORD")) {
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
            System.out.println(g.toString());
            guiElements.put(g, new SpriteDrawable(new Sprite(atlas.findRegion(g.toString()))));
        }

        mapRoomTypeImages = new HashMap<>();
        RoomTypeEnum[] allRooms = RoomTypeEnum.values();
        for (RoomTypeEnum r : allRooms) {
            mapRoomTypeImages.put(r, new SpriteDrawable(new Sprite(atlas.findRegion("map-room-icons/" + r.toString()))));
        }

        statIconsMap = new HashMap<>();
        StatisticEnum[] allStats = StatisticEnum.values();
        for (StatisticEnum s : allStats) {
            statIconsMap.put(s, new SpriteDrawable(new Sprite(atlas.findRegion("stat-icons/" + s.toString()))));
        }

        perkIconsMap = new HashMap<>();
        PerkEnum[] allPerks = PerkEnum.values();
        for (PerkEnum p : allPerks) {
            perkIconsMap.put(p, new SpriteDrawable(new Sprite(atlas.findRegion("perk-icons/" + p.toString()))));
        }

        itemEffectIconsMap = new HashMap<>();
        ItemEffectEnum[] allItemEffects = ItemEffectEnum.values();
        for (ItemEffectEnum e : allItemEffects) {
            itemEffectIconsMap.put(e, new SpriteDrawable(new Sprite(atlas.findRegion("item-effect-icons/" + e.toString()))));
        }

        effectsIconMap = new HashMap<>();
        EffectEnum[] allEffects = EffectEnum.values();
        for (EffectEnum ef : allEffects) {
            effectsIconMap.put(ef, new SpriteDrawable(new Sprite(atlas.findRegion("effect-icons/" + ef.toString()))));
        }


        financesIconsMap = new HashMap<>();
        FinanceUpgradeEnum[] allFinances = FinanceUpgradeEnum.values();
        for (FinanceUpgradeEnum f : allFinances) {
            financesIconsMap.put(f, new SpriteDrawable(new Sprite(atlas.findRegion("finances-icons/" + f.toString()))));
        }

        itemCategoryIconsMap = new HashMap<>();
        ItemTypeEnum[] allTypes = ItemTypeEnum.values();
        for (ItemTypeEnum i : allTypes) {
            itemCategoryIconsMap.put(i, new SpriteDrawable(new Sprite(atlas.findRegion("item-type-icons/" + i.toString()))));
        }

        itemRarityIconsMap = new HashMap<>();
        ItemRarityEnum[] allRarities = ItemRarityEnum.values();
        for (ItemRarityEnum r : allRarities) {
            itemRarityIconsMap.put(r, new SpriteDrawable(new Sprite(atlas.findRegion("item-rarity-bg/" + r.toString()))));
        }

        heroHeads = new HashMap<>();
        heroHeads.put(CharacterEnum.HERO_ELF, guiElements.get(GuiElementEnum.HEAD_ELF));
        heroHeads.put(CharacterEnum.HERO_KNIGHT, guiElements.get(GuiElementEnum.HEAD_KNIGHT));
        heroHeads.put(CharacterEnum.HERO_WIZZARD, guiElements.get(GuiElementEnum.HEAD_WIZARD));
        heroHeads.put(CharacterEnum.HERO_LIZARD, guiElements.get(GuiElementEnum.HEAD_LIZARD));
        heroHeads.put(CharacterEnum.HERO_PIRATE, guiElements.get(GuiElementEnum.HEAD_PIRATE));
        heroHeads.put(CharacterEnum.HERO_NINJA, guiElements.get(GuiElementEnum.HEAD_NINJA));
        heroHeads.put(CharacterEnum.HERO_BABY, guiElements.get(GuiElementEnum.HEAD_BABY));
    }

    private static void loadCharacters(TextureAtlas atlas) {
        characterProjectiles = new HashMap<>();
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
                            CHARACTER_IDLE_FRAMETIME
                    ));

            charactersAnimations.get(c).put(
                    CharacterStateEnum.RUN,
                    createAnimation(
                            atlas,
                            "characters/" + c.toString() + "/run",
                            CHARACTER_RUN_FRAMETIME
                    ));

            TextureAtlas.AtlasRegion projectile = atlas.findRegion("characters/" + c.toString() + "/projectile");
            if (projectile != null) {
                characterProjectiles.put(
                        c,
                        new SpriteDrawable(new Sprite(projectile)));
            }
        }
    }

    private static void loadMapSprites(TextureAtlas atlas) {
        mapElementSprites = new HashMap<>();
        MapElementSpriteEnum[] allSprites = MapElementSpriteEnum.values();

        for (MapElementSpriteEnum s : allSprites) {
            mapElementSprites.put(
                    s,
                    new SpriteDrawable(new Sprite(atlas.findRegion("map-elements/sprites/" + s.toString()))));
        }

        mapElementAnimations = new HashMap<>();
        MapElementAnimationEnum[] allAnimations = MapElementAnimationEnum.values();

        for (MapElementAnimationEnum a : allAnimations) {
            mapElementAnimations.put(
                    a,
                    createAnimation(
                            atlas,
                            "map-elements/animations/" + a.toString() + "/frame",
                            MAP_ELEMENT_ANIMATION_FRAMETIME.get(a)
                    )
            );
        }

//        //broken
//
//        mapElementSpritesBroken = new HashMap<>();
//        MapElementSpriteEnum[] allSpritesBroken = MapElementSpriteEnum.values();
//
//        for (MapElementSpriteEnum s : allSpritesBroken) {
//            mapElementSpritesBroken.put(
//                    s,
//                    new SpriteDrawable(new Sprite(atlas.findRegion("map-elements/sprites/" + s.toString() + "_BROKEN"))));
//        }
//
//        mapElementAnimationsBroken = new HashMap<>();
//        MapElementAnimationEnum[] allAnimationsBroken = MapElementAnimationEnum.values();
//
//        for (MapElementAnimationEnum a : allAnimationsBroken) {
//            System.out.println("wdvewve:   " + a.toString());
//            mapElementAnimationsBroken.put(
//                    a,
//                    new SpriteDrawable(new Sprite(atlas.findRegion("map-elements/sprites/" + a.toString() + "_BROKEN"))));
//        }
    }

    private static Animation<SpriteDrawable> createAnimation(TextureAtlas atlas, String path, float frameTime) {
        TextureRegion[] regions = atlas.findRegions(path).toArray();
        Array<SpriteDrawable> frames = new Array<>();
        for (TextureRegion r : regions) {
            frames.add(new SpriteDrawable(new Sprite(r)));
        }
        return new Animation<>(frameTime, frames, Animation.PlayMode.LOOP);
    }

    public static void load(AssetManager assetManager) {
        TextureAtlas guiAtlas = assetManager.get("gui-atlas.txt", TextureAtlas.class);
        TextureAtlas worldAtlas = assetManager.get("game-world-graphics-atlas.txt", TextureAtlas.class);

        loadItems(guiAtlas);
        loadGuiElements(guiAtlas);

        loadCharacters(worldAtlas);
        loadMapSprites(worldAtlas);

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

    public static SpriteDrawable getPerkIcon(PerkEnum p) {
        return perkIconsMap.get(p);
    }

    public static SpriteDrawable getItemEffectIcon(ItemEffectEnum e) {
        return itemEffectIconsMap.get(e);
    }

    public static SpriteDrawable getEffectIcon(EffectEnum e) {
        return effectsIconMap.get(e);
    }

    public static SpriteDrawable getFinancesIcon(FinanceUpgradeEnum e) {
        return financesIconsMap.get(e);
    }

    public static SpriteDrawable getItemCategoryIcon(ItemTypeEnum i) {
        return itemCategoryIconsMap.get(i);
    }

    public static Animation<SpriteDrawable> getCampUpgradeFellow(CampUpgradeEnum campUpgrade, CharacterStateEnum s) {
        switch (campUpgrade) {
            case ALCHEMIST: return charactersAnimations.get(CharacterEnum.NPC_ALCHEMIST).get(s);
            case BLACKSMITH: return charactersAnimations.get(CharacterEnum.NPC_BLACKSMITH).get(s);
            case MAGIC_SHOP: return charactersAnimations.get(CharacterEnum.NPC_MAGIC_SHOP).get(s);
            case BUTCHER: return charactersAnimations.get(CharacterEnum.NPC_BUTCHER).get(s);
            case PRINCESS: return charactersAnimations.get(CharacterEnum.NPC_PRINCESS).get(s);
            case MOUNTAIN_KING: return charactersAnimations.get(CharacterEnum.NPC_MOUNTAIN_KING).get(s);
            case LUGGAGE_CARRIAGE: return charactersAnimations.get(CharacterEnum.NPC_CITIZEN_MALE).get(s);
            default: return null;
        }
    }

    public static Map<RoomTypeEnum, SpriteDrawable> getMapRoomIcons() {
        return mapRoomTypeImages;
    }

    public static SpriteDrawable getMapElementSprite(MapElementSpriteEnum spriteEnum) {
        return mapElementSprites.get(spriteEnum);
    }

    public static Animation<SpriteDrawable> getMapElementAnimation(MapElementAnimationEnum animationEnum) {
        return mapElementAnimations.get(animationEnum);
    }

//    public static SpriteDrawable getMapElementInactiveSprite(MapElementAnimationEnum animationEnum) {
//        return mapElementAnimationsBroken.get(animationEnum);
//    }

    public static SpriteDrawable getMapElementInactiveSprite(MapElementSpriteEnum spriteEnum) {
        return mapElementSpritesBroken.get(spriteEnum);
    }

    public static SpriteDrawable getItemRaritySprite(ItemRarityEnum rarityEnum) {
        return itemRarityIconsMap.get(rarityEnum);
    }

    public static SpriteDrawable getProjectile(CharacterEnum characterEnum) {
        return characterProjectiles.get(characterEnum);
    }

    public static void setRotationXforHeroes(boolean b) {
        CharacterEnum[] characters = {
                CharacterEnum.HERO_ELF,
                CharacterEnum.HERO_KNIGHT,
                CharacterEnum.HERO_LIZARD,
                CharacterEnum.HERO_WIZZARD,
                CharacterEnum.HERO_NINJA,
                CharacterEnum.HERO_PIRATE,
                CharacterEnum.HERO_PIRATE,
                CharacterEnum.HERO_BABY};

        CharacterStateEnum[] states = {
                CharacterStateEnum.IDLE,
                CharacterStateEnum.RUN
        };

        for (CharacterEnum character : characters) {
            for (CharacterStateEnum state : states) {
                Object[] frames =
                        GraphicsManager.getCharactersAnimation(character, state).getKeyFrames();

                for (int i=0; i<frames.length; i++) {
                    SpriteDrawable sd = (SpriteDrawable) frames[i];
                    sd.getSprite().setFlip(false, false);
                }
            }
        }

    }

    public static SpriteDrawable getHead(CharacterEnum characterEnum) {
        return heroHeads.get(characterEnum);
    }
}
