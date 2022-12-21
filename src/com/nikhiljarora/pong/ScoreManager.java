package com.nikhiljarora.pong;

import java.awt.*;

public class ScoreManager {
    private final int gamePixelWidth, gamePixelHeight;
    private int lScore;
    private int rScore;


    public ScoreManager(int gamePixelWidth, int gamePixelHeight) {

        this.gamePixelWidth = gamePixelWidth;
        this.gamePixelHeight = gamePixelHeight;
    }

    public void draw(Graphics g){
        g.drawString(String.format("%d", lScore), gamePixelWidth/4,gamePixelHeight/40);
        g.drawString(String.format("%d", rScore), 3*gamePixelWidth/4,gamePixelWidth/40);
    }

    public void lScored(){
        lScore ++;
    }

    public void rScored(){
        rScore ++;
    }
}
