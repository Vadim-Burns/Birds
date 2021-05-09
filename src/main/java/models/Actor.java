package models;

import interfaces.Intersectable;
import interfaces.Paintable;
import textures.RectangularCollider;
import textures.Sprite;

import java.awt.*;

public abstract class Actor implements Intersectable, Paintable {

    private Sprite sprite;
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
    }


    @Override
    public boolean intersects(Intersectable intersectable) {
        return collider.intersects(intersectable.getCollider());
    }

    @Override
    public RectangularCollider getCollider() {
        return collider;
    }

    protected void changePoint(double x, double y) {
        sprite.changePoint(x, y);
        collider.update(x, y);
    }

    protected double getX() {
        return sprite.getX();
    }

    protected double getY() {
        return sprite.getY();
    }

    protected void addFrame(Point p) {
        sprite.addFrame(p);
    }

    protected int getFrameWidth() {
        return sprite.getFrameWidth();
    }

    protected int getFrameHeight() {
        return sprite.getFrameHeight();
    }

    public boolean isActive() {
        return active;
    }

    protected void setFrameHeight(int frameHeight) {
        sprite.setFrameHeight(frameHeight);
    }

    protected void setFrameWidth(int frameWidth) {
        sprite.setFrameWidth(frameWidth);
    }
}
