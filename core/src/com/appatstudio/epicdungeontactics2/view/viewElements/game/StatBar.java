package com.appatstudio.epicdungeontactics2.view.viewElements.game;

import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.CameraHandler;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosTextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class StatBar {

    private SpriteDrawable hpBarBg;
    private SpriteDrawable hpBar;
    private CoordsFloat wholeBarSize;
    private CoordsFloat hpBarOffset;
    private float maxHpW;
    private float hpH;

    private boolean isActive = true;

    private Vector3 posBar;

    private RelativePosTextWithIcon textObject;

    private CharacterDrawable character;



    public StatBar(CharacterDrawable character) {
        this.character = character;

        if (character.getCharacterEnum().toString().startsWith("BOSS")) isActive = false;
        else {
            int size = CharacterStats.getCharacterSize(character.getCharacterEnum());


            if (character.getCharacterEnum().toString().startsWith("PET")) {
                hpBarBg = GraphicsManager.getGuiElement(GuiElementEnum.PET_BAR_BG);
                hpBar = GraphicsManager.getGuiElement(GuiElementEnum.PET_BAR);


                textObject = new RelativePosTextWithIcon(
                        GraphicsManager.getGuiElement(
                                character.getStats().getRange() > 1 ?
                                        GuiElementEnum.BAR_DISTANCE : GuiElementEnum.BAR_MELE),
                        FontsManager.getFont(FontEnum.PET_BAR_NAME),
                        StringsManager.getCharacterName(character.getCharacterEnum()),
                        Align.center
                );
            } else {
                hpBarBg = GraphicsManager.getGuiElement(GuiElementEnum.ENEMY_BAR_BG);
                hpBar = GraphicsManager.getGuiElement(GuiElementEnum.ENEMY_BAR);

                textObject = new RelativePosTextWithIcon(
                        GraphicsManager.getGuiElement(
                                character.getStats().getRange() > 1 ?
                                        GuiElementEnum.BAR_DISTANCE : GuiElementEnum.BAR_MELE),
                        FontsManager.getFont(FontEnum.ENEMY_BAR_NAME),
                        StringsManager.getCharacterName(character.getCharacterEnum()),
                        Align.center
                );
            }

            wholeBarSize = new CoordsFloat(
                    Gdx.graphics.getWidth() * 0.15f * size,
                    Gdx.graphics.getWidth() * 0.17f * (9 / 80f) * size
            );
            hpBarOffset = new CoordsFloat(
                    wholeBarSize.x * (8f / 80f),
                    wholeBarSize.y * (2f / 9f)
            );

            maxHpW = wholeBarSize.x * (64f / 80f);
            hpH = wholeBarSize.y * (5f / 9f);
        }
    }

    public void refreshPos() {

        posBar = new Vector3(
                character.getX() + character.getWidth() * 0.5f,
                character.getY() + character.getHeight() * 0.55f,
                0
        );

        posBar = CameraHandler.projectCoords(posBar);
    }

    public void draw(Batch batch) {
        if (isActive) {
            batch.getColor().a = 1f;

            refreshPos();

            float posYmodif = character.getHeight() / CameraHandler.getZoom();

            hpBar.draw(
                    batch,
                    posBar.x - wholeBarSize.x / 2f + hpBarOffset.x,
                    posBar.y - wholeBarSize.y + hpBarOffset.y + posYmodif,
                    ((float) character.getStats().getCurrHp() / (float) character.getStats().getMaxHp()) * maxHpW,
                    hpH
            );

            hpBarBg.draw(
                    batch,
                    posBar.x - wholeBarSize.x / 2f,
                    posBar.y - wholeBarSize.y + posYmodif,
                    wholeBarSize.x,
                    wholeBarSize.y
            );

            textObject.draw(batch, posBar.x, posBar.y + wholeBarSize.y + posYmodif);
        }
    }
}
