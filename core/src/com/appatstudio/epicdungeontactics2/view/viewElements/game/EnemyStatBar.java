package com.appatstudio.epicdungeontactics2.view.viewElements.game;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosText;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosTextWithIcon;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class EnemyStatBar {

    private static SpriteDrawable hpBarBg;
    private static SpriteDrawable hpBar;
    private static CoordsFloat wholeBarOffset;
    private static CoordsFloat getHpBarOffset;
    private static CoordsFloat textOffset;

    private static float maxHpW;
    private static float hph;

    private int maxHp;
    private int currHp;
    private RelativePosTextWithIcon textObject;

    private CharacterDrawable character;

    static {

    }

    public EnemyStatBar(CharacterDrawable character) {
        this.character = character;

        maxHp = character.getStats().getMaxHp();
        currHp = character.getStats().getCurrHp();

        textObject = new RelativePosTextWithIcon(
                GraphicsManager.getGuiElement(
                        character.getStats().getRange() > 1 ?
                                GuiElementEnum.ENEMY_BAR_DISTANCE : GuiElementEnum.ENEMY_BAR_MELE),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getCharacterName(character.getCharacterEnum()),
                Align.left
        );
    }

    public static CoordsFloat getWholeBarOffset() {
        return wholeBarOffset;
    }

    public void draw(Batch batch) {

    }
}
