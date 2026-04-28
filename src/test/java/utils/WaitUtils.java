package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static void waitForVisibility(WebElement element, int timeoutSeconds) {
        WebDriver driver = extractDriver(element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element, int timeoutSeconds) {
        WebDriver driver = extractDriver(element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForTextToBePresentInElement(WebElement element, String text, int timeoutSeconds) {
        WebDriver driver = extractDriver(element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitForInvisibility(WebElement element, int timeoutSeconds) {
        WebDriver driver = extractDriver(element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    private static WebDriver extractDriver(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("WebElement must not be null. Replace TODO locators before execution.");
        }

        if (!(element instanceof WrapsDriver)) {
            throw new IllegalArgumentException("WebElement must implement WrapsDriver to support WaitUtils.");
        }

        return ((WrapsDriver) element).getWrappedDriver();
    }
}

