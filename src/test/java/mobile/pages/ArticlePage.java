package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
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

    @AndroidFindBy(id = "org.wikipedia:id/pcs")
    private WebElement pcs;

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

    public void missClick() {
        try {
            Point btnLocation = backButton.getLocation();
            Dimension btnSize = backButton.getSize();

            int clickX = btnLocation.getX() + 1;
            int clickY = btnLocation.getY() + btnSize.getHeight() + 1;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 0);

            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), clickX, clickY));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(new Pause(finger, Duration.ofMillis(100)));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(tap));

        }catch(Exception e) {
            log.info("Ошибка при попытке тапнуть по экрану: {}", e.getMessage());
        }
    }

    public boolean isArticleTitleDisplayed(String titleText) {
        try {

            WebElement title = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + titleText + "\"]"));
            return title.isDisplayed();
        } catch (Exception e) {
            log.info("Ошибка при получения списка текстовых полей в заголовке: {}", e.getMessage());
            return false;
        }
    }
}