package com.appatstudio.epicdungeontactics2.view.menuScreen;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.StatisticEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.stats.CharacterStats;
import com.appatstudio.epicdungeontactics2.global.stats.HeroStats;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosText;
import com.appatstudio.epicdungeontactics2.view.viewElements.RelativePosTextWithIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public final class CharacterIcon extends Actor {

    private final CharacterEnum characterEnum;
    private final Animation<SpriteDrawable> animation;
    private float stateTime = 0f;

    RelativePosText title, description;
    RelativePosTextWithIcon cost;

    boolean isUnlocked = true;

    private float itemsY;
    private float itemSize;
    private float[] itemsX;
    private SpriteDrawable[] startingItems;
    private RelativePosTextWithIcon[] stats;

    CharacterIcon(CharacterEnum character) {
        this.animation = GraphicsManager.getCharactersAnimation(character, CharacterStateEnum.IDLE);

        this.characterEnum = character;

        this.setSize(Gdx.graphics.getWidth()/2f, Gdx.graphics.getWidth()/2f);
        this.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.8f - this.getHeight());

        title = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_TITLE_UNLOCKED : FontEnum.MENU_HERO_TITLE_LOCKED),
                StringsManager.getCharacterName(characterEnum)
                );

        description = new RelativePosText(
                FontsManager.getFont(
                        isUnlocked ? FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED : FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                StringsManager.getCharacterDescription(characterEnum)
        );

        if (!isUnlocked) this.getColor().a = 0.3f;

        ItemEnum[] items = HeroStats.getStartingItems(characterEnum);
        startingItems = new SpriteDrawable[items.length];
        itemsX = new float[items.length];
        for (int i=0; i<items.length; i++) {
            startingItems[i] = GraphicsManager.getItemImage(items[i]);
            itemSize = this.getWidth() / 5f;
            itemsX[i] = Gdx.graphics.getWidth()/2f - (itemSize/2f * startingItems.length * 1.2f) + i * itemSize * 1.2f;
        }
        itemsY = this.getY() - itemSize * 1.8f;


        StatisticEnum[] allStats = StatisticEnum.values();
        stats = new RelativePosTextWithIcon[allStats.length];
        for (int i=0; i<allStats.length; i++) {
            stats[i] = new RelativePosTextWithIcon(
                    GraphicsManager.getStatIcon(allStats[i]),
                    isUnlocked ? FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_UNLOCKED) : FontsManager.getFont(FontEnum.MENU_HERO_DESCRIPTION_LOCKED),
                    allStats[i].toString() + ": " + Integer.toString(CharacterStats.getStat(characterEnum, allStats[i])),
                    Align.left);
        }
    }

    boolean tap(float x, float y) {
        return x > this.getX() && x < this.getX() + this.getWidth() &&
                y > this.getY() && y < this.getY() + this.getHeight();
    }

    public CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();

        animation.getKeyFrame(stateTime).draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());

        title.draw(batch,
                this.getX() + this.getWidth()/2f,
                this.getY() + this.getHeight() * 1.2f);
        description.draw(batch,
                this.getX() + this.getWidth()/2f,
                this.getY() + this.getHeight() * 1.07f);

        for (int i=0; i<startingItems.length; i++) {
            startingItems[i].draw(batch, this.getX() - Gdx.graphics.getWidth()/2f + this.getWidth()/2f + itemsX[i], itemsY, itemSize, itemSize);
        }

        for (int i=0; i<stats.length; i++) {
            stats[i].draw(batch, this.getX() + this.getWidth()*0.35f, this.getY() - this.getWidth() * 0.6f - (i * this.getWidth() * 0.2f));
        }
    }
}
