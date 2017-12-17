package com.my.gdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	private World world;
	private Texture pikemanImg;
	private Texture puckmanImg;
	private Texture bulletImg;
	private BitmapFont font;
	private ArrayList<Puckman> enemyarray;
	private ArrayList<Bullet> bulletarray;
	private int finalscore = 0;
	
	public WorldRenderer(PikemanGame pikemanGame,World world)
	{
		this.world = world;
		
		pikemanImg = new Texture("pikeman.png");
		puckmanImg = new Texture("puckman.png");
		bulletImg = new Texture("dot.png");
		enemyarray = world.getPuck();
		bulletarray = world.getBullet();
		font = new BitmapFont();

	}
	
	public void render(float delta, SpriteBatch batch) {
		if(!world.isGameover()) {
			batch.begin();
			batch.draw(pikemanImg,world.getPike().x,world.getPike().y);
			for(Object t : enemyarray)
			{
				batch.draw(puckmanImg,((Puckman) t).getX(),((Puckman) t).getY());
			}
			for(Object t : bulletarray)
			{
				batch.draw(bulletImg,((Bullet) t).getX(),((Bullet) t).getY());
			}
			font.draw(batch, "Life : "+world.getLife(), 580,60);
			font.draw(batch, "Score : "+world.getScore(), 580,40);
			font.draw(batch, "Snowball (Z) : "+!world.havebullet(), 580,20);
			batch.end();
			finalscore = world.getScore();
		}
		else
		{
			batch.begin();
			font.draw(batch, "GAME OVER", 300,370);
			font.draw(batch,"Final Score : "+finalscore,290,350);
			font.draw(batch, "Press Space to play again.", 260,330);
			batch.end();
		}
	}
}
