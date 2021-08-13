package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.badlogic.gdx.scenes.scene2d.Action;

public class RefreshShotableTiles extends Action {

    CharacterDrawable characterDrawable;
    Room room;

    public RefreshShotableTiles(CharacterDrawable characterDrawable, Room room) {
        this.characterDrawable = characterDrawable;
        this.room = room;
    }

    @Override
    public boolean act(float delta) {
        room.getShotInfo(characterDrawable);
        return true;
    }
}