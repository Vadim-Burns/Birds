package ui;

import config.ConfigVars;
import interfaces.PlayersManager;
import managers.EnemiesManager;
import managers.KitsManagerImpl;
import managers.PlayersManagerImpl;
import utils.EndlessThread;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        initMetaInfo();

        PlayersManager playersManager = new PlayersManagerImpl();
        EnemiesManager enemiesManager = new EnemiesManager();
        KitsManagerImpl kitManager = new KitsManagerImpl();

        Panel panel = new Panel(
                playersManager,
                enemiesManager,
                kitManager
        );
        add(panel);

        addKeyListener(playersManager.generateArrowKeyListener());
        addKeyListener(playersManager.generateWasdKeyListener());

        revalidate();

        startUIThread();
    }

    private void startUIThread() {
        new EndlessThread(
                ConfigVars.tikRate,
                () -> {
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                }
        ).start();
    }

    private void initMetaInfo() {
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Фани Бёд");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
