package ui;

import config.ConfigVars;
import utils.EndlessThread;

import javax.swing.*;
import java.awt.*;

public class LevelLabel extends JLabel {
    private int currentLevel = 0;

    public LevelLabel() {
        super();

        setForeground(Color.WHITE);
        setFont(new Font("Calibri", Font.BOLD, ConfigVars.levelFontSize));

        startUpdatingLevelThread();
    }

    private void startUpdatingLevelThread() {
        new EndlessThread(
                ConfigVars.levelTime,
                () -> {
                    setText("Level: " + ++currentLevel);
                }
        ).start();
    }
}
