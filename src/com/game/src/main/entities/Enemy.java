package com.game.src.main.entities;
import java.util.Random;
import com.game.src.main.Game;
import com.game.src.main.GameActor;
import com.game.src.main.Physics;
import com.game.src.main.controllers.CalcController;
import com.game.src.main.controllers.RenderController;
import com.game.src.main.interfaces.IEntityHarmful;
import com.game.src.main.views.EnemyView;

public class Enemy extends GameActor implements IEntityHarmful
{
	static private int _levelEnemyCountAllowed = 5;
	static private int _enemyKilled = 0;
	private double velY;

	private Random _rand = new Random();

	public Enemy(double x, double y) {
		super(x,y);
		velY = randRange(3, 6);
		RenderController.addActorView(new EnemyView(this));
	}
	
	public int randRange(int min, int max) {
		return min + ( _rand.nextInt((max + 1) - min) );
	}
	
	public void tick() {
		set_y(get_y() + velY);
		
		if(Physics.Collision(this, CalcController.sActorList) ) {
			System.out.println("COLLISION DETECTED!!!");
		}
		if(get_y() > Game.HEIGHT* Game.SCALE) {
			set_x((_rand.nextInt(Game.WIDTH * Game.SCALE)));
			set_y(0);
			velY = randRange(3, 6);
		}
	}
	
	public static int getEnemyCountAllowed() {
		return _levelEnemyCountAllowed;
	}

	@Override
	public void CollidedWithObject() {
		setIsToBeRemoved(true);
	}

	public void destructor() {
		_rand = null;
		_enemyKilled = _enemyKilled < _levelEnemyCountAllowed ? _enemyKilled + 1: 0;
	}
}
