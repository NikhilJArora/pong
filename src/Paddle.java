import java.awt.*;

public class Paddle {
    private Dimension panel_size;
    private int xPos;
    private int yPos;
    private int moveSize;
    private int xDim;
    private int yDim;

    public Paddle(char side, Dimension panel_size) {
        this.panel_size = panel_size;
        yDim = (int)panel_size.getHeight()/10;
        xDim = (int)panel_size.getWidth()/30;
        moveSize = (int)panel_size.getHeight()/30;

        if (side == 'l') xPos = 0;
        if (side == 'r') xPos = (int)panel_size.getWidth() - xDim;

        reset();
    }

    public void draw(Graphics g){
//        g.drawOval(10, 10, 10, 10);
        g.fillRect(xPos, yPos, xDim, yDim);
    }

    public void moveUp(){
        if(yPos > 0) yPos -= moveSize;
        if (yPos < 0) yPos = 0;
    }

    public void moveDown(){
        if (yPos < (int)panel_size.getHeight() - yDim) yPos += moveSize;
        if (yPos + yDim > (int)panel_size.getHeight()) yPos = (int)panel_size.getHeight() - yDim;
    }

    public void reset(){
        yPos = ((int)panel_size.getHeight()/2) - (yDim/2);
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
