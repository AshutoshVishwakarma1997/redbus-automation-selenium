package base;

import org.openqa.selenium.By;
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

    protected WebElement findElement(By locator, int timeoutSeconds) {
        WaitUtils.waitForVisibility(driver, locator, timeoutSeconds);
        return driver.findElement(locator);
    }

    protected void clickElement(By locator, int timeoutSeconds) {
        WaitUtils.waitForClickability(driver, locator, timeoutSeconds);
        driver.findElement(locator).click();
    }

    protected void clickElement(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForClickability(element, timeoutSeconds);
        element.click();
    }

    protected void typeText(By locator, String value, int timeoutSeconds) {
        WebElement element = findElement(locator, timeoutSeconds);
        element.clear();
        element.sendKeys(value);
    }

    protected void typeText(WebElement element, String value, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        element.clear();
        element.sendKeys(value);
    }

    protected String readText(By locator, int timeoutSeconds) {
        return findElement(locator, timeoutSeconds).getText();
    }

    protected String readText(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.getText();
    }

    protected boolean isElementDisplayed(By locator, int timeoutSeconds) {
        return findElement(locator, timeoutSeconds).isDisplayed();
    }

    protected boolean isElementDisplayed(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.isDisplayed();
    }

    protected boolean isElementEnabled(By locator, int timeoutSeconds) {
        return findElement(locator, timeoutSeconds).isEnabled();
    }

    protected boolean isElementEnabled(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.isEnabled();
    }

    protected boolean isElementSelected(By locator, int timeoutSeconds) {
        return findElement(locator, timeoutSeconds).isSelected();
    }

    protected boolean isElementSelected(WebElement element, int timeoutSeconds) {
        WaitUtils.waitForVisibility(element, timeoutSeconds);
        return element.isSelected();
    }
}
