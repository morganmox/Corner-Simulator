package com.my.gdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	private World world;
	private Texture pikemanImg;
	private Texture puckmanImg;
	private float posx,posy;
	private BitmapFont font;
	private ArrayList<Puckman> enemyarray;
	private int finalscore = 0;
	
	public WorldRenderer(PikemanGame pikemanGame,World world)
	{
		this.world = world;
		
		pikemanImg = new Texture("pikeman.png");
		puckmanImg = new Texture("puckman.png");
		enemyarray = world.getPuck();
		font = new BitmapFont();

	}
	
	public void render(float delta, SpriteBatch batch) {
		if(!world.isGameover()) {
			batch.begin();
			posx = world.getPikex();
			posy = world.getPikey();
			batch.draw(pikemanImg,posx,posy);
			for(Object t : enemyarray)
			{
				batch.draw(puckmanImg,((Puckman) t).getX(),((Puckman) t).getY());
			}
			font.draw(batch, "Life : "+world.getLife(), 600,60);
			font.draw(batch, "Time : "+world.getTime(), 600,40);
			batch.end();
			finalscore = world.getTime();
		}
		else
		{
			batch.begin();
			font.draw(batch, "GAME OVER", 300,370);
			font.draw(batch,"Final Score : "+finalscore,290,350);
			batch.end();
		}
	}
}
