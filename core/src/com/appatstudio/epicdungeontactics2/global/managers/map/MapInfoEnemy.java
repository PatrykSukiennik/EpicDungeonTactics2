package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.RoomEnum;

import java.util.HashMap;

import static com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum.*;

public class MapInfoEnemy {

    private static final HashMap<RoomEnum, CharacterEnum[][]> charactersInfoMap;

    static {
        charactersInfoMap = new HashMap<>();
//python-insert-char
charactersInfoMap.put(RoomEnum.STAGE_1_FIRST_1, new CharacterEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, NPC_CITIZEN_MALE, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, NPC_BLACKSMITH, null, null },
{  null, null, null, NPC_PRINCESS, null, null, null, null, null, null, null },
{  null, null, NPC_MOUNTAIN_KING, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, NPC_BUTCHER, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, NPC_ALCHEMIST, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, NPC_MAGIC_SHOP, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
charactersInfoMap.put(RoomEnum.STAGE_1_REGULAR_1, new CharacterEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, BEAR, null, null, null, ZOMBIE, null, ENT, null },
{  null, null, null, null, null, null, null, null, null, ZOMBIE, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, WOLF, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, WOLF, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, WOLF, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
charactersInfoMap.put(RoomEnum.STAGE_1_BOSS_1, new CharacterEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, MUSHROOM_NORMAL, null },
{  null, null, ELVEN_KNIGHT, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, BOSS_FOREST_GUARDIAN, null, null, null, null, null },
{  null, null, ELVEN_KNIGHT, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, ELVEN_KNIGHT, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, CENTAUR_MALE, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, MUSHROOM_SMALL, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
charactersInfoMap.put(RoomEnum.STAGE_2_FIRST_1, new CharacterEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, NPC_BLACKSMITH, null, null, null },
{  null, null, NPC_CITIZEN_MALE, null, null, null, null, null, NPC_BUTCHER, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, NPC_MAGIC_SHOP, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, NPC_ALCHEMIST, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, NPC_PRINCESS, null, null, null, null },
{  null, null, null, null, null, null, null, NPC_MOUNTAIN_KING, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
charactersInfoMap.put(RoomEnum.STAGE_3_FIRST_1, new CharacterEnum[][] { 
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, NPC_PRINCESS, null, null, null, null, NPC_BUTCHER, null, null },
{  null, null, null, NPC_MOUNTAIN_KING, null, null, null, null, null, NPC_ALCHEMIST, null },
{  null, null, NPC_CITIZEN_MALE, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, NPC_MAGIC_SHOP, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, NPC_BLACKSMITH, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null },
{  null, null, null, null, null, null, null, null, null, null, null }
});
//python-insert-char-end

    }

    public static CharacterEnum[][] getCharactersInfo(RoomEnum roomEnum) {
        return charactersInfoMap.get(roomEnum);
    }
}
