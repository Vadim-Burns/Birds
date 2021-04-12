import java.awt.*;

public class EnemyBird extends Actor  {

    EnemyBird(double x, double y) {
        super(x, y, EnemyBird.class.getClassLoader().getResource("player.png").getPath(), new Point(0, 0), 5);
        // Сделать сеттеры для этих полей
        s.frameWidth = 110;
        s.frameHeight = 101;

        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++, frame++) {
                if (frame == 0 || frame == 14) {
                    continue;
                }
                s.addFrame( new Point(j * s.frameWidth, i * s.frameHeight)  )   ;

            }

        }

       respawn();
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        if (s.getX() < -s.frameWidth ) {
            respawn();
        }

    }

    private void respawn() {
        s.y = Math.random() * 500;
        s.x = 2000 + Math.random() * 500;
        left();
    }

    @Override
    public void onIntersects(Actor act) {
        respawn();
    }
}
