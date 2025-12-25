package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ArticlePage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/view_article_header_text")
    @iOSXCUITFindBy(accessibility = "article_title")
    private WebElement articleTitle;

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement backButton;

    @AndroidFindBy(accessibility = "Save")
    private WebElement saveButton;

    @AndroidFindBy(accessibility = "Share")
    private WebElement shareButton;

    @AndroidFindBy(accessibility = "Find in article")
    private WebElement findInArticleButton;

    @AndroidFindBy(id = "org.wikipedia:id/page_contents_container")
    private WebElement articleContent;

    @AndroidFindBy(id = "org.wikipedia:id/view_article_header_image")
    private WebElement articleImage;

    public ArticlePage(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleTitle() {
        waitForSeconds(2); // Даем время для загрузки заголовка
        return articleTitle.getText();
    }

    public boolean isArticleTitleDisplayed() {
        try {
            return articleTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBackButton() {
        backButton.click();
        waitForSeconds(1);
    }

    public void clickSaveButton() {
        saveButton.click();
        waitForSeconds(1);
    }

    public boolean isSaveButtonDisplayed() {
        try {
            return saveButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArticleContentDisplayed() {
        try {
            return articleContent.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollDown() {
        // Простая реализация скролла
        // В реальном проекте лучше использовать TouchAction
        driver.executeScript("mobile: scrollGesture",
                java.util.Map.of(
                        "left", 100, "top", 500, "width", 200, "height", 800,
                        "direction", "down",
                        "percent", 1.0
                ));
        waitForSeconds(1);
    }
}