package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemBackpackShelfEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements.BackpackPage;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements.CategoryColumns;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class BackpackSegment extends AbstractSegment {

    private CategoryColumns categoryColumns;
    private HashMap<ItemBackpackShelfEnum, BackpackPage> pages;

    private AbstractItem currItem;
    private ItemBackpackShelfEnum currShelf;

    private static float posY = Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight()/2f;

    BackpackSegment(CharacterEnum hero) {
        currShelf = ItemBackpackShelfEnum.MELE;
        currItem = null;

        bg = GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_BACKPACK);

        ItemBackpackShelfEnum[] allCategories = ItemBackpackShelfEnum.values();
        pages = new HashMap<>();

        categoryColumns = new CategoryColumns();

        for (ItemBackpackShelfEnum shelf : allCategories) {
            pages.put(shelf, new BackpackPage(shelf, hero));
        }
    }

    boolean isSpaceFor(AbstractItem item) {
        switch (item.getItemTypeEnum()) {
            case SWORD:
                return pages.get(ItemBackpackShelfEnum.MELE).isSpace();
            case HELMET:
            case NECKLACE:
            case SHIELD:
            case RING:
            case ARMOR:
                return pages.get(ItemBackpackShelfEnum.ARMOR).isSpace();
            case BOOK:
            case FOOD:
            case OTHER:
                return pages.get(ItemBackpackShelfEnum.FOOD).isSpace();
            case ARROW:
            case BOW:
            case STAFF:
                return pages.get(ItemBackpackShelfEnum.DISTANCE).isSpace();
            default: return false;
        }
    }

    void draw(Batch batch) {
        bg.draw(batch, posX, getPosY(), fullWidth, fullHeight);
        categoryColumns.draw(batch, currShelf);
        pages.get(currShelf).draw(batch, currItem);
    }

    AbstractItem getTapItem(float x, float y) {
        if (x < posX + CategoryColumns.getW()) {
            currShelf = categoryColumns.getCategory(y);
            return null;
        }
        else return pages.get(currShelf).tap(x, y);
    }

    void dropItem(AbstractItem item) {
        pages.get(currShelf).drop(item);
    }

    void selectItem(AbstractItem item) {
        if (item != null) {
            switch (item.getItemTypeEnum()) {
                case STAFF:
                case SWORD:
                    currShelf = ItemBackpackShelfEnum.MELE;
                    break;
                case HELMET:
                case NECKLACE:
                case SHIELD:
                case RING:
                case ARMOR:
                    currShelf = ItemBackpackShelfEnum.ARMOR;
                    break;
                case BOOK:
                case FOOD:
                case OTHER:
                    currShelf = ItemBackpackShelfEnum.FOOD;
                    break;
                case ARROW:
                case BOW:
                    currShelf = ItemBackpackShelfEnum.DISTANCE;
                    break;
            }
        }
    }

    public static float getPosY() {
        return posY;
    }

    public boolean isTap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && posY < posY + fullHeight;
    }
}
