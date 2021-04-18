package interfaces;

import models.Bird;

import java.util.List;

public interface BirdsManager<T extends Bird> extends Paintable {

    void addBird(T bird);

    void checkIntersections(List<? extends Bird> birds);
}
