package com.appatstudio.epicdungeontactics2.view.gameScreen.gui.turnQueue;

import com.appatstudio.epicdungeontactics2.global.enums.GuiElementEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.view.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class TurnQueue {

    private static final Image queueBorder;
    public static final CoordsFloat[] COORDS;

    private TurnQueueIcon[] queueIcons;
    private Array<CharacterDrawable> queue;
    private HashMap<CharacterDrawable, Boolean> queueAlive;

    public static final float[] ICON_ALPHA = {0f, 1f, 0.55f, 0.45f, 0.3f, 0.12f, 0.09f, 0.07f, 0f};
    public static final float QUEUE_MOVE_DURATION = 0.4f;
    public static final float QUEUE_ICON_SIZE = Gdx.graphics.getWidth() * 0.12f;
    public static final int QUEUE_SIZE = 9;
    public static final float QUEUE_START_Y =
            Gdx.graphics.getHeight() - (QUEUE_SIZE - 2) * QUEUE_ICON_SIZE;

    private int currentTurn = 1;
    private int maxSpeed;

    private Room room;

    static {
        COORDS = new CoordsFloat[QUEUE_SIZE];
        for (int i = 0; i < QUEUE_SIZE; i++) {
            COORDS[i] = new CoordsFloat(
                    Gdx.graphics.getWidth() - QUEUE_ICON_SIZE,
                    QUEUE_START_Y + i * QUEUE_ICON_SIZE);

        }

        queueBorder = new Image(GraphicsManager.getGuiElement(GuiElementEnum.QUEUE_BORDER));
        queueBorder.setSize(QUEUE_ICON_SIZE, QUEUE_ICON_SIZE);
        queueBorder.setPosition(Gdx.graphics.getWidth() - QUEUE_ICON_SIZE, QUEUE_START_Y + QUEUE_ICON_SIZE);
    }

    public TurnQueue(Room room) {
        this.room = room;

        queue = room.getTurnQueue();
        queueIcons = new TurnQueueIcon[QUEUE_SIZE];
        for (int i = 0; i < QUEUE_SIZE; i++) {
            queueIcons[i] = new TurnQueueIcon(i, COORDS[i]);
            queueIcons[i].setRepresentedCharacter(queue.get( (i + 1) % queue.size ));
        }

        queueAlive = new HashMap<>();
        for (CharacterDrawable c : queue) {
            queueAlive.put(c, true);
        }

    }

    public void draw(Batch batch) {
        for (TurnQueueIcon icon : queueIcons) {
            icon.act(Gdx.graphics.getDeltaTime());
            icon.draw(batch, queueAlive.get(icon.getRepresentedCharacter()) ? 1f : 0.2f);
        }
        queueBorder.draw(batch, 1f);
    }

    public void tick() {
        for (TurnQueueIcon icon : queueIcons) {
            icon.tick();
        }
        currentTurn++;
    }

    public CharacterDrawable getCurrentCharacter() {
        System.out.println("CURRENT CHARACTER TO MOVE: " +  queueIcons[currentTurn % QUEUE_SIZE].getRepresentedCharacter().getCharacterEnum().toString());
        return
                queueAlive.get(queueIcons[currentTurn % QUEUE_SIZE].getRepresentedCharacter())
                        ? queueIcons[currentTurn % QUEUE_SIZE].getRepresentedCharacter() : null;
    }

    private int countNotDestroyed() {
        int counter = 0;
        for (TurnQueueIcon icon : queueIcons) {
            if (!icon.isDestroyed()) counter++;
        }
        return counter;
    }

    public CharacterDrawable getTapCharacter(float x, float y) {
        for (TurnQueueIcon t : queueIcons) {
            if (t.isTap(x, y)) return t.getRepresentedCharacter();
        }
        return null;
    }

    public void removeCharacter(CharacterDrawable characterDrawable) {
        queueAlive.put(characterDrawable, false);
    }
}
