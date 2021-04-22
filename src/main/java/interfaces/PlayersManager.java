package interfaces;

import models.birds.PlayerBird;

import java.util.List;

public interface PlayersManager extends BirdsManager<PlayerBird> {

    BirdKeyListener generateArrowKeyListener();

    BirdKeyListener generateWasdKeyListener();

    void checkIntersections(List<? extends Intersectable> intersectables);

}
