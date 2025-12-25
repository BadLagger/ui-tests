package mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileTestSetup {
    protected AppiumDriver driver;

    private static final String DEVICE_NAME = "emulator-5554"; // Или имя реального устройства
    private static final String PLATFORM_VERSION = "16.0";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        System.out.println("=== Настройка мобильного тестового окружения ===");
        System.out.println("Устройство: " + DEVICE_NAME);
        System.out.println("Appium сервер: " + APPIUM_SERVER_URL);

        // Настройки для эмулятора Android
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(DEVICE_NAME) // Имя вашего эмулятора
                .setPlatformVersion(PLATFORM_VERSION) // Версия Android
                .setAppPackage("org.wikipedia")
                .setAppActivity("org.wikipedia.main.MainActivity")
                .setAutoGrantPermissions(true) // Автоматическое предоставление разрешений
                .setNoReset(false) // Не сбрасывать состояние приложения между тестами
                .setFullReset(false) // Не переустанавливать приложение
                .setAvd("Pixel_4a") // Имя AVD, если используете эмулятор
                .setAvdLaunchTimeout(Duration.ofSeconds(90))
                .setAvdReadyTimeout(Duration.ofSeconds(90));

        // URL Appium сервера (по умолчанию localhost:4723)
        URL appiumServerUrl = new URL(APPIUM_SERVER_URL);

        try {
            driver = new AndroidDriver(appiumServerUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("Драйвер успешно создан");
        } catch (Exception e) {
            System.err.println("Ошибка при создании драйвера: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Завершение мобильного теста...");
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Драйвер успешно закрыт");
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
            }
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}