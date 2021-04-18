package ui;

import models.EnemyBird;
import models.PlayerBird;

import javax.swing.*;
import java.awt.*;

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
        playerBird.paint(g);
        enemyBird.paint(g);
    }
}
