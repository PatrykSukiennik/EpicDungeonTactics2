package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.*;


public class BodyConfig {

    private static final HashMap<CharacterEnum, BodyDef> characterBodyDef;
    private static final HashMap<CharacterEnum, FixtureDef> characterFixtureDef;
    private static final HashMap<MapElementAnimationEnum, BodyDef> mapElementsAnimationBodyDef;
    private static final HashMap<MapElementAnimationEnum, FixtureDef> mapElementsAnimationFixtureDef;
    private static final HashMap<MapElementSpriteEnum, BodyDef> mapElementsSpriteBodyDef;
    private static final HashMap<MapElementSpriteEnum, FixtureDef> mapElementsSpriteFixtureDef;

    static {
        final BodyDef smallBody;
        final BodyDef mediumBody;
        final BodyDef bigBody;

        final CircleShape smallShape;
        final CircleShape mediumShape;
        final CircleShape bigShape;

        final FixtureDef smallFixtureDef;
        final FixtureDef mediumFixtureDef;
        final FixtureDef bigFixtureDef;

        smallBody = new BodyDef();
        smallBody.type = BodyDef.BodyType.KinematicBody;
        smallShape = new CircleShape();
        smallShape.setRadius(WorldConfig.TILE_SIZE / 3f);
        smallFixtureDef = new FixtureDef();
        smallFixtureDef.shape = smallShape;
        smallFixtureDef.density = 0.8f;

        mediumBody = new BodyDef();
        mediumBody.type = BodyDef.BodyType.KinematicBody;
        mediumShape = new CircleShape();
        mediumShape.setRadius(WorldConfig.TILE_SIZE / 2f);
        mediumFixtureDef = new FixtureDef();
        mediumFixtureDef.shape = mediumShape;
        mediumFixtureDef.density = 0.8f;

        bigBody = new BodyDef();
        bigBody.type = BodyDef.BodyType.KinematicBody;
        bigShape = new CircleShape();
        bigShape.setRadius(WorldConfig.TILE_SIZE);
        bigFixtureDef = new FixtureDef();
        bigFixtureDef.shape = bigShape;
        bigFixtureDef.density = 0.8f;

        characterBodyDef = new HashMap<>();
        characterBodyDef.put(HERO_ELF, smallBody);
        characterBodyDef.put(HERO_KNIGHT, smallBody);
        characterBodyDef.put(HERO_WIZZARD, smallBody);
        characterBodyDef.put(HERO_LIZARD, smallBody);
        characterBodyDef.put(HERO_PIRATE, smallBody);
        characterBodyDef.put(HERO_NINJA, smallBody);
        characterBodyDef.put(HERO_BABY, smallBody);
        characterBodyDef.put(TINY_ZOMBIE, smallBody);
        characterBodyDef.put(ZOMBIE, smallBody);
        characterBodyDef.put(MUDDY, smallBody);
        characterBodyDef.put(BIG_ZOMBIE, smallBody);
        characterBodyDef.put(SWAMPY, smallBody);
        characterBodyDef.put(ORC_SHAMAN, smallBody);
        characterBodyDef.put(ORC_WARRIOR, smallBody);
        characterBodyDef.put(GOBLIN, smallBody);
        characterBodyDef.put(MASKED_ORC, smallBody);
        characterBodyDef.put(BOSS_OGRE, smallBody);
        characterBodyDef.put(SKELET, smallBody);
        characterBodyDef.put(CHORT, smallBody);
        characterBodyDef.put(IMP, smallBody);
        characterBodyDef.put(NECROMANCER, smallBody);
        characterBodyDef.put(WOGOL, smallBody);
        characterBodyDef.put(BOSS_BIG_DEMON, bigBody);
        characterBodyDef.put(CENTAUR_MALE, smallBody);
        characterBodyDef.put(CENTAUR_FEMALE, smallBody);
        characterBodyDef.put(ENT, smallBody);
        characterBodyDef.put(TROLL, smallBody);
        characterBodyDef.put(WOLF, smallBody);
        characterBodyDef.put(MUSHROOM_SMALL, smallBody);
        characterBodyDef.put(MUSHROOM_NORMAL, smallBody);
        characterBodyDef.put(MUSHROOM_LARGE, smallBody);
        characterBodyDef.put(BEAR, smallBody);
        characterBodyDef.put(BOSS_FOREST_GUARDIAN, smallBody);
        characterBodyDef.put(ELVEN_PRINCESS, smallBody);
        characterBodyDef.put(ELVEN_KING, smallBody);
        characterBodyDef.put(ELVEN_KNIGHT, smallBody);
        characterBodyDef.put(GNOLL_BRUTE, smallBody);
        characterBodyDef.put(GNOLL_OVERSEER, smallBody);
        characterBodyDef.put(GNOLL_SCOUT, smallBody);
        characterBodyDef.put(GNOLL_SHAMAN, smallBody);
        characterBodyDef.put(NPC_ALCHEMIST, smallBody);
        characterBodyDef.put(NPC_THIEF, smallBody);
        characterBodyDef.put(NPC_CITIZEN_FEMALE, smallBody);
        characterBodyDef.put(NPC_CITIZEN_MALE, smallBody);
        characterBodyDef.put(NPC_BUTCHER, smallBody);
        characterBodyDef.put(NPC_MOUNTAIN_KING, smallBody);
        characterBodyDef.put(NPC_PRINCESS, smallBody);
        characterBodyDef.put(NPC_BISHOP, smallBody);
        characterBodyDef.put(NPC_NUN_FAT, smallBody);
        characterBodyDef.put(NPC_NUN_NORMAL, smallBody);
        characterBodyDef.put(NPC_BLACKSMITH, smallBody);
        characterBodyDef.put(NPC_KING, smallBody);
        characterBodyDef.put(NPC_KNIGHT_ELITE, smallBody);
        characterBodyDef.put(PET_KNIGHT, smallBody);
        characterBodyDef.put(PET_HUNTER, smallBody);


        characterFixtureDef = new HashMap<>();
        characterFixtureDef.put(HERO_ELF, smallFixtureDef);
        characterFixtureDef.put(HERO_KNIGHT, smallFixtureDef);
        characterFixtureDef.put(HERO_WIZZARD, smallFixtureDef);
        characterFixtureDef.put(HERO_LIZARD, smallFixtureDef);
        characterFixtureDef.put(HERO_PIRATE, smallFixtureDef);
        characterFixtureDef.put(HERO_NINJA, smallFixtureDef);
        characterFixtureDef.put(HERO_BABY, smallFixtureDef);
        characterFixtureDef.put(TINY_ZOMBIE, smallFixtureDef);
        characterFixtureDef.put(ZOMBIE, smallFixtureDef);
        characterFixtureDef.put(MUDDY, smallFixtureDef);
        characterFixtureDef.put(BIG_ZOMBIE, smallFixtureDef);
        characterFixtureDef.put(SWAMPY, smallFixtureDef);
        characterFixtureDef.put(ORC_SHAMAN, smallFixtureDef);
        characterFixtureDef.put(ORC_WARRIOR, smallFixtureDef);
        characterFixtureDef.put(GOBLIN, smallFixtureDef);
        characterFixtureDef.put(MASKED_ORC, smallFixtureDef);
        characterFixtureDef.put(BOSS_OGRE, smallFixtureDef);
        characterFixtureDef.put(SKELET, smallFixtureDef);
        characterFixtureDef.put(CHORT, smallFixtureDef);
        characterFixtureDef.put(IMP, smallFixtureDef);
        characterFixtureDef.put(NECROMANCER, smallFixtureDef);
        characterFixtureDef.put(WOGOL, smallFixtureDef);
        characterFixtureDef.put(BOSS_BIG_DEMON, bigFixtureDef);
        characterFixtureDef.put(CENTAUR_MALE, smallFixtureDef);
        characterFixtureDef.put(CENTAUR_FEMALE, smallFixtureDef);
        characterFixtureDef.put(ENT, smallFixtureDef);
        characterFixtureDef.put(TROLL, smallFixtureDef);
        characterFixtureDef.put(WOLF, smallFixtureDef);
        characterFixtureDef.put(MUSHROOM_SMALL, smallFixtureDef);
        characterFixtureDef.put(MUSHROOM_NORMAL, smallFixtureDef);
        characterFixtureDef.put(MUSHROOM_LARGE, smallFixtureDef);
        characterFixtureDef.put(BEAR, smallFixtureDef);
        characterFixtureDef.put(BOSS_FOREST_GUARDIAN, smallFixtureDef);
        characterFixtureDef.put(ELVEN_PRINCESS, smallFixtureDef);
        characterFixtureDef.put(ELVEN_KING, smallFixtureDef);
        characterFixtureDef.put(ELVEN_KNIGHT, smallFixtureDef);
        characterFixtureDef.put(GNOLL_BRUTE, smallFixtureDef);
        characterFixtureDef.put(GNOLL_OVERSEER, smallFixtureDef);
        characterFixtureDef.put(GNOLL_SCOUT, smallFixtureDef);
        characterFixtureDef.put(GNOLL_SHAMAN, smallFixtureDef);
        characterFixtureDef.put(NPC_ALCHEMIST, smallFixtureDef);
        characterFixtureDef.put(NPC_THIEF, smallFixtureDef);
        characterFixtureDef.put(NPC_CITIZEN_FEMALE, smallFixtureDef);
        characterFixtureDef.put(NPC_CITIZEN_MALE, smallFixtureDef);
        characterFixtureDef.put(NPC_BUTCHER, smallFixtureDef);
        characterFixtureDef.put(NPC_MOUNTAIN_KING, smallFixtureDef);
        characterFixtureDef.put(NPC_PRINCESS, smallFixtureDef);
        characterFixtureDef.put(NPC_BISHOP, smallFixtureDef);
        characterFixtureDef.put(NPC_NUN_FAT, smallFixtureDef);
        characterFixtureDef.put(NPC_NUN_NORMAL, smallFixtureDef);
        characterFixtureDef.put(NPC_BLACKSMITH, smallFixtureDef);
        characterFixtureDef.put(NPC_KING, smallFixtureDef);
        characterFixtureDef.put(NPC_KNIGHT_ELITE, smallFixtureDef);
        characterFixtureDef.put(PET_KNIGHT, smallFixtureDef);
        characterFixtureDef.put(PET_HUNTER, smallFixtureDef);


        mapElementsAnimationBodyDef = new HashMap<>();
        mapElementsAnimationBodyDef.put(MapElementAnimationEnum.WATER, smallBody);
        mapElementsAnimationBodyDef.put(MapElementAnimationEnum.LAVA, smallBody);
        mapElementsAnimationBodyDef.put(MapElementAnimationEnum.CANDLE_BIG, smallBody);
        mapElementsAnimationBodyDef.put(MapElementAnimationEnum.CANDLE, smallBody);
        mapElementsAnimationBodyDef.put(MapElementAnimationEnum.TORCH, smallBody);
        mapElementsAnimationBodyDef.put(MapElementAnimationEnum.CHEST, smallBody);

        mapElementsAnimationFixtureDef = new HashMap<>();
        mapElementsAnimationFixtureDef.put(MapElementAnimationEnum.WATER, smallFixtureDef);
        mapElementsAnimationFixtureDef.put(MapElementAnimationEnum.LAVA, smallFixtureDef);
        mapElementsAnimationFixtureDef.put(MapElementAnimationEnum.CANDLE_BIG, smallFixtureDef);
        mapElementsAnimationFixtureDef.put(MapElementAnimationEnum.CANDLE, smallFixtureDef);
        mapElementsAnimationFixtureDef.put(MapElementAnimationEnum.TORCH, smallFixtureDef);
        mapElementsAnimationFixtureDef.put(MapElementAnimationEnum.CHEST, smallFixtureDef);

        mapElementsSpriteBodyDef = new HashMap<>();
//        mapElementsSpriteBodyDef.put(MapElementSpriteEnum.CRATE, smallBody);
        mapElementsSpriteBodyDef.put(MapElementSpriteEnum.TILE_YELLOW, smallBody);
        mapElementsSpriteBodyDef.put(MapElementSpriteEnum.TILE_RED, smallBody);
        mapElementsSpriteBodyDef.put(MapElementSpriteEnum.TILE_GREEN, smallBody);
//        mapElementsSpriteBodyDef.put(MapElementSpriteEnum.CHEST_EMPTY, smallBody);
//
        mapElementsSpriteFixtureDef = new HashMap<>();
//        mapElementsSpriteFixtureDef.put(MapElementSpriteEnum.CRATE, smallFixtureDef);
        mapElementsSpriteFixtureDef.put(MapElementSpriteEnum.TILE_YELLOW, smallFixtureDef);
        mapElementsSpriteFixtureDef.put(MapElementSpriteEnum.TILE_RED, smallFixtureDef);
        mapElementsSpriteFixtureDef.put(MapElementSpriteEnum.TILE_GREEN, smallFixtureDef);
//        mapElementsSpriteFixtureDef.put(MapElementSpriteEnum.CHEST_EMPTY, smallFixtureDef);

    }

    public static BodyDef getCharacterBodyDef(CharacterEnum characterEnum) {
        return characterBodyDef.get(characterEnum);
    }

    public static FixtureDef getCharacterFixtureDef(CharacterEnum characterEnum) {
        return characterFixtureDef.get(characterEnum);
    }

    public static BodyDef getMapElementsAnimationBodyDef(MapElementAnimationEnum mapElementAnimationEnum) {
        return mapElementsAnimationBodyDef.get(mapElementAnimationEnum);
    }

    public static FixtureDef getMapElementsAnimationFixtureDef(MapElementAnimationEnum mapElementAnimationEnum) {
        return mapElementsAnimationFixtureDef.get(mapElementAnimationEnum);
    }

    public static BodyDef getMapElementsSpriteBodyDef(MapElementSpriteEnum mapElementSpriteEnum) {
        return mapElementsSpriteBodyDef.get(mapElementSpriteEnum);
    }

    public static FixtureDef getMapElementsSpriteFixtureDef(MapElementSpriteEnum mapElementSpriteEnum) {
        return mapElementsSpriteFixtureDef.get(mapElementSpriteEnum);
    }
}
