package com.my.gdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class World {

	public PikemanGame pikemanGame;
	private Pikeman pikeman;
	private Puckman puckman;
	private int time,dir,life;
	public float posx=350,posy=350;
	ArrayList<Puckman> enemyarray = new ArrayList<Puckman>();
	int puckmanx[] = {-66,317,733,733,733,350,-66,-66};
	int puckmany[] = {-66,-66,-66,350,733,733,733,317};
	boolean gameover = false;
	Random dice = new Random();
	
	World(PikemanGame pikemanGame){
		this.pikemanGame =  pikemanGame;
		pikeman = new Pikeman(350,350);
		time = 0;
		life = 200;
	}
	
	public void update(float delta) {
		if(!gameover) {
			Vector2 pos = pikeman.getPosition();
			posx = pos.x;
			posy = pos.y;
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
				if(life<=0)
					gameover = true;
			}
		}
	}
	public boolean isGameover()
	{
		return gameover;
	}
	public int getLife()
	{
		return life;
	}
	public int getTime()
	{
		return time;
	}
	public ArrayList<Puckman> getPuck() {
		return enemyarray;
	}
	public float getPikex()
	{
		return posx;
	}
	public float getPikey()
	{
		return posy;
	}
}
