package web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import web.pages.*;
import web.utils.TestSetup;

public class WebTests extends TestSetup {

    @Test(priority = 1, description = "Тест открытия главной страницы")
    public void testOpenMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        Assert.assertEquals(mainPage.getPageTitle(), "The Internet");
        Assert.assertEquals(mainPage.getHeaderText(), "Welcome to the-internet");
        Assert.assertEquals(mainPage.getSubheaderText(), "Available Examples");
        Assert.assertTrue(mainPage.getExampleLinksCount() > 0);

        System.out.println("✓ Тест 1 пройден: Главная страница открыта корректно");
        System.out.println("  Найдено примеров: " + mainPage.getExampleLinksCount());
    }

    @Test(priority = 2, description = "Тест навигации по меню")
    public void testNavigationToLoginPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        // Переходим на страницу логина
        mainPage.clickLinkByText("Form Authentication");

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/login"));
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());

        System.out.println("✓ Тест 2 пройден: Навигация на страницу логина работает");
    }

    @Test(priority = 3, description = "Тест авторизации с валидными данными")
    public void testSuccessfulLogin() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkByText("Form Authentication");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");

        SecureAreaPage secureAreaPage = new SecureAreaPage(driver);
        Assert.assertTrue(secureAreaPage.getCurrentUrl().contains("/secure"));
        Assert.assertEquals(secureAreaPage.getHeaderText(), "Secure Area");
        Assert.assertTrue(secureAreaPage.getFlashMessage().contains("You logged into a secure area!"));
        Assert.assertTrue(secureAreaPage.isLogoutButtonDisplayed());

        System.out.println("✓ Тест 3 пройден: Успешная авторизация работает");
    }

    @Test(priority = 4, description = "Тест авторизации с невалидными данными")
    public void testFailedLogin() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkByText("Form Authentication");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wronguser", "wrongpassword");

        Assert.assertTrue(loginPage.isFlashMessageDisplayed());
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"));

        System.out.println("✓ Тест 4 пройден: Неуспешная авторизация обрабатывается корректно");
    }

    @Test(priority = 5, description = "Тест работы с чекбоксами")
    public void testCheckboxesFunctionality() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkByText("Checkboxes");

        CheckboxesPage checkboxesPage = new CheckboxesPage(driver);
        Assert.assertEquals(checkboxesPage.getHeaderText(), "Checkboxes");
        Assert.assertEquals(checkboxesPage.getCheckboxesCount(), 2);

        // Проверяем начальное состояние
        Assert.assertFalse(checkboxesPage.isCheckboxSelected(0));
        Assert.assertTrue(checkboxesPage.isCheckboxSelected(1));

        // Изменяем состояние
        checkboxesPage.toggleCheckbox(0);
        checkboxesPage.toggleCheckbox(1);

        // Проверяем измененное состояние
        Assert.assertTrue(checkboxesPage.isCheckboxSelected(0));
        Assert.assertFalse(checkboxesPage.isCheckboxSelected(1));

        System.out.println("✓ Тест 5 пройден: Чекбоксы работают корректно");
    }

    @Test(priority = 6, description = "Тест работы с выпадающим списком")
    public void testDropdownFunctionality() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkByText("Dropdown");

        DropdownPage dropdownPage = new DropdownPage(driver);
        Assert.assertEquals(dropdownPage.getHeaderText(), "Dropdown List");

        // Проверяем начальное состояние
        Assert.assertEquals(dropdownPage.getSelectedOptionText(), "Please select an option");
        Assert.assertEquals(dropdownPage.getOptionsCount(), 3);

        // Выбираем Option 1
        dropdownPage.selectByVisibleText("Option 1");
        Assert.assertEquals(dropdownPage.getSelectedOptionText(), "Option 1");
        Assert.assertEquals(dropdownPage.getSelectedOptionValue(), "1");

        // Выбираем Option 2
        dropdownPage.selectByValue("2");
        Assert.assertEquals(dropdownPage.getSelectedOptionText(), "Option 2");
        Assert.assertEquals(dropdownPage.getSelectedOptionValue(), "2");

        System.out.println("✓ Тест 6 пройден: Выпадающий список работает корректно");
    }

    @Test(priority = 7, description = "Тест работы с динамическими элементами")
    public void testDynamicLoading() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkByText("Dynamic Loading");

        // Проверяем, что перешли на нужную страницу
        Assert.assertTrue(driver.getCurrentUrl().contains("/dynamic_loading"));

        // Находим и кликаем на пример
        driver.findElement(org.openqa.selenium.By.linkText("Example 1: Element on page that is hidden")).click();

        // Находим и кликаем на кнопку Start
        driver.findElement(org.openqa.selenium.By.cssSelector("#start button")).click();

        // Ждем загрузки элемента (используем явное ожидание)
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(org.openqa.selenium.By.id("finish")));

        // Проверяем, что текст отобразился
        String loadedText = driver.findElement(org.openqa.selenium.By.id("finish")).getText();
        Assert.assertEquals(loadedText, "Hello World!");

        System.out.println("✓ Тест 7 пройден: Динамическая загрузка работает корректно");
    }

    @Test(priority = 8, description = "Тест работы с JavaScript Alerts")
    public void testJavaScriptAlerts() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkByText("JavaScript Alerts");

        // Проверяем заголовок
        String header = driver.findElement(org.openqa.selenium.By.tagName("h3")).getText();
        Assert.assertEquals(header, "JavaScript Alerts");

        // Тестируем Alert
        driver.findElement(org.openqa.selenium.By.cssSelector("button[onclick='jsAlert()']")).click();

        // Принимаем alert
        driver.switchTo().alert().accept();

        // Проверяем результат
        String result = driver.findElement(org.openqa.selenium.By.id("result")).getText();
        Assert.assertTrue(result.contains("You successfully clicked an alert"));

        System.out.println("✓ Тест 8 пройден: JavaScript Alerts работают корректно");
    }
}