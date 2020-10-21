package com.appatstudio.epicdungeontactics2.view.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.scenes.scene2d.Action;

public class SwitchMapTile extends Action {

    private MapTile oldTile;
    private MapTile newTile;
    private CharacterDrawable characterDrawable;

    public SwitchMapTile(MapTile oldTile, MapTile newTile, CharacterDrawable characterDrawable) {
        this.oldTile = oldTile;
        this.newTile = newTile;
        this.characterDrawable = characterDrawable;
    }

    @Override
    public boolean act(float delta) {
        if (oldTile != null) oldTile.setCharacter(null, false);
        newTile.setCharacter(characterDrawable, true);
        this.characterDrawable.setPosition(newTile.getPositionInt());
        return true;
    }
}
