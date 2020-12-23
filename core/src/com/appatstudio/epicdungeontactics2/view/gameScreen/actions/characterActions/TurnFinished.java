package com.appatstudio.epicdungeontactics2.view.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.scenes.scene2d.Action;

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
