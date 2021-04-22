package managers;

import config.ConfigVars;
import interfaces.Intersectable;
import interfaces.Manager;
import models.Kit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KitManager implements Manager {

    private final List<Kit> kits = new ArrayList<>();

    public KitManager() {
        for (int i = 0; i < ConfigVars.kitsCount; i++) {
            kits.add(new Kit());
        }
    }

    @Override
    public void checkIntersections(List<? extends Intersectable> intersectables) {

    }

    @Override
    public void paint(Graphics g) {
        for (Kit kit : kits) {
            kit.paint(g);
        }
    }
}
