package trello.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static trello.demo.utils.TestData.testData;

public class ListOnBoardPage extends BasePage{

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") +
            "/boards";

    public ListOnBoardPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CardOnListPage getCardOnList(String cardName){

        return new CardOnListPage(driver);
    }

    @Override
    public ListOnBoardPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
