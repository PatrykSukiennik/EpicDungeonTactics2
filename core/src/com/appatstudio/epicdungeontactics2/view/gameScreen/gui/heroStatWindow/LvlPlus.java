package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.heroStatWindow;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class LvlPlus extends Image {

    private int availablePoints = 1;
    private TextObject pointsObject;

    LvlPlus(CoordsFloat coordsFloat, float size) {
        super(GraphicsManager.getGuiElement(GuiElementEnum.STAT_PLUS));
        this.setSize(size, size);
        this.setPosition(coordsFloat.x - this.getWidth()/2f, coordsFloat.y - this.getHeight()/2f);
        this.setOrigin(Align.center);

        this.addAction(Actions.forever(
                Actions.sequence(Actions.scaleTo(1.1f, 1.1f, 0.5f), Actions.scaleTo(1f, 1f, 0.5f))
        ));

        pointsObject = new TextObject(
                FontsManager.getFont(FontEnum.LVL_POINTS_NUMBER),
                Integer.toString(availablePoints),
                this.getX() + this.getWidth()/2f,
                this.getY() + this.getHeight()/2f,
                Align.center
        );
    }

    boolean tap(float x, float y) {
        return x > this.getX() && x < this.getX() + this.getHeight() &&
                y > this.getY() && y < this.getY() + this.getHeight();
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (availablePoints == 0) {
            batch.getColor().a = 0.3f;
            super.getDrawable().draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            batch.getColor().a = 0.6f;
            //pointsObject.draw(batch);
        }
        else {
            act(Gdx.graphics.getDeltaTime());
            super.draw(batch, 1f);
            batch.getColor().a = super.getColor().a;
            pointsObject.draw(batch);
            batch.getColor().a = 1f;
        }
    }

    public void minusPoint() {
        this.availablePoints--;
    }
}