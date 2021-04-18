package models;

import interfaces.Movable;
import interfaces.UnExitable;
import utils.EndlessThread;

import java.awt.*;

public class PlayerBird extends Bird implements Movable, UnExitable {

    private final int speed = 10;
    private final int gravity = 5;

    public PlayerBird() {
        super(
                100,
                100,
                PlayerBird.class.getClassLoader().getResource("player.png").getPath()
        );

        initFrames();

        startGravityThread();
    }

    private void initFrames() {
        sprite.setFrameWidth(110);
        sprite.setFrameHeight(101);

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                sprite.addFrame(new Point(j * sprite.getFrameWidth(), i * sprite.getFrameHeight()));
            }
        }
    }

    private void startGravityThread() {
        new EndlessThread(
                50,
                () -> {
                    sprite.changePoint(sprite.getX(), sprite.getY() + gravity);
                    checkPosition();
                }
        ).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillRect((int) sprite.getX(), (int) sprite.getY() + sprite.getFrameHeight(), (int) (hp / 100.0 * sprite.getFrameWidth()), 3);
    }

    @Override
    public void onIntersects() {
        damage(super.damage);
    }

    @Override
    public void up() {
        sprite.changePoint(sprite.getX(), sprite.getY() - speed);
        checkPosition();
    }

    @Override
    public void down() {
        sprite.changePoint(sprite.getX(), sprite.getY() + speed);
        checkPosition();
    }

    @Override
    public void checkPosition() {
        if (sprite.getY() < 0) {
            sprite.setY(0);
        } else if (sprite.getY() > 700) {
            sprite.setY(700);
        }
    }
}
