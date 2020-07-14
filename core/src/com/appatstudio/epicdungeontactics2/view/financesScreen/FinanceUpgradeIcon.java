package com.appatstudio.epicdungeontactics2.view.financesScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.FinanceUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.FinancesStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import static com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum.CURR_INCOME;
import static com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum.DAY;

public final class FinanceUpgradeIcon extends Image {

    private static final float X, ICON_SIZE, WIDTH;
    private float y;
    private TextWithIcon upgradeCostText;
    private FinanceUpgradeEnum financeEnum;
    private MultiLineText title, description;
    private int upgradeCost, lvl;
    private static SpriteDrawable bgAlpha = GraphicsManager.getGuiElement(GuiElementEnum.BLACK_ALPHA_50percent);

    static {
        X = Gdx.graphics.getWidth() * 0.15f;
        WIDTH = Gdx.graphics.getWidth() * 0.5f;
        ICON_SIZE = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth() * 0.15f;
    }

    FinanceUpgradeIcon(FinanceUpgradeEnum financeEnum, float y) {
        super(GraphicsManager.getFinancesIcon(financeEnum));
        this.y = y;
        this.financeEnum = financeEnum;

        this.setPosition(X, y);
        this.setSize(ICON_SIZE, ICON_SIZE);

        this.lvl = SavedInfoManager.getFinancesLvl(financeEnum);
        this.upgradeCost = FinancesStats.getCost(financeEnum) * ((lvl/2) + 1);

        upgradeCostText = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                upgradeCost <= GlobalValues.getGold() ? FontsManager.getFont(FontEnum.MENU_PERK_TITLE) : FontsManager.getFont(FontEnum.MENU_PERK_DESCRIPTION),
                Integer.toString(upgradeCost),
                Gdx.graphics.getWidth() * 0.85f,
                y + ICON_SIZE * 0.55f,
                Align.right
        );

        title = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_PERK_TITLE),
                StringsManager.getFinanceUpgradeName(financeEnum) + " " + lvl,
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.2f,
                this.y + ICON_SIZE * 0.9f,
                Align.left
        );

        int perkStat = FinancesStats.getIncome(financeEnum) * lvl;

        description = new MultiLineText(
                FontsManager.getFont(FontEnum.MENU_PERK_DESCRIPTION),
                StringsManager.getGuiString(CURR_INCOME) + " " + perkStat + " /" + StringsManager.getGuiString(DAY),
                X + ICON_SIZE * 1.2f,
                WIDTH - ICON_SIZE * 1.2f,
                this.y + ICON_SIZE * 0.55f,
                Align.left
        );
    }

    FinanceUpgradeEnum getFinanceEnum() {
        return financeEnum;
    }

    public void draw(Batch batch, float parentAlpha, boolean isSelected) {
        if (isSelected) bgAlpha.draw(batch, this.getX(), this.getY(), Gdx.graphics.getWidth() * 0.70f, this.getHeight());

        super.draw(batch, parentAlpha);

        title.draw(batch);
        if (lvl > 0) description.draw(batch);
        batch.getColor().a = 1f;
        upgradeCostText.draw(batch);
    }

    public boolean tap(float x, float y) {
        if (lvl < 3 && upgradeCost <= GlobalValues.getGold() &&
                y > this.y && y < this.y + ICON_SIZE &&
                x > Gdx.graphics.getWidth() * 0.8f &&
                x < Gdx.graphics.getWidth() * 0.95f) {

            GlobalValues.minusGold(upgradeCost);
            SavedInfoManager.saveFinancesLvl(financeEnum, SavedInfoManager.getFinancesLvl(financeEnum) + 1);
            FinancesUpgradeScreen.updateFinances();
            return false;
        }

        return x > X && x < X + WIDTH &&
                y > this.y && y < this.y + ICON_SIZE;
    }

    static float getIconHeight() {
        return ICON_SIZE;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public static float getIconSize() {
        return ICON_SIZE;
    }
}
