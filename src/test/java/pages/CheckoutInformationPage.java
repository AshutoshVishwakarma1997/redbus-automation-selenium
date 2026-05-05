package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: CheckoutInformationPage extends BasePage to share reusable page methods.
public class CheckoutInformationPage extends BasePage {

    // OOP - Encapsulation: checkout information elements stay private within the page object.
    private By checkoutInformationContainer = By.xpath("//div[@data-test='checkout-info-container']"); // TODO: Add locator for SauceDemo checkout information container here
    private By firstNameInput = By.xpath("//input[@data-test='firstName']"); // TODO: Add locator for SauceDemo first name input here
    private By lastNameInput = By.xpath("//input[@data-test='lastName']"); // TODO: Add locator for SauceDemo last name input here
    private By postalCodeInput = By.xpath("//input[@data-test='postalCode']"); // TODO: Add locator for SauceDemo postal code input here
    private By continueButton = By.xpath("//input[@data-test='continue']"); // TODO: Add locator for SauceDemo continue button here

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutInformationPage enterFirstName(String firstName) {
        typeText(firstNameInput, firstName, 10);
        return this;
    }

    public CheckoutInformationPage enterLastName(String lastName) {
        typeText(lastNameInput, lastName, 10);
        return this;
    }

    public CheckoutInformationPage enterPostalCode(String postalCode) {
        typeText(postalCodeInput, postalCode, 10);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        clickElement(continueButton, 10);
        return new CheckoutOverviewPage(driver);
    }

    @Override
    // OOP - Polymorphism: CheckoutInformationPage overrides the page-loaded rule for checkout information.
    public boolean isPageLoaded() {
        return isElementDisplayed(checkoutInformationContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Checkout Information Page";
    }

    @Override
    // OOP - Polymorphism: CheckoutInformationPage overrides toString() with checkout-information-specific text.
    public String toString() {
        return getPageName();
    }
}
