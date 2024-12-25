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

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    // Шаблонные локаторы для заголовков и панелей списка.
    private static final String ACCORDION_HEADING_TEMPLATE = "accordion__heading-%d";
    private static final String ACCORDION_PANEL_TEMPLATE = "accordion__panel-%d";

    // Локаторы кнопок "Заказать"
    private final By orderButtonTop = By.cssSelector(".Button_Button__ra12g");
    private final By orderButtonBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    //Локатор кнопки "Куки"
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    // Метод для нажатия на кнопку "Куки"
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
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

    // Метод для проверки видимости панели после клика на заголовке
    public boolean isPanelVisible(int index) {
        WebElement heading = getAccordionHeading(index);
        WebElement panel = getAccordionPanel(index);

        // Ожидание до появления heading перед кликом
        wait.until(ExpectedConditions.visibilityOf(heading));
        heading.click();

        // Ожидание до появления panel после клика
        wait.until(ExpectedConditions.visibilityOf(panel));
        return panel.isDisplayed();
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