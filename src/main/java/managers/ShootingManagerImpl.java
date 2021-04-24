package managers;

import interfaces.Hitable;
import interfaces.managers.ShootingManager;
import models.Shoot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShootingManagerImpl implements ShootingManager {
    private final List<Shoot> shoots = new ArrayList<>();

    private static ShootingManagerImpl shootingManager;

    public static ShootingManager getDefaultShootingManager() {
        if (shootingManager == null) shootingManager = new ShootingManagerImpl();
        return shootingManager;
    }

    @Override
    public void paint(Graphics g) {
        List<Shoot> deletedShoots = new ArrayList<>();

        for (Shoot shoot : shoots) {
            shoot.paint(g);

            if (!shoot.isActive()) deletedShoots.add(shoot);
        }

        shoots.removeAll(deletedShoots);
    }

    @Override
    public List<Shoot> getShoots() {
        return shoots;
    }

    @Override
    public void shoot(double x, double y, Hitable hitable) {
        shoots.add(
                new Shoot(
                        x,
                        y,
                        hitable
                )
        );
    }
}
