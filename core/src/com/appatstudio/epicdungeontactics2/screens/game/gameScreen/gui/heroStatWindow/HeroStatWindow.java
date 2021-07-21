package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.heroStatWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.PerkEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.stats.PerkStats;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.viewElements.MultiLineTextWithIcon;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.HashMap;

public class HeroStatWindow {

    private static CharacterEnum hero;
    private float stateTime = 0;

    private boolean isLvlUpMode = false;

    private boolean isUp = false;

    private static SpriteDrawable bg;
    private static Animation<SpriteDrawable> heroAnimation;
    private static CoordsFloat bgSize, bgPos;
    private static CoordsFloat heroSize, heroPos;
    private static TextObject title;
    private static LvlPlus lvlPlus;
    private static HashMap<StatisticEnum, TextWithIcon> statTitles;
    private static HashMap<StatisticEnum, TextObject> statBasic;
    private static HashMap<StatisticEnum, TextObject> statCurrent;
    private static HashMap<StatisticEnum, StatPlus> statPluses;
    private static Image firstSeparator, secondSeparator;

    private static TextWithIcon[] leftColumn;
    private static TextWithIcon[] rightColumn;
    private static MultiLineTextWithIcon perkTextObject;

    private static Image hpBar, mpBar, expBar;
    private static Image hpBarBg, mpBarBg, expBarBg;
    private static TextObject hpStat, mpStat, expStat;

    private final int ROWS = 8;

    public HeroStatWindow() {

    }

