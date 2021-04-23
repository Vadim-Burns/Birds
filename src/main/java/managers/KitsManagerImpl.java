package managers;

import config.ConfigVars;
import interfaces.KitsManager;
import models.Kit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KitsManagerImpl implements KitsManager {

    private final List<Kit> kits = new ArrayList<>();

    public KitsManagerImpl() {
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
