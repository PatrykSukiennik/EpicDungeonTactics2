package com.appatstudio.epicdungeontactics2.view.perkScreen;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.PerkStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class PerkIcon extends Image {

    private static final float X, ICON_SIZE, WIDTH;


    private float y;
    private PerkEnum perkEnum;
    private MultiLineText title, description;
    private boolean isSelected = false;

    static {
        X = Gdx.graphics.getWidth() * 0.1f;
        WIDTH = Gdx.graphics.getWidth() * 0.8f;
        ICON_SIZE = Gdx.graphics.getWidth() * 0.18f;
    }

    PerkIcon(PerkEnum perkEnum, float y) {
        super(GraphicsManager.getPerkIcon(perkEnum));
        this.y = y;
        this.perkEnum = perkEnum;

        this.setPosition(X, y);
        this.setSize(ICON_SIZE, ICON_SIZE);

        int lvl = SavedInfoManager.getPerkLvl(perkEnum);

        title = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getPerkName(perkEnum) + " lvl." + Integer.toString(lvl),
                (this.getX() + WIDTH)/2f + ICON_SIZE/2f,
                WIDTH - ICON_SIZE * 1.2f,
                this.y + ICON_SIZE * 0.85f,
                Align.center
        );

        String descEnd = StringsManager.getPerkDescription(perkEnum);
        float perkStat = PerkStats.getPerkStat(perkEnum, lvl);
        String descString =
                perkStat > 1 ?
                        Integer.toString((int)perkStat) + " " + descEnd :
                        Integer.toString((int)(perkStat * 100)) + "% " + descEnd;

        description = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                descString,
                (this.getX() + WIDTH)/2f + ICON_SIZE/2f,
                WIDTH - ICON_SIZE * 1.6f,
                this.y + ICON_SIZE * 0.6f,
                Align.center
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        title.draw(batch);
        description.draw(batch);
    }

    public boolean tap(float x, float y) {
        return x > X && x < X + WIDTH &&
            y > this.y && y < this.y + ICON_SIZE;
    }

    public PerkEnum getPerkEnum() {
        return perkEnum;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    static float getIconHeight() {
        return ICON_SIZE;
    }
}
