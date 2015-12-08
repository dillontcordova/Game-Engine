package com.game.src.main.views;

import com.game.src.libs.Animation;
import com.game.src.main.GameActor;
import com.game.src.main.Sprites.ssSpriteSheet;
import java.awt.*;

public abstract class GameView {
	protected boolean isToBeRemoved;
	protected Animation animation;
	protected GameActor _model;

	protected GameView(GameActor model) {
		_model = model;
		model.setSpriteSheet();
		createAnimation(_model);
	}

	public synchronized void render(Graphics g) {
		if(!_model.getIsToBeRemoved()) {
			animation.drawAnimation(g, (int) _model.getX(), (int) _model.getY(), 0);
		} else {
			destructor();
		}
	}

	public boolean isToBeRemoved() {
		return isToBeRemoved;
	}

	private synchronized void destructor() {
		animation.destructor();
		animation = null;
		isToBeRemoved = true;
	}

	private void createAnimation(GameActor model) {
		ssSpriteSheet sheet = model.getSpriteSheet();
		animation = _model.createAnimation(sheet.drawSpriteClipsFromSheet());
	}
}
