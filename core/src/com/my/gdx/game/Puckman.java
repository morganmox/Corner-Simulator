package com.my.gdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Puckman {
	private Vector2 position;
	int speed = 1,fat = 33;
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
	public void chase(float x,float y,ArrayList<Puckman>team)
	{
		if(x>=position.x)//right
		{
			position.x+=speed;
			if(!canmoveindirection(1,team))
				position.x-=speed;
		}
		if(x<=position.x)//left
		{
			position.x-=speed;
			if(!canmoveindirection(1,team))
				position.x+=speed;
		}
		if(y>=position.y)//up
		{
			position.y+=speed;
			if(!canmoveindirection(1,team))
				position.y-=speed;
		}
		if(y<=position.y)//down
		{
			position.y-=speed;
			if(!canmoveindirection(1,team))
				position.y+=speed;
			
		}
		
	}
	public boolean canmoveindirection(int direction,ArrayList<Puckman>team)
	{
		for(int i = 0;i<team.size();i++)
		{
			Object unit = team.get(i);
			if(((Puckman) unit).getPosition()==this.getPosition())
				continue;
			else
			{
				if(this.getX()<((Puckman) unit).getX()+fat&&this.getX()+fat>((Puckman) unit).getX()&&this.getY()<((Puckman) unit).getY()+fat&&this.getY()+fat>((Puckman) unit).getY())
				{
					return false;
				}
			}
		}
		return true;
	}
}
