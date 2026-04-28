package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;
import utils.WaitUtils;

// OOP - Inheritance: SearchResultsPage extends BasePage to reuse shared functionality.
public class SearchResultsPage extends BasePage {

    // OOP - Encapsulation: search results elements are private within the page object.
    private WebElement resultsContainer; // TODO: Add locator for search results container here
    private WebElement firstAvailableBusCard; // TODO: Add locator for first available bus result here
    private WebElement proceedToSeatSelectionButton; // TODO: Add locator for proceed to seat selection button here
    private WebElement loadingSpinner; // TODO: Add locator for results loading spinner here

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean areSearchResultsDisplayed() {
        if (loadingSpinner != null) {
            WaitUtils.waitForInvisibility(loadingSpinner, 10);
        }
        return isElementDisplayed(resultsContainer, 10);
    }

    public boolean isFirstAvailableBusDisplayed() {
        return isElementDisplayed(firstAvailableBusCard, 10);
    }

    public SearchResultsPage clickFirstAvailableBus() {
        clickElement(firstAvailableBusCard, 10);
        return this;
    }

    public SeatSelectionPage clickProceedToSeatSelection() {
        clickElement(proceedToSeatSelectionButton, 10);
        return new SeatSelectionPage(driver);
    }

    @Override
    // OOP - Polymorphism: SearchResultsPage overrides the readiness contract for results state.
    public boolean isPageLoaded() {
        return isElementDisplayed(resultsContainer, 10);
    }

    @Override
    public String getPageName() {
        return "Search Results Page";
    }

    @Override
    // OOP - Polymorphism: SearchResultsPage overrides toString() to describe search results.
    public String toString() {
        WaitUtils.waitForVisibility(resultsContainer, 10);
        return getPageName();
    }
}

