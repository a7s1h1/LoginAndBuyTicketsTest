package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница входа в систему.
 */
public final class LoginPage extends Page {

    /*
     * При первом обращении элемент находится по тексту в url
     * и кэшируется
     */
    // Поле ввода имени пользователя
    @FindBy(xpath = ".//input[@name='userName']")
    @CacheLookup
    private WebElement userNameField;

    // Поле ввода пароля
    @FindBy(xpath = ".//input[@name='password']")
    @CacheLookup
    private WebElement passwordField;

    // Кнопка Submit
    @FindBy(xpath = ".//input[@name='login']")
    @CacheLookup
    private WebElement submitButton;

    /**
     * Конструктор.
     * Сначала конструктор родителя, затем инициализация элементов.
     *
     * @param webDriver драйвер
     */
    public LoginPage(final WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Заполнение полей.
     *
     * @param userName имя пользователя
     * @param password пароль
     */
    public void fill(final String userName, final String password) {
        waitForReadinessOf(userNameField);
        clearAndSendKeys(userNameField, userName);
        waitForReadinessOf(passwordField);
        clearAndSendKeys(passwordField, password);
    }

    /**
     * Клик на кнопку submit.
     */
    public void clickSubmit() {
        waitForReadinessOf(submitButton);
        submitButton.click();
    }


}
