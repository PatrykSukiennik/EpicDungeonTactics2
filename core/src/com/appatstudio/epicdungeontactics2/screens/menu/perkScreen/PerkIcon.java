package com.appatstudio.epicdungeontactics2.screens.menu.perkScreen;

import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.PerkStats;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public final class PerkIcon extends Image {

    private static final float X, ICON_SIZE, WIDTH;
    private float y;
    private PerkEnum perkEnum;
    private MultiLineText title, description;
    private TextObject upgradeText;
    private TextWithIcon upgradeCostText;
    private int upgradeCost, lvl;

    static {
        X = Gdx.graphics.getWidth() * 0.05f;
        WIDTH = Gdx.graphics.getWidth() * 0.75f;
        ICON_SIZE = Gdx.graphics.getWidth() * 0.15f;
    }

    PerkIcon(PerkEnum perkEnum, float y) {
        super(GraphicsManager.getPerkIcon(perkEnum));
        this.y = y;
        this.perkEnum = perkEnum;

        this.setPosition(X, y);
        this.setSize(ICON_SIZE, ICON_SIZE);

        this.lvl = SavedInfoManager.getPerkLvl(perkEnum);

        if (lvl < 3) {
            upgradeCost = PerkStats.getPerkUpgradeCost(perkEnum, lvl);
            upgradeText = new TextObject(
                    upgradeCost <= GlobalValues.getGold() ? FontsManager.getFont(FontEnum.MENU_PERK_TITLE) : FontsManager.getFont(FontEnum.MENU_PERK_DESCRIPTION),
                    StringsManager.getGuiString(GuiStringEnum.UPGRADE),
                    Gdx.graphics.getWidth() * 0.95f,
                    y + ICON_SIZE * 0.9f,
                    Align.right
            );
            upgradeCostText = new TextWithIcon(
                    GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                    upgradeCost <= GlobalValues.getGold() ? FontsManager.getFont(FontEnum.MENU_PERK_TITLE) : FontsManager.getFont(FontEnum.MENU_PERK_DESCRIPTION),
                    Integer.toString(upgradeCost),
                    Gdx.graphics.getWidth() * 0.95f,
                    y + ICON_SIZE * 0.55f,
                    Align.right
            );
        }

        title = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_PERK_TITLE),
                StringsManager.getPerkName(perkEnum) + " lvl." + lvl,
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.2f,
                this.y + ICON_SIZE * 0.9f,
                Align.left
        );

        String descEnd = StringsManager.getPerkDescription(perkEnum);
        float perkStat = PerkStats.getPerkStat(perkEnum, lvl);
        String descString;

        if (lvl > 0) {
            descString =
                    perkStat > 1 ?
                            (int) perkStat + " " + descEnd :
                            (int) (perkStat * 100) + "% " + descEnd;
        } else {
            descString = descEnd;
        }


        description = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_PERK_DESCRIPTION),
                descString,
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.6f,
                this.y + ICON_SIZE * 0.55f,
                Align.left
        );
    }

    PerkEnum getPerkEnum() {
        return perkEnum;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        title.draw(batch);
        description.draw(batch);
        if (lvl < 3) {
            upgradeText.draw(batch);
            batch.getColor().a = 1f;
            upgradeCostText.draw(batch);
        }
    }

    public boolean tap(float x, float y) {
        if (lvl < 3 && upgradeCost <= GlobalValues.getGold() &&
                y > this.y && y < this.y + ICON_SIZE &&
                x > Gdx.graphics.getWidth() * 0.8f &&
                x < Gdx.graphics.getWidth() * 0.95f) {

            GlobalValues.minusGold(upgradeCost);
            SavedInfoManager.savePerkLvl(perkEnum, SavedInfoManager.getPerkLvl(perkEnum) + 1);
            SavedInfoManager.playerStatEffect(PlayerStatsTrackerFlagsEnum.PERKS_UPGRADED, 1);
            PerkScreen.updatePerks();
            return false;
        }

        return x > X && x < X + WIDTH &&
                y > this.y && y < this.y + ICON_SIZE;
    }

    static float getIconHeight() {
        return ICON_SIZE;
    }
}
