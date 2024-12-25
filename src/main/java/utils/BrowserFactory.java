package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver createDriver(String browser) throws Exception {
        if ("chrome".equals(browser.toLowerCase())) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver\\chromedriver.exe");
            return new ChromeDriver();
        } else if ("firefox".equals(browser.toLowerCase())) {
            System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);//.
        }
    }
}