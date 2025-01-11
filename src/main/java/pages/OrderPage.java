package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;
import java.time.Duration;

public class OrderPage {

    private WebDriver driver;
    private final WebDriverWait wait;
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroSelect = By.className("select-search__value");
    private final By phoneNumberInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.xpath("//div[@class='Dropdown-control']");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderConfirmationButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Да')]");
    private final By confirmationWindow = By.className("Order_ModalHeader__3FDaJ");
    private final By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[contains(text(), 'Заказать')]");
    private final By theNextButton = By.xpath("//button[contains(text(), 'Далее')]");
    private final By secondOrderButtonBottom = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Используем Duration.ofSeconds()
    }

    // Вводим имя
    public void setFirstName(String firstName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)); // Дожидаемся появления элемента
        element.sendKeys(firstName); // Вводим данные.
    }

    // Вводим фамилию
    public void setLastName(String lastName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)); // Дожидаемся появления элемента
        element.sendKeys(lastName); // Вводим данные
    }

    // Вводим адрес
    public void setAddress(String address) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput)); // Дожидаемся появления элемента
        element.sendKeys(address); // Вводим данные
    }

    //Выбираем станцию метро
    public void selectMetroStation(String stationNumber) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(metroSelect)); // Дожидаемся доступности элемента для клика
        element.click(); // Открываем список станций

        // Формируем локатор с учетом переданного номера станции
        By metroOption = By.xpath("//li[@class='select-search__row' and @data-index='" + stationNumber + "']");

        // Дожидаемся появления опции станции
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(metroOption));

        // Находим элемент с нужной станцией
        WebElement targetOption = option.findElement(metroOption);

        // Скроллим до элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", targetOption);

        // Кликаем на нужную станцию
        targetOption.click();
    }

    // Вводим номер телефона
    public void setPhoneNumber(String phoneNumber) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput)); // Дожидаемся появления элемента
        element.sendKeys(phoneNumber); // Вводим данные
    }

    // Выбираем дату
    public void selectDate (String  dayNumber) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dateInput)); // Дожидаемся доступности элемента для клика
        element.sendKeys(dayNumber); // Вписываем дату

        //Найдем элемент, который хотим скрыть
        WebElement popupDiv = driver.findElement(By.className("react-datepicker-popper"));

        // Выполним JavaScript-код для скрытия элемента
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'hidden';", popupDiv);

    }

    //Выбираем срок аренды
    public void choosingRentalPeriod (String meaningText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriod)); // Дожидаемся доступности элемента для клика
        element.click(); // Открываем список дней до аренды

        // Формируем локатор с учетом переданного текста периода
        By rentalOption = By.xpath("//div[@class='Dropdown-option' and text()='" + meaningText + "']");

        // Дожидаемся появления опции периода
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalOption));

        // Находим элемент с нужным периодом
        WebElement targetRentalOption = option.findElement(rentalOption);

        // Скроллим до элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", targetRentalOption);

        // Кликаем на нужный период
        targetRentalOption.click();
    }

    //Выбираем цвет
    public void checkColor (String color){
        driver.findElement(By.xpath("//input[@id='"+color+"']")).click();
    }

    // Комментарий для курьера
    public void commentCourier (String comment) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(commentInput)); // Дожидаемся появления элемента
        element.sendKeys(comment); // Вводим данные
    }

    // Подтверждаем заказ
    public void orderConfirmation () {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderConfirmationButton)); // Дожидаемся доступности элемента для клика
        element.click(); // Подтверждаем заказ
    }

    //Появление окна подтверждения заказа
    public void confirmationWindow (){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationWindow));
        Assert.assertTrue("Элемент не появился на странице.", element.isDisplayed());
    }

    // Метод для нажатия на нижнюю кнопку "Заказать"
    public void clickOrderButtonBottom() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)); // Дожидаемся доступности элемента для клика
        element.click();

    }

    // Метод для нажатия на нижнюю кнопку "Далее"
    public void theNextButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(theNextButton)); // Дожидаемся доступности элемента для клика
        element.click();

    }

    // Метод для нажатия на второй странице кнопку "Заказать"
    public void secondOrderButtonBottom() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(secondOrderButtonBottom)); // Дожидаемся доступности элемента для клика
        element.click();

    }
}