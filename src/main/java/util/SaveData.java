package util;

import java.util.HashMap;

/**
 * Синглтон для сохранения данных
 * для последующей проверки.
 * Сохранению подлежат только те данные,
 * которые отображаются в процессе теста
 * после первого ввода/появления.
 */
public enum SaveData {
    INSTANCE;

    /**
     * Параметры для сохранения.
     */
    public enum Param {
        PASSENGERS,     // Количество пассажиров
        FROMPORT,       // Пункт вылета туда
        FROMMONTH,      // Месяц вылета туда
        FROMDAY,        // День вылета туда
        TOPORT,         // Пункт прилёта туда
        RETURNMONTH,    // Месяц возвращения
        RETURNDAY,      // День возвращения
        SERVICETYPE,    // Тип обслуживания
        DEPARTFLIGHT,   // Авиакомпания и номер рейса вылета
        DEPARTPRICE,    // Цена рейса туда
        DEPARTTIME,     // Время вылета туда
        RETURNFLIGHT,   // Авиакомпания и номер рейса прилёта
        RETURNPRICE,    // Цена рейса обратно
        RETURNTIME,     // Время рейса обратно
        TAXES,          // Налоги
        TOTALPRICE,     // Общая сумма
        BILLINGSTREET,  // Адрес плательщика
        BILLINGSCITY,   // Город плательщика
        BILLINGZIP,     // Индекс плательщика
        DELIVERYSTREET, // Адрес доставки билета
        DELIVERYCITY,   // Город доставки билета
        DELIVERYZIP     // Индекс доставки билета
    }

    /*
     * Словарь тестовых данных
     * для сохранения и последующей проверки
     */
    private HashMap<Param, String> data = new HashMap<Param, String>();

    /**
     * Сохранение значения параметра.
     *
     * @param key   Параметр
     * @param value Значение
     */
    public void saveParameter(final Param key, final String value) {
        data.putIfAbsent(key, value);
    }

    /**
     * Получение значения параметра.
     *
     * @param k Параметр
     * @return Значение
     */
    public String get(final Param k) {
        return data.get(k);
    }
}
