package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentWindow;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ItemSegment extends AbstractSegment {

    ButtonWithText deleteButton;
    AbstractItem selectedItem;

    private static float posY;

    ItemSegment() {
        posY = Gdx.graphics.getHeight()/2f - AbstractSegment.getFullHeight()/2f - AbstractSegment.fullHeight * 1.1f;
        bg = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
    }

    void draw(Batch batch) {
        super.draw(batch);
    }

    boolean tap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && posY < posY + fullHeight;
    }

    void selectItem(AbstractItem item) {
        this.selectedItem = item;
    }

    boolean isDrop(float x, float y) {
        return deleteButton.tap(x, y);
    }


    public boolean isTap(float x, float y) {
        return x > posX && x < posX + fullWidth
                && y > posY && posY < posY + fullHeight;
    }
}
