package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.weaponSelector;

import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.ItemSegment;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.equipmentAndShoppingWindow.backpackElements.ItemBlock;
import com.appatstudio.epicdungeontactics2.view.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.view.gameScreen.playerStatus.Backpack;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import sun.font.CoreMetrics;

public class WeaponSelector {

    private boolean isMele = false;
    private boolean isDistance = false;
    private boolean isUp = false;
    private ItemBlock imageMele, imageDistance;
    private CharacterDrawable target;
    private static final float blockSize = Gdx.graphics.getWidth() * 0.2f;

    public WeaponSelector() {
        imageMele = new ItemBlock(new CoordsFloat(0, 0), blockSize);
        imageDistance = new ItemBlock(new CoordsFloat(0, 0), blockSize);
    }

    public void show(Hero heroInRoom, CharacterDrawable target) {
        this.target = target;

        if (heroInRoom.getAttackableTiles().contains(target.getTileStandingOn(), false)) {
            isMele = true;
        }
        if (Vector2.dst(
                heroInRoom.getPosition().x,
                heroInRoom.getPosition().y,
                target.getPosition().x,
                target.getPosition().y
                ) <= StatTracker.getCurrentStat(CompleteHeroStatsEnum.RANGE)
                && StatTracker.getRangedWeapon() != null) {
            isDistance = true;
        }

        if (isMele) {
            AbstractItem meleWeapon = StatTracker.getMeleWeapon();
            if (meleWeapon != null) {
                imageMele.setItem(meleWeapon);
            } else {
                imageMele.setItem(null);
            }
        }
        if (isDistance) {
            imageDistance.setItem(StatTracker.getRangedWeapon());
        }

        if (isMele && isDistance) {
            imageMele.setPosition(Gdx.graphics.getWidth()/2f - blockSize, Gdx.graphics.getHeight() * 0.3f);
            imageDistance.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight() * 0.3f);
        }
        System.out.println("SHOW WEAPON SELECOR   " + isMele + "   " + isDistance);
        isUp = true;
    }

    public void hide() {
        System.out.println("HIDE SELECTOR");
        isUp = false;
    }

    public void draw(Batch batch) {
        if (isMele) imageMele.draw(batch, 1f);
        if (isDistance) imageDistance.draw(batch, 1f);

        System.out.println("DRAWING");
    }

    public ItemTypeEnum tap(float x, float y) {
        if (isMele && imageMele.isTap(x, y)) return ItemTypeEnum.MELE;
        else if (isDistance && imageDistance.isTap(x, y)) return ItemTypeEnum.BOW;
        else {
            hide();
            return null;
        }
    }

    public boolean isUp() {
        return isUp;
    }

    public CharacterDrawable getTarget() {
        return target;
    }
}
