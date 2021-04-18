package models;

import java.awt.*;

public class PlayerBird extends Actor implements Movable {

    public PlayerBird() {
        super(
                100,
                100,
                PlayerBird.class.getClassLoader().getResource("player.png").getPath()
        );

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillRect((int) sprite.getX(), (int) sprite.getY() + sprite.getFrameHeight(), (int) (hp / 100.0 * sprite.getFrameWidth()), 3);
    }

    @Override
    public void update(int ms) {
        super.update(ms);

        if (sprite.getY() < 0) {
            sprite.setY(0);
        } else if (sprite.getY() > 700) {
            sprite.setY(700);
        }
    }

    @Override
    public void onIntersects(Actor act) {
        damage(act.damage);
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
