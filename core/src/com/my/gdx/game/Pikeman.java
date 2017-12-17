package com.my.gdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Pikeman {
	private Vector2 position;
	public static final int DIRECTION_UP =3;
	public static final int DIRECTION_RIGHT =2;
	public static final int DIRECTION_DOWN =1;
	public static final int DIRECTION_LEFT =4;
	public static final int DIRECTION_STILL =0;
	int speed = 2,fat=33;
	
	public static final int [][] DIR_OFFSETS = new int [][] {
		{0,0},{0,-1},{1,0},{0,1},{-1,0}
	};
	
	public Pikeman(int x,int y)
	{
		position = new Vector2(x,y);
	}
	public Vector2 getPosition()
	{
		return position;
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
	public void move(int dir)
	{
		position.x += speed*DIR_OFFSETS[dir][0];
		position.y += speed*DIR_OFFSETS[dir][1];
	}
	public boolean hit(ArrayList<Puckman>enemy)
	{
		for(int i = 0;i<enemy.size();i++)
		{
			Object unit = enemy.get(i);
			if(this.getX()<((Puckman) unit).getX()+fat&&this.getX()+fat>((Puckman) unit).getX()&&this.getY()<((Puckman) unit).getY()+fat&&this.getY()+fat>((Puckman) unit).getY())
			{
				return true;
			}
		}
		return false;
	}

}
