package tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;

// OOP - Inheritance: the test class extends BaseTest to inherit driver setup and teardown.
public class CreateAccountTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG note: SauceDemo does not provide a real self-registration flow, so this placeholder stays skipped by design.
    @Test(groups = {"disabled"})
    public void verifyUserCanCreateAccount() {
        throw new SkipException("SauceDemo does not support account registration or self-sign-up.");
    }
}
