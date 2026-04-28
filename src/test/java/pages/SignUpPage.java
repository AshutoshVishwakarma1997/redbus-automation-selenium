package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;
import utils.WaitUtils;

// OOP - Inheritance: SignUpPage extends BasePage for shared reusable actions.
public class SignUpPage extends BasePage {

    // OOP - Encapsulation: these elements stay private and are used only through public methods.
    private WebElement nameInput; // TODO: Add locator for name input here
    private WebElement emailInput; // TODO: Add locator for email input here
    private WebElement mobileInput; // TODO: Add locator for mobile input here
    private WebElement passwordInput; // TODO: Add locator for password input here
    private WebElement registerButton; // TODO: Add locator for register button here
    private WebElement successMessageLabel; // TODO: Add locator for account creation success message here
    private WebElement signUpFormTitle; // TODO: Add locator for sign up form title here

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage enterName(String fullName) {
        typeText(nameInput, fullName, 10);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        typeText(emailInput, email, 10);
        return this;
    }

    public SignUpPage enterMobile(String mobileNumber) {
        typeText(mobileInput, mobileNumber, 10);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        typeText(passwordInput, password, 10);
        return this;
    }

    public SignUpPage clickRegister() {
        clickElement(registerButton, 10);
        return this;
    }

    public boolean isRegisterButtonEnabled() {
        return isElementEnabled(registerButton, 10);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessageLabel, 10);
    }

    public String getSuccessMessageText() {
        return readText(successMessageLabel, 10);
    }

    @Override
    // OOP - Polymorphism: SignUpPage overrides the common page-loaded contract for sign-up state.
    public boolean isPageLoaded() {
        return isElementDisplayed(signUpFormTitle, 10);
    }

    @Override
    public String getPageName() {
        return "Sign Up Page";
    }

    @Override
    // OOP - Polymorphism: SignUpPage overrides toString() with page-specific output.
    public String toString() {
        WaitUtils.waitForVisibility(signUpFormTitle, 10);
        return getPageName();
    }
}

