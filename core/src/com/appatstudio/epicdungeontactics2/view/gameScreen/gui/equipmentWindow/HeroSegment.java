package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow.backpackElements.HeroEqItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public class HeroSegment extends AbstractSegment {

    private AbstractItem selectedItem;
    private static float posY;
    private float stateTime = 0;

    private static float heroSize = fullHeight;
    private static float heroX = getPosX();
    private static float heroY = posY;

    private static final float tileSize = heroSize/3f;
    //eq tiles positions
    private static Vector2 tileHelmet = new Vector2();
    private static Vector2 tileNecklace = new Vector2();
    private static Vector2 tileSword = new Vector2();
    private static Vector2 tileArmor = new Vector2();
    private static Vector2 tileShield = new Vector2();
    private static Vector2 tileRing0 = new Vector2();
    private static Vector2 tileRing1 = new Vector2();
    private static Vector2 tileBow = new Vector2();
    private static Vector2 tileStaff = new Vector2();
    private static Vector2 tileArrow = new Vector2();


    private Animation<SpriteDrawable> heroAnimation;

    private Array<HeroEqItemBlock> eqItemBlocks;

    HeroSegment(CharacterEnum hero) {
        posY = Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight()/2f + AbstractSegment.fullHeight;
        bg = GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_HERO);
        heroAnimation = GraphicsManager.getCharactersAnimation(hero, CharacterStateEnum.IDLE);
        heroY = posY + fullHeight/2f - heroSize/3f;

        createHeroSpecifiedEquipment(hero);
    }

    void draw(Batch batch) {
        bg.draw(batch, posX, posY, fullWidth, fullHeight);

        stateTime += Gdx.graphics.getDeltaTime();
        heroAnimation.getKeyFrame(stateTime).draw(batch, heroX, heroY, heroSize, heroSize);

    }


    void selectItem(AbstractItem item) {
        this.selectedItem = item;
    }

    AbstractItem getTapItem(float x, float y) {
        return null;
    }

    boolean tapWithItem(float x, float y, AbstractItem item) {
        for (HeroEqItemBlock b : eqItemBlocks) {
            if (b.getRequiredItem() == item.getItemTypeEnum()) {
                if (b.isTap(x, y)) {
                    EquipmentWindow.equipped(item, b.getItem());
                    b.setItem(item);
                    StatTracker.refreshEq();
                    return true;
                }
            }
        }
        return false;
//        return x > posX && x < posX + fullWidth
//                && y > posY && posY < posY + fullHeight;
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
                eqItemBlocks.add(new HeroEqItemBlock(tileRing0, tileSize, ItemTypeEnum.RING));
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


}
