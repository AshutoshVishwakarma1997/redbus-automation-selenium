package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: HomePage extends BasePage to reuse common browser actions.
public class HomePage extends BasePage {

    // OOP - Encapsulation: page elements are private and only exposed through public methods.
    private By usernameInput = By.xpath("//input[@id='user-name']"); // TODO: Add locator for SauceDemo username input here
    private By passwordInput = By.xpath("//input[@id='password']"); // TODO: Add locator for SauceDemo password input here
    private By loginButton = By.xpath("//input[@id='login-button']"); // TODO: Add locator for SauceDemo login button here
    private By loginLogo = By.xpath("//div[contains(@class,'login_logo')]"); // TODO: Add locator for SauceDemo login page logo or container here
    private By errorMessageLabel = By.xpath("//h3[@data-test='error']"); // TODO: Add locator for SauceDemo login error message here

    public HomePage(WebDriver driver) {
        // super keyword is used to call the constructor of the parent class (BasePage) to initialize the WebDriver instance in the HomePage class, allowing it to use the common methods defined in BasePage for interacting with web elements.
        super(driver);
    }

    public HomePage enterUsername(String username) {
        typeText(usernameInput, username, 10);
        return this;
    }

    public HomePage enterPassword(String password) {
        typeText(passwordInput, password, 10);
        return this;
    }

    public DashboardPage clickLoginButton() {
        clickElement(loginButton, 10);
        return new DashboardPage(driver);
    }

    public DashboardPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton, 10);
    }

    public String getErrorMessageText() {
        return readText(errorMessageLabel, 10);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessageLabel, 10);
    }

    @Override
    // OOP - Polymorphism: HomePage overrides the common readiness check with page-specific behavior.
    public boolean isPageLoaded() {
        return isElementDisplayed(loginLogo, 10);
    }

    @Override
    public String getPageName() {
        return "Home Page";
    }

    @Override
    // OOP - Polymorphism: HomePage overrides toString() to provide a page-specific description.
    public String toString() {
        return getPageName();
    }
}
