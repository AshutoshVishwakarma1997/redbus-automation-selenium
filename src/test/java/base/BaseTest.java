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
    // ThreadLocal is used to ensure thread safety when running tests in parallel, allowing each test thread to have its own WebDriver instance.
    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>();

    // OOP - Abstraction: concrete test classes must define their primary landing page.
    protected abstract BasePage getLandingPage();
    // we are not using above method in the base test class, but it forces all subclasses to implement it, ensuring a consistent way to access the landing page across all tests.
    // And by lending page it means the page which will be loaded when we will open the application URL in the browser.

    // OOP - Abstraction: browser and application URL are injected into the base setup from TestNG.
    @Parameters({"browser", "appURL"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browser, String appURL) {
        if (!"chrome".equalsIgnoreCase(browser)) {
            throw new IllegalArgumentException("Only Chrome is configured in this framework.");
        }

        WebDriverManager.chromedriver().setup();
        // above method call automatically manages the ChromeDriver binary, eliminating manual setup and versioning issues.
        ChromeOptions options = new ChromeOptions();
        // above line initializes ChromeOptions to configure browser settings before launching, such as headless mode or window size.
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        // reason to add below line is to set implicit wait to 0 seconds to avoid unintended delays in element interactions, ensuring that tests fail fast if elements are not immediately available, which is crucial for reliable test execution and accurate failure reporting.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get(appURL);
        // below line stores the WebDriver instance in a ThreadLocal variable, ensuring that each test thread has its own isolated driver instance, which is essential for thread safety when running tests in parallel.
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

