package interfaces;

import models.birds.Bird;

import java.util.List;

public interface BirdsManager<T extends Bird> extends Manager {

    List<T> getBirds();
}
