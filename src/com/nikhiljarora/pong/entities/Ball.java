package com.nikhiljarora.pong.entities;

import com.nikhiljarora.pong.graphics.Sprite;
import com.nikhiljarora.pong.input.Keyboard;

import java.awt.*;
import java.util.Random;

public class Ball {
    private final int gamePixelWidth, gamePixelHeight;

    private final int tickVel = 4;

    private final double paddleHitSpeedup = 0.1;

    private final Sprite sprite;
    private int xPos;
    private int yPos;
    // for coll detection purposes Im considering the ball to a square lol
    private final int xDim;
    private final int yDim;
    private int xVel;

    private int yVel;

    public Ball(int gamePixelWidth, int gamePixelHeight, Sprite sprite) {
        this.gamePixelWidth = gamePixelWidth;
        this.gamePixelHeight = gamePixelHeight;
        this.sprite = sprite;

        xDim = yDim = sprite.getSprintSize();
        reset();
    }

    public int getyPos() {
        return yPos;
    }

    public int getyDim() {
        return yDim;
    }

    public int getxVel() {
        return xVel;
    }

    public int getxPos() {
        return xPos;
    }

    public int getxDim() {
        return xDim;
    }

    /*
     * Draws the ball based on its current state
     * */
    public void draw(Graphics g) {
        g.fillOval(xPos, yPos, xDim, yDim);
    }

    public void draw(int[] pixels) {
        int spriteSize = sprite.getSprintSize();
        for (int i = 0; i < spriteSize; i++) {
            for (int j = 0; j < spriteSize; j++) {
                int spritePixel = sprite.pixels[i * spriteSize + j];
                int screenPixel = (yPos + i) * gamePixelWidth + (xPos + j);
                if ((spritePixel != 0) && (screenPixel < pixels.length && screenPixel >= 0)) {
                    pixels[screenPixel] = spritePixel;
                }
            }
        }
    }

    /*
     * resets balls pos
     * uses: start of each round
     * */


    public void reset() {
        xPos = (gamePixelWidth / 2) - (xDim / 2);
        yPos = (gamePixelHeight / 2) - (xDim / 2);

        xVel = tickVel * (new Random().nextBoolean() ? -1 : 1);
        yVel = tickVel * (new Random().nextBoolean() ? -1 : 1);

    }

    public void update() {
        // we are going to handle wall collisions based on our knowledge of the top and bottom
        if ((xPos + xDim) > gamePixelWidth || (xPos) < 0) {
            //hit the left or right wall
            xVel *= -1;
        }
        if ((yPos) < 0 || (yPos + yDim) > gamePixelHeight) {
            yVel *= -1;
        }

        xPos = xPos + xVel;
        yPos = yPos + yVel;
    }

    public void collisionUpdate() {
        xVel *= -1;
    }

    public void leftCollisionUpdate(Keyboard keyboard) {
        xVel *= -(1 + paddleHitSpeedup);
        if ((keyboard.lUp && yVel < 0) || (keyboard.lDown && yVel > 0)) {
            yVel *= 2;
        } else if ((keyboard.lUp && yVel > 0) || (keyboard.lDown && yVel < 0)) {
            yVel /= 2;
        }
    }

    public void rightCollisionUpdate(Keyboard keyboard) {
        xVel *= -(1 + paddleHitSpeedup);
        if ((keyboard.rUp && yVel < 0) || (keyboard.rDown && yVel > 0)) {
            yVel *= 2;
        } else if ((keyboard.rUp && yVel > 0) || (keyboard.rDown && yVel < 0)) {
            yVel /= 2;
        }
    }
}
