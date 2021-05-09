package interfaces.managers;

import interfaces.BirdKeyListener;
import models.birds.PlayerBird;

public interface PlayersManager extends BirdsManager<PlayerBird> {

    BirdKeyListener generateArrowKeyListener();

    BirdKeyListener generateWasdKeyListener();
}
