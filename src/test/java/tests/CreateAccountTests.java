package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import pages.SignUpPage;

// OOP - Inheritance: the test class extends BaseTest to inherit driver setup and teardown.
public class CreateAccountTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject account creation data and application context from testng.xml.
    @Parameters({"fullName", "email", "mobileNumber", "password"})
    @Test(groups = {"regression"})
    public void verifyUserCanCreateAccount(String fullName, String email, String mobileNumber, String password) {
        HomePage homePage = new HomePage(getDriver());
        SignUpPage signUpPage = homePage.clickSignUp();

        // Assertion - isEnabled(): verifies the register button is interactable before submit.
        Assert.assertTrue(signUpPage.isRegisterButtonEnabled(), "Register button should be enabled.");

        signUpPage.enterName(fullName)
                .enterEmail(email)
                .enterMobile(mobileNumber)
                .enterPassword(password)
                .clickRegister();

        // Assertion - isDisplayed(): verifies the success message is visible after registration.
        Assert.assertTrue(signUpPage.isSuccessMessageDisplayed(), "Success message should be displayed.");
        // Assertion - getText() with Assert.assertEquals: verifies the success message content.
        Assert.assertEquals(signUpPage.getSuccessMessageText(), "Account created successfully",
                "Success message text should match.");
    }
}

