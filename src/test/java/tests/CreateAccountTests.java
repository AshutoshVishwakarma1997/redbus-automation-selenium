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
        HomePage homePage = new HomePage(getDriver()); // we created an object of Home Page class and passed the driver instance to it which we get from BaseTest class, so that we can perform actions on the home page using that driver.
        SignUpPage signUpPage = homePage.clickSignUp(); // this will return the object of SignUpPage class because after clicking on sign up link we will be navigated to sign up page and we can perform actions on that page using that object.

        Assert.assertTrue(signUpPage.isPageLoaded(), "Sign up dialog should be displayed.");
        Assert.assertEquals(signUpPage.getDialogTitleText(), "Login to get exciting offers",
                "Sign up dialog title should match.");
        Assert.assertTrue(signUpPage.isCaptchaDisplayed(), "Captcha should be displayed on the sign up dialog.");

        signUpPage.enterMobile(mobileNumber);
        Assert.assertFalse(signUpPage.isContinueButtonEnabled(),
                "Continue button should remain disabled until captcha is completed.");
    }
}
