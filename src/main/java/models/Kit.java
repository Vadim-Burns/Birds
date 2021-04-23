package models;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.UnExitable;
import utils.EndlessThread;

import java.awt.*;

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
                    changePoint(
                            getX() - ConfigVars.kitSpeed,
                            getY()
                    );

                    checkPosition();
                }
        ).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    // Добавление hp при пересечении с птицей игрока, нужно подумать
    @Override
    public void onIntersects() {
        respawn();
    }

    private void respawn() {
        active = true;

        changePoint(
                2000 + Math.random() * 500,
                Math.random() * 500
        );
    }

    @Override
    public void checkPosition() {
        if (getX() < 0) {
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
