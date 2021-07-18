package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.selectItemsForNextHeroWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.stats.CampUpgradeStats;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.AbstractSegment;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.backpackElements.ItemBlock;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class ItemsForNewHeroSegment {

    private int itemsToExchange = 3;
    private AbstractItem selectedItem = null;
    private ItemBlock[] items;
    private Image bg;
    private TextObject title;

    public ItemsForNewHeroSegment() {
        int upgradeLvl = SavedInfoManager.getNpcLvl(CampUpgradeEnum.LUGGAGE_CARRIAGE);
        if (upgradeLvl > 0) {
            itemsToExchange = 3 + CampUpgradeStats.getCampUpgradeStat(CampUpgradeEnum.LUGGAGE_CARRIAGE, upgradeLvl);
        }

        float blockSize = (Gdx.graphics.getWidth() * 0.5f) / itemsToExchange;

        items = new ItemBlock[itemsToExchange];
        for (int i = 0; i< itemsToExchange; i++) {
            items[i] = new ItemBlock(new CoordsFloat(
                    (Gdx.graphics.getWidth() * 0.25f) + i * blockSize,
                    Gdx.graphics.getHeight() * 0.5f + AbstractSegment.getFullHeight() * 0.95f - blockSize * 0.5f)
                    , blockSize);
        }

        bg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_HERO));
        bg.setSize(AbstractSegment.getFullWidth(),
                AbstractSegment.getFullHeight());
        bg.setPosition(AbstractSegment.getPosX(),
                Gdx.graphics.getHeight()/2f + AbstractSegment.getFullHeight()/2f);

        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.KEEP_ITEMS),
                Gdx.graphics.getWidth()/2f,
                Gdx.graphics.getHeight()/2f + AbstractSegment.getFullHeight() * 1.85f,
                Align.center
        );

    }

    void draw(Batch batch, AbstractItem selectedItem) {
        bg.draw(batch, 1f);
        title.draw(batch);

        for (ItemBlock i : items) {
            i.draw(batch, selectedItem, 1f);
        }
    }

    public AbstractItem tap(float x, float y) {
        for (ItemBlock ib : items) if (ib.isTap(x, y)) return ib.getItem();
        return null;
    }

    public boolean isTap(float x, float y) {
        return x > items[0].getX() && x < items[itemsToExchange -1].getX() + items[0].getWidth()
                && y > items[0].getY() && y < items[0].getY() + items[0].getHeight();
    }

    void selectItem(AbstractItem item) {
        this.selectedItem = item;
    }

    AbstractItem getTapItem(float x, float y) {
        for (ItemBlock ib : items) if (ib.isTap(x, y)) return ib.getItem();
        return null;
    }


    public boolean hasSpace() {
        for (int i = 0; i< itemsToExchange; i++) {
            if (items[i].getItem() == null) return true;
        }
        return false;
    }

    public void addItem(AbstractItem currItem) {
        for (int i = 0; i< itemsToExchange; i++) {
            if (items[i].getItem() == null) {
                items[i].setItem(currItem);
                return;
            }
        }
    }

    public void deleteItem(AbstractItem currItem) {
        for (int i = 0; i< itemsToExchange; i++) {
            if (items[i].getItem() == currItem) {
                items[i].setItem(null);
                cleanUp();
                break;
            }
        }
    }

    private void cleanUp() {
        for (int i = 0; i < itemsToExchange - 1; i++) {
            if (items[i].getItem() == null && items[i + 1].getItem() != null) {
                items[i].setItem(items[i + 1].getItem());
                items[i + 1].setItem(null);
            }
        }
    }

    public Array<AbstractItem> getItems() {
        Array<AbstractItem> result = new Array<>();
        for (int i=0; i<itemsToExchange; i++) {
            if (items[i].getItem() != null) result.add(items[i].getItem());
        }
        return result;
    }
}
