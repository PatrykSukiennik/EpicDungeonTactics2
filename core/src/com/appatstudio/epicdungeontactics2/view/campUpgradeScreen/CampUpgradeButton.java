package com.appatstudio.epicdungeontactics2.view.campUpgradeScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import lombok.Getter;

final class CampUpgradeButton {

    private static final float size = (Gdx.graphics.getWidth()*1.001f)/ CampUpgradeEnum.values().length;
    private final float x;
    @Getter private final CampUpgradeEnum upgradeEnum;

    private SpriteDrawable bg;
    private TextObject lvlText;

    CampUpgradeButton(int index, CampUpgradeEnum upgradeEnum) {
        this.upgradeEnum = upgradeEnum;
        int lvl = SavedInfoManager.getNpcLvl(upgradeEnum);

        if (lvl == 0) {
            switch (upgradeEnum) {
                case BUTCHER:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_BUTCHER_ZERO);
                    break;
                case ALCHEMIST:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_ALCHEMIST_ZERO);
                    break;
                case PRINCESS:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_PRINCESS_ZERO);
                    break;
                case LUGGAGE_CARRIAGE:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_LUGGAGE_CARRIAGE_ZERO);
                    break;
                case BLACKSMITH:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_BLACKSMITH_ZERO);
                    break;
                case MAGIC_SHOP:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MAGIC_SHOP_ZERO);
                    break;
                case MOUNTAIN_KING:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING_ZERO);
                    break;
            }
        }
        else {
            switch (upgradeEnum) {
                case BUTCHER:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_BUTCHER);
                    break;
                case ALCHEMIST:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_ALCHEMIST);
                    break;
                case PRINCESS:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_PRINCESS);
                    break;
                case LUGGAGE_CARRIAGE:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_LUGGAGE_CARRIAGE);
                    break;
                case BLACKSMITH:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_BLACKSMITH);
                    break;
                case MAGIC_SHOP:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MAGIC_SHOP);
                    break;
                case MOUNTAIN_KING:
                    bg = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING);
                    break;
            }
        }
        x = index*size;

        if (lvl != 0) {
            lvlText = new TextObject(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), Integer.toString(lvl), x + size * (13f/16f), size * (3f/16f), Align.center);
        }
    }

    boolean tap(float tapX, float tapY) {
        return tapX > x && tapX < x + size && tapY < size;
    }

    void draw(Batch batch) {
        bg.draw(batch, x, 0, size, size);

        if (lvlText != null) {
            lvlText.draw(batch);
        }
    }

}
