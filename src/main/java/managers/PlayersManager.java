package managers;

import interfaces.BirdsManager;
import models.Bird;
import models.PlayerBird;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayersManager implements BirdsManager<PlayerBird> {
    private final List<PlayerBird> birds = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        for (PlayerBird bird : birds) {
            bird.paint(g);
        }
    }


    @Override
    public void addBird(PlayerBird bird) {
        birds.add(bird);
    }

    @Override
    public void checkIntersections(List<? extends Bird> birds) {
        for (PlayerBird playerBird : this.birds) {
            for (Bird bird : birds) {
                if (playerBird.intersects(bird)) {
                    playerBird.onIntersects();
                    bird.onIntersects();
                }
            }
        }
    }
}
