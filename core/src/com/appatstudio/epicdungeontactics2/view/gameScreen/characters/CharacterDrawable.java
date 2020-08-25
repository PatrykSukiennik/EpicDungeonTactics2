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
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
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

    private CharacterStateEnum state;
    private float stateTime;
    private CoordsInt position;

    private PointLight pointLight;
    private Body body;

    private float actionTime;

    private Array<Array<MapTile>> possibleMovements;

    public CharacterDrawable(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world) {
        idleAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.IDLE);
        runAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.RUN);

        stateTime = EpicDungeonTactics.random.nextFloat();

        this.position = position;
        this.state = CharacterStateEnum.IDLE;

        CoordsFloat coords = WorldConfig.getTileCoord(position.x, position.y);
        this.setPosition(coords.x, coords.y);
        int size = CharacterStats.getCharacterSize(characterEnum);
        this.setSize(2f * size * WorldConfig.TILE_SIZE, 2f * size * WorldConfig.TILE_SIZE);

        LightConfigObject lightConfigObject = LightsConfig.getCharacterLights(characterEnum);
        this.pointLight = new PointLight(
                rayHandler,
                LightsConfig.CHARACTER_RAYS,
                lightConfigObject.getColor(),
                lightConfigObject.getRadius(),
                this.getX() + lightConfigObject.getOffset().x,
                this.getY() + lightConfigObject.getOffset().y
                );

        if (BodyConfig.getCharacterBodyDef(characterEnum) != null) {
            this.body = world.createBody(BodyConfig.getCharacterBodyDef(characterEnum));
            this.body.createFixture(BodyConfig.getCharacterFixtureDef(characterEnum));
            this.body.setTransform(coords.x + (size * WorldConfig.TILE_SIZE) / 2f, coords.y + (size * WorldConfig.TILE_SIZE) / 2f, 0);
        }
    }

    public void setState(CharacterStateEnum state) {
        this.state = state;
    }

    public void draw(Batch batch) {
        super.act(Gdx.graphics.getDeltaTime());

        stateTime += Gdx.graphics.getDeltaTime();

        if (state == CharacterStateEnum.IDLE) {
            this.setDrawable(idleAnimation.getKeyFrame(stateTime));
        }
        else {
            this.setDrawable(runAnimation.getKeyFrame(stateTime));
        }

        this.draw(batch, 1f);

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

    public void moveToMapTile(MoveToMapTile way) {
        this.addAction(way.getSequenceAction());
        this.state = CharacterStateEnum.RUN;
        this.actionTime = way.getDuration();
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
        this.position = coords;
    }
}
