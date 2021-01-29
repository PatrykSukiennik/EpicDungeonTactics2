package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.stats.CampUpgradeStats;
import com.appatstudio.epicdungeontactics2.global.stats.itemGenerator.ItemGenerator;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements.HeroEqItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements.ItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class ShopSegment extends AbstractSegment {

    //4x2
    private static float posY = Gdx.graphics.getHeight()/2f + AbstractSegment.getFullHeight()/2f;
    private ItemBlock[] itemsToBuy;

    private AbstractItem selectedItem;
    private float stateTime = 0;

    private TextObject title;

    private static float heroSize = fullHeight;
    private static float heroX = getPosX();
    private static float heroY = posY;
    private static final float tileSize = (fullWidth - heroSize * 0.75f * 1.5f) / 4f;

    private int itemsToGenerate = 0;

    private static final CoordsFloat[] tileCoords = new CoordsFloat[8];

    private Animation<SpriteDrawable> heroAnimation;

    static  {

        float topY = posY + fullHeight * 0.8f - tileSize;
        float bottomY = topY - tileSize;
        float leftX = posX + heroSize * 0.85f;

        for (int i=0; i<8; i++) {
            tileCoords[i] = new CoordsFloat(leftX + tileSize * (i % 4), topY - tileSize * (int)(i / 4f));
        }

    }

    public ShopSegment(CharacterEnum shop) {
        bg = GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_HERO);
        heroAnimation = GraphicsManager.getCharactersAnimation(shop, CharacterStateEnum.IDLE);
        heroY = posY + fullHeight / 2f - heroSize / 3f;

        itemsToBuy = new ItemBlock[8];
        for (int i=0; i<8; i++) {
            itemsToBuy[i] = new ItemBlock(tileCoords[i], tileSize);
        }

        CampUpgradeEnum campUpgradeEnum = null;
        switch (shop) {
            case NPC_MAGIC_SHOP: campUpgradeEnum = CampUpgradeEnum.MAGIC_SHOP; break;
            case NPC_ALCHEMIST: campUpgradeEnum = CampUpgradeEnum.ALCHEMIST; break;
            case NPC_BLACKSMITH: campUpgradeEnum = CampUpgradeEnum.BLACKSMITH; break;
            case NPC_BUTCHER: campUpgradeEnum = CampUpgradeEnum.BUTCHER; break;
        }
        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCharacterName(shop) + " lvl. " + SavedInfoManager.getNpcLvl(campUpgradeEnum),
                Gdx.graphics.getWidth()/2f,
                posY + fullHeight * 1.2f,
                Align.center
        );

        itemsToGenerate =
                CampUpgradeStats.getCampUpgradeStat(campUpgradeEnum, SavedInfoManager.getNpcLvl(campUpgradeEnum) - 1);

        ItemGenerator.refresh();
        for (int i=0; i<8; i++) {
            if (i < itemsToGenerate) {
                AbstractItem newItem = null;
                float random = EpicDungeonTactics.random.nextFloat();
                switch (shop) {
                    case NPC_MAGIC_SHOP: {
                        if (random < 0.25f) newItem = ItemGenerator.getItem(ItemTypeEnum.RING);
                        else if (random < 0.5f) newItem = ItemGenerator.getItem(ItemTypeEnum.NECKLACE);
                        else if (random < 0.54f) newItem = ItemGenerator.getItem(ItemEnum.BOOKv0);
                        else if (random < 0.58f) newItem = ItemGenerator.getItem(ItemEnum.BOOKv1);
                        else if (random < 0.62f) newItem = ItemGenerator.getItem(ItemEnum.BOOKv2);
                        else if (random < 0.66f) newItem = ItemGenerator.getItem(ItemEnum.BOOKv3);
                        else if (random < 0.7f) newItem = ItemGenerator.getItem(ItemEnum.BOOKv4);
                        else if (random < 0.74f) newItem = ItemGenerator.getItem(ItemEnum.BOOKv5);
                        else newItem = ItemGenerator.getItem(ItemTypeEnum.STAFF);
                        break;
                    }
                    case NPC_BLACKSMITH: {
                        if (random < 0.4f) newItem = ItemGenerator.getItem(ItemTypeEnum.MELE);
                        else if (random < 0.55f) newItem = ItemGenerator.getItem(ItemTypeEnum.BOW);
                        else if (random < 0.65f) newItem = ItemGenerator.getItem(ItemTypeEnum.HELMET);
                        else if (random < 0.75f) newItem = ItemGenerator.getItem(ItemTypeEnum.ARMOR);
                        else if (random < 0.85f) newItem = ItemGenerator.getItem(ItemTypeEnum.ARROW);
                        else newItem = ItemGenerator.getItem(ItemTypeEnum.SHIELD);
                        break;
                    }
                    case NPC_ALCHEMIST: {
                        if (random < 0.2f) newItem = ItemGenerator.getItem(ItemEnum.FOODv7);
                        else if (random < 0.30f) newItem = ItemGenerator.getItem(ItemEnum.FOODv8);
                        else if (random < 0.5f) newItem = ItemGenerator.getItem(ItemEnum.FOODv9);
                        else if (random < 0.75f) newItem = ItemGenerator.getItem(ItemEnum.FOODv10);
                        else newItem = ItemGenerator.getItem(ItemEnum.FOODv4);
                        break;
                    }
                    case NPC_BUTCHER: {
                        if (random < 0.7f) newItem = ItemGenerator.getItem(ItemEnum.FOODv6);
                        else if (random < 0.8f) newItem = ItemGenerator.getItem(ItemEnum.FOODv0);
                        else if (random < 0.9f) newItem = ItemGenerator.getItem(ItemEnum.FOODv1);
                        else newItem = ItemGenerator.getItem(ItemEnum.FOODv2);
                        break;
                    }
                }

                itemsToBuy[i].setItem(newItem);
            }
            else itemsToBuy[i].setItem(null);
        }
    }

    void draw(Batch batch, AbstractItem selectedItem) {
        bg.draw(batch, posX, posY, fullWidth, fullHeight);

        stateTime += Gdx.graphics.getDeltaTime();
        if (selectedItem != null
                && (selectedItem.getItemTypeEnum() == ItemTypeEnum.FOOD
                || selectedItem.getItemTypeEnum() == ItemTypeEnum.BOOK)) {
            batch.getColor().a = HeroEqItemBlock.getPulsating();
            heroAnimation.getKeyFrame(stateTime).draw(batch, heroX, heroY, heroSize, heroSize);
        } else {
            batch.getColor().a = 1f;
            heroAnimation.getKeyFrame(stateTime).draw(batch, heroX, heroY, heroSize, heroSize);
        }

        batch.getColor().a = 1f;
        for (ItemBlock ib : itemsToBuy) {
            ib.draw(batch, selectedItem, 1f);
        }

        title.draw(batch);

        //HeroEqItemBlock.act();
    }

    public AbstractItem tap(float x, float y) {
        for (ItemBlock ib : itemsToBuy) if (ib.isTap(x, y)) return ib.getItem();
        return null;
    }

    public boolean isTap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && y < posY + fullHeight;
    }

    void selectItem(AbstractItem item) {
        this.selectedItem = item;
    }

    AbstractItem getTapItem(float x, float y) {
        for (ItemBlock ib : itemsToBuy) if (ib.isTap(x, y)) return ib.getItem();
        return null;
    }


    public boolean hasSpace() {
        for (int i=0; i<8; i++) {
            if (itemsToBuy[i].getItem() == null) return true;
        }
        return false;
    }

    public void addItem(AbstractItem currItem) {
        for (int i=0; i<8; i++) {
            if (itemsToBuy[i].getItem() == null) {
                itemsToBuy[i].setItem(currItem);
                return;
            }
        }
    }

    public void deleteItem(AbstractItem currItem) {
        for (int i=0; i<8; i++) {
            if (itemsToBuy[i].getItem() == currItem) {
                itemsToBuy[i].setItem(null);
                cleanUp();
                break;
            }
        }
    }

    private void cleanUp() {
        for (int i=0; i < 7; i++) {
            if (itemsToBuy[i].getItem() == null && itemsToBuy[i + 1].getItem() != null) {
                itemsToBuy[i].setItem(itemsToBuy[i + 1].getItem());
                itemsToBuy[i + 1].setItem(null);
            }
        }
    }
}
