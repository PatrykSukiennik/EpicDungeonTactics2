package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.BodyConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightConfigObject;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.MoveToMapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.view.viewElements.GuiButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class CharacterDrawable extends Image {

    private final Animation<SpriteDrawable> idleAnimation;
    private final Animation<SpriteDrawable> runAnimation;

    private CharacterEnum characterEnum;

    private CharacterStateEnum state;
    private float stateTime;
    private CoordsInt position;

    private PointLight pointLight;
    private Body body;

    private float actionTime;

    protected Room room;
    private Array<Array<MapTile>> possibleMovements;

    protected CharacterStatsObject stats;

    private CoordsFloat bodyOffset;
    private CoordsFloat lightOffset;

    private MapTile tileStandingOn;
    private MapTile targetTile;

    private boolean isRotation = EpicDungeonTactics.random.nextBoolean();

    public CharacterDrawable(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world, Room room, MapTile tile, boolean isRotation) {
        idleAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.IDLE);
        runAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.RUN);

        stateTime = EpicDungeonTactics.random.nextFloat();

        this.characterEnum = characterEnum;
        this.position = position;
        this.state = CharacterStateEnum.IDLE;
        this.room = room;
        this.isRotation = isRotation;

        int size = CharacterStats.getCharacterSize(characterEnum);
        CoordsFloat coords = WorldConfig.getDrawingCoord(position.x, position.y);
        if (size > 1) {
            coords.x -= (size/4f) * WorldConfig.TILE_SIZE;
        }
        this.setPosition(coords.x, coords.y);
        this.setSize(2f * WorldConfig.TILE_SIZE, 2f * WorldConfig.TILE_SIZE);

        LightConfigObject lightConfigObject = LightsConfig.getCharacterLights(characterEnum);
        System.out.println(characterEnum.toString());
        lightOffset = lightConfigObject.getOffset();
        this.pointLight = new PointLight(
                rayHandler,
                LightsConfig.CHARACTER_RAYS,
                lightConfigObject.getColor(),
                lightConfigObject.getRadius(),
                //this.getX() + lightConfigObject.getOffset().x,
                //this.getY() + lightConfigObject.getOffset().y
                this.getX() + this.getWidth()/2f,
                this.getY() + this.getHeight()/2f
        );

        if (BodyConfig.getCharacterBodyDef(characterEnum) != null) {
            this.body = world.createBody(BodyConfig.getCharacterBodyDef(characterEnum));
            this.body.createFixture(BodyConfig.getCharacterFixtureDef(characterEnum));
            bodyOffset = new CoordsFloat((size * WorldConfig.TILE_SIZE) + ((int)(size/2f)*(-1) * WorldConfig.TILE_SIZE), (size * WorldConfig.TILE_SIZE)/2f);
            this.body.setTransform(this.getX() + bodyOffset.x, this.getY() + bodyOffset.y, 0);
            pointLight.attachToBody(this.body);
        }

        createStatsObject();

        this.tileStandingOn = tile;


    }

    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(characterEnum);
    }

    public void setState(CharacterStateEnum state) {
        this.state = state;
    }

    public void draw(Batch mapBatch) {
        super.act(Gdx.graphics.getDeltaTime());

        if (hasActions()) {
            this.body.setTransform(this.getX() + bodyOffset.x, this.getY() + bodyOffset.y, 0);
            //this.pointLight.setPosition(getX() + lightOffset.x, getY() + lightOffset.y);
        }

        stateTime += Gdx.graphics.getDeltaTime();
        //System.out.println(characterEnum.toString() + "  " + idleAnimation.getFrameDuration());
        if (state == CharacterStateEnum.IDLE) {
            this.setDrawable(idleAnimation.getKeyFrame(stateTime));
        }
        else {
            this.setDrawable(runAnimation.getKeyFrame(stateTime));
        }

        Sprite s = ((SpriteDrawable) this.getDrawable()).getSprite();
        if (isRotation) {
            if (!s.isFlipX()) s.flip(true, false);
        }
        else {
            if (s.isFlipX()) s.flip(true, false);
        }

        this.draw(mapBatch, 1f);

    }

    public CoordsInt getPosition() {
        return position;
    }

    public void dead() {
        pointLight.remove(true);
    }

    public void dispose() {
        pointLight.remove(true);
    }

    public void setTileStandingOn(MapTile tileStandingOn, boolean heroFlag) {
        this.tileStandingOn = tileStandingOn;
        if (heroFlag) {
            //GuiContainer.setItemsToPick(tileStandingOn.getItemsToPick());
        }
    }

    public void moveToMapTile(MapTile tile) {
        MoveToMapTile way = room.getWay(possibleMovements, tile, this);
        this.addAction(way.getSequenceAction());
        this.state = CharacterStateEnum.RUN;
        this.actionTime = way.getDuration();
        this.targetTile = tile;
    }

    public boolean isReady() {
        return !this.hasActions();
    }

    public void getPossibleMovement() {

    }

    public float getActionTime() {
        return actionTime;
    }

    public void setPosition(CoordsInt coords) {
        if (coords.x < position.x) isRotation = true;
        else if (coords.x > position.x) isRotation = false;

        this.position = coords;

        for (int i=CharacterStats.getCharacterSize(characterEnum)-1; i>=0; i--) {
            tileStandingOn = room.getTiles()[coords.x + i][coords.y];
            tileStandingOn.setCharacter(this, i==0);
        }
    }

    public void setPositionForce(CoordsInt coords) {
        if (coords.x < WorldConfig.ROOM_WIDTH/2f) isRotation = false;
        else if (coords.x > WorldConfig.ROOM_WIDTH/2f) isRotation = true;

        this.position = coords;
        CoordsFloat newCoords = WorldConfig.getDrawingCoord(position.x, position.y);
        this.setPosition(newCoords.x, newCoords.y);
        this.body.setTransform(this.getX() + bodyOffset.x, this.getY() + bodyOffset.y, 0);

        for (int i=CharacterStats.getCharacterSize(characterEnum)-1; i>=0; i--) {
            tileStandingOn = room.getTiles()[coords.x + i][coords.y];
            tileStandingOn.setCharacter(this, i==0);
        }


    }

    public void setPossibleMovements(Array<Array<MapTile>> possibleMovements) {
        this.possibleMovements = possibleMovements;
    }

    public void getPossibleWays() {
        //override me
    }

    public void getPossibleWays(int range) {

    }

    public CharacterStatsObject getStats() {
        return stats;
    }

    public MapTile getTileStandingOn() {
        return tileStandingOn;
    }

    public CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    public void drawTop(Batch guiBatch) {
        //override me
    }

    protected CharacterStateEnum getState() {
        return this.state;
    }

    protected boolean isOnTarget() {
        return this.getTileStandingOn() == targetTile;
    }


    public int getSpeed() {
        return stats.getSpeed();
    }
}
