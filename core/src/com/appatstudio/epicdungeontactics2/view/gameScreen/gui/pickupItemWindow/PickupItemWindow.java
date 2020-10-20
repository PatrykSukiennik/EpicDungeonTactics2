package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.pickupItemWindow;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.ItemSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class PickupItemWindow {

    private static SpriteDrawable blackBg;
    private static TextObject title;
    private static ItemSegment itemSegment;
    private static ItemsBar itemsBar;
    private static ButtonWithText pickupButton;
    private static ButtonWithText noSpaceButton;

    private static boolean isUp = false;
    private static AbstractItem selectedItem;

    static {
        blackBg = GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent);
        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.ITEMS_TO_PICK),
                Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getHeight() * 0.7f,
                Align.center
        );

        itemSegment = new ItemSegment(Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight()*1.5f);
        itemsBar = new ItemsBar();
        pickupButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.NONE),
                Gdx.graphics.getWidth()/2f - Gdx.graphics.getWidth() * 0.7f * 0.5f,
                Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight() * 1.5f - itemsBar.getSIZE(),
                Gdx.graphics.getWidth() * 0.7f,
                itemsBar.getSIZE(),
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.PICK)
        );
        noSpaceButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.NONE),
                Gdx.graphics.getWidth()/2f - Gdx.graphics.getWidth() * 0.7f * 0.5f,
                Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight() * 1.5f - itemsBar.getSIZE(),
                Gdx.graphics.getWidth() * 0.7f,
                itemsBar.getSIZE(),
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.NO_SPACE)
        );
    }

    public void draw(Batch batch) {
        blackBg.draw(batch, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        title.draw(batch);

        itemsBar.draw(batch, selectedItem);
        if (selectedItem != null) {
            itemSegment.draw(batch, false);
            if (EquipmentWindow.isSpaceFor(selectedItem)) pickupButton.draw(batch, 1f);
            else noSpaceButton.draw(batch, 1f);
        }
    }

    public boolean tap(float x, float y) {

        if (x < Gdx.graphics.getWidth() * 0.2f || x > Gdx.graphics.getWidth() * 0.8f
                || y < Gdx.graphics.getHeight() * 0.2f || y > Gdx.graphics.getHeight() * 0.8f) {
            hide();
            return true;
        }

        if (selectedItem == null) {
            if (itemSegment.isTap(x, y, false)) return true;
            selectedItem = null;
        }
        else {
            if (EquipmentWindow.isSpaceFor(selectedItem) && pickupButton.tap(x, y)) {
                EquipmentWindow.pickItem(selectedItem);
                GameScreen.getInstance().itemPickedUp(selectedItem);
                selectedItem = null;
            }
            else if (itemSegment.isTap(x, y, false)) {
                return true;
            }
        }
        selectedItem = itemsBar.tap(x, y, selectedItem);
        if (selectedItem != null) itemSegment.selectItem(selectedItem);
        return true;
    }

    public static boolean isUp() {
        return isUp;
    }

    public static void show(Array<AbstractItem> itemsArray) {
        isUp = true;
        itemsBar.setItems(itemsArray);
        title.setPos(Gdx.graphics.getWidth()/2f, itemsBar.getTopY() + Gdx.graphics.getWidth() * 0.1f);
        if (itemsArray.size == 1) {
            selectedItem = itemsArray.get(0);
            itemSegment.selectItem(selectedItem);
        }

    }

    public static void hide() {
        isUp = false;
    }

}
