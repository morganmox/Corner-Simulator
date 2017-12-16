package com.my.gdx.game;

import com.badlogic.gdx.math.Vector2;

public class Puckman {
	private Vector2 position;
	
	public Puckman(int x,int y)
	{
		position = new Vector2(x,y);
	}
	public Vector2 getPosition()
	{
		return position;//floatx,floaty
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
	public void chase(float x,float y)
	{
		if(x>=position.x)
		{
			position.x+=2;
		}
		else
		{
			position.x-=2;
		}
		if(y>position.y)
		{
			position.y+=2;
		}
		else
		{
			position.y-=2;
		}
	}
}
