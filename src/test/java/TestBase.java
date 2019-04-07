import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import util.EventHandler;

/**
 * Абстрактный класс - основа тестировочного класса.
 * Содержит ссылки на драйвер и обработчик событий.
 */
public abstract class TestBase {

    private EventFiringWebDriver driver;        // Драйвер с обработкой событий
    private EventHandler eventHandler;  // Обработчик событий

    /**
     * Получение ссылки на драйвер
     * дочерними классами.
     * @return driver
     */
    EventFiringWebDriver driver() {
        return driver;
    }

    /**
     * Установка драйвера до начала всех тестов.
     */
    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Перед началом теста.
     * Объявление и регистрация драйвера.
     * Объявление обработчика событий.
     */
    @Before
    public void setUp() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        eventHandler = new EventHandler();
        driver.register(eventHandler);
    }

    /**
     * После каждого теста.
     * ЗАкрываем все страницы.
     */
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
