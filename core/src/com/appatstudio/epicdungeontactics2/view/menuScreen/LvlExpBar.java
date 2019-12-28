package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class LvlExpBar {

    private SpriteDrawable bg, bar;
    private float bgModifX, bgModifY, bgW, bgH, barW, barH, barModifX, barModifY;
    private RelativePosText lvlText;

    LvlExpBar(int exp, int expCap, int lvl) {
        this.lvlText = new RelativePosText(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(lvl),
                Align.center
        );

        barW = Gdx.graphics.getWidth() * 0.05f;
        bgW = Gdx.graphics.getWidth() * 0.08f;
        barModifX = -barW / 2f;
        bgModifX = -bgW / 2f;
        bgH = bgW * 3.5f;
        float maxBarH = bgH * 0.5f;
        barH = ((float) exp / expCap) * maxBarH;
        bgModifY = -bgW * 0.4f;
        barModifY = bgH * 0.1f;


        bg = GraphicsManager.getGuiElement(GuiElementEnum.LVL_EXP_BAR_BG);
        bar = GraphicsManager.getGuiElement(GuiElementEnum.LVL_EXP_BAR);
    }

    public void draw(Batch batch, float centerX, float centerY) {
        bg.draw(batch, centerX + bgModifX, centerY + bgModifY, bgW, bgH);
        bar.draw(batch, centerX + barModifX, centerY + barModifY, barW, barH);
        lvlText.draw(batch, centerX, centerY);
    }
}
