package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

// OOP - Inheritance: DashboardPage extends BasePage for shared behaviors.
public class DashboardPage extends BasePage {

    // OOP - Encapsulation: dashboard elements remain private inside the page object.
    private By inventoryContainer = By.xpath("//div[@id='inventory_container']"); // TODO: Add locator for SauceDemo inventory container here
    private By inventoryTitleLabel = By.xpath("//span[@data-test='title']"); // TODO: Add locator for SauceDemo inventory title here
    private By inventoryItemCards = By.xpath("//div[@data-test='inventory-item']"); // TODO: Add locator for all SauceDemo inventory item cards here
    private By inventoryItemNameLabel = By.xpath(".//*[@data-test='inventory-item-name']"); // TODO: Add relative locator for product name inside each inventory card here
    private By inventoryItemAddToCartButton = By.xpath(".//button[contains(@id,'add-to-cart')]"); // TODO: Add relative locator for add-to-cart button inside each inventory card here
    private By shoppingCartLink = By.xpath("//a[@data-test='shopping-cart-link']"); // TODO: Add locator for SauceDemo cart link here

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInventoryDisplayed() {
        return isElementDisplayed(inventoryContainer, 10);
    }

    public String getInventoryTitleText() {
        return readText(inventoryTitleLabel, 10);
    }

    public ProductDetailsPage clickProduct(String productName) {
        for (WebElement card : findElements(inventoryItemCards, 10)) {
            String currentProductName = card.findElement(inventoryItemNameLabel).getText().trim();
            if (currentProductName.equals(productName)) {
                card.findElement(inventoryItemNameLabel).click();
                return new ProductDetailsPage(driver);
            }
        }
        throw new IllegalStateException("Unable to find product in inventory list: " + productName);
    }

    public DashboardPage addProductToCart(String productName) {
        for (WebElement card : findElements(inventoryItemCards, 10)) {
            String currentProductName = card.findElement(inventoryItemNameLabel).getText().trim();
            if (currentProductName.equals(productName)) {
                card.findElement(inventoryItemAddToCartButton).click();
                return this;
            }
        }
        throw new IllegalStateException("Unable to find add-to-cart button for product: " + productName);
    }

    public CartPage clickShoppingCart() {
        clickElement(shoppingCartLink, 10);
        return new CartPage(driver);
    }

    @Override
    // OOP - Polymorphism: DashboardPage overrides page readiness to match dashboard visibility.
    public boolean isPageLoaded() {
        return isElementDisplayed(inventoryContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Dashboard Page";
    }

    @Override
    // OOP - Polymorphism: DashboardPage overrides toString() with dashboard-specific text.
    public String toString() {
        return getPageName();
    }
}
