package interfaces;

import java.util.List;

public interface Manager extends Paintable {

    void checkIntersections(List<? extends Intersectable> intersectables);


}
