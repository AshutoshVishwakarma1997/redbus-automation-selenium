package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;

// OOP - Inheritance: the test class extends BaseTest so all WebDriver setup stays centralized.
public class InvalidLoginTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject invalid credentials from testng.xml for negative coverage.
    @Parameters({"invalidUsername", "invalidPassword"})
    @Test(groups = {"regression"})
    public void verifyUserSeesErrorForInvalidLogin(String invalidUsername, String invalidPassword) {
        HomePage homePage = new HomePage(getDriver());

        homePage.enterUsername(invalidUsername)
                .enterPassword(invalidPassword)
                .clickLoginButton();

        // Assertion - isDisplayed(): verifies the login error banner is visible.
        Assert.assertTrue(homePage.isErrorMessageDisplayed(), "Login error message should be displayed.");
        // Assertion - getText() with Assert.assertTrue: verifies an error message is returned to the user.
        Assert.assertFalse(homePage.getErrorMessageText().isBlank(), "Login error message text should not be blank.");
    }
}
