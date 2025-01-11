package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import utils.BrowserFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ListOpensTest {

    private static WebDriver driver;
    private static MainPage accordionPage;

    private int index;
    private String expectedText;

    public ListOpensTest(int index, String expectedText) {
        this.index = index;
        this.expectedText = expectedText;
    }

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
    public void testAccordionPanelVisibilityAndContent() {
        // Переход на страницу списка
        driver.get("https://qa-scooter.praktikum-services.ru/");

        accordionPage.clickCookieButton();

        WebElement panel = accordionPage.getAccordionPanel(index);

        // Ожидание до появления заголовка перед кликом
        accordionPage.wait.until(ExpectedConditions.visibilityOf(accordionPage.getAccordionHeading(index)));
        accordionPage.getAccordionHeading(index).click();

        // Ожидание до появления панели после клика
        accordionPage.wait.until(ExpectedConditions.visibilityOf(panel));

        // Получение текста панели
        String actualText = panel.getText();

        // Проверки
        assertTrue("Панель " + index + " должна быть видима после клика на заголовке", panel.isDisplayed());
        assertEquals("Текст панели " + index + " не соответствует ожидаемому.", expectedText, actualText);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
}