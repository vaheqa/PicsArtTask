package picsart.task.main.java.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import picsart.task.main.java.modals.SignInModal;

public class EditorPage extends BasePage {

    private final SignInModal signInModal;

    public EditorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        signInModal = new SignInModal(driver);
    }

    public SignInModal getSignInModal() {
        return signInModal;
    }

    @FindBy(xpath = "//section[@data-testid='editor-header']")
    private WebElement editorHeader;

    @Override
    public boolean isLoaded() {
        try {
            waitUntil(d -> isDisplayed(editorHeader));
            return true;
        } catch (TimeoutException e) {
            throw new AssertionError(getClass().getSimpleName() + " does not load");
        }
    }
}
