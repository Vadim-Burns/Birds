package ui;

import config.ConfigVars;
import utils.EndlessThread;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JLabel {
    private static InfoPanel infoPanel;

    private int currentLevel = 0;
    private int player1Points = 0;
    private int player2Points = 0;

    public static InfoPanel getDefaultInfoPanel() {
        if (infoPanel == null) infoPanel = new InfoPanel();
        return infoPanel;
    }

    private InfoPanel() {
        super();

        setForeground(Color.BLACK);
        setFont(new Font("Calibri", Font.BOLD, ConfigVars.levelFontSize));

        setText("Level: " + currentLevel + " Player 1: " + player1Points + " Player 2: " + player2Points);

        startUpdatingLevelThread();
    }

    @Override
    public void paint(Graphics g) {
        setText("Level: " + currentLevel + " Player 1: " + player1Points + " Player 2: " + player2Points);

        super.paint(g);
    }

    private void startUpdatingLevelThread() {
        new EndlessThread(
                ConfigVars.levelTime,
                this::processLevelUpdate
        ).start();
    }

    private void processLevelUpdate() {
        ConfigVars.enemySpeed += ConfigVars.deltaEnemySpeed;
        currentLevel++;
    }

    public void addPoints(int playerNumber) {
        switch (playerNumber) {
            case 1:
                player1Points += ConfigVars.pointsForEnemy;
                break;
            case 2:
                player2Points += ConfigVars.pointsForEnemy;
                break;
        }
    }
}
