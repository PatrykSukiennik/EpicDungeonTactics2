package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.enums.RoomStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.gameScreen.playerStatus.Backpack;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.BOTTOM;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.LEFT;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.RIGHT;
import static com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum.TOP;

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
}
