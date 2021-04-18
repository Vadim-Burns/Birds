package ui;

import utils.EndlessThread;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final static int tik = 50;

    public Window() {
        initMetaInfo();

        Panel panel = new Panel();
        add(panel);

        addKeyListener(panel.buildKeyListener());

        revalidate();

        startUIThread();
    }

    private void startUIThread() {
        new EndlessThread(
                tik,
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
