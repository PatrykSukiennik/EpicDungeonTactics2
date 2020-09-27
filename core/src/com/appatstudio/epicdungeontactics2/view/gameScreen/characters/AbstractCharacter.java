package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightConfigObject;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterPrototype;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.view.gameScreen.actions.MapAction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.HashMap;

import box2dLight.PointLight;
import box2dLight.RayHandler;

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
        this.stats.put(STR, basicPrototype.getSTR() + (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * lvlPrototype.getSTR());
        this.stats.put(INT, basicPrototype.getINT() + (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * lvlPrototype.getINT());
        this.stats.put(VIT, basicPrototype.getVIT() + (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * lvlPrototype.getVIT());
        this.stats.put(DEX, basicPrototype.getDEX() + (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * lvlPrototype.getDEX());
        this.stats.put(LCK, basicPrototype.getLCK() + (int)StatTracker.getCurrentStat(CompleteHeroStatsEnum.LVL) * lvlPrototype.getLCK());

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

    public void move(MapAction move) {
        this.characterDrawable.addAction(move.getMoveAction());
    }

    public void setState(CharacterStateEnum state) {
        this.state = state;
    }

    public int getStat(StatisticEnum stat) {
        return stats.get(stat);
    }
}
