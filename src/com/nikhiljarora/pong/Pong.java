package com.nikhiljarora.pong;

import javax.swing.*;

public class Pong {

    public static void main(String[] args) {
        // entrypoint for my game
        GameDisplay gameDisplay = new GameDisplay();
        gameDisplay.frame.setResizable(false);
        gameDisplay.frame.setTitle("Pong");
        gameDisplay.frame.add(gameDisplay);
        gameDisplay.frame.pack();
        gameDisplay.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameDisplay.frame.setLocationRelativeTo(null);
        gameDisplay.frame.setVisible(true);

        gameDisplay.start();
    }
}
