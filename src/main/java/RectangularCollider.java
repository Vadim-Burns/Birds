import java.awt.*;

public class RectangularCollider {

    double x, y;
    int w, h;
    int pad;


    public RectangularCollider(double x, double y, int w, int h, int pad) {
        this(x, y, w, h);
        this.pad = pad;
    }

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
        return new Rectangle((int) x + pad, (int) y + pad, w - pad * 2, h - pad * 2);
    }


    public boolean intersects(RectangularCollider collider) {
        return getRectangle().intersects(collider.getRectangle());

    }
}
