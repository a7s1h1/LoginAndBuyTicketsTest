package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * HomePage.
 * Наследуется от абстрактного класса страницы
 */
public final class HomePage extends Page {

    /*
     * При первом обращении элемент находится по тексту в url
     * и кэшируется
     */
    // Кнопка перехода на страницу входа в ситему
    @FindBy(xpath = ".//a[text()='SIGN-ON']")
    @CacheLookup
    private WebElement signOnLink;

    /**
     * Конструктор.
     * Сначала конструктор родителя, затем инициализация элементов.
     *
     * @param webDriver драйвер
     */
    public HomePage(final WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Клик на кнопку перехода
     * на страницу входа в систему.
     */
    public void clickSignOn() {
        waitForReadinessOf(signOnLink);
        signOnLink.click();
    }
}
