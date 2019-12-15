package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class SelectorArrow extends Image {

    public boolean tap(float x, float y) {
        if (x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight()) {

            this.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.05f),
                    Actions.scaleTo(1f, 1f, 0.05f)));
            return true;
        }
        else return false;
    }

}
