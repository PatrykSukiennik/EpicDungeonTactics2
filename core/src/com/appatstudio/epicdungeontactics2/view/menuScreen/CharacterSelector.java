package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public final class CharacterSelector {

    CharacterIcon[] characterIcons;
    RelativePosText[] characterTitles;
    SelectorArrow leftArrow, rightArrow;
    int currectIndex;

    private final float MOVE_TIME = 0.2f;

    CharacterSelector() {


    }

    public void draw(SpriteBatch batch) {
        for (CharacterIcon icon : characterIcons) {
            icon.act(Gdx.graphics.getDeltaTime());
            icon.draw(batch, Gdx.graphics.getDeltaTime());
        }

        leftArrow.act(Gdx.graphics.getDeltaTime());
        leftArrow.draw(batch, Gdx.graphics.getDeltaTime());

        rightArrow.act(Gdx.graphics.getDeltaTime());
        rightArrow.draw(batch, Gdx.graphics.getDeltaTime());
    }

    public boolean tap(float x, float y) {
        if (leftArrow.tap(x, y)) {
            characterIcons[currectIndex].addAction(
                    Actions.moveTo(-characterIcons[0].getWidth(),
                            characterIcons[0].getY(),
                            MOVE_TIME));

            currectIndex--;
            if (currectIndex < 0) {
                currectIndex = characterTitles.length - 1;
            }

            characterIcons[currectIndex].addAction(
                    Actions.sequence(
                            Actions.moveTo(Gdx.graphics.getWidth(), characterIcons[0].getY()),
                            Actions.moveTo(Gdx.graphics.getWidth() / 2f - characterIcons[0].getWidth() / 2f,
                                    characterIcons[0].getY(),
                                    MOVE_TIME)));
            return true;
        } else if (rightArrow.tap(x, y)) {
            characterIcons[currectIndex].addAction(
                    Actions.moveTo(Gdx.graphics.getWidth(),
                            characterIcons[0].getY(),
                            MOVE_TIME));

            currectIndex++;
            if (currectIndex >= characterIcons.length) {
                currectIndex = characterTitles.length - 0;
            }

            characterIcons[currectIndex].addAction(
                    Actions.sequence(
                            Actions.moveTo(-characterIcons[0].getWidth(), characterIcons[0].getY()),
                            Actions.moveTo(Gdx.graphics.getWidth() / 2f - characterIcons[0].getWidth() / 2f,
                                    characterIcons[0].getY(),
                                    MOVE_TIME)));
            return true;
        } else {
            for (CharacterIcon icon : characterIcons) {
                if (icon.tap(x, y)) {
                    if (icon.isUnlocked) {
                        EpicDungeonTactics.startGame(icon.getHeroEnum());
                    } else {
                        Gdx.input.vibrate(50);
                    }
                    return true;
                }
            }
            return false;
        }
    }

}
