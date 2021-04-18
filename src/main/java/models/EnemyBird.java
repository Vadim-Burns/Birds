package models;

import java.awt.*;

public class EnemyBird extends Bird {

    public EnemyBird(double x, double y) {
        super(
                x,
                y,
                EnemyBird.class.getClassLoader().getResource("enemy.png").getPath()
        );

        s.setFrameWidth(110);
        s.setFrameHeight(101);

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                s.addFrame(new Point(j * s.getFrameWidth(), i * s.getFrameHeight()));
            }
        }

        respawn();
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        if (s.getX() < -s.getFrameWidth()) {
            respawn();
        }
    }

    private void respawn() {
        s.setY(Math.random() * 500);
        s.setX(2000 + Math.random() * 500);
        s.setAlpha(Math.PI);
    }

    @Override
    public void onIntersects(Actor act) {
        respawn();
    }
}
