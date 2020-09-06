package com.appatstudio.epicdungeontactics2.view.gameScreen.items;

import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;
import com.appatstudio.epicdungeontactics2.global.stats.itemEffects.ItemEffect;
import com.badlogic.gdx.utils.Array;

public class Staff extends AbstractItem {

    private final SpellEnum spell;
    private final int dmg;
    private final int speedEffect;
    private final float spellChance;

    public Staff(ItemEnum itemEnum, ItemTypeEnum typeEnum, int value, Array<ItemEffect> effects, SpellEnum spell, int dmg, float spellChance, int speedEffect, ItemRarityEnum rarityEnum) {
        super(itemEnum, typeEnum, value, effects, rarityEnum);
        this.spell = spell;
        this.dmg = dmg;
        this.spellChance = spellChance;
        this.speedEffect = speedEffect;
    }

}
