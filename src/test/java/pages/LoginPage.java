package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: LoginPage extends BasePage to inherit shared page methods.
public class LoginPage extends BasePage {

    // OOP - Encapsulation: page elements are private and hidden behind public actions.
    private By usernameInput; // TODO: Add locator for username input here
    private By passwordInput; // TODO: Add locator for password input here
    private By rememberMeCheckbox; // TODO: Add locator for remember me checkbox here
    private By loginButton; // TODO: Add locator for login button here
    private By loginHeaderLabel; // TODO: Add locator for login header label here

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        typeText(usernameInput, username, 10);
        return this;
    }

    public LoginPage enterPassword(String password) {
        typeText(passwordInput, password, 10);
        return this;
    }

    public LoginPage clickRememberMe() {
        clickElement(rememberMeCheckbox, 10);
        return this;
    }

    public DashboardPage clickLoginButton() {
        clickElement(loginButton, 10);
        return new DashboardPage(driver);
    }

    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton, 10);
    }

    public boolean isRememberMeSelected() {
        return isElementSelected(rememberMeCheckbox, 10);
    }

    public String getLoginHeaderText() {
        return readText(loginHeaderLabel, 10);
    }

    @Override
    // OOP - Polymorphism: LoginPage overrides the base readiness check with login-page validation.
    public boolean isPageLoaded() {
        return isElementDisplayed(loginHeaderLabel, 10);
    }

    @Override
    public String getPageName() {
        return "Login Page";
    }

    @Override
    // OOP - Polymorphism: LoginPage overrides toString() to describe itself differently at runtime.
    public String toString() {
        return getPageName() + " - " + readText(loginHeaderLabel, 10);
    }
}
