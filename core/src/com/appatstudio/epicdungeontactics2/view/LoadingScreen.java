package com.appatstudio.epicdungeontactics2.view;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.input.MobileInputManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.savedInfo.SavedInfoManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public final class LoadingScreen {

    private MultiLineText communicate;
    private Texture bg, bgAlpha;
    private SpriteBatch batch;
    private float bgY, bgHeight;
    private float barX, barY, maxBarWidth, barHeight;


    private float mainCharactersY = (Gdx.graphics.getHeight()/18f) * 9.35f;
    private float npcY = (Gdx.graphics.getHeight()/18f) * 8.35f;
    private static float characterWidth = (Gdx.graphics.getHeight()/18f) * 2;
    private static float characterHeight = (Gdx.graphics.getHeight()/18f) * 2;
    private static float[] mainCharactersX;
    private static float[] npcsX;
    private static Array<Animation<SpriteDrawable>> mainCharacters;
    private static Array<Animation<SpriteDrawable>> npcs;

    private float stateTime = 0f;


    public LoadingScreen() {
        batch = new SpriteBatch();

        bgAlpha = new Texture("gui/BLACK_ALPHA_50percent.png");
        bg = new Texture("loading-screen/bg.jpg");

        bgHeight = Gdx.graphics.getHeight();
        bgY = Gdx.graphics.getHeight() / 2f - bgHeight / 2f;
        maxBarWidth = Gdx.graphics.getWidth() * 0.7f;
        barX = Gdx.graphics.getWidth() / 2f - maxBarWidth / 2f;
        barY = Gdx.graphics.getHeight() * 0.3f;
        barHeight = maxBarWidth * (9/120f);
        String randomText = getRandomLoadingCommunicate();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/decorated_font_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.characters = randomText;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        fontParameter.size = (int) (Gdx.graphics.getWidth() * 0.05f);

        communicate = new MultiLineText(
                fontGenerator.generateFont(fontParameter),
                randomText,
                Gdx.graphics.getWidth()/2f,
                maxBarWidth,
                Gdx.graphics.getHeight() * 0.25f,
                Align.center);

        mainCharacters = new Array<>();
        npcs = new Array<>();
        updateCharacters();
    }

    private String getRandomLoadingCommunicate() {
        I18NBundle loadingTexts = I18NBundle.createBundle(Gdx.files.internal("strings/loading-texts"), Locale.getDefault());
        return loadingTexts.get(Integer.toString(Math.abs(EpicDungeonTactics.random.nextInt(199) + 1)));
    }

    public void draw(float progress) {
        batch.begin();
        batch.draw(bg, Gdx.graphics.getWidth()/2f - bgHeight/2f, bgY, bgHeight, bgHeight);

        batch.draw(bgAlpha, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stateTime += Gdx.graphics.getDeltaTime();
        for (int i=0; i<mainCharacters.size; i++) {
            mainCharacters.get(i).getKeyFrame(stateTime).draw(batch, mainCharactersX[i] - (0.5f - progress * 1.4f) * Gdx.graphics.getWidth() - characterWidth/4f, mainCharactersY, characterWidth, characterHeight);
        }
        for (int i=0; i<npcs.size; i++) {
            npcs.get(i).getKeyFrame(stateTime).draw(batch, npcsX[i] - (0.5f - progress * 1.4f) * Gdx.graphics.getWidth() - characterWidth/4f, npcY, characterWidth, characterHeight);
        }

        communicate.draw(batch);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        bg.dispose();
        bgAlpha.dispose();

//
//        cannot dispose that dont know why :(
//
//        for (Animation<SpriteDrawable> an : npcs) {
//            for (SpriteDrawable sp : an.getKeyFrames()) {
//                sp.getSprite().getTexture().dispose();
//            }
//        }
//        for (Animation<SpriteDrawable> an : mainCharacters) {
//            for (SpriteDrawable sp : an.getKeyFrames()) {
//                sp.getSprite().getTexture().dispose();
//            }
//        }

        Gdx.input.setInputProcessor(new GestureDetector(new MobileInputManager()));
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }


    private void updateCharacters() {
        mainCharacters.clear();
        npcs.clear();

        CharacterEnum[] allCharacters = SavedInfoManager.getAllUnlockedCharacters();
        CharacterEnum[] allNpcs = SavedInfoManager.getAllUnlockedNpcs();
        mainCharactersX = new float[allCharacters.length];
        npcsX = new float[allNpcs.length];

        if (allCharacters.length >= allNpcs.length) {

            for (int i = 0; i < allCharacters.length; i++) {
                mainCharacters.add(createAnimation(allCharacters[i]));
                mainCharactersX[i] = Gdx.graphics.getWidth() / 2f + (allCharacters.length * characterWidth/2f) / 2f - (i + 1) * characterWidth/2f;
            }
            for (int i = 0; i < allNpcs.length; i++) {
                npcs.add(createAnimation(allNpcs[i]));
                npcsX[i] = mainCharactersX[0] - (i + 0.5f) * characterWidth/2f;
            }
        }
        else {
            for (int i = 0; i < allNpcs.length; i++) {
                npcs.add(createAnimation(allNpcs[i]));
                npcsX[i] = Gdx.graphics.getWidth() / 2f + (allNpcs.length * characterWidth/2f) / 2f - (i + 1f) * characterWidth/2f;

            }
            for (int i = 0; i < allCharacters.length; i++) {
                mainCharacters.add(createAnimation(allCharacters[i]));
                mainCharactersX[i] = npcsX[0] - (i - 0.5f) * characterWidth/2f;
            }
        }
    }

    private Animation<SpriteDrawable> createAnimation(CharacterEnum character) {
        Array<SpriteDrawable> arr = new Array<>();
        arr.add(new SpriteDrawable(new Sprite(new Texture("game-world-graphics/characters/" + character.toString() + "/run_0.png"))));
        arr.add(new SpriteDrawable(new Sprite(new Texture("game-world-graphics/characters/" + character.toString() + "/run_1.png"))));
        arr.add(new SpriteDrawable(new Sprite(new Texture("game-world-graphics/characters/" + character.toString() + "/run_2.png"))));
        arr.add(new SpriteDrawable(new Sprite(new Texture("game-world-graphics/characters/" + character.toString() + "/run_3.png"))));

        return new Animation<>(
                0.1f,
                arr,
                Animation.PlayMode.LOOP
        );
    }
}