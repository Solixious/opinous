package com.opinous.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class IconEditor {

	public static void main(String[] args) throws Exception {
		String filePath = "C:\\Users\\Pratyush\\Downloads\\message.png";
		File file = new File(filePath);
		BufferedImage bi = ImageIO.read(file);
		Color c = new Color(96, 96, 96);
		for(int x = 0; x < bi.getWidth(); x++) {
			for(int y = 0; y < bi.getHeight(); y++) {
				Color b = new Color(bi.getRGB(x, y), true);
				if(b.getAlpha() > 20)
					bi.setRGB(x, y, c.getRGB());
			}
		}
		ImageIO.write(bi,  "png", file);
	}
}
