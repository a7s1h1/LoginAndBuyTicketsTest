package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.SaveData;

import java.security.InvalidParameterException;

import static org.hamcrest.MatcherAssert.assertThat;
import static util.SaveData.Param.*;

/**
 * Страница поиска рейса.
 */
public final class FindFlightPage extends Page {

    // радиокнопка "туда-обратно"
    @FindBy(xpath = ".//input[@type='radio'][@name='tripType'][@value='roundtrip']")
    @CacheLookup
    private WebElement roundTripRadioButton;

    // радиокнопка "в один конец"
    @FindBy(xpath = ".//input[@type='radio'][@name='tripType'][@value='oneway']")
    @CacheLookup
    private WebElement oneWayRadioButton;

    // listBox кол-во пассажиров (1-4)
    @FindBy(xpath = ".//select[@name='passCount']")
    @CacheLookup
    private WebElement passengersList;

    // listBox пункт отправления
    @FindBy(xpath = ".//select[@name='fromPort']")
    @CacheLookup
    private WebElement fromList;

    // listBox месяц отправления
    @FindBy(xpath = ".//select[@name='fromMonth']")
    @CacheLookup
    private WebElement fromMonthList;

    // listBox число отправления
    @FindBy(xpath = ".//select[@name='fromDay']")
    @CacheLookup
    private WebElement fromDayList;

    // listBox пункт прибытия
    @FindBy(xpath = ".//select[@name='toPort']")
    @CacheLookup
    private WebElement toList;

    // listBox месяц возврата
    @FindBy(xpath = ".//select[@name='toMonth']")
    @CacheLookup
    private WebElement returnMonthList;

    // listBox число возврата
    @FindBy(xpath = ".//select[@name='toDay']")
    @CacheLookup
    private WebElement returnDayList;

    // радиокнопка "эконом класс"
    @FindBy(xpath = ".//input[@type='radio'][@name='servClass'][@value='Coach']")
    @CacheLookup
    private WebElement economyClassRadioButton;

    // радиокнопка "бизнес класс"
    @FindBy(xpath = ".//input[@type='radio'][@name='servClass'][@value='Business']")
    @CacheLookup
    private WebElement businessClassRadioButton;

    // радиокнопка "первый класс"
    @FindBy(xpath = ".//input[@type='radio'][@name='servClass'][@value='First']")
    @CacheLookup
    private WebElement firstClassRadioButton;

    // listBox авиакомпания
    @FindBy(xpath = ".//select[@name='airline']")
    @CacheLookup
    private WebElement airlineList;

    // Кнопка "продолжить"
    @FindBy(xpath = ".//input[@name='findFlights']")
    @CacheLookup
    private WebElement continueButton;

    /**
     * Конструктор.
     * Сначала конструктор родителя, затем инициализация элементов.
     *
     * @param webDriver драйвер
     */
    public FindFlightPage(final WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Заполнение полей.
     * Радиокнопки оставляем первые по умолчанию.
     * В наборе радиокнопок одна и так должна быть нажата,
     * но всё равно нажимаем.
     * <p>
     * В выпадающем списке выбираем
     * первые варианты для вылета
     * и последние для возвращения.
     */
    public void fillAndSaveBoundaryOptions() {
        // по умолчанию RoundTrip
        waitForReadinessOf(roundTripRadioButton);
        roundTripRadioButton.click();
        assertThat("Радиокнопка RoundTrip должна быть выбрана",
                roundTripRadioButton.isSelected());
        waitForReadinessOf(roundTripRadioButton);
        assertThat("Радиокнопка OneWay НЕ должна быть выбрана",
                !oneWayRadioButton.isSelected());

        // Пассажиры: 1 вариант (1)
        selectAndSaveFirstOrLast(passengersList, 0, PASSENGERS);
        // Вылет: 1 пункт в списках
        selectAndSaveFirstOrLast(fromList, 0, FROMPORT);
        selectAndSaveFirstOrLast(fromMonthList, 0, FROMMONTH);
        selectAndSaveFirstOrLast(fromDayList, 0, FROMDAY);
        // Прилёт: последний пункт в списках
        selectAndSaveFirstOrLast(toList, 1, TOPORT);
        selectAndSaveFirstOrLast(returnMonthList, 1, RETURNMONTH);
        selectAndSaveFirstOrLast(returnDayList, 1, RETURNDAY);

        // по умолчанию Economy
        waitForReadinessOf(economyClassRadioButton);
        economyClassRadioButton.click();
        assertThat("Радиокнопка EconomyClass должна быть выбрана",
                economyClassRadioButton.isSelected());
        waitForReadinessOf(businessClassRadioButton);
        assertThat("Радиокнопка BusinessClass НЕ должна быть выбрана",
                !businessClassRadioButton.isSelected());
        waitForReadinessOf(firstClassRadioButton);
        assertThat("Радиокнопка FirstsClass НЕ должна быть выбрана",
                !firstClassRadioButton.isSelected());
        // Сохранение класса обслуживания
        saveData().saveParameter(SERVICETYPE, economyClassRadioButton.getAttribute("value"));

        waitForReadinessOf(airlineList);
        selectFromDropDownListByIndex(airlineList, 0);
    }

    /**
     * Выбор в выпадающем списке первого или последнего пункта,
     * и сохранение выбора.
     *
     * @param dropDownList список
     * @param firstOrLast  0 - первый, 1 - последний
     * @param param        параметр для сохранения
     */
    private void selectAndSaveFirstOrLast(final WebElement dropDownList,
                                          final int firstOrLast,
                                          final SaveData.Param param) {
        if (firstOrLast < 0 || firstOrLast > 1) {
            throw new InvalidParameterException(firstOrLast + " is invalid. Choose 0 for first, 1 for last.");
        }
        waitForReadinessOf(dropDownList);
        Select select = new Select(dropDownList);
        /*
        * Определение индекса для выбора:
        * Находим последний в любом случае,
        * умножаем его на 0, если нужен первый,
        * на 1, если последний
         */
        final int index = (select.getOptions().size() - 1) * firstOrLast;
        selectFromDropDownListByIndex(dropDownList, index);
        saveData().saveParameter(param, dropDownList.getAttribute("value"));
    }

    /**
     * Клик на кнопку "продолжить".
     */
    public void clickContinue() {
        waitForReadinessOf(continueButton);
        continueButton.click();
    }


}
