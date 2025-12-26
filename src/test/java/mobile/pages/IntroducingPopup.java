package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class IntroducingPopup extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Introducing\")")
    private WebElement introducingImage;

    @AndroidFindBy(id = "org.wikipedia:id/closeButton")
    private WebElement closeButton;

    public IntroducingPopup(AppiumDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        try {
            return introducingImage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Ошибка получения картинки: " + e.getMessage());
            return false;
        }
    }

    public void closeBtn() {
        try {
            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (Exception ignored) {
        }
    }
}
