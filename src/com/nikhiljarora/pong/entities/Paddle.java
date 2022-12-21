package com.nikhiljarora.pong.entities;

import com.nikhiljarora.pong.graphics.Sprite;

public class Paddle {
    private final int gamePixelWidth, gamePixelHeight;

    private final Sprite sprite;
    private int xPos;
    private int yPos;
    private final int moveSize;
    private int xDim;
    private final int yDim;

    public Paddle(char side, int gamePixelWidth, int gamePixelHeight, Sprite sprite) {
        this.gamePixelWidth = gamePixelWidth;
        this.gamePixelHeight = gamePixelHeight;
        this.sprite = sprite;

        yDim = sprite.getSprintSize();
        xDim = yDim / 4;
        moveSize = gamePixelHeight / 60;

        int trueXDim = yDim / 4;
        if (side == 'l') {
            xPos = 0;
            xDim = (yDim / 2) + (trueXDim / 2);
        } else if (side == 'r') {
            xPos = gamePixelWidth - sprite.getSprintSize();
            xDim = (yDim / 2) - (trueXDim / 2);
        }

        reset();
    }

    public void draw(int[] pixels) {
        int spriteSize = sprite.getSprintSize();
        for (int i = 0; i < spriteSize; i++) {
            for (int j = 0; j < spriteSize; j++) {
                int pixel = sprite.pixels[i * spriteSize + j];
                if (pixel != 0) {
                    pixels[(yPos + i) * gamePixelWidth + (xPos + j)] = pixel;
                }
            }
        }
    }

    public void moveUp() {
        if (yPos > 0) yPos -= moveSize;
        if (yPos < 0) yPos = 0;
    }

    public void moveDown() {
        if (yPos < gamePixelHeight - yDim) yPos += moveSize;
        if (yPos + yDim > gamePixelHeight) yPos = gamePixelHeight - yDim;
    }

    public void reset() {
        yPos = (gamePixelHeight / 2) - (yDim / 2);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxDim() {
        return xDim;
    }

    public int getyDim() {
        return yDim;
    }
}
