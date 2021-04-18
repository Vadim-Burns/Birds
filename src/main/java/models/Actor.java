package models;

import interfaces.Intersectable;
import textures.RectangularCollider;
import textures.Sprite;

import java.awt.*;

public abstract class Actor implements Intersectable {

    protected Sprite sprite;
    private RectangularCollider collider;

    protected boolean active = true;
    protected int hp = 100;
    protected int damage = 30;

    Actor(double x, double y, String filePath) {
        sprite = new Sprite(x, y, filePath, new Point(0, 0));
        collider = new RectangularCollider(x, y, 100, 100);
    }

    public void damage(int d) {
        hp -= d;
        if (hp <= 0) {
            die();
        }
    }

    public void die() {
        hp = 0;
        active = false;
    }

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
