package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckboxesPage extends BasePage {

    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(tagName = "h3")
    private WebElement header;

    public CheckboxesPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public int getCheckboxesCount() {
        return checkboxes.size();
    }

    public boolean isCheckboxSelected(int index) {
        return checkboxes.get(index).isSelected();
    }

    public void toggleCheckbox(int index) {
        checkboxes.get(index).click();
    }

    public void selectCheckbox(int index) {
        if (!isCheckboxSelected(index)) {
            toggleCheckbox(index);
        }
    }

    public void deselectCheckbox(int index) {
        if (isCheckboxSelected(index)) {
            toggleCheckbox(index);
        }
    }

    public void selectAllCheckboxes() {
        for (int i = 0; i < checkboxes.size(); i++) {
            selectCheckbox(i);
        }
    }

    public void deselectAllCheckboxes() {
        for (int i = 0; i < checkboxes.size(); i++) {
            deselectCheckbox(i);
        }
    }
}