package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.viewElements.game.EnemyStatBar;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class Enemy extends CharacterDrawable {

    private EnemyStatBar statBar;

    public Enemy(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world, Room room, MapTile tile) {
        super(characterEnum, position, rayHandler, world, room, tile);
    }

    @Override
    public void getPossibleWays() {
        this.setPossibleMovements(room.findWays(this.getPosition(), stats.getSpeed()));
    }

    @Override
    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(getCharacterEnum());
    }

    @Override
    public void draw(Batch mapBatch, Batch guiBatch) {
        super.draw(mapBatch, guiBatch);

        statBar.draw(guiBatch);
    }

    @Override
    public void drawTop(Batch guiBatch) {
        super.drawTop(guiBatch);
        statBar.draw(guiBatch);
    }
}
