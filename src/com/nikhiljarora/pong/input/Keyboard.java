package com.nikhiljarora.pong.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120];
    public boolean lUp, lDown, rUp, rDown;

    public void update() {
        lUp = keys[KeyEvent.VK_W];
        rUp = keys[KeyEvent.VK_UP];
        lDown = keys[KeyEvent.VK_S];
        rDown = keys[KeyEvent.VK_DOWN];
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }
}
