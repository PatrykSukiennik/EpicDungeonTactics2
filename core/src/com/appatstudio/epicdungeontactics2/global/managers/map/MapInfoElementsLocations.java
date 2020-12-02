package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum.*;

public class MapInfoElementsLocations {

    private static final HashMap<RoomEnum, MapElementAnimationEnum[][]> animationElementsMap;
    private static final HashMap<RoomEnum, MapElementSpriteEnum[][]> spriteElementsMap;

    static {
        animationElementsMap = new HashMap<>();

//python-insert-anim
animationElementsMap.put(RoomEnum.STAGE_1_FIRST_1, new MapElementAnimationEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, TREE_9, null },
{  TREE_8, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, TREE_6 },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
animationElementsMap.put(RoomEnum.STAGE_1_REGULAR_1, new MapElementAnimationEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, TREE_5, null, null },
{  TREE_12, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
animationElementsMap.put(RoomEnum.STAGE_1_BOSS_1, new MapElementAnimationEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, TREE_2, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, TREE_7, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, TREE_9, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
animationElementsMap.put(RoomEnum.STAGE_2_FIRST_1, new MapElementAnimationEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, GLOWING_STONE_4, null, null, GLOWING_STONE_3, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, GLOWING_STONE_3, null },
{  null, null, GLOWING_STONE_4, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, GLOWING_STONE_4, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  GLOWING_STONE_2, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, CANDLE_BIG, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, LAVA_ROCK_SMOKE_3 },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, GLOWING_STONE_1, null, null, null },
{  null, null, null, null, GLOWING_STONE_3, null, null, null, null, null, null },
{  null, null, null, null, null, null, GLOWING_STONE_1, null, null, null, null },
{  null, null, null, null, null, null, null, CANDLE, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
animationElementsMap.put(RoomEnum.STAGE_3_FIRST_1, new MapElementAnimationEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, LAVA_ROCK_SMOKE_1 },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, LAVA_ROCK_SMOKE_2, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, LAVA_ROCK_SMOKE_3, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, LAVA_ROCK_SMOKE_1, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
//python-insert-anim-end

        spriteElementsMap = new HashMap<>();
        spriteElementsMap.put(RoomEnum.STAGE_1_FIRST_1, new MapElementSpriteEnum[][]{
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null },
                {  null, null, null, null, null, null, null, null, null, null, null }
        });
    }

    public static MapElementAnimationEnum[][] getAnimatedElements(RoomEnum roomEnum) {
        return animationElementsMap.get(roomEnum);
    }

    public static MapElementSpriteEnum[][] getSpriteElements(RoomEnum roomEnum) {
        return spriteElementsMap.get(roomEnum);
    }
}
