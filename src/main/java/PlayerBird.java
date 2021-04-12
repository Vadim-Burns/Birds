import java.awt.*;

public class PlayerBird extends Actor {

    @Override
    public void up() {
        super.up();
        Sound.jump();
    }

    @Override
    public void down() {
        super.down();
        Sound.jump();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillRect((int) s.getX(), (int) s.getY() + s.frameHeight, (int) (hp / 100.0 * s.frameWidth), 3);
    }

    @Override
    public void update(int ms) {
        super.update(ms);

        if (s.getY() < 0) {
            s.y = 0;
            down();
        } else if (s.getY() > 700) {
            s.y = 700;
            up();
        }
    }

    PlayerBird(double x, double y) {
        super(x, y, PlayerBird.class.getClassLoader().getResource("player.png").getPath(), new Point(0, 0));
        // Сделать сеттеры для этих полей
        s.frameWidth = 110;
        s.frameHeight = 101;

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                s.addFrame(new Point(j * s.frameWidth, i * s.frameHeight));
            }
        }

        up();
    }

    @Override
    public void onIntersects(Actor act) {
        damage(act.damage);
    }
}