    public void init(CharacterEnum hero) {
        this.hero = hero;

        StatTracker.init(hero, GameScreen.getInstance().getPerk());

        bgSize = new CoordsFloat(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getWidth() * 0.7f * 2f);
        bgPos = new CoordsFloat(Gdx.graphics.getWidth() / 2f - bgSize.x / 2f, Gdx.graphics.getHeight() / 2f - bgSize.y / 2f);
        bg = GraphicsManager.getGuiElement(GuiElementEnum.HERO_STAT_BG);

        heroSize = new CoordsFloat(bgSize.x / 3f, bgSize.x / 3f);
        heroPos = new CoordsFloat(bgPos.x + heroSize.x * 0.1f, bgPos.y + bgSize.y - heroSize.y);
        heroAnimation = GraphicsManager.getGuiHeroAnimation(hero, CharacterStateEnum.IDLE);

        lvlPlus = new LvlPlus(new CoordsFloat(heroPos.x + heroSize.x / 2f, heroPos.y - heroSize.y * 0.65f), heroSize.x * 0.5f);

        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCharacterName(hero) + " lvl." + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL),
                bgPos.x + heroSize.x + (bgSize.x - (heroSize.x * 1.1f)) / 2f,
                heroPos.y + heroSize.y * 0.6f,
                Align.center
        );

        StatisticEnum[] allStats = {StatisticEnum.VIT, StatisticEnum.STR, StatisticEnum.DEX, StatisticEnum.INT, StatisticEnum.LCK};
        CompleteHeroStatsEnum[] heroStatsEnums = {CompleteHeroStatsEnum.VIT, CompleteHeroStatsEnum.STR, CompleteHeroStatsEnum.DEX, CompleteHeroStatsEnum.INT, CompleteHeroStatsEnum.LCK};

        statTitles = new HashMap<>();
        statBasic = new HashMap<>();
        statCurrent = new HashMap<>();
        statPluses = new HashMap<>();

        for (int i = allStats.length - 1; i >= 0; i--) {
            statTitles.put(allStats[i], new TextWithIcon(
                    GraphicsManager.getStatIcon(allStats[i]),
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    " " + allStats[i].toString() + ": ",
                    heroPos.x + heroSize.x * 1f,
                    heroPos.y + heroSize.y * 0.3f - i * FontsManager.getTextHeight(
                            FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4f,
                    Align.left));

            statBasic.put(allStats[i], new TextObject(
                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    Integer.toString(SavedInfoManager.getCharacterStat(hero, allStats[i])),
                    heroPos.x + heroSize.x * 1.9f,
                    heroPos.y + heroSize.y * 0.3f - i * FontsManager.getTextHeight(
                            FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4f,
                    Align.center
            ));
            statCurrent.put(allStats[i], new TextObject(
                    FontsManager.getFont(
                            StatTracker.getCurrentStat(heroStatsEnums[i])
                                    > SavedInfoManager.getCharacterStat(hero, allStats[i])
                                    ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_GREEN
                                    : StatTracker.getCurrentStat(heroStatsEnums[i])
                                    < SavedInfoManager.getCharacterStat(hero, allStats[i])
                                    ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_RED
                                    : FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    Integer.toString((int) StatTracker.getCurrentStat(heroStatsEnums[i])),
                    heroPos.x + heroSize.x * 2.4f,
                    heroPos.y + heroSize.y * 0.3f - i * FontsManager.getTextHeight(
                            FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4f,
                    Align.center
            ));
            statPluses.put(
                    allStats[i],
                    new StatPlus(
                            allStats[i],
                            new CoordsFloat(
                                    heroPos.x + heroSize.x * 2.4f,
                                    heroPos.y + heroSize.y * 0.3f - i * FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4f),
                            FontsManager.getTextHeight(
                                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 2.5f
                    ));
        }

        firstSeparator = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HERO_STAT_SEPARATOR));
        firstSeparator.setSize(bgSize.x * 0.8f, (10f / 120f) * bgSize.x * 0.8f);
        firstSeparator.setPosition(
                bgPos.x + bgSize.x / 2f - firstSeparator.getWidth() / 2f,
                heroPos.y + heroSize.y * 0.3f - allStats.length
                        * FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4.2f);


        leftColumn = new TextWithIcon[ROWS];
        String[] leftTexts = {
                StringsManager.getGuiString(GuiStringEnum.MELE_DMG) + " " + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.MELE_DMG),
                StringsManager.getGuiString(GuiStringEnum.DISTANCE_DMG) + " " + 2,
                StringsManager.getGuiString(GuiStringEnum.ARMOR) + " " + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.ARMOR),
                StringsManager.getGuiString(GuiStringEnum.CRIT_CHANCE) + " " + (int) (StatTracker.getCurrentStat(CompleteHeroStatsEnum.CRIT_CHANCE) * 100) + "%",
                StringsManager.getGuiString(GuiStringEnum.MISS_CHANCE) + " " + (int) (StatTracker.getCurrentStat(CompleteHeroStatsEnum.MISS_CHANCE) * 100) + "%",
                StringsManager.getGuiString(GuiStringEnum.SPEED) + " " + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.SPEED)

        };
        SpriteDrawable[] leftIcons = {
                GraphicsManager.getGuiElement(GuiElementEnum.MELE_DMG_ICON),
                GraphicsManager.getGuiElement(GuiElementEnum.ARMOR_ICON),
                GraphicsManager.getGuiElement(GuiElementEnum.ARMOR_ICON),
                GraphicsManager.getGuiElement(GuiElementEnum.CRIT_CHANCE_ICON),
                GraphicsManager.getGuiElement(GuiElementEnum.CRIT_CHANCE_ICON),
                GraphicsManager.getGuiElement(GuiElementEnum.CRIT_CHANCE_ICON)
        };
        for (int i = 0; i < ROWS - 2; i++) {
            leftColumn[i] = new TextWithIcon(
                    leftIcons[i],
                    FontsManager.getFont(FontEnum.STAT_DESC),
                    leftTexts[i],
                    bgPos.x + bgSize.x * 0.1f,
                    firstSeparator.getY() - firstSeparator.getHeight() * 0.4f -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * i * 2.5f,
                    Align.left
            );
        }

        rightColumn = new TextWithIcon[ROWS];
        for (int i = 0; i < ROWS; i++) {
            rightColumn[i] = new TextWithIcon(
                    GraphicsManager.getGuiElement(GuiElementEnum.NONE),
                    FontsManager.getFont(FontEnum.STAT_DESC),
                    "",
                    bgPos.x + bgSize.x * 0.9f,
                    firstSeparator.getY() - firstSeparator.getHeight() * 0.4f -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * i * 2.5f,
                    Align.right
            );
        }


        String descEnd = StringsManager.getPerkDescription(StatTracker.getPerk());
        float perkStat = PerkStats.getPerkStat(StatTracker.getPerk(), (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL));
        String descString =
                perkStat > 1 ?
                        (int) perkStat + " " + descEnd :
                        (int) (perkStat * 100) + "% " + descEnd;

        perkTextObject = new MultiLineTextWithIcon(
                GraphicsManager.getPerkIcon(StatTracker.getPerk()),
                FontsManager.getFont(FontEnum.STAT_DESC),
                descString,
                bgPos.x + bgSize.x * 0.1f,
                bgSize.x * 0.45f,
                firstSeparator.getY() - firstSeparator.getHeight() * 0.4f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * ((ROWS - 2) - 0.2f) * 2.5f
        );

        secondSeparator = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HERO_STAT_SEPARATOR));
        secondSeparator.setSize(bgSize.x * 0.8f, (10f / 120f) * bgSize.x * 0.8f);
        secondSeparator.setPosition(
                bgPos.x + bgSize.x / 2f - firstSeparator.getWidth() / 2f,
                firstSeparator.getY() - firstSeparator.getHeight() * 0.2f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * (ROWS + 1) * 2.5f);

        hpBarBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BAR_BG));
        mpBarBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BAR_BG));
        expBarBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BAR_BG));

        hpBarBg.setSize(bgSize.x * 0.7f, bgSize.x * 0.7f * (9f / 100f));
        mpBarBg.setSize(bgSize.x * 0.7f, bgSize.x * 0.7f * (9f / 100f));
        expBarBg.setSize(bgSize.x * 0.7f, bgSize.x * 0.7f * (9f / 100f));

        hpBarBg.setPosition(bgPos.x + bgSize.x * 0.15f, secondSeparator.getY() - secondSeparator.getHeight() - hpBarBg.getHeight() * 0.35f);
        mpBarBg.setPosition(bgPos.x + bgSize.x * 0.15f, hpBarBg.getY() - hpBarBg.getHeight() * 2.2f);
        expBarBg.setPosition(bgPos.x + bgSize.x * 0.15f, mpBarBg.getY() - hpBarBg.getHeight() * 2.2f);

        hpBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HP_BAR));
        mpBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.MP_BAR));
        expBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.EXP_BAR));

        hpBar.setPosition(hpBarBg.getX() + hpBarBg.getWidth() * (8f / 100f), hpBarBg.getY() + hpBarBg.getHeight() * (2f / 9f));
        mpBar.setPosition(hpBarBg.getX() + hpBarBg.getWidth() * (8f / 100f), mpBarBg.getY() + hpBarBg.getHeight() * (2f / 9f));
        expBar.setPosition(hpBarBg.getX() + hpBarBg.getWidth() * (8f / 100f), expBarBg.getY() + hpBarBg.getHeight() * (2f / 9f));

        hpBar.setSize(hpBarBg.getWidth() * (84f / 100f) * (StatTracker.getCurrentStat(CompleteHeroStatsEnum.HP) / StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_HP)), hpBarBg.getHeight() * (5f / 9f));
        mpBar.setSize(hpBarBg.getWidth() * (84f / 100f) * (StatTracker.getCurrentStat(CompleteHeroStatsEnum.MP) / StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_MP)), hpBarBg.getHeight() * (5f / 9f));
        expBar.setSize(hpBarBg.getWidth() * (84f / 100f) * (StatTracker.getCurrentStat(CompleteHeroStatsEnum.EXP) / StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_EXP)), hpBarBg.getHeight() * (5f / 9f));

        hpStat = new TextObject(
                FontsManager.getFont(FontEnum.STAT_HP),
                "hp: " + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.HP) + "/" + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_HP),
                hpBarBg.getX() + hpBarBg.getWidth() * 0.9f,
                hpBarBg.getY() - hpBarBg.getHeight() * 0.35f,
                Align.right
        );
        mpStat = new TextObject(
                FontsManager.getFont(FontEnum.STAT_MP),
                "mp: " + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.MP) + "/" + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_MP),
                mpBarBg.getX() + mpBarBg.getWidth() * 0.9f,
                mpBarBg.getY() - mpBarBg.getHeight() * 0.35f,
                Align.right
        );
        expStat = new TextObject(
                FontsManager.getFont(FontEnum.STAT_EXP),
                "exp: " + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.EXP) + "/" + (int) StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_EXP),
                expBarBg.getX() + expBarBg.getWidth() * 0.9f,
                expBarBg.getY() - expBarBg.getHeight() * 0.35f,
                Align.right
        );

        refreshStats();
    }

    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        bg.draw(batch, bgPos.x, bgPos.y, bgSize.x, bgSize.y);
        heroAnimation.getKeyFrame(stateTime).draw(batch, heroPos.x, heroPos.y, heroSize.x, heroSize.y);

        lvlPlus.draw(batch, 1f);

        title.draw(batch);
        for (TextWithIcon t : statTitles.values()) t.draw(batch);
        for (TextObject b : statBasic.values()) b.draw(batch);
        if (!isLvlUpMode) for (TextObject c : statCurrent.values()) c.draw(batch);
        else for (StatPlus p : statPluses.values()) {
            p.act(Gdx.graphics.getDeltaTime());
            p.draw(batch, 1f);
        }

        for (TextWithIcon t : leftColumn) if (t != null) t.draw(batch);
        for (TextWithIcon t : rightColumn) t.draw(batch);
        perkTextObject.draw(batch);

        firstSeparator.draw(batch, 1f);

        secondSeparator.draw(batch, 1f);

        hpBar.draw(batch, 1f);
        mpBar.draw(batch, 1f);
        expBar.draw(batch, 1f);

        hpBarBg.draw(batch, 1f);
        mpBarBg.draw(batch, 1f);
        expBarBg.draw(batch, 1f);

        hpStat.draw(batch);
        mpStat.draw(batch);
        expStat.draw(batch);

    }

    public static void refreshStats() {
        leftColumn[0].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.MELE_DMG_ICON),
                StringsManager.getGuiString(GuiStringEnum.MELE_DMG));

        leftColumn[1].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.RANGE_DMG_ICON),
                StringsManager.getGuiString(GuiStringEnum.DISTANCE_DMG));

        leftColumn[2].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.RANGE_ICON),
                StringsManager.getGuiString(GuiStringEnum.RANGE));

        leftColumn[3].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.ARMOR_ICON),
                StringsManager.getGuiString(GuiStringEnum.ARMOR));

        leftColumn[4].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.CRIT_CHANCE_ICON),
                StringsManager.getGuiString(GuiStringEnum.CRIT_CHANCE));


        StatisticEnum[] allStats = {StatisticEnum.VIT, StatisticEnum.STR, StatisticEnum.DEX, StatisticEnum.INT, StatisticEnum.LCK};
        CompleteHeroStatsEnum[] heroStatsEnums = {CompleteHeroStatsEnum.VIT, CompleteHeroStatsEnum.STR, CompleteHeroStatsEnum.DEX, CompleteHeroStatsEnum.INT, CompleteHeroStatsEnum.LCK};

        for (int i = allStats.length - 1; i >= 0; i--) {
            statBasic.get(allStats[i]).setText(
                    Integer.toString(SavedInfoManager.getCharacterStat(hero, allStats[i])));

            statCurrent.put(allStats[i], new TextObject(
                    FontsManager.getFont(
                            StatTracker.getCurrentStat(heroStatsEnums[i])
                                    > SavedInfoManager.getCharacterStat(hero, allStats[i])
                                    ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_GREEN
                                    : StatTracker.getCurrentStat(heroStatsEnums[i])
                                    < SavedInfoManager.getCharacterStat(hero, allStats[i])
                                    ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_RED
                                    : FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    Integer.toString((int) StatTracker.getCurrentStat(heroStatsEnums[i])),
                    heroPos.x + heroSize.x * 2.4f,
                    heroPos.y + heroSize.y * 0.3f - i * FontsManager.getTextHeight(
                            FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4f,
                    Align.center
            ));
        }



        PerkEnum heroPerk = StatTracker.getPerk();
        String descEnd = StringsManager.getPerkDescription(heroPerk);
        float perkStat = PerkStats.getPerkStat(heroPerk, SavedInfoManager.getPerkLvl(heroPerk));
        String perkText =
                perkStat > 1 ?
                        (int) perkStat + " " + descEnd :
                        (int) (perkStat * 100) + "% " + descEnd;

        switch (SavedInfoManager.getPerkLvl(StatTracker.getPerk())) {
            case 1:
                perkTextObject.setIconAndText(
                        GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_MEDAL),
                        perkText
                );
                break;
            case 2:
                perkTextObject.setIconAndText(
                        GraphicsManager.getGuiElement(GuiElementEnum.SILVER_MEDAL),
                        perkText
                );
                break;
            default:
                perkTextObject.setIconAndText(
                        GraphicsManager.getGuiElement(GuiElementEnum.GOLD_MEDAL),
                        perkText
                );
                break;

        }



        refreshBars();

    }

    public static void refreshBars() {
        float currHp = StatTracker.getCurrentStat(CompleteHeroStatsEnum.HP);
        float currMp = StatTracker.getCurrentStat(CompleteHeroStatsEnum.MP);
        float currExp = StatTracker.getCurrentStat(CompleteHeroStatsEnum.EXP);
        float maxHp = StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_HP);
        float maxMp = StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_MP);
        float maxExp = StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_EXP);

        hpStat.setText("hp: " + (int) currHp + "/" + (int) maxHp);
        mpStat.setText("mp: " + (int) currMp + "/" + (int) maxMp);
        expStat.setText("exp: " + (int) currExp + "/" + (int) maxExp);

        hpBar.setSize(hpBarBg.getWidth() * (84f / 100f) * (currHp / maxHp), hpBarBg.getHeight() * (5f / 9f));
        mpBar.setSize(hpBarBg.getWidth() * (84f / 100f) * (currMp / maxMp), hpBarBg.getHeight() * (5f / 9f));
        expBar.setSize(hpBarBg.getWidth() * (84f / 100f) * (currExp / maxExp), hpBarBg.getHeight() * (5f / 9f));
    }

    public boolean isUp() {
        return isUp;
    }

    public void tap(float x, float y) {
        if (lvlPlus.tap(x, y) && lvlPlus.getAvailablePoints() != 0) {
            isLvlUpMode = !isLvlUpMode;
        } else if (isLvlUpMode) {
            for (StatPlus p : statPluses.values()) {
                if (p.tap(x, y)) {
                    StatTracker.statAdd(p.getStatisticEnum());
                    lvlPlus.minusPoint();
                    break;
                }
            }
        }
        if (lvlPlus.getAvailablePoints() == 0) isLvlUpMode = false;
        if (x < bgPos.x || x > bgPos.x + bgSize.x || y < bgPos.y || y > bgPos.y + bgSize.y) hide();

    }

    public void show() {
        isUp = true;
    }

    public void hide() {
        isUp = false;
    }
}
