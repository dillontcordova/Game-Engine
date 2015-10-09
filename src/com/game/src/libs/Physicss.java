package com.game.src.libs;

import java.util.ArrayList;
import java.util.List;

public class Physicss {
	private double _scaleX;
	private double _scaleY;
	private double _velocityY;
	private double _velocityX;
	private double _acceleration;

	public Physicss() {
	}

	public Physicss(double angle, double speed) {
		resetTrajectory(angle, speed);
	}

	public void resetTrajectory(double angle, double speed) {
		double radiansOfAngle = convertDegreesToRadians(angle);
		_scaleX = Math.cos(radiansOfAngle);
		_scaleY = Math.sin(radiansOfAngle);
		_velocityX = speed * _scaleX;
		_velocityY = speed * _scaleY;
	}

	public List<Integer> plotNewPosition(int xPos, int yPos) {
		List<Integer> position = new ArrayList<>();
		position.add( (int)Math.round(xPos + _velocityX) );
		position.add( (int)Math.round(yPos + _velocityY) );
		return position;
	}

	public double convertDegreesToRadians(double degrees){
		//(pi * degrees / 180) //degrees to radians
		return (Math.PI * degrees / 180);
	}

	public double convertFeetToMeters(double feet){
		// (feet / 3.2808) //feet to meters
		return (feet / 3.2808);
	}
}
