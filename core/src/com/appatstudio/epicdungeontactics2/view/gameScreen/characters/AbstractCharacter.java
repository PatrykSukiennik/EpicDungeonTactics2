package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.Move;

public abstract class AbstractCharacter {

    private final CharacterEnum characterEnum;
    private final CharacterDrawable characterDrawable;
    private int posX;
    private int posY;
    private CharacterStateEnum state;

    protected AbstractCharacter(CharacterEnum characterEnum, CharacterDrawable characterDrawable, int posX, int posY) {
        this.characterEnum = characterEnum;
        this.characterDrawable = characterDrawable;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void move(Move move) {
        this.characterDrawable.addAction(move.getMoveAction());
    }

    public void setState(CharacterStateEnum state) {
        this.state = state;
    }
}
