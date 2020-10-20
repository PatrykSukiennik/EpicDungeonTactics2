package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public final class EquipmentWindow {

    private GameScreen gameScreen;

    private static boolean isUp = false;

    private static AbstractItem currItem = null;

    private static HeroSegment heroSegment;
    private static BackpackSegment backpackSegment;
    private static ItemSegment itemSegment;

    private static final float NOT_POSSIBLE_IN_FIGHT_TIMER = 2f;
    private static float notPossibleCommunicateTimer = -1;
    private static ButtonWithText notPossibleInFightCommunicate;

    public EquipmentWindow(CharacterEnum characterEnum, GameScreen gameScreen) {
        this.gameScreen = gameScreen;

        heroSegment = new HeroSegment(characterEnum);
        backpackSegment = new BackpackSegment(characterEnum);
        itemSegment = new ItemSegment(Gdx.graphics.getHeight() / 2f - AbstractSegment.getFullHeight() / 2f - AbstractSegment.fullHeight);

        notPossibleInFightCommunicate = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.WOODEN_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.1f,
                Gdx.graphics.getHeight() * 0.6f,
                Gdx.graphics.getWidth() * 0.8f,
                Gdx.graphics.getWidth() * 0.8f / 5f,
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.NOT_POSSIBLE_IN_FIGHT)
        );

        AbstractItem tempItem;
        Array<AbstractItem> items = StatTracker.getEqItems();
        for (AbstractItem item : items) {
            tempItem = item;
            pickItem(item);

            switch (tempItem.getItemTypeEnum()) {
                case ARMOR:
                case ARROW:
                case NECKLACE:
                case SHIELD:
                case STAFF:
                case MELE:
                case RING:
                case HELMET:
                case BOW:
                    equipped(null, tempItem);
            }
        }
    }

    public static void itemUsed(AbstractItem item) {
        backpackSegment.itemUsed(item);
        backpackSegment.cleanUp();
        currItem = null;
        //play sound or whatever
    }

    public void draw(Batch batch) {
        heroSegment.draw(batch, currItem);
        backpackSegment.draw(batch, currItem);
        if (currItem != null) itemSegment.draw(batch, true);
        if (notPossibleCommunicateTimer > 0) notPossibleInFightCommunicate.draw(batch, 1f);
    }

    public static boolean isSpaceFor(AbstractItem item) {
        return backpackSegment.isSpaceFor(item);
    }

    public static void equipped(AbstractItem toReplace, AbstractItem newItem) {
        if (toReplace == null) heroSegment.justEquip(newItem);
        else backpackSegment.replace(toReplace, newItem);
    }

    public boolean tap(float x, float y) {
        if (heroSegment.isTap(x, y)) {
            if (currItem == null) currItem = heroSegment.getTapItem(x, y);
            else {
                if (heroSegment.tapWithItem(x, y, currItem)) currItem = null;
            }
        }
        else if (backpackSegment.isTap(x, y)) {
            if (backpackSegment.categoriesSegmentTap(x, y)) {

            } else {
                if (currItem == backpackSegment.getTapItem(x, y)) {
                    currItem = null;
                    heroSegment.selectItem(null);
                    backpackSegment.selectItem(null);
                    itemSegment.selectItem(null);
                }
                else {
                    currItem = backpackSegment.getTapItem(x, y);
                    if (currItem != null) {
                        heroSegment.selectItem(currItem);
                        backpackSegment.selectItem(currItem);
                        itemSegment.selectItem(currItem);
                    }
                }
            }
        }
        else if (currItem != null && itemSegment.isTap(x, y, true)) {
            if (itemSegment.isDrop(x, y)) {
                gameScreen.itemDropped(currItem);

                backpackSegment.itemDropped(currItem);
                heroSegment.itemDropped(currItem);

                currItem = null;
                backpackSegment.selectItem(null);
                heroSegment.selectItem(null);

            }
        }
        else {
            currItem = null;
            isUp = false;
        }

        return true;
    }

    public static boolean isUp() {
        return isUp;
    }

    public static void hide() {
        isUp = false;
    }

    public static void show() {
        isUp = true;
        //cleanUp();
    }

    public static void cleanUp() {
        backpackSegment.cleanUp();
    }

    public static void pickItem(AbstractItem item) {
        backpackSegment.pickItem(item);
    }

    public static void showNotPossibleInFight() {
        notPossibleCommunicateTimer = NOT_POSSIBLE_IN_FIGHT_TIMER;
    }
}
