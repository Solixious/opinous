package com.opinous.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class IconEditor {

	public static void main(String[] args) throws Exception {
		String[] filePaths = {
				"C:\\Users\\Pratyush\\Downloads\\recent.jpg",
				"C:\\Users\\Pratyush\\Downloads\\message.jpg",
				"C:\\Users\\Pratyush\\Downloads\\notification.jpg",
				"C:\\Users\\Pratyush\\Downloads\\popular.jpg",
				"C:\\Users\\Pratyush\\Downloads\\search.jpg",
				"C:\\Users\\Pratyush\\Downloads\\recent white.jpg",
				"C:\\Users\\Pratyush\\Downloads\\message white.jpg",
				"C:\\Users\\Pratyush\\Downloads\\notification white.jpg",
				"C:\\Users\\Pratyush\\Downloads\\popular white.jpg",
				"C:\\Users\\Pratyush\\Downloads\\search white.jpg"
		};
		for(String filePath:filePaths) {
			System.out.println(filePath);
			File file = new File(filePath);
			BufferedImage bi = ImageIO.read(file);
			Color c = new Color(204, 0, 0);
			Color a = new Color(96, 96, 96, 0);
			for(int x = 0; x < bi.getWidth(); x++) {
				for(int y = 0; y < bi.getHeight(); y++) {
					Color b = new Color(bi.getRGB(x, y), true);
					if(b.getAlpha() > 200 && !c.equals(Color.white)) {
						//bi.setRGB(x, y, c.getRGB());
					} else {
						bi.setRGB(x, y, a.getRGB());
					}
				}
			}
			ImageIO.write(bi,  "png", file);
		}
	}
}
