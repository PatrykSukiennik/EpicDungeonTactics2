package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class PerkIcon extends Image {

    private static final float LOADING_TIME = 0.5f;
    private static final float X, HEIGHT, WIDTH;

    private float currentLoadingTime = 0f;
    private static Texture loadingTexture;

    private float y;
    private PerkEnum perkEnum;
    private MultiLineText title, description;
    private boolean isSelected = false;

    static {
        X = Gdx.graphics.getWidth() * 0.15f;
        WIDTH = Gdx.graphics.getWidth() * 0.7f;
        HEIGHT = Gdx.graphics.getWidth() / 8f;
    }

    PerkIcon(int pos, PerkEnum perkEnum) {
        y = Gdx.graphics.getHeight()/2f - (HEIGHT * PerkEnum.values().length)/2f + (pos * HEIGHT);
        this.perkEnum = perkEnum;
    }

    public boolean tap(float x, float y) {
        return x > X && x < X + WIDTH &&
            y > this.y && y < this.y + HEIGHT;
    }

    public PerkEnum getPerkEnum() {
        return perkEnum;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
