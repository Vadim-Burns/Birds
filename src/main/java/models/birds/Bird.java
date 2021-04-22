package models.birds;

import interfaces.UnExitable;
import models.Actor;

public abstract class Bird extends Actor implements UnExitable {

    public Bird(double x, double y, String filePath) {
        super(
                x,
                y,
                Bird.class.getClassLoader().getResource(filePath).getPath()
        );
    }
}
