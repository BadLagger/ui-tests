package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    @FindBy(id = "dropdown")
    private WebElement dropdownElement;

    @FindBy(tagName = "h3")
    private WebElement header;

    private Select dropdown;

    public DropdownPage(WebDriver driver) {
        super(driver);
        dropdown = new Select(dropdownElement);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public void selectByVisibleText(String text) {
        dropdown.selectByVisibleText(text);
    }

    public void selectByValue(String value) {
        dropdown.selectByValue(value);
    }

    public void selectByIndex(int index) {
        dropdown.selectByIndex(index);
    }

    public String getSelectedOptionText() {
        return dropdown.getFirstSelectedOption().getText();
    }

    public String getSelectedOptionValue() {
        return dropdown.getFirstSelectedOption().getAttribute("value");
    }

    public int getOptionsCount() {
        return dropdown.getOptions().size();
    }
}