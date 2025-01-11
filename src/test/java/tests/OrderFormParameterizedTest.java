package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;
import utils.BrowserFactory;

@RunWith(Parameterized.class)
public class OrderFormParameterizedTest {

    private static WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String dateNumber;
    private final String meaningText;
    private final String color;
    private final String commentInput;

    public OrderFormParameterizedTest(String firstName, String lastName, String address, String metroStation, String phoneNumber, String dateNumber, String meaningText, String color, String commentInput) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.dateNumber = dateNumber;
        this.meaningText = meaningText;
        this.color = color;
        this.commentInput = commentInput;
    }

    @BeforeClass
    public static void setup() throws Exception {
        // Читаем название браузера из системного свойства или другого источника конфигурации
        String browser = System.getProperty("browser", "chrome");

        // Создаем драйвер с помощью фабрики браузеров
        driver = BrowserFactory.createDriver(browser);

        // Максимизируем окно браузера.
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { "Иван", "Иванов", "Ивановская, 1", "1", "+79219219219", "28.12.2024", "двое суток","black", "Комментарий 1"},
                { "Петр", "Петров", "Петровская, 1", "3", "+79819819819", "29.12.2024", "трое суток", "grey", "Комментарий 2"}
        };
    }

    @Test
    public void testOrderFlowFromTopButton() {
        // Переход на домашнюю страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

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
        orderPage.setPhoneNumber(phoneNumber);           // Вводим телефон


        // Нажатие на кнопку "Далее"
        orderPage.theNextButton();

        orderPage.selectDate(dateNumber);// Выбираем дату
        orderPage.choosingRentalPeriod(meaningText);// Выбираем период
        orderPage.checkColor(color);
        orderPage.commentCourier(commentInput);

        // Нажатие на кнопку "Заказать"
        orderPage.secondOrderButtonBottom();

        // Подтверждаем заказ
        orderPage.orderConfirmation();

        //Появилось окно подтверждения заказа
        orderPage.confirmationWindow();

        // Завершаем тестирование
    }

    @Test
    public void testOrderFlowFromBottomButton() {
        // Переход на домашнюю страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Создаем объект домашней страницы
        MainPage mainPage = new MainPage(driver);

        // Нажатие кнопки "Куки"
        mainPage.clickCookieButton();

        // Нажатие на нижнюю кнопку "Заказать"
        mainPage.clickOrderButtonBottom();

        // Создаем объект страницы заказа
        OrderPage orderPage = new OrderPage(driver);

        // Заполняем форму данными
        orderPage.setFirstName(firstName);               // Вводим имя
        orderPage.setLastName(lastName);                 // Вводим фамилию
        orderPage.setAddress(address);                   // Вводим адрес
        orderPage.selectMetroStation(metroStation);      // Выбираем станцию метро
        orderPage.setPhoneNumber(phoneNumber);           // Вводим телефон


        // Нажатие на кнопку "Далее"
        orderPage.theNextButton();

        orderPage.selectDate(dateNumber);// Выбираем дату
        orderPage.choosingRentalPeriod(meaningText);// Выбираем период
        orderPage.checkColor(color);
        orderPage.commentCourier(commentInput);

        // Нажатие на кнопку "Заказать"
        orderPage.secondOrderButtonBottom();

        // Подтверждаем заказ
        orderPage.orderConfirmation();

        //Появилось окно подтверждения заказа
        orderPage.confirmationWindow();

        // Завершаем тестирование
    }
}