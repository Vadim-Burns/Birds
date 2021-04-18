package ui;

import models.PlayerBird;
import textures.Sprite;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final Sprite sprite = new Sprite(
            100,
            100,
            PlayerBird.class.getClassLoader().getResource("player.png").getPath(),
            new Point(0, 0)
    );

    public Panel() {
        setFocusable(true);
        setBackground(Color.BLACK);

        sprite.setFrameWidth(110);
        sprite.setFrameHeight(101);

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                sprite.addFrame(new Point(j * sprite.getFrameWidth(), i * sprite.getFrameHeight()));
            }
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        sprite.paint(g);
        Toolkit.getDefaultToolkit().sync();
    }
}
