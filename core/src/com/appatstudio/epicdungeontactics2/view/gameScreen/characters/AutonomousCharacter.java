package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.viewElements.game.StatBar;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class AutonomousCharacter extends CharacterDrawable {

    private StatBar statBar;

    public AutonomousCharacter(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world, Room room, MapTile tile, boolean isRotated) {
        super(characterEnum, position, rayHandler, world, room, tile, isRotated);

        if (!characterEnum.toString().startsWith("NPC")) statBar = new StatBar(this);
    }

    @Override
    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(getCharacterEnum());
    }

    @Override
    public void draw(Batch mapBatch) {
        super.draw(mapBatch);
    }

    @Override
    public void drawTop(Batch guiBatch) {
        super.drawTop(guiBatch);
        if (statBar != null)statBar.draw(guiBatch);
    }
}
