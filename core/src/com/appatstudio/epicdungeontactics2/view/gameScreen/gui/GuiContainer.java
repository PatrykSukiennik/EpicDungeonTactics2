package com.appatstudio.epicdungeontactics2.view.gameScreen.gui;

import com.appatstudio.epicdungeontactics2.global.enums.EffectEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.communicatePrinter.CommunicatePrinter;
import com.appatstudio.epicdungeontactics2.view.gameScreen.gui.statusBars.StatusBarContainer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class GuiContainer {

    private Batch batch;

    private CommunicatePrinter communicatePrinter;
    private StatusBarContainer statusBarContainer;

    public GuiContainer(GameScreen gameScreen) {
        batch = new SpriteBatch();

        communicatePrinter = new CommunicatePrinter();
        statusBarContainer = new StatusBarContainer(gameScreen.getHero());

        statusBarContainer.addEffect(EffectEnum.POISON, 4);
        statusBarContainer.addEffect(EffectEnum.POISON, 3);
        statusBarContainer.addEffect(EffectEnum.POISON, 2);
        statusBarContainer.addEffect(EffectEnum.POISON, 11);
    }

    public void draw() {
        batch.begin();
        communicatePrinter.draw(batch);
        statusBarContainer.draw(batch);

        batch.end();
    }

}
