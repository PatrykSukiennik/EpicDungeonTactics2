package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runQuitWindow;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class RunQuitWindow extends Image {

    private boolean isUp = false;
    private SpriteDrawable bg;
    private TextObject yes, no;
    private MultiLineText question;

    public RunQuitWindow() {
        super(GraphicsManager.getGuiElement(GuiElementEnum.RUN_QUIT_WINDOW));

        this.getColor().a = 0.9f;
        this.setSize(Gdx.graphics.getWidth()/2f, (Gdx.graphics.getWidth()/2f)/ 2);
        this.setPosition(Gdx.graphics.getWidth()/2f - this.getWidth()/2f, Gdx.graphics.getHeight()/2f - this.getHeight()/2f);

        yes = new TextObject(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), StringsManager.getGuiString(GuiStringEnum.YES), this.getX() + this.getWidth()*0.25f, this.getY() + this.getHeight()*0.3f, Align.center);
        no = new TextObject(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), StringsManager.getGuiString(GuiStringEnum.NO), this.getX() + this.getWidth()*0.75f, this.getY() + this.getHeight()*0.3f, Align.center);

        float maxQuestionW = EpicDungeonTactics.isTablet() ? this.getWidth() * 0.6f : this.getWidth() * 0.8f;
        question = new MultiLineText(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), StringsManager.getGuiString(GuiStringEnum.QUIT_RUN_QUESTION), this.getX() + this.getWidth()/2f, maxQuestionW, this.getY() + this.getHeight() * 0.8f, Align.center);
    }

    public void tap(float x, float y) {
        if (x > this.getX() - this.getHeight()/2f && x < this.getX() + this.getX()*0.7f &&
            y > this.getY() - this.getHeight()/2f && y < this.getY() + this.getHeight()/2f) {
            EpicDungeonTactics.runEnded();
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }
        else this.hide();
    }

    private void hide() {
        isUp = false;
    }

    public void show() {
        isUp = true;
    }

    public boolean isUp() {
        return isUp;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        yes.draw(batch);
        no.draw(batch);
        question.draw(batch);
    }
}
