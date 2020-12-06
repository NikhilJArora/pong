import java.awt.*;
import java.util.Random;

public class Ball {
    private Dimension panel_size;

    private int xPos;
    private int yPos;
    // for coll detection purposes Im considering the ball to a square lol
    private int xDim;
    private int yDim;
    private static int frameVel = 4;
    private int xVel;

    private int yVel;

    public int getyPos() {
        return yPos;
    }

    public int getyDim() {
        return yDim;
    }

    public int getxVel() {
        return xVel;
    }

    public Ball(Dimension panel_size) {
        this.panel_size = panel_size;
        xDim = (int)panel_size.getHeight()/30;
        yDim = xDim;
        reset();
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
    public void draw(Graphics g){
        g.fillOval(xPos, yPos, xDim, yDim);
    }

    /*
    * resets balls pos
    * uses: start of each round
    * */
    public void reset(){
        xPos = ((int)panel_size.getWidth()/2) - (xDim/2);
        yPos = ((int)panel_size.getHeight()/2) - (xDim/2);

        xVel = frameVel * (new Random().nextBoolean() ? -1 : 1);
        yVel = frameVel * (new Random().nextBoolean() ? -1 : 1);

    }

    public void update() {
        // we are going to handle wall collisions based on our knowledge of the top and bottom
        if ( (xPos+xDim) >  (int)panel_size.getWidth() || (xPos) < 0) {
            //hit the left or right wall
            xVel *= -1;
        }
        if ( (yPos) < 0 || (yPos+ yDim) > (int)panel_size.getHeight()) {
            //hit the top wall
            // TODO: incorp some randomness to the yVel after bouncing off a wall
            yVel *= -1;
        }

        xPos = xPos + xVel;
        yPos = yPos + yVel;
    }

    public void collisionUpdate(){ xVel *= -1; }

}
