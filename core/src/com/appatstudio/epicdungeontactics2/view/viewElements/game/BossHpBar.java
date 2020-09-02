package com.appatstudio.epicdungeontactics2.view.viewElements.game;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class BossHpBar extends Image {

    private CharacterDrawable boss;
    private TextObject text;

    private CoordsFloat hpBarOffset;
    private float maxHpW;
    private float hpH;

    private static SpriteDrawable hpBar;

    public BossHpBar(CharacterDrawable boss) {
        super(GraphicsManager.getGuiElement(GuiElementEnum.LVL_EXP_BAR_BG));

        this.boss = boss;
        this.setSize(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getWidth() * 0.7f * (9/80f));
        this.setPosition(Gdx.graphics.getWidth()/2f - this.getWidth()/2f, Gdx.graphics.getHeight() * 0.8f);

        text = new TextObject(
                FontsManager.getFont(FontEnum.BOSS_BAR_NAME),
                StringsManager.getCharacterName(boss.getCharacterEnum()),
                Gdx.graphics.getWidth()/2f,
                this.getY() + this.getHeight() + this.getHeight()/3f,
                Align.center
        );

        maxHpW = this.getWidth() * (64f / 80f);
        hpH = this.getHeight() * (5f / 9f);
        hpBarOffset = new CoordsFloat(
                this.getWidth() * (8f / 80f),
                this.getHeight() * (2f / 9f)
        );

        hpBar = GraphicsManager.getGuiElement(GuiElementEnum.BOSS_BAR);
    }

    public void draw(Batch batch) {
        super.draw(batch, 1f);
        hpBar.draw(batch,
                this.getX() + hpBarOffset.x,
                this.getY() + hpBarOffset.y,
                ((float)boss.getStats().getCurrHp() / (float)boss.getStats().getMaxHp()) * maxHpW,
                hpH);
        text.draw(batch);
    }

}
