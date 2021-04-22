package config;

public class ConfigVars {

    /**
     * App settings
     */
    // Период между перерисовкой приложения в мили секундах
    public static final int tikRate = 50;

    /**
     * Enemy settings
     */
    // Скорость вражеских птиц по X
    public static final int enemySpeed = 10;

    // Количество вражеских птиц
    public static final int enemiesCount = 0;

    /**
     * Player settings
     */
    // Скорость игрока по Y
    public static final int playerSpeed = 10;

    // Сила гравитации игрока по Y
    public static final int playerGravity = 5;

    // Урон при столкновении для игрока
    public static final int playerIntersectDamage = 30;

    // Количество здоровья игрока
    public static final int playerHp = 1000;
}
