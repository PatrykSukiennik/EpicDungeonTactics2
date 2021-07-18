package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;

public class TurnFinished extends AbstractCharacterAction {

    private final Room room;

    public TurnFinished(Room room) {
        this.room = room;
    }

    @Override
    public boolean act(float delta) {
        room.moveFinished();
        return true;
    }

}
