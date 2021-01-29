package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.communicatePrinter;

import com.appatstudio.epicdungeontactics2.global.enums.CommunicatesEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class CommunicatePrinter {

    private static final int COMMUNICATE_LIMIT = 6;
    private static final float[] TEXT_Y;
    private static CommunicateObject[] communicates;

    private static final float CLEARING_TIME = 3f;
    private static float clearingTimer;

    static  {

        TEXT_Y = new float[COMMUNICATE_LIMIT];
        communicates = new CommunicateObject[COMMUNICATE_LIMIT];

        for (int i=0; i<COMMUNICATE_LIMIT; i++) {
            TEXT_Y[i] = Gdx.graphics.getWidth() * 0.05f
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.COMMUNICATE_WHITE), "0") * 0.3f
                    + FontsManager.getTextHeight(FontsManager.getFont(FontEnum.COMMUNICATE_WHITE), "0") * 1.5f * (i+1);
        }

        clearingTimer = CLEARING_TIME;

    }

    private static void print(CommunicateObject communicate) {
        for (int i=COMMUNICATE_LIMIT-2; i>=0; i--) {
            if (communicates[i] != null) communicates[i+1] = communicates[i];
        }
        communicates[0] = communicate;
        clearingTimer = CLEARING_TIME;
    }

    public static void dmgGot(int dmg, String enemyName, boolean isCrit) {
        BitmapFont font = FontsManager.getFont(
                isCrit ? FontEnum.COMMUNICATE_DARK_RED : FontEnum.COMMUNICATE_RED);
        String text = enemyName + StringsManager.getCommunicate(CommunicatesEnum.DMG_GET) + " " + dmg;
        if (isCrit) text += " " + StringsManager.getCommunicate(CommunicatesEnum.CRITICAL);
        print(new CommunicateObject(font, text));
    }

    public static void dmgDealt(int dmg, String enemyName, boolean isCrit) {
        BitmapFont font = FontsManager.getFont(
                FontEnum.COMMUNICATE_WHITE);
        String text = StringsManager.getCommunicate(CommunicatesEnum.DMG_DEALT) + " " + enemyName + ": " + dmg;
        if (isCrit) text += " " + StringsManager.getCommunicate(CommunicatesEnum.CRITICAL);
        print(new CommunicateObject(font, text));
    }

    public static void usedItem(String itemName) {
        BitmapFont font = FontsManager.getFont(
                FontEnum.COMMUNICATE_GRAY);
        String text = StringsManager.getCommunicate(CommunicatesEnum.USE_ITEM) + " " + itemName;
        print(new CommunicateObject(font, text));
    }

    public static void restoredHp(int amount) {
        BitmapFont font = FontsManager.getFont(
                FontEnum.COMMUNICATE_WHITE);
        String text = StringsManager.getCommunicate(CommunicatesEnum.HEALED) + " " + amount;
        print(new CommunicateObject(font, text));
    }

    public static void killedEnemy(String enemyName, boolean isBoss) {
        BitmapFont font = FontsManager.getFont(
                isBoss ? FontEnum.COMMUNICATE_GOLD : FontEnum.COMMUNICATE_WHITE);
        String text = enemyName + " " + StringsManager.getCommunicate(CommunicatesEnum.DOWN);
        print(new CommunicateObject(font, text));
    }

    public static void lvlUp() {
        BitmapFont font = FontsManager.getFont(
                FontEnum.COMMUNICATE_GOLD);
        String text = StringsManager.getCommunicate(CommunicatesEnum.LVL_UP);
        print(new CommunicateObject(font, text));
    }

    public static void usedSkill(String skillName) {
        BitmapFont font = FontsManager.getFont(
                FontEnum.COMMUNICATE_WHITE);
        String text = StringsManager.getCommunicate(CommunicatesEnum.USED_SKILL) + " " + skillName;
        print(new CommunicateObject(font, text));
    }

    public static void itemPickedUp(String itemName) {
        BitmapFont font = FontsManager.getFont(
                FontEnum.COMMUNICATE_GRAY);
        String text = StringsManager.getCommunicate(CommunicatesEnum.COLLECT_ITEM) + " " + itemName;
        print(new CommunicateObject(font, text));
    }

    public void draw(Batch batch) {
        for (int i=0; i<COMMUNICATE_LIMIT; i++) {
            if (communicates[i] != null) communicates[i].draw(batch, TEXT_Y[i]);
        }

        clearingTimer -= Gdx.graphics.getDeltaTime();
        if (clearingTimer <= 0) {
            for (int i=communicates.length -1; i>=0; i--) {
                if (communicates[i] != null) {
                    communicates[i] = null;
                    break;
                }
            }
            clearingTimer = 5f;
        }
    }



}

