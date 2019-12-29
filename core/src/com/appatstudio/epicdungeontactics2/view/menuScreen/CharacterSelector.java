package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.characters.HeroStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

public final class CharacterSelector {

    private CharacterIcon[] characterIcons;
    private SelectorArrow leftArrow, rightArrow;
    private int currectIndex = 0;
    private TextWithIcon goldStatus, statsButton;
    private ButtonWithText startButton, unlockButton;

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
                Gdx.graphics.getWidth() / 2f - characterIcons[0].getWidth() / 2f,
                characterIcons[0].getY());

        leftArrow = new SelectorArrow(DirectionEnum.LEFT);
        rightArrow = new SelectorArrow(DirectionEnum.RIGHT);

        startButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.25f,
                Gdx.graphics.getWidth() * 0.34f,
                Gdx.graphics.getWidth() * 0.5f,
                Gdx.graphics.getWidth() * 0.15f,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.SELECT));

        unlockButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.25f,
                Gdx.graphics.getWidth() * 0.34f,
                Gdx.graphics.getWidth() * 0.5f,
                Gdx.graphics.getWidth() * 0.15f,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.UNLOCK));

        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.left
        );

        statsButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.STATS_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STATS),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.right
        );
    }

    private void moveRight() {
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
    }

    private void moveLeft() {
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
    }

    void swiped(DirectionEnum directionEnum) {
        switch (directionEnum) {
            case RIGHT:
                moveLeft();
                break;
            case LEFT:
                moveRight();
                break;
        }
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

        goldStatus.draw(batch);
        statsButton.draw(batch);

        if (characterIcons[currectIndex].isUnlocked) startButton.draw(batch, 1f);
        else if (characterIcons[currectIndex].canBeUnlocked()) unlockButton.draw(batch, 1f);
    }

    public boolean tap(float x, float y) {
        if (leftArrow.tap(x, y)) {
            moveLeft();
            return true;
        } else if (rightArrow.tap(x, y)) {
            moveRight();
            return true;
        } else if (statsButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.STATS_SCREEN);
            return true;
        } else {
            if (characterIcons[currectIndex].isUnlocked) {
                if (startButton.tap(x, y)) {
                    EpicDungeonTactics.setSelectedHero(characterIcons[currectIndex].getCharacterEnum());
                    EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.PERK_SCREEN);
                    return true;
                }
            } else if (characterIcons[currectIndex].canBeUnlocked()) {
                if (unlockButton.tap(x, y)) {
                    SavedInfoManager.unlock(characterIcons[currectIndex].getCharacterEnum());
                    GlobalValues.minusGold(HeroStats.getBuyCost(characterIcons[currectIndex].getCharacterEnum()));
                    CharacterEnum unlockedEnum = characterIcons[currectIndex].getCharacterEnum();
                    characterIcons[currectIndex] = new CharacterIcon(unlockedEnum);
                    characterIcons[currectIndex].moveToCenterNow();
                    return true;
                }

            }
            return false;
        }
    }

}
