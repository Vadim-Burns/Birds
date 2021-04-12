import java.awt.*;

public class RectangularCollider {

    double x, y;
    int w, h;

    public RectangularCollider(double x, double y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, w, h);
    }


    public boolean intersects(RectangularCollider collider) {
        return getRectangle().intersects(collider.getRectangle());

    }
}
