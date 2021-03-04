package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements.HeroEqItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements.ItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public class HeroSegment extends AbstractSegment {

    private AbstractItem selectedItem;
    private static float posY;
    private float stateTime = 0;

    private static float heroSize = fullHeight;
    private static float heroX = getPosX();
    private static float heroY = posY;

    private static final float tileSize = (fullWidth - heroSize * 0.75f * 1.5f) / 4f;
    //eq tiles positions
    private static CoordsFloat tileHelmet;
    private static CoordsFloat tileNecklace;
    private static CoordsFloat tileSword;
    private static CoordsFloat tileArmor;
    private static CoordsFloat tileShield;
    private static CoordsFloat tileRing0;
    private static CoordsFloat tileRing1;
    private static CoordsFloat tileBow;
    private static CoordsFloat tileStaff;
    private static CoordsFloat tileArrow;


    private Animation<SpriteDrawable> heroAnimation;

    private static Array<HeroEqItemBlock> eqItemBlocks;

    static {
        posY = Gdx.graphics.getHeight() / 2f - AbstractSegment.getFullHeight() / 2f + AbstractSegment.fullHeight;

        float topY = posY + fullHeight * 0.8f - tileSize;
        float bottomY = topY - tileSize;
        float leftX = posX + heroSize * 0.85f;

        tileHelmet = new CoordsFloat(leftX, topY);
        tileNecklace = new CoordsFloat(leftX + tileSize, topY);
        tileArmor = new CoordsFloat(leftX, bottomY);
        tileShield = new CoordsFloat(leftX + tileSize, bottomY);
        tileRing0 = new CoordsFloat(leftX + tileSize * 2, bottomY);
        tileRing1 = new CoordsFloat(leftX + tileSize * 3, bottomY);
        tileSword = new CoordsFloat(leftX + tileSize * 2, topY);
        tileBow = new CoordsFloat(leftX + tileSize * 3, topY);
        tileStaff = new CoordsFloat(leftX + tileSize * 3, topY);
        tileArrow = new CoordsFloat(leftX + tileSize * 2, bottomY);
    }

    HeroSegment(CharacterEnum hero) {
        bg = GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_HERO);
        heroAnimation = GraphicsManager.guiHeroAnimations(hero, CharacterStateEnum.IDLE);
        heroY = posY + fullHeight / 2f - heroSize / 3f;

        createHeroSpecifiedEquipment(hero);
    }

    public static Array<AbstractItem> getItems() {
        Array<AbstractItem> items = new Array<>();

        if (eqItemBlocks != null) {
            for (ItemBlock item : eqItemBlocks) {
                if (item.getItem() != null) items.add(item.getItem());
            }
        }

        return items;
    }

    public void newRun() {
        for (ItemBlock i : eqItemBlocks) {
            i.setItem(null);
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
        for (HeroEqItemBlock ib : eqItemBlocks) {
            ib.draw(batch, selectedItem);
        }


        HeroEqItemBlock.act();
    }


    void selectItem(AbstractItem item) {
        this.selectedItem = item;
        HeroEqItemBlock.resetPulsating();
    }

    AbstractItem getTapItem(float x, float y) {
        for (HeroEqItemBlock ib : eqItemBlocks) if (ib.isTap(x, y)) return ib.getItem();
        return null;
    }

    boolean tapWithItem(float x, float y, AbstractItem item) {
        if (item.getItemTypeEnum() == ItemTypeEnum.FOOD
                || item.getItemTypeEnum() == ItemTypeEnum.BOOK) {
            if (x > heroX && x < heroX + heroSize && y > heroY && y < heroY + heroSize) {
                StatTracker.itemUsed(item);
                EquipmentWindow.itemUsed(item);
            }
        }

        for (HeroEqItemBlock b : eqItemBlocks) {
            if (b.getRequiredItem() == item.getItemTypeEnum()) {
                if (b.isTap(x, y)) {
                    EquipmentWindow.equipped(item, b.getItem());
                    b.setItem(item);
                    StatTracker.refreshWholeCharacter();
                    return true;
                }
            }
        }
        return false;
//        return x > posX && x < posX + fullWidth
//                && y > posY && posY < posY + fullHeight;
    }

    public AbstractItem tap(float x, float y) {
        for (HeroEqItemBlock ib : eqItemBlocks) if (ib.isTap(x, y)) return ib.getItem();
        return null;
    }

    public boolean isTap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && y < posY + fullHeight;
    }

    private void createHeroSpecifiedEquipment(CharacterEnum hero) {
        eqItemBlocks = new Array<>();

        switch (hero) {
            case HERO_ELF:
                eqItemBlocks.add(new HeroEqItemBlock(tileHelmet, tileSize, ItemTypeEnum.HELMET));
                eqItemBlocks.add(new HeroEqItemBlock(tileNecklace, tileSize, ItemTypeEnum.NECKLACE));
                eqItemBlocks.add(new HeroEqItemBlock(tileArmor, tileSize, ItemTypeEnum.ARMOR));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing1, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileSword, tileSize, ItemTypeEnum.MELE));
                eqItemBlocks.add(new HeroEqItemBlock(tileBow, tileSize, ItemTypeEnum.BOW));
                eqItemBlocks.add(new HeroEqItemBlock(tileArrow, tileSize, ItemTypeEnum.ARROW));
                break;

            case HERO_KNIGHT:
                eqItemBlocks.add(new HeroEqItemBlock(tileHelmet, tileSize, ItemTypeEnum.HELMET));
                eqItemBlocks.add(new HeroEqItemBlock(tileArmor, tileSize, ItemTypeEnum.ARMOR));
                eqItemBlocks.add(new HeroEqItemBlock(tileShield, tileSize, ItemTypeEnum.SHIELD));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing0, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileSword, tileSize, ItemTypeEnum.MELE));
                eqItemBlocks.add(new HeroEqItemBlock(tileBow, tileSize, ItemTypeEnum.BOW));
                eqItemBlocks.add(new HeroEqItemBlock(tileArrow, tileSize, ItemTypeEnum.ARROW));
                break;

            case HERO_WIZZARD:
                eqItemBlocks.add(new HeroEqItemBlock(tileArmor, tileSize, ItemTypeEnum.ARMOR));
                eqItemBlocks.add(new HeroEqItemBlock(tileNecklace, tileSize, ItemTypeEnum.NECKLACE));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing0, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing1, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileStaff, tileSize, ItemTypeEnum.STAFF));
                break;

            case HERO_LIZARD:
                eqItemBlocks.add(new HeroEqItemBlock(tileHelmet, tileSize, ItemTypeEnum.HELMET));
                eqItemBlocks.add(new HeroEqItemBlock(tileArmor, tileSize, ItemTypeEnum.ARMOR));
                eqItemBlocks.add(new HeroEqItemBlock(tileSword, tileSize, ItemTypeEnum.MELE));
                eqItemBlocks.add(new HeroEqItemBlock(tileBow, tileSize, ItemTypeEnum.BOW));
                eqItemBlocks.add(new HeroEqItemBlock(tileArrow, tileSize, ItemTypeEnum.ARROW));
                break;

            case HERO_NINJA:
                eqItemBlocks.add(new HeroEqItemBlock(tileHelmet, tileSize, ItemTypeEnum.HELMET));
                eqItemBlocks.add(new HeroEqItemBlock(tileArmor, tileSize, ItemTypeEnum.ARMOR));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing0, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing1, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileSword, tileSize, ItemTypeEnum.MELE));
                eqItemBlocks.add(new HeroEqItemBlock(tileBow, tileSize, ItemTypeEnum.BOW));
                eqItemBlocks.add(new HeroEqItemBlock(tileArrow, tileSize, ItemTypeEnum.ARROW));
                break;

            case HERO_PIRATE:
                eqItemBlocks.add(new HeroEqItemBlock(tileHelmet, tileSize, ItemTypeEnum.HELMET));
                eqItemBlocks.add(new HeroEqItemBlock(tileNecklace, tileSize, ItemTypeEnum.NECKLACE));
                eqItemBlocks.add(new HeroEqItemBlock(tileArmor, tileSize, ItemTypeEnum.ARMOR));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing0, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileRing1, tileSize, ItemTypeEnum.RING));
                eqItemBlocks.add(new HeroEqItemBlock(tileSword, tileSize, ItemTypeEnum.MELE));
                eqItemBlocks.add(new HeroEqItemBlock(tileBow, tileSize, ItemTypeEnum.BOW));
                eqItemBlocks.add(new HeroEqItemBlock(tileArrow, tileSize, ItemTypeEnum.ARROW));
                break;

            case HERO_BABY:
                eqItemBlocks.add(new HeroEqItemBlock(tileStaff, tileSize, ItemTypeEnum.STAFF));
                break;
        }
    }


    public void justEquip(AbstractItem newItem) {
        for (HeroEqItemBlock b : eqItemBlocks) {
            if (b.getRequiredItem() == newItem.getItemTypeEnum()) {
                    EquipmentWindow.equipped(newItem, b.getItem());
                    b.setItem(newItem);
                    break;
            }
        }
    }

    public static void equipIfIsSpace(AbstractItem newItem) {
        if (EquipmentWindow.isSpaceFor(newItem)) {

            //EquipmentWindow.pickItem(newItem);

            for (HeroEqItemBlock b : eqItemBlocks) {
                if (b.getRequiredItem() == newItem.getItemTypeEnum()) {
                    EquipmentWindow.equipped(newItem, b.getItem());
                    b.setItem(newItem);
                    //StatTracker.equippedItems.add(newItem);
                    StatTracker.refreshWholeCharacter();
                    break;
                }
            }
        }
    }

    public static void itemDropped(AbstractItem currItem) {
        for (HeroEqItemBlock b : eqItemBlocks) {
            if (b.getItem() == currItem) {
                b.setItem(null);
                StatTracker.refreshWholeCharacter();
                break;
            }
        }
    }
}
