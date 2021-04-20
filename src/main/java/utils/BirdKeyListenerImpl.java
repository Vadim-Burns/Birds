package utils;

import interfaces.BirdKeyListener;
import models.KeyBind;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BirdKeyListenerImpl implements BirdKeyListener {

    private final List<KeyBind> binds = new ArrayList<>();

    public BirdKeyListenerImpl() {
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

    @Override
    public void addBind(KeyBind keyBind) {
        binds.add(keyBind);
    }
}
