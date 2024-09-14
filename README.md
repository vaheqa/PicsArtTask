# PicsArtTask
This is a web automation framework for testing the PicsArt search page using Selenium WebDriver, TestNG, and Java.

### Project Structure
- src/test/java/picsart/task/main/java/: Contains the main classes for page objects, components, driver, and modals.
- src/test/java/picsart/task/test/java/searchPage/: Contains the test classes.
- testng.xml: TestNG suite file for configuring and running tests in parallel.
- pom.xml: Maven build file containing project dependencies.

## Getting Started
To get started with running these tests, you'll need to have the following software installed:
- Java 17
- Chrome browser
- Maven

## Running the Tests Locally
To run the tests via the TestNg runner, you can right-click on `${testng}.xml` file and click on "Run" for running the test.
This will run the test in parallel on three different
resolutions: 1024 x 768, 1440 x 900, and 1366 x 768 in the Chrome browser.
