package models;

import textures.RectangularCollider;
import textures.Sprite;

import java.awt.*;

public abstract class Actor {

    protected Sprite s;
    protected RectangularCollider collider;

    protected boolean active = true;
    protected int hp = 100;
    protected int damage = 30;

    public void damage(int d) {
        hp -= d;
        if (hp <= 0) {
            die();
        }
    }

    public void up() {
        s.setAlpha(-Math.PI / 2);
    }

    public void down() {
        s.setAlpha(Math.PI / 2);
    }

    public void left() {
        s.setAlpha(Math.PI);
    }

    public void right() {
        s.setAlpha(0);
    }

    public void die() {
        hp = 0;
        active = false;
    }

    Actor(double x, double y, String fileName, Point p) {
        s = new Sprite(x, y, fileName, p);
        collider = new RectangularCollider(x, y, 100, 100);
    }

    public void show() {
        s.show();
    }

    public void hide() {
        s.hide();
    }

    public void paint(Graphics g) {
        if (!active) {
            return;
        }
        s.paint(g);
    }


    public abstract void onIntersects(Actor act);

    public final void intersects(Actor act) {

        if (collider.intersects(act.collider) && active && act.active) {
            onIntersects(act);
            act.onIntersects(this);
        }
    }

    public void update(int ms) {
        if (!active) {
            return;
        }

        s.update(ms);
        collider.update(s.getX(), s.getY());
    }
}
