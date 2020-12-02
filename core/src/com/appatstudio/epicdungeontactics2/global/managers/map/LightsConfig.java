package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.BEAR;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.BOSS_BIG_DEMON;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.BIG_ZOMBIE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.CENTAUR_FEMALE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.CENTAUR_MALE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.CHORT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ELVEN_KING;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ELVEN_KNIGHT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ELVEN_PRINCESS;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ENT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.BOSS_FOREST_GUARDIAN;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.GNOLL_BRUTE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.GNOLL_OVERSEER;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.GNOLL_SCOUT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.GNOLL_SHAMAN;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.GOBLIN;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_BABY;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_ELF;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_KNIGHT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_LIZARD;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_NINJA;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_PIRATE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.HERO_WIZZARD;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.IMP;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MASKED_ORC;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MUDDY;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MUSHROOM_LARGE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MUSHROOM_NORMAL;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MUSHROOM_SMALL;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NECROMANCER;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_ALCHEMIST;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_BISHOP;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_BLACKSMITH;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_BUTCHER;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_CITIZEN_FEMALE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_CITIZEN_MALE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_KING;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_KNIGHT_ELITE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_MAGIC_SHOP;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_MOUNTAIN_KING;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_NUN_FAT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_NUN_NORMAL;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_PRINCESS;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.NPC_THIEF;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.BOSS_OGRE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ORC_SHAMAN;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ORC_WARRIOR;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.PET_HUNTER;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.PET_KNIGHT;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.SKELET;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.SWAMPY;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.TINY_ZOMBIE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.TROLL;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.WOGOL;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.WOLF;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ZOMBIE;

public class LightsConfig {

    public static final int CHARACTER_RAYS = 32;
    public static final int ELEMENT_RAYS = 32;

    private static final Color[] ambientColors = new Color[]
            {
            new Color(0.84f, 0.84f, 0.84f, 0.95f), //forest day
            new Color(0.62f, 0.56f, 0.68f, 0.85f), //forest night
            new Color(0.62f, 0.26f, 0.38f, 0.45f), //stage 2
            new Color(0.84f, 0.44f, 0.44f, 0.55f)  //stage 3
            };

    private static final HashMap<CharacterEnum, LightConfigObject> characterLights;
    private static final HashMap<MapElementAnimationEnum, LightConfigObject> mapElementsAnimationLightConfig;
    private static final HashMap<MapElementSpriteEnum, LightConfigObject> mapElementsSpriteLightConfig;

