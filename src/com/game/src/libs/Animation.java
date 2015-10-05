package com.game.src.libs;
import com.game.src.main.Assertion;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

	private int speed;
	private int index = 0;
	private int curFrame = 0;
	private int currentClip = 0;

	private BufferedImage currentImg;
	private List<List<BufferedImage>> spriteClips;

	public Animation(int _speed, List<List<BufferedImage>> _spriteClips) {
		spriteClips = _spriteClips;
		speed = _speed;
	}

	public void animate() {
		index++;
		if(index > speed) {
			index = 0;
			next_Frame();
		}
		Assertion._assert(spriteClips.size() > 0, "Exception: you most likely messed up with animation destructor");
		currentImg = spriteClips.get(currentClip).get(curFrame);
	}

	public void next_Frame() {
		curFrame++;
		Assertion._assert(spriteClips.size() > 0, "Exception: you most likely messed up with animation destructor");
		if(curFrame >= spriteClips.get(currentClip).size() ) {
			curFrame = 0;
		}
	}

	public void drawAnimation(Graphics graphic, double x, double y, int offset) {
		animate();
		graphic.drawImage(currentImg, (int)x + offset, (int)y, null);
	}
	
	public void destructor() {
		for (int i = spriteClips.size() - 1; i >= 0; i--) {
			for (int j = spriteClips.get(i).size() - 1; j >= 0; j--) {
				spriteClips.get(i).remove(j);
			}
			spriteClips.remove(i);
		}
		currentImg = null;
	}
}
