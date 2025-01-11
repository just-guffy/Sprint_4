package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    // Шаблонные локаторы для заголовков и панелей списка.
    private static final String ACCORDION_HEADING_TEMPLATE = "accordion__heading-%d";
    private static final String ACCORDION_PANEL_TEMPLATE = "accordion__panel-%d";

    // Локаторы кнопок "Заказать"
    private final By orderButtonTop = By.xpath("//div[@class='Header_Nav__AGCXC']/button[contains(text(), 'Заказать')]");
    private final By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[contains(text(), 'Заказать')]");

    //Локатор кнопки "Куки"
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    // Метод для нажатия на кнопку "Куки"
    public void clickCookieButton() {
        if (!driver.findElements(cookieButton).isEmpty()) { // Проверяем наличие элемента
            driver.findElement(cookieButton).click(); // Нажимаем на элемент
        }
    }

    // Метод для получения заголовка списка по индексу
    public WebElement getAccordionHeading(int index) {
        String id = String.format(ACCORDION_HEADING_TEMPLATE, index);
        return driver.findElement(By.id(id));
    }

    // Метод для получения панели списка по индексу
    public WebElement getAccordionPanel(int index) {
        String id = String.format(ACCORDION_PANEL_TEMPLATE, index);
        return driver.findElement(By.id(id));
    }

    // Метод для нажатия на верхнюю кнопку "Заказать"
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    // Метод для нажатия на нижнюю кнопку "Заказать"
    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

}