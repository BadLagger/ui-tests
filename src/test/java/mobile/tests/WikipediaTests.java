package mobile.tests;

import io.appium.java_client.AppiumDriver;
import mobile.pages.*;
import mobile.utils.MobileTestSetup;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaTests extends MobileTestSetup {

    @Test(priority = 1, description = "Тест запуска приложения и пропуска welcome экрана")
    public void testAppLaunchAndSkipWelcome() {
        System.out.println("Тест 1: Запуск приложения...");

        WelcomePage welcomePage = new WelcomePage(driver);

        // Проверяем, что welcome экран отображается
        if (welcomePage.isSkipButtonDisplayed()) {
            System.out.println("Найден экран приветствия, пропускаем...");
            welcomePage.clickSkip();
        }

        // После пропуска должен отобразиться главный экран
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isSearchBoxDisplayed(),
                "После запуска приложения должна отображаться строка поиска");

        System.out.println("✓ Приложение успешно запущено, главный экран отображается");
    }

    @Test(priority = 2, description = "Тест поиска статьи")
    public void testSearchArticle() {
        System.out.println("Тест 2: Поиск статьи...");

        // Пропускаем welcome экран если есть
        WelcomePage welcomePage = new WelcomePage(driver);
        if (welcomePage.isSkipButtonDisplayed()) {
            welcomePage.clickSkip();
        }

        // Ищем статью
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSearchBox();
        mainPage.enterSearchText("Appium");

        SearchPage searchPage = new SearchPage(driver);

        // Проверяем, что есть результаты поиска
        Assert.assertTrue(searchPage.hasSearchResults(),
                "Поиск должен возвращать результаты");

        System.out.println("Найдено результатов: " + searchPage.getSearchResultsCount());

        // Открываем первую статью
        searchPage.clickFirstResult();

        ArticlePage articlePage = new ArticlePage(driver);

        // Проверяем, что статья открылась
        Assert.assertTrue(articlePage.isArticleTitleDisplayed(),
                "Заголовок статьи должен отображаться");

        String articleTitle = articlePage.getArticleTitle();
        Assert.assertTrue(articleTitle.contains("Appium"),
                "Заголовок статьи должен содержать 'Appium'");

        System.out.println("✓ Статья успешно найдена и открыта: " + articleTitle);
    }

    @Test(priority = 3, description = "Тест проверки заголовка открытой статьи")
    public void testArticleTitleVerification() {
        System.out.println("Тест 3: Проверка заголовка статьи...");

        // Пропускаем welcome экран
        WelcomePage welcomePage = new WelcomePage(driver);
        if (welcomePage.isSkipButtonDisplayed()) {
            welcomePage.clickSkip();
        }

        // Ищем и открываем статью
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSearchBox();
        mainPage.enterSearchText("Selenium");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickFirstResult();

        ArticlePage articlePage = new ArticlePage(driver);

        // Получаем и проверяем заголовок
        String title = articlePage.getArticleTitle();
        Assert.assertNotNull(title, "Заголовок статьи не должен быть null");
        Assert.assertFalse(title.isEmpty(), "Заголовок статьи не должен быть пустым");

        // Проверяем, что заголовок содержит искомое слово
        Assert.assertTrue(title.contains("Selenium") ||
                        title.contains("селен") ||
                        title.contains("Селен"),
                "Заголовок должен содержать ключевое слово");

        System.out.println("✓ Заголовок статьи проверен: " + title);
    }

    @Test(priority = 4, description = "Тест навигации назад из статьи")
    public void testBackNavigationFromArticle() {
        System.out.println("Тест 4: Навигация назад из статьи...");

        // Пропускаем welcome экран
        WelcomePage welcomePage = new WelcomePage(driver);
        if (welcomePage.isSkipButtonDisplayed()) {
            welcomePage.clickSkip();
        }

        // Ищем и открываем статью
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSearchBox();
        mainPage.enterSearchText("Java");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickFirstResult();

        ArticlePage articlePage = new ArticlePage(driver);

        // Запоминаем заголовок статьи
        String articleTitle = articlePage.getArticleTitle();

        // Возвращаемся назад
        articlePage.clickBackButton();

        // Проверяем, что вернулись на страницу поиска
        Assert.assertTrue(searchPage.hasSearchResults() ||
                        mainPage.isSearchBoxDisplayed(),
                "После навигации назад должна отображаться либо страница поиска, либо главный экран");

        System.out.println("✓ Навигация назад работает корректно. Была открыта статья: " + articleTitle);
    }

    @Test(priority = 5, description = "Тест пустого поиска")
    public void testEmptySearch() {
        System.out.println("Тест 5: Проверка пустого поиска...");

        // Пропускаем welcome экран
        WelcomePage welcomePage = new WelcomePage(driver);
        if (welcomePage.isSkipButtonDisplayed()) {
            welcomePage.clickSkip();
        }

        // Открываем поиск и вводим несуществующий запрос
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSearchBox();
        mainPage.enterSearchText("zxcvbnmasdfghjklqwertyuiop1234567890");

        SearchPage searchPage = new SearchPage(driver);

        // Ждем немного для загрузки результатов
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Должно быть сообщение об отсутствии результатов или 0 результатов
        boolean noResults = searchPage.isEmptySearchMessageDisplayed() ||
                searchPage.getSearchResultsCount() == 0;

        Assert.assertTrue(noResults,
                "При поиске несуществующего запроса должно отображаться сообщение об отсутствии результатов");

        // Очищаем поиск
        mainPage.clearSearch();

        System.out.println("✓ Пустой поиск обрабатывается корректно");
    }
}