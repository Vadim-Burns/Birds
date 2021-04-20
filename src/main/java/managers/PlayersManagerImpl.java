package managers;

import interfaces.BirdKeyListener;
import interfaces.PlayersManager;
import models.Bird;
import models.KeyBind;
import models.PlayerBird;
import utils.BirdKeyListenerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayersManagerImpl implements PlayersManager {
    private final List<PlayerBird> birds = new ArrayList<>();

    public PlayersManagerImpl() {
        birds.add(
                new PlayerBird()
        );

        birds.add(
                new PlayerBird()
        );
    }

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

    @Override
    public List<PlayerBird> getBirds() {
        return birds;
    }

    @Override
    public BirdKeyListener generateArrowKeyListener() {
        if (!birds.isEmpty()) {
            BirdKeyListenerImpl birdKeyListener = new BirdKeyListenerImpl();
            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_UP,
                            birds.get(0)::up
                    )
            );

            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_DOWN,
                            birds.get(0)::down
                    )
            );

            return birdKeyListener;
        } else {
            System.out.println("Not enough birds!");
            System.out.println("It must be one bird at least for arrow listener");
            return null;
        }
    }

    @Override
    public BirdKeyListener generateWasdKeyListener() {
        if (birds.size() > 1) {
            BirdKeyListenerImpl birdKeyListener = new BirdKeyListenerImpl();
            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_W,
                            birds.get(1)::up
                    )
            );

            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_S,
                            birds.get(1)::down
                    )
            );

            return birdKeyListener;
        } else {
            System.out.println("Not enough birds!");
            System.out.println("It must be two birds at least for wasd listener");
            return null;
        }
    }
}
