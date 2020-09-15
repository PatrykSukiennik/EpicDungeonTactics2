package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.heroStatWindow;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
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
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineTextWithIcon;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.HashMap;

public class HeroStatWindow {

    private CharacterEnum hero;
    private float stateTime = 0;

    private boolean isLvlUpMode = false;

    private boolean isUp = false;

    private SpriteDrawable bg;
    private Animation<SpriteDrawable> heroAnimation;
    private CoordsFloat bgSize, bgPos;
    private CoordsFloat heroSize, heroPos;
    private TextObject title;
    private LvlPlus lvlPlus;
    private HashMap<StatisticEnum, TextWithIcon> statTitles;
    private HashMap<StatisticEnum, TextObject> statBasic;
    private HashMap<StatisticEnum, TextObject> statCurrent;
    private HashMap<StatisticEnum, StatPlus> statPluses;
    private Image firstSeparator, secondSeparator;

    private TextWithIcon[] leftColumn;
    private TextWithIcon[] rightColumn;
    private MultiLineTextWithIcon perkTextObject;

    private Image hpBar, mpBar, expBar;
    private Image hpBarBg, mpBarBg, expBarBg;
    private TextObject hpStat, mpStat, expStat;

    private final int ROWS = 7;

    public HeroStatWindow(CharacterEnum hero) {
        this.hero = hero;

        bgSize = new CoordsFloat(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getWidth() * 0.7f * 2f);
        bgPos = new CoordsFloat(Gdx.graphics.getWidth()/2f - bgSize.x/2f, Gdx.graphics.getHeight()/2f - bgSize.y/2f);
        bg = GraphicsManager.getGuiElement(GuiElementEnum.HERO_STAT_BG);

        heroSize = new CoordsFloat(bgSize.x / 3f, bgSize.x / 3f);
        heroPos = new CoordsFloat(bgPos.x + heroSize.x * 0.1f, bgPos.y + bgSize.y - heroSize.y);
        heroAnimation = GraphicsManager.getCharactersAnimation(hero, CharacterStateEnum.IDLE);

        lvlPlus = new LvlPlus(new CoordsFloat(heroPos.x + heroSize.x/2f, heroPos.y - heroSize.y * 0.65f), heroSize.x * 0.5f);

        title = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getCharacterName(hero) + " lvl." + StatTracker.getLvl(),
                bgPos.x + heroSize.x + (bgSize.x - (heroSize.x * 1.1f))/2f,
                heroPos.y + heroSize.y * 0.6f,
                Align.center
                );

