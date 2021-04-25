package managers;

import config.ConfigVars;
import interfaces.managers.KitsManager;
import interfaces.managers.Spawnable;
import models.Kit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KitsManagerImpl implements KitsManager, Spawnable {

    private final List<Kit> kits = new ArrayList<>();
    private boolean spawned = false;

    @Override
    public void paint(Graphics g) {
        for (Kit kit : kits) {
            kit.paint(g);
        }
    }

    @Override
    public List<Kit> getKits() {
        return kits;
    }

    @Override
    public void spawn() {
        if (!spawned) {
            for (int i = 0; i < ConfigVars.kitsCount; i++) {
                kits.add(new Kit());
            }
            spawned = true;
        }
    }
}
