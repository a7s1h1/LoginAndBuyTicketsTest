package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static util.SaveData.Param.*;

/**
 * Страница подтверждения покупки билета.
 * Элементов для взаимодействия нет, только проверка данных.
 */
public final class ConfirmationPage extends Page {

    // Информация о рейсе туда
    @FindBy(xpath = ".//font[contains(text(),'Depart')]/following::font")
    @CacheLookup
    private WebElement departInfo;

    // Информация о рейсе обратно
    @FindBy(xpath = ".//font[contains(text(),'Return')]/following::font")
    @CacheLookup
    private WebElement returnInfo;

    // Количество пассажиров
    @FindBy(xpath = ".//font//*[contains(text(),'Passengers')]/following::font")
    @CacheLookup
    private WebElement passengersInfo;

    // Сборы
    @FindBy(xpath = ".//font[contains(text(),'Taxes')]/following::font[contains(text(),'USD')]")
    @CacheLookup
    private WebElement taxes;

    // Общая стоимсоть билетов
    @FindBy(xpath = ".//font[contains(text(),'Total') and contains(text(),'Price')]"
            + "/following::font[contains(text(),'USD')]")
    @CacheLookup
    private WebElement totalPrice;

    // Полный адрес плательщика
    @FindBy(xpath = ".//font//*[contains(text(),'Billed')]/following::font")
    @CacheLookup
    private WebElement billedAddress;

    // Полный адрес доставки билетов
    @FindBy(xpath = ".//font//*[contains(text(),'Delivery')]/following::font")
    @CacheLookup
    private WebElement deliveryAddress;

    /**
     * Конструктор.
     * Сначала конструктор родителя, затем инициализация элементов.
     *
     * @param webDriver драйвер
     */
    public ConfirmationPage(final WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    /**
     * Проверка совпадения отображаемых данных с введёнными ранее.
     * Проверяемые поля составные, проверка не по значениям,
     * а по содержанию нужных значений в строке.
     */
    public void checkData() {
        final int year = LocalDate.now().getYear();

        // Проверка информации о рейсе туда
        waitForReadinessOf(departInfo);
        String infoToCheck = departInfo.getText();
        assertContent(saveData().get(FROMPORT) + " to " + saveData().get(TOPORT),
                infoToCheck, "Неверные данные рейса 'туда':");
        assertContent(saveData().get(FROMMONTH) + "/" + saveData().get(FROMDAY) + "/" + year,
                infoToCheck, "Неверная дата рейса 'туда':");
        assertContent(saveData().get(DEPARTTIME), infoToCheck, "Неверное время вылета 'туда':");
        assertContent(saveData().get(DEPARTFLIGHT), infoToCheck,
                "Неверная авиакомпания и номер рейса 'туда':");
        assertContent(saveData().get(SERVICETYPE), infoToCheck,
                "Неверный класс обслуживания 'туда':");
        assertContent(saveData().get(DEPARTPRICE), infoToCheck,
                "Неверная цена рейса 'туда':");

        // Проверка информации о рейсе обратно
        waitForReadinessOf(returnInfo);
        infoToCheck = returnInfo.getText();
        assertContent(saveData().get(TOPORT) + " to " + saveData().get(FROMPORT),
                infoToCheck, "Неверные данные рейса 'туда':");
        assertContent(saveData().get(RETURNMONTH) + "/" + saveData().get(RETURNDAY) + "/" + year,
                infoToCheck, "Неверная дата рейса 'туда':");
        assertContent(saveData().get(RETURNTIME), infoToCheck,
                "Неверное время вылета 'туда':");
        assertContent(saveData().get(RETURNFLIGHT), infoToCheck,
                "Неверная авиакомпания и номер рейса 'туда':");
        assertContent(saveData().get(SERVICETYPE), infoToCheck,
                "Неверный класс обслуживания 'туда':");
        assertContent(saveData().get(RETURNPRICE), infoToCheck,
                "Неверная цена рейса 'туда':");

        // Проверка количества пассажиров
        waitForReadinessOf(passengersInfo);
        // удаляем из строки всё, кроме цифр, и обрезаем пробелы
        final String passengersCount = passengersInfo.getText()
                .replaceAll("[^\\d.]", "").trim();
        assertThat("Неверное количество пассажиров ("
                        + passengersCount + " вместо " + saveData().get(PASSENGERS) + ")",
                passengersCount.equals(saveData().get(PASSENGERS)));

        // Проверка сборов
        waitForReadinessOf(taxes);
        // Удаляем из строки всё, кроме цифр, и обрезаем пробелы
        final String taxesText = taxes.getText().replaceAll("[^\\d.]", "").trim();
        assertThat("Неверные сборы (" + taxesText + " вместо " + saveData().get(TAXES) + ")",
                taxesText.equals(saveData().get(TAXES)));

        // Проверка суммы
        waitForReadinessOf(totalPrice);
        // удаляем из строки всё, кроме цифр, и обрезаем пробелы
        final String totalPriceText = totalPrice.getText().replaceAll("[^\\d.]", "").trim();
        assertThat("Неверная сумма ("
                        + totalPriceText + " вместо " + saveData().get(TOTALPRICE) + ")",
                totalPriceText.equals(saveData().get(TOTALPRICE)));

        // Проверка адреса плательщика
        waitForReadinessOf(billedAddress);
        infoToCheck = billedAddress.getText();
        assertContent(saveData().get(BILLINGSTREET), infoToCheck, "Неверный billed адрес:");
        assertContent(saveData().get(BILLINGSCITY), infoToCheck, "Неверный billed город:");
        assertContent(saveData().get(BILLINGZIP), infoToCheck, "Неверный billed индекс:");

        // Проверка адреса доставки билетов
        waitForReadinessOf(deliveryAddress);
        infoToCheck = deliveryAddress.getText();
        assertContent(saveData().get(DELIVERYSTREET), infoToCheck, "Неверный delivery адрес:");
        assertContent(saveData().get(DELIVERYCITY), infoToCheck, "Неверный delivery город:");
        assertContent(saveData().get(DELIVERYZIP), infoToCheck, "Неверный delivery индекс:");
    }

    /**
     * Проверка содержания строки в составе другой строки
     * и вывод ошибки в случае неудачи.
     *
     * @param expectedContent содержащаяся строка
     * @param fullText        текст, в котором должна содержаться строка
     * @param errorText       текст ошибки
     */
    private void assertContent(final String expectedContent, final String fullText, final String errorText) {
        assertThat(errorText
                        + "\nExpected: " + expectedContent
                        + "\nActual: " + fullText,
                fullText.contains(expectedContent));
    }
}
