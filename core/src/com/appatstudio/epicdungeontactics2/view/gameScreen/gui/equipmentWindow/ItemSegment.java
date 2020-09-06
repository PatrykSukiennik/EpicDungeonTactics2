package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

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
        for (int i = 0; i < ROWS; i++) {
            leftColumn[i] = new TextObject(
                    FontsManager.getFont(FontEnum.ITEM_DESC),
                    "test",
                    itemDrawable.getX() + itemDrawable.getWidth() * 1.4f,
                    itemDrawable.getY() + itemDrawable.getHeight() * 0.4f -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.ITEM_DESC), "0") * i * 1.7f,
                    Align.left
            );
        }

        rightColumn = new TextWithIcon[ROWS];
        for (int i = 0; i < ROWS; i++) {
            rightColumn[i] = new TextWithIcon(
                    GraphicsManager.getStatIcon(StatisticEnum.STR),
                    FontsManager.getFont(FontEnum.ITEM_DESC),
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
