package com.game.src.main.Sprites;
import com.game.src.main.Assertion;
import java.util.Map;

public class Texture { //Singleton
	static private Texture instance;
	private Map<String, ssSpriteSheet> spriteSheets;

	private Texture() {}
	static public Texture getInstance() {
		if(instance == null) {
			instance = new Texture();
		}
		return instance;
	}

	public void setSpriteSheets(Map<String, ssSpriteSheet> spriteSheets) {
		this.spriteSheets = spriteSheets;
	}

	public ssSpriteSheet getSpriteSheet(Class<?> aClass) {
		ssSpriteSheet sheet = this.spriteSheets.get(aClass.getSimpleName());
		Assertion._assert(sheet != null, "!Exception: your Class name does not match any of the spriteSheet names in res. Please rename your spriteSheet or Class");
		return sheet;
	}
}
