import java.awt.*;

public class ScoreManager {
    private Dimension panel_size;
    private int lScore;
    private int rScore;


    public ScoreManager(Dimension panel_size) {
        this.panel_size = panel_size;
    }

    public void draw(Graphics g){
        g.drawString(String.format("%d -- %d", lScore,rScore), (int)panel_size.getWidth()/2,(int)panel_size.getHeight()/40);
    }

    public void lScored(){
        lScore ++;
    }

    public void rScored(){
        rScore ++;
    }
}
