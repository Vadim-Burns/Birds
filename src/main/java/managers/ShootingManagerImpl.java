package managers;

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
        for (Shoot shoot : shoots) {
            shoot.paint(g);
        }
    }

    @Override
    public List<Shoot> getShoots() {
        return shoots;
    }

    // TODO: Удаление старых выстрелов
    @Override
    public void shoot(double x, double y) {
        shoots.add(
                new Shoot(
                        x,
                        y
                )
        );
    }
}
