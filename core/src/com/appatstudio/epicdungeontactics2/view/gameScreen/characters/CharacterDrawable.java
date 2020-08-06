package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class CharacterDrawable extends Actor {

    private final Animation<SpriteDrawable> idleAnimation;
    private final Animation<SpriteDrawable> runAnimation;
    private float stateTime;

    public CharacterDrawable(CharacterEnum characterEnum, int size) {
        idleAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.IDLE);
        runAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.RUN);

        stateTime = EpicDungeonTactics.random.nextFloat();

    }

    public void draw(Batch batch, CharacterStateEnum stateEnum) {
        super.act(Gdx.graphics.getDeltaTime());

        stateTime += Gdx.graphics.getDeltaTime();

        if (stateEnum == CharacterStateEnum.IDLE) {
            idleAnimation.getKeyFrame(stateTime).draw(batch, super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }
        else {
            runAnimation.getKeyFrame(stateTime).draw(batch, super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }
    }

}
