import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;
import utils.BrowserFactory;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderFormParameterizedTest {

    private static WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;

    public OrderFormParameterizedTest(String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
    }

    @BeforeClass
    public static void setup() throws Exception {
        // Читаем название браузера из системного свойства или другого источника конфигурации
        String browser = System.getProperty("browser", "chrome");

        // Создаем драйвер с помощью фабрики браузеров
        driver = BrowserFactory.createDriver(browser);

        // Максимизируем окно браузера
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters(name = "{index}: First Name={0}, Last Name={1}, Address={2}, Metro Station={3}, Phone Number={4}")
    public static Object[][] data() {
        return new Object[][] {
                { "Иван", "Иванов", "Ивановская, 1", "Черкизовская", "+79219219219" },
                { "Петр", "Петров", "Петровская, 1", "Сокольники", "+79819819819" }
        };
    }

    @Test
    public void testOrderFlowFromTopButton() {
        // Переход на домашнюю страницу
        driver.get("http://yourwebsite.com");

        // Создаем объект домашней страницы
        MainPage mainPage = new MainPage(driver);

        // Нажатие на верхнюю кнопку "Заказать"
        mainPage.clickOrderButtonTop();

        // Создаем объект страницы заказа
        OrderPage orderPage = new OrderPage(driver);

        // Заполняем форму данными
        orderPage.setFirstName(firstName);               // Вводим имя
        orderPage.setLastName(lastName);                 // Вводим фамилию
        orderPage.setAddress(address);                   // Вводим адрес
        orderPage.selectMetroStation(metroStation);      // Выбираем станцию метро
        assertEquals(orderPage.getSelectedMetroValue(), metroStation); // Проверяем, что выбрана правильная станция
        orderPage.setPhoneNumber(phoneNumber);           // Вводим телефон

        // Завершаем тестирование
    }

    @Test
    public void testOrderFlowFromBottomButton() {
        // Переход на домашнюю страницу
        driver.get("http://yourwebsite.com");

        // Создаем объект домашней страницы
        MainPage mainPage = new MainPage(driver);

        // Нажатие на нижнюю кнопку "Заказать"
        mainPage.clickOrderButtonBottom();

        // Создаем объект страницы заказа
        OrderPage orderPage = new OrderPage(driver);

        // Заполняем форму данными
        orderPage.setFirstName(firstName);               // Вводим имя
        orderPage.setLastName(lastName);                 // Вводим фамилию
        orderPage.setAddress(address);                   // Вводим адрес
        orderPage.selectMetroStation(metroStation);      // Выбираем станцию метро
        assertEquals(orderPage.getSelectedMetroValue(), metroStation); // Проверяем, что выбрана правильная станция
        orderPage.setPhoneNumber(phoneNumber);           // Вводим телефон

        // Завершаем тестирование
    }
}