package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public final class StatusBarContainer {

    private static Image heroHead, barsBg, barsBorder, hpBar, expBar;
    private static Array<EffectIcon> effects;

    public StatusBarContainer(CharacterEnum hero) {
        SpriteDrawable headIcon = null;
        switch (hero) {
            case HERO_ELF: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_ELF); break;
            case HERO_LIZARD: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_LIZARD); break;
            case HERO_WIZZARD: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_WIZARD); break;
            case HERO_NINJA: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_NINJA); break;
            case HERO_PIRATE: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_PIRATE); break;
            case HERO_BABY: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_BABY); break;
        }
        heroHead = new Image(headIcon);
        heroHead.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getWidth() * 0.2f);
        heroHead.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() - heroHead.getWidth() - Gdx.graphics.getWidth() * 0.05f);

        barsBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BARS_BG));
        barsBg.setSize(heroHead.getWidth() * (3f/2), heroHead.getHeight() * (7f/16));
        barsBg.setPosition(heroHead.getX() + heroHead.getWidth() * (15f/16), heroHead.getY() + heroHead.getHeight() * (8f/16));

        barsBorder = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BARS_BORDER));
        barsBorder.setSize(heroHead.getWidth() * (3f/2), heroHead.getHeight() * (7f/16));
        barsBorder.setPosition(heroHead.getX() + heroHead.getWidth() * (15f/16), heroHead.getY() + heroHead.getHeight() * (8f/16));

        hpBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HP_BAR));
        hpBar.setSize(barsBg.getWidth() * (225f/240), barsBg.getHeight() * (2f/7));
        hpBar.setPosition(barsBg.getX() * (25f/24), barsBg.getY() + barsBg.getHeight() * (4f/7));

        expBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.EXP_BAR));
        expBar.setSize(barsBg.getWidth() * (195f/240), barsBg.getHeight() * (2f/7));
        expBar.setPosition(barsBg.getX() * (25f/24), barsBg.getY() + barsBg.getHeight() * (1f/7));
    }

    static {
        effects = new Array<>();
    }

    public void draw(Batch batch) {
        barsBg.draw(batch, 1f);
        hpBar.draw(batch, 1f);
        expBar.draw(batch, 1f);
        barsBorder.draw(batch, 1f);
        heroHead.draw(batch, 1f);

        for (EffectIcon e : effects) {
            e.draw(batch, 1f);
        }
    }

    public static void setHp(float hpPercent) {
        hpBar.setSize((barsBg.getWidth() * (225f/240)) * hpPercent, barsBg.getHeight() * (2f/7));
        hpBar.act(Gdx.graphics.getDeltaTime());
    }

    public static void setExp(float expPercent) {
        expBar.setSize((barsBg.getWidth() * (195f/240)) * expPercent, barsBg.getHeight() * (2f/7));
        expBar.act(Gdx.graphics.getDeltaTime());
    }

    public static void addEffect(EffectEnum effect, int duration) {
        effects.add(
                new EffectIcon(
                        effect,
                        duration,
                        heroHead.getX() + (heroHead.getWidth() * 1.05f) + (heroHead.getWidth() * 0.08f + Gdx.graphics.getWidth() * 0.05f) * effects.size,
                        heroHead.getY() + heroHead.getWidth() * 0.2f));
    }

}
