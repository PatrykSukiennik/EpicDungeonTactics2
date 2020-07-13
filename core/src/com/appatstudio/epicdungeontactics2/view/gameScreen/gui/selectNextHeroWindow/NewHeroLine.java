package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.selectNextHeroWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class NewHeroLine {

    private static final float x = Gdx.graphics.getWidth() * 0.2f;
    private static final float width = Gdx.graphics.getWidth() * 0.6f;

    private TextWithIcon iconAndName;
    private TextWithIcon lvl;

    private CharacterEnum characterEnum;
    private boolean isOk;

    NewHeroLine(CharacterEnum hero, int lvl, float y, boolean isOk) {
        this.isOk = isOk;
        this.characterEnum = hero;

        iconAndName = new TextWithIcon(
                GraphicsManager.getCharactersAnimation(hero, CharacterStateEnum.IDLE).getKeyFrame(0),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCharacterName(hero),
                x,
                y,
                Align.left
        );

        this.lvl = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                lvl + "lvl",
                x + width,
                y,
                Align.right
        );
    }

    void setUsed() {
        isOk = false;
        iconAndName.setFont(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED));
    }

    void draw(SpriteBatch batch) {
        if (isOk) batch.getColor().a = 1f;
        else batch.getColor().a = 0.5f;

        iconAndName.draw(batch);

        batch.getColor().a = 1;

        lvl.draw(batch);
    }

    boolean tap(float tapX, float tapY) {
        return x > tapX && tapX < x + width &&
                tapY > iconAndName.getIconY() && tapY < iconAndName.getIconY() + iconAndName.getIconHeight() && isOk;
    }

    CharacterEnum getCharacterEnum() {
        return characterEnum;
    }
}
