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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class PerkIcon extends Image {

    private static final float X, ICON_SIZE, WIDTH;
    private float y;
    private PerkEnum perkEnum;
    private MultiLineText title, description;

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
                StringsManager.getPerkName(perkEnum) + " lvl." + lvl,
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.2f,
                this.y + ICON_SIZE * 0.9f,
                Align.left
        );

        String descEnd = StringsManager.getPerkDescription(perkEnum);
        float perkStat = PerkStats.getPerkStat(perkEnum, lvl);
        String descString =
                perkStat > 1 ?
                        (int) perkStat + " " + descEnd :
                        (int) (perkStat * 100) + "% " + descEnd;

        description = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                descString,
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.6f,
                this.y + ICON_SIZE * 0.55f,
                Align.left
        );
    }

    PerkEnum getPerkEnum() {
        return perkEnum;
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

    static float getIconHeight() {
        return ICON_SIZE;
    }
}
