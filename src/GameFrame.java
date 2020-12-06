import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // adds the Panel to the frame created - sets up a few things
    public GameFrame() {
        super("Pong");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(new GamePanel(), BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // boiler plate to setup our frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new GameFrame();
                frame.setVisible(true);
            }
        });
    }
}

// This is the actual panel where the game is taking place

class GamePanel extends JPanel implements ActionListener, KeyListener {
    Random rand = new Random();

    private static final long serialVersionUID = 1L;
    private static final Dimension PANEL_SIZE = new Dimension(900, 600);
    private static final int REFRESH_RATE = 10;

    // init the game state
    private Timer timer = new Timer(REFRESH_RATE, this);

    PongManager pongManager = new PongManager(PANEL_SIZE);

    public GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
    }

    public Dimension getPreferredSize() {
        return PANEL_SIZE;
    }

    // this guy just renders for you
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pongManager.draw(g);
    }

    // Called when the Timer fires
    public void actionPerformed(ActionEvent e) {
        pongManager.update();
        repaint();
    }

    //
    /* Called when key event occurs - lets you move your paddles around
    * Java bug workaround: defaults write -g ApplePressAndHoldEnabled -bool false
    * */
    public void keyPressed(KeyEvent e) {
        System.out.println("DEBUG: keypress: " + e.getKeyCode());
        pongManager.keyPressed(e);
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}