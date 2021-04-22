package interfaces;

import models.Actor;

import java.util.List;

public interface BirdsManager<T extends Actor> extends Manager {

    void addBird(T bird);

    List<T> getBirds();
}
