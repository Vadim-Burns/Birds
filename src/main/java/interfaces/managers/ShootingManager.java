package interfaces.managers;

import interfaces.Hitable;
import models.Shoot;

import java.util.List;

public interface ShootingManager extends Manager {

    List<Shoot> getShoots();

    void shoot(double x, double y, Hitable hitable);
}
