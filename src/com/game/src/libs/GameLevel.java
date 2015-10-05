package com.game.src.libs;

public enum GameLevel 
{
	one(2),
	two(3),
	three(5),
	four(5),
	five(1);
	
	private final int _num;
	
	GameLevel(int number)
	{
		_num = number;
	}
	
	public int getAmtOfLevels()
	{
		return _num;
	}
}
