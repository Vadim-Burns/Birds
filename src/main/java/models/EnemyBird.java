package models;

import java.awt.*;

public class EnemyBird extends Actor {

    public EnemyBird(double x, double y) {
        super(x, y, EnemyBird.class.getClassLoader().getResource("player.png").getPath(), new Point(0, 0));
        // Сделать сеттеры для этих полей
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
        left();
    }

    @Override
    public void onIntersects(Actor act) {
        respawn();
    }
}
