package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGeneratorConfig;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.RoomEnum.*;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum.BOSS_ROOM;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum.FIRST_ROOM;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum.REGULAR_ROOM;

public class MapGenerator {

    private static final HashMap<Integer, HashMap<RoomTypeEnum, Integer>> mapCounter;
    private static final HashMap<Integer, HashMap<RoomTypeEnum, Integer>> mapChanceSums;
    private static final HashMap<Integer, HashMap<RoomTypeEnum, RoomEnum[]>> mapEnums;
    private static final HashMap<RoomEnum, Integer> mapChances;

    static {
        mapCounter = new HashMap<>();
        mapCounter.put(1, new HashMap<RoomTypeEnum, Integer>());
        mapCounter.put(2, new HashMap<RoomTypeEnum, Integer>());
        mapCounter.put(3, new HashMap<RoomTypeEnum, Integer>());

        mapChances = new HashMap<>();

//python-include-map-counter
mapCounter.get(1).put(FIRST_ROOM, 1);
mapCounter.get(1).put(REGULAR_ROOM, 1);
mapCounter.get(1).put(BOSS_ROOM, 1);
mapCounter.get(2).put(FIRST_ROOM, 1);
mapCounter.get(2).put(REGULAR_ROOM, 0);
mapCounter.get(2).put(BOSS_ROOM, 0);
mapCounter.get(3).put(FIRST_ROOM, 1);
mapCounter.get(3).put(REGULAR_ROOM, 0);
mapCounter.get(3).put(BOSS_ROOM, 0);
//python-include-map-counter-end

//python-include-map-chances
mapChances.put(STAGE_1_FIRST_1, 100);
mapChances.put(STAGE_1_REGULAR_1, 100);
mapChances.put(STAGE_1_BOSS_1, 100);
mapChances.put(STAGE_2_FIRST_1, 100);
mapChances.put(STAGE_3_FIRST_1, 100);
//python-include-map-chances-end

        mapEnums = new HashMap<>();
        mapEnums.put(1, new HashMap<RoomTypeEnum, RoomEnum[]>());
        mapEnums.put(2, new HashMap<RoomTypeEnum, RoomEnum[]>());
        mapEnums.put(3, new HashMap<RoomTypeEnum, RoomEnum[]>());

        mapChanceSums = new HashMap<>();
        mapChanceSums.put(1, new HashMap<RoomTypeEnum, Integer>());
        mapChanceSums.put(2, new HashMap<RoomTypeEnum, Integer>());
        mapChanceSums.put(3, new HashMap<RoomTypeEnum, Integer>());

        for (int stage = 1; stage <= 3; stage++) {
            int temp = 0;
            mapEnums.get(stage).put(FIRST_ROOM, new RoomEnum[mapCounter.get(stage).get(FIRST_ROOM)]);
            for (int fr = 1; fr <= mapCounter.get(stage).get(FIRST_ROOM); fr++) {
                temp += mapChances.get(RoomEnum.valueOf("STAGE_"+stage+"_FIRST_"+fr));
                mapEnums.get(stage).get(FIRST_ROOM)[fr-1] = RoomEnum.valueOf("STAGE_"+stage+"_FIRST_"+fr);
            }
            mapChanceSums.get(stage).put(FIRST_ROOM, temp);
            temp = 0;

            mapEnums.get(stage).put(REGULAR_ROOM, new RoomEnum[mapCounter.get(stage).get(REGULAR_ROOM)]);
            for (int rr = 1; rr <= mapCounter.get(stage).get(REGULAR_ROOM); rr++) {
                temp += mapChances.get(RoomEnum.valueOf("STAGE_"+stage+"_REGULAR_"+rr));
                mapEnums.get(stage).get(REGULAR_ROOM)[rr-1] = RoomEnum.valueOf("STAGE_"+stage+"_REGULAR_"+rr);
            }
            mapChanceSums.get(stage).put(REGULAR_ROOM, temp);
            temp = 0;

            mapEnums.get(stage).put(BOSS_ROOM, new RoomEnum[mapCounter.get(stage).get(BOSS_ROOM)]);
            for (int br = 1; br <= mapCounter.get(stage).get(BOSS_ROOM); br++) {
                temp += mapChances.get(RoomEnum.valueOf("STAGE_"+stage+"_BOSS_"+br));
                mapEnums.get(stage).get(BOSS_ROOM)[br-1] = RoomEnum.valueOf("STAGE_"+stage+"_BOSS_"+br);
            }
            mapChanceSums.get(stage).put(BOSS_ROOM, temp);
        }



    }

