package picsart.task.main.java.modals;


import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import picsart.task.main.java.pages.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SignInModal extends BasePage {
    @FindBy(xpath = "//*[@data-testid='registration-modal-container']")
    private WebElement signInModal;
    @FindBy(xpath = "//*[@data-testid='modal-close-icon']")
    private WebElement closeButton;

    public SignInModal(WebDriver driver) {
        super(driver);
    }

    public void clickOnCloseButton() {
        waitUntil(visibilityOf(signInModal));
        click(closeButton);
    }

    @Override
    public boolean isLoaded() {
        try {
            waitUntil(d -> isDisplayed(signInModal));
            return true;
        } catch (TimeoutException e) {
            throw new AssertionError(getClass().getSimpleName() + " does not load");
        }
    }
}
