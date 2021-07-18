package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.MapTile;
import com.badlogic.gdx.scenes.scene2d.Action;

public class RemoveFromTile extends Action {

    private MapTile tile;

    public RemoveFromTile(MapTile tile) {
        this.tile = tile;
    }

    @Override
    public boolean act(float delta) {
        tile.setCharacter(null, false);
        return true;
    }
}