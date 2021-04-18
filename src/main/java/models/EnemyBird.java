package models;

import interfaces.UnExitable;
import utils.EndlessThread;

import java.awt.*;

public class EnemyBird extends Actor implements UnExitable {
    private final int speed = 10;

    public EnemyBird(double x, double y) {
        super(
                x,
                y,
                EnemyBird.class.getClassLoader().getResource("enemy.png").getPath()
        );

        initFrames();

        respawn();

        startMovementThread();
    }

    private void initFrames() {
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
    }

    private void startMovementThread() {
        new EndlessThread(
                50,
                () -> {
                    sprite.changePoint(sprite.getX() - speed, sprite.getY());
                    checkPosition();
                }
        ).start();
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

    @Override
    public void checkPosition() {
        if (sprite.getX() < 0) {
            respawn();
        }
    }
}
