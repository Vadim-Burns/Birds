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
    public static int enemySpeed = 10;

    // Количество вражеских птиц
    public static final int enemiesCount = 10;

    // Увеличение скорости врага за уровень
    public static final int deltaEnemySpeed = 5;

    /**
     * Player settings
     */
    // Скорость игрока по Y
    public static final int playerSpeed = 10;

    // Сила гравитации игрока по Y
    public static final int playerGravity = 5;

    // Урон при столкновении для игрока
    public static final int playerIntersectDamage = 500;

    // Количество здоровья игрока
    public static final int playerHp = 1000;

    // Минимальная задержка между выстрелами игрока в мили секундах
    public static final int playerShootDelay = 500;

    /**
     * Kit settings
     */
    // Количество аптечек
    public static final int kitsCount = 1;

    // Время появления аптечек в мили секундах
    public static final int kitDelay = 5000;

    // Скорость движения аптечки по X
    public static final int kitSpeed = 10;

    // Количество восполняемых hp
    public static final int kitHp = 500;

    /**
     * Shooting settings
     */
    // Скорость полета снаряда
    public static final int shootSpeed = 20;

    // Расстояние на котором появляется снаряд от игрока
    public static final int shootMargin = 3;

    /**
     * Level label info
     */
    // Размер шрифта информационной панели
    public static final int levelFontSize = 20;

    // Время прохождения одного уровня в мили секундах
    public static final int levelTime = 10000;

    // Количество очков за попадание по вражеской птице
    public static final int pointsForEnemy = 1;
}
