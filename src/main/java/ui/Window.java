package ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final static int tik = 50;

    public Window() {
        initMetaInfo();

        add(new Panel());

        revalidate();

        startUIThread();
    }

    private void startUIThread() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(tik);
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    private void initMetaInfo() {
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Фани Бёд");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
