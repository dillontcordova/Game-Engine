package com.game.src.main;

public class Main {
	public static void main(String args[])
	{
		Game game = new Game();
		GameWindow window = new GameWindow(game);
		game.start();
	}
}
