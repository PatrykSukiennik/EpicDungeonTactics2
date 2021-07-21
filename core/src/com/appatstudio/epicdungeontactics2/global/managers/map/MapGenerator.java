package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.MusicEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Stage;
import com.badlogic.gdx.utils.Array;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.BIG_ZOMBIE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.GOBLIN;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ICE_ZOMBIE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MASKED_ORC;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.MUDDY;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ORC_SHAMAN;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ORC_WARRIOR;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.SWAMPY;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.TINY_ZOMBIE;
import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.ZOMBIE;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomEnum.*;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum.BOSS_ROOM;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum.FIRST_ROOM;
import static com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum.REGULAR_ROOM;

public class MapGenerator {

    private static final HashMap<Integer, HashMap<RoomTypeEnum, Integer>> mapCounter;
    private static final HashMap<Integer, HashMap<RoomTypeEnum, Integer>> mapChanceSums;
    private static final HashMap<Integer, HashMap<RoomTypeEnum, RoomEnum[]>> mapEnums;
    private static final HashMap<Integer, Float> enemiesFactor;
    private static final HashMap<RoomEnum, Integer> mapChances;
    private static final HashMap<Integer, CharacterEnum[]> stageEnemies;

    static {
        mapCounter = new HashMap<>();
        mapCounter.put(1, new HashMap<RoomTypeEnum, Integer>());
        mapCounter.put(2, new HashMap<RoomTypeEnum, Integer>());
        mapCounter.put(3, new HashMap<RoomTypeEnum, Integer>());

        enemiesFactor = new HashMap<>();
        enemiesFactor.put(1, 0.7f);
        enemiesFactor.put(2, 0.3f);
        enemiesFactor.put(3, 0.6f);
        enemiesFactor.put(4, 1f);
        enemiesFactor.put(5, 0.3f);
        enemiesFactor.put(6, 0.7f);
        enemiesFactor.put(7, 1f);

        mapChances = new HashMap<>();

//python-include-map-counter
mapCounter.get(1).put(FIRST_ROOM, 4);
mapCounter.get(1).put(REGULAR_ROOM, 20);
mapCounter.get(1).put(BOSS_ROOM, 4);
mapCounter.get(2).put(FIRST_ROOM, 4);
mapCounter.get(2).put(REGULAR_ROOM, 20);
mapCounter.get(2).put(BOSS_ROOM, 4);
mapCounter.get(3).put(FIRST_ROOM, 4);
mapCounter.get(3).put(REGULAR_ROOM, 20);
mapCounter.get(3).put(BOSS_ROOM, 4);
//python-include-map-counter-end

//python-include-map-chances
mapChances.put(STAGE_1_FIRST_1, 100);
mapChances.put(STAGE_1_FIRST_2, 100);
mapChances.put(STAGE_1_FIRST_3, 100);
mapChances.put(STAGE_1_FIRST_4, 100);
mapChances.put(STAGE_1_REGULAR_1, 100);
mapChances.put(STAGE_1_REGULAR_2, 100);
mapChances.put(STAGE_1_REGULAR_3, 100);
mapChances.put(STAGE_1_REGULAR_4, 100);
mapChances.put(STAGE_1_REGULAR_5, 100);
mapChances.put(STAGE_1_REGULAR_6, 100);
mapChances.put(STAGE_1_REGULAR_7, 100);
mapChances.put(STAGE_1_REGULAR_8, 100);
mapChances.put(STAGE_1_REGULAR_9, 100);
mapChances.put(STAGE_1_REGULAR_10, 100);
mapChances.put(STAGE_1_REGULAR_11, 100);
mapChances.put(STAGE_1_REGULAR_12, 100);
mapChances.put(STAGE_1_REGULAR_13, 100);
mapChances.put(STAGE_1_REGULAR_14, 100);
mapChances.put(STAGE_1_REGULAR_15, 100);
mapChances.put(STAGE_1_REGULAR_16, 100);
mapChances.put(STAGE_1_REGULAR_17, 100);
mapChances.put(STAGE_1_REGULAR_18, 100);
mapChances.put(STAGE_1_REGULAR_19, 100);
mapChances.put(STAGE_1_REGULAR_20, 100);
mapChances.put(STAGE_1_BOSS_1, 100);
mapChances.put(STAGE_1_BOSS_2, 100);
mapChances.put(STAGE_1_BOSS_3, 100);
mapChances.put(STAGE_1_BOSS_4, 100);
mapChances.put(STAGE_2_FIRST_1, 100);
mapChances.put(STAGE_2_FIRST_2, 100);
mapChances.put(STAGE_2_FIRST_3, 100);
mapChances.put(STAGE_2_FIRST_4, 100);
mapChances.put(STAGE_2_REGULAR_1, 100);
mapChances.put(STAGE_2_REGULAR_2, 100);
mapChances.put(STAGE_2_REGULAR_3, 100);
mapChances.put(STAGE_2_REGULAR_4, 100);
mapChances.put(STAGE_2_REGULAR_5, 100);
mapChances.put(STAGE_2_REGULAR_6, 100);
mapChances.put(STAGE_2_REGULAR_7, 100);
mapChances.put(STAGE_2_REGULAR_8, 100);
mapChances.put(STAGE_2_REGULAR_9, 100);
mapChances.put(STAGE_2_REGULAR_10, 100);
mapChances.put(STAGE_2_REGULAR_11, 100);
mapChances.put(STAGE_2_REGULAR_12, 100);
mapChances.put(STAGE_2_REGULAR_13, 100);
mapChances.put(STAGE_2_REGULAR_14, 100);
mapChances.put(STAGE_2_REGULAR_15, 100);
mapChances.put(STAGE_2_REGULAR_16, 100);
mapChances.put(STAGE_2_REGULAR_17, 100);
mapChances.put(STAGE_2_REGULAR_18, 100);
mapChances.put(STAGE_2_REGULAR_19, 100);
mapChances.put(STAGE_2_REGULAR_20, 100);
mapChances.put(STAGE_2_BOSS_1, 100);
mapChances.put(STAGE_2_BOSS_2, 100);
mapChances.put(STAGE_2_BOSS_3, 100);
mapChances.put(STAGE_2_BOSS_4, 100);
mapChances.put(STAGE_3_FIRST_1, 100);
mapChances.put(STAGE_3_FIRST_2, 100);
mapChances.put(STAGE_3_FIRST_3, 100);
mapChances.put(STAGE_3_FIRST_4, 100);
mapChances.put(STAGE_3_REGULAR_1, 100);
mapChances.put(STAGE_3_REGULAR_2, 100);
mapChances.put(STAGE_3_REGULAR_3, 100);
mapChances.put(STAGE_3_REGULAR_4, 100);
mapChances.put(STAGE_3_REGULAR_5, 100);
mapChances.put(STAGE_3_REGULAR_6, 100);
mapChances.put(STAGE_3_REGULAR_7, 100);
mapChances.put(STAGE_3_REGULAR_8, 100);
mapChances.put(STAGE_3_REGULAR_9, 100);
mapChances.put(STAGE_3_REGULAR_10, 100);
mapChances.put(STAGE_3_REGULAR_11, 100);
mapChances.put(STAGE_3_REGULAR_12, 100);
mapChances.put(STAGE_3_REGULAR_13, 100);
mapChances.put(STAGE_3_REGULAR_14, 100);
mapChances.put(STAGE_3_REGULAR_15, 100);
mapChances.put(STAGE_3_REGULAR_16, 100);
mapChances.put(STAGE_3_REGULAR_17, 100);
mapChances.put(STAGE_3_REGULAR_18, 100);
mapChances.put(STAGE_3_REGULAR_19, 100);
mapChances.put(STAGE_3_REGULAR_20, 100);
mapChances.put(STAGE_3_BOSS_1, 100);
mapChances.put(STAGE_3_BOSS_2, 100);
mapChances.put(STAGE_3_BOSS_3, 100);
mapChances.put(STAGE_3_BOSS_4, 100);
//python-include-map-chances-end

        stageEnemies = new HashMap<>();
        stageEnemies.put(1, new CharacterEnum[]{ TINY_ZOMBIE, ZOMBIE, ICE_ZOMBIE, MUDDY, BIG_ZOMBIE, SWAMPY, ORC_SHAMAN, ORC_WARRIOR, GOBLIN, MASKED_ORC });
        stageEnemies.put(2, new CharacterEnum[]{});
        stageEnemies.put(3, new CharacterEnum[]{});

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
                    if (EpicDungeonTactics.random.nextFloat() <  ((Math.abs(tempCoords.y) % 2 == 0) ? 0.35f : 0f)) {
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
                    if (EpicDungeonTactics.random.nextFloat() < ((Math.abs(tempCoords.y) % 2 == 0) ? 0.35f : 0f)) {
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
                    if (EpicDungeonTactics.random.nextFloat() < ((Math.abs(tempCoords.x) % 2 == 0) ? 0.35f : 0f)) {
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
                    if (EpicDungeonTactics.random.nextFloat() < ((Math.abs(tempCoords.x) % 2 == 0) ? 0.35f : 0f)) {
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

        int mapStage = stage == 1 ? 1 : stage <= 4 ? 2 : 3;

        for (int i=0; i<mapCounter.get(mapStage).get(type); i++) {
            temp += mapChances.get(mapEnums.get(mapStage).get(type)[i]);

            if (result <= temp) return mapEnums.get(mapStage).get(type)[i];
        }
        return mapEnums.get(mapStage).get(type)[0];

    }

    public static float getEnemiesFactor(int stage) {
        return stage <= enemiesFactor.size() ? enemiesFactor.get(stage) : 1f;
    }

    public static MusicEnum nextMusic(int stage) {
        return stage <= 4 ? MusicEnum.STAGE_2_MUSIC : MusicEnum.STAGE_3_MUSIC;
    }

    public static SoundEnum nextStepSound(int stage) {
        return stage <= 4 ? SoundEnum.STAGE_2_STEP : SoundEnum.STAGE_3_STEP;
    }

    public static CharacterEnum getRandomEnemy(int stage) {
        if (stage == 1) {
            return stageEnemies.get(1)[Math.abs(EpicDungeonTactics.random.nextInt()) % stageEnemies.get(1).length];
        } else if (stage <= 4) {
            return stageEnemies.get(2)[Math.abs(EpicDungeonTactics.random.nextInt()) % stageEnemies.get(2).length];
        } else {
            return stageEnemies.get(3)[Math.abs(EpicDungeonTactics.random.nextInt()) % stageEnemies.get(3).length];
        }
    }
}
