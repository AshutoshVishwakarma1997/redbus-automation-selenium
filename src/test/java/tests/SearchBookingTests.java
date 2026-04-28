package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.SeatSelectionPage;

// OOP - Inheritance: the test class extends BaseTest so all WebDriver setup stays centralized.
public class SearchBookingTests extends BaseTest {

    @Override
    protected BasePage getLandingPage() {
        return new HomePage(getDriver());
    }

    // TestNG @Parameters: inject route and journey date data from testng.xml for data-driven execution.
    @Parameters({"sourceCity", "destinationCity", "travelDate"})
    @Test(groups = {"regression"})
    public void verifyUserCanSearchAndReachSeatSelection(String sourceCity, String destinationCity, String travelDate) {
        HomePage homePage = new HomePage(getDriver());

        // Assertion - isEnabled(): verifies the search button is ready for interaction.
        Assert.assertTrue(homePage.isSearchButtonEnabled(), "Search Buses button should be enabled.");

        SearchResultsPage searchResultsPage = homePage.enterSourceCity(sourceCity)
                .enterDestinationCity(destinationCity)
                .selectTravelDate(travelDate)
                .clickSearchBuses();

        // Assertion - isDisplayed(): verifies search results are visible.
        Assert.assertTrue(searchResultsPage.areSearchResultsDisplayed(), "Search results should be displayed.");
        // Assertion - Assert.assertTrue: verifies at least one bus result is visible for booking.
        Assert.assertTrue(searchResultsPage.isFirstAvailableBusDisplayed(),
                "At least one available bus result should be visible.");

        SeatSelectionPage seatSelectionPage = searchResultsPage.clickFirstAvailableBus()
                .clickProceedToSeatSelection();

        // Assertion - isDisplayed(): verifies the seat selection screen is visible.
        Assert.assertTrue(seatSelectionPage.isSeatSelectionDisplayed(),
                "Seat selection screen should be displayed.");
        // Assertion - getText() with Assert.assertEquals: verifies the seat selection title text.
        Assert.assertEquals(seatSelectionPage.getSeatSelectionTitleText(), "Select Seats",
                "Seat selection title should match.");
    }
}

