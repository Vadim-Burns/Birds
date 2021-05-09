package managers;

import interfaces.Hitable;
import interfaces.managers.ShootingManager;
import models.Shoot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер для управления снарядами игроков.
 */
public class ShootingManagerImpl implements ShootingManager {
    private final List<Shoot> shoots = new ArrayList<>();

    private static ShootingManagerImpl shootingManager;

    /**
     * Защита от создания нескольких менеджеров, чтобы любой класс мог обратиться к ShootingManagerImpl.
     * Если это первый запрос, то будет создан новый менеджер.
     */
    public static ShootingManager getDefaultShootingManager() {
        if (shootingManager == null) shootingManager = new ShootingManagerImpl();
        return shootingManager;
    }

    /**
     * Отрисовка всех снарядов.
     * Отработавшие(вышедшие за поле и попавшие в кого-то) снаряды удаляются.
     */
    @Override
    public void paint(Graphics g) {
        // Массив удаленных снарядов
        List<Shoot> deletedShoots = new ArrayList<>();

        for (Shoot shoot : shoots) {
            shoot.paint(g);

            if (!shoot.isActive()) deletedShoots.add(shoot);
        }

        // Удаление отработавших снарядов
        shoots.removeAll(deletedShoots);
    }

    /**
     * Получение всех текущий снарядов
     */
    @Override
    public List<Shoot> getShoots() {
        return shoots;
    }

    /**
     * Совершение выстрела.
     *
     * @param x       Координата ОТКУДА летит снаряд
     * @param y       Координата ОТКУДА летит снаряд
     * @param hitable Объект, чей метод hit() будет вызван при попадании по врагу
     */
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
