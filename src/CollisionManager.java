import java.awt.*;

public class CollisionManager {
    private Dimension panel_size;
    private Ball ball;
    private Paddle lPaddle;
    private Paddle rPaddle;


//    public CollisionManager(Dimension panel_size, Ball ball, Paddle lPaddle, Paddle rPaddle) {
//        this.panel_size = panel_size;
//        this.
//    }

    public CollisionManager(Dimension panel_size, Ball ball, Paddle lPaddle, Paddle rPaddle) {
        this.panel_size = panel_size;
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
            System.out.println("DEBUG: left coll occurred");
            return CollisionType.LPADDLE;
        }
        // check coll with rPaddle
        else if (ball.getxPos() + ball.getxDim() >= rPaddle.getxPos() &&
                (ball.getyPos() < rPaddle.getyPos() + rPaddle.getyDim() &&
                        ball.getyPos() + ball.getyDim() > rPaddle.getyPos()) &&
                ball.getxVel() > 0
        ) {
            System.out.println("DEBUG: right coll occurred");
            return CollisionType.RPADDLE;
        }

        // check coll with left wall
        else if (ball.getxPos() <= 0){
            System.out.println("DEBUG: rPaddle has scored");
            return CollisionType.LWALL;
        }

        // check coll with right wall
        else if (ball.getxPos() + ball.getxDim() >= panel_size.getWidth()){
            System.out.println("DEBUG: lPaddle has scored");
            return CollisionType.RWALL;
        } else {
            return CollisionType.NOCOLLISION;
        }
    }

}
