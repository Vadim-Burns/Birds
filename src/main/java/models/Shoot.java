package models;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.UnExitable;
import utils.EndlessThread;

public class Shoot extends Actor implements Intersectable, UnExitable {
    private final Thread movementThread;

    public Shoot(double x, double y) {
        super(x, y, "shoot.png");

        movementThread = startMovementThread();
        movementThread.start();
    }

    private Thread startMovementThread() {
        return new EndlessThread(
                ConfigVars.tikRate,
                () -> {
                    changePoint(
                            getX() + ConfigVars.shootSpeed,
                            getY()
                    );

                    checkPosition();
                }
        );
    }

    public void destroy() {
        active = false;
        movementThread.interrupt();
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
