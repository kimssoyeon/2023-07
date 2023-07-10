package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveFile {
	int width = 300;
	int height = 300;
	
	BufferedImage buffImge = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	Graphics2D graphic = buffImge.createGraphics();
	
	File file = new File("파일저장.png");
	
}
