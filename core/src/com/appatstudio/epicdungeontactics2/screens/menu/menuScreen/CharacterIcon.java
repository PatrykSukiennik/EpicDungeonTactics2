package com.appatstudio.epicdungeontactics2.screens.menu.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public final class CharacterIcon extends Image {

    private final Animation<SpriteDrawable> animation;
    private float stateTime = 0f;

    CharacterIcon(CharacterEnum character) {
        super(GraphicsManager.getCharactersAnimation(character, CharacterStateEnum.IDLE).getKeyFrame(0));

        this.animation = GraphicsManager.getGuiHeroAnimation(character, CharacterStateEnum.IDLE);
    }

    public void draw(Batch batch, float parentAlpha, boolean isUnlocked) {
        super.draw(batch, parentAlpha);

        if (isUnlocked) stateTime += Gdx.graphics.getDeltaTime();
        this.setDrawable(animation.getKeyFrame(stateTime));

    }
}
