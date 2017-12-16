package com.my.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends ScreenAdapter {
	
	private PacmanGame pacmangame;
	private Texture pacmanImg;
	private Texture puckmanImg;
	private BitmapFont font;
	private Pacman pacman;
	private Puckman puckman;
	private int score = 0;
	Array enemyarray = new Array();

	public GameScreen(PacmanGame pacmanGame) {
		this.pacmangame = pacmanGame;
		font = new BitmapFont();
		pacmanImg = new Texture("notpacman.png");//pic
		puckmanImg = new Texture("notpacmanenemy.png");//pic
		pacman = new Pacman(100,100);//state
	}

	private void update(float delta) {
		Vector2 pos = pacman.getPosition();
		score ++;
		if(score%200==1)
		{
			puckman = new Puckman(500,500);//state
			enemyarray.add(puckman);
		}
		
		for(Object t : enemyarray)
		{
			((Puckman) t).chase(pos.x ,pos.y);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)&&pos.x>0) {
			pacman.move(Pacman.DIRECTION_LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)&&pos.x<670) {
			pacman.move(Pacman.DIRECTION_RIGHT);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)&&pos.y<670) {
			pacman.move(Pacman.DIRECTION_UP);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)&&pos.y>0) {
			pacman.move(Pacman.DIRECTION_DOWN);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		SpriteBatch batch = pacmangame.batch;
		batch.begin();
		Vector2 pos = pacman.getPosition();
		batch.draw(pacmanImg,pos.x,pos.y);//(pic,statex,statey)
		for(Object t : enemyarray)
		{
			batch.draw(puckmanImg,((Puckman) t).getX(),((Puckman) t).getY());
		}
		font.draw(batch,"Time : "+score,600,60);
		batch.end();
	}

}