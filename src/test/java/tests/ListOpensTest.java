package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utils.BrowserFactory;
import static org.junit.Assert.*;

public class ListOpensTest {

    private static WebDriver driver;
    private static MainPage accordionPage;

    @BeforeClass
    public static void setup() throws Exception {
        // Читаем название браузера из системного свойства или другого источника конфигурации
        String browser = System.getProperty("browser", "chrome");

        // Создаем драйвер с помощью фабрики браузеров.
        driver = BrowserFactory.createDriver(browser);

        // Максимизируем окно браузера
        driver.manage().window().maximize();

        // Создаем объект страницы списка
        accordionPage = new MainPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testAllAccordionPanelsVisibility() {
        // Переход на страницу списка
        driver.get("https://qa-scooter.praktikum-services.ru/");

        accordionPage.clickCookieButton();

        // Количество элементов списка
        int totalElements = 8;

        for (int i = 0; i < totalElements; i++) {
            boolean isVisible = accordionPage.isPanelVisible(i);
            assertTrue("Панель " + i + " должна быть видима после клика на заголовке", isVisible);
        }
    }
}