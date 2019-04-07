package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static util.SaveData.Param.*;

/**
 * Страница покупки билета.
 */
public final class BookFlightPage extends Page {

    // Путь к сводной таблице с информацией о рейсе
    private static final String SUMMARY_TABLE_XPATH = ".//table//font[contains(text(),'Summary')]/following::table";

    /*
     * SUMMARY
     */
    // Отображение маршрута "туда"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[1]/td[1]//font")
    @CacheLookup
    private WebElement departRoute;

    // Отображение даты "туда"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[1]/td[2]//font")
    @CacheLookup
    private WebElement departDate;

    // Отображение рейса "туда"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[3]/td[1]//font")
    @CacheLookup
    private WebElement departFlight;

    // Отображение класса обслуживания "туда"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[3]/td[2]//font")
    @CacheLookup
    private WebElement departClass;

    // Отображение цены "туда"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[3]/td[3]//font")
    @CacheLookup
    private WebElement departPrice;

    // Отображение маршрута "обратно"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[4]/td[1]//font")
    @CacheLookup
    private WebElement returnRoute;

    // Отображение даты "обратно"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[4]/td[2]//font")
    @CacheLookup
    private WebElement returnDate;

    // Отображение рейса "обратно"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[6]/td[1]//font")
    @CacheLookup
    private WebElement returnFlight;

    // Отображение класса обслуживания "обратно"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[6]/td[2]//font")
    @CacheLookup
    private WebElement returnClass;

    // Отображение цены "обратно"
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[6]/td[3]//font")
    @CacheLookup
    private WebElement returnPrice;

    // Отображение количества пассажиров
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[7]/td[2]//font")
    @CacheLookup
    private WebElement passengers;

    // Отображение суммы сборов
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[8]/td[2]//font")
    @CacheLookup
    private WebElement taxes;

    // Отображение суммы
    @FindBy(xpath = SUMMARY_TABLE_XPATH + "//tr[9]/td[2]//font")
    @CacheLookup
    private WebElement sum;

    // Формы для ввода данных
    /*
     * PASSENGERS
     */
    // Поле для ввода имени пассажира
    @FindBy(xpath = ".//input[@name='passFirst0']")
    @CacheLookup
    private WebElement firstNameField;

    // Поле для ввода фамилии пассажира
    @FindBy(xpath = ".//input[@name='passLast0']")
    @CacheLookup
    private WebElement lastNameField;

    // Список для выбора питания
    @FindBy(xpath = ".//select[@name='pass.0.meal']")
    @CacheLookup
    private WebElement mealList;

    /*
     * CREDIT CARD
     */
    // Список для выбора типа карты
    @FindBy(xpath = ".//select[@name='creditCard']")
    @CacheLookup
    private WebElement cardTypeList;

    // Поле для ввода номера карты
    @FindBy(xpath = ".//input[@name='creditnumber']")
    @CacheLookup
    private WebElement cardNumberField;

    // Список для выбора месяца срока действия карты
    @FindBy(xpath = ".//select[@name='cc_exp_dt_mn']")
    @CacheLookup
    private WebElement cardExpMonthList;

    // Список для выбора года срока действия карты
    @FindBy(xpath = ".//select[@name='cc_exp_dt_yr']")
    @CacheLookup
    private WebElement cardExpYearList;

    // Поле для ввода имени держателя карты
    @FindBy(xpath = ".//input[@name='cc_frst_name']")
    @CacheLookup
    private WebElement cardNameField;

    // Поле для ввода отчества держателя карты
    @FindBy(xpath = ".//input[@name='cc_mid_name']")
    @CacheLookup
    private WebElement cardMiddleField;

    // Поле для ввода фамилии держателя карты
    @FindBy(xpath = ".//input[@name='cc_last_name']")
    @CacheLookup
    private WebElement cardLastField;

    /*
     * ЧЕКБОКСЫ
     * У чекбоксов одинаковые имена,
     * поэтому идентификация по тексту
     */
    // Ticketless Travel
    @FindBy(xpath = ".//font[contains(text(),'Ticketless')]/preceding-sibling::input[@type='checkbox']")
    @CacheLookup
    private WebElement ticketLessCheckBox;

    // Same As Billing Address
    @FindBy(xpath = ".//font[contains(text(),'Billing Address')]/preceding-sibling::input[@type='checkbox']")
    @CacheLookup
    private WebElement sameAsBillingAddressCheckBox;

