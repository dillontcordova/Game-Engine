package com.game.src.main.Sprites;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteSheetGenerator {

	static public void spriteSheetCreator(Map<String, Map<String, Object>> sprites) {
		Map<String, ssSpriteSheet> allSpriteSheets = new HashMap<>();
		for(String spriteSheetName : sprites.keySet()) {
			try {
				ssSpriteSheet curSpriteSheet = pretendFutureConstructor(sprites.get(spriteSheetName), spriteSheetName);
				allSpriteSheets.put(spriteSheetName, curSpriteSheet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Texture texture = Texture.getInstance();
		texture.setSpriteSheets(allSpriteSheets);
	}

	static public ssSpriteSheet pretendFutureConstructor(Map<String, Object> spriteSheetData, String key) throws IOException {
		SheetValue[] sheetValues = SheetValue.values();

		ssSpriteSheet spriteSheet = new ssSpriteSheet(key);

		for(SheetValue sheetValue : sheetValues) {
			String value = sheetValue.getSheetValue();
			switch(sheetValue) {
				case SheetPath:
					spriteSheet.setSheetPath((String) spriteSheetData.get(value));
					break;
				case SheetHeight:
					spriteSheet.setSheetHeight((String) spriteSheetData.get(value));
					break;
				case SheetWidth:
					spriteSheet.setSheetWidth((String) spriteSheetData.get(value));
					break;
				case SpriteSize:
					spriteSheet.setSpriteSize((String) spriteSheetData.get(value));
					break;
			}
		}
		return spriteSheet;
	}

	public enum SheetValue {
		SheetKey("sheetKey"),
		SheetPath("sheetPath"),
		SpriteSize("spriteSize"),
		SheetWidth("sheetWidth"),
		SheetHeight("sheetHeight");

		private final String sheetValue;

		SheetValue(String sheetValue) {
			this.sheetValue = sheetValue;
		}
		final public String getSheetValue() {
			return this.sheetValue;
		}
	}
}
