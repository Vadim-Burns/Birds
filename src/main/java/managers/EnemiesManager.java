package managers;

import config.ConfigVars;
import interfaces.BirdsManager;
import models.Actor;
import models.birds.EnemyBird;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemiesManager implements BirdsManager<EnemyBird> {
    private final List<EnemyBird> birds = new ArrayList<>();


    public EnemiesManager() {
        for (int i = 0; i < ConfigVars.enemiesCount; i++) {
            birds.add(
                    new EnemyBird()
            );
        }
    }

    @Override
    public void addBird(EnemyBird bird) {
        birds.add(bird);
    }

    @Override
    public void checkIntersections(List<? extends Actor> birds) {
        for (EnemyBird enemyBird : this.birds) {
            for (Actor bird : birds) {
                if (enemyBird.intersects(bird)) {
                    enemyBird.onIntersects();
                    bird.onIntersects();
                }
            }
        }
    }

    @Override
    public List<EnemyBird> getBirds() {
        return birds;
    }

    @Override
    public void paint(Graphics g) {
        for (EnemyBird bird : birds) {
            bird.paint(g);
        }
    }
}
