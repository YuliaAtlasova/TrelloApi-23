package trello.demo.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    protected BasePage openPage() {
        return null;
    }

    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected BasePage(WebDriver driver)
    {
        this.driver = driver;
    }
}
