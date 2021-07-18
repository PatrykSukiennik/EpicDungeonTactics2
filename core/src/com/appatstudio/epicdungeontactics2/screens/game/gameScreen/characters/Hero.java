package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
    public void draw(Batch mapBatch) {
        super.draw(mapBatch);

        if (StatTracker.getProjectile().hasActions()) {
            StatTracker.getProjectile().act(Gdx.graphics.getDeltaTime());
            StatTracker.getProjectile().draw(mapBatch, 1f);
        }
    }

    @Override
    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(getCharacterEnum());
    }

    @Override
    public void setPosition(CoordsInt coords) {
        super.setPosition(coords);
        setTileStandingOn(room.getTiles()[coords.x][coords.y], true);

        GuiContainer.setItemsToPick(null);
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

    @Override
    public int getShotDamage() {
        return (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.BOW_DMG);
    }

    @Override
    public Image getProjectile() {
        return StatTracker.getProjectile();
    }
}
