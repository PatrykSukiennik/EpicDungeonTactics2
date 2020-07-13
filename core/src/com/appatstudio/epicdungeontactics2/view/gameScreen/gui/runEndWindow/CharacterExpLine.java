package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.runEndWindow;

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

public class CharacterExpLine {

    private static final float x = Gdx.graphics.getWidth() * 0.2f;
    private static final float width = Gdx.graphics.getWidth() * 0.6f;

    private TextWithIcon iconAndName;
    private TextWithIcon expAndLvl;

    CharacterExpLine(CharacterEnum hero, int exp, boolean lvlUp, float y) {
        iconAndName = new TextWithIcon(
                GraphicsManager.getCharactersAnimation(hero, CharacterStateEnum.IDLE).getKeyFrame(0),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCharacterName(hero),
                x,
                y,
                Align.left
        );

         expAndLvl = new TextWithIcon(
                lvlUp ? GraphicsManager.getGuiElement(GuiElementEnum.HEAD_MOUNTAIN_KING) : null,
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                "+" + exp + " exp",
                x + width,
                y,
                Align.right
        );
    }

    void draw(SpriteBatch batch) {
        iconAndName.draw(batch);
        expAndLvl.draw(batch);
    }

}
