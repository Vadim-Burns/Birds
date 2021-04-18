package ui;

import javax.swing.*;

public class Window extends JFrame {
    private final static int tik = 50;
    public Window() {
        initMetaInfo();

        add(new Panel());

        revalidate();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(tik);
                    repaint();
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
