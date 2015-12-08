package com.game.src.main;
import com.game.src.libs.Animation;
import com.game.src.libs.Physicss;
import com.game.src.main.Sprites.Texture;
import com.game.src.main.Sprites.ssSpriteSheet;
import com.game.src.main.controllers.CalcController;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class GameActor {
	private double _x;
	private double _y;
	private double _velX;
	private double _velY;
	private double _topSpeed;
	protected Physicss _physics;
	protected boolean _isToBeRemoved = false;
	protected ssSpriteSheet spriteSheet;

	public GameActor(double x, double y) {
		set_x(x);
		set_y(y);
		CalcController.addEntity(this);
		_physics = new Physicss();
	}

	public abstract void tick();
	public abstract void destructor();
	public abstract void CollidedWithObject();
	protected void handleKeyPress() {}

	public Rectangle getBounds(/*int width, int height*/) {
		return new Rectangle((int) get_x(), (int) get_y(), 32, 32);
	}

	public void setSpriteSheet() {
		Texture tex = Texture.getInstance();
		spriteSheet = tex.getSpriteSheet(this.getClass());
	}

	public Animation createAnimation(List<List<BufferedImage>> clips) {
		return new Animation(5, clips);//TODO: add animation speed input here
	}

	protected void handleKeyInput() {
		if(CalcController.keyPressed) {
			handleKeyPress();
		}
	}

	public boolean getIsToBeRemoved(){
		return _isToBeRemoved;
	}

	public void setIsToBeRemoved(boolean isRemoving){
		_isToBeRemoved = isRemoving;
	}

	public double getX(){
		return get_x();
	}

	public double getY(){
		return get_y();
	}

	public ssSpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public double get_x() {
		return _x;
	}

	public void set_x(double _x) {
		this._x = _x;
	}

	public double get_y() {
		return _y;
	}

	public void set_y(double _y) {
		this._y = _y;
	}

	public double get_velX() {
		return _velX;
	}

	public void setVelX(double velocity) {
		if( !(Math.abs(velocity) > _topSpeed) ) {
			this._velX = velocity;
		}
	}

	public void setVelY(double velocity) {
		if( !(Math.abs(velocity) > _topSpeed) ) {
			this._velY = velocity;
		}
	}

	public double get_velY() {
		return _velY;
	}

	public void set_topSpeed(double _topSpeed) {
		this._topSpeed = _topSpeed;
	}

	protected enum AnimationClip {
		Idle(0),
		Walking(1),
		Running(2),
		Jumping(3);

		private final int clip;

		AnimationClip(int _clip) {
			this.clip = _clip;
		}
		final public int getValue() {
			return this.clip;
		}
	}
}
