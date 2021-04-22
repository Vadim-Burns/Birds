package models;

import interfaces.UnExitable;

public abstract class Bird extends Actor implements UnExitable {

    Bird(double x, double y, String filePath) {
        super(
                x,
                y,
                Bird.class.getClassLoader().getResource(filePath).getPath()
        );
    }
}
