package com.game.src.main.controllers;
import java.util.LinkedList;
import java.util.Random;
import com.game.src.main.Game;
import com.game.src.main.GameObject;
import com.game.src.main.entities.Enemy;

public class CalcController {
	static public volatile LinkedList<GameObject> sActorList = new LinkedList<>();

	private Random _rand;

	public CalcController() {
		_rand = new Random();
	}

	public void createEnemies() {
		int avgSize_ReplaceThis = 32;
		for(int i = 0; i < Enemy.getEnemyCountAllowed(); i++) {
			new Enemy(randRange(avgSize_ReplaceThis, Game.WIDTH * Game.SCALE) - avgSize_ReplaceThis, 0);
		}
	}
	
	public int randRange(int min, int max) {
		return min + ( _rand.nextInt((max + 1) - min) );
	}

	public void tick() {
		for (int i = sActorList.size() - 1; i >= 0; i--) {
			GameObject actor = sActorList.get(i);
			actor.tick();
			if (actor.getIsToBeRemoved()) {
				removeEntity(actor);
			}
		}
	}

	public static void addEntity(GameObject block) {
		sActorList.add(block);
	}

	public void removeEntity(GameObject block) {
		block.destructor();
		synchronized (Enemy.class) {
			sActorList.remove(block);
		}
	}

}
