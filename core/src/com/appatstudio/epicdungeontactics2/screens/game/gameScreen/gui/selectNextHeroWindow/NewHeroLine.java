package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.selectNextHeroWindow;
import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;


public final class NewHeroLine extends Image {

    private static final float X, ICON_SIZE, WIDTH;
    private final float y;
    private boolean isAvailable;
    private final CharacterEnum characterEnum;
    private final MultiLineText title;
    private static SpriteDrawable bgAlpha = GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent);

    static {
        X = Gdx.graphics.getWidth() * 0.2f;
        WIDTH = Gdx.graphics.getWidth() * 0.6f;
        ICON_SIZE = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth() * 0.15f;
    }

    NewHeroLine(CharacterEnum characterEnum, float y) {
        super(GraphicsManager.getHead(characterEnum));
        this.y = y;
        this.characterEnum = characterEnum;

        this.setPosition(X, y);
        this.setSize(ICON_SIZE, ICON_SIZE);

        title = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCharacterName(characterEnum) + " lvl." + SavedInfoManager.getCharacterLvl(characterEnum),
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.2f,
                this.y + ICON_SIZE * 0.8f,
                Align.left
        );

        isAvailable = ! (StatTracker.getUsedCharacters().contains(characterEnum, false));

        if (!isAvailable) {
            this.getColor().a = 0.3f;
        }
    }

    CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    public void draw(Batch batch, float parentAlpha, boolean isSelected) {
        if (isSelected) bgAlpha.draw(batch, this.getX(), this.getY(), Gdx.graphics.getWidth() * 0.60f, this.getHeight());
        super.draw(batch, parentAlpha);
        title.draw(batch);
        batch.getColor().a = 1f;
    }

    public boolean tap(float x, float y) {
        return x > X && x < X + WIDTH &&
                y > this.y && y < this.y + ICON_SIZE;
    }

    static float getIconHeight() {
        return ICON_SIZE;
    }
    public static float getIconSize() {
        return ICON_SIZE;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
