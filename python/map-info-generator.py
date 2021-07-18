# script that generates map info and write it into sourcecode

import xml.etree.ElementTree as ET

character_map = {
    15401: 'BEAR',
    15402: 'BOSS_FOREST_GUARDIAN',
    15403: 'WOLF',
    15501: 'CENTAUR_MALE',
    15502: 'MUSHROOM_SMALL',
    15503: 'MUSHROOM_NORMAL',
    15601: 'CENTAUR_FEMALE',
    15602: 'ENT',
    15603: 'ZOMBIE',
    15701: 'MUSHROOM_LARGE',
    15702: 'ELVEN_KNIGHT',
    15801: '',
    15802: 'ZOMBIE',

    15405: 'ORC_SHAMAN',
    15406: 'BOSS_OGRE',
    15407: 'TROLL',
    15505: 'MASKED_ORC',
    15506: 'GOBLIN',
    15507: 'GNOLL_SHAMAN',
    15605: 'GNOLL_SCOUT',
    15606: 'MUDDY',
    15705: 'GNOLL_BRUTE',
    15706: 'SWAMPY',
    15805: 'GNOLL_OVERSEER',
    15806: 'ORC_WARRIOR',

    15410: 'CHORT',
    15411: 'SKELET',
    15510: 'NECROMANCER',
    15511: 'TINY_ZOMBIE',
    15610: 'WOGOL',
    15611: 'BOSS_BIG_DEMON',
    15710: 'IMP',
    15810: 'ICE_ZOMBIE',

    15414: 'NPC_CITIZEN_MALE',
    15415: 'HERO_BABY',
    15416: '',
    15417: 'NPC_THIEF',
    15514: 'NPC_MAGIC_SHOP',
    15515: 'NPC_PRINCESS',
    15516: 'NPC_NUN_NORMAL',
    15517: 'PET_KNIGHT',
    15614: 'NPC_ALCHEMIST',
    15615: 'NPC_NUN_FAT',
    15616: 'NPC_BLACKSMITH',
    15714: 'NPC_BUTCHER',
    15715: 'NPC_MOUNTAIN_KING',
    15716: 'NPC_BISHOP',
    15814: '',
    15815: 'ELVEN_KING',
    15816: 'PET_HUNTER',

}

animation_map = {
    15421: 'TREE_1',
    15422: 'TREE_7',
    15423: 'TREE_12',
    15521: 'TREE_4',
    15522: 'TREE_6',
    15523: 'TREE_11',
    15621: 'TREE_3',
    15622: 'TREE_10',
    15721: 'TREE_2',
    15722: 'TREE_9',
    15821: 'TREE_8',
    15822: 'TREE_5',

    15426: 'GLOWING_STONE_1',
    15427: 'LAVA_ROCK_SMOKE_4',
    15428: 'LAVA_ROCK_SMOKE_2',
    15526: 'GLOWING_STONE_3',
    15527: 'LAVA_ROCK_SMOKE_3',
    15626: 'GLOWING_STONE_2',
    15627: 'LAVA_ROCK_SMOKE_1',
    15726: 'GLOWING_STONE_4',

    15438: 'CANDLE_BIG',
    15439: 'LAVA',
    15538: 'CANDLE',
    15539: 'WATER',
}

chest = 15431
# todo chest

walkable_array_map = {
    15436: 'NONE',
    15536: 'MOVABLE',
    15636: 'NEW_STAGE'
}

map_counter = {
    1: {'FIRST': 0,
        'REGULAR': 0,
        'BOSS': 0},

    2: {'FIRST': 0,
        'REGULAR': 0,
        'BOSS': 0},

    3: {'FIRST': 0,
        'REGULAR': 0,
        'BOSS': 0}
}

file_room_enum = '../core/src/com/appatstudio/epicdungeontactics2/global/enums/RoomEnum.java'

file_room_map_generator = '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/MapGenerator.java'

file_animations = [
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoElementsLocationsStage1.java',
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoElementsLocationsStage2.java',
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoElementsLocationsStage3.java'
]

file_characters = [
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoEnemyStage1.java',
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoEnemyStage2.java',
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoEnemyStage3.java'
]

file_walkable = [
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoWalkableArrayStage1.java',
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoWalkableArrayStage2.java',
    '../core/src/com/appatstudio/epicdungeontactics2/global/managers/map/mapInfo/MapInfoWalkableArrayStage3.java'
]

enums_text = ''
counter_text = ''
map_chances_text = ''

chars_text = ['', '', '']
anim_text = ['', '', '']
walkable_text = ['', '', '']

