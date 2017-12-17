package com.my.gdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 position;
	private int dir = 2,screenwidth=700;
	private boolean outofbound = false;
	public Vector2 getPosition()
	{
		return position;//floatx,floaty
	}
	public Bullet(float x,float y)
	{
		position = new Vector2(x,y);
	}
	public void face(int facing)
	{
		dir = facing;
	}
	public float getX()
	{
		float x = position.x;
		return x;
	}
	public float getY()
	{
		float y = position.y;
		return y;
	}
	public boolean getOut()
	{
		return outofbound;
	}
	public void move()
	{
		if(dir==1)
			position.x-=4;
		else if(dir==2)
			position.x+=4;
		else if(dir==3)
			position.y+=4;
		else
			position.y-=4;
		if(position.x>screenwidth||position.x<0||position.y>screenwidth||position.y<0)
			outofbound = true;
	}
}
