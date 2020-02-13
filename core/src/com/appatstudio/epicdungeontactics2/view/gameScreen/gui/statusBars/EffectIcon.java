package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public final class EffectIcon extends ButtonWithText {

    static float iconSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.05f;

    EffectIcon(EffectEnum effect, int duration, float x, float y) {
        super(GraphicsManager.getEffectIcon(effect),
                x, y,
                iconSize,
                iconSize,
                FontsManager.getFont(FontEnum.EFFECT_DURATION_FONT),
                Integer.toString(duration));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
