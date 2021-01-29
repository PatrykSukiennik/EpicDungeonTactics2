package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class Hero extends CharacterDrawable {
    public Hero(CharacterEnum characterEnum, RayHandler handler, World world, Room room, MapTile tile) {
        super(characterEnum, new CoordsInt(0, 0), handler, world, room, tile, false);
        isHero = true;
    }

    public Hero(CharacterEnum characterEnum, CoordsInt coords, RayHandler handler, World world, Room room, MapTile tile) {
        super(characterEnum, coords, handler, world, room, tile, false);
        isHero = true;
    }

    @Override
    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(getCharacterEnum());
    }

    @Override
    public void setPosition(CoordsInt coords) {
        super.setPosition(coords);
        setTileStandingOn(room.getTiles()[coords.x][coords.y], true);
        if (this.isOnTarget()) GuiContainer.setItemsToPick(room.getTiles()[coords.x][coords.y].getItemsToPick());
    }

    @Override
    public int getMeleDamage() {
        return (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.MELE_DMG);
    }

    @Override
    public void dmgGet(int dmg) {
        StatTracker.hpEffect(-dmg);
    }
}
