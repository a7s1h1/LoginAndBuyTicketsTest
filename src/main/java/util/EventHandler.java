package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Свой обработчик событий.
 */
public final class EventHandler implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(final WebDriver webDriver) {
    }

    @Override
    public void afterAlertAccept(final WebDriver webDriver) {
    }

    @Override
    public void afterAlertDismiss(final WebDriver webDriver) {
    }

    @Override
    public void beforeAlertDismiss(final WebDriver webDriver) {
    }

    @Override
    public void beforeNavigateTo(final String s, final WebDriver webDriver) {
    }

    @Override
    public void afterNavigateTo(final String s, final WebDriver webDriver) {
        System.out.println("Осуществлён переход по адресу: " + s);
    }

    @Override
    public void beforeNavigateBack(final WebDriver webDriver) {
    }

    @Override
    public void afterNavigateBack(final WebDriver webDriver) {
    }

    @Override
    public void beforeNavigateForward(final WebDriver webDriver) {
    }

    @Override
    public void afterNavigateForward(final WebDriver webDriver) {
    }

    @Override
    public void beforeNavigateRefresh(final WebDriver webDriver) {
    }

    @Override
    public void afterNavigateRefresh(final WebDriver webDriver) {
    }

    @Override
    public void beforeFindBy(final By by, final WebElement webElement, final WebDriver webDriver) {
    }

    @Override
    public void afterFindBy(final By by, final WebElement webElement, final WebDriver webDriver) {
    }

    @Override
    public void beforeClickOn(final WebElement webElement, final WebDriver webDriver) {
        // пишем здесь, а не after, потому что потом может пропасть ссылка
        System.out.println(getElementInfo(webElement) + " clicked");
    }

    @Override
    public void afterClickOn(final WebElement webElement, final WebDriver webDriver) {
    }

    @Override
    public void beforeChangeValueOf(final WebElement webElement,
                                    final WebDriver webDriver,
                                    final CharSequence[] charSequences) {
    }

    @Override
    public void afterChangeValueOf(final WebElement webElement,
                                   final WebDriver webDriver,
                                   final CharSequence[] charSequences) {
        System.out.println("Value of element "
                + getElementInfo(webElement)
                + " changed");
    }

    @Override
    public void beforeScript(final String s, final WebDriver webDriver) {
    }

    @Override
    public void afterScript(final String s, final WebDriver webDriver) {
    }

    @Override
    public void beforeSwitchToWindow(final String s, final WebDriver webDriver) {
    }

    @Override
    public void afterSwitchToWindow(final String s, final WebDriver webDriver) {
    }

    @Override
    public void onException(final Throwable throwable, final WebDriver webDriver) {
    }

    private String getElementInfo(final WebElement element) {
        String info = "[tag:" + element.getTagName();

        final String id = element.getAttribute("id");
        if (id != null && !id.isEmpty()) {
            info += " id:" + id;
        }

        final String name = element.getAttribute("name");
        if (name != null && !name.isEmpty()) {
            info += " name:" + name;
        }

        final String value = element.getAttribute("value");
        if (value != null && !value.isEmpty()) {
            info += " value:" + value;
        }

        if (!element.getText().isEmpty()) {
            info += " ('" + element.getText() + "')";
        }

        info += "]";

        return info;
    }
}
