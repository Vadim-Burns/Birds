package managers;

import config.ConfigVars;
import interfaces.BirdKeyListener;
import interfaces.Intersectable;
import interfaces.managers.PlayersManager;
import models.KeyBind;
import models.Kit;
import models.birds.EnemyBird;
import models.birds.PlayerBird;
import utils.BirdKeyListenerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер для управления птицами игроков
 */
public class PlayersManagerImpl implements PlayersManager {
    private final List<PlayerBird> birds = new ArrayList<>();

    /**
     * Внутри конструктора создаются 2 птицы для двух игроков
     */
    public PlayersManagerImpl() {
        birds.add(
                new PlayerBird(1, Color.RED)
        );

        birds.add(
                new PlayerBird(2, Color.BLUE)
        );
    }

    /**
     * Отрисовка всех птиц игроков
     */
    @Override
    public void paint(Graphics g) {
        for (PlayerBird bird : birds) {
            bird.paint(g);
        }
    }

    /**
     * Проверка всех птиц игроков на столкновения с вражескими птицами или с аптечками.
     * Погибшие птицы будут удалены из менеджера и с поля.
     */
    @Override
    public void checkIntersections(List<? extends Intersectable> intersectables) {
        // Список погибших птиц
        List<PlayerBird> deletedBirds = new ArrayList<>();

        for (PlayerBird playerBird : this.birds) {
            for (Intersectable intersectable : intersectables) {
                if (playerBird.intersects(intersectable)) {

                    // Если встретившийся объект это аптечка, то мы увеличиваем здоровье птицы
                    if (intersectable instanceof Kit) {
                        playerBird.cure(ConfigVars.kitHp);
                        intersectable.onIntersects();

                        continue;
                    }

                    // В случае вражеской птицы наносим урон игроку и проверяем жив ли он после этого
                    if (intersectable instanceof EnemyBird) {
                        playerBird.onIntersects();
                        intersectable.onIntersects();

                        // Проверка на живость игрока
                        if (!playerBird.isActive()) deletedBirds.add(playerBird);

                        continue;
                    }

                }
            }
        }

        // Удаляем мертвых птиц
        deleteBirds(deletedBirds);
    }

    private void deleteBirds(List<PlayerBird> deletedBirds) {
        birds.removeAll(deletedBirds);
    }

    /**
     * Получение списка всех птиц игроков
     */
    @Override
    public List<PlayerBird> getBirds() {
        return birds;
    }

    /**
     * Создание KeyListener клавиатуры для управления первой птицей через стрелочки и пробел для стрельбы.
     */
    @Override
    public BirdKeyListener generateArrowKeyListener() {
        if (!birds.isEmpty()) {
            BirdKeyListenerImpl birdKeyListener = new BirdKeyListenerImpl();
            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_UP,
                            birds.get(0)::up
                    )
            );

            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_DOWN,
                            birds.get(0)::down
                    )
            );

            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_SPACE,
                            birds.get(0)::shoot
                    )
            );

            return birdKeyListener;
        } else {
            System.out.println("Not enough birds!");
            System.out.println("It must be one bird at least for arrow listener");
            return null;
        }
    }

    /**
     * Создание KeyListener клавиатуры для управления второй птицей через WASD и ENTER для стрельбы.
     */
    @Override
    public BirdKeyListener generateWasdKeyListener() {
        if (birds.size() > 1) {
            BirdKeyListenerImpl birdKeyListener = new BirdKeyListenerImpl();
            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_W,
                            birds.get(1)::up
                    )
            );

            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_S,
                            birds.get(1)::down
                    )
            );

            birdKeyListener.addBind(
                    new KeyBind(
                            KeyEvent.VK_ENTER,
                            birds.get(1)::shoot
                    )
            );

            return birdKeyListener;
        } else {
            System.out.println("Not enough birds!");
            System.out.println("It must be two birds at least for wasd listener");
            return null;
        }
    }
}
