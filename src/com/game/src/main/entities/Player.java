package com.game.src.main.entities;
import java.awt.Rectangle;
import com.game.src.main.Game;
import com.game.src.main.GameObject;
import com.game.src.main.controllers.RenderController;
import com.game.src.main.interfaces.IEntityFriendly;
import com.game.src.main.views.PlayerView;

public class Player extends GameObject implements IEntityFriendly {

	private int _lives = 2;
	private double _velX = 0;
	private double _velY = 0;
	private boolean _isAlive = true;

	public Player(double x, double y) {
		super(x,y);
		RenderController.addActorView(new PlayerView(this));
	}
	
	public void tick() {
		set_x(get_x() + _velX);
		set_y(get_y() + _velY);

		//crappy collision with outer box of screen TODO: get rid of this logic, replace with proper collision with camera
			int avgSize_ReplaceThis = 32;
			if(get_x() <= 0) {
				set_x(0);
			}
			if(get_x() + avgSize_ReplaceThis >= Game.WIDTH * Game.SCALE) {
				set_x((Game.WIDTH * Game.SCALE) - avgSize_ReplaceThis);
			}
			if(get_y() <= 0) {
				set_y(0);
			}
			if(get_y() + avgSize_ReplaceThis +2 >= Game.HEIGHT * Game.SCALE) {
				set_y((Game.HEIGHT * Game.SCALE) - avgSize_ReplaceThis +2);
			}
	}

	public void setVelX(double velocity) {
		_velX = velocity;
	}

	public void setVelY(double velocity) {
		_velY = velocity;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) get_x(), (int) get_y(), 32, 32);
	}

	public boolean getIsAlive() {
		return _isAlive;
	}
	
	public void CollidedWithObject() {
		_lives = _lives > 0 ? _lives - 1: 0;
		_isAlive = _lives > 0;
		_isToBeRemoved = !_isAlive;
	}

	public void destructor()  {
	}
}
