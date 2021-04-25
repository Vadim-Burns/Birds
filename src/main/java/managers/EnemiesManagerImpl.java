package managers;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.managers.EnemiesManager;
import models.birds.EnemyBird;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemiesManagerImpl implements EnemiesManager {
    private final List<EnemyBird> birds = new ArrayList<>();

    private boolean spawned = false;

    @Override
    public List<EnemyBird> getBirds() {
        return birds;
    }

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

    @Override
    public void paint(Graphics g) {
        for (EnemyBird bird : birds) {
            bird.paint(g);
        }
    }

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
