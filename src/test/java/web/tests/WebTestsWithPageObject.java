package web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import web.pages.MainPage;
import web.utils.TestSetup;

public class WebTestsWithPageObject extends TestSetup {

    @Test
    public void testMainPageWithPageObject() {
        // Используем Page Object
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        // Проверки через Page Object
        Assert.assertEquals(mainPage.getTitle(), "The Internet");
        Assert.assertEquals(mainPage.getHeaderText(), "Welcome to the-internet");
        Assert.assertEquals(mainPage.getSubheaderText(), "Available Examples");
        Assert.assertTrue(mainPage.getExampleLinksCount() > 0);

        System.out.println("Page Object тест пройден! Найдено ссылок: " + mainPage.getExampleLinksCount());
    }

    @Test
    public void testNavigationWithPageObject() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        // Переходим на страницу логина
        mainPage.clickFormAuthentication();

        // Проверяем URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"));
        Assert.assertEquals(driver.getTitle(), "The Internet");

        System.out.println("Навигация через Page Object пройдена!");
    }
}