package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.actions.characterActions;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.enums.CompleteHeroStatsEnum;
import com.appatstudio.epicdungeontactics2.global.enums.GameModeEnum;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.PlayerStatsTrackerFlagsEnum;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.global.stats.PlayerStatisticsStats;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.StatTracker;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.characters.CharacterDrawable;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.gui.communicatePrinter.CommunicatePrinter;

public class DamageGiving extends AbstractCharacterAction{

    private CharacterDrawable source;
    private CharacterDrawable target;
    private int dmg;
    private boolean isCrit;

    public DamageGiving(CharacterDrawable target, int dmg, CharacterDrawable source) {
        this.target = target;
        this.dmg = dmg;
        this.isCrit = false;
        this.source = source;
    }

    @Override
    public boolean act(float delta) {
        if (target.isHero()) {
            CommunicatePrinter.dmgGot(dmg, StringsManager.getCharacterName(source.getCharacterEnum()), isCrit);
        }
        else {
            SavedInfoManager.playerStatEffect(PlayerStatsTrackerFlagsEnum.DMG_DEALT, dmg * ((EpicDungeonTactics.GAMEMODE == GameModeEnum.DEBUG) ? 1000 : 1));
            SavedInfoManager.playerStatEffect(PlayerStatsTrackerFlagsEnum.HIGHEST_DMG_DEALT, dmg * ((EpicDungeonTactics.GAMEMODE == GameModeEnum.DEBUG) ? 1000 : 1));
            CommunicatePrinter.dmgDealt(dmg, StringsManager.getCharacterName(target.getCharacterEnum()), isCrit);
        }

        int dealDmg = dmg;
        if (!target.isHero() && EpicDungeonTactics.GAMEMODE == GameModeEnum.DEBUG) { dealDmg *= 1000; }
        else if (target.isHero() && EpicDungeonTactics.GAMEMODE == GameModeEnum.DEBUG) { dealDmg /= 2; }
        else {
            if (StatTracker.getCurrentStat(CompleteHeroStatsEnum.HP)
                    <= StatTracker.getCurrentStat(CompleteHeroStatsEnum.MAX_HP) * 0.3f) {

                dealDmg *= StatTracker.getCurrentStat(CompleteHeroStatsEnum.DMG_MULTIPLIER_LESS_THAN_30_PERCENT_HP);
            }
        }

        target.dmgGet(dealDmg);
        return true;
    }
}