    /*
     * АДРЕСА
     */
    // Billing Address01
    @FindBy(xpath = ".//input[@name='billAddress1']")
    @CacheLookup
    private WebElement billingAddress01Field;

    // Billing Address02
    @FindBy(xpath = ".//input[@name='billAddress2']")
    @CacheLookup
    private WebElement billingAddress02Field;

    // Billing City
    @FindBy(xpath = ".//input[@name='billCity']")
    @CacheLookup
    private WebElement billingCityField;

    // Billing State
    @FindBy(xpath = ".//input[@name='billState']")
    @CacheLookup
    private WebElement billingStateField;

    // Billing Zip
    @FindBy(xpath = ".//input[@name='billZip']")
    @CacheLookup
    private WebElement billingZipField;

    // Billing Country
    @FindBy(xpath = ".//select[@name='billCountry']")
    @CacheLookup
    private WebElement billingCountryList;

    // Delivery Address01
    @FindBy(xpath = ".//input[@name='delAddress1']")
    @CacheLookup
    private WebElement deliveryAddress01Field;

    // Delivery Address02
    @FindBy(xpath = ".//input[@name='delAddress2']")
    @CacheLookup
    private WebElement deliveryAddress02Field;

    // Delivery City
    @FindBy(xpath = ".//input[@name='delCity']")
    @CacheLookup
    private WebElement deliveryCityField;

    // Delivery State
    @FindBy(xpath = ".//input[@name='delState']")
    @CacheLookup
    private WebElement deliveryStateField;

    // Delivery Zip
    @FindBy(xpath = ".//input[@name='delZip']")
    @CacheLookup
    private WebElement deliveryZipField;

    // Delivery Country
    @FindBy(xpath = ".//select[@name='delCountry']")
    @CacheLookup
    private WebElement deliveryCountryList;

    // Кнопка подтверждения покупки
    @FindBy(xpath = ".//input[@name='buyFlights']")
    @CacheLookup
    private WebElement buyButton;

