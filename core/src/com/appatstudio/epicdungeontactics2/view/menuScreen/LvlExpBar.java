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
    private float bgModifX, bgW, bgH, barW, barH, bgModifY, barModifX, barModifY;

    LvlExpBar(int exp, int expCap, float iconW) {

        bgW = iconW/2f;
        bgModifX = -bgW/2f;
        barModifX = bgModifX + (8/100f) * bgW;
        bgH = bgW * (9/100f);
        bgModifY = -bgH;
        float maxBarW = bgW * (84/100f);
        barH = (5/9f) * bgH;
        barW = ((float) exp / expCap) * maxBarW;
        barModifY = bgModifY + (2/9f) * bgH;

        bg = GraphicsManager.getGuiElement(GuiElementEnum.LVL_EXP_BAR_BG);
        bar = GraphicsManager.getGuiElement(GuiElementEnum.LVL_EXP_BAR);
    }

    public void draw(Batch batch, float centerX, float centerY) {
        bar.draw(batch, centerX + barModifX, centerY + barModifY, barW, barH);
        bg.draw(batch, centerX + bgModifX, centerY + bgModifY, bgW, bgH);
    }
}
