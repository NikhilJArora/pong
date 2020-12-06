import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class PongManager {
    private Dimension panel_size;
    private Paddle lPaddle;
    private Paddle rPaddle;
    private Ball ball;
    private CollisionManager collisionManager;
    private ScoreManager scoreManager;

    public PongManager(Dimension panel_size) {
        this.panel_size = panel_size;
        this.lPaddle = new Paddle('l', panel_size);
        this.rPaddle = new Paddle('r', panel_size);
        this.ball = new Ball(panel_size);
        this.collisionManager = new CollisionManager(panel_size, ball, lPaddle, rPaddle);
        this.scoreManager = new ScoreManager(panel_size);
    }

    public void draw(Graphics g) {
        ball.draw(g);
        lPaddle.draw(g);
        rPaddle.draw(g);
        scoreManager.draw(g); // probably shouldn't draw every time...
    }

    public void update(){
        CollisionType collType = collisionManager.checkCollisions();

        switch (collType){
            case NOCOLLISION:
                ball.update();
                break;
            case LPADDLE:
            case RPADDLE:
                ball.collisionUpdate();
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
    }

    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                rPaddle.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                rPaddle.moveDown();
                break;
            case KeyEvent.VK_W:
                lPaddle.moveUp();
                break;
            case KeyEvent.VK_S:
                lPaddle.moveDown();
                break;
        }
    }
}

