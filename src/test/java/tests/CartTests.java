package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.CartPage;
import pages.DashboardPage;
import pages.HomePage;

// OOP - Inheritance: the test class extends BaseTest to reuse common test lifecycle behavior.
public class CartTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject login credentials and the product under test from testng.xml.
    @Parameters({"username", "password", "productName"})
    @Test(groups = {"regression"})
    public void verifyUserCanAddProductToCart(String username, String password, String productName) {
        HomePage homePage = new HomePage(getDriver());
        DashboardPage dashboardPage = homePage.loginAs(username, password);
        CartPage cartPage = dashboardPage.addProductToCart(productName)
                .clickShoppingCart();

        // Assertion - isDisplayed(): verifies the cart screen is visible.
        Assert.assertTrue(cartPage.isCartDisplayed(), "Cart page should be displayed.");
        // Assertion - Assert.assertTrue: verifies the chosen product is present in the cart.
        Assert.assertTrue(cartPage.hasProduct(productName), "Selected product should be present in the cart.");
    }
}
