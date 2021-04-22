package managers;

import config.ConfigVars;
import interfaces.BirdsManager;
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