    static {

        final float SMALL_CHARACTER_RADIUS = WorldConfig.TILE_SIZE * 4f;
        final float SMALL_SOFTNESS = SMALL_CHARACTER_RADIUS/2f;
        final float MEDIUM_CHARACTER_RADIUS = WorldConfig.TILE_SIZE * 4f;
        final float MEDIUM_SOFTNESS = SMALL_CHARACTER_RADIUS/2f;
        final float BIG_CHARACTER_RADIUS = WorldConfig.TILE_SIZE * 4f;
        final float BIG_SOFTNESS = SMALL_CHARACTER_RADIUS/2f;

        final Color LIGHT_COLOR = new Color(1f, 1f, 1f, 1f);

        characterLights = new HashMap<>();
        characterLights.put(HERO_ELF,
                new LightConfigObject(
                SMALL_CHARACTER_RADIUS,
                SMALL_SOFTNESS,
                LIGHT_COLOR,
                new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
        ));
        characterLights.put(HERO_KNIGHT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(HERO_WIZZARD,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(HERO_LIZARD,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(HERO_PIRATE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(HERO_NINJA,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(HERO_BABY,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(TINY_ZOMBIE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ZOMBIE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(MUDDY,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(BIG_ZOMBIE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(SWAMPY,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ORC_SHAMAN,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ORC_WARRIOR,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(GOBLIN,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(MASKED_ORC,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(BOSS_OGRE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(SKELET,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(CHORT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(IMP,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NECROMANCER,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(WOGOL,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(BOSS_BIG_DEMON,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(CENTAUR_MALE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(CENTAUR_FEMALE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ENT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(TROLL,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(WOLF,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(MUSHROOM_SMALL,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(MUSHROOM_NORMAL,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(MUSHROOM_LARGE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(BEAR,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(BOSS_FOREST_GUARDIAN,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ELVEN_PRINCESS,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ELVEN_KING,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(ELVEN_KNIGHT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(GNOLL_BRUTE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(GNOLL_OVERSEER,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(GNOLL_SCOUT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(GNOLL_SHAMAN,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_ALCHEMIST,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_THIEF,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_CITIZEN_FEMALE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_CITIZEN_MALE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_BUTCHER,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_MOUNTAIN_KING,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_MAGIC_SHOP,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_PRINCESS,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_BISHOP,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_NUN_FAT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_NUN_NORMAL,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_BLACKSMITH,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_KING,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(NPC_KNIGHT_ELITE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(PET_KNIGHT,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        characterLights.put(PET_HUNTER,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));


        mapElementsAnimationLightConfig = new HashMap<>();
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.WATER,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.LAVA,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.CANDLE_BIG,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.CANDLE,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TORCH,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.CHEST,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));

        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_1,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));

        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_2,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_3,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_4,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_5,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_6,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_7,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_8,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_9,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_10,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_11,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.TREE_12,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.LAVA_ROCK_SMOKE_1,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.LAVA_ROCK_SMOKE_2,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.LAVA_ROCK_SMOKE_3,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.LAVA_ROCK_SMOKE_4,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.GLOWING_STONE_1,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.GLOWING_STONE_2,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.GLOWING_STONE_3,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsAnimationLightConfig.put(
                MapElementAnimationEnum.GLOWING_STONE_4,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));









        //_______________________________________________

        mapElementsSpriteLightConfig = new HashMap<>();
//        mapElementsSpriteLightConfig.put(
//                MapElementSpriteEnum.CHEST_EMPTY,
//                new LightConfigObject(
//                        SMALL_CHARACTER_RADIUS,
//                        SMALL_SOFTNESS,
//                        LIGHT_COLOR,
//                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
//                ));
        mapElementsSpriteLightConfig.put(
                MapElementSpriteEnum.TILE_GREEN,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsSpriteLightConfig.put(
                MapElementSpriteEnum.TILE_RED,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
        mapElementsSpriteLightConfig.put(
                MapElementSpriteEnum.TILE_YELLOW,
                new LightConfigObject(
                        SMALL_CHARACTER_RADIUS,
                        SMALL_SOFTNESS,
                        LIGHT_COLOR,
                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
                ));
//        mapElementsSpriteLightConfig.put(
//                MapElementSpriteEnum.CRATE,
//                new LightConfigObject(
//                        SMALL_CHARACTER_RADIUS,
//                        SMALL_SOFTNESS,
//                        LIGHT_COLOR,
//                        new CoordsFloat(WorldConfig.TILE_SIZE/2f, WorldConfig.TILE_SIZE)
//                ));
    }

    public static Color getAmbientColor(int stage) {
        switch (stage) {
            case 2:
                return ambientColors[2];
            case 3:
                return ambientColors[3];
            default:
                if (EpicDungeonTactics.isDay()) {
                    return ambientColors[0];
                }
                else return ambientColors[1];

        }
    }

    public static LightConfigObject getCharacterLights(CharacterEnum characterEnum) {
        return characterLights.get(characterEnum);
    }

    public static LightConfigObject getMapElementAnimationLights(MapElementAnimationEnum mapElementAnimationEnum) {
        return mapElementsAnimationLightConfig.get(mapElementAnimationEnum);
    }

    public static LightConfigObject getMapElementSpriteLights(MapElementSpriteEnum mapElementSpriteEnum) {
        return mapElementsSpriteLightConfig.get(mapElementSpriteEnum);
    }
}
