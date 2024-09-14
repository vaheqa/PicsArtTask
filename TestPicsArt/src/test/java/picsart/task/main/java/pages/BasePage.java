package picsart.task.main.java.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected FluentWait<WebDriver> doWait(final Duration duration) {
        return new FluentWait<>(driver)
                .withTimeout(duration)
                .pollingEvery(Duration.ofMillis(200))
                .ignoreAll(List.of(NoSuchElementException.class,
                        NoSuchFrameException.class,
                        InvalidElementStateException.class));
    }

    protected void waitUntil(final ExpectedCondition<?> expectedCondition) {
        doWait(Duration.ofSeconds(30)).until(expectedCondition);
    }

    protected void switchToIFrame(By frame) {
        waitUntil(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    protected void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    protected void scrollToElement(By by, WebElement element) {
        waitUntil(presenceOfElementLocated(by));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    protected void hoverOverElement(Actions actions, WebElement element) {
        waitForVisibility(element);
        actions.moveToElement(element).pause(1000).perform();
    }

    protected void click(WebElement element) {
        waitUntil(visibilityOf(element));
        waitUntil(elementToBeClickable(element));
        element.click();
    }

    protected void reloadPage() {
        driver.navigate().refresh();
    }

    public void waitForVisibility(WebElement element) {
        waitUntil(visibilityOf(element));
    }

    public String getHref(WebElement element) {
        return element.getAttribute("href");
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    protected abstract boolean isLoaded();

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
