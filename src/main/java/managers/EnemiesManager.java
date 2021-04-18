package managers;

import interfaces.BirdsManager;
import models.Bird;
import models.EnemyBird;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemiesManager implements BirdsManager<EnemyBird> {
    private final List<EnemyBird> birds = new ArrayList<>();


    @Override
    public void addBird(EnemyBird bird) {
        birds.add(bird);
    }

    @Override
    public void checkIntersections(List<? extends Bird> birds) {
        for (EnemyBird enemyBird : this.birds) {
            for (Bird bird : birds) {
                if (enemyBird.intersects(bird)) {
                    enemyBird.onIntersects();
                    bird.onIntersects();
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
}
