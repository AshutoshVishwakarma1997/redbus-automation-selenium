package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.DashboardPage;
import pages.HomePage;

// OOP - Inheritance: the test class extends BaseTest to reuse common test lifecycle behavior.
public class CheckoutTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject login credentials, checkout data, and the product under test from testng.xml.
    @Parameters({"username", "password", "productName", "firstName", "lastName", "postalCode"})
    @Test(groups = {"regression"})
    public void verifyUserCanCompleteCheckout(String username, String password, String productName,
                                              String firstName, String lastName, String postalCode) {
        HomePage homePage = new HomePage(getDriver());
        DashboardPage dashboardPage = homePage.loginAs(username, password);
        CartPage cartPage = dashboardPage.addProductToCart(productName)
                .clickShoppingCart();
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckout();
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .clickContinue();

        // Assertion - Assert.assertTrue: verifies the selected product remains listed during checkout overview.
        Assert.assertTrue(checkoutOverviewPage.hasProduct(productName),
                "Selected product should be present in checkout overview.");
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinish();
        // Assertion - isDisplayed(): verifies the final order confirmation screen is visible.
        Assert.assertTrue(checkoutCompletePage.isPageLoaded(), "Checkout complete page should be displayed.");
        // Assertion - getText() with Assert.assertEquals: verifies the order success header text.
        Assert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), "Thank you for your order!",
                "Checkout success message should match.");
    }
}
