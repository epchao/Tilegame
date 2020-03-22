package dev.epchao.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	//Loading images using loadImage;
    public static BufferedImage loadImage(String path) {
        try {
        	//important to load images (grabs the path of the image)
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}