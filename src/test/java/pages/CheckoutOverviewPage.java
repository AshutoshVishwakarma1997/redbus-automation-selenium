package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

// OOP - Inheritance: CheckoutOverviewPage extends BasePage for shared reusable actions.
public class CheckoutOverviewPage extends BasePage {

    // OOP - Encapsulation: checkout overview elements remain private inside the page object.
    private By checkoutOverviewContainer = By.xpath("//div[@data-test='checkout-summary-container']"); // TODO: Add locator for SauceDemo checkout overview container here
    private By checkoutItemNameLabels = By.xpath("//*[@data-test='inventory-item-name' or contains(@class,'inventory_item_name')]"); // TODO: Add locator for all product names displayed in SauceDemo checkout overview here
    private By finishButton = By.xpath("//button[@data-test='finish']"); // TODO: Add locator for SauceDemo finish button here

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasProduct(String productName) {
        List<WebElement> productNames = findElements(checkoutItemNameLabels, 10);
        for (WebElement productNameLabel : productNames) {
            String currentProductName = productNameLabel.getText().trim();
            if (currentProductName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public CheckoutCompletePage clickFinish() {
        clickElement(finishButton, 10);
        return new CheckoutCompletePage(driver);
    }

    @Override
    // OOP - Polymorphism: CheckoutOverviewPage overrides the common page-loaded contract for overview state.
    public boolean isPageLoaded() {
        return isElementDisplayed(checkoutOverviewContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Checkout Overview Page";
    }

    @Override
    // OOP - Polymorphism: CheckoutOverviewPage overrides toString() with page-specific output.
    public String toString() {
        return getPageName();
    }
}
