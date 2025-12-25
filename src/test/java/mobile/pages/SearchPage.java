package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    @iOSXCUITFindBy(className = "XCUIElementTypeCell")
    private List<WebElement> searchResults;

    @AndroidFindBy(id = "org.wikipedia:id/search_empty_message")
    private WebElement emptySearchMessage;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_container")
    private List<WebElement> resultContainers;

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public int getSearchResultsCount() {
        waitForSeconds(2); // Даем время для загрузки результатов
        return searchResults.size();
    }

    public boolean hasSearchResults() {
        return getSearchResultsCount() > 0;
    }

    public void clickFirstResult() {
        if (hasSearchResults()) {
            searchResults.get(0).click();
            waitForSeconds(2);
        }
    }

    public void clickResultByIndex(int index) {
        if (searchResults.size() > index) {
            searchResults.get(index).click();
            waitForSeconds(2);
        }
    }

    public void clickResultByText(String text) {
        for (WebElement result : searchResults) {
            if (result.getText().contains(text)) {
                result.click();
                waitForSeconds(2);
                break;
            }
        }
    }

    public String getFirstResultTitle() {
        if (hasSearchResults()) {
            return searchResults.get(0).getText();
        }
        return "";
    }

    public boolean isEmptySearchMessageDisplayed() {
        try {
            return emptySearchMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}