for stage in range(1, 4):
    for mode in ('FIRST', 'REGULAR', 'BOSS'):
        index = 1
        mode_text = ''

        while True:
            if mode == 'FIRST':
                mode_text = 'FIRST_ROOM'
            elif mode == 'BOSS':
                mode_text = 'BOSS_ROOM'
            else:
                mode_text = 'REGULAR_ROOM'

            try:

                curr_file_tree = ET.parse('../world_creating_elements/maps/STAGE_'
                                          + str(stage) + '_'
                                          + mode + '_'
                                          + str(index) + '.tmx')

                curr_file_root = curr_file_tree.getroot()

            except IOError:
                print (str(stage) + '_'
                       + mode + '_'
                       + str(index) + '.tmx')
                map_counter[stage][mode] = index

                counter_text = counter_text + 'mapCounter.get(' + str(stage) + ').put(' + mode_text + ', ' \
                               + str(index - 1) + ');\n'

                index = 1
                break

            for layer in curr_file_root.iter('layer'):
                if layer.get('name') == 'anim':
                    for data in layer.iterfind('data'):  # only one
                        d = data.text
                        d = d.replace('0', ' null')
                        for v in animation_map.keys():
                            d = d.replace(str(v + 1), ' ' + animation_map.get(v))

                        d = ('{' + (' ' + (d[1:])).replace(',\n', ' },\n{ '))[:-1] + ' }'
                        anim_text[stage-1] = anim_text[stage-1] + 'animationElementsMap.put(RoomEnum.STAGE_' + str(stage) \
                                           + '_' + mode + '_' + str(index) + \
                                           ', new MapElementAnimationEnum[][] { \n' + d + '\n});\n'

                elif layer.get('name') == 'characters':
                    for data in layer.iterfind('data'):  # only one
                        d = data.text
                        d = d.replace('0,', ' null,')
                        d = d.replace(',0', ', null')
                        for v in character_map.keys():
                            d = d.replace(str(v + 1), ' ' + character_map.get(v))

                        d = ('{' + (' ' + (d[1:])).replace(',\n', ' },\n{ '))[:-1] + ' }'
                        chars_text[stage-1] = chars_text[stage-1] + 'charactersInfoMap.put(RoomEnum.STAGE_' + str(
                            stage) + '_' + mode + '_' + str(
                            index) + ', new CharacterEnum[][] { \n' + d + '\n});\n'

                elif layer.get('name') == 'walkable':
                    for data in layer.iterfind('data'):  # only one
                        d = data.text
                        d = d.replace('0', ' null')
                        for v in walkable_array_map.keys():
                            d = d.replace(str(v + 1), ' ' + walkable_array_map.get(v))

                        d = ('{' + (' ' + (d[1:])).replace(',\n', ' },\n{ '))[:-1] + ' }'
                        walkable_text[stage-1] = walkable_text[stage-1] + 'walkableMapsMap.put(RoomEnum.STAGE_' + str(
                            stage) + '_' + mode + '_' + str(
                            index) + ', new MapPathFindingFlags[][] { \n' + d + '\n});\n'

            enums_text = enums_text + ', STAGE_' + str(stage) + '_' + mode + '_' + str(index) + '\n'

            map_chances_text = map_chances_text + 'mapChances.put(' \
                                                  'STAGE_' + str(stage) + '_' + mode + '_' + str(index) + ', ' \
                               + '100' + ');\n'

            index = index + 1

    enums_text = enums_text + '\n'

enums_text = enums_text[2:]
f_enums = open(file_room_enum, 'r')
file_text = f_enums.readlines()
f_enums.close()
insert_index = file_text.index('//python-insert-enums\n')
delete_index = file_text.index('//python-insert-enums-end\n')
del file_text[insert_index + 1:delete_index]
file_text.insert(insert_index + 1, enums_text)
f_enums = open(file_room_enum, 'w')
f_enums.write("".join(file_text))
f_enums.close()

f_generator = open(file_room_map_generator, 'r')
file_text = f_generator.readlines()
f_generator.close()
insert_index = file_text.index('//python-include-map-counter\n')
delete_index = file_text.index('//python-include-map-counter-end\n')
del file_text[insert_index + 1:delete_index]
file_text.insert(insert_index + 1, counter_text)
f_generator = open(file_room_map_generator, 'w')
f_generator.write("".join(file_text))
f_generator.close()

f_generator = open(file_room_map_generator, 'r')
file_text = f_generator.readlines()
f_generator.close()
insert_index = file_text.index('//python-include-map-chances\n')
delete_index = file_text.index('//python-include-map-chances-end\n')
del file_text[insert_index + 1:delete_index]
file_text.insert(insert_index + 1, map_chances_text)
f_generator = open(file_room_map_generator, 'w')
f_generator.write("".join(file_text))
f_generator.close()

for stage in range(1, 4):
    f_char = open(file_characters[stage-1], 'r')
    file_text = f_char.readlines()
    f_char.close()
    insert_index = file_text.index('//python-insert-char\n')
    delete_index = file_text.index('//python-insert-char-end\n')
    del file_text[insert_index + 1:delete_index]
    file_text.insert(insert_index + 1, chars_text[stage-1])
    f_char = open(file_characters[stage-1], 'w')
    f_char.write("".join(file_text))
    f_char.close()

    f_anim = open(file_animations[stage-1], 'r')
    file_text = f_anim.readlines()
    f_anim.close()
    insert_index = file_text.index('//python-insert-anim\n')
    delete_index = file_text.index('//python-insert-anim-end\n')
    del file_text[insert_index + 1:delete_index]
    file_text.insert(insert_index + 1, anim_text[stage-1])
    f_anim = open(file_animations[stage-1], 'w')
    f_anim.write("".join(file_text))
    f_anim.close()

    f_walkable = open(file_walkable[stage-1], 'r')
    file_text = f_walkable.readlines()
    f_walkable.close()
    insert_index = file_text.index('//python-insert-walkable\n')
    delete_index = file_text.index('//python-insert-walkable-end\n')
    del file_text[insert_index + 1:delete_index]
    file_text.insert(insert_index + 1, walkable_text[stage-1])
    f_walkable = open(file_walkable[stage-1], 'w')
    f_walkable.write("".join(file_text))
    f_walkable.close()
