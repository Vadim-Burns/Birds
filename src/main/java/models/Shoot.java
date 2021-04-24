package models;

import config.ConfigVars;
import interfaces.Hitable;
import interfaces.Intersectable;
import interfaces.UnExitable;
import utils.EndlessThread;

public class Shoot extends Actor implements Intersectable, UnExitable {
    private final Thread movementThread;
    private final Hitable hitable;

    public Shoot(double x, double y, Hitable hitable) {
        super(x, y, "shoot.png");

        this.hitable = hitable;

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
                },
                false
        );
    }

    public void destroy() {
        active = false;
        movementThread.interrupt();
    }

    @Override
    public void onIntersects() {
        hitable.onHit();

        destroy();
    }

    @Override
    public void checkPosition() {
        if (getX() > 2500) {
            destroy();
        }
    }
}
