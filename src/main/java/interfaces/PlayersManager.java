package interfaces;

import models.PlayerBird;

public interface PlayersManager extends BirdsManager<PlayerBird> {

    BirdKeyListener generateArrowKeyListener();

    BirdKeyListener generateWasdKeyListener();
}
