package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

// OOP - Inheritance: CartPage extends BasePage to reuse shared functionality.
public class CartPage extends BasePage {

    // OOP - Encapsulation: cart elements are private within the page object.
    private By cartContainer = By.xpath("//div[@id='cart_contents_container']"); // TODO: Add locator for SauceDemo cart container here
    private By cartItemNameLabels = By.xpath("//*[@data-test='inventory-item-name' or contains(@class,'inventory_item_name')]"); // TODO: Add locator for all product names displayed in the SauceDemo cart here
    private By checkoutButton = By.xpath("//button[@data-test='checkout']"); // TODO: Add locator for SauceDemo checkout button here

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartDisplayed() {
        return isElementDisplayed(cartContainer, 10);
    }

    public boolean hasProduct(String productName) {
        List<WebElement> productNames = findElements(cartItemNameLabels, 10);
        for (WebElement productNameLabel : productNames) {
            String currentProductName = productNameLabel.getText().trim();
            if (currentProductName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public CheckoutInformationPage clickCheckout() {
        clickElement(checkoutButton, 10);
        return new CheckoutInformationPage(driver);
    }

    @Override
    // OOP - Polymorphism: CartPage overrides the readiness contract for cart state.
    public boolean isPageLoaded() {
        return isElementDisplayed(cartContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Cart Page";
    }

    @Override
    // OOP - Polymorphism: CartPage overrides toString() to describe cart state.
    public String toString() {
        return getPageName();
    }
}
