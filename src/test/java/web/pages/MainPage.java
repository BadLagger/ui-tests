package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(tagName = "h2")
    private WebElement subheader;

    @FindBy(css = "ul li a")
    private List<WebElement> exampleLinks;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        navigateTo("https://the-internet.herokuapp.com/");
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

    public List<WebElement> getExampleLinks() {
        return exampleLinks;
    }

    public void clickLinkByText(String linkText) {
        for (WebElement link : exampleLinks) {
            if (link.getText().equals(linkText)) {
                link.click();
                break;
            }
        }
    }
}