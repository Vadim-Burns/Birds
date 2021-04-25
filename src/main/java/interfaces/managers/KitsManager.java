package interfaces.managers;

import models.Kit;

import java.util.List;

public interface KitsManager extends Manager, Spawnable {

    List<Kit> getKits();

}
