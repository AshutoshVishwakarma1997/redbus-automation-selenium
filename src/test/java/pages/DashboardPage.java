package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

// OOP - Inheritance: DashboardPage extends BasePage for shared behaviors.
public class DashboardPage extends BasePage {

    // OOP - Encapsulation: dashboard elements remain private inside the page object.
    private By profileNameLabel; // TODO: Add locator for logged-in profile name label here
    private By dashboardContainer; // TODO: Add locator for dashboard container here

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProfileNameDisplayed() {
        return isElementDisplayed(profileNameLabel, 10);
    }

    public String getProfileNameText() {
        return readText(profileNameLabel, 10);
    }

    @Override
    // OOP - Polymorphism: DashboardPage overrides page readiness to match dashboard visibility.
    public boolean isPageLoaded() {
        return isElementDisplayed(dashboardContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Dashboard Page";
    }

    @Override
    // OOP - Polymorphism: DashboardPage overrides toString() with dashboard-specific text.
    public String toString() {
        return getPageName();
    }
}
