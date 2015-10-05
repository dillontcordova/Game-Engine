package com.game.src.main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

	public BufferedImage loadImage(String path) throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResource(path));
		Assertion._assert(image != null, "!Exception: the image at location \"/res" + path + "\" could not be found.");
		return image;
	}
}
