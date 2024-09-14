package picsart.task.main.java.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import picsart.task.main.java.modals.SignInModal;
import picsart.task.main.java.components.FiltersComponent;


public class SearchPage extends BasePage {
    private final SignInModal signInModal;
    private final FiltersComponent filtersComponent;
    final By searchIFrame = By.xpath("//iframe[@data-testid='com.picsart.social.search']");
    final By plusAssetLocator = By.xpath("//*[@data-automation ='search-item-premium']/a");
    @FindBy(xpath = "//*[@data-testid='search-root']")
    private WebElement searchResultsBlock;
    @FindBy(xpath = "//*[@data-automation ='search-item-premium']/a")
    private WebElement plusAsset;

    @FindBy(xpath = "//*[@data-automation ='search-item-fte']")
    private WebElement freeAsset;

    @FindBy(xpath = "//*[@id ='base_card_item0']/a")
    private WebElement firstAsset;

    @FindBy(xpath = "//*[@id ='like_button_item0']")
    private WebElement firstAssetLikeButton;

    @FindBy(xpath = "//*[@id ='save_button_item0']")
    private WebElement firstAssetSaveButton;

    @FindBy(xpath = "//*[@id ='try_now_button_item0']")
    private WebElement firstAssetTryNowButton;

    @FindBy(xpath = "//*[@data-automation ='search-item-premium']/div/button[@aria-label='try-now-button']")
    private WebElement plusAssetTryNowButton;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement AcceptAllCookiesButton;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        signInModal = new SignInModal(driver);
        filtersComponent = new FiltersComponent(driver);
    }

    public FiltersComponent getFiltersComponent() {
        return filtersComponent;
    }

    public SignInModal getSignInModal() {
        return signInModal;
    }

    public void hoverOverFreeAsset() {
        Actions actions = new Actions(driver);
        hoverOverElement(actions, firstAsset);
    }

    public void hoverOverPlusAsset() {
        scrollToElement(plusAssetLocator, plusAsset);
        Actions actions = new Actions(driver);
        hoverOverElement(actions, plusAsset);
    }

    public void switchToSearchIFrame() {
        switchToIFrame(searchIFrame);
    }

    public void switchOutOfIFrame() {
        switchToDefaultFrame();
    }

    public void clickAcceptAllCookiesButton() {
        click(AcceptAllCookiesButton);
    }

    public void clickPlusAssetTryNowButton() {
        click(plusAssetTryNowButton);
    }

    public void clickLikeButton() {
        click(firstAssetLikeButton);
    }

    public void reloadSearchPage() {
        reloadPage();
    }

    public String getPlusAssetHREF() {
        return getHref(plusAsset);
    }

    public String getURL() {
        return getCurrentURL();
    }

    public String trimImageID(String url) {
        String imageIdKey = "imageId=";
        int startIndex = url.indexOf(imageIdKey) + imageIdKey.length();

        int endIndex = url.indexOf("&", startIndex);

        if (endIndex == -1) {
            endIndex = url.length();
        }

        return url.substring(startIndex, endIndex);
    }

    public boolean isLikeButtonLocated() {
        try {
            waitUntil(d -> isDisplayed(firstAssetLikeButton));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSaveButtonLocated() {
        try {
            waitUntil(d -> isDisplayed(firstAssetSaveButton));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isTryNowLocated() {
        try {
            waitUntil(d -> isDisplayed(firstAssetTryNowButton));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPlusAssetTryNowLocated() {
        try {
            waitUntil(d -> isDisplayed(plusAssetTryNowButton));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPlusAssetLocated() {
        try {
            return plusAsset.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isLoaded() {
        try {
            waitUntil(d -> isDisplayed(searchResultsBlock));
            return true;
        } catch (TimeoutException e) {
            throw new AssertionError(getClass().getSimpleName() + " does not load");
        }
    }
}
