package utils;

import models.KeyBind;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyListenerImpl implements KeyListener {

    private final List<KeyBind> binds = new ArrayList<>();

    public KeyListenerImpl() {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        for (KeyBind keyBind : binds) {
            if (keyBind.isKeyEquals(keyEvent)) {
                keyBind.run();
                return;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    public void addBind(KeyBind keyBind) {
        binds.add(keyBind);
    }
}
