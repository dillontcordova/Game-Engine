package com.game.src.libs;
import java.util.ArrayList;
import java.util.List;

public class Physicss {
	private double _speed;
	private double _angle;
	private double _topSpeed;
	private double _velocityX;
	private double _velocityY;
	private double _acceleration;
	private double _opposingForce;
	private double _scaleX;
	private double _scaleY;
	private double xPos = 320;
	private double yPos = 240;

	public Physicss() {
		init(270, 0, .5, 10);
	}

	public Physicss(double angle, double speed, double acceleration, double topSpeed) {
		init(angle, speed, acceleration, topSpeed);
	}

	private void init(double angle, double speed, double acceleration, double topSpeed) {
		_opposingForce = 0;
		_speed = speed;
		_acceleration = acceleration;
		_angle = angle;
		_topSpeed = topSpeed;
		resetTrajectory(_angle);
	}

	private double adjustSpeed() {
		if(_speed < _topSpeed) {
			_speed += _acceleration/* + _opposingForce*/;
			if (_speed > _topSpeed) {
				_speed = _topSpeed;
			}
		}
		_velocityX = _speed * _scaleX;
		_velocityY = _speed * _scaleY;
		return _speed;
	}

	public void resetTrajectory(double angle) {
		double radiansOfAngle = convertDegreesToRadians(angle);
		_scaleX = Math.cos(radiansOfAngle);
		_scaleY = Math.sin(radiansOfAngle);
	}

	public List<Integer> plotNewPosition() {
		List<Integer> position = new ArrayList<>();
		adjustSpeed();
		xPos = (int)Math.round(xPos + _velocityX);
		yPos = (int)Math.round(yPos + _velocityY);
		System.out.println( xPos );
		System.out.println( yPos );
//		position.add( (int)Math.round(xPos + _velocityX) );
//		position.add( (int)Math.round(yPos + _velocityY) );
		return position;
	}

	public void set_opposingForce(double _opposingForce) {
		this._opposingForce = _opposingForce;
	}

	private double convertDegreesToRadians(double degrees){
		//(pi * degrees / 180) //degrees to radians
		return (Math.PI * degrees / 180);
	}

	private double convertFeetToMeters(double feet){
		// (feet / 3.2808) //feet to meters
		return (feet / 3.2808);
	}
}