        StatisticEnum[] allStats = StatisticEnum.values();

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
                            StatTracker.getCurrentStat(allStats[i])
                                    > SavedInfoManager.getCharacterStat(hero, allStats[i])
                                    ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_GREEN
                                    : StatTracker.getCurrentStat(allStats[i])
                                    < SavedInfoManager.getCharacterStat(hero, allStats[i])
                                    ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED_RED
                                    : FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                    Integer.toString(StatTracker.getCurrentStat(allStats[i])),
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
                                    FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4.5f
                    ));
        }

        firstSeparator = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HERO_STAT_SEPARATOR));
        firstSeparator.setSize(bgSize.x * 0.8f, (10f/120f) * bgSize.x * 0.8f);
        firstSeparator.setPosition(
                bgPos.x + bgSize.x/2f - firstSeparator.getWidth()/2f,
                heroPos.y + heroSize.y * 0.3f - allStats.length
                        * FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 4.2f);


        leftColumn = new TextWithIcon[ROWS];
        for (int i = 0; i < ROWS-2; i++) {
            leftColumn[i] = new TextWithIcon(
                    GraphicsManager.getStatIcon(StatisticEnum.STR),
                    FontsManager.getFont(FontEnum.STAT_DESC),
                    "test",
                    bgPos.x + bgSize.x * 0.1f,
                    firstSeparator.getY() - firstSeparator.getHeight() -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * i * 3f,
                    Align.left
            );
        }

        rightColumn = new TextWithIcon[ROWS];
        for (int i = 0; i < ROWS; i++) {
            rightColumn[i] = new TextWithIcon(
                    GraphicsManager.getStatIcon(StatisticEnum.STR),
                    FontsManager.getFont(FontEnum.STAT_DESC),
                    "test",
                    bgPos.x + bgSize.x * 0.9f,
                    firstSeparator.getY() - firstSeparator.getHeight() -
                            FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * i * 3f,
                    Align.right
            );
        }

        perkTextObject = new MultiLineTextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.RED_DOT),
                FontsManager.getFont(FontEnum.STAT_DESC),
                "test",
                bgPos.x + bgSize.x * 0.1f,
                bgSize.x * 0.45f,
                firstSeparator.getY() - firstSeparator.getHeight() -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * ((ROWS-2)-0.2f) * 3f
        );

        secondSeparator = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HERO_STAT_SEPARATOR));
        secondSeparator.setSize(bgSize.x * 0.8f, (10f/120f) * bgSize.x * 0.8f);
        secondSeparator.setPosition(
                bgPos.x + bgSize.x/2f - firstSeparator.getWidth()/2f,
                firstSeparator.getY() - firstSeparator.getHeight() -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.STAT_DESC), "0") * (ROWS + 1) * 3f);

        hpBarBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BAR_BG));
        mpBarBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BAR_BG));
        expBarBg = new Image(GraphicsManager.getGuiElement(GuiElementEnum.STATUS_BAR_BG));

        hpBarBg.setSize(bgSize.x * 0.7f, bgSize.x * 0.7f * (9f/100f));
        mpBarBg.setSize(bgSize.x * 0.7f, bgSize.x * 0.7f * (9f/100f));
        expBarBg.setSize(bgSize.x * 0.7f, bgSize.x * 0.7f * (9f/100f));

        hpBarBg.setPosition(bgPos.x + bgSize.x * 0.15f, secondSeparator.getY() - secondSeparator.getHeight() - hpBarBg.getHeight() * 0.35f);
        mpBarBg.setPosition(bgPos.x + bgSize.x * 0.15f, hpBarBg.getY() - hpBarBg.getHeight() * 2.2f);
        expBarBg.setPosition(bgPos.x + bgSize.x * 0.15f, mpBarBg.getY() - hpBarBg.getHeight() * 2.2f);

        hpBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.HP_BAR));
        mpBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.MP_BAR));
        expBar = new Image(GraphicsManager.getGuiElement(GuiElementEnum.EXP_BAR));

        hpBar.setPosition(hpBarBg.getX() + hpBarBg.getWidth() * (8f/100f), hpBarBg.getY() + hpBarBg.getHeight() * (2f/9f));
        mpBar.setPosition(hpBarBg.getX() + hpBarBg.getWidth() * (8f/100f), mpBarBg.getY() + hpBarBg.getHeight() * (2f/9f));
        expBar.setPosition(hpBarBg.getX() + hpBarBg.getWidth() * (8f/100f), expBarBg.getY() + hpBarBg.getHeight() * (2f/9f));

        hpBar.setSize(hpBarBg.getWidth() * (84f/100f), hpBarBg.getHeight() * (5f/9f));
        mpBar.setSize(hpBarBg.getWidth() * (84f/100f), hpBarBg.getHeight() * (5f/9f));
        expBar.setSize(hpBarBg.getWidth() * (84f/100f), hpBarBg.getHeight() * (5f/9f));

        hpStat = new TextObject(
                FontsManager.getFont(FontEnum.STAT_HP),
                "hp: 100/100",
                hpBarBg.getX() + hpBarBg.getWidth() * 0.9f,
                hpBarBg.getY() - hpBarBg.getHeight() * 0.35f,
                Align.right
        );
        mpStat = new TextObject(
                FontsManager.getFont(FontEnum.STAT_MP),
                "mp: 100/100",
                mpBarBg.getX() + mpBarBg.getWidth() * 0.9f,
                mpBarBg.getY() - mpBarBg.getHeight() * 0.35f,
                Align.right
        );
        expStat = new TextObject(
                FontsManager.getFont(FontEnum.STAT_EXP),
                "exp: 100/100",
                expBarBg.getX() + expBarBg.getWidth() * 0.9f,
                expBarBg.getY() - expBarBg.getHeight() * 0.35f,
                Align.right
        );

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

        for (TextWithIcon t : leftColumn) if (t != null)t.draw(batch);
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

    public void refreshStats() {
        leftColumn[0].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.DOT),
                StringsManager.getGuiString(GuiStringEnum.MELE_DMG));

        leftColumn[1].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.DOT),
                StringsManager.getGuiString(GuiStringEnum.DISTANCE_DMG));

        leftColumn[2].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.DOT),
                StringsManager.getGuiString(GuiStringEnum.RANGE));

        leftColumn[3].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.DOT),
                StringsManager.getGuiString(GuiStringEnum.ARMOR));

        leftColumn[4].setIconAndFont(
                GraphicsManager.getGuiElement(GuiElementEnum.DOT),
                StringsManager.getGuiString(GuiStringEnum.CRIT_CHANCE));

        PerkEnum heroPerk = StatTracker.getPerk();
        String descEnd = StringsManager.getPerkDescription(heroPerk);
        float perkStat = PerkStats.getPerkStat(heroPerk, SavedInfoManager.getPerkLvl(heroPerk));
        String perkText =
                perkStat > 1 ?
                        (int) perkStat + " " + descEnd :
                        (int) (perkStat * 100) + "% " + descEnd;
        perkTextObject.setIconAndText(
                GraphicsManager.getGuiElement(GuiElementEnum.RED_DOT),
                perkText
        );

    }

    public boolean isUp() {
        return isUp;
    }

    public void tap(float x, float y) {
        if (lvlPlus.tap(x, y) && lvlPlus.getAvailablePoints() != 0) {
            isLvlUpMode = !isLvlUpMode;
        }
        else {
            isLvlUpMode = false;

            if (x < bgPos.x || x > bgPos.x + bgSize.x || y < bgPos.y || y > bgPos.y + bgSize.y) hide();
        }
    }

    public void show() {
        isUp = true;
    }

    public void hide() {
        isUp = false;
    }
}
