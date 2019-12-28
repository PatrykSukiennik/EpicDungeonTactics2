package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.DirectionEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class SelectorArrow extends Image {

    SelectorArrow(DirectionEnum direction) {
        super(GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE));
        this.setSize(Gdx.graphics.getWidth() / 8f, Gdx.graphics.getWidth() / 4f);
        this.setOrigin(getWidth() / 2f, getHeight() / 2f);
        this.setY(Gdx.graphics.getHeight() / 2f - this.getHeight() / 2f);

        switch (direction) {
            case RIGHT: {
                this.setX(Gdx.graphics.getWidth() * 0.95f - this.getWidth());
                break;
            }
            case LEFT: {
                this.setX(Gdx.graphics.getWidth() * 0.05f);
                break;
            }
        }

    }

    public boolean tap(float x, float y) {
        if (x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight()) {

            this.addAction(Actions.sequence(
                    Actions.scaleTo(0.8f, 0.8f, 0.05f),
                    Actions.scaleTo(1f, 1f, 0.05f)));
            return true;
        } else return false;
    }

}
