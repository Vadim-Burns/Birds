package models;

import java.awt.*;

public class EnemyBird extends Actor {

    public EnemyBird(double x, double y) {
        super(
                x,
                y,
                EnemyBird.class.getClassLoader().getResource("enemy.png").getPath()
        );

        sprite.setFrameWidth(110);
        sprite.setFrameHeight(101);

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                sprite.addFrame(new Point(j * sprite.getFrameWidth(), i * sprite.getFrameHeight()));
            }
        }

        respawn();
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        if (sprite.getX() < -sprite.getFrameWidth()) {
            respawn();
        }
    }

    private void respawn() {
        sprite.setY(Math.random() * 500);
        sprite.setX(2000 + Math.random() * 500);
    }

    @Override
    public void onIntersects(Actor act) {
        respawn();
    }
}
