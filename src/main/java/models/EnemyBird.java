package models;

public class EnemyBird extends Bird {

    public EnemyBird(double x, double y) {
        super(
                x,
                y,
                EnemyBird.class.getClassLoader().getResource("player.png").getPath()
        );

        respawn();
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        if (s.getX() < -s.getFrameWidth()) {
            respawn();
        }

    }

    private void respawn() {
        s.setY(Math.random() * 500);
        s.setX(2000 + Math.random() * 500);
        s.setAlpha(Math.PI);
    }

    @Override
    public void onIntersects(Actor act) {
        respawn();
    }
}
