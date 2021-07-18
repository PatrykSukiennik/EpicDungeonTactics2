package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow;

import com.appatstudio.epicdungeontactics2.global.assets.SoundsLoader;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.ItemSegmentMode;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.SoundPlayAction;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
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
        switch (item.getItemTypeEnum()) {
            case FOOD:
                switch (item.getItemEnum()) {
                    case FOODv7:
                    case FOODv8:
                    case FOODv9:
                    case FOODv10:
                        SoundsManager.playSound(SoundEnum.POTION_DRINKING);
                        break;
                    default:
                        SoundsManager.playSound(SoundEnum.EATING);
                }
                break;
            case BOOK:
                SoundsManager.playSound(SoundEnum.BOOK_READ);
                break;
        }
    }

    public static void newRun(CharacterEnum hero) {
        heroSegment = new HeroSegment(hero);
        backpackSegment = new BackpackSegment(hero);
    }

    public void draw(Batch batch) {
        heroSegment.draw(batch, currItem);
        backpackSegment.draw(batch, currItem);
        if (currItem != null) itemSegment.draw(batch, true, ItemSegmentMode.NORMAL);
        if (notPossibleCommunicateTimer > 0) notPossibleInFightCommunicate.draw(batch, 1f);
    }

    public static boolean isSpaceFor(AbstractItem item) {
        return backpackSegment.isSpaceFor(item);
    }

    public static void equipped(AbstractItem toReplace, AbstractItem newItem) {
        if (toReplace == null) heroSegment.justEquip(newItem);
        else backpackSegment.replace(toReplace, newItem);

        if (newItem != null) {
            switch (newItem.getItemTypeEnum()) {
                case MELE:
                    SoundsManager.playSound(SoundEnum.MELE_WEAPON_WEAR);
                    break;
                case ARMOR:
                case NECKLACE:
                case SHIELD:
                case RING:
                case HELMET:
                case STAFF:
                case BOOK:
                case BOW:
                case ARROW:
                    SoundsManager.playSound(SoundEnum.ARMOR_DRESSING);
                    break;
            }
        }
    }

    public boolean tap(float x, float y) {
        if (heroSegment.isTap(x, y)) {
            if (currItem == null) {
                currItem = heroSegment.getTapItem(x, y);
                backpackSegment.selectItem(currItem);
                itemSegment.selectItem(currItem, ItemSegmentMode.NORMAL);
            }
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
                    itemSegment.selectItem(null, ItemSegmentMode.NORMAL);
                }
                else {
                    currItem = backpackSegment.getTapItem(x, y);
                    if (currItem != null) {
                        heroSegment.selectItem(currItem);
                        backpackSegment.selectItem(currItem);
                        itemSegment.selectItem(currItem, ItemSegmentMode.NORMAL);
                    }
                }
            }
        }
        else if (currItem != null && itemSegment.isTap(x, y, true)) {
            if (itemSegment.isButtonClicked(x, y)) {
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

    public static BackpackSegment getBackpackSegment() {
        return backpackSegment;
    }
}
