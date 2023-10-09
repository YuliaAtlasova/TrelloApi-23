package trello.demo.pages;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final int WAIT_TIMEOUT_SECONDS_LONG = 10;
    protected final int WAIT_TIMEOUT_SECONDS_SHORT = 5;
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void until(ExpectedCondition condition) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS_SHORT)).until(condition);
        } catch (AssertionError Ae) {
            Ae.printStackTrace();
        }
    }

    protected BasePage openPage() {
        throw new NotImplementedException();
    }
}
