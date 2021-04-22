package models;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.UnExitable;
import utils.EndlessThread;

public class Kit extends Actor implements Intersectable, UnExitable {

    public Kit() {
        super(100, 100, "kit.png");

        respawn();

        startMovementThread();
    }

    private void startMovementThread() {
        new EndlessThread(
                ConfigVars.tikRate,
                () -> {
                    sprite.changePoint(sprite.getX() - ConfigVars.kitSpeed, sprite.getY());
                    checkPosition();
                }
        ).start();
    }

    // Добавление hp при пересечении с птицей игрока, нужно подумать
    @Override
    public void onIntersects() {
        respawn();
    }

    private void respawn() {
        active = true;
        sprite.setY(Math.random() * 500);
        sprite.setX(2000 + Math.random() * 500);
    }

    @Override
    public void checkPosition() {
        if (sprite.getX() < 0) {
            try {
                active = false;

                Thread.sleep(ConfigVars.kitDelay);

                respawn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
