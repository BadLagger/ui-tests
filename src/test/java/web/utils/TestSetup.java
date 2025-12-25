package web.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestSetup {
    protected WebDriver driver;
    protected WebDriverHelper helper;

    @BeforeMethod
    public void setUp() {
        System.out.println("Настройка тестовой среды...");

        try {
            // Автоматическая загрузка драйвера
            WebDriverManager.chromedriver().setup();

            // Настройка опций браузера
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            // Создание драйвера
            driver = new ChromeDriver(options);

            // Настройка таймаутов
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

            // Максимизация окна
            driver.manage().window().maximize();

            // Инициализация хелпера
            helper = new WebDriverHelper(driver);

            System.out.println("Тестовая среда настроена успешно");

        } catch (Exception e) {
            System.err.println("Ошибка при настройке тестовой среды: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Завершение теста...");

        if (driver != null) {
            try {
                // Делаем скриншот при неудачном тесте (можно расширить)
                driver.quit();
                System.out.println("Драйвер успешно закрыт");
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
            }
        }
    }
}