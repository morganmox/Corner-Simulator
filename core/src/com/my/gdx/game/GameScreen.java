package com.my.gdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	
	private PikemanGame pikemangame;
	private Texture pikemanImg;
	private Texture puckmanImg;
	private BitmapFont font;
	private Pikeman pikeman;
	private Puckman puckman;
	private int time = 0,dir,life = 200;
	ArrayList<Puckman> enemyarray = new ArrayList<Puckman>();
	int puckmanx[] = {-66,317,733,733,733,350,-66,-66};
	int puckmany[] = {-66,-66,-66,350,733,733,733,317};
	Random dice = new Random();

	public GameScreen(PikemanGame pikemanGame) {
		this.pikemangame = pikemanGame;
		font = new BitmapFont();
		pikemanImg = new Texture("pikeman.png");
		puckmanImg = new Texture("puckman.png");
		pikeman = new Pikeman(350,350);
	}

	private void update(float delta) {
		Vector2 pos = pikeman.getPosition();
		time ++;
		if(time%100==1)
		{
			dir = dice.nextInt(8);
			puckman = new Puckman(puckmanx[dir],puckmany[dir]);
			enemyarray.add(puckman);
		}
		
		for(Object t : enemyarray)
			((Puckman) t).chase(pos.x ,pos.y,enemyarray);
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)&&pos.x>0) {
			pikeman.move(Pikeman.DIRECTION_LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)&&pos.x<670) {
			pikeman.move(Pikeman.DIRECTION_RIGHT);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)&&pos.y<670) {
			pikeman.move(Pikeman.DIRECTION_UP);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)&&pos.y>0) {
			pikeman.move(Pikeman.DIRECTION_DOWN);
		}
		if(pikeman.hit(enemyarray))
		{
			life--;
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		SpriteBatch batch = pikemangame.batch;
		batch.begin();
		Vector2 pos = pikeman.getPosition();
		batch.draw(pikemanImg,pos.x,pos.y);
		for(Object t : enemyarray)
		{
			batch.draw(puckmanImg,((Puckman) t).getX(),((Puckman) t).getY());
		}
		font.draw(batch,"Life : "+life,600,60);
		font.draw(batch,"Time : "+time,600,40);
		batch.end();
	}

}