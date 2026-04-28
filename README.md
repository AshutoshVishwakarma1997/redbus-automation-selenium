# RedBus Automation Framework

## Project Overview

This project is a Java Selenium TestNG Maven framework built to automate the RedBus website at `https://www.redbus.in/`. It uses the Page Object Model, reusable explicit waits, TestNG parameter injection, parallel execution through Maven Surefire, and WebDriverManager for automatic ChromeDriver setup.

The framework is intentionally scaffolded with placeholder locators so it compiles cleanly while still demonstrating the requested automation design patterns and testing structure.

## Folder Structure

```text
.
├── .gitignore                                # Ignores build outputs and IDE files
├── pom.xml                                   # Maven dependencies, compiler, surefire, and reporting config
├── README.md                                 # Project documentation and usage guide
├── requirements.txt                          # Machine setup and execution instructions
└── src
    └── test
        ├── java
        │   ├── base
        │   │   ├── BasePage.java             # Shared page behavior and abstraction layer
        │   │   └── BaseTest.java             # Shared WebDriver lifecycle and test abstraction
        │   ├── pages
        │   │   ├── DashboardPage.java        # Logged-in state validations
        │   │   ├── HomePage.java             # RedBus landing page actions
        │   │   ├── LoginPage.java            # Login page actions and state checks
        │   │   ├── SearchResultsPage.java    # Search results and booking flow actions
        │   │   ├── SeatSelectionPage.java    # Seat selection page validations
        │   │   └── SignUpPage.java           # Account creation flow actions
        │   ├── tests
        │   │   ├── CreateAccountTests.java   # Regression test for account creation
        │   │   ├── LoginTests.java           # Smoke + regression login coverage
        │   │   └── SearchBookingTests.java   # Regression test for search and seat selection
        │   └── utils
        │       ├── CalendarUtils.java        # Reusable calendar navigation helper
        │       └── WaitUtils.java            # Reusable explicit wait methods
        └── resources
            ├── testng-regression.xml         # Full suite with include/exclude group syntax
            └── testng-smoke.xml              # Smoke-only suite
```

## Concepts Map

| Concept | Where It's Used | How It Works |
| --- | --- | --- |
| Inheritance | `BaseTest`, `BasePage`, all test classes, all page classes | Test classes extend `BaseTest` for driver setup and teardown, and page classes extend `BasePage` for shared browser actions. |
| Encapsulation | All classes under `pages` | Every `WebElement` field is `private`, and tests interact only through public page methods. |
| Abstraction | `BaseTest`, `BasePage` | Both base classes are abstract and define abstract methods that concrete subclasses implement. |
| Polymorphism | `toString()` overrides in page classes | Each page class overrides `toString()` so the same method behaves differently per page object. |
| POM | All classes under `pages` | Each page is represented by one Java class with page-specific actions and validations. |
| Explicit Waits | `WaitUtils` and all page methods | Page methods call reusable wait helpers before interacting with elements. |
| Calendar Utility | `CalendarUtils` and `HomePage.selectTravelDate()` | The utility parses the target date, advances the calendar, then clicks the correct day cell. |
| `@Parameters` | `BaseTest` and all test classes | Test data such as browser, credentials, cities, and dates is injected from TestNG XML. |
| Groups | `@Test(groups = ...)`, `testng-smoke.xml`, `testng-regression.xml` | Smoke and regression runs are controlled through group includes and excludes. |
| Parallel Execution | `pom.xml` Surefire config | Surefire runs methods in parallel with a thread count of `3`. |
| HTML Reports | `maven-surefire-report-plugin` | Running `mvn surefire-report:report` generates a browsable HTML report. |

## Test Scenarios

1. `CreateAccountTests.verifyUserCanCreateAccount`
   - Group: `regression`
   - Covers navigation to sign up, form completion, form submission, and success message verification.

2. `LoginTests.verifyUserCanLogin`
   - Groups: `smoke`, `regression`
   - Covers login, checkbox state validation, submit button state validation, and logged-in confirmation.

3. `SearchBookingTests.verifyUserCanSearchAndReachSeatSelection`
   - Group: `regression`
   - Covers source and destination entry, calendar selection, search result validation, first bus selection, and seat selection page validation.

## How to Run

1. Clone the repository into your local machine.
2. Open a terminal in the project root.
3. Install dependencies and compile the project:

```bash
mvn clean install
```

4. Run the smoke suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml
```

5. Run the regression suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-regression.xml
```

6. Generate the HTML report:

```bash
mvn surefire-report:report
```

7. Open the generated report at:

```text
target/site/surefire-report.html
```

## How to Add New Tests

1. Create a new page class under `src/test/java/pages` and add `TODO` locator comments for every new element.
2. Create a new test class under `src/test/java/tests` that extends `BaseTest`.
3. Add `@Parameters` to inject required data from the TestNG XML file.
4. Add `@Test(groups = {...})` to place the test in the correct suite grouping.
5. Register the new test class in `testng-smoke.xml` or `testng-regression.xml` as needed.

## How to Switch Parallel Thread Count

Parallel execution is controlled in `pom.xml` inside the `maven-surefire-plugin` configuration:

```xml
<parallel>methods</parallel>
<threadCount>3</threadCount>
```

To change concurrency, update the `threadCount` value. A higher value increases parallel method execution, while a lower value reduces browser concurrency and resource usage.

