package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;
import utils.WaitUtils;

// OOP - Inheritance: SeatSelectionPage extends BasePage to share reusable page methods.
public class SeatSelectionPage extends BasePage {

    // OOP - Encapsulation: seat selection elements stay private within the page object.
    private WebElement seatSelectionPanel; // TODO: Add locator for seat selection panel here
    private WebElement seatSelectionTitle; // TODO: Add locator for seat selection title here

    public SeatSelectionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSeatSelectionDisplayed() {
        return isElementDisplayed(seatSelectionPanel, 10);
    }

    public String getSeatSelectionTitleText() {
        return readText(seatSelectionTitle, 10);
    }

    @Override
    // OOP - Polymorphism: SeatSelectionPage overrides the page-loaded rule for seat selection.
    public boolean isPageLoaded() {
        return isElementDisplayed(seatSelectionPanel, 10);
    }

    @Override
    public String getPageName() {
        return "Seat Selection Page";
    }

    @Override
    // OOP - Polymorphism: SeatSelectionPage overrides toString() with seat-selection-specific text.
    public String toString() {
        WaitUtils.waitForVisibility(seatSelectionPanel, 10);
        return getPageName();
    }
}

