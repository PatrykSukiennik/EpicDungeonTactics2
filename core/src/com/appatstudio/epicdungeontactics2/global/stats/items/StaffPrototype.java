package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

public class StaffPrototype extends ItemPrototype {

    private final int SPEED_EFFECT;
    private final SpellEnum SPELL;
    private final int DMG;
    private final float SPELL_CHANCE;

    public StaffPrototype(ItemEnum id, ItemTypeEnum type, int value, int speed, SpellEnum spell, int dmg, float spellChance) {
        super(id, type, value);

        this.SPEED_EFFECT = speed;
        this.SPELL = spell;
        this.DMG = dmg;
        this.SPELL_CHANCE = spellChance;
    }

    public int getSPEED_EFFECT() {
        return SPEED_EFFECT;
    }

    public int getDMG() {
        return DMG;
    }

    public SpellEnum getSPELL() {
        return SPELL;
    }

    public float getSPELL_CHANCE() {
        return SPELL_CHANCE;
    }
}
