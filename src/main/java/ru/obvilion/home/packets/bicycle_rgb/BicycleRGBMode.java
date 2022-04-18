package ru.obvilion.home.packets.bicycle_rgb;

public enum BicycleRGBMode {
    NONE(0),

    // Основные режимы ленты
    CUSTOM(1),             // Кастомная последовательность (до 10 цветов)
    STATIC_RGB(2),         // Один цвет
    FADING_STATIC_RGB(3),  // Один цвет с эффектом затухания
    PULSE(4),              // Пульсирующий один цвет
    RAINBOW(5),            // Радуга с резким изменением цвета
    SMOOTH_RAINBOW(6),     // Переливающаяся радуга
    FADING_RAINBOW(7),     // Переливающаяся радуга с эффектом затухания
    LEFT_RIGHT_RAINBOW(8), // Переход цвета с левого колеса на правое и обратно (радуга)
    FLARE(9),              // Эффект огня, цвет можн настраивать
    BREATHING(10),         // Эффект дыхания
    LEFT_RIGHT(11),        // Переход цвета с ле вого колеса на правое и обратно (один цвет)
    LEFT(12),              // Переход цвета налево
    RIGHT(13),             // Переход цвета направо
    VIRTUALIZER(14),       // Мигание в такт музыки
    SHAKE(15);             // Реагирует на тряску телефона;

    /**
     * Ищет режим по индексу
     * @param index Индекс режима
     * @return Режим с индексом
     */
    public static BicycleRGBMode valueOf(int index) {
        for (BicycleRGBMode mode : BicycleRGBMode.values()) {
            if (mode.getIndex() == index) {
                return mode;
            }
        }

        return NONE;
    }

    // Конструктор енума
    private final int index;

    BicycleRGBMode(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
