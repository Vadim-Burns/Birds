package ui;

import models.Actor;
import models.EnemyBird;
import models.PlayerBird;
import utils.KeyState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {

    KeyState keyState = new KeyState();
    Actor act = new PlayerBird(300, 400);
    Actor act2 = new EnemyBird(300, 400);

    private boolean isGameThreadEnabled = false;

    long t1, t2;

    public Panel() {
        setFocusable(true);
        setBackground(Color.BLACK);
        t1 = System.currentTimeMillis();

//        new Thread(() -> {
//            System.out.println("Start UI thread");
//            while (true) {
//                try {
//                    repaint();
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    break;
//                }
//            }
//        }).start();
    }

    private void controlGame() {

        if (keyState.keyDown(KeyEvent.VK_UP)) {
            act.up();
        } else if (keyState.keyDown(KeyEvent.VK_DOWN)) {
            act.down();
        }


        act.show();

        if (keyState.keyDown(KeyEvent.VK_SPACE)) {
            act.hide();


        }

        keyState.update();
    }

    private void updateGame(int ms) {
        act.update(ms);
        act2.update(ms);

        act.intersects(act2);
    }

    private void paintGame(Graphics g) {
        act.paint(g);
        act2.paint(g);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        startGameThread(g);
    }

    private void startGameThread(Graphics g) {
        if (!isGameThreadEnabled) {
            isGameThreadEnabled = true;

            new Thread(() -> {
                while (true) {
                    try {
                        t2 = System.currentTimeMillis();
                        int ms = (int) (t2 - t1);

                        controlGame();
                        updateGame(ms);
                        paintGame(g);

                        t1 = t2;

                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }).start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyState.setKeyState(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyState.setKeyState(e.getKeyCode(), false);
    }
}
