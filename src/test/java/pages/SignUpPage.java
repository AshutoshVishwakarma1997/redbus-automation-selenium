package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: SignUpPage extends BasePage for shared reusable actions.
public class SignUpPage extends BasePage {

    // OOP - Encapsulation: these elements stay private and are used only through public methods.
    private By signUpDialog = By.xpath("//div[@data-autoid='bottom-sheet' and @role='dialog' and @aria-label='Login to get exciting offers']");
    private By signUpDialogTitle = By.xpath("//div[@data-autoid='bottom-sheet']//h2[normalize-space()='Login to get exciting offers']");
    private By mobileNumberInput = By.xpath("//div[@data-autoid='bottom-sheet']//input[@type='tel' and @maxlength='10']");
    private By continueButton = By.xpath("//div[@data-autoid='bottom-sheet']//button[@aria-label='Continue' and not(@type='button')]");
    private By recaptchaFrame = By.xpath("//div[@data-autoid='bottom-sheet']//iframe[@title='reCAPTCHA']");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage enterMobile(String mobileNumber) {
        typeText(mobileNumberInput, mobileNumber, 10);
        return this;
    }

    public SignUpPage clickContinue() {
        clickElement(continueButton, 10);
        return this;
    }

    public boolean isContinueButtonEnabled() {
        return isElementEnabled(continueButton, 10);
    }

    public boolean isCaptchaDisplayed() {
        return isElementDisplayed(recaptchaFrame, 10);
    }

    public String getDialogTitleText() {
        return readText(signUpDialogTitle, 10);
    }

    @Override
    // OOP - Polymorphism: SignUpPage overrides the common page-loaded contract for sign-up state.
    public boolean isPageLoaded() {
        return isElementDisplayed(signUpDialog, 10);
    }

    @Override
    public String getPageName() {
        return "Sign Up Page";
    }

    @Override
    // OOP - Polymorphism: SignUpPage overrides toString() with page-specific output.
    public String toString() {
        return getPageName();
    }
}
