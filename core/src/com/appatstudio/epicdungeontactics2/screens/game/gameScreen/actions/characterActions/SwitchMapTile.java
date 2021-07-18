package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.MapTile;

public class SwitchMapTile extends AbstractCharacterAction {

    private final MapTile oldTile;
    private final MapTile newTile;
    private final CharacterDrawable characterDrawable;

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
