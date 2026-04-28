package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

    // OOP - Encapsulation: the driver is kept private and exposed through protected accessors only.
    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>();

    // OOP - Abstraction: concrete test classes must define their primary landing page.
    protected abstract BasePage getLandingPage();

    // OOP - Abstraction: browser and application URL are injected into the base setup from TestNG.
    @Parameters({"browser", "appURL"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browser, String appURL) {
        if (!"chrome".equalsIgnoreCase(browser)) {
            throw new IllegalArgumentException("Only Chrome is configured in this framework.");
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get(appURL);
        DRIVER_HOLDER.set(driver);

        getLandingPage();
    }

    protected WebDriver getDriver() {
        return DRIVER_HOLDER.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DRIVER_HOLDER.get();
        if (driver != null) {
            driver.quit();
            DRIVER_HOLDER.remove();
        }
    }
}

