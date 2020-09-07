package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.heroStatWindow;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class StatPlus extends Image {

    private StatisticEnum statisticEnum;

    StatPlus(StatisticEnum statisticEnum, CoordsFloat coordsFloat, float size) {
        super(GraphicsManager.getGuiElement(GuiElementEnum.STAT_PLUS));
        this.setSize(size, size);
        this.setPosition(coordsFloat.x - this.getWidth()/2f, coordsFloat.y - this.getHeight()/2f);
        this.setOrigin(Align.center);
        this.statisticEnum = statisticEnum;

        this.addAction(Actions.forever(
                Actions.sequence(Actions.scaleTo(1.1f, 1.1f, 0.5f), Actions.scaleTo(1f, 1f, 0.5f))
        ));
    }

    boolean tap(float x, float y) {
        return x > this.getX() && x < this.getX() + this.getHeight() &&
                y > this.getY() && y < this.getY() + this.getHeight();
    }

    public StatisticEnum getStatisticEnum() {
        return statisticEnum;
    }
}
