package interfaces;

import models.KeyBind;

import java.awt.event.KeyListener;

public interface BirdKeyListener extends KeyListener {

    void addBind(KeyBind keyBind);

}
