package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.spellBook;

import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class KnownSpell extends Image {

    SpellEnum spellEnum;
    SpriteDrawable icon;
    int duration;
    int mpCost;
    int dmg;
    boolean isEnoughMP;
    TextObject text;
    String mpCostText;
    static BitmapFont textFont;
    static BitmapFont mpCostFont;

    static {
        textFont = FontsManager.getFont(FontEnum.ITEM_DESC);
        mpCostFont = FontsManager.getFont(FontEnum.MP_COST_FONT);
    }

    public KnownSpell(SpellEnum spellEnum, int duration, int mpCost, int dmg, SpriteDrawable icon) {
        this.spellEnum = spellEnum;
        this.duration = duration;
        this.mpCost = mpCost;
        this.dmg = dmg;
        this.icon = icon;

        mpCostText = Integer.toString(mpCost);
        isEnoughMP = StatTracker.getCurrentStat(CompleteHeroStatsEnum.MP) >= mpCost;

    }

    public void setY(float y) {
        text = new TextObject(
                textFont,
                StringsManager.getSpellNameFull(spellEnum) + (isEnoughMP ? "" : " | " + StringsManager.getGuiString(GuiStringEnum.NOT_ENOUGH_MP)),
                this.getX() + this.getWidth() * 1.2f,
                this.getY() + this.getHeight()/2f,
                Align.left);
    }

    public void draw(Batch guiBatch, boolean isSelected) {
        guiBatch.getColor().a = isSelected ? 1f : 0.5f;

        //dunno why its not working
        //super.draw(guiBatch, 1f);
        this.icon.draw(guiBatch,this.getX(), this.getY(), this.getWidth(), this.getWidth());

        if (isSelected) {
            mpCostFont.draw(guiBatch, mpCostText, this.getX(), this.getY());
            text.draw(guiBatch);
        }
        guiBatch.getColor().a = 1f;

    }

    boolean isTap(float x, float y) {
        return
                x > this.getX() - this.getWidth() * 0.43f
                && x < this.getX() + this.getWidth() + this.getWidth() * 0.43f
                && y < this.getY() + this.getHeight() + this.getWidth() * 0.43f
                && y > this.getY() - this.getWidth() * 0.43f;
    }

    public int getMpCost() {
        return mpCost;
    }

    public int getDuration() {
        return duration;
    }

    public int getDmg() {
        return dmg;
    }

    public SpellEnum getSpell() {
        return spellEnum;
    }

    public void refreshIsEnoughMP() {
        isEnoughMP = StatTracker.getCurrentStat(CompleteHeroStatsEnum.MP) >= mpCost;
        text.setText(StringsManager.getSpellNameFull(spellEnum) + (isEnoughMP ? "" : " | " + StringsManager.getGuiString(GuiStringEnum.NOT_ENOUGH_MP)));
    }
}
