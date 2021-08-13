package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.selectItemsForNextHeroWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.ItemSegmentMode;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.BackpackSegment;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.HeroSegment;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.ItemSegment;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public class SelectItemsForNextHeroWindow {

    private static BackpackSegment backpackSegment;
    private static ItemSegment itemSegment;
    private static ItemsForNewHeroSegment itemsForNewHeroSegment;
    private ButtonWithText finishButton;

    private AbstractItem currItem;

    private CharacterEnum hero;

    private ItemSegmentMode mode = ItemSegmentMode.NORMAL;

    private static boolean isUp = false;

    public SelectItemsForNextHeroWindow() {
        backpackSegment = new BackpackSegment(CharacterEnum.HERO_ELF);
        itemsForNewHeroSegment = new ItemsForNewHeroSegment();
        itemSegment = new ItemSegment(Gdx.graphics.getHeight() / 2f - AbstractSegment.getFullHeight() / 2f - AbstractSegment.getFullHeight());

        backpackSegment = EquipmentWindow.getBackpackSegment();

        finishButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth() / 2f - MenuScreen.BOTTOM_BUTTON_WIDTH / 2f,
                CharacterSelector.bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.GO));
    }

    public void draw(Batch batch) {
        backpackSegment.draw(batch, currItem);
        itemsForNewHeroSegment.draw(batch, currItem);
        finishButton.draw(batch, 1f);
        if (currItem != null) itemSegment.draw(batch, true, mode);
    }

    public boolean tap(float x, float y) {
        if (backpackSegment.isTap(x, y)) {
            if (backpackSegment.categoriesSegmentTap(x, y)) {
            } else {
                if (currItem == backpackSegment.getTapItem(x, y)) {
                    itemsForNewHeroSegment.selectItem(null);
                    currItem = null;
                    backpackSegment.selectItem(null);
                    itemSegment.selectItem(null, ItemSegmentMode.NORMAL);
                } else {
                    currItem = backpackSegment.getTapItem(x, y);
                    if (currItem != null) {
                        itemsForNewHeroSegment.selectItem(currItem);
                        backpackSegment.selectItem(currItem);
                        itemSegment.selectItem(currItem, ItemSegmentMode.KEEP);
                        mode = ItemSegmentMode.KEEP;
                    }
                }
            }
        }
        else if (itemsForNewHeroSegment.isTap(x, y)) {
            if (currItem == itemsForNewHeroSegment.getTapItem(x, y)) {
                currItem = null;
                itemsForNewHeroSegment.selectItem(null);
                backpackSegment.selectItem(null);
                itemSegment.selectItem(null, ItemSegmentMode.NORMAL);
            } else {
                currItem = itemsForNewHeroSegment.getTapItem(x, y);
                if (currItem != null) {
                    backpackSegment.selectItem(currItem);
                    itemsForNewHeroSegment.selectItem(currItem);
                    itemSegment.selectItem(currItem, ItemSegmentMode.WASTE);
                    mode = ItemSegmentMode.WASTE;
                }
            }
        }
        else if (currItem != null && itemSegment.isTap(x, y, true)) {
            if (itemSegment.isButtonClicked(x, y)) {
                if (mode == ItemSegmentMode.KEEP) {
                    if (itemsForNewHeroSegment.hasSpace()) {
                        itemsForNewHeroSegment.addItem(currItem);
                        backpackSegment.itemDropped(currItem);
                        HeroSegment.itemDropped(currItem);
                        currItem = null;
                        backpackSegment.selectItem(null);
                        itemsForNewHeroSegment.selectItem(null);
                    }
                } else if (mode == ItemSegmentMode.WASTE) {
                    if (backpackSegment.isSpaceFor(currItem)) {
                        EquipmentWindow.pickItem(currItem);
                        itemsForNewHeroSegment.deleteItem(currItem);
                        currItem = null;
                        backpackSegment.selectItem(null);
                        itemsForNewHeroSegment.selectItem(null);
                    }
                }
            }
        }
        else if (finishButton.tap(x, y)) {
            //GuiContainer.newHero();
            System.out.println("SIZE OF BACKPACK2: "+ StatTracker.getBackpackItems().size);
            GameScreen.newHero(hero, itemsForNewHeroSegment.getItems());
            hide();
            System.out.println("SIZE OF BACKPACK3: "+StatTracker.getBackpackItems().size);
            return true;
        }
        else {
            currItem = null;
            return false;
        }
        return false;
    }

    public void show(CharacterEnum hero) {
        this.hero = hero;
        isUp = true;
    }

    public void hide() {
        currItem = null;
        isUp = false;
    }

    public boolean isUp() {
        return isUp;
    }
}
