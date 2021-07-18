package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.statusBars;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class EffectIcon extends Image {

    private TextObject durationText = null;
    static float iconSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.02f : Gdx.graphics.getWidth() * 0.035f;

    EffectIcon(EffectEnum effect, int duration, float x, float y) {
        super(GraphicsManager.getEffectIcon(effect));

        this.setPosition(x, y);
        this.setSize(iconSize, iconSize);

        if (duration != -1)
            durationText = new TextObject(
                FontsManager.getFont(FontEnum.EFFECT_DURATION_FONT),
                Integer.toString(duration),
                x + iconSize/2f,
                y - iconSize/2f,
                Align.center);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (durationText != null) durationText.draw(batch);
    }
}
