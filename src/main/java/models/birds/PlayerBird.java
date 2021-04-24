package models.birds;

import config.ConfigVars;
import interfaces.*;
import managers.ShootingManagerImpl;
import ui.InfoPanel;
import utils.EndlessThread;

import java.awt.*;
import java.util.Date;

public class PlayerBird extends Bird implements Movable, UnExitable, Damagable, Curable, Shootable, Hitable {

    private final Color color;
    private final int playerNumber;

    private int hp;
    private Date lastShootTime;

    public PlayerBird(int playerNumber, Color color) {
        super(
                100,
                100,
                "player.png"
        );

        initFrames();

        this.color = color;
        this.playerNumber = playerNumber;
        this.hp = ConfigVars.playerHp;

        startGravityThread();
    }

    private void initFrames() {
        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }

                addFrame(
                        new Point(
                                j * getFrameWidth(),
                                i * getFrameHeight()
                        )
                );
            }
        }
    }

    private void startGravityThread() {
        new EndlessThread(
                ConfigVars.tikRate,
                () -> {
                    changePoint(getX(), getY() + ConfigVars.playerGravity);
                    checkPosition();
                }
        ).start();
    }

    @Override
    public void paint(Graphics g) {
        if (!active) return;

        super.paint(g);

        drawHealthBar(g);
    }

    private void drawHealthBar(Graphics g) {
        g.setColor(color);
        g.fillRect(
                (int) getX(),
                (int) getY() + getFrameHeight(),
                (int) (hp / 100.0 * getFrameWidth()),
                3
        );
    }

    @Override
    public void onIntersects() {
        damage(ConfigVars.playerIntersectDamage);
    }

    @Override
    public void up() {
        changePoint(getX(), getY() - ConfigVars.playerSpeed);
        checkPosition();
    }

    @Override
    public void down() {
        changePoint(getX(), getY() + ConfigVars.playerSpeed);
        checkPosition();
    }

    @Override
    public void checkPosition() {
        if (getY() < 0) {
            changePoint(
                    getX(),
                    0
            );
        } else if (getY() > 700) {
            changePoint(
                    getX(),
                    700
            );
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

    @Override
    public void cure(int c) {
        hp += c;
        if (hp > ConfigVars.playerHp) {
            hp = ConfigVars.playerHp;
        }
    }

    @Override
    public void shoot() {
        if (active && isShootAllowed()) {
            ShootingManagerImpl.getDefaultShootingManager().shoot(
                    getX() + getFrameWidth() + ConfigVars.shootMargin,
                    getY() + getFrameHeight() / 2,
                    this
            );

            lastShootTime = new Date();
        }
    }

    private boolean isShootAllowed() {
        if (lastShootTime == null) return true;
        if (new Date().getTime() - lastShootTime.getTime() >= ConfigVars.playerShootDelay) return true;
        return false;
    }

    @Override
    public void onHit() {
        InfoPanel.getDefaultInfoPanel().addPoints(playerNumber);
    }
}
