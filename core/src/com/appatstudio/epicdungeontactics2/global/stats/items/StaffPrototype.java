package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class StaffPrototype extends ItemPrototype {

    @Getter private final int SPEED_EFFECT;
    @Getter private final SpellEnum SPELL;
    @Getter private final int DMG;
    @Getter private final float SPELL_CHANCE;
    @Getter private final int RANGE;

    public StaffPrototype(ItemEnum id, ItemTypeEnum type, int value, int speed, SpellEnum spell, int dmg, float spellChance, ItemRarityEnum rarityEnum, int range) {
        super(id, type, value, rarityEnum);

        this.SPEED_EFFECT = speed;
        this.SPELL = spell;
        this.DMG = dmg;
        this.SPELL_CHANCE = spellChance;
        this.RANGE = range;
    }

}
