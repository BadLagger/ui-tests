package mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class WelcomePage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    @iOSXCUITFindBy(accessibility = "Skip")
    private WebElement skipButton;

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_forward_button")
    private WebElement continueButton;

    @AndroidFindBy(id = "org.wikipedia:id/primaryTextView")
    private WebElement welcomeText;

    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isSkipButtonDisplayed() {
        try {
            return skipButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSkip() {
        if (isSkipButtonDisplayed()) {
            skipButton.click();
            waitForSeconds(1);
        }
    }

    public void clickContinue() {
        continueButton.click();
        waitForSeconds(1);
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }
}