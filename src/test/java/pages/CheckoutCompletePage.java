package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: CheckoutCompletePage extends BasePage for shared reusable actions.
public class CheckoutCompletePage extends BasePage {

    // OOP - Encapsulation: checkout completion elements remain private inside the page object.
    private By checkoutCompleteContainer = By.xpath("//div[@data-test='checkout-complete-container' or @id='checkout_complete_container' or contains(@class,'checkout_complete_container')]"); // TODO: Add locator for SauceDemo checkout complete container here
    private By completeHeaderLabel = By.xpath("//h2[@data-test='complete-header' or contains(@class,'complete-header') or normalize-space()='Thank you for your order!']"); // TODO: Add locator for SauceDemo order success header here
    private By backHomeButton = By.xpath("//button[@data-test='back-to-products' or @id='back-to-products' or normalize-space()='Back Home']"); // TODO: Add locator for SauceDemo back-home button here

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return readText(completeHeaderLabel, 10);
    }

    public DashboardPage clickBackHome() {
        clickElement(backHomeButton, 10);
        return new DashboardPage(driver);
    }

    @Override
    // OOP - Polymorphism: CheckoutCompletePage overrides the common page-loaded contract for completion state.
    public boolean isPageLoaded() {
        return isElementDisplayed(checkoutCompleteContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Checkout Complete Page";
    }

    @Override
    // OOP - Polymorphism: CheckoutCompletePage overrides toString() with page-specific output.
    public String toString() {
        return getPageName();
    }
}
