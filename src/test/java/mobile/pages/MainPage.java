package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    @AndroidFindBy(accessibility = "Search Wikipedia")
    @iOSXCUITFindBy(accessibility = "Search Wikipedia")
    private WebElement searchBox;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia:id/search_close_btn")
    private WebElement clearSearchButton;

    @AndroidFindBy(id = "org.wikipedia:id/nav_more_container")
    private WebElement moreOptionsButton;

    @AndroidFindBy(accessibility = "My lists")
    private WebElement myListsButton;

    @AndroidFindBy(accessibility = "History")
    private WebElement historyButton;

    @AndroidFindBy(id = "org.wikipedia:id/view_card_header_title")
    private WebElement exploreFeedTitle;

    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isSearchBoxDisplayed() {
        try {
            return searchBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSearchBox() {
        searchBox.click();
        waitForSeconds(1);
    }

    public void enterSearchText(String text) {
        searchInput.sendKeys(text);
        waitForSeconds(1);
    }

    public void clearSearch() {
        if (clearSearchButton.isDisplayed()) {
            clearSearchButton.click();
            waitForSeconds(1);
        }
    }

    public String getExploreFeedTitle() {
        return exploreFeedTitle.getText();
    }

    public void openMoreOptions() {
        moreOptionsButton.click();
        waitForSeconds(1);
    }
}