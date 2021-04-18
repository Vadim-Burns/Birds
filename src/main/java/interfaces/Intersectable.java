package interfaces;

import textures.RectangularCollider;

public interface Intersectable {

    void onIntersects();

    boolean intersects(Intersectable intersectable);

    RectangularCollider getCollider();
}
