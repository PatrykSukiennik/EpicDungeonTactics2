package com.appatstudio.epicdungeontactics2.view.viewElements;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class IdleGoldCollectedBanner extends Image {

    private static final float MAX_TIME = 4f;

    private static SpriteBatch batch;

    private static RelativePosTextWithIcon incomeText;
    private static float time = 0;

    public IdleGoldCollectedBanner() {
        super(GraphicsManager.getGuiElement(GuiElementEnum.IDLE_INCOME_BANNER));
        batch = new SpriteBatch();
    }

    public void show(int income) {
        time = MAX_TIME;

        clearActions();

        incomeText = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.COMMUNICATE_WHITE),
                StringsManager.getGuiString(GuiStringEnum.IDLE_INCOME_COLLECTED) + " " + income,
                Align.right);

        this.setSize(incomeText.getWidth() + incomeText.getHeight() * 1.5f, incomeText.getHeight() * 2f);
        this.setPosition(
                -this.getWidth(),
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f - this.getHeight() * 2);
        this.act(Gdx.graphics.getDeltaTime());

        this.addAction(Actions.moveBy(this.getWidth(), 0, 0.5f));
    }

    public void draw() {
        if (time > 0) {
            batch.begin();

            time -= Gdx.graphics.getDeltaTime();

            this.act(Gdx.graphics.getDeltaTime());

            super.draw(batch, 1f);
            incomeText.draw(batch,
                    this.getX() + this.getWidth() - incomeText.getHeight(),
                    this.getY() + this.getHeight()/2f - FontsManager.getTextHeight(FontsManager.getFont(FontEnum.COMMUNICATE_WHITE), "0")/2f);

            batch.end();
        }
    }
}
