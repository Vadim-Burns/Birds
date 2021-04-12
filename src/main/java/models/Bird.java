package models;

import java.awt.*;

public abstract class Bird extends Actor {

    public Bird(double x, double y, String filePath) {
        super(x, y, filePath, new Point(0, 0));

        s.setFrameWidth(110);
        s.setFrameHeight(101);

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                s.addFrame(new Point(j * s.getFrameWidth(), i * s.getFrameHeight()));
            }
        }
    }
}