    /**
     * Конструктор.
     * Сначала конструктор родителя, затем инициализация элементов.
     *
     * @param webDriver драйвер
     */
    public BookFlightPage(final WebDriver webDriver) {
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
        final String departRouteText = saveData().get(FROMPORT) + " to " + saveData().get(TOPORT);
        assertThat("Неверные данные рейса 'туда':"
                        + "\nExpected: " + departRouteText
                        + "\nActual: " + departRoute.getText(),
                departRoute.getText().equals(departRouteText));

        // Проверка отображения даты туда
        waitForReadinessOf(departDate);
        final String departDateText = saveData().get(FROMMONTH) + "/" + saveData().get(FROMDAY) + "/" + year;
        assertThat("Неверная дата рейса 'туда':"
                        + "\nExpected: " + departDateText
                        + "\nActual: " + departDate.getText(),
                departDate.getText().equals(departDateText));

        // Проверка отображения рейса туда
        waitForReadinessOf(departFlight);
        assertThat("Неверная авиакомпания и номер рейса 'туда':"
                        + "\nExpected: " + saveData().get(DEPARTFLIGHT)
                        + "\nActual: " + departFlight.getText(),
                departFlight.getText().equals(saveData().get(DEPARTFLIGHT)));

        // Проверка отображения класса обслуживания туда
        waitForReadinessOf(departClass);
        assertThat("Неверный класс обслуживания 'туда':"
                        + "\nExpected: " + saveData().get(SERVICETYPE)
                        + "\nActual: " + departClass.getText(),
                departClass.getText().equals(saveData().get(SERVICETYPE)));

        // Проверка отображения цены билета туда
        waitForReadinessOf(departPrice);
        assertThat("Неверная цена рейса 'туда':"
                        + "\nExpected: " + saveData().get(DEPARTPRICE)
                        + "\nActual: " + departPrice.getText(),
                departPrice.getText().equals(saveData().get(DEPARTPRICE)));

        // Проверка отображения маршрута обратно
        waitForReadinessOf(returnRoute);
        final String returnRouteText = saveData().get(TOPORT) + " to " + saveData().get(FROMPORT);
        assertThat("Неверные данные рейса 'обратно':"
                        + "\nExpected: " + returnRouteText
                        + "\nActual: " + returnRoute.getText(),
                returnRoute.getText().equals(returnRouteText));

        // Проверка отображения даты обратно
        waitForReadinessOf(returnDate);
        final String returnDateText = saveData().get(RETURNMONTH) + "/" + saveData().get(RETURNDAY) + "/" + year;
        assertThat("Неверная дата рейса 'обратно':"
                        + "\nExpected: " + returnDateText
                        + "\nActual: " + returnDate.getText(),
                returnDate.getText().equals(returnDateText));

        // Проверка отображения рейса обратно
        waitForReadinessOf(returnFlight);
        assertThat("Неверная авиакомпания и номер рейса 'обратно':"
                        + "\nExpected: " + saveData().get(RETURNFLIGHT)
                        + "\nActual: " + returnFlight.getText(),
                returnFlight.getText().equals(saveData().get(RETURNFLIGHT)));

        // Проверка отображения класса обслуживания обратно
        waitForReadinessOf(returnClass);
        assertThat("Неверный класс обслуживания 'обратно':"
                        + "\nExpected: " + saveData().get(SERVICETYPE)
                        + "\nActual: " + returnClass.getText(),
                returnClass.getText().equals(saveData().get(SERVICETYPE)));

        // Проверка отображения цены билета обратно
        waitForReadinessOf(returnPrice);
        assertThat("Неверная цена рейса 'обратно':"
                        + "\nExpected: " + saveData().get(RETURNPRICE)
                        + "\nActual: " + returnPrice.getText(),
                returnPrice.getText().equals(saveData().get(RETURNPRICE)));

        // Проверка отображения количества пассажиров
        waitForReadinessOf(passengers);
        assertThat("Неверное количество пассажиров:"
                        + "\nExpected: " + saveData().get(PASSENGERS)
                        + "\nActual: " + passengers.getText(),
                passengers.getText().equals(saveData().get(PASSENGERS)));

        // Сохранение сборов
        waitForReadinessOf(taxes);
        // Из строки со сборами убираем симовол '$'
        final String taxesText = taxes.getText().replace("$", "");
        saveData().saveParameter(TAXES, taxesText);

        /*
         * Подсчёт стоимости билетов:
         * Сумма = Билет туда + билет обратно + сборы
         */
        final double taxesNum = Double.valueOf(taxesText);
        final double departPriceDouble = Double.valueOf(saveData().get(DEPARTPRICE));
        final double returnPriceDouble = Double.valueOf(saveData().get(RETURNPRICE));
        final double expectedSum = departPriceDouble + returnPriceDouble + taxesNum;

        waitForReadinessOf(sum);
        // Из строки с суммой убираем симовол '$'
        final String totalPrice = sum.getText().replace("$", "");
        // Сохранение стоимости билетов
        saveData().saveParameter(TOTALPRICE, totalPrice);
        final double actualSum = Double.valueOf(totalPrice);

        final double accuracy = .001d;
        // Проверка суммы на погрешность
        assertThat("Неверная сумма:"
                        + "\nExpected: " + expectedSum
                        + "\nActual: " + actualSum,
                Math.abs(expectedSum - actualSum) < accuracy);
    }

