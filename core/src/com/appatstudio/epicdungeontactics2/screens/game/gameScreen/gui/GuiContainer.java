package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.GlobalValues;
import com.appatstudio.epicdungeontactics2.global.enums.CampUpgradeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CurrentScreenEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GuiStringEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapPathFindingFlags;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.SoundsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.Hero;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.EquipmentWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.equipmentAndShoppingWindow.ShopWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.heroStatWindow.HeroStatWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.minimapWindow.MapWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.pickupItemWindow.PickupItemWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.runEndWindow.RunEndWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.runQuitWindow.RunQuitWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.selectItemsForNextHeroWindow.SelectItemsForNextHeroWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.selectNextHeroWindow.SelectNextHeroWindow;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.statusBars.StatusBarContainer;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.weaponSelector.WeaponSelector;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Room;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.Stage;
import com.appatstudio.epicdungeontactics2.screens.menu.menuScreen.MenuScreen;
import com.appatstudio.epicdungeontactics2.screens.viewElements.ButtonWithText;
import com.appatstudio.epicdungeontactics2.screens.viewElements.GuiButton;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextObject;
import com.appatstudio.epicdungeontactics2.screens.viewElements.TextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public final class GuiContainer {

    private final Batch batch;

    private final RunQuitWindow runQuitWindow;

    private final TextWithIcon goldStatus;
    private final TextObject stageStatus;

    private CommunicatePrinter communicatePrinter;
    private StatusBarContainer statusBarContainer;

    private EquipmentWindow equipmentWindow;
    private final MapWindow mapWindow;
    private HeroStatWindow heroStatWindow;
    private PickupItemWindow pickupItemWindow;
    private ShopWindow shopWindow;
    private WeaponSelector weaponSelector;
    private SelectNextHeroWindow selectNextHeroWindow;
    private SelectItemsForNextHeroWindow selectItemsForNextHeroWindow;
    private RunEndWindow runEndWindow;

    private final GuiButton eqButton, mapButton, pickingButton;

    private final ButtonWithText changeRoomButton;
    private final ButtonWithText goingDownButton;
    private final HashMap<CharacterEnum, ButtonWithText> npcButtons;

    private static Room currRoom;
    private static CharacterDrawable heroInRoom;

    private static Array<AbstractItem> itemsToPick;

    public static final float guiButtonSize = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.1f : Gdx.graphics.getWidth()*0.15f;
    public static final float guiMargin = EpicDungeonTactics.isTablet() ? Gdx.graphics.getWidth() * 0.03f : Gdx.graphics.getWidth() * 0.05f;

    private static GuiContainer INSTANCE;

    private GameScreen gameScreen;

    public static GuiContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GuiContainer(GameScreen.getInstance());
        }
        return INSTANCE;
    }


    private GuiContainer(GameScreen gameScreen) {
        batch = new SpriteBatch();
        batch.enableBlending();

        runQuitWindow = new RunQuitWindow();

        communicatePrinter = new CommunicatePrinter();
        statusBarContainer = new StatusBarContainer(gameScreen.getHero());

        equipmentWindow = new EquipmentWindow(gameScreen.getHero(), gameScreen);
        heroStatWindow = new HeroStatWindow();
        pickupItemWindow = new PickupItemWindow();
        mapWindow = new MapWindow();

        this.gameScreen = gameScreen;

        SpriteDrawable bgAlchemist = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
        SpriteDrawable bgButcher = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
        SpriteDrawable bgBlacksmith = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
        SpriteDrawable bgMagicShop = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);

        switch (SavedInfoManager.getNpcLvl(CampUpgradeEnum.ALCHEMIST)) {
            case 1: {
                bgAlchemist = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
                break;
            }
            case 2: {
                bgAlchemist = GraphicsManager.getGuiElement(GuiElementEnum.SILVER_BUTTON_WIDE);
                break;
            }
            case 3: {
                bgAlchemist = GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE);
                break;
            }
        }

        switch (SavedInfoManager.getNpcLvl(CampUpgradeEnum.BUTCHER)) {
            case 1: {
                bgButcher = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
                break;
            }
            case 2: {
                bgButcher = GraphicsManager.getGuiElement(GuiElementEnum.SILVER_BUTTON_WIDE);
                break;
            }
            case 3: {
                bgButcher = GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE);
                break;
            }
        }

        switch (SavedInfoManager.getNpcLvl(CampUpgradeEnum.BLACKSMITH)) {
            case 1: {
                bgBlacksmith = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
                break;
            }
            case 2: {
                bgBlacksmith = GraphicsManager.getGuiElement(GuiElementEnum.SILVER_BUTTON_WIDE);
                break;
            }
            case 3: {
                bgBlacksmith = GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE);
                break;
            }
        }

        switch (SavedInfoManager.getNpcLvl(CampUpgradeEnum.MAGIC_SHOP)) {
            case 1: {
                bgMagicShop = GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE);
                break;
            }
            case 2: {
                bgMagicShop = GraphicsManager.getGuiElement(GuiElementEnum.SILVER_BUTTON_WIDE);
                break;
            }
            case 3: {
                bgMagicShop = GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE);
                break;
            }
        }

        npcButtons = new HashMap<>();
        npcButtons.put(
                CharacterEnum.NPC_ALCHEMIST,
                new ButtonWithText(
                        bgAlchemist,
                        Gdx.graphics.getWidth() * 0.25f,
                        Gdx.graphics.getHeight() * 0.1f,
                        MenuScreen.BOTTOM_BUTTON_WIDTH,
                        MenuScreen.BOTTOM_BUTTON_HEIGHT,
                        FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                        StringsManager.getCharacterName(CharacterEnum.NPC_ALCHEMIST)));
        npcButtons.put(
                CharacterEnum.NPC_BUTCHER,
                new ButtonWithText(
                        bgButcher,
                        Gdx.graphics.getWidth() * 0.25f,
                        Gdx.graphics.getHeight() * 0.1f,
                        MenuScreen.BOTTOM_BUTTON_WIDTH,
                        MenuScreen.BOTTOM_BUTTON_HEIGHT,
                        FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                        StringsManager.getCharacterName(CharacterEnum.NPC_BUTCHER)));
        npcButtons.put(
                CharacterEnum.NPC_BLACKSMITH,
                new ButtonWithText(
                        bgBlacksmith,
                        Gdx.graphics.getWidth() * 0.25f,
                        Gdx.graphics.getHeight() * 0.1f,
                        MenuScreen.BOTTOM_BUTTON_WIDTH,
                        MenuScreen.BOTTOM_BUTTON_HEIGHT,
                        FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                        StringsManager.getCharacterName(CharacterEnum.NPC_BLACKSMITH)));
        npcButtons.put(
                CharacterEnum.NPC_MAGIC_SHOP,
                new ButtonWithText(
                        bgMagicShop,
                        Gdx.graphics.getWidth() * 0.25f,
                        Gdx.graphics.getHeight() * 0.1f,
                        MenuScreen.BOTTOM_BUTTON_WIDTH,
                        MenuScreen.BOTTOM_BUTTON_HEIGHT,
                        FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                        StringsManager.getCharacterName(CharacterEnum.NPC_MAGIC_SHOP)));

        goldStatus = new TextWithIcon(
                GraphicsManager.getGuiElement(GuiElementEnum.COINS),
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                Integer.toString(GlobalValues.getGold()),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f,
                Align.right
        );
        stageStatus = new TextObject(
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getCurrentStage(),
                Gdx.graphics.getWidth() * 0.95f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() * 0.05f -
                        FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED), "0") * 1.2f
                        - Gdx.graphics.getWidth() * 0.05f,
                Align.right
        );
        changeRoomButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.BRONZE_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.25f,
                Gdx.graphics.getHeight() * 0.1f,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.CHANGE_ROOM)
        );
        goingDownButton = new ButtonWithText(
                GraphicsManager.getGuiElement(GuiElementEnum.YELLOW_BUTTON_WIDE),
                Gdx.graphics.getWidth() * 0.25f,
                Gdx.graphics.getHeight() * 0.1f,
                MenuScreen.BOTTOM_BUTTON_WIDTH,
                MenuScreen.BOTTOM_BUTTON_HEIGHT,
                FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED),
                StringsManager.getGuiString(GuiStringEnum.GO_DOWN)
        );
        changeRoomButton.getColor().a = 0.8f;
        goingDownButton.getColor().a = 0.8f;

        eqButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.EQUIPMENT_ICON), guiButtonSize, 0, Gdx.graphics.getHeight() * 0.7f);
        mapButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.MAP_ICON), guiButtonSize, 0, Gdx.graphics.getHeight() * 0.7f + guiButtonSize);
        pickingButton = new GuiButton(GraphicsManager.getGuiElement(GuiElementEnum.ITEM_PICK_BUTTON), guiButtonSize, 0, Gdx.graphics.getHeight() * 0.7f - guiButtonSize);
        shopWindow = new ShopWindow(StatTracker.getHero());
        selectNextHeroWindow = new SelectNextHeroWindow();
        selectItemsForNextHeroWindow = new SelectItemsForNextHeroWindow();
        weaponSelector = new WeaponSelector();

        heroStatWindow.init(gameScreen.getHero());
    }

    public void newHero() {
        communicatePrinter = new CommunicatePrinter();
        statusBarContainer = new StatusBarContainer(gameScreen.getHero());

        equipmentWindow = new EquipmentWindow(gameScreen.getHero(), gameScreen);
        heroStatWindow = new HeroStatWindow();
        shopWindow = new ShopWindow(StatTracker.getHero());
        selectNextHeroWindow = new SelectNextHeroWindow();
        selectItemsForNextHeroWindow = new SelectItemsForNextHeroWindow();
        weaponSelector = new WeaponSelector();

    }

    public boolean canCameraMove() {
        return  !runQuitWindow.isUp() &&
                !EquipmentWindow.isUp() &&
                !mapWindow.isUp() &&
                !heroStatWindow.isUp() &&
                !PickupItemWindow.isUp() &&
                !shopWindow.isUp() &&
                (runEndWindow == null || !(runEndWindow.isUp())) &&
                !selectNextHeroWindow.isUp();
    }

    public void draw() {
        batch.begin();
        communicatePrinter.draw(batch);
        statusBarContainer.draw(batch);

        goldStatus.draw(batch);
        stageStatus.draw(batch);

        if (EquipmentWindow.isUp()) {
            eqButton.draw(batch, 0.5f);
        }
        else eqButton.draw(batch);

        if (itemsToPick != null && itemsToPick.size > 0) {
            if (PickupItemWindow.isUp()) {
                pickingButton.draw(batch, 0.5f);
            } else pickingButton.draw(batch);
        }

        MapPathFindingFlags flag = GameScreen.canChangeRoom();
        if (flag != null) {
            switch (flag) {
                case NEW_STAGE:
                    goingDownButton.draw(batch, 1f);
                    break;
                case ROOM_NODE:
                    changeRoomButton.draw(batch, 1f);
                    break;
            }
        }

        mapButton.draw(batch);

        if (runQuitWindow.isUp()) {
            runQuitWindow.act(Gdx.graphics.getDeltaTime());
            runQuitWindow.draw(batch, 1f);
        }
        else if (selectNextHeroWindow.isUp()) {
            selectNextHeroWindow.draw(batch);
        }
        else if (selectItemsForNextHeroWindow.isUp()) {
            selectItemsForNextHeroWindow.draw(batch);
        }
        else if (runEndWindow != null && runEndWindow.isUp()) {
            runEndWindow.draw(batch);
        }
        else if (WeaponSelector.isUp()) {
            WeaponSelector.draw(batch);
        }

        else if (EquipmentWindow.isUp()) {
            equipmentWindow.draw(batch);
        }

        else if (mapWindow.isUp()) {
            mapWindow.draw(batch);
        }

        else if (heroStatWindow.isUp()) {
            heroStatWindow.draw(batch);
        }
        else if (PickupItemWindow.isUp()) {
            pickupItemWindow.draw(batch);
        }

        if (currRoom.getNpcMode() != null) {
            if (shopWindow.isUp()) {
                shopWindow.draw(batch, currRoom.getNpcMode());
            }
            else npcButtons.get(currRoom.getNpcMode()).draw(batch, 1f);
        }

        batch.end();
    }

    public boolean tap(float x, float y) {
        if (runQuitWindow.isUp()) {
            runQuitWindow.tap(x, y);
            return true;
        }
        else if (selectNextHeroWindow.isUp()) {
            CharacterEnum selectedHero = selectNextHeroWindow.tap(x, y);
            if (selectedHero != null) {
                selectItemsForNextHeroWindow.show(selectedHero);
                selectNextHeroWindow.hide();
            }
            return true;
        }
        else if (selectItemsForNextHeroWindow.isUp()) {
            selectItemsForNextHeroWindow.tap(x, y);
            return true;
        }
        else if (runEndWindow != null && runEndWindow.isUp()) {
            if (runEndWindow.tap(x, y)) {
                EpicDungeonTactics.refreshMenu();
                EpicDungeonTactics.setCurrentScreen(CurrentScreenEnum.MENU_SCREEN);
            }
            return true;
        }
        else if (weaponSelector != null && weaponSelector.isUp()) {
            ItemTypeEnum weaponSelected = weaponSelector.tap(x, y);
            if (weaponSelected == ItemTypeEnum.MELE) {
                gameScreen.getCurrRoom().setMeleTarget(weaponSelector.getTarget());
            }
            else if (weaponSelected == ItemTypeEnum.BOW) {
                gameScreen.getCurrRoom().shotSelected(weaponSelector.getTarget());
            }
            return true;
        }
        else if (shopWindow.isUp()) {
            if (shopWindow.tap(x, y, currRoom.getNpcMode())) {
            }
            else {
                shopWindow.hide();
            }
            return true;
        }
        else if (currRoom.getNpcMode() != null
                && npcButtons.get(CharacterEnum.NPC_ALCHEMIST).tap(x, y)) {
            shopWindow.show();
            return true;
        }
        else if (mapWindow.isUp()) {
            mapWindow.tap(x, y);
            return true;
        }
        else if (EquipmentWindow.isUp()) {
            equipmentWindow.tap(x, y);
            return true;
        }
        else if (heroStatWindow.isUp()) {
            heroStatWindow.tap(x, y);
            return true;
        }
        else if (PickupItemWindow.isUp()) {
            pickupItemWindow.tap(x, y);
            return true;
        }
        else if (GameScreen.canChangeRoom() != null && changeRoomButton.tap(x, y)) {
            GameScreen.changeRoom();
            return true;
        }
        else if (eqButton.isTap(x, y)) {
            EquipmentWindow.show();
            return true;
        }
        else if (itemsToPick != null && itemsToPick.size > 0 && pickingButton.isTap(x, y)) {
            PickupItemWindow.show(itemsToPick);
            return true;
        }
        else if (mapButton.isTap(x, y)) {
            mapWindow.show();
            return true;
        }
        else if (statusBarContainer.isTap(x, y)) {
            heroStatWindow.show();
            return true;
        }

        return false;
    }

    public void backPressed() {
        if (EquipmentWindow.isUp()) {
            EquipmentWindow.hide();
        }
        else if (mapWindow.isUp()) {
            mapWindow.hide();
        }
        else if (weaponSelector.isUp()) {
            weaponSelector.hide();
        }
        else if (heroStatWindow.isUp()) {
            heroStatWindow.hide();
        }
        else if (PickupItemWindow.isUp()) {
            PickupItemWindow.hide();
        }
        else if (shopWindow.isUp()) {
            shopWindow.hide();
        }
        else if (selectNextHeroWindow.isUp()) {
            runQuitWindow.show();
        }
        else if (selectItemsForNextHeroWindow.isUp()) {
            selectNextHeroWindow.hide();
            selectNextHeroWindow.show();
        }
        else if (runEndWindow != null && runEndWindow.isUp()) {
            runQuitWindow.show();
        }

        else runQuitWindow.show();
    }

    public void updateGold() {
        goldStatus.setText(Integer.toString(GlobalValues.getGold()));
    }

    public void updateStage(Stage newStage) {
        stageStatus.setText(StringsManager.getGuiString(GuiStringEnum.STAGE) + " " + GameScreen.getCurrentStage());
        mapWindow.setStage(newStage);
    }

    public void setMapStage(Stage stage) {
        mapWindow.setStage(stage);
    }

    public void roomChanged(Room oldRoom, Room newRoom) {
        mapWindow.setCurrRoom(oldRoom, newRoom);
    }

    public WeaponSelector getWeaponSelector() {
        return weaponSelector;
    }

    public static void setItemsToPick(Array<AbstractItem> newItemsToPick) {
        if (newItemsToPick == null || newItemsToPick.size == 0) {
            PickupItemWindow.hide();
            itemsToPick = null;
        }
        else {
            if (PickupItemWindow.isUp()) PickupItemWindow.show(newItemsToPick);
            itemsToPick = newItemsToPick;
        }

    }

    public static void refreshStats() {
        HeroStatWindow.refreshStats();
    }

    public void init() {
        //heroStatWindow.init(GameScreen.getHero());
    }

    public void refreshRoom() {
        currRoom = GameScreen.getInstance().getCurrRoom();
        heroInRoom = GameScreen.getInstance().getCurrRoom().getRoomCharacters().get(0);
    }

    public void refreshGui() {
        INSTANCE = new GuiContainer(GameScreen.getInstance());
        communicatePrinter.clear();
    }

    public void createOrRefreshShop(CharacterEnum shop) {
        shopWindow.addOrRefreshShop(shop);
    }

    public void showWeaponSelector(Hero heroInRoom, CharacterDrawable characterStandingOn) {
        weaponSelector.show(heroInRoom, characterStandingOn);
    }

    public void heroDied() {
        if (StatTracker.getUsedCharacters().size == SavedInfoManager.getAllUnlockedCharacters().length) {
            runEndWindow = new RunEndWindow();
            runEndWindow.show();
            SoundsManager.playSound(SoundEnum.HEROES_ALL_DOWN);
        }
        else {
            selectNextHeroWindow.show();
            SoundsManager.playSound(SoundEnum.HERO_DOWN);
        }
    }

    public void hideWeaponSelector() {
        weaponSelector.hide();
    }
}
