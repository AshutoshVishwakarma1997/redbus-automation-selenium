package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;

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
        LoginPage loginPage = homePage.clickLogin();

        // Assertion - getText() with Assert.assertEquals: verifies the login form heading text.
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login", "Login header text should match.");
        // Assertion - isEnabled(): verifies the login button can be clicked.
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled.");
        // Assertion - Assert.assertFalse with isSelected(): verifies the checkbox starts unchecked.
        Assert.assertFalse(loginPage.isRememberMeSelected(), "Remember Me should be unchecked by default.");

        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickRememberMe();

        // Assertion - Assert.assertTrue with isSelected(): verifies the checkbox is selected after click.
        Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember Me should be checked after selecting it.");

        DashboardPage dashboardPage = loginPage.clickLoginButton();

        // Assertion - isDisplayed(): verifies the logged-in profile or dashboard state is visible.
        Assert.assertTrue(dashboardPage.isProfileNameDisplayed(), "Profile name should be displayed after login.");
    }
}

