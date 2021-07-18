package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.weaponSelector;

import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class WeaponSelector {

    private static boolean isMele = false;
    private static boolean isDistance = false;
    private static boolean isUp = false;
    private static Image imageMele, imageDistance;
    private static CharacterDrawable target;
    private static final float blockSize = Gdx.graphics.getWidth() * 0.15f;

    public WeaponSelector() {
        imageMele = new Image();
        imageDistance = new Image();

        imageMele.setSize(blockSize, blockSize);
        imageDistance.setSize(blockSize, blockSize);
    }

    public void show(Hero heroInRoom, CharacterDrawable target) {
        this.target = target;

        isMele = heroInRoom.getAttackableTiles().contains(target.getTileStandingOn(), false);
        isDistance =
                Vector2.dst(
                heroInRoom.getPosition().x,
                heroInRoom.getPosition().y,
                target.getPosition().x,
                target.getPosition().y
                ) <= StatTracker.getCurrentStat(CompleteHeroStatsEnum.RANGE)
                && StatTracker.getRangedWeapon() != null;

        if (isMele) {
            AbstractItem meleWeapon = StatTracker.getMeleWeapon();
            if (meleWeapon != null) {
                imageMele.setDrawable(GraphicsManager.getItemImage(meleWeapon.getItemEnum()));
                imageMele.setPosition(Gdx.graphics.getWidth()/2f - blockSize/2f, Gdx.graphics.getHeight() * 0.2f);
            } else {
                imageMele.setDrawable(GraphicsManager.getGuiElement(GuiElementEnum.MELE_PUNCH));
            }
        }
        if (isDistance) {
            imageDistance.setDrawable(GraphicsManager.getItemImage(StatTracker.getRangedWeapon().getItemEnum()));
            imageDistance.setPosition(Gdx.graphics.getWidth()/2f - blockSize/2f, Gdx.graphics.getHeight() * 0.2f);
        }

        if (isMele && isDistance) {
            imageMele.setPosition(Gdx.graphics.getWidth()/2f - blockSize * 1.2f, Gdx.graphics.getHeight() * 0.2f);
            imageDistance.setPosition(Gdx.graphics.getWidth()/2f + blockSize * 0.2f, Gdx.graphics.getHeight() * 0.2f);
        }
        System.out.println("SHOW WEAPON SELECOR   " + isMele + "   " + isDistance);
        isUp = true;
    }

    public void hide() {
        System.out.println("HIDE SELECTOR");
        isUp = false;
    }

    public static void draw(Batch batch) {
        if (isMele) imageMele.draw(batch, 1f);
        if (isDistance) imageDistance.draw(batch, 1f);

        System.out.println("DRAWING");
    }

    public ItemTypeEnum tap(float x, float y) {
        if (isMele && x > imageMele.getX()
                && x < imageMele.getX() + imageMele.getWidth()
                && y > imageMele.getY()
                && y < imageMele.getY() + imageMele.getHeight()) {
            hide();
            return ItemTypeEnum.MELE;
        }

        else if (isDistance && x > imageDistance.getX()
                && x < imageDistance.getX() + imageDistance.getWidth()
                && y > imageDistance.getY()
                && y < imageDistance.getY() + imageDistance.getHeight()) {
            hide();
            return ItemTypeEnum.BOW;
        }

        else {
            hide();
            return null;
        }
    }

    public static boolean isUp() {
        return isUp;
    }

    public CharacterDrawable getTarget() {
        return target;
    }
}
