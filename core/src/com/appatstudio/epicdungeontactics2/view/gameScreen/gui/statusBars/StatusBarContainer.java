package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.GuiContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public final class StatusBarContainer {

    private static Image heroHead, barsBg, barsBorder, hpBar, expBar;
    private static Array<EffectIcon> effects;

    private static float headSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth() * 0.15f;

    public StatusBarContainer(CharacterEnum hero) {
        SpriteDrawable headIcon = null;
        switch (hero) {
            case HERO_ELF: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_ELF); break;
            case HERO_KNIGHT: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_KNIGHT); break;
            case HERO_LIZARD: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_LIZARD); break;
            case HERO_WIZZARD: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_WIZARD); break;
            case HERO_NINJA: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_NINJA); break;
            case HERO_PIRATE: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_PIRATE); break;
            case HERO_BABY: headIcon = GraphicsManager.getGuiElement(GuiElementEnum.HEAD_BABY); break;
        }
        heroHead = new Image(headIcon);
        heroHead.setSize(headSize, headSize);
        heroHead.setPosition(GuiContainer.guiMargin, Gdx.graphics.getHeight() - heroHead.getWidth() - GuiContainer.guiMargin);

        barsBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BARS_BG));
        barsBg.setSize(heroHead.getWidth() * (3f/2), heroHead.getHeight() * (7f/16));
        barsBg.setPosition(heroHead.getX() + heroHead.getWidth(), heroHead.getY() + heroHead.getHeight() * (8f/16));

        barsBorder = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BARS_BORDER));
        barsBorder.setSize(heroHead.getWidth() * (3f/2), heroHead.getHeight() * (7f/16));
        barsBorder.setPosition(heroHead.getX() + heroHead.getWidth(), heroHead.getY() + heroHead.getHeight() * (8f/16));

        hpBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HP_BAR));
        hpBar.setSize(barsBg.getWidth() * (63f/72f), barsBg.getHeight() * (5f/16));
        hpBar.setPosition(barsBg.getX() + barsBg.getX() * (1f/72f), barsBg.getY() + barsBg.getHeight() * (9f/16));

        expBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.EXP_BAR));
        expBar.setSize(barsBg.getWidth() * (53f/72f), barsBg.getHeight() * (5f/16));
        expBar.setPosition(barsBg.getX() + barsBg.getX() * (1f/72f), barsBg.getY() + barsBg.getHeight() * (3f/16));
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
        hpBar.setSize((barsBg.getWidth() * (225f/240)) * hpPercent, barsBg.getHeight() * (5f/16));
        hpBar.act(Gdx.graphics.getDeltaTime());
    }

    public static void setExp(float expPercent) {
        expBar.setSize((barsBg.getWidth() * (195f/240)) * expPercent, barsBg.getHeight() * (5f/16));
        expBar.act(Gdx.graphics.getDeltaTime());
    }

    public static void addEffect(EffectEnum effect, int duration) {
        effects.add(
                new EffectIcon(
                        effect,
                        duration,
                        heroHead.getX() + heroHead.getWidth() + EffectIcon.iconSize/3f + (EffectIcon.iconSize * 1.05f) * effects.size,
                        barsBg.getY() - EffectIcon.iconSize/3f - EffectIcon.iconSize));
    }

    public float getBottomY() {
        return heroHead.getY();
    }

    public boolean isTap(float x, float y) {
        return x > heroHead.getX() && x < heroHead.getX() + heroHead.getWidth()
                && y > heroHead.getY() && y < heroHead.getY() + heroHead.getHeight();
    }
}
