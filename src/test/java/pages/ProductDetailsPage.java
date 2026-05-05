package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: ProductDetailsPage extends BasePage to inherit shared page methods.
public class ProductDetailsPage extends BasePage {

    // OOP - Encapsulation: page elements are private and hidden behind public actions.
    private By productDetailsContainer = By.xpath("//div[@data-test='inventory-item']"); // TODO: Add locator for SauceDemo product details container here
    private By productNameLabel = By.xpath("//div[@data-test='inventory-item-name']"); // TODO: Add locator for SauceDemo product name on the details page here
    private By productDescriptionLabel = By.xpath("//div[@data-test='inventory-item-desc']"); // TODO: Add locator for SauceDemo product description on the details page here
    private By addToCartButton = By.xpath("//button[contains(@id,'add-to-cart')]"); // TODO: Add locator for SauceDemo add-to-cart button on the details page here
    private By backToProductsButton = By.xpath("//button[@data-test='back-to-products']"); // TODO: Add locator for SauceDemo back-to-products button here

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameText() {
        return readText(productNameLabel, 10);
    }

    public String getProductDescriptionText() {
        return readText(productDescriptionLabel, 10);
    }

    public ProductDetailsPage clickAddToCart() {
        clickElement(addToCartButton, 10);
        return this;
    }

    public DashboardPage clickBackToProducts() {
        clickElement(backToProductsButton, 10);
        return new DashboardPage(driver);
    }

    @Override
    // OOP - Polymorphism: ProductDetailsPage overrides the base readiness check with details-page validation.
    public boolean isPageLoaded() {
        return isElementDisplayed(productDetailsContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Product Details Page";
    }

    @Override
    // OOP - Polymorphism: ProductDetailsPage overrides toString() to describe itself differently at runtime.
    public String toString() {
        return getPageName();
    }
}
