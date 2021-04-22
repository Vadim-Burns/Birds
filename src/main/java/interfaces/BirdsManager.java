package interfaces;

import models.Actor;

import java.util.List;

public interface BirdsManager<T extends Actor> extends Paintable {

    void addBird(T bird);

    void checkIntersections(List<? extends Intersectable> birds);

    List<T> getBirds();
}
