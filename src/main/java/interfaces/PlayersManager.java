package interfaces;

import models.birds.PlayerBird;

public interface PlayersManager extends BirdsManager<PlayerBird> {

    BirdKeyListener generateArrowKeyListener();

    BirdKeyListener generateWasdKeyListener();
}
