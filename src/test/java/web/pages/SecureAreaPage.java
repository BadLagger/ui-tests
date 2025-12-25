package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecureAreaPage extends BasePage {

    @FindBy(tagName = "h2")
    private WebElement header;

    @FindBy(css = ".subheader")
    private WebElement subheader;

    @FindBy(className = "button")
    private WebElement logoutButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public String getSubheaderText() {
        return subheader.getText();
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public String getFlashMessage() {
        return flashMessage.getText().trim();
    }

    public boolean isLogoutButtonDisplayed() {
        return logoutButton.isDisplayed();
    }
}