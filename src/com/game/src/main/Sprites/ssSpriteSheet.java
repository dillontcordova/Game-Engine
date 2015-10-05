package com.game.src.main.Sprites;
import com.game.src.main.ImageLoader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ssSpriteSheet {
	private BufferedImage spriteSheet;
	private ImageLoader imageloader;
	public String spriteSheetName;
	private int spriteSheetHeight;
	private int spriteSheetWidth;
	private int spriteSize;

	public ssSpriteSheet(String name) {
		spriteSheetName = name;
		imageloader = new ImageLoader();
	}

	private BufferedImage grabImage(int col, int row) {
		return spriteSheet.getSubimage(col * spriteSize, row * spriteSize, spriteSize, spriteSize);
	}

	public boolean isAnImage( BufferedImage img ) {
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int curPixel = img.getRGB(i, j);
				if(!isPixelTransparent(curPixel)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isPixelTransparent( int pixel ) {
		return (pixel>>24) == 0x00;
	}

	public List<List<BufferedImage>> drawSpriteClipsFromSheet() {
		int COLUMNS = spriteSheetWidth / spriteSize;
		int ROWS = spriteSheetHeight / spriteSize;
		List<List<BufferedImage>> spriteClips = new ArrayList<>();;

		for (int col = 0; col < COLUMNS; col++) {
			List<BufferedImage> imgList = new ArrayList<>();
			for (int row = 0; row < ROWS; row++) {
				BufferedImage curGrabbedImg = grabImage(col, row);
				if( isAnImage(curGrabbedImg) ){
					imgList.add(curGrabbedImg);
				}
			}
			spriteClips.add(imgList);
		}
		return spriteClips;
	}

	public void setImageHeight(String sheetHeight) {
		spriteSheetHeight = Integer.valueOf(sheetHeight);
	}

	public void setImageWidth(String sheetPath) {
		spriteSheetWidth = Integer.valueOf(sheetPath);
	}

	public void setSpriteSize(String sheetPath) {
		spriteSize = Integer.valueOf(sheetPath);
	}

	public void setImagePath(String spriteSheetPath) {
		imageloader = new ImageLoader();
		try {
			spriteSheet = imageloader.loadImage(spriteSheetPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
