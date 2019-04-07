/**
 * Входные тестовые данные для LoginAndBuyTicketsTest.
 */
final class TestData {

    private TestData() {
    }

    /*
     * Данные для заполнения форм
     */
    static final String USER_NAME = "123";                  // Логин для входа в систему
    static final String PASSWORD = "123";                  // Пароль для входа в систему
    static final String PASSENGER_FIRST_NAME = "Ivan";       // Имя пассажира
    static final String PASSENGER_LAST_NAME = "Ivanov";      // Фамилия пассажира
    static final String BILLING_ADDRESS = "Lenina St. 1";   // Адрес плательщика
    static final String BILLING_CITY = "Ryazan";            // Город плательщика
    static final String BILLING_ZIP = "390000";             // Индекс плательщика
    static final String BILLING_COUNTRY = "RUSSIA";         // Страна плательщика
    static final String DELIVERY_ADDRESS = "Some Street 2"; // Адрес доставки билета
    static final String DELIVERY_CITY = "SomeCity";         // Город доставки билета
    static final String DELIVERY_ZIP = "123456";            // Индекс доставки билета


    // Заголовки страниц (для проверки перехода на нужную страницу)
    static final String HOME_PAGE_TITLE = "Welcome: Mercury Tours";
    static final String LOGIN_PAGE_TITLE = "Sign-on: Mercury Tours";
    static final String FIND_FLIGHT_PAGE_TITLE = "Find a Flight: Mercury Tours:";
    static final String SELECT_FLIGHT_PAGE_TITLE = "Select a Flight: Mercury Tours";
    static final String BOOK_FLIGHT_PAGE_TITLE = "Book a Flight: Mercury Tours";
    static final String CONFIRMATION_PAGE_TITLE = "Flight Confirmation: Mercury Tours";

}
