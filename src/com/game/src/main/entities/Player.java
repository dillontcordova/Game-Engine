package com.game.src.main.entities;
import java.awt.event.KeyEvent;
import com.game.src.main.Game;
import com.game.src.main.GameActor;
import com.game.src.main.controllers.CalcController;
import com.game.src.main.controllers.RenderController;
import com.game.src.main.interfaces.IEntityFriendly;
import com.game.src.main.views.PlayerView;

public class Player extends GameActor implements IEntityFriendly {

	private int _lives;
	private double _acceleration;
	private boolean _isAlive;
	private boolean isShooting;

	public Player(double x, double y) {
		super(x,y);
		RenderController.addActorView(new PlayerView(this));
		_lives = 2;
		_isAlive = true;
		setMeasurements();
	}

	public void setMeasurements() {
		setVelX(0);
		setVelY(0);
		set_topSpeed(6);
		_acceleration = .1;
	}
//	public double convertFeetToMeters(double feet){
//		// (feet / 3.2808) //feet to meters
//		return (feet / 3.2808);
//	}
//	public double convertDegreesToRadions(double degrees){
//		//(pi * degrees / 180) //degrees to radions
//		return (Math.PI * degrees / 180);
//	}

	public void tick() {
		handleKeyInput();
		//collision();

		//crappy collision with outer box of screen TODO: get rid of this logic, replace with proper collision with camera and all other ranged objects
			int avgSize_ReplaceThis = 32;
			if(get_x() <= 0) {
				setVelX(0);
//				set_x(0);
			}
			if(get_x() + avgSize_ReplaceThis >= Game.WIDTH * Game.SCALE) {
				setVelX(0);
//				set_x((Game.WIDTH * Game.SCALE) - avgSize_ReplaceThis);
			}
			if(get_y() <= 0) {
				setVelY(0);
//				set_y(0);
			}
			if(get_y() + avgSize_ReplaceThis +2 >= Game.HEIGHT * Game.SCALE) {
				setVelY(0);
//				set_y((Game.HEIGHT * Game.SCALE) - avgSize_ReplaceThis +2);
			}
		physics();
	}

	protected void physics() {
		double velX = get_velX() /*- .5*/;
		double velY = get_velY() /*- .5*/;
		set_x(get_x() + velX);
		set_y(get_y() + velY);
	}

	@Override
	protected void handleKeyPress() {
		switch(CalcController.currentKey) {
			case KeyEvent.VK_RIGHT:
				setVelX(get_velX() + _acceleration);
				break;
			case KeyEvent.VK_LEFT:
				setVelX(get_velX() - _acceleration);
				break;
			case KeyEvent.VK_UP:
				setVelY(get_velY() - _acceleration);
				break;
			case KeyEvent.VK_DOWN:
				setVelY(get_velY() + _acceleration);
				break;
			case KeyEvent.VK_SPACE:
				if(!isShooting) {
					isShooting = true;
					if(!Bullet.isMaxBulletsReached() && getIsAlive()) {
						CalcController.addEntity(new Bullet(getX(), getY()) );
					}
				}
				break;
		}
	}

	public boolean getIsAlive() {
		return _isAlive;
	}
	
	public void CollidedWithObject() {
		_lives = _lives > 0 ? _lives - 1: 0;
		_isAlive = _lives > 0;
		_isToBeRemoved = !_isAlive;
	}

	public void destructor() {
	}
}
