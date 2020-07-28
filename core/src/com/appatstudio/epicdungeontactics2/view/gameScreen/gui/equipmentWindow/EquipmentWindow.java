package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class EquipmentWindow {

    private static boolean isUp = false;

    private static AbstractItem currItem = null;

    private static HeroSegment heroSegment;
    private static BackpackSegment backpackSegment;
    private static ItemSegment itemSegment;

    public EquipmentWindow(CharacterEnum characterEnum) {
        heroSegment = new HeroSegment(characterEnum);
        backpackSegment = new BackpackSegment(characterEnum);


    }

    public void draw(Batch batch) {
        heroSegment.draw(batch);
        backpackSegment.draw(batch);
        if (currItem != null) {
            itemSegment.draw(batch);
        }
    }

    public boolean isSpaceFor(AbstractItem item) {
        return backpackSegment.isSpaceFor(item);
    }

    public boolean tap(float x, float y) {
        if (heroSegment.isTap(x, y)) {
            currItem = heroSegment.getTapItem(x, y);
        }
        else if (backpackSegment.isTap(x, y)) {
            if (backpackSegment.categoriesSegmentTap(x, y)) {

            } else {
                currItem = backpackSegment.getTapItem(x, y);
                if (currItem != null) {
                    heroSegment.selectItem(currItem);
                    backpackSegment.selectItem(currItem);
                    itemSegment.selectItem(currItem);
                }
            }
        }
        else if (currItem != null && itemSegment.isTap(x, y)) {
            if (itemSegment.isDrop(x, y)) {
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
    }
}
