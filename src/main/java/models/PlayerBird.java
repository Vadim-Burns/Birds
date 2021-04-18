package models;

import utils.Sound;

import java.awt.*;

public class PlayerBird extends Bird {

    @Override
    public void up() {
        super.up();
        Sound.playJumpSound();
    }

    @Override
    public void down() {
        super.down();
        Sound.playJumpSound();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillRect((int) s.getX(), (int) s.getY() + s.getFrameHeight(), (int) (hp / 100.0 * s.getFrameWidth()), 3);
    }

    @Override
    public void update(int ms) {
        super.update(ms);

        if (s.getY() < 0) {
            s.setY(0);
            down();
        } else if (s.getY() > 700) {
            s.setY(700);
            up();
        }
    }

    public PlayerBird() {
        super(
                100,
                100,
                PlayerBird.class.getClassLoader().getResource("player.png").getPath()
        );

        s.setFrameWidth(110);
        s.setFrameHeight(101);

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                s.addFrame(new Point(j * s.getFrameWidth(), i * s.getFrameHeight()));
            }
        }

        up();
    }

    @Override
    public void onIntersects(Actor act) {
        damage(act.damage);
    }
}
