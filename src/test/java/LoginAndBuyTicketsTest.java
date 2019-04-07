import org.junit.Before;
import org.junit.Test;
import pages.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Тест входа в систему и покупки билетов.
 */
public class LoginAndBuyTicketsTest extends TestBase {

    /*
     * Страницы, участвующие в тесте
     */
    private HomePage homePage;
    private LoginPage loginPage;
    private FindFlightPage findFlightPage;
    private SelectFlightPage selectFlightPage;
    private BookFlightPage bookFlightPage;
    private ConfirmationPage confirmationPage;

    /**
     * Условия начала сценария:
     * - открыта домашняя страница;
     * - пользователь не вошёл в систему.
     */
    @Override
    @Before
    public void setUp() {
        super.setUp();
        // Переход на старт
        final String startPageUrl = "http://newtours.demoaut.com";
        driver().get(startPageUrl);
    }

    /**
     * Шаги теста.
     * Каждый шаг связан с соответствующей страницей.
     */
    @Test
    public void testSteps() {
        clickSignOnAtHomePage(); // HomePage: клик на ссылку входа в систему
        login();        // LoginPage: вход в систему
        findFlight();   // FindFlightPage: поиск рейса
        selectFlight(); // SelectFlightPage: выбор рейса
        bookFlights();  // BookFlightPage: покупка билетов
        checkTickets(); // ConfirmationPage: gроверка билетов
    }

    /**
     * На домашней странице клик на ссылку входа в систему.
     */
    private void clickSignOnAtHomePage() {
        homePage = new HomePage(driver());
        // Проверка перехода на страницу
        testCurrentPage(TestData.HOME_PAGE_TITLE);
        // Клик на кнопку перехода на страницу входа в систему
        homePage.clickSignOn();
    }

    /**
     * На странице входа в систему
     * заполнение формы и клик кнопки submit.
     */
    private void login() {
        loginPage = new LoginPage(driver());
        // Проверка перехода на страницу
        testCurrentPage(TestData.LOGIN_PAGE_TITLE);
        // Ввод имени пользователя и пароля
        loginPage.fill(TestData.USER_NAME, TestData.PASSWORD);
        // Клик на кнопку входа в систему
        loginPage.clickSubmit();
    }

    /**
     * На странице поиска рейса
     * заполнение полей и клик кнопки "продолжить".
     */
    private void findFlight() {
        findFlightPage = new FindFlightPage(driver());
        // Проверка перехода на страницу
        testCurrentPage(TestData.FIND_FLIGHT_PAGE_TITLE);
        // Заполнение граничными значениями, сохранение выбора
        findFlightPage.fillAndSaveBoundaryOptions();
        // Клик на кнопку "продолжить"
        findFlightPage.clickContinue();
    }

    /**
     * На странице выбора рейса
     * проверка данных и выбор рейсов.
     */
    private void selectFlight() {
        selectFlightPage = new SelectFlightPage(driver());
        // Проверка перехода на страницу
        testCurrentPage(TestData.SELECT_FLIGHT_PAGE_TITLE);
        // Проверка отображения данных
        selectFlightPage.checkData();
        // Выбор граничных рейсов и сохранение выбора
        selectFlightPage.selectAndSaveBoundaryOptions();
        // Клик на кнопку "продолжить"
        selectFlightPage.clickContinue();
    }

    /**
     * На странице покупки рейса
     * проверка данных и заполнение формы.
     */
    private void bookFlights() {
        bookFlightPage = new BookFlightPage(driver());
        // Проверка перехода на страницу
        testCurrentPage(TestData.BOOK_FLIGHT_PAGE_TITLE);
        // Проверка отображения данных
        bookFlightPage.checkData();
        // Заполнение формы и сохранение данных
        bookFlightPage.fillAndSaveForm(
                TestData.PASSENGER_FIRST_NAME,
                TestData.PASSENGER_LAST_NAME,
                TestData.BILLING_ADDRESS,
                TestData.BILLING_CITY,
                TestData.BILLING_ZIP,
                TestData.BILLING_COUNTRY,
                TestData.DELIVERY_ADDRESS,
                TestData.DELIVERY_CITY,
                TestData.DELIVERY_ZIP);
        // Клик на кнопку покупки
        bookFlightPage.clickBuy();
    }

    /**
     * На странице пподтверждения
     * проверка данных и заполнение формы.
     */
    private void checkTickets() {
        confirmationPage = new ConfirmationPage(driver());
        // Проверка перехода на страницу
        testCurrentPage(TestData.CONFIRMATION_PAGE_TITLE);
        // Проверка отображения данных
        confirmationPage.checkData();
    }

    /**
     * Проверка перехода на страницу.
     * Проводится после каждого перехода.
     *
     * @param title ожидаемый заголовок страницы.
     */
    private void testCurrentPage(final String title) {
        assertThat("Неверный заголовок страницы!"
                        + " Expected: " + title
                        + ", actual:" + driver().getTitle(),
                driver().getTitle().equals(title));
    }
}
