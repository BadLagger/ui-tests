package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(tagName = "h2")
    private WebElement subheader;

    @FindBy(css = "ul li a")
    private List<WebElement> exampleLinks;

    @FindBy(linkText = "Form Authentication")
    private WebElement formAuthenticationLink;

    @FindBy(linkText = "Checkboxes")
    private WebElement checkboxesLink;

    @FindBy(linkText = "Dropdown")
    private WebElement dropdownLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getHeaderText() {
        return header.getText();
    }

    public String getSubheaderText() {
        return subheader.getText();
    }

    public int getExampleLinksCount() {
        return exampleLinks.size();
    }

    public void clickFormAuthentication() {
        formAuthenticationLink.click();
    }

    public void clickCheckboxes() {
        checkboxesLink.click();
    }

    public void clickDropdown() {
        dropdownLink.click();
    }

    public void clickExampleLink(String linkText) {
        for (WebElement link : exampleLinks) {
            if (link.getText().equals(linkText)) {
                link.click();
                break;
            }
        }
    }
}
