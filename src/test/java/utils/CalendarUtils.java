package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class CalendarUtils {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);

    private CalendarUtils() {
    }

    public static void selectDate(String date, WebElement nextArrow, WebElement monthYearLabel, WebElement datesGrid) {
        // Calendar algorithm: parse the input date string into a LocalDate for safe date handling.
        LocalDate targetDate = LocalDate.parse(date, INPUT_FORMATTER);
        YearMonth targetMonth = YearMonth.from(targetDate);

        // Calendar algorithm: keep reading the current month/year label until the desired month is visible.
        while (true) {
            WaitUtils.waitForVisibility(monthYearLabel, 10);
            String displayedMonthYear = monthYearLabel.getText().trim();
            YearMonth displayedMonth = YearMonth.parse(displayedMonthYear, MONTH_YEAR_FORMATTER);

            if (displayedMonth.equals(targetMonth)) {
                break;
            }

            // Calendar algorithm: advance the calendar one month at a time using the next arrow.
            WaitUtils.waitForClickability(nextArrow, 10);
            nextArrow.click();
        }

        // Calendar algorithm: once the target month is shown, scan the date grid and click the matching day.
        String targetDay = String.valueOf(targetDate.getDayOfMonth());
        WaitUtils.waitForVisibility(datesGrid, 10);
        List<WebElement> dayCells = datesGrid.findElements(By.xpath(".//*[normalize-space()='" + targetDay + "']"));
        if (dayCells.isEmpty()) {
            throw new IllegalStateException("Unable to find day " + targetDay + " in the current calendar grid.");
        }

        WaitUtils.waitForClickability(dayCells.get(0), 10);
        dayCells.get(0).click();
    }
}

