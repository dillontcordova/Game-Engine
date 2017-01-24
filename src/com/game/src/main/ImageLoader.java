package com.game.src.main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

	public BufferedImage loadImage(String path) throws IOException {
		BufferedImage image = ImageIO.read(new File("D:/Repositories/Java-Game-Engine/res/" + path));
		Assertion._assert(image != null, "!Exception: the image at location \"/res" + path + "\" could not be found.");
		return image;
	}
}