    /**
     * Заполненеи формы указанными значениями.
     * Перед введение значения любое поле сначала очищается.
     * В списках выбираются первые значения, если не указано иное.
     * Сохраняются только те параметры,
     * которые потом будут проверяться.
     *
     * @param passengerFirstName Имя пассажира
     * @param passengerLastName  Фамилия пассажира
     * @param billingAddress     Адрес плательщика
     * @param billingCity        Город плательщика
     * @param billingZip         Индекс плательщика
     * @param billingCountry     Страна плательщика
     * @param deliveryAddress    Адрес доставки билета
     * @param deliveryCity       Город доставки билета
     * @param deliveryZip        Индекс доставки билета
     */
    public void fillAndSaveForm(final String passengerFirstName,
                                final String passengerLastName,
                                final String billingAddress,
                                final String billingCity,
                                final String billingZip,
                                final String billingCountry,
                                final String deliveryAddress,
                                final String deliveryCity,
                                final String deliveryZip) {
        // PASSENGERS
        // Имя пассажира
        waitForReadinessOf(firstNameField);
        clearAndSendKeys(firstNameField, passengerFirstName);
        // Фамилия пассажира
        waitForReadinessOf(lastNameField);
        clearAndSendKeys(lastNameField, passengerLastName);
        // Питание (первый вариант)
        waitForReadinessOf(mealList);
        selectFromDropDownListByIndex(mealList, 0);

        // CREDIT CARD
        // Тип карты (первый вариант)
        waitForReadinessOf(cardTypeList);
        selectFromDropDownListByIndex(cardTypeList, 0);
        // Номер карты
        waitForReadinessOf(cardNumberField);
        cardNumberField.clear();
        // Месяц срока действия карты (первый вариант)
        waitForReadinessOf(cardExpMonthList);
        selectFromDropDownListByIndex(cardExpMonthList, 0);
        // Год срока действия карты (первый вариант)
        waitForReadinessOf(cardExpYearList);
        selectFromDropDownListByIndex(cardExpYearList, 0);
        // Имя держателя карты
        waitForReadinessOf(cardNameField);
        cardNameField.clear();
        // Отчество держателя карты
        waitForReadinessOf(cardMiddleField);
        cardMiddleField.clear();
        // Фамилия держателя карты
        waitForReadinessOf(cardLastField);
        cardLastField.clear();

        // Чекбокс полёта без билета
        waitForReadinessOf(ticketLessCheckBox);
        // Клик, если не выбран
        if (!ticketLessCheckBox.isSelected()) {
            ticketLessCheckBox.click();
        }
        assertThat("Чекбокс 'Ticketless' должен быть отмечен",
                ticketLessCheckBox.isSelected());

        // Чекбокс совпадения адресов
        waitForReadinessOf(sameAsBillingAddressCheckBox);
        // Клик, если не выбран
        if (!sameAsBillingAddressCheckBox.isSelected()) {
            sameAsBillingAddressCheckBox.click();
        }
        assertThat("Чекбокс 'Same as Billing Address' должен быть отмечен",
                sameAsBillingAddressCheckBox.isSelected());

        /*
         * Ввод и сохранение адресов
         */
        // Billing Address 01
        waitForReadinessOf(billingAddress01Field);
        clearAndSendKeys(billingAddress01Field, billingAddress);
        saveData().saveParameter(BILLINGSTREET, billingAddress);
        // Billing Address 02 - оставляем пустым и не сохраняем
        waitForReadinessOf(billingAddress02Field);
        billingAddress02Field.clear();
        // Billing City
        waitForReadinessOf(billingCityField);
        clearAndSendKeys(billingCityField, billingCity);
        saveData().saveParameter(BILLINGSCITY, billingCity);
        // Billing State - оставляем пустым и не сохраняем
        waitForReadinessOf(billingStateField);
        billingStateField.clear();
        // Billing Zip
        waitForReadinessOf(billingZipField);
        clearAndSendKeys(billingZipField, billingZip);
        saveData().saveParameter(BILLINGZIP, billingZip);
        // Billing Country
        waitForReadinessOf(billingCountryList);
        selectFromDropDownListByText(billingCountryList, billingCountry);

        // Delivery Address 01
        waitForReadinessOf(deliveryAddress01Field);
        clearAndSendKeys(deliveryAddress01Field, deliveryAddress);
        saveData().saveParameter(DELIVERYSTREET, deliveryAddress);
        // Delivery Address 02 - оставляем пустым и не сохраняем
        waitForReadinessOf(deliveryAddress02Field);
        deliveryAddress02Field.clear();
        // Delivery City
        waitForReadinessOf(deliveryCityField);
        clearAndSendKeys(deliveryCityField, deliveryCity);
        saveData().saveParameter(DELIVERYCITY, deliveryCity);
        // Delivery State - оставляем пустым и не сохраняем
        waitForReadinessOf(deliveryStateField);
        deliveryStateField.clear();
        // Delivery Zip
        waitForReadinessOf(deliveryZipField);
        clearAndSendKeys(deliveryZipField, deliveryZip);
        saveData().saveParameter(DELIVERYZIP, deliveryZip);
        /*
         * Delivery Country - выбираем перую страну в списке
         */
        waitForReadinessOf(deliveryCountryList);
        Select select = new Select(deliveryCountryList);
        /*
         * После установки страны, отличной от US,
         * выскакиваем сообщение о доп. сборах.
         * При выборе первого пункта, если это не US,
         * ловим сообщение и выключаем.
         * Если первый пункт US, оставляем, как есть
         */
        if (!select.getOptions().get(0).getText().contains("UNITED STATES")) {
            selectFromDropDownListByIndex(deliveryCountryList, 0);
            waitForAlertAndDismissIt();
        }
    }

    /**
     * Клик на кнопку подтверждения покупки.
     */
    public void clickBuy() {
        waitForReadinessOf(buyButton);
        buyButton.click();
    }
}
