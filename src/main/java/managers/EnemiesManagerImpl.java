package managers;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.managers.EnemiesManager;
import models.birds.EnemyBird;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер для управления всеми вражескими птицами
 */
public class EnemiesManagerImpl implements EnemiesManager {
    private final List<EnemyBird> birds = new ArrayList<>();

    private boolean spawned = false;

    /**
     * Получение списка вражеских птиц
     */
    @Override
    public List<EnemyBird> getBirds() {
        return birds;
    }

    /**
     * Проверка на столкновения
     */
    @Override
    public void checkIntersections(List<? extends Intersectable> intersectables) {
        for (EnemyBird bird : birds) {
            for (Intersectable intersectable : intersectables) {
                if (bird.intersects(intersectable)) {
                    bird.onIntersects();
                    intersectable.onIntersects();
                }
            }
        }
    }

    /**
     * Отрисовка всех вражеских птиц
     */
    @Override
    public void paint(Graphics g) {
        for (EnemyBird bird : birds) {
            bird.paint(g);
        }
    }

    /**
     * Генерация вражеских птиц.
     * Метод может быть вызван только один раз.
     */
    @Override
    public void spawn() {
        if (!spawned) {
            for (int i = 0; i < ConfigVars.enemiesCount; i++) {
                birds.add(
                        new EnemyBird()
                );
            }

            spawned = true;
        }
    }
}
