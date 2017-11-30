package com.my.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	 
    private PacmanGame pacmanGame;
    private Texture pacmanImg;
    private Texture puckmanImg;
    private int x=100,y=100,xe=600,ye=600,speed = 2;
 
    public GameScreen(PacmanGame pacmanGame) {
        this.pacmanGame = pacmanGame;
        pacmanImg = new Texture("notpacman.png");
        puckmanImg = new Texture("notpacmanenemy.png");
    }
    
    private void update(float delta) {
    	if(xe>x)
    	{
    		xe-=speed;
    	}
    	else
    	{
    		xe+=speed;
    	}
    	if(ye>y)
    	{
    		ye-=speed;
    	}
    	else
    	{
    		ye+=speed;
    	}
    	
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            x -= (speed+1);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            x += (speed+1);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            y += (speed+1);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            y -= (speed+1);
        }
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	update(delta);
        SpriteBatch batch = pacmanGame.batch;
        batch.begin();
        batch.draw(pacmanImg, x, y);
        batch.draw(puckmanImg,xe,ye);
        batch.end();
    }
    
}