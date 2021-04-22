package ui;

import interfaces.PlayersManager;
import managers.EnemiesManager;
import managers.KitManagerImpl;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final PlayersManager playersManager;
    private final EnemiesManager enemiesManager;
    private final KitManagerImpl kitManager;

    public Panel(PlayersManager playersManager, EnemiesManager enemiesManager, KitManagerImpl kitManager) {

        setFocusable(true);
        setBackground(Color.BLACK);

        this.playersManager = playersManager;
        this.enemiesManager = enemiesManager;
        this.kitManager = kitManager;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        processGame();

        playersManager.paint(g);
        enemiesManager.paint(g);
        kitManager.paint(g);
    }

    private void processGame() {
        playersManager.checkIntersections(enemiesManager.getBirds());
        playersManager.checkIntersections(kitManager.getKits());
    }
}
