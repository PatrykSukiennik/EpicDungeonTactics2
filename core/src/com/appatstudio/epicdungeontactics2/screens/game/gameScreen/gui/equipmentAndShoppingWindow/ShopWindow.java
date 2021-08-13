package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.ItemSegmentMode;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.HashMap;

public class ShopWindow {

    public static final float BUY_VALUE_MULTIPLIER = 10f;

    private GameScreen gameScreen;

    private static boolean isUp = false;

    private static AbstractItem currItem = null;

    private static HashMap<CharacterEnum, ShopSegment> shopSegment;
    private static BackpackSegment backpackSegment;
    private static ItemSegment itemSegment;

    private static final float NOT_POSSIBLE_IN_FIGHT_TIMER = 2f;
    private static float notPossibleCommunicateTimer = -1;
    private static ButtonWithText notPossibleInFightCommunicate;

    private ItemSegmentMode mode;

    public ShopWindow(CharacterEnum hero) {
        shopSegment = new HashMap<>();
        backpackSegment = new BackpackSegment(hero);
        itemSegment = new ItemSegment(Gdx.graphics.getHeight() / 2f - AbstractSegment.getFullHeight() / 2f - AbstractSegment.fullHeight);

        backpackSegment = EquipmentWindow.getBackpackSegment();
    }

    public void draw(Batch batch, CharacterEnum shop) {
        shopSegment.get(shop).draw(batch, currItem);
        backpackSegment.draw(batch, currItem);
        if (currItem != null) {
            if (mode == ItemSegmentMode.BUY) {
                if (backpackSegment.isSpaceFor(currItem)
                        && GlobalValues.getGold() >= currItem.getValue() * (mode == ItemSegmentMode.BUY ? BUY_VALUE_MULTIPLIER : 1)) {
                    itemSegment.draw(batch, true, mode);
                } else {
                    itemSegment.draw(batch, false, mode);
                }
            }
            else if (mode == ItemSegmentMode.SELL) {
                if (shopSegment.get(shop).hasSpace() && !currItem.isStartingItem()) {
                    itemSegment.draw(batch, true, mode);
                } else {
                    itemSegment.draw(batch, false, mode);
                }
            }
        }
        if (notPossibleCommunicateTimer > 0) notPossibleInFightCommunicate.draw(batch, 1f);
    }


    public void addOrRefreshShop(CharacterEnum shop) {
        shopSegment.put(shop, new ShopSegment(shop));
    }

    public void hide() {
        currItem = null;
        isUp = false;
    }

    public void show() {
        isUp = true;
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean tap(float x, float y, CharacterEnum shop) {
        if (shopSegment.get(shop).isTap(x, y)) {
            if (currItem == null) {
                currItem = shopSegment.get(shop).getTapItem(x, y);
                itemSegment.selectItem(currItem, ItemSegmentMode.BUY);
                mode = ItemSegmentMode.BUY;
            } else {
                if (currItem == shopSegment.get(shop).getTapItem(x, y)) {
                    currItem = null;
                } else {
                    currItem = shopSegment.get(shop).getTapItem(x, y);
                    itemSegment.selectItem(currItem, ItemSegmentMode.BUY);
                    mode = ItemSegmentMode.BUY;
                }
            }
        } else if (backpackSegment.isTap(x, y)) {
            if (backpackSegment.categoriesSegmentTap(x, y)) {

            } else {
                if (currItem == backpackSegment.getTapItem(x, y)) {
                    currItem = null;
                    shopSegment.get(shop).selectItem(null);
                    backpackSegment.selectItem(null);
                    itemSegment.selectItem(null, ItemSegmentMode.NORMAL);
                } else {
                    currItem = backpackSegment.getTapItem(x, y);
                    if (currItem != null) {
                        shopSegment.get(shop).selectItem(currItem);
                        backpackSegment.selectItem(currItem);
                        itemSegment.selectItem(currItem, ItemSegmentMode.SELL);
                        mode = ItemSegmentMode.SELL;
                    }
                }
            }
        } else if (currItem != null && itemSegment.isTap(x, y, true)) {
            if (itemSegment.isButtonClicked(x, y)) {
                if (mode == ItemSegmentMode.SELL) {
                    if (shopSegment.get(shop).hasSpace() && !currItem.isStartingItem()) {
                        shopSegment.get(shop).addItem(currItem);
                        backpackSegment.itemDropped(currItem);
                        GlobalValues.addGold(currItem.getValue());
                        HeroSegment.itemDropped(currItem);
                        currItem = null;
                        backpackSegment.selectItem(null);
                        shopSegment.get(shop).selectItem(null);
                    }
                } else if (mode == ItemSegmentMode.BUY) {
                    if (backpackSegment.isSpaceFor(currItem)
                            && GlobalValues.getGold() >= currItem.getValue() * BUY_VALUE_MULTIPLIER) {
                        EquipmentWindow.pickItem(currItem);
                        GlobalValues.minusGold((int)(currItem.getValue() * BUY_VALUE_MULTIPLIER));
                        shopSegment.get(shop).deleteItem(currItem);
                        HeroSegment.equipIfIsSpace(currItem);
                        currItem = null;
                        backpackSegment.selectItem(null);
                        shopSegment.get(shop).selectItem(null);
                    }
                }

            }
        } else {
            currItem = null;
            isUp = false;
        }

        return true;
    }


}
