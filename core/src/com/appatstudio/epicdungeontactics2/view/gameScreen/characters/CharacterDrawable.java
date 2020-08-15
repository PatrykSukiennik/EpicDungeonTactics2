package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class CharacterDrawable extends Image {

    private final Animation<SpriteDrawable> idleAnimation;
    private final Animation<SpriteDrawable> runAnimation;

    private CharacterStateEnum state;
    private float stateTime;
    private CoordsInt position;

    public CharacterDrawable(CharacterEnum characterEnum, CoordsInt position) {
        idleAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.IDLE);
        runAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.RUN);

        stateTime = EpicDungeonTactics.random.nextFloat();

        this.position = position;
        this.state = CharacterStateEnum.IDLE;

        CoordsFloat coords = WorldConfig.getTileCoord(position.x, position.y);
        this.setPosition(coords.x, coords.y);
        int size = CharacterStats.getCharacterSize(characterEnum);
        this.setSize(2f * size * WorldConfig.TILE_SIZE, 2f * size * WorldConfig.TILE_SIZE);
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
}
