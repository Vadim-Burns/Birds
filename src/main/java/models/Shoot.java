package models;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.UnExitable;
import utils.EndlessThread;

public class Shoot extends Actor implements Intersectable, UnExitable {

    public Shoot(double x, double y) {
        super(x, y, "shoot.png");

        startMovementThread();
    }

    private void startMovementThread() {
        new EndlessThread(
                ConfigVars.tikRate,
                () -> {
                    changePoint(
                            getX() + ConfigVars.shootSpeed,
                            getY()
                    );

                    checkPosition();
                }
        ).start();
    }

    public void destroy() {
        active = false;
    }

    @Override
    public void onIntersects() {
        destroy();
    }

    @Override
    public void checkPosition() {
        if (getX() > 2500) {
            destroy();
        }
    }
}
