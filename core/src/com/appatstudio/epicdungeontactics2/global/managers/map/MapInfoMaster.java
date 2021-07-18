package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementAnimationEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoElementsLocationsStage1;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoElementsLocationsStage2;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoElementsLocationsStage3;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoEnemyStage1;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoEnemyStage2;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoEnemyStage3;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoWalkableArrayStage1;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoWalkableArrayStage2;
import com.appatstudio.epicdungeontactics2.global.managers.map.mapInfo.MapInfoWalkableArrayStage3;

import java.util.HashMap;

public class MapInfoMaster {

    private static final HashMap<RoomEnum, MapElementAnimationEnum[][]> animationElementsMap;
    private static final HashMap<RoomEnum, CharacterEnum[][]> charactersInfoMap;
    private static final HashMap<RoomEnum, MapPathFindingFlags[][]> walkableMapsMap;

    static {
        animationElementsMap = new HashMap<>();
        animationElementsMap.putAll(MapInfoElementsLocationsStage1.getAnimatedElements());
        animationElementsMap.putAll(MapInfoElementsLocationsStage2.getAnimatedElements());
        animationElementsMap.putAll(MapInfoElementsLocationsStage3.getAnimatedElements());

        charactersInfoMap = new HashMap<>();
        charactersInfoMap.putAll(MapInfoEnemyStage1.getCharactersInfo());
        charactersInfoMap.putAll(MapInfoEnemyStage2.getCharactersInfo());
        charactersInfoMap.putAll(MapInfoEnemyStage3.getCharactersInfo());

        walkableMapsMap = new HashMap<>();
        walkableMapsMap.putAll(MapInfoWalkableArrayStage1.getWalkableArray());
        walkableMapsMap.putAll(MapInfoWalkableArrayStage2.getWalkableArray());
        walkableMapsMap.putAll(MapInfoWalkableArrayStage3.getWalkableArray());

    }


    public static MapElementAnimationEnum[][] getAnimatedElements(RoomEnum roomEnum) {
        return animationElementsMap.get(roomEnum);
    }

    public static CharacterEnum[][] getCharactersInfo(RoomEnum roomEnum) {
        return charactersInfoMap.get(roomEnum);
    }

    public static MapPathFindingFlags[][] getWalkableArray(RoomEnum room) {
        return walkableMapsMap.get(room);
    }


}
