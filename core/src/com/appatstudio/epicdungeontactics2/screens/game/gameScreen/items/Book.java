package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public final class Book extends AbstractItem {

    private SpellEnum spell;
    private int mpCost;
    private int dmg;
    private int duration;

    public Book(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, SpellEnum spell, int mpCost, int duration, int dmg, ItemRarityEnum rarityEnum) {
        super(itemEnum, typeEnum, value, effects, rarityEnum);
        this.spell = spell;
        this.mpCost = mpCost;
        this.duration = duration;
        this.dmg = dmg;
    }

    public SpellEnum getSpell() {
        return spell;
    }

    public int getDmg() {
        return dmg;
    }

    public int getDuration() {
        return duration;
    }

    public int getMpCost() {
        return mpCost;
    }
}
