package com.game.src.main.entities;
import java.awt.Rectangle;
import com.game.src.main.GameActor;
import com.game.src.main.Physics;
import com.game.src.main.controllers.CalcController;
import com.game.src.main.controllers.RenderController;
import com.game.src.main.interfaces.IEntityFriendly;
import com.game.src.main.views.BulletView;

public class Bullet extends GameActor implements IEntityFriendly {

	static private final int MAX_BULLETS = 2;
	private boolean isToBeRemoved = false;
	static private int _bulletCount = 0;
	private int velY = 5;

	public Bullet(double x, double y) {
		super(x,y);
		_bulletCount++;
		RenderController.addActorView(new BulletView(this));
	}
	
	public void tick() {
		set_y(get_y() - velY);
		
		if(Physics.Collision(this, CalcController.sActorList) ) {
			System.out.println("COLLISION DETECTED!!!");
		}
		if(get_y() < 0 && _bulletCount > 0) {
			isToBeRemoved = true;
		}
	}
	
	public double getY() {
		return get_y();
	}
	
	public double getX() {
		return get_x();
	}

	public boolean getIsToBeRemoved() {
		return isToBeRemoved;
	}
	public void setIsToBeRemoved(boolean isRemoving) {
		isToBeRemoved = isRemoving;
	}

	static public boolean isMaxBulletsReached() {
		return _bulletCount >= MAX_BULLETS;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) get_x(), (int) get_y(), 32, 32);
	}

	public void destructor() {
		_bulletCount = _bulletCount > 0 ? _bulletCount - 1: 0;
	}

	public void CollidedWithObject() 
	{
		isToBeRemoved = true;
	}
}
