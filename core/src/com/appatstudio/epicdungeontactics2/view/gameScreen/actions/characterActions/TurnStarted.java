package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.scenes.scene2d.Action;

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
