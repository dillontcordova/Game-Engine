package com.game.src.main.Sprites;
import com.game.src.main.ImageLoader;
import java.awt.*;
import java.awt.geom.AffineTransform;
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

	private boolean isPixelTransparent( int pixel ) {
		return (pixel>>24) == 0x00;
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

	public List<List<BufferedImage>> drawSpriteClipsFromSheet() {
		int COLUMNS = spriteSheetWidth / spriteSize;
		int ROWS = spriteSheetHeight / spriteSize;
		List<List<BufferedImage>> spriteClips = new ArrayList<>();

		for (int col = 0; col < COLUMNS; col++) {
			List<BufferedImage> imgList = new ArrayList<>();
			for (int row = 0; row < ROWS; row++) {
				BufferedImage curGrabbedImg = grabImage(col, row);
				if( isAnImage(curGrabbedImg) ) {
					boolean isVertical = true;
					curGrabbedImg = flipImage(curGrabbedImg, isVertical);
					imgList.add(curGrabbedImg);
				}
			}
			spriteClips.add(imgList);
		}
		return spriteClips;
	}

	private static BufferedImage flipImage(BufferedImage oldImage, boolean isVertical) {
		AffineTransform at = new AffineTransform();
		double flip = isVertical ? 1D: -1D;
		at.concatenate(AffineTransform.getScaleInstance(1 * flip, -1 * flip));
		at.concatenate(AffineTransform.getTranslateInstance(0, -oldImage.getHeight()));

		BufferedImage newImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D tmpGraphic = newImage.createGraphics();
		tmpGraphic.transform(at);
		tmpGraphic.drawImage(oldImage, 0, 0, null);
		tmpGraphic.dispose();
		return newImage;
	}

	public void setSheetHeight(String sheetHeight) {
		spriteSheetHeight = Integer.valueOf(sheetHeight);
	}

	public void setSheetWidth(String sheetWidth) {
		spriteSheetWidth = Integer.valueOf(sheetWidth);
	}

	public void setSpriteSize(String newSpriteSize) {
		spriteSize = Integer.valueOf(newSpriteSize);
	}

	public void setSheetPath(String spriteSheetPath) {
		imageloader = new ImageLoader();
		try {
			spriteSheet = imageloader.loadImage(spriteSheetPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
