package models;

import config.ConfigVars;
import interfaces.Damagable;
import interfaces.Movable;
import interfaces.UnExitable;
import utils.EndlessThread;

import java.awt.*;

public class PlayerBird extends Bird implements Movable, UnExitable, Damagable {

    private final Color color;

    private int hp;

    public PlayerBird(Color color) {
        super(
                100,
                100,
                "player.png"
        );

        initFrames();

        this.color = color;
        this.hp = ConfigVars.playerHp;

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
                ConfigVars.tikRate,
                () -> {
                    sprite.changePoint(sprite.getX(), sprite.getY() + ConfigVars.playerGravity);
                    checkPosition();
                }
        ).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.fillRect((int) sprite.getX(), (int) sprite.getY() + sprite.getFrameHeight(), (int) (hp / 100.0 * sprite.getFrameWidth()), 3);
    }

    @Override
    public void onIntersects() {
        damage(ConfigVars.playerIntersectDamage);
    }

    @Override
    public void up() {
        sprite.changePoint(sprite.getX(), sprite.getY() - ConfigVars.playerSpeed);
        checkPosition();
    }

    @Override
    public void down() {
        sprite.changePoint(sprite.getX(), sprite.getY() + ConfigVars.playerSpeed);
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

    @Override
    public void damage(int d) {
        hp -= d;
        if (hp <= 0) {
            die();
        }
    }

    @Override
    public void die() {
        hp = 0;
        active = false;
    }
}
