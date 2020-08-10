package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomTypeEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Stage;
import com.badlogic.gdx.utils.Array;

public class MapGenerator {

    public static Stage createStage(int stage) {

        int roomCount =
                (EpicDungeonTactics.random.nextInt() %
                        (WorldConfig.STAGE_MAX_ROOMS - WorldConfig.STAGE_MIN_ROOMS))
                        + WorldConfig.STAGE_MIN_ROOMS;

        Array<Room> result = new Array<>();

        result.add(new Room(RoomTypeEnum.FIRST_ROOM, stage, new CoordsInt(0, 0)));

        //create rooms
        int generatedInLastLoop = 1;
        int generatedInThisLoop = 0;

        System.out.println("Room count: " + roomCount);

        for (int i = 1; i < roomCount; i++) {

            for (int j = 0; j < generatedInLastLoop; j++) { //loop for "newest" rooms

                CoordsInt tempCoords = result.get(result.size - generatedInLastLoop + j).getPosition();

                if (isFree(result, tempCoords.x - 1, tempCoords.y)) { //left
                    if (EpicDungeonTactics.random.nextFloat() < 0.4f) {
                        result.add(new Room(
                                result.size == roomCount ? RoomTypeEnum.BOSS_ROOM : RoomTypeEnum.REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x - 1, tempCoords.y)));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }
                if (isFree(result, tempCoords.x + 1, tempCoords.y)) { //right
                    if (EpicDungeonTactics.random.nextFloat() < 0.4f) {
                        result.add(new Room(
                                result.size == roomCount ? RoomTypeEnum.BOSS_ROOM : RoomTypeEnum.REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x + 1, tempCoords.y)));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }
                if (isFree(result, tempCoords.x, tempCoords.y - 1)) { //bottom
                    if (EpicDungeonTactics.random.nextFloat() < 0.4f) {
                        result.add(new Room(
                                result.size == roomCount ? RoomTypeEnum.BOSS_ROOM : RoomTypeEnum.REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x, tempCoords.y - 1)));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }
                if (isFree(result, tempCoords.x, tempCoords.y + 1)) { //top
                    if (EpicDungeonTactics.random.nextFloat() < 0.4f) {
                        result.add(new Room(
                                result.size == roomCount ? RoomTypeEnum.BOSS_ROOM : RoomTypeEnum.REGULAR_ROOM,
                                stage,
                                new CoordsInt(tempCoords.x, tempCoords.y + 1)));

                        if (result.size == roomCount) break;
                        generatedInThisLoop++;
                    }
                }

            }

            if (generatedInThisLoop == 0) i--;
            else {
                generatedInLastLoop = generatedInThisLoop;
                generatedInThisLoop = 0;
            }

            if (result.size == roomCount) break;

        }
        System.out.println("Size: " + result.size);

        //generate nodes

        CoordsInt currectCoords;
        for (int i = 0; i < roomCount; i++) {
            currectCoords = result.get(i).getPosition();
            for (int j = i + 1; j < roomCount; j++) {
                if (currectCoords.x - result.get(j).getPosition().x == -1) {
                    result.get(i).getRoomNodes().put(DirectionEnum.RIGHT, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.LEFT, result.get(i));
                } else if (currectCoords.x - result.get(j).getPosition().x == 1) {
                    result.get(i).getRoomNodes().put(DirectionEnum.LEFT, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.RIGHT, result.get(i));
                } else if (currectCoords.y - result.get(j).getPosition().y == -1) {
                    result.get(i).getRoomNodes().put(DirectionEnum.TOP, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.BOTTOM, result.get(i));
                } else if (currectCoords.y - result.get(j).getPosition().y == 1) {
                    result.get(i).getRoomNodes().put(DirectionEnum.BOTTOM, result.get(j));
                    result.get(j).getRoomNodes().put(DirectionEnum.TOP, result.get(i));
                }
            }
        }

        return new Stage(result);
    }

    private static boolean isFree(Array<Room> rooms, int x, int y) {
        int size = rooms.size;
        for (int i = 0; i < size; i++) {
            CoordsInt coords = rooms.get(i).getPosition();
            if (coords.x == x && coords.y == y) return false;
        }
        return true;
    }

}
