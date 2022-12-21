package com.nikhiljarora.pong.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    public int sheetSize;
    public int[] pixels;
    private final String path;

    public SpriteSheet(String path) {
        this.path = path;
        loadSheet();
    }

    private void loadSheet() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();
            if (w != h) throw new AssertionError("Sprite sheet must be square!");
            this.sheetSize = w;
            pixels = new int[sheetSize * sheetSize];
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
