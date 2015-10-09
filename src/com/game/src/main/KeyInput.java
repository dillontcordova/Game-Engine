package com.game.src.main;

import com.game.src.main.controllers.CalcController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	Game game;
	
	public KeyInput(Game game){
		this.game = game;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
//		game.keyPressed(e);
		CalcController.currentKey = e.getKeyCode();
		CalcController.keyPressed = true;
//		CalcController.keyReleased = false;
	}
	
	public void keyReleased(KeyEvent e) {
//		game.keyReleased(e);
//		CalcController.currentKey = e.getKeyCode();
		CalcController.keyPressed = false;
//		CalcController.keyReleased = true;
	}
}
