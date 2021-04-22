package ui;

import interfaces.PlayersManager;
import managers.EnemiesManager;
import models.Kit;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final PlayersManager playersManager;
    private final EnemiesManager enemiesManager;
    private final Kit kit;

    public Panel(PlayersManager playersManager, EnemiesManager enemiesManager) {

        setFocusable(true);
        setBackground(Color.BLACK);

        this.playersManager = playersManager;
        this.enemiesManager = enemiesManager;
        this.kit = new Kit();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        processGame();

        playersManager.paint(g);
        enemiesManager.paint(g);

        kit.paint(g);
    }

    private void processGame() {
        playersManager.checkIntersections(enemiesManager.getBirds());
    }
}
