package web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.utils.TestSetup;

import java.util.List;

public class WebTests extends TestSetup {

    @Test
    public void testOpenTheInternetMainPage() {
        // Открываем главную страницу the-internet.herokuapp.com
        driver.get("https://the-internet.herokuapp.com/");

        // Проверяем заголовок страницы
        String pageTitle = driver.getTitle();
        System.out.println("Заголовок страницы: " + pageTitle);

        Assert.assertEquals(pageTitle, "The Internet",
                "Заголовок страницы должен быть 'The Internet'");

        // Проверяем заголовок h1 на странице
        WebElement header = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(header.getText(), "Welcome to the-internet",
                "H1 заголовок должен быть 'Welcome to the-internet'");

        // Проверяем наличие подзаголовка
        WebElement subheader = driver.findElement(By.tagName("h2"));
        Assert.assertEquals(subheader.getText(), "Available Examples",
                "H2 заголовок должен быть 'Available Examples'");

        // Проверяем, что есть ссылки на примеры
        List<WebElement> links = driver.findElements(By.cssSelector("ul li a"));
        Assert.assertTrue(links.size() > 0,
                "На странице должны быть ссылки на примеры");

        System.out.println("Найдено ссылок на примеры: " + links.size());
        System.out.println("Тест пройден успешно!");
    }

    @Test
    public void testNavigationToLoginPage() {
        // Открываем главную страницу
        driver.get("https://the-internet.herokuapp.com/");

        // Находим и кликаем на ссылку "Form Authentication"
        WebElement loginLink = driver.findElement(By.linkText("Form Authentication"));
        loginLink.click();

        // Проверяем, что перешли на страницу логина
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "URL должен содержать '/login'");

        // Проверяем заголовок страницы
        WebElement pageHeader = driver.findElement(By.tagName("h2"));
        Assert.assertEquals(pageHeader.getText(), "Login Page",
                "Заголовок должен быть 'Login Page'");

        // Проверяем наличие полей для ввода
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        Assert.assertTrue(usernameField.isDisplayed(), "Поле username должно отображаться");
        Assert.assertTrue(passwordField.isDisplayed(), "Поле password должно отображаться");
        Assert.assertTrue(loginButton.isDisplayed(), "Кнопка Login должна отображаться");

        System.out.println("Тест перехода на страницу логина пройден успешно!");
    }

    @Test
    public void testCheckboxesFunctionality() {
        // Открываем страницу с чекбоксами
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Находим все чекбоксы
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        Assert.assertEquals(checkboxes.size(), 2, "Должно быть 2 чекбокса");

        // Проверяем состояние первого чекбокса (по умолчанию не выбран)
        WebElement checkbox1 = checkboxes.get(0);
        Assert.assertFalse(checkbox1.isSelected(), "Первый чекбокс не должен быть выбран");

        // Кликаем на первый чекбокс
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected(), "Первый чекбокс должен быть выбран после клика");

        // Проверяем состояние второго чекбокса (по умолчанию выбран)
        WebElement checkbox2 = checkboxes.get(1);
        Assert.assertTrue(checkbox2.isSelected(), "Второй чекбокс должен быть выбран по умолчанию");

        // Кликаем на второй чекбокс
        checkbox2.click();
        Assert.assertFalse(checkbox2.isSelected(), "Второй чекбокс не должен быть выбран после клика");

        System.out.println("Тест чекбоксов пройден успешно!");
    }

    @Test
    public void testDropdownFunctionality() {
        // Открываем страницу с dropdown
        driver.get("https://the-internet.herokuapp.com/dropdown");

        // Находим dropdown элемент
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Assert.assertTrue(dropdown.isDisplayed(), "Dropdown должен отображаться");

        // Проверяем, что по умолчанию выбран "Please select an option"
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(dropdown);

        WebElement firstOption = select.getFirstSelectedOption();
        Assert.assertEquals(firstOption.getText(), "Please select an option",
                "По умолчанию должна быть выбрана опция 'Please select an option'");

        // Выбираем Option 1
        select.selectByVisibleText("Option 1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1",
                "Должна быть выбрана Option 1");

        // Выбираем Option 2
        select.selectByValue("2");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2",
                "Должна быть выбрана Option 2");

        System.out.println("Тест dropdown пройден успешно!");
    }
}