    public static Stage createStage(int stage) {

        Stage stageResult = new Stage();

        int roomCount =
                (Math.abs(EpicDungeonTactics.random.nextInt()) %
                        (WorldConfig.STAGE_MAX_ROOMS - WorldConfig.STAGE_MIN_ROOMS))
                        + WorldConfig.STAGE_MIN_ROOMS;

        Array<Room> result = new Array<>();

        result.add(new Room(FIRST_ROOM, stage, new CoordsInt(0, 0), stageResult));

        //create rooms
        int generatedInLastLoop = 1;
        int generatedInThisLoop = 0;


        while (result.size < roomCount) {

            for (int j = 0; j < generatedInLastLoop; j++) { //loop for "newest" rooms

                CoordsInt tempCoords = result.get(result.size - generatedInLastLoop + j).getPosition();

                if (isFree(result, tempCoords.x - 1, tempCoords.y)) { //left
                    if (EpicDungeonTactics.random.nextFloat() <  ((Math.abs(tempCoords.y) % 2 == 0) ? 0.25f : 0f)) {
                        result.add(new Room(
                                result.size == roomCount - 1 ? BOSS_ROOM : REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x - 1, tempCoords.y),
                                stageResult));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }
                if (isFree(result, tempCoords.x + 1, tempCoords.y)) { //right
                    if (EpicDungeonTactics.random.nextFloat() < ((Math.abs(tempCoords.y) % 2 == 0) ? 0.25f : 0f)) {
                        result.add(new Room(
                                result.size == roomCount - 1 ? BOSS_ROOM : REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x + 1, tempCoords.y),
                                stageResult));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }
                if (isFree(result, tempCoords.x, tempCoords.y - 1)) { //bottom
                    if (EpicDungeonTactics.random.nextFloat() < ((Math.abs(tempCoords.x) % 2 == 0) ? 0.25f : 0f)) {
                        result.add(new Room(
                                result.size == roomCount - 1 ? BOSS_ROOM : REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x, tempCoords.y - 1),
                                stageResult));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }
                if (isFree(result, tempCoords.x, tempCoords.y + 1)) { //top
                    if (EpicDungeonTactics.random.nextFloat() < ((Math.abs(tempCoords.x) % 2 == 0) ? 0.25f : 0f)) {
                        result.add(new Room(
                                result.size == roomCount - 1 ? BOSS_ROOM : REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x, tempCoords.y + 1),
                                stageResult));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }

            }

            if (generatedInThisLoop == 0) {
                generatedInThisLoop = generatedInLastLoop;
            }
            else {
                generatedInLastLoop = generatedInThisLoop;
                generatedInThisLoop = 0;
            }

            if (result.size == roomCount) break;

        }

        //generate nodes

        CoordsInt currectCoords;
        for (int i = 0; i < roomCount; i++) {
            currectCoords = result.get(i).getPosition();
            for (int j = i + 1; j < roomCount; j++) {
                if (currectCoords.x - result.get(j).getPosition().x == -1
                        && currectCoords.y == result.get(j).getPosition().y) {

                    result.get(i).getRoomNodes().put(DirectionEnum.RIGHT, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.LEFT, result.get(i));
                    result.get(j).createNodes();
                } else if (currectCoords.x - result.get(j).getPosition().x == 1
                        && currectCoords.y == result.get(j).getPosition().y) {

                    result.get(i).getRoomNodes().put(DirectionEnum.LEFT, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.RIGHT, result.get(i));
                    result.get(j).createNodes();
                } else if (currectCoords.y - result.get(j).getPosition().y == -1
                        && currectCoords.x == result.get(j).getPosition().x) {

                    result.get(i).getRoomNodes().put(DirectionEnum.TOP, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.BOTTOM, result.get(i));
                    result.get(j).createNodes();
                } else if (currectCoords.y - result.get(j).getPosition().y == 1
                        && currectCoords.x == result.get(j).getPosition().x) {

                    result.get(i).getRoomNodes().put(DirectionEnum.BOTTOM, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.TOP, result.get(i));
                    result.get(j).createNodes();
                }
            }
            result.get(i).createNodes();
        }

        stageResult.setRooms(result);
        return stageResult;
    }

    private static boolean isFree(Array<Room> rooms, int x, int y) {
        int size = rooms.size;
        for (int i = 0; i < size; i++) {
            CoordsInt coords = rooms.get(i).getPosition();
            if (coords.x == x && coords.y == y) return false;
        }
        return true;
    }

    public static RoomEnum getRandomRoom(RoomTypeEnum type, int stage) {

        int temp = 0;
        int result = Math.abs(EpicDungeonTactics.random.nextInt()) % mapChanceSums.get(stage).get(type);

        for (int i=0; i<mapCounter.get(stage).get(type); i++) {
            temp += mapChances.get(mapEnums.get(stage).get(type)[i]);

            if (result <= temp) return mapEnums.get(stage).get(type)[i];
        }
        return mapEnums.get(stage).get(type)[0];

    }
}
