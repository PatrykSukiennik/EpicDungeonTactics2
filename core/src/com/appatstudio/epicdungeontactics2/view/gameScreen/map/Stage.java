package com.appatstudio.epicdungeontactics2.view.gameScreen.map;

import com.badlogic.gdx.utils.Array;

public class Stage {

    private Array<Room> rooms;
    private Room firstRoom;

    public Stage(Array<Room> rooms) {
        this.rooms = rooms;
        this.firstRoom = rooms.get(0);
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public Array<Room> getRooms() {
        return rooms;
    }

    public void disposeStage() {
        for (Room r : rooms) r.disposeRoom();
    }
}
