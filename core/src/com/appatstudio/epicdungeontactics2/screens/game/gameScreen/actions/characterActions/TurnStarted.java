package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;

public class TurnStarted extends AbstractCharacterAction {
    private final Room room;

    public TurnStarted(Room room) {
        this.room = room;
    }

    @Override
    public boolean act(float delta) {
        room.moveStarted();
        return true;
    }
}
