package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;
import utils.CalendarUtils;
import utils.WaitUtils;

// OOP - Inheritance: HomePage extends BasePage to reuse common browser actions.
public class HomePage extends BasePage {

    // OOP - Encapsulation: page elements are private and only exposed through public methods.
    private WebElement signUpLink; // TODO: Add locator for sign up or register link here
    private WebElement loginLink; // TODO: Add locator for login link here
    private WebElement sourceCityInput; // TODO: Add locator for source city input here
    private WebElement destinationCityInput; // TODO: Add locator for destination city input here
    private WebElement datePickerTrigger; // TODO: Add locator for travel date picker trigger here
    private WebElement calendarNextArrow; // TODO: Add locator for calendar next arrow here
    private WebElement calendarMonthYearLabel; // TODO: Add locator for calendar month year label here
    private WebElement calendarDatesGrid; // TODO: Add locator for calendar dates grid here
    private WebElement searchBusesButton; // TODO: Add locator for search buses button here
    private WebElement homeBanner; // TODO: Add locator for home page banner here

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage clickSignUp() {
        clickElement(signUpLink, 10);
        return new SignUpPage(driver);
    }

    public LoginPage clickLogin() {
        clickElement(loginLink, 10);
        return new LoginPage(driver);
    }

    public HomePage enterSourceCity(String sourceCity) {
        typeText(sourceCityInput, sourceCity, 10);
        return this;
    }

    public HomePage enterDestinationCity(String destinationCity) {
        typeText(destinationCityInput, destinationCity, 10);
        return this;
    }

    public HomePage selectTravelDate(String travelDate) {
        clickElement(datePickerTrigger, 10);
        CalendarUtils.selectDate(travelDate, calendarNextArrow, calendarMonthYearLabel, calendarDatesGrid);
        return this;
    }

    public SearchResultsPage clickSearchBuses() {
        clickElement(searchBusesButton, 10);
        return new SearchResultsPage(driver);
    }

    public boolean isSearchButtonEnabled() {
        return isElementEnabled(searchBusesButton, 10);
    }

    @Override
    // OOP - Polymorphism: HomePage overrides the common readiness check with page-specific behavior.
    public boolean isPageLoaded() {
        return isElementDisplayed(homeBanner, 10);
    }

    @Override
    public String getPageName() {
        return "Home Page";
    }

    @Override
    // OOP - Polymorphism: HomePage overrides toString() to provide a page-specific description.
    public String toString() {
        WaitUtils.waitForVisibility(homeBanner, 10);
        return getPageName();
    }
}

