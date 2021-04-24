package ui;

import interfaces.managers.KitsManager;
import interfaces.managers.PlayersManager;
import interfaces.managers.ShootingManager;
import managers.EnemiesManager;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final PlayersManager playersManager;
    private final EnemiesManager enemiesManager;
    private final KitsManager kitManager;
    private final ShootingManager shootingManager;

    private final Image background;

    public Panel(
            PlayersManager playersManager,
            EnemiesManager enemiesManager,
            KitsManager kitManager,
            ShootingManager shootingManager
    ) {

        setFocusable(true);

        this.playersManager = playersManager;
        this.enemiesManager = enemiesManager;
        this.kitManager = kitManager;
        this.shootingManager = shootingManager;

        this.background = Toolkit
                .getDefaultToolkit()
                .getImage(
                        Window.class.getClassLoader().getResource("background.png")
                );

        add(InfoPanel.getDefaultInfoPanel());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(
                background,
                0, 0,
                getWidth(), getHeight(),
                this
        );

        processGame();

        playersManager.paint(g);
        enemiesManager.paint(g);
        kitManager.paint(g);
        shootingManager.paint(g);
        InfoPanel.getDefaultInfoPanel().paint(g);
    }

    private void processGame() {
        enemiesManager.checkIntersections(shootingManager.getShoots());

        playersManager.checkIntersections(enemiesManager.getBirds());
        playersManager.checkIntersections(kitManager.getKits());
    }
}
