package managers;

import config.ConfigVars;
import interfaces.KitManager;
import models.Kit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KitManagerImpl implements KitManager {

    private final List<Kit> kits = new ArrayList<>();

    public KitManagerImpl() {
        for (int i = 0; i < ConfigVars.kitsCount; i++) {
            kits.add(new Kit());
        }
    }

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
}
