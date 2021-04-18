package models;

import java.awt.event.KeyEvent;

public class KeyBind {
    private final int key;
    private final Runnable function;

    public KeyBind(int key, Runnable function) {
        this.key = key;
        this.function = function;
    }

    public boolean isKeyEquals(KeyEvent keyEvent) {
        return key == keyEvent.getKeyCode();
    }

    public void run() {
        function.run();
    }
}
