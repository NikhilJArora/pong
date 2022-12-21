package com.nikhiljarora.pong;

import com.nikhiljarora.pong.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class GameDisplay extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int width = 1280;
    public static int height = width / 16 * 9;  // 720
    public static int scale = 1;

    public static int tickRate = 64;

    private Thread thread;
    public JFrame frame;
    private boolean running = false;

    private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private final PongManager pongManager;

    private final Keyboard keyboard;
    public GameDisplay() {
        Dimension panelSize = new Dimension(width * scale, height * scale);
        setPreferredSize(panelSize);

        frame = new JFrame();

        keyboard = new Keyboard();
        addKeyListener(keyboard);

        pongManager = new PongManager(width, height, keyboard);
    }

    // start
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "GameDisplay");
        thread.start();
    }

    // stop
    public synchronized void stop() throws InterruptedException {
        running = false;
        thread.join();
    }

    // run
    @Override
    public void run() {

        long lastTick = System.nanoTime();
        long timer = System.currentTimeMillis();
        long delta;
        long now;
        int tickCounter = 0;
        int renderCounter = 0;

        final long tickTime = 1_000_000_000/tickRate;
        while (running) {
            now = System.nanoTime();
            delta = now-lastTick;
            if (delta >= (tickTime)) {
                tick();
                lastTick = now;
                tickCounter++;
            }
            render();  // this can happen as fast as your comp can handle
            renderCounter++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("Pong | " + tickCounter + " Ticks | " + renderCounter + " Frames");
                tickCounter = 0;
                renderCounter = 0;
            }
        }
    }

    private void tick() {
//        System.out.println("Im Ticking...");
        keyboard.update();
        pongManager.update();
    }

    private void clearPixels() {
        Arrays.fill(pixels, 0);
    }
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        clearPixels();
        // apply our buffer to a graphics object
        Graphics g = bs.getDrawGraphics();
        pongManager.render(pixels);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        pongManager.render(g);  // used for simple text
        g.dispose();
        bs.show();
    }
}
