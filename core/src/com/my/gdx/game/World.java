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
	private Bullet bul;
	private int time,dir,life,pikeface,charge,score;
	public float posx=350,posy=350;
	ArrayList<Puckman> enemyarray = new ArrayList<Puckman>();
	ArrayList<Bullet> bulletarray = new ArrayList<Bullet>();
	int puckmanx[] = {-66,317,733,733,733,350,-66,-66};
	int puckmany[] = {-66,-66,-66,350,733,733,733,317};
	boolean gameover = false,hold = true;
	Random dice = new Random();
	
	World(PikemanGame pikemanGame){
		this.pikemanGame =  pikemanGame;
		pikeman = new Pikeman(350,350);
		time = 0;
		life = 200;
		pikeface = 2;
		charge = 0;
		score = 0;
	}
	
	public void update(float delta) {
		if(!gameover) {
			Vector2 pos = pikeman.getPosition();
			time ++;
			charge++;
			if(charge>=120)
				hold = true;
			if(time%10==0)
				score+=1;
			if(time%70==1)
			{
				dir = dice.nextInt(8);
				puckman = new Puckman(puckmanx[dir],puckmany[dir]);
				enemyarray.add(puckman);
			}
			
			for(int i = 0;i<enemyarray.size();i++)
			{
				Puckman puckman = enemyarray.get(i);
				puckman.chase(pos.x, pos.y, enemyarray);
			}
			for(int i = 0;i<bulletarray.size();i++)
			{
				Bullet bul = bulletarray.get(i);
				bul.move();
				if(bul.getOut())
					bulletarray.remove(bul);
			}
			for(int i = 0;i<enemyarray.size();i++)
			{
				Puckman puckman = enemyarray.get(i);
				for(int j = 0;j<bulletarray.size();j++)
				{
					Bullet bul = bulletarray.get(j);
					if(puckman.ishitbyabullet(bul))
					{
						bulletarray.remove(bul);
						enemyarray.remove(puckman);
						score+=20;
					}
				}
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT)&&pos.x>0) {
				pikeman.move(Pikeman.DIRECTION_LEFT);
				pikeface = 1;
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT)&&pos.x<670) {
				pikeman.move(Pikeman.DIRECTION_RIGHT);
				pikeface = 2;
			}
			if(Gdx.input.isKeyPressed(Keys.UP)&&pos.y<670) {
				pikeman.move(Pikeman.DIRECTION_UP);
				pikeface = 3;
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)&&pos.y>0) {
				pikeman.move(Pikeman.DIRECTION_DOWN);
				pikeface = 4;
			}
			if(Gdx.input.isKeyPressed(Keys.Z)&&hold)
			{
				hold = false;
				charge = 0;
				bul = new Bullet(pos.x,pos.y);
				bul.face(pikeface);
				bulletarray.add(bul);
			}
			if(pikeman.hit(enemyarray))
			{
				life--;
				if(life<=0)
					gameover = true;
			}
		}
		else
		{
			if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				gameover = false;
				life = 200;
				time = 0;
				score = 0;
				pikeman = new Pikeman(350,350);
				enemyarray.clear();
				bulletarray.clear();
			}
		}
	}
	public boolean isGameover()
	{
		return gameover;
	}
	public Vector2 getPike()
	{
		return pikeman.getPosition();
	}
	public int getLife()
	{
		return life;
	}
	public int getScore()
	{
		return score;
	}
	public ArrayList<Bullet> getBullet()
	{
		return bulletarray;
	}
	public ArrayList<Puckman> getPuck() 
	{
		return enemyarray;
	}
	public boolean havebullet()
	{
		return !hold;
	}
}
