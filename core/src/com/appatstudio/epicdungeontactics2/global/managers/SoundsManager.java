package com.appatstudio.epicdungeontactics2.global.managers;

import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.MusicEnum;
import com.appatstudio.epicdungeontactics2.global.enums.soundEnum.SoundEnum;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public final class SoundsManager {

    private static final float MUSIC_TRANSITION_DURATION = 0.3f;
    private static float musicTransitionTime = -1;

    private static Map<SoundEnum, Sound> soundMap;
    private static Map<MusicEnum, Music> musicMap;

    private static Music currentMusic = null;
    private static MusicEnum currentMusicEnum = null;
    private static MusicEnum nextMusicEnum = null;
    private static float volume = 0;

    private static final float STEP_TIME = 0.15f;
    private static float stepTimer = STEP_TIME;
    private static boolean isCharacterMoving = false;

    public static void load(AssetManager assetManager) {
        soundMap = new HashMap<>();
        SoundEnum[] allSounds = SoundEnum.values();

        for (SoundEnum s : allSounds) {
            soundMap.put(s, assetManager.get("sound/sounds/" + s.toString() + ".ogg", Sound.class));
        }

        musicMap = new HashMap<>();
        MusicEnum[] allMusic = MusicEnum.values();
        for (MusicEnum m : allMusic) {
            musicMap.put(m, assetManager.get("sound/music/" + m.toString() + ".mp3", Music.class));
        }
    }

    public static void tick() {
        if (musicTransitionTime > 0) {
            musicTransitionTime -= Gdx.graphics.getDeltaTime();
            if (musicTransitionTime < 0) volume = 1;
            else {

                if (musicTransitionTime > MUSIC_TRANSITION_DURATION) {
                    volume -= (1f / MUSIC_TRANSITION_DURATION) * Gdx.graphics.getDeltaTime();
                    if (volume < 0) volume = 0;
                } else {
                    if (currentMusicEnum != nextMusicEnum) {
                        if (currentMusic != null) currentMusic.stop();

                        currentMusicEnum = nextMusicEnum;
                        currentMusic = musicMap.get(currentMusicEnum);
                        currentMusic.setLooping(true);
                        currentMusic.play();

                        volume += (1f / MUSIC_TRANSITION_DURATION) * Gdx.graphics.getDeltaTime();
                        if (volume > 1) volume = 1;
                    }
                }
            }
            currentMusic.setVolume(volume);
        }
        if (isCharacterMoving) {
            stepTimer -= Gdx.graphics.getDeltaTime();
            if (stepTimer < 0) {
                playSound(GameScreen.stepSound);
                stepTimer = STEP_TIME;
            }
        }
    }

    public static void playMusic(MusicEnum musicEnum) {
//        nextMusicEnum = musicEnum;
//        musicTransitionTime = (currentMusic != null) ? MUSIC_TRANSITION_DURATION * 2 : MUSIC_TRANSITION_DURATION;
    }

    public static void playSound(SoundEnum soundEnum) {
        System.out.println("PLAY SOUND: " + soundEnum.toString());
        soundMap.get(soundEnum).play();
    }

    public static void setCharacterMoving(boolean state) {
        isCharacterMoving = state;
    }
}
