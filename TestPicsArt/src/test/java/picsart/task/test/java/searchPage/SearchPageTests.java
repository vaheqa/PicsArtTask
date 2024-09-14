package picsart.task.test.java.searchPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import picsart.task.main.java.driver.DriverFactory;
import picsart.task.main.java.pages.EditorPage;
import picsart.task.main.java.pages.SearchPage;

import static org.testng.Assert.*;

public class SearchPageTests {
    private WebDriver driver;
    private SearchPage searchPage;
    private EditorPage editorPage;

    // Accepts the parameters from testng.xml
    @Parameters({"width", "height"})
    @BeforeMethod
    public void setUp(int width, int height) {
        driver = DriverFactory.getDriver(width, height); // Pass the resolution
        driver.get("https://picsart.com/search");
        searchPage = new SearchPage(driver);
        editorPage = new EditorPage(driver);
    }

    @Test()
    public void testSearchPageFiltersAndAssets() {
        searchPage.clickAcceptAllCookiesButton();
        searchPage.switchToSearchIFrame();
        searchPage.getFiltersComponent().clickOnTheFilterButton();

        assertTrue(searchPage.getFiltersComponent().isSearchFiltersBlockHiddenLocated()); //Assert that the filter had disappeared

        searchPage.getFiltersComponent().clickOnTheFilterButton();
        searchPage.getFiltersComponent().applyPersonalFilter();

        assertFalse(searchPage.isPlusAssetLocated()); //Assert that no plus assets are shown

        searchPage.hoverOverFreeAsset();

        assertTrue(searchPage.isLikeButtonLocated()); //Assert that like button is shown
        assertTrue(searchPage.isSaveButtonLocated()); //Assert that save button is shown
        assertTrue(searchPage.isTryNowLocated()); //Assert that try now button is shown

        searchPage.clickLikeButton();
        searchPage.switchOutOfIFrame();

        assertTrue(searchPage.getSignInModal().isLoaded()); //Assert that the sign in modal is displayed

        searchPage.getSignInModal().clickOnCloseButton();
        searchPage.switchToSearchIFrame();
        searchPage.getFiltersComponent().applyPersonalFilter();
        searchPage.reloadSearchPage();
        searchPage.switchToSearchIFrame();
        searchPage.isLoaded();
        searchPage.hoverOverPlusAsset();
        String plusAssetID = searchPage.trimImageID(searchPage.getPlusAssetHREF());

        assertTrue(searchPage.isPlusAssetTryNowLocated()); //Assert that plus asset try now button is shown

        searchPage.clickPlusAssetTryNowButton();
        searchPage.switchOutOfIFrame();
        editorPage.getSignInModal().clickOnCloseButton();
        editorPage.isLoaded();

        String imageIDInEditorURL = searchPage.trimImageID(searchPage.getURL());
        assertEquals(plusAssetID, imageIDInEditorURL); //Assert that imageId from ULR equals to imageID from element href
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}