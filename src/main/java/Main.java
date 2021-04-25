import config.ConfigVars;
import ui.Window;
import utils.Sound;

public class Main {
    public static void main(String[] args) {
        initEnvVariables();
        Sound.playBackgroundMusic();
        new Window();
    }

    /**
     * Настройка игры по переменным окружения
     */
    private static void initEnvVariables() {
        // GOD_MODE
        if (getEnvVariableOrDefaultValue("GOD_MODE", "OFF").equals("ON")) {
            ConfigVars.playerIntersectDamage = 0;
            System.out.println("GOD_MODE включен\n");
        } else {
            System.out.println("GOD_MODE выключен\n");
        }

        // SHOOT_MODE
        switch (getEnvVariableOrDefaultValue("SHOOT_MODE", "1")) {
            case "2":
                ConfigVars.playerShootDelay = 1000 / 4;
                System.out.println("SHOOT_MODE выставлен на 2\n");
                break;
            case "3":
                ConfigVars.playerShootDelay = 0;
                System.out.println("SHOOT_MODE выставлен на 3\n");
                break;
            default:
                System.out.println("SHOOT_MODE выставлен на 1\n");
        }

        // UPDATE_LEVEL
        if (getEnvVariableOrDefaultValue("UPDATE_LEVEL", "ON").equals("OFF")) {
            ConfigVars.deltaEnemySpeed = 0;
            System.out.println("UPDATE_LEVEL выключен\n");
        } else {
            System.out.println("UPDATE_LEVEL включен\n");
        }

        // ENEMIES_COUNT
        String enemiesCount = System.getenv("ENEMIES_COUNT");
        if (enemiesCount != null) {
            try {
                ConfigVars.enemiesCount = Integer.parseInt(enemiesCount);
                System.out.println("ENEMIES_COUNT выставлен на " + enemiesCount + "\n");
            } catch (NumberFormatException e) {
                System.out.println(
                        "Указано неверное значение для ENEMIES_COUNT.\n" +
                                "Значение выставлено по умолчанию.\n"
                );
            }
        } else {
            System.out.println("ENEMIES_COUNT выставлен по умолчанию\n");
        }

        // KITS_COUNT
        String kitsCount = System.getenv("KITS_COUNT");
        if (kitsCount != null) {
            try {
                ConfigVars.kitsCount = Integer.parseInt(kitsCount);
                System.out.println("KITS_COUNT выставлен на " + kitsCount + "\n");
            } catch (NumberFormatException e) {
                System.out.println(
                        "Указано неверное значение для KITS_COUNT.\n" +
                                "Значение выставлено по умолчанию.\n"
                );
            }
        } else {
            System.out.println("KITS_COUNT выставлен по умолчанию\n");
        }

        // SOUND
        if (getEnvVariableOrDefaultValue("SOUND", "ON").equals("OFF")) {
            ConfigVars.isSoundEnabled = false;
            System.out.println("SOUND выключен");
        } else {
            System.out.println("SOUND включен");
        }
    }

    /**
     * Получение переменной окружение с обходом null.
     * Если переменная окружения не выставлена, то возвращается defaultValue
     */
    private static String getEnvVariableOrDefaultValue(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null ? defaultValue : value;
    }
}