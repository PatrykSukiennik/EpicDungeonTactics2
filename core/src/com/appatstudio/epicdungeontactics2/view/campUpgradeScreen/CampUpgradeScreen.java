package com.appatstudio.epicdungeontactics2.view.campUpgradeScreen;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.CampUpgradeStats;
import com.appatstudio.epicdungeontactics2.view.MenuBgContainer;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.view.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.view.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.view.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.view.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public final class CampUpgradeScreen {

    private SpriteBatch batch;
    private TextWithIcon backButton, goldStatus;

    private CampUpgradeButton[] buttons;

    private ButtonWithText upgradeButton, unlockButton;
    private TextObject maxedOutText;

    private float mainCharactersY = (Gdx.graphics.getHeight()/18f) * 9.15f;
    private float npcY = (Gdx.graphics.getHeight()/18f) * 8.15f;
    private static float characterWidth = (Gdx.graphics.getHeight()/18f);
    private static float characterHeight = (Gdx.graphics.getHeight()/18f) * (24f/16f);

    private static float xModif = Gdx.graphics.getWidth();

    private static float[] mainCharactersX;
    private static float[] npcsX;

    private static Array<Animation<SpriteDrawable>> mainCharacters;
    private static Array<Animation<SpriteDrawable>> npcs;

    private static Map<CampUpgradeEnum, CampUpgradeCard> upgradeCards;
    private static CampUpgradeCard selectedUpgrade = null;

    private float stateTime = 0f;

    public CampUpgradeScreen() {

        batch = new SpriteBatch();
        batch.enableBlending();

        upgradeButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                CharacterSelector.bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.JUST_UPGRADE));

        unlockButton = new ButtonWithText(GraphicsManager.getGuiElement(GuiElementEnum.STONE_BUTTON_WIDE),
                Gdx.graphics.getWidth()/2f - MenuScreen.BOTTOM_BUTTON_WIDTH/2f,
                CharacterSelector.bottomY,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_TITLE_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.HIRE));

        maxedOutText = new TextObject(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.MAXED_OUT),
                Gdx.graphics.getWidth()/2f,
                unlockButton.getY() + MenuScreen.BOTTOM_BUTTON_HEIGHT/2f,
                Align.center);

        backButton = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.BACK_ICON),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getGuiString(GuiStringEnum.BACK),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED), "0"),
                Align.right
        );
        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.left
        );

        upgradeCards = new HashMap<>();
        CampUpgradeEnum[] allUpgrades = CampUpgradeEnum.values();
        buttons = new CampUpgradeButton[allUpgrades.length];
        for (int i=0; i<buttons.length; i++) {
            buttons[i] = new CampUpgradeButton(i, allUpgrades[i]);
            upgradeCards.put(allUpgrades[i], new CampUpgradeCard(allUpgrades[i]));
        }

        mainCharacters = new Array<>();
        npcs = new Array<>();
        updateCharacters();
    }

    public static void updateNpcs() {
        if (npcs == null) npcs = new Array<>();
        else npcs.clear();
        updateCharacters();
    }
    public static void updateMainCharacters() {
        if (mainCharacters == null) mainCharacters = new Array<>();
        else mainCharacters.clear();
        updateCharacters();
    }

    public void draw() {
        MenuBgContainer.drawOnlyBg(batch);

        if (xModif > 0) {
            xModif -= Gdx.graphics.getWidth()/2.5f * Gdx.graphics.getDeltaTime();
        }

        batch.begin();

        stateTime += Gdx.graphics.getDeltaTime();
        for (int i=0; i<mainCharacters.size; i++) {
            mainCharacters.get(i).getKeyFrame(stateTime).draw(batch, mainCharactersX[i] - xModif, mainCharactersY, characterWidth, characterHeight);
        }
        for (int i=0; i<npcs.size; i++) {
            npcs.get(i).getKeyFrame(stateTime).draw(batch, npcsX[i] - xModif, npcY, characterWidth, characterHeight);
        }

        MenuBgContainer.drawOnlyLights(batch);

        for (CampUpgradeButton c : buttons) {
            c.draw(batch);
        }
        backButton.draw(batch);
        goldStatus.draw(batch);
        if (selectedUpgrade != null) {
            selectedUpgrade.draw(batch);
            if (selectedUpgrade.isUnlockPossible()) unlockButton.draw(batch, 1f);
            else if (selectedUpgrade.isUpgradePossible()) upgradeButton.draw(batch, 1f);
            else if (selectedUpgrade.isMaxedOut()) maxedOutText.draw(batch);
        }
        batch.end();
    }

    private static void updateCharacters() {
        mainCharacters.clear();
        npcs.clear();

        CharacterEnum[] allCharacters = SavedInfoManager.getAllUnlockedCharacters();
        CharacterEnum[] allNpcs = SavedInfoManager.getAllUnlockedNpcs();
        mainCharactersX = new float[allCharacters.length];
        npcsX = new float[allNpcs.length];

        if (allCharacters.length >= allNpcs.length) {

            for (int i = 0; i < allCharacters.length; i++) {
                mainCharacters.add(GraphicsManager.getCharactersAnimation(allCharacters[i], CharacterStateEnum.RUN));
                mainCharactersX[i] = Gdx.graphics.getWidth() / 2f + (allCharacters.length * characterWidth) / 2f - (i + 1) * characterWidth;
            }
            for (int i = 0; i < allNpcs.length; i++) {
                npcs.add(GraphicsManager.getCharactersAnimation(allNpcs[i], CharacterStateEnum.RUN));
                npcsX[i] = mainCharactersX[0] - (i + 0.5f) * characterWidth;
            }
        }
        else {
            for (int i = 0; i < allNpcs.length; i++) {
                npcs.add(GraphicsManager.getCharactersAnimation(allNpcs[i], CharacterStateEnum.RUN));
                npcsX[i] = Gdx.graphics.getWidth() / 2f + (allNpcs.length * characterWidth) / 2f - (i + 1f) * characterWidth;

            }
            for (int i = 0; i < allCharacters.length; i++) {
                mainCharacters.add(GraphicsManager.getCharactersAnimation(allCharacters[i], CharacterStateEnum.RUN));
                mainCharactersX[i] = npcsX[0] - (i - 0.5f) * characterWidth;
            }
        }
    }

    public boolean tap(float x, float y) {
        CampUpgradeEnum[] allUpgrades = CampUpgradeEnum.values();

        if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }
        else if (selectedUpgrade != null && selectedUpgrade.isUpgradePossible()) {
            if (upgradeButton.tap(x, y)) {
                SavedInfoManager.saveCampUpgradeLvl(
                        selectedUpgrade.getUpgradeEnum(),
                        SavedInfoManager.getNpcLvl(selectedUpgrade.getUpgradeEnum()) + 1);
            }
            GlobalValues.minusGold(CampUpgradeStats.getCampUpgradeCost(selectedUpgrade.getUpgradeEnum(), selectedUpgrade.getLvl()));
            CampUpgradeCard newCard = new CampUpgradeCard(selectedUpgrade.getUpgradeEnum());
            upgradeCards.put(selectedUpgrade.getUpgradeEnum(), newCard);
            selectedUpgrade = upgradeCards.get(newCard.getUpgradeEnum());
            updateCharacters();

            for (int i=0; i<buttons.length; i++) {
                buttons[i] = new CampUpgradeButton(i, allUpgrades[i]);
                upgradeCards.put(allUpgrades[i], new CampUpgradeCard(allUpgrades[i]));
            }
        }
        else if (selectedUpgrade != null && selectedUpgrade.isUnlockPossible()) {
            if (unlockButton.tap(x, y)) {
                SavedInfoManager.saveCampUpgradeLvl(
                        selectedUpgrade.getUpgradeEnum(),
                        SavedInfoManager.getNpcLvl(selectedUpgrade.getUpgradeEnum()) + 1);
            }
            GlobalValues.minusGold(CampUpgradeStats.getCampUpgradeCost(selectedUpgrade.getUpgradeEnum(), selectedUpgrade.getLvl()));
            CampUpgradeCard newCard = new CampUpgradeCard(selectedUpgrade.getUpgradeEnum());
            upgradeCards.put(selectedUpgrade.getUpgradeEnum(), newCard);
            selectedUpgrade = upgradeCards.get(newCard.getUpgradeEnum());
            updateCharacters();

            for (int i=0; i<buttons.length; i++) {
                buttons[i] = new CampUpgradeButton(i, allUpgrades[i]);
                upgradeCards.put(allUpgrades[i], new CampUpgradeCard(allUpgrades[i]));
            }
        }
        else {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].tap(x, y)) {
                    if (selectedUpgrade != null) {
                        if (selectedUpgrade.getUpgradeEnum() == buttons[i].getEnum()) selectedUpgrade = null;
                        else selectedUpgrade = upgradeCards.get(buttons[i].getEnum());
                    }
                    else selectedUpgrade = upgradeCards.get(buttons[i].getEnum());
                    return true;
                }
            }
            if (x < Gdx.graphics.getWidth()/2f - CharacterSelector.iconSize/2f || x > Gdx.graphics.getWidth()/2f + CharacterSelector.iconSize/2f) selectedUpgrade = null;
        }
        return false;
    }

    public void show() {
        xModif = Gdx.graphics.getWidth();
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }


}
