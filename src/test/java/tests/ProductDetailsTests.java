package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.DashboardPage;
import pages.HomePage;
import pages.ProductDetailsPage;

// OOP - Inheritance: the test class extends BaseTest to reuse common test lifecycle behavior.
public class ProductDetailsTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject login credentials and the product under test from testng.xml.
    @Parameters({"username", "password", "productName"})
    @Test(groups = {"regression"})
    public void verifyUserCanOpenProductDetails(String username, String password, String productName) {
        HomePage homePage = new HomePage(getDriver());
        DashboardPage dashboardPage = homePage.loginAs(username, password);
        ProductDetailsPage productDetailsPage = dashboardPage.clickProduct(productName);

        // Assertion - isDisplayed(): verifies the product details page is visible.
        Assert.assertTrue(productDetailsPage.isPageLoaded(), "Product details page should be displayed.");
        // Assertion - getText() with Assert.assertEquals: verifies the selected product name matches the expected item.
        Assert.assertEquals(productDetailsPage.getProductNameText(), productName,
                "Product details page should show the selected product name.");
        // Assertion - Assert.assertFalse: verifies the product description is populated.
        Assert.assertFalse(productDetailsPage.getProductDescriptionText().isBlank(),
                "Product description should not be blank.");
    }
}
