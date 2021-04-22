package models;

import interfaces.Intersectable;

public class Kit extends Actor implements Intersectable {

    public Kit() {
        super(0, 0, "kit.png");
    }

    @Override
    public void onIntersects() {

    }
}
