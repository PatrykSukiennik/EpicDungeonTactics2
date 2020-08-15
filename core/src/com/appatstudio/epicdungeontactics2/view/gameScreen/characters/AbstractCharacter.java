package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.Move;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum.DEX;
import static com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum.INT;
import static com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum.LCK;
import static com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum.STR;
import static com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum.VIT;

public abstract class AbstractCharacter {

    private final CharacterEnum characterEnum;
    private final CharacterDrawable characterDrawable;
    private int posX;
    private int posY;
    private CharacterStateEnum state;



    private HashMap<StatisticEnum, Integer> stats;


    protected AbstractCharacter(CharacterEnum characterEnum, CharacterDrawable characterDrawable, int posX, int posY) {
        this.characterEnum = characterEnum;
        this.characterDrawable = characterDrawable;
        this.posX = posX;
        this.posY = posY;
        this.state = CharacterStateEnum.IDLE;


        this.stats = new HashMap<>();
        CharacterPrototype basicPrototype = CharacterStats.getPrototype(characterEnum);
        CharacterPrototype lvlPrototype = CharacterStats.getEnemeyLvlUpStats(characterEnum);
        this.stats.put(STR, basicPrototype.getSTR() + StatTracker.getLvl() * lvlPrototype.getSTR());
        this.stats.put(INT, basicPrototype.getINT() + StatTracker.getLvl() * lvlPrototype.getINT());
        this.stats.put(VIT, basicPrototype.getVIT() + StatTracker.getLvl() * lvlPrototype.getVIT());
        this.stats.put(DEX, basicPrototype.getDEX() + StatTracker.getLvl() * lvlPrototype.getDEX());
        this.stats.put(LCK, basicPrototype.getLCK() + StatTracker.getLvl() * lvlPrototype.getLCK());

    }

    public void draw(Batch batch) {
        characterDrawable.act(Gdx.graphics.getDeltaTime());
        characterDrawable.draw(batch, 1f);
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

    public int getStat(StatisticEnum stat) {
        return stats.get(stat);
    }
}
