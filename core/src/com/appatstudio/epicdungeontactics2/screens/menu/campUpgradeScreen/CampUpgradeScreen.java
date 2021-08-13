package com.appatstudio.epicdungeontactics2.screens.menu.campUpgradeScreen;

import static com.appatstudio.epicdungeontactics2.global.enums.GameModeEnum.PROD;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GameModeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.global.stats.CampUpgradeStats;
import com.appatstudio.epicdungeontactics2.screens.menu.MenuBgContainer;
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
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.CharacterSelector;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public final class CampUpgradeScreen {

    private SpriteBatch batch;
    private TextWithIcon backButton, goldStatus;

    private Image campUpgradeButton, financesUpgradeButton, bestiaryButton;

    private CampUpgradeButton[] buttons;

    private ButtonWithText upgradeButton, unlockButton;
    private TextObject maxedOutText;

    private float mainCharactersY = (Gdx.graphics.getHeight()/18f) * 9.35f;
    private float npcY = (Gdx.graphics.getHeight()/18f) * 8.35f;
    private static float characterWidth = (Gdx.graphics.getHeight()/18f) * 2;
    private static float characterHeight = (Gdx.graphics.getHeight()/18f) * 2;

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
        float sizeButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;

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

        campUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.CAMP_UPGRADE_BUTTON));
        campUpgradeButton.setSize(sizeButtonSize, sizeButtonSize);
        campUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f);
        campUpgradeButton.getColor().a = 0.4f;

        financesUpgradeButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.FINANCES_UPGRADE_BUTTON));
        financesUpgradeButton.setSize(sizeButtonSize, sizeButtonSize);
        financesUpgradeButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f + sizeButtonSize);
        financesUpgradeButton.getColor().a = 0.8f;

        bestiaryButton = new Image(GraphicsManager.getGuiElement(GuiElementEnum.BESTIARY_BUTTON));
        bestiaryButton.setSize(sizeButtonSize, sizeButtonSize);
        bestiaryButton.setPosition(0, Gdx.graphics.getHeight() * 0.7f + sizeButtonSize * 2);
        bestiaryButton.getColor().a = 0.8f;

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
            xModif -= Gdx.graphics.getWidth()/2f * Gdx.graphics.getDeltaTime();
        }

        batch.begin();
        batch.getColor().a = 1f;

        stateTime += Gdx.graphics.getDeltaTime();
        for (int i=0; i<mainCharacters.size; i++) {
            mainCharacters.get(i).getKeyFrame(stateTime).draw(batch, mainCharactersX[i] - xModif - characterWidth/4f, mainCharactersY, characterWidth, characterHeight);
        }
        for (int i=0; i<npcs.size; i++) {
            npcs.get(i).getKeyFrame(stateTime).draw(batch, npcsX[i] - xModif - characterWidth/4f, npcY, characterWidth, characterHeight);
        }

        MenuBgContainer.drawOnlyLights(batch);

        if (EpicDungeonTactics.GAMEMODE != GameModeEnum.PROMO) {

            campUpgradeButton.draw(batch, 1f);
            financesUpgradeButton.draw(batch, 1f);
            bestiaryButton.draw(batch, 1f);
            batch.getColor().a = 1f;

            for (CampUpgradeButton c : buttons) {
                if (selectedUpgrade != null) {
                    if (selectedUpgrade.getUpgradeEnum() == c.getUpgradeEnum()) {
                        batch.getColor().a = 0.6f;
                        c.draw(batch);
                        batch.getColor().a = 1f;
                    } else c.draw(batch);
                } else c.draw(batch);
            }

            backButton.draw(batch);
            goldStatus.draw(batch);
            if (selectedUpgrade != null) {
                selectedUpgrade.draw(batch);
                if (selectedUpgrade.isUnlockPossible()) unlockButton.draw(batch, 1f);
                else if (selectedUpgrade.isUpgradePossible()) upgradeButton.draw(batch, 1f);
                else if (selectedUpgrade.isMaxedOut()) maxedOutText.draw(batch);
            }
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
                mainCharacters.add(GraphicsManager.getGuiHeroAnimation(allCharacters[i], CharacterStateEnum.RUN));
                mainCharactersX[i] = Gdx.graphics.getWidth() / 2f + (allCharacters.length * characterWidth/2f) / 2f - (i + 1) * characterWidth/2f;
            }
            for (int i = 0; i < allNpcs.length; i++) {
                npcs.add(GraphicsManager.getGuiHeroAnimation(allNpcs[i], CharacterStateEnum.RUN));
                npcsX[i] = mainCharactersX[0] - (i + 0.5f) * characterWidth/2f;
            }
        }
        else {
            for (int i = 0; i < allNpcs.length; i++) {
                npcs.add(GraphicsManager.getGuiHeroAnimation(allNpcs[i], CharacterStateEnum.RUN));
                npcsX[i] = Gdx.graphics.getWidth() / 2f + (allNpcs.length * characterWidth/2f) / 2f - (i + 1f) * characterWidth/2f;

            }
            for (int i = 0; i < allCharacters.length; i++) {
                mainCharacters.add(GraphicsManager.getGuiHeroAnimation(allCharacters[i], CharacterStateEnum.RUN));
                mainCharactersX[i] = npcsX[0] - (i - 0.5f) * characterWidth/2f;
            }
        }
    }

    public boolean tap(float x, float y) {
        CampUpgradeEnum[] allUpgrades = CampUpgradeEnum.values();

        if (backButton.tap(x, y)) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }
        if (x < campUpgradeButton.getWidth() &&
                y > campUpgradeButton.getY() &&
                y < campUpgradeButton.getY() + campUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
        }
        else if (x < financesUpgradeButton.getWidth() &&
                y > financesUpgradeButton.getY() &&
                y < financesUpgradeButton.getY() + financesUpgradeButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.FINANCES_UPGRADE_SCREEN);
        }
        else if (x < bestiaryButton.getWidth() &&
                y > bestiaryButton.getY() &&
                y < bestiaryButton.getY() + bestiaryButton.getHeight()) {
            EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.BESTIARY_SCREEN);
        }

        else if ((selectedUpgrade != null && selectedUpgrade.isUpgradePossible()) || (selectedUpgrade != null && EpicDungeonTactics.GAMEMODE != PROD)) {
            if (upgradeButton.tap(x, y)) {
                SavedInfoManager.saveCampUpgradeLvl(
                        selectedUpgrade.getUpgradeEnum(),
                        SavedInfoManager.getNpcLvl(selectedUpgrade.getUpgradeEnum()) + 1);

                GlobalValues.minusGold(CampUpgradeStats.getCampUpgradeCost(selectedUpgrade.getUpgradeEnum(), selectedUpgrade.getLvl()));
                CampUpgradeCard newCard = new CampUpgradeCard(selectedUpgrade.getUpgradeEnum());
                upgradeCards.put(selectedUpgrade.getUpgradeEnum(), newCard);
                selectedUpgrade = upgradeCards.get(newCard.getUpgradeEnum());
                updateCharacters();

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = new CampUpgradeButton(i, allUpgrades[i]);
                    upgradeCards.put(allUpgrades[i], new CampUpgradeCard(allUpgrades[i]));
                }
            }
            else  {
                for (CampUpgradeButton button : buttons) {
                    if (button.tap(x, y)) {
                        if (selectedUpgrade != null) {
                            if (selectedUpgrade.getUpgradeEnum() == button.getUpgradeEnum())
                                selectedUpgrade = null;
                            else selectedUpgrade = upgradeCards.get(button.getUpgradeEnum());
                        } else selectedUpgrade = upgradeCards.get(button.getUpgradeEnum());
                        return true;
                    }
                }
                if (x < Gdx.graphics.getWidth()/2f - CharacterSelector.iconSize/2f || x > Gdx.graphics.getWidth()/2f + CharacterSelector.iconSize/2f) selectedUpgrade = null;
            }

        }
        else if (selectedUpgrade != null && selectedUpgrade.isUnlockPossible()) {
            if (unlockButton.tap(x, y)) {
                SavedInfoManager.saveCampUpgradeLvl(
                        selectedUpgrade.getUpgradeEnum(),
                        SavedInfoManager.getNpcLvl(selectedUpgrade.getUpgradeEnum()) + 1);

                GlobalValues.minusGold(CampUpgradeStats.getCampUpgradeCost(selectedUpgrade.getUpgradeEnum(), selectedUpgrade.getLvl()));
                CampUpgradeCard newCard = new CampUpgradeCard(selectedUpgrade.getUpgradeEnum());
                upgradeCards.put(selectedUpgrade.getUpgradeEnum(), newCard);
                selectedUpgrade = upgradeCards.get(newCard.getUpgradeEnum());
                updateCharacters();

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = new CampUpgradeButton(i, allUpgrades[i]);
                    upgradeCards.put(allUpgrades[i], new CampUpgradeCard(allUpgrades[i]));
                }
            }
            else  {
                for (CampUpgradeButton button : buttons) {
                    if (button.tap(x, y)) {
                        if (selectedUpgrade != null) {
                            if (selectedUpgrade.getUpgradeEnum() == button.getUpgradeEnum())
                                selectedUpgrade = null;
                            else selectedUpgrade = upgradeCards.get(button.getUpgradeEnum());
                        } else selectedUpgrade = upgradeCards.get(button.getUpgradeEnum());
                        return true;
                    }
                }
                if (x < Gdx.graphics.getWidth()/2f - CharacterSelector.iconSize/2f || x > Gdx.graphics.getWidth()/2f + CharacterSelector.iconSize/2f) selectedUpgrade = null;
            }
        }
        else {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].tap(x, y)) {
                    if (selectedUpgrade != null) {
                        if (selectedUpgrade.getUpgradeEnum() == buttons[i].getUpgradeEnum()) selectedUpgrade = null;
                        else selectedUpgrade = upgradeCards.get(buttons[i].getUpgradeEnum());
                    }
                    else selectedUpgrade = upgradeCards.get(buttons[i].getUpgradeEnum());
                    return true;
                }
            }
            if (selectedUpgrade == null) {
                for (int i = 0; i < npcs.size; i++) {
                    if (x > npcsX[i] - xModif - characterWidth / 4f && x < npcsX[i] - xModif - characterWidth / 4f + characterWidth &&
                            y > npcY && y < npcY + characterHeight / 2f) {

                        if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_BLACKSMITH, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.BLACKSMITH);
                        } else if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_ALCHEMIST, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.ALCHEMIST);
                        } else if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_MAGIC_SHOP, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.MAGIC_SHOP);
                        } else if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_CITIZEN_MALE, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.LUGGAGE_CARRIAGE);
                        } else if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_PRINCESS, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.PRINCESS);
                        } else if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_MOUNTAIN_KING, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.MOUNTAIN_KING);
                        } else if (npcs.get(i) == GraphicsManager.getCharactersAnimation(CharacterEnum.NPC_BUTCHER, CharacterStateEnum.RUN)) {
                            selectedUpgrade = upgradeCards.get(CampUpgradeEnum.BUTCHER);
                        }
                        return true;
                    }
                }
            }
            if (x < Gdx.graphics.getWidth()/2f - CharacterSelector.iconSize/2f || x > Gdx.graphics.getWidth()/2f + CharacterSelector.iconSize/2f) selectedUpgrade = null;
        }
        return false;
    }

    public void show() {
        selectedUpgrade = null;
        xModif = Gdx.graphics.getWidth();
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }


}
