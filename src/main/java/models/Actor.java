package models;

import interfaces.Intersectable;
import interfaces.Paintable;
import textures.RectangularCollider;
import textures.Sprite;

import java.awt.*;

public abstract class Actor implements Intersectable, Paintable {

    protected Sprite sprite;
    private RectangularCollider collider;

    protected boolean active = true;

    public Actor(double x, double y, String filePath) {
        sprite = new Sprite(x, y, filePath, new Point(0, 0));
        collider = new RectangularCollider(x, y, 100, 100);
    }

    @Override
    public void paint(Graphics g) {
        if (!active) {
            return;
        }
        sprite.paint(g);

        update();
    }


    @Override
    public boolean intersects(Intersectable intersectable) {
        return collider.intersects(intersectable.getCollider());
    }

    // TODO: Убрать прямые запросы к sprite с проверкой на каждый тик и сделать отдельный метод
    private void update() {
        if (!active) {
            return;
        }

        collider.update(sprite.getX(), sprite.getY());
    }

    @Override
    public RectangularCollider getCollider() {
        return collider;
    }
}
