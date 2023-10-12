package trello.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final int WAIT_TIMEOUT_SECONDS_LONG = 10;
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriverWait longWait(){
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_LONG));
    }
}
