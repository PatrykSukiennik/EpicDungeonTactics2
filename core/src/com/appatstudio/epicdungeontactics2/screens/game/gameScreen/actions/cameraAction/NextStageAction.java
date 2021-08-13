package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.cameraAction;

import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Action;

public class NextStageAction extends Action {
    @Override
    public boolean act(float delta) {
        System.out.println("NEXTSTAGEGEGEGEGE");
        GameScreen.nextStageNow();
        return true;
    }
}
