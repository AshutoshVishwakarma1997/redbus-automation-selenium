package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utils.CalendarUtils;

// OOP - Inheritance: HomePage extends BasePage to reuse common browser actions.
public class HomePage extends BasePage {

    // OOP - Encapsulation: page elements are private and only exposed through public methods.
    private By accountIcon = By.xpath("//button[contains(.,\"Account\")]"); // Account menu trigger on the home page
    private By accountSidePanel = By.xpath("//div[@role='dialog' and @aria-label='Account Settings']"); // Account side drawer container
    private By signUpLink = By.xpath("//div[@role='dialog' and @aria-label='Account Settings']//div[@data-autoid='signInPrompt']//button[@aria-label='Sign up']"); // Sign up button inside the account side panel
    private By loginLink; // TODO: Add locator for login link here
    private By sourceCityInput; // TODO: Add locator for source city input here
    private By destinationCityInput; // TODO: Add locator for destination city input here
    private By datePickerTrigger; // TODO: Add locator for travel date picker trigger here
    private By calendarNextArrow; // TODO: Add locator for calendar next arrow here
    private By calendarMonthYearLabel; // TODO: Add locator for calendar month year label here
    private By calendarDatesGrid; // TODO: Add locator for calendar dates grid here
    private By searchBusesButton; // TODO: Add locator for search buses button here
    private By homeBanner; // TODO: Add locator for home page banner here

    public HomePage(WebDriver driver) {
        // super keyword is used to call the constructor of the parent class (BasePage) to initialize the WebDriver instance in the HomePage class, allowing it to use the common methods defined in BasePage for interacting with web elements.
        super(driver);
    }

    //the class name SignUpPage is used as the return type of the clickSignUp() method to indicate that when this method is called, it will perform the action of clicking the sign-up link and then return an instance of the SignUpPage class, allowing the caller to interact with the sign-up page that is expected to be loaded after the click action.
    public SignUpPage clickSignUp() {
        clickElement(accountIcon, 10);
        isElementDisplayed(accountSidePanel, 10);
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
        CalendarUtils.selectDate(travelDate, driver, calendarNextArrow, calendarMonthYearLabel, calendarDatesGrid);
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
        return getPageName();
    }
}
