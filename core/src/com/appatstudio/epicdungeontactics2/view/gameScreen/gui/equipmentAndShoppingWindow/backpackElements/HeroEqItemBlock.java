package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements;

import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HeroEqItemBlock extends ItemBlock {

    private static Actor pulsatingActor;

    private Image bg;
    private ItemTypeEnum requiredCategory;

    static {
        pulsatingActor = new Actor();

    }

    public HeroEqItemBlock(CoordsFloat pos, float size, ItemTypeEnum requiredCategory) {
        super(pos, size);
        bg = new Image(GraphicsManager.getItemCategoryIcon(requiredCategory));
        bg.setSize(size, size);
        bg.setPosition(pos.x, pos.y);

        this.requiredCategory = requiredCategory;
    }

    public static float getPulsating() {
        return pulsatingActor.getColor().a;
    }

    public void draw(Batch batch, AbstractItem selectedItem) {
        if (selectedItem == null) {
            if (getItem() == null) {
                bg.draw(batch, 1f);
            }
            else {
                super.draw(batch, null, 1f);
            }
        }
        else {
            if (getItem() == null) {
                if (selectedItem.getItemTypeEnum() == requiredCategory) {
                    bg.draw(batch, pulsatingActor.getColor().a);
                }
                else {
                    bg.draw(batch, 1f);
                }
            }
            else {
                if (selectedItem.getItemTypeEnum() == requiredCategory) {
                    super.draw(batch, selectedItem, selectedItem == getItem() ? 1f : pulsatingActor.getColor().a);
                }
                else {
                    super.draw(batch, selectedItem,  1f);
                }
            }
        }
        batch.getColor().a = 1f;
    }

    @Override
    public boolean isTap(float x, float y) {
        return super.isTap(x, y);
    }

    public boolean isTapWithItem(float x, float y, AbstractItem item) {
        if (item.getItemTypeEnum() == requiredCategory) {
            return isTap(x, y);
        }
        return false;
    }

    public ItemTypeEnum getRequiredItem() {
        return requiredCategory;
    }

    public static void act() {
        pulsatingActor.act(Gdx.graphics.getDeltaTime());
    }

    public static void resetPulsating() {
        pulsatingActor.act(Gdx.graphics.getDeltaTime());
        pulsatingActor.clearActions();
        pulsatingActor.getColor().a = 1f;
        pulsatingActor.addAction(Actions.forever(
                Actions.sequence(
                        Actions.fadeOut(0.7f), Actions.fadeIn(0.7f))));
    }
}
