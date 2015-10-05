package com.game.src.main.Sprites;
import com.game.src.main.Assertion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadSpriteFiles {

	private String filePath;
	private String[] readOrder;

	//make into enums
	private String nextSheet = ".";
	private String eof = "!";

	public ReadSpriteFiles(String file_path) {
		//make into enums
		readOrder = new String[5];
		readOrder[0] = "sheetKey";
		readOrder[1] = "sheetPath";
		readOrder[2] = "spriteSize";
		readOrder[3] = "sheetWidth";
		readOrder[4] = "sheetHeight";

		filePath = file_path;
	}

	public Map<String, Map<String, Object>> openFile() throws IOException {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader textReader = new BufferedReader(fileReader);

		String aLine;
		int sheetLineNum = 0;
		Map<String, Object> spriteSheet = null;
		Map<String, Map<String, Object>> spriteSheetMap = new HashMap<>();

		while( !(aLine = textReader.readLine()).equals(eof) ){
			if(aLine.equalsIgnoreCase(nextSheet)) {
				sheetLineNum = 0;
				continue;
			}
			switch (readOrder[sheetLineNum]) {
				case "sheetKey"://make enum
					spriteSheet = new HashMap<>();
					spriteSheetMap.put(aLine, spriteSheet);
					break;
				default:
					Assertion._assert(spriteSheet != null, "!Exception: your spriteSheet text file is out of order. ");
					assert spriteSheet != null;
					spriteSheet.put(readOrder[sheetLineNum], aLine);
					break;
			}
			sheetLineNum++;
		}
		textReader.close();

		return spriteSheetMap;
	}
}
