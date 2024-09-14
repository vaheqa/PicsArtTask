package picsart.task.main.java.components;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import picsart.task.main.java.pages.BasePage;

public class FiltersComponent extends BasePage {
    @FindBy(xpath = "//*[@data-testid='search-filter-root']")
    protected WebElement searchFiltersBlock;

    @FindBy(xpath = "//*[@data-testid='search-filter-root' and contains(@class, 'hide')]")
    protected WebElement searchFiltersBlockHiddenState;

    @FindBy(xpath = "//button[@data-testid='search-header-filter']")
    private WebElement filterButton;

    @FindBy(xpath = "//*[@data-testid='checkbox-item-check' and contains(@aria-label, 'Personal')]")
    private WebElement personalCheckbox;

    public FiltersComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnTheFilterButton() {
        click(filterButton);
    }

    public void applyPersonalFilter() {
        click(personalCheckbox);
    }

    public boolean isSearchFiltersBlockHiddenLocated() {
        try {
            return searchFiltersBlockHiddenState.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isLoaded() {
        try {
            waitUntil(d -> isDisplayed(searchFiltersBlock));
            return true;
        } catch (TimeoutException e) {
            throw new AssertionError(getClass().getSimpleName() + " does not load");
        }
    }
}
