package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Armor;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Arrow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Book;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Bow;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Food;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Helmet;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.MeleWeapon;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Necklace;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Ring;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Shield;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.Staff;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class ItemSegment extends AbstractSegment {

    private AbstractItem selectedItem;
    private Image itemDrawable;
    private ButtonWithText dropButton;

    private HashMap<ItemRarityEnum, TextObject> titles;

    private TextObject[] leftColumn;
    private TextWithIcon[] rightColumn;

    private final int ROWS = 4;

    private static float posY;

    ItemSegment() {
        posY = Gdx.graphics.getHeight() / 2f - AbstractSegment.getFullHeight() / 2f - AbstractSegment.fullHeight;
        bg = GraphicsManager.getGuiElement(GuiElementEnum.SEGMENT_ITEM);

        itemDrawable = new Image();
        itemDrawable.setSize(fullHeight * 0.3f, fullHeight * 0.3f);
        itemDrawable.setPosition(posX + fullHeight * 0.22f, posY + fullHeight * 0.5f);

        dropButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.NONE),
                itemDrawable.getX() - itemDrawable.getWidth() * 0.25f,
                posY + fullHeight * 0.1f,
                itemDrawable.getWidth() * 1.5f,
                itemDrawable.getWidth(),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.DROP)
        );

        titles = new HashMap<>();
        ItemRarityEnum[] allRarities = ItemRarityEnum.values();
        for (ItemRarityEnum r : allRarities) {
            titles.put(
                    r,
                    new TextObject(
                            FontsManager.getFont(r),
                            "",
                            posX + itemDrawable.getWidth() + (fullWidth - itemDrawable.getWidth()) / 2f,
                            itemDrawable.getY() + itemDrawable.getHeight() * 0.9f,
                            Align.center));
        }

        leftColumn = new TextObject[ROWS];
        for (int i = 0; i < ROWS-1; i++) {
            leftColumn[i] = new TextObject(
                    FontsManager.getFont(FontEnum.SMALL_STAT_DESC),
                    "test",
                    itemDrawable.getX() + itemDrawable.getWidth() * 1.4f,
                    itemDrawable.getY() + itemDrawable.getHeight() * 0.4f -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.ITEM_DESC), "0") * i * 1.7f,
                    Align.left
            );
        }
        leftColumn[ROWS-1] = new TextObject(
                FontsManager.getFont(FontEnum.ITEM_DESC_YELLOW),
                "test",
                itemDrawable.getX() + itemDrawable.getWidth() * 1.4f,
                itemDrawable.getY() + itemDrawable.getHeight() * 0.4f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.ITEM_DESC), "0") * (ROWS-1) * 1.7f,
                Align.left
        );

        rightColumn = new TextWithIcon[ROWS];
        for (int i = 0; i < ROWS; i++) {
            rightColumn[i] = new TextWithIcon(
                    GraphicsManager.getStatIcon(StatisticEnum.STR),
                    FontsManager.getFont(FontEnum.SMALL_STAT_DESC),
                    "test",
                    posX + fullWidth * 0.91f,
                    itemDrawable.getY() + itemDrawable.getHeight() * 0.4f -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.ITEM_DESC), "0") * i * 1.7f,
                    Align.right
            );
        }
    }

    void draw(Batch batch) {
        super.draw(batch);
        bg.draw(batch, posX, posY, fullWidth, fullHeight);
        itemDrawable.draw(batch, 1f);
        dropButton.draw(batch, 1f);
        titles.get(selectedItem.getRarity()).draw(batch);

        for (int i = 0; i < ROWS; i++) {
            leftColumn[i].draw(batch);
            rightColumn[i].draw(batch);
        }
    }

    boolean tap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && posY < posY + fullHeight;
    }

    void selectItem(AbstractItem item) {
        this.selectedItem = item;
        if (item != null) {
            itemDrawable.setDrawable(GraphicsManager.getItemImage(item.getItemEnum()));
            titles.get(item.getRarity()).setText(StringsManager.getItemName(item.getItemEnum()));

            switch (item.getItemTypeEnum()) {
                case ARMOR:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.ARMOR) + " " + ((Armor)item).getArmor());
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.MOVE_SPEED_COST) + " " + ((Armor)item).getMoveSpeedCost());
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsArmor = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsArmor != null && i < effectsArmor.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsArmor.get(i).getEffectEnum()),
                                    effectsArmor.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsArmor.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }

                    break;
                case ARROW:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.DMG_EFFECT) + " " + ((Arrow)item).getDmgEffect());
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.RANGE_EFFECT) + " " + ((Arrow)item).getRangeEffect());
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsArrow = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsArrow != null && i < effectsArrow.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsArrow.get(i).getEffectEnum()),
                                    effectsArrow.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsArrow.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case BOOK:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.EXP_EFFECT) + " " + ((Book)item).getExpEffect());
                    leftColumn[1].setText("");
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    for (TextWithIcon t1 : rightColumn) t1.setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                    break;
                case BOW:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.DISTANCE_DMG) + " " + ((Bow)item).getDmg());
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.RANGE_EFFECT) + " " + ((Bow)item).getRange());
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsBow = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsBow != null && i < effectsBow.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsBow.get(i).getEffectEnum()),
                                    effectsBow.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsBow.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case FOOD:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.HP_EFFECT) + " " + (((Food)item).getHpEffect() != 0 ? ((Food)item).getHpEffect() : ""));
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.MP_EFFECT) + " " + (((Food)item).getMpEffect() != 0 ? ((Food)item).getMpEffect() : ""));
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    for (TextWithIcon t2 : rightColumn) t2.setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                    break;
                case HELMET:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.ARMOR) + " " + ((Helmet)item).getArmor());
                    leftColumn[1].setText("");
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsHelmet = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsHelmet != null && i < effectsHelmet.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsHelmet.get(i).getEffectEnum()),
                                    effectsHelmet.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsHelmet.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case NECKLACE:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.ARMOR) + " " + ((Necklace)item).getArmor());
                    leftColumn[1].setText("");
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsNecklace = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsNecklace != null && i < effectsNecklace.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsNecklace.get(i).getEffectEnum()),
                                    effectsNecklace.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsNecklace.get(i).getEffectEnum()));
                        } else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                            break;
                        }
                    }
                case RING:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.ARMOR) + " " + ((Ring)item).getArmor());
                    leftColumn[1].setText("");
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsRing = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsRing != null && i < effectsRing.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsRing.get(i).getEffectEnum()),
                                    effectsRing.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsRing.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case SHIELD:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.ARMOR) + " " + ((Shield)item).getArmor());
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.MOVE_SPEED_COST) + " " + ((Shield)item).getSpeedEffect());
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsShield = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsShield != null && i < effectsShield.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsShield.get(i).getEffectEnum()),
                                    effectsShield.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsShield.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case STAFF:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.DISTANCE_DMG) + " " + ((Staff)item).getDmg());
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.MOVE_SPEED_COST) + " " + ((Staff)item).getSpeedEffect());
                    leftColumn[2].setText((int)(((Staff)item).getSpellChance() * 100) + "% " + StringsManager.getGuiString(GuiStringEnum.CHANCE_FOR) + " " + StringsManager.getSpellName(((Staff)item).getSpell()));
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsStaff = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsStaff != null && i < effectsStaff.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsStaff.get(i).getEffectEnum()),
                                    effectsStaff.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsStaff.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case MELE:
                    leftColumn[0].setText(StringsManager.getGuiString(GuiStringEnum.MELE_DMG) + " " + ((MeleWeapon)item).getDmg());
                    leftColumn[1].setText(StringsManager.getGuiString(GuiStringEnum.MOVE_SPEED_COST) + " " + ((MeleWeapon)item).getSpeedEffect());
                    leftColumn[2].setText("");
                    leftColumn[3].setText(StringsManager.getGuiString(GuiStringEnum.VALUE) + " " + item.getValue());

                    Array<ItemEffect> effectsMele = item.getEffects();
                    for (int i=0; i<ROWS; i++) {
                        if (effectsMele != null && i < effectsMele.size) {
                            rightColumn[i].setIconAndFont(
                                    GraphicsManager.getItemEffectIcon(effectsMele.get(i).getEffectEnum()),
                                    effectsMele.get(i).getPower() + " " + StringsManager.getItemEffectDescription(effectsMele.get(i).getEffectEnum()));
                        }
                        else {
                            rightColumn[i].setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                        }
                    }
                    break;
                case OTHER:
                    for (TextObject tl : leftColumn) tl.setText("");
                    for (TextWithIcon tr : rightColumn) tr.setIconAndFont(GraphicsManager.getGuiElement(GuiElementEnum.NONE), "");
                    break;
            }
        }
    }

    boolean isDrop(float x, float y) {
        return dropButton.tap(x, y);
    }


    public boolean isTap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && y < posY + fullHeight;
    }
}
