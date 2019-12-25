package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public final class CharacterSelector {

    CharacterIcon[] characterIcons;
    SelectorArrow leftArrow, rightArrow;
    int currectIndex = 0;

    private final float MOVE_TIME = 0.2f;

    CharacterSelector() {

        characterIcons = new CharacterIcon[]{
            new CharacterIcon(CharacterEnum.HERO_ELF),
            new CharacterIcon(CharacterEnum.HERO_KNIGHT),
            new CharacterIcon(CharacterEnum.HERO_WIZZARD),
            new CharacterIcon(CharacterEnum.HERO_LIZARD),
            new CharacterIcon(CharacterEnum.HERO_NINJA),
            new CharacterIcon(CharacterEnum.HERO_PIRATE),
            new CharacterIcon(CharacterEnum.HERO_BABY)
        };

        characterIcons[currectIndex].setPosition(
                        Gdx.graphics.getWidth()/2f - characterIcons[0].getWidth()/2f,
                        characterIcons[0].getY());

        leftArrow = new SelectorArrow(DirectionEnum.LEFT);
        rightArrow = new SelectorArrow(DirectionEnum.RIGHT);

    }

    public void draw(SpriteBatch batch) {
        for (CharacterIcon icon : characterIcons) {
            icon.act(Gdx.graphics.getDeltaTime());
            icon.draw(batch, 1f);
        }

        leftArrow.act(Gdx.graphics.getDeltaTime());
        leftArrow.draw(batch, 1f);

        rightArrow.act(Gdx.graphics.getDeltaTime());
        rightArrow.draw(batch, 1f);

        System.out.println(currectIndex);
    }

    public boolean tap(float x, float y) {
        if (leftArrow.tap(x, y)) {
            characterIcons[currectIndex].addAction(
                    Actions.moveTo(Gdx.graphics.getWidth() + characterIcons[0].getWidth(),
                            characterIcons[0].getY(),
                            MOVE_TIME));

            currectIndex--;
            if (currectIndex < 0) {
                currectIndex = characterIcons.length - 1;
            }

            characterIcons[currectIndex].addAction(
                    Actions.sequence(
                            Actions.moveTo(-characterIcons[0].getWidth() - characterIcons[0].getWidth(), characterIcons[0].getY()),
                            Actions.moveTo(Gdx.graphics.getWidth() / 2f - characterIcons[0].getWidth() / 2f,
                                    characterIcons[0].getY(),
                                    MOVE_TIME)));
            return true;
        } else if (rightArrow.tap(x, y)) {
            characterIcons[currectIndex].addAction(
                    Actions.moveTo(-characterIcons[0].getWidth() - characterIcons[0].getWidth(),
                            characterIcons[0].getY(),
                            MOVE_TIME));

            currectIndex++;
            if (currectIndex >= characterIcons.length) {
                currectIndex = 0;
            }

            characterIcons[currectIndex].addAction(
                    Actions.sequence(
                            Actions.moveTo(Gdx.graphics.getWidth() + characterIcons[0].getWidth()
                                    , characterIcons[0].getY()),
                            Actions.moveTo(Gdx.graphics.getWidth() / 2f - characterIcons[0].getWidth() / 2f,
                                    characterIcons[0].getY(),
                                    MOVE_TIME)));
            return true;
        } else {
            for (CharacterIcon icon : characterIcons) {
                if (icon.tap(x, y)) {
                    if (icon.isUnlocked) {
                        EpicDungeonTactics.setSelectedHero(icon.getCharacterEnum());
                        MenuScreen.showPerkSelector();
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
