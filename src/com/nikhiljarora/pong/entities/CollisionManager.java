package com.nikhiljarora.pong.entities;

import java.awt.*;

public class CollisionManager {
    private final int gamePixelWidth, gamePixelHeight;
    private Ball ball;
    private Paddle lPaddle;
    private Paddle rPaddle;

    public CollisionManager(int gamePixelWidth, int gamePixelHeight, Ball ball, Paddle lPaddle, Paddle rPaddle) {
        this.gamePixelWidth = gamePixelWidth;
        this.gamePixelHeight = gamePixelHeight;
        this.ball = ball;
        this.lPaddle = lPaddle;
        this.rPaddle = rPaddle;
    }
    public CollisionType checkCollisions(){
        // check coll with lPaddle
        if (lPaddle.getxPos() + lPaddle.getxDim() >= ball.getxPos() &&
                (ball.getyPos() < lPaddle.getyPos() + lPaddle.getyDim() &&
                        ball.getyPos() + ball.getyDim() > lPaddle.getyPos()) &&
                ball.getxVel() < 0
        ) {
            return CollisionType.LPADDLE;
        }
        // check coll with rPaddle
        else if (ball.getxPos() + ball.getxDim() >= rPaddle.getxPos() + rPaddle.getxDim() &&
                (ball.getyPos() < rPaddle.getyPos() + rPaddle.getyDim() &&
                        ball.getyPos() + ball.getyDim() > rPaddle.getyPos()) &&
                ball.getxVel() > 0
        ) {
            return CollisionType.RPADDLE;
        }

        // check coll with left wall
        else if (ball.getxPos() <= 0){
            return CollisionType.LWALL;
        }

        // check coll with right wall
        else if (ball.getxPos() + ball.getxDim() >= gamePixelWidth){
            return CollisionType.RWALL;
        } else {
            return CollisionType.NOCOLLISION;
        }
    }

}
