package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // OOP - Abstraction: concrete pages must define how page readiness is validated.
    public abstract boolean isPageLoaded();

    // OOP - Abstraction: concrete pages must define a human-readable page name.
    public abstract String getPageName();

    protected void clickElement(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForClickability(element, timeoutSeconds);
        element.click();
    }

    protected void typeText(WebElement element, String value, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        element.clear();
        element.sendKeys(value);
    }

    protected String readText(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.isDisplayed();
    }

    protected boolean isElementEnabled(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.isEnabled();
    }

    protected boolean isElementSelected(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.isSelected();
    }
}

