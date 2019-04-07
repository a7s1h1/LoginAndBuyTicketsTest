package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.SaveData;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Абстрактный класс - основа для страниц для теста.
 */
abstract class Page {

    // Ссылка на синглтон с хранилищем данных для сохранения
    private final SaveData saveData = SaveData.INSTANCE;
    private WebDriver driver;   // драйвер
    private Select select;      // селектор для выпадающих списков

    /**
     * Конструктор.
     *
     * @param driver драйвер
     */
    Page(final WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Получение ссылки на синглтон
     * хранилища данных для сохранения
     * дочерними классами.
     *
     * @return saveData
     */
    public SaveData saveData() {
        return saveData;
    }

    /**
     * Явное ожидание готовности элемента к взаимодействию.
     * Условия: если доступен и если отображается.
     *
     * @param element элемент для взаимодействия
     */
    void waitForReadinessOf(final WebElement element) {
        // Ожидание 10 секунд с шагом а 100мс
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10, 100);
        /* Анонимная функция выполнения нескольких условий
         * во время ожидания.
         * (параметр -> {тело метода}
         */
        Function<WebDriver, Boolean> allConditionsAreSatisfied = webDriver ->
                element.isEnabled() && element.isDisplayed();
        wait.until(allConditionsAreSatisfied);
    }

    /**
     * Явное ожидание всплывающего окна и его закрытие.
     */
    void waitForAlertAndDismissIt() {
        // Ожидание 10 секунд с шагом а 100мс
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10, 100);
        wait.until(ExpectedConditions.alertIsPresent());
        // После появления окна закрываем его
        driver.switchTo().alert().dismiss();
    }

    /**
     * Очистка поля и введение в него данных.
     *
     * @param element лемент
     * @param keys    текст
     */
    void clearAndSendKeys(final WebElement element, final String keys) {
        element.clear();
        element.sendKeys(keys);
    }

    /**
     * Выбор пункта в выпадающем списке по индексу.
     *
     * @param dropDownList выпадающий список
     * @param index        индекс
     */
    void selectFromDropDownListByIndex(final WebElement dropDownList, final int index) {
        select = new Select(dropDownList);
        assertThat("Множественный выбор недопустим", !select.isMultiple());
        select.selectByIndex(index);
    }

    /**
     * Выбор пункта в выпадающем списке по тексту пункта.
     *
     * @param dropDownList выпадающий список
     * @param text         текст
     */
    void selectFromDropDownListByText(final WebElement dropDownList, final String text) {
        select = new Select(dropDownList);
        assertThat("Множественный выбор недопустим", !select.isMultiple());
        select.selectByVisibleText(text);
    }
}
