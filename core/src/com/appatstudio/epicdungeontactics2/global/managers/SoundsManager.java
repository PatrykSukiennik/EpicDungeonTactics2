package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.MusicEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public final class SoundsManager {

    private static Map<SoundEnum, Sound> soundMap;
    private static Map<MusicEnum, Music> musicMap;

    public static void load(AssetManager assetManager) {
        soundMap = new HashMap<>();
        SoundEnum[] allSounds = SoundEnum.values();
        for (SoundEnum s : allSounds) {
            soundMap.put(s, assetManager.get("sounds/" + s.toString() + ".mp3", Sound.class));
        }

        musicMap = new HashMap<>();
        MusicEnum[] allMusic = MusicEnum.values();
        for (MusicEnum m : allMusic) {
            musicMap.put(m, assetManager.get("sounds/" + m.toString() + ".mp3", Music.class));
        }
    }

    public static Sound getSound(SoundEnum soundEnum) {
        return soundMap.get(soundEnum);
    }

    public static Music getMusic(MusicEnum musicEnum) {
        return musicMap.get(musicEnum);
    }

}
