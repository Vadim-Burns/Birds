package ui;

import models.EnemyBird;
import models.KeyBind;
import models.PlayerBird;
import utils.KeyListenerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {
    private final PlayerBird playerBird = new PlayerBird();
    private final EnemyBird enemyBird = new EnemyBird(100, 100);

    public Panel() {
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        processGame();

        playerBird.paint(g);
        enemyBird.paint(g);
    }

    private void processGame() {
    }

    public KeyListener buildKeyListener() {
        KeyListenerImpl keyListener = new KeyListenerImpl();
        keyListener.addBind(
                new KeyBind(
                        KeyEvent.VK_UP,
                        () -> System.out.println("UP")
                )
        );

        keyListener.addBind(
                new KeyBind(
                        KeyEvent.VK_DOWN,
                        () -> System.out.println("DOWN")
                )
        );

        return keyListener;
    }
}
