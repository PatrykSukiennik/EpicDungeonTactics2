package com.appatstudio.epicdungeontactics2.global.assets;

import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.MusicEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundsLoader {

    static void init(AssetManager assetManager) {
        SoundEnum[] allSounds = SoundEnum.values();
        for (SoundEnum s : allSounds) {
            assetManager.load("sound/sounds/" + s.toString() + ".ogg", Sound.class);
        }

        MusicEnum[] allMusic = MusicEnum.values();
        for (MusicEnum m : allMusic) {
            assetManager.load("sound/music/" + m.toString() + ".mp3", Music.class);
        }

    }

}
