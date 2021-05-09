package models.birds;

import config.ConfigVars;
import config.CurrentWindowSettings;
import interfaces.UnExitable;
import utils.EndlessThread;

import java.awt.*;

public class EnemyBird extends Bird implements UnExitable {

    public EnemyBird() {
        super(
                0,
                0,
                "enemy.png"
        );

        initFrames();

        respawn();

        startMovementThread();
    }

    private void initFrames() {
        int frame = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--, frame++) {
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

    private void startMovementThread() {
        new EndlessThread(
                ConfigVars.tikRate,
                () -> {
                    changePoint(getX() - ConfigVars.enemySpeed, getY());
                    checkPosition();
                }
        ).start();
    }

    private void respawn() {
        changePoint(
                2000 + Math.random() * 500,
                Math.random() * (CurrentWindowSettings.height - getFrameHeight() - 10)
        );
    }

    @Override
    public void checkPosition() {
        if (getX() < 0) {
            respawn();
        }
    }

    @Override
    public void onIntersects() {
        respawn();
    }
}
