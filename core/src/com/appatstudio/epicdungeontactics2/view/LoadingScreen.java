package com.appatstudio.epicdungeontactics2.view;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.input.MobileInputManager;
import com.appatstudio.epicdungeontactics2.view.viewElements.MultiLineText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public final class LoadingScreen {

    private MultiLineText communicate;
    private Texture bg, barBg, bar;
    private SpriteBatch batch;
    private String randomText;
    private float bgY, bgHeight;
    private float barX, barY, maxBarWidth, barHeight;

    public LoadingScreen() {
        batch = new SpriteBatch();

        bg = new Texture("loading-screen/bg.jpg");
        bar = new Texture("loading-screen/bar.jpg");
        barBg = new Texture("loading-screen/barBg.jpg");

        bgHeight = Gdx.graphics.getWidth() * 2.5f;
        bgY = Gdx.graphics.getHeight()/2f - bgHeight/2f;
        maxBarWidth = Gdx.graphics.getWidth() * 0.7f;
        barX = Gdx.graphics.getWidth()/2f - maxBarWidth/2f;
        barY = Gdx.graphics.getHeight() * 0.3f;
        barHeight = maxBarWidth * 0.05f;
        randomText = getRandomLoadingCommunicate();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/decoratedFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.characters = randomText;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        fontParameter.size = (int)(Gdx.graphics.getWidth() * 0.05f);

        communicate = new MultiLineText(fontGenerator.generateFont(fontParameter), randomText, Gdx.graphics.getWidth()/2f, maxBarWidth, Gdx.graphics.getHeight() * 0.25f, Align.center);

    }

    private String getRandomLoadingCommunicate() {
        I18NBundle loadingTexts = I18NBundle.createBundle(Gdx.files.internal("strings/loading-texts"), Locale.getDefault());
        return loadingTexts.get(Integer.toString(Math.abs(EpicDungeonTactics.random.nextInt(10))));
    }

    public void draw(float progress) {
        batch.begin();
        batch.draw(bg, 0, bgY, Gdx.graphics.getWidth(), bgHeight);
        batch.draw(barBg, barX, barY, maxBarWidth, barHeight);
        batch.draw(bar, barX, barY, progress * maxBarWidth, barHeight);

        communicate.draw(batch);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        bg.dispose();
        barBg.dispose();
        bar.dispose();


        Gdx.input.setInputProcessor(new GestureDetector(new MobileInputManager()));
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

}