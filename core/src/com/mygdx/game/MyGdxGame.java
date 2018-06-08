package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Music mp3;
    private Music ogg;
    private Music wav;

    private Stage stage;

    @Override
    public void create() {

        Skin skin = new Skin(Gdx.files.internal("star-soldier/star-soldier-ui.json"));

        stage = new Stage();
        batch = new SpriteBatch();
        mp3 = Gdx.audio.newMusic(Gdx.files.internal("DST-DustLoop.mp3"));
        ogg = Gdx.audio.newMusic(Gdx.files.internal("menu_screen_loop.ogg"));
        wav = Gdx.audio.newMusic(Gdx.files.internal("music_background.wav"));
        TextButton playMp3 = new TextButton("Play mp3", skin);
        playMp3.setPosition(0, 100);
        playMp3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stopAllMusic();
                mp3.play();
            }
        });
        TextButton playOgg = new TextButton("Play ogg", skin);
        playOgg.setPosition(0, 50);
        playOgg.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stopAllMusic();
                ogg.play();
            }
        });
        TextButton playWav = new TextButton("Play wav", skin);
        playWav.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stopAllMusic();
                wav.play();
            }
        });

        stage.addActor(playMp3);
        stage.addActor(playOgg);
        stage.addActor(playWav);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        mp3.dispose();
        ogg.dispose();
        wav.dispose();
    }

    private void stopAllMusic() {

        mp3.stop();
        ogg.stop();
        wav.stop();
    }
}
