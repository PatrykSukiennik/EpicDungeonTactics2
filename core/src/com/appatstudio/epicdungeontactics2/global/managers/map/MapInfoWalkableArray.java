package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags.MOVABLE;
import static com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags.NONE;

public class MapInfoWalkableArray {

    private static final HashMap<RoomEnum, MapPathFindingFlags[][]> walkableMapsMap;

    static {
        walkableMapsMap = new HashMap<>();

//python-insert-walkable
walkableMapsMap.put(RoomEnum.STAGE_1_FIRST_1, new MapPathFindingFlags[][] { 
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE, NONE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, NONE, NONE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE, NONE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, MOVABLE, NONE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE }
});
walkableMapsMap.put(RoomEnum.STAGE_2_FIRST_1, new MapPathFindingFlags[][] { 
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE },
{  MOVABLE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, MOVABLE, NONE, NONE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  MOVABLE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE }
});
walkableMapsMap.put(RoomEnum.STAGE_3_FIRST_1, new MapPathFindingFlags[][] { 
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, MOVABLE, NONE, NONE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  MOVABLE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, MOVABLE },
{  MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE, MOVABLE },
{  MOVABLE, MOVABLE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, MOVABLE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE },
{  NONE, NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, NONE },
{  NONE, MOVABLE, MOVABLE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE },
{  NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE },
{  NONE, NONE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE },
{  NONE, NONE, MOVABLE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE },
{  NONE, NONE, NONE, NONE, MOVABLE, MOVABLE, MOVABLE, NONE, NONE, NONE, NONE }
});
//python-insert-walkable-end

    }
}