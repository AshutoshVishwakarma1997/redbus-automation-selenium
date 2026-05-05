package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.DashboardPage;
import pages.HomePage;

// OOP - Inheritance: the test class extends BaseTest to reuse common test lifecycle behavior.
public class LoginTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject login credentials from testng.xml instead of hardcoding values in the test.
    @Parameters({"username", "password"})
    @Test(groups = {"smoke", "regression"})
    public void verifyUserCanLogin(String username, String password) {
        HomePage homePage = new HomePage(getDriver());

        // Assertion - isEnabled(): verifies the login button can be clicked.
        Assert.assertTrue(homePage.isLoginButtonEnabled(), "Login button should be enabled.");

        DashboardPage dashboardPage = homePage.enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        // Assertion - isDisplayed(): verifies the inventory screen is visible after login.
        Assert.assertTrue(dashboardPage.isInventoryDisplayed(), "Inventory page should be displayed after login.");
        // Assertion - getText() with Assert.assertEquals: verifies the inventory page heading text.
        Assert.assertEquals(dashboardPage.getInventoryTitleText(), "Products",
                "Inventory page title should match.");
    }
}
