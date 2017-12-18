package com.my.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PikemanGame extends Game {
    SpriteBatch batch;
    private Music music;

    @Override
    public void create () {
        batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("sound.mp3"));
        music.setLooping(true);
        music.setVolume(0.7f);
        music.play();
        setScreen(new GameScreen(this));
    }
 
    @Override
    public void render () {
        super.render();
    }
 
    @Override
    public void dispose () {
        batch.dispose();
    }
}
