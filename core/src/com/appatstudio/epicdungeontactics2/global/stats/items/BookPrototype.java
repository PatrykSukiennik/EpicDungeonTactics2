package com.appatstudio.epicdungeontactics2.global.stats.items;

import com.appatstudio.epicdungeontactics2.global.enums.SpellEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemTypeEnum;

import lombok.Getter;

public class BookPrototype extends ItemPrototype {

    @Getter private final SpellEnum spell;
    @Getter private final int MP_COST;
    @Getter private final int DMG;
    @Getter private final int DURATION;

    public BookPrototype(ItemEnum id, ItemTypeEnum type, int value, SpellEnum spell, int mpCost, int duration, int dmg, ItemRarityEnum rarityEnum) {
        super(id, type, value, rarityEnum);

        this.spell = spell;
        this.MP_COST = mpCost;
        this.DURATION = duration;
        this.DMG = dmg;
    }

    public int getMP_COST() {
        return MP_COST;
    }

    public SpellEnum getSpell() {
        return spell;
    }

    public int getDURATION() {
        return DURATION;
    }

    public int getDMG() {
        return DMG;
    }
}
