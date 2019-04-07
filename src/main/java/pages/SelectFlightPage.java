package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static util.SaveData.Param.*;

/**
 * Страница выбора рейса.
 */
public final class SelectFlightPage extends Page {

    // Путь к строке с указанием маршрута "туда"
    private static final String DEPART_FLIGHT_XPATH = ".//table//font[text()='DEPART']/"
            + "following::font[contains(text(),' to ')]";
    // Путь к строке с указанием маршрута "обратно"
    private static final String RETURN_FLIGHT_XPATH = ".//table//font[text()='RETURN']/"
            + "following::font[contains(text(),' to ')]";

    // Отображение маршрута "туда"
    @FindBy(xpath = DEPART_FLIGHT_XPATH)
    @CacheLookup
    private WebElement departRoute;

    // Отображение даты "туда"
    @FindBy(xpath = DEPART_FLIGHT_XPATH + "/following::font")
    @CacheLookup
    private WebElement departDate;

    // Отображение маршрута "обратно"
    @FindBy(xpath = RETURN_FLIGHT_XPATH)
    @CacheLookup
    private WebElement returnRoute;

    // Отображение даты "обратно"
    @FindBy(xpath = RETURN_FLIGHT_XPATH + "/following::font")
    @CacheLookup
    private WebElement returnDate;

    // Список радиокнопок вариантов полёта туда
    @FindAll({@FindBy(xpath = ".//input[@type='radio' and @name='outFlight']")})
    @CacheLookup
    private List<WebElement> departRadioButtons;

    // Список радиокнопок вариантов полёта обратно
    @FindAll({@FindBy(xpath = ".//input[@type='radio' and @name='inFlight']")})
    @CacheLookup
    private List<WebElement> returnRadioButtons;

    // Кнопка "продолжить"
    @FindBy(xpath = ".//input[@name='reserveFlights']")
    @CacheLookup
    private WebElement continueButton;

    /**
     * Конструктор.
     * Сначала конструктор родителя, затем инициализация элементов.
     *
     * @param webDriver драйвер
     */
    public SelectFlightPage(final WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Проверка совпадения отображаемых данных с введёнными ранее.
     */
    public void checkData() {
        // Получение текущего года для проверки отображения даты
        final int year = LocalDate.now().getYear();

        // Проверка отображения маршрута туда
        waitForReadinessOf(departRoute);
        final String departFlightText = saveData().get(FROMPORT) + " to " + saveData().get(TOPORT);
        assertThat("Неверные данные рейса 'туда':"
                        + "\nExpected: " + departFlightText
                        + "\nActual: " + departRoute.getText(),
                departRoute.getText().equals(departFlightText));

        // Проверка отображения даты туда
        waitForReadinessOf(departDate);
        final String departDateText = saveData().get(FROMMONTH) + "/" + saveData().get(FROMDAY) + "/" + year;
        assertThat("Неверная дата рейса 'туда':"
                        + "\nExpected: " + departDateText
                        + "\nActual: " + departDate.getText(),
                departDate.getText().equals(departDateText));

        // Проверка отображения маршрута обратно
        waitForReadinessOf(returnRoute);
        final String returnFlightText = saveData().get(TOPORT) + " to "
                + saveData().get(FROMPORT);
        assertThat("Неверные данные рейса 'обратно':"
                        + "\nExpected: " + returnFlightText
                        + "\nActual: " + returnRoute.getText(),
                returnRoute.getText().equals(returnFlightText));

        // Проверка отображения даты обратно
        waitForReadinessOf(returnDate);
        final String returnDateText = saveData().get(RETURNMONTH) + "/" + saveData().get(RETURNDAY) + "/" + year;
        assertThat("Неверная дата рейса 'обратно':"
                        + "\nExpected: " + returnDateText
                        + "\nActual: " + returnDate.getText(),
                returnDate.getText().equals(returnDateText));
    }

    /**
     * Выбор граничных значений.
     * Для рейса туда 1 вариант.
     * Для рейса обратно полсдений вариант.
     * В наборе радиокнопок одна и так должна быть нажата,
     * но всё равно нажимаем.
     */
    public void selectAndSaveBoundaryOptions() {
        // По умолчанию выбраны первые кнопки
        WebElement radioButton = null;
        // массив слов для разделения строк
        String[] words;

        // Для рейса туда выбор 1 варианта
        departRadioButtons.get(0).click();
        /*
         * После выбора варианта проверка,
         * чтобы остальные не были выбраны
         */
        for (int i = 0; i < departRadioButtons.size(); i++) {
            radioButton = departRadioButtons.get(i);
            waitForReadinessOf(radioButton);
            if (i == 0) {
                assertThat("Радиокнопка рейса 'туда' №" + (i + 1)
                        + " должна быть выбрана", radioButton.isSelected());
            } else {
                assertThat("Радиокнопка рейса 'туда' №" + (i + 1)
                        + " НЕ должна быть выбрана", !radioButton.isSelected());
            }
        }

        /*
         * Сохранение данных рейса.
         * Данные рейса содержатся в радикнопке
         * в формате <"Авиакомпания$НомерРейса$Цена$Время>.
         * Делим строку на куски, разделённые символом '$'.
         * Сохраняем рейс, цену и время вылета.
         */
        words = departRadioButtons.get(0).getAttribute("value").split("\\$");
        saveData().saveParameter(DEPARTFLIGHT, words[0] + " " + words[1]);
        saveData().saveParameter(DEPARTPRICE, words[2]);
        saveData().saveParameter(DEPARTTIME, words[3]);

        // Для рейса обратно выбор 1 варианта
        final int lastIndex = returnRadioButtons.size() - 1;
        returnRadioButtons.get(lastIndex).click();
        /*
         * После выбора варианта проверка,
         * чтобы остальные не были выбраны
         */
        for (int j = 0; j <= lastIndex; j++) {
            radioButton = returnRadioButtons.get(j);
            waitForReadinessOf(radioButton);
            if (j == lastIndex) {
                assertThat("Радиокнопка рейса 'обратно' №" + lastIndex
                        + " должна быть выбрана", radioButton.isSelected());
            } else {
                assertThat("Радиокнопка рейса 'обратно' №" + j
                        + " НЕ должна быть выбрана", !radioButton.isSelected());
            }
        }

        /*
         * Сохранение данных рейса.
         * Данные рейса содержатся в радикнопке
         * в формате <"Авиакомпания$НомерРейса$Цена$Время>.
         * Делим строку на куски, разделённые символом '$'.
         * Сохраняем рейс, цену и время вылета.
         */
        words = returnRadioButtons.get(lastIndex).getAttribute("value").split("\\$");
        saveData().saveParameter(RETURNFLIGHT, words[0] + " " + words[1]);
        saveData().saveParameter(RETURNPRICE, words[2]);
        saveData().saveParameter(RETURNTIME, words[3]);

    }

    /**
     * Клик на кнопку "продолжить".
     */
    public void clickContinue() {
        waitForReadinessOf(continueButton);
        continueButton.click();
    }
}
