package com.nikhiljarora.pong;

import com.nikhiljarora.pong.entities.Ball;
import com.nikhiljarora.pong.entities.CollisionManager;
import com.nikhiljarora.pong.entities.CollisionType;
import com.nikhiljarora.pong.entities.Paddle;
import com.nikhiljarora.pong.graphics.Sprite;
import com.nikhiljarora.pong.graphics.SpriteSheet;
import com.nikhiljarora.pong.input.Keyboard;

import java.awt.*;

public class PongManager {
    private final int pixelWidth;
    private final int pixelHeight;
    private final Keyboard keyboard;
    private final Paddle lPaddle;
    private final Paddle rPaddle;
    private final Ball ball;
    private final Sprite ballSprite;
    private final CollisionManager collisionManager;
    private final ScoreManager scoreManager;

    public PongManager(int pixelWidth, int pixelHeight, Keyboard keyboard) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.keyboard = keyboard;

        String spriteSheetPath = "/home/nja/dev/gh/pong/res/spritesheets/pong.png";
        SpriteSheet pongSpriteSheet = new SpriteSheet(spriteSheetPath);

        ballSprite = new Sprite(16, 0, 0, pongSpriteSheet);
        this.ball = new Ball(pixelWidth, pixelHeight, ballSprite);

        Sprite paddleSprite = new Sprite(32, 0, 1, pongSpriteSheet);
        this.lPaddle = new Paddle('l', pixelWidth, pixelHeight, paddleSprite);
        this.rPaddle = new Paddle('r', pixelWidth, pixelHeight, paddleSprite);

        this.collisionManager = new CollisionManager(pixelWidth, pixelHeight, ball, lPaddle, rPaddle);
        this.scoreManager = new ScoreManager(pixelWidth, pixelHeight);
    }


    public void render(int[] pixels) {
        // initialize  pixels
        for (int i = 0; i < pixelHeight; i++) { // row
            for (int j = 0; j < pixelWidth; j++) {  // column
                pixels[i * pixelWidth + j] = 0x8A9A5B;
            }
        }

        ball.draw(pixels);
        lPaddle.draw(pixels);
        rPaddle.draw(pixels);
    }

    public void render(Graphics g) {
        scoreManager.draw(g);
    }

    public void update() {
        CollisionType collType = collisionManager.checkCollisions();

        switch (collType) {
            case NOCOLLISION:
                ball.update();
                break;
            case LPADDLE:
                ball.leftCollisionUpdate(keyboard);
                break;
            case RPADDLE:
                ball.rightCollisionUpdate(keyboard);
                break;
            case LWALL:
                scoreManager.rScored();
                ball.reset();
                lPaddle.reset();
                rPaddle.reset();
                break;
            case RWALL:
                scoreManager.lScored();
                ball.reset();
                lPaddle.reset();
                rPaddle.reset();
                break;
        }

        handleKeyEvents();
    }

    //
    public void handleKeyEvents() {
        if (keyboard.lUp) {
            lPaddle.moveUp();
        }
        if (keyboard.rUp) {
            rPaddle.moveUp();
        }
        if (keyboard.lDown) {
            lPaddle.moveDown();
        }
        if (keyboard.rDown) {
            rPaddle.moveDown();
        }
    }
}

