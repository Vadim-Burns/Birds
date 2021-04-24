package ui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Image image;

    public BackgroundPanel(String filepath) {
        this.image = Toolkit
                .getDefaultToolkit()
                .getImage(
                        Window.class.getClassLoader().getResource(filepath)
                );
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(
                image,
                0, 0,
                getWidth(), getHeight(),
                this
        );
    }
}
