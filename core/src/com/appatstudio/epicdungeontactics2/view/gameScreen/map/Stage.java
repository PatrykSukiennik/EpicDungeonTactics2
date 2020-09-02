package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.badlogic.gdx.utils.Array;

import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.LEFT;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.RIGHT;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.TOP;

public class Stage {

    private Array<Room> rooms;
    private Room firstRoom;

    public Stage(Array<Room> rooms) {
        this.rooms = rooms;
        this.firstRoom = rooms.get(0);
    }

    public Stage() {}

    public void setRooms(Array<Room> rooms) {
        this.rooms = rooms;
        this.firstRoom = rooms.get(0);
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public Array<Room> getRooms() {
        return rooms;
    }

    public void changeRoom(DirectionEnum dir, Room newRoom, CoordsInt heroCoords) {
        if (dir == LEFT) newRoom.heroMovedIntoRoom(LEFT, heroCoords);
        if (dir == TOP) newRoom.heroMovedIntoRoom(TOP, heroCoords);
        if (dir == LEFT) newRoom.heroMovedIntoRoom(LEFT, heroCoords);
        if (dir == RIGHT) newRoom.heroMovedIntoRoom(RIGHT, heroCoords);
        GameScreen.switchRoomInit(dir);
    }

    public void disposeStage() {
        for (Room r : rooms) r.disposeRoom();
    }